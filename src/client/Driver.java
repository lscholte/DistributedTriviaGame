package client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import utilities.AddressParser;
import utilities.Logger;

public class Driver {

    public static void main(String[] args)
            throws UnknownHostException, IOException, InterruptedException {
        Client client = new Client();
        client.start();
    }
}
