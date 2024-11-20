package examples.grpc.server.grpc;

import examples.grpc.server.service.HelloWorldService;
import grpc.examples.helloworld.HelloReply;
import grpc.examples.helloworld.HelloRequest;
import grpc.examples.helloworld.HelloWorldServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldServer extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {

    private final HelloWorldService helloWorldService;

    public HelloWorldServer(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        //System.out.println("Received");
        responseObserver.onNext(HelloReply.newBuilder().setMessage(helloWorldService.sayHello(request.getName())).build());
        responseObserver.onCompleted();
    }
}
