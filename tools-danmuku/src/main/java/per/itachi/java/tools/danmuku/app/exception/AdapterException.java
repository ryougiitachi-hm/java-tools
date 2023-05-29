package per.itachi.java.tools.danmuku.app.exception;

public class AdapterException extends RuntimeException{

    public AdapterException(String message) {
        super(message);
    }

    public AdapterException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdapterException(Throwable cause) {
        super(cause);
    }
}
