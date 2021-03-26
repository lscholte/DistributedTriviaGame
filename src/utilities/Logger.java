package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Logger {
  
  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
  private static final Appendable APPENDABLE = System.out;
  
  private Logger() {}
  
  public static void logInfo(String message) {
    log("INFO", message);
  }
  
  public static void logError(String message) {
    log("ERROR", message);
  }
  
  private static void log(String level, String message) {
    String formattedMessage = 
        String.format(
            "%s : %016X : %s : %s",
            DATE_FORMAT.format(new Date()),
            Thread.currentThread().getId(),
            level,
            message)
        + System.lineSeparator();
    
    try {
      APPENDABLE.append(formattedMessage);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

}
