package max.rzhe.airlines.service.exception;


public class TransactionException extends ServiceException {

    public TransactionException(Throwable cause) {
        super(cause);
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }


}
