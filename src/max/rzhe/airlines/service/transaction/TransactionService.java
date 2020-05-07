package max.rzhe.airlines.service.transaction;

public class TransactionService {
    private TransactionHandler transaction;

    protected TransactionHandler getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionHandler transaction) {
        this.transaction = transaction;
    }


}
