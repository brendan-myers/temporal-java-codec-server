package com.brendan.temporal;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brendan.temporal.helloWorkflow.HelloWorkflow;
import com.google.protobuf.util.JsonFormat;

import io.temporal.api.common.v1.Payload;
import io.temporal.api.common.v1.Payloads;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class WorkflowController {

    @Autowired
    private WorkflowClient client;

    @GetMapping("/hello")
    String hello(String name) throws Exception {
        HelloWorkflow workflow = client.newWorkflowStub(
            HelloWorkflow.class, 
            WorkflowOptions.newBuilder()
                .setTaskQueue("hello-queue")
                .setWorkflowId("hello-workflow")
                .build()
            );

        return workflow.sayHi(name);
    }

    @PostMapping("/decode")
    @CrossOrigin(origins = {"http://localhost:8233", "https://cloud.temporal.io"})
    void decode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Payloads.Builder incomingPayloads = Payloads.newBuilder();
        InputStreamReader ioReader = new InputStreamReader(request.getInputStream());
        JsonFormat.parser().merge(ioReader, incomingPayloads);

        CryptCodec codec = new CryptCodec();

        List<Payload> incomingPayloadsList = incomingPayloads.build().getPayloadsList();
        List<Payload> outgoingPayloadsList = codec.decode(incomingPayloadsList);

        OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream());

        JsonFormat.printer().appendTo(
          Payloads.newBuilder().addAllPayloads(outgoingPayloadsList).build(), out);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(200);

        out.flush();
        out.close();
    }
}
