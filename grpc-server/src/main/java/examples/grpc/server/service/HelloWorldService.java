package examples.grpc.server.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class HelloWorldService {

    private static int nbr = 0;

    public String sayHello(String name) {
        nbr++;
        System.out.println("received : " + nbr);
        int sleepTime = ThreadLocalRandom.current().nextInt(1, 4) * 1000;
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ignored) {
        }
        return "Hello ".concat(name).concat(" !");
    }

}
