package app1;

public class DataAccessException extends RuntimeException {

    /**
     * 
     */
    private static final long       serialVersionUID        = -3818928950827825468L;

    public DataAccessException() {
            super();
    }

    public DataAccessException(String message, Throwable cause) {
            super(message, cause);
    }

    public DataAccessException(String message) {
            super(message);
    }

    public DataAccessException(Throwable cause) {
            super("Unable to connect to data source", cause);
    }
}
