
package com.brendan.temporal;

import java.util.Collections;

import javax.annotation.Nonnull;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.temporal.client.WorkflowClientOptions;
import io.temporal.common.converter.CodecDataConverter;
import io.temporal.common.converter.DefaultDataConverter;
import io.temporal.spring.boot.TemporalOptionsCustomizer;

@Configuration
public class WorkflowClientCustomizer {
    
    @Bean
    public TemporalOptionsCustomizer<WorkflowClientOptions.Builder> customClientOptions() {
      return new TemporalOptionsCustomizer<WorkflowClientOptions.Builder>() {
        @Nonnull
        @Override
        public WorkflowClientOptions.Builder customize(
            @Nonnull WorkflowClientOptions.Builder optionsBuilder) {

                optionsBuilder.setDataConverter(new CodecDataConverter(
                    DefaultDataConverter.newDefaultInstance(),
                    Collections.singletonList(new CryptCodec())
                ));
          
          return optionsBuilder;
        }
      };
    }
}