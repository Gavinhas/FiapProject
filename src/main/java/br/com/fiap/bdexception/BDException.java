package br.com.fiap.bdexception;

public class BDException extends Exception {

    public BDException() {
    }

    public BDException(String message) {
        super(message);
    }

    public BDException(String message, Throwable cause) {
        super(message, cause);
    }

    public BDException(Throwable cause) {
        super(cause);
    }

    public BDException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}