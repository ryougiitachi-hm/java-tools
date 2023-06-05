package per.itachi.java.tools.danmuku.app.exception;

/**
 * Used when html parse fails to parse html file.
 * */
public class InvalidHtmlException extends RuntimeException{

    public InvalidHtmlException(String message) {
        super(message);
    }

    public InvalidHtmlException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidHtmlException(Throwable cause) {
        super(cause);
    }
}
