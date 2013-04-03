package util.exception;

public class UtilityException extends Exception {

    private static final long serialVersionUID = 1L;

    public UtilityException() {
	super();
    }

    public UtilityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

    public UtilityException(String message, Throwable cause) {
	super(message, cause);
    }

    public UtilityException(String message) {
	super(message);
    }

    public UtilityException(Throwable cause) {
	super(cause);
    }

}
