package examples.grpc.client.controller;

import grpc.examples.helloworld.HelloRequest;
import grpc.examples.helloworld.HelloWorldServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class HelloWorldController {

    private final HelloWorldServiceGrpc.HelloWorldServiceFutureStub helloWorldServiceBlockingStub;

    public HelloWorldController() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
        this.helloWorldServiceBlockingStub = HelloWorldServiceGrpc.newFutureStub(channel);
    }


    @GetMapping("say-hello/{name}")
    public String sayHello(@PathVariable("name") String name) throws ExecutionException, InterruptedException {
        //return "hello ".concat(name);
        return helloWorldServiceBlockingStub.sayHello(HelloRequest.newBuilder().setName(name).build()).get().getMessage();
    }
}
