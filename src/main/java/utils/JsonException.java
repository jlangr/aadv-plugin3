package utils;

public class JsonException extends RuntimeException {
   public JsonException(String message, Exception cause) {
      super(message, cause);
   }
}
