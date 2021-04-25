package game;

import java.io.IOException;
import java.net.UnknownHostException;

public class Driver {

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {    
        Server server = new Server(args[0]);
        server.start();
    }

}
