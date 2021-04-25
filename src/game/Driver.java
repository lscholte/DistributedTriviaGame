package game;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import utilities.Logger;

public class Driver {

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {    

        int serverPort;
        try {
            serverPort = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException e) {
            return;
        }

        Server server = new Server(serverPort);
        server.start();
    }

}
