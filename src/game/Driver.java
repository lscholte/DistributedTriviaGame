package game;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import utilities.AddressParser;
import utilities.Logger;

public class Driver {
    
    private static final String USAGE = "USAGE: java -jar ./Server.jar <mongoDbHost:port> ...";

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {        
        List<InetSocketAddress> mongoDbAddresses = new ArrayList<>();
        for (int i = 0; i < args.length; ++i) {
            InetSocketAddress serverAddress = AddressParser.parseAddress(args[i]);
            if (serverAddress == null) {
                Logger.logError(USAGE);
                return;
            }

            mongoDbAddresses.add(serverAddress);
        }

        //        args = new String[1];
        //        args[0] = "172.26.0.4:27017,172.26.0.3:27017,172.26.0.2:27017";
        Server server = new Server(mongoDbAddresses);
        server.start();
    }

}
