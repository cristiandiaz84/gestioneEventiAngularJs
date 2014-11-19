package it.cspnet.gestioneeventi.exception;

public class GandalfException extends Exception{

    public GandalfException() {
    }

    public GandalfException(String message) {
        super(message);
    }

    public GandalfException(String message, Throwable cause) {
        super(message, cause);
    }

    public GandalfException(Throwable cause) {
        super(cause);
    }

    public GandalfException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    
}
