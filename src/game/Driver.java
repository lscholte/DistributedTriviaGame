package game;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import utilities.AddressParser;
import utilities.Logger;

public class Driver {
    
    private static final String USAGE = "USAGE: java -jar ./Server.jar port <mongoDbHost:port> ...";

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {        
        
        int serverPort;
        try {
          serverPort = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException e) {
          Logger.logError(USAGE);
          return;
        }
        
        List<InetSocketAddress> mongoDbAddresses = new ArrayList<>();
        for (int i = 1; i < args.length; ++i) {
            InetSocketAddress serverAddress = AddressParser.parseAddress(args[i]);
            if (serverAddress == null) {
                Logger.logError(USAGE);
                return;
            }
            mongoDbAddresses.add(serverAddress);
        }

        Server server = new Server(serverPort, mongoDbAddresses);
        server.start();
    }
}
