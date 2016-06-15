package org.evilcamp.v.framework.exception;

public class VException extends RuntimeException {

    public VException() {
        super();
    }


    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public VException(String message, Throwable cause,
                            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * @param message
     * @param cause
     */
    public VException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public VException(String message) {
        super(message);
    }

    public VException(Throwable cause) {
        super(cause);
    }

}
