package client;

import java.io.IOException;
import java.net.UnknownHostException;

public class Driver {
    
  public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {    
    Client client = new Client();
    client.start();
  }

}
