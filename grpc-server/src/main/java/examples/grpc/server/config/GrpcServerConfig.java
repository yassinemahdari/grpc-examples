package examples.grpc.server.config;

import examples.grpc.server.grpc.HelloWorldServer;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GrpcServerConfig {

    private Server server;
    private final HelloWorldServer helloWorldServer;

    public GrpcServerConfig(HelloWorldServer helloWorldServer) {
        this.helloWorldServer = helloWorldServer;
    }

    @PostConstruct
    private void initGrpcServer() {
        new Thread() {
            @Override
            public void run() {
                int port = 50051;
                server = ServerBuilder.forPort(port).addService(helloWorldServer).build();
                try {
                    server.start();
                    server.awaitTermination();
                } catch (IOException | InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }.start();
    }

    @PreDestroy
    private void destroy() {
        if (server != null)
            server.shutdownNow();
    }

}
