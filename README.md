# Temporal Java Codec Server

A Temporal codec server in Java hacked together with pieces stolen from the Temporal Java SDK samples.

## Requirements

*(AKA What I had installed)*

* Java 1.8+
* Local Temporal Server. This is straightforward with the [Temporal CLI](https://github.com/temporalio/cli)

## Usage

* `./gradlew bootRun` - starts the server.
* `http://localhost:8080/hello?name=Hecuba` - Runs the hello workflow.
* `http://localhost:8080` - Codec Server browser endpoint.

## Learn more about Temporal and the Java SDK

* [Temporal Hompage](https://temporal.io/)
* [Temporal Java SDK Samples](https://github.com/temporalio/samples-java)
* [Java SDK Guide](https://docs.temporal.io/dev-guide/java)