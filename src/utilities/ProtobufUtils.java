package utilities;

import com.google.protobuf.Message;

public final class ProtobufUtils {
  
  public static String getPrintableMessage(Message message) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder
      .append(message.getDescriptorForType().getName())
      .append(" {")
      .append(System.lineSeparator())
      .append(message.toString())
      .append("}");
    return stringBuilder.toString();
  }

}
