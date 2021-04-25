package utilities;

import java.net.InetSocketAddress;

/**
 * A utility class for parsing hostname and port strings
 * into InetSocketAddress objects.
 * @author lscholte
 *
 */
public final class AddressParser {
  
  private AddressParser() {}
  
  /**
   * Parsers a string of the format "host:port" into an InetSocketAddress.
   * @param address the address to parse
   * @return the parsed InetSocketAddress
   */
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
