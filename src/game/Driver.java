package game;

import java.io.IOException;
import java.net.UnknownHostException;

public class Driver {

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        // TODO: remove when compiling jar
        args = new String[1];
        args[0] = "172.26.0.2:27017,172.26.0.3:27017,172.26.0.4:27017";
        Server server = new Server(args[0]);
        server.start();
    }

}
