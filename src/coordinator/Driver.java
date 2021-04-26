package coordinator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import utilities.AddressParser;

public class Driver {

    public static void main(String[] args)
            throws UnknownHostException, IOException, InterruptedException {
        
        
        List<InetSocketAddress> serverAddresses = new ArrayList<>();
        for (int i = 0; i < args.length; ++i) {
          InetSocketAddress serverAddress = AddressParser.parseAddress(args[i]);
          if (serverAddress == null) {
            return;
          }
          
          serverAddresses.add(serverAddress);
        }        
        
        Coordinator coordinator = new Coordinator(serverAddresses);
        coordinator.start();
    }

}
