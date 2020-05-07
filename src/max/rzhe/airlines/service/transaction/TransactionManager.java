package max.rzhe.airlines.service.transaction;

import max.rzhe.airlines.service.exception.ServiceException;

public interface TransactionManager {
   void execute() throws ServiceException;
}
