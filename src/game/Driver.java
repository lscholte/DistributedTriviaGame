package game;

import java.io.IOException;
import java.net.UnknownHostException;

public class Driver {

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {    
        Server server = new Server(10000);
        server.start();
        Server server2 = new Server(11000);
        server2.start();
        Server server3 = new Server(12000);
        server3.start();
    }

}
