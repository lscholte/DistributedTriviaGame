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
        serverAddresses.add(new InetSocketAddress("localhost", 10000));
        serverAddresses.add(new InetSocketAddress("localhost", 11000));
        serverAddresses.add(new InetSocketAddress("localhost", 12000));
        serverAddresses.add(new InetSocketAddress("localhost", 13000));
        serverAddresses.add(new InetSocketAddress("localhost", 14000));

        Coordinator coordinator = new Coordinator(serverAddresses);
        coordinator.start();
    }

}
