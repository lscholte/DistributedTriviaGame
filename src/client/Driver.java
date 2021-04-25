package client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import utilities.AddressParser;
import utilities.Logger;

public class Driver {

    public static void main(String[] args)
            throws UnknownHostException, IOException, InterruptedException {
        
        if (args.length < 1) {
            Logger.logError("Not enough arguments");
            return;
        }
        
        InetSocketAddress serverAddress = AddressParser.parseAddress(args[0]);
        if (serverAddress == null) {
          return;
        }
        
        Client client = new Client(serverAddress);
        client.start();
    }

}
