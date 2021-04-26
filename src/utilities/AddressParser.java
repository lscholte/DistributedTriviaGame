package utilities;

import java.net.InetSocketAddress;

public final class AddressParser {
  
  private AddressParser() {}
  
  public static InetSocketAddress parseAddress(String address) {
    int colonIndex = address.lastIndexOf(':');
    if (colonIndex == -1) {
      return null;
    }
    
    String host = address.substring(0, colonIndex);
    int port;
    try {
      port = Integer.parseInt(address.substring(colonIndex + 1));
    }
    catch (NumberFormatException e) {
      return null;
    }
    
    return InetSocketAddress.createUnresolved(host, port);
  }

}
