package max.rzhe.airlines.ioc;

public class IoCException extends Exception {
    public IoCException() {
    }

    public IoCException(String message) {
        super(message);
    }

    public IoCException(String message, Throwable cause) {
        super(message, cause);
    }

    public IoCException(Throwable cause) {
        super(cause);
    }


}
