package client;

import java.io.IOException;
import java.net.UnknownHostException;

import utilities.Logger;

public class Driver {
    
  public static void main(String[] args) throws UnknownHostException, IOException {    
    Client client = new Client();
    client.start();
  }

}
