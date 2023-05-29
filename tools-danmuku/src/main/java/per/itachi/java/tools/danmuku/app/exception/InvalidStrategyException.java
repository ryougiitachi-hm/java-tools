package per.itachi.java.tools.danmuku.app.exception;

public class InvalidStrategyException extends RuntimeException{

    public InvalidStrategyException(String message) {
        super(message);
    }

    public InvalidStrategyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidStrategyException(Throwable cause) {
        super(cause);
    }
}
