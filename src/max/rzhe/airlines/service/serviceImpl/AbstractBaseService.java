package max.rzhe.airlines.service.serviceImpl;

import max.rzhe.airlines.dao.Dao;
import max.rzhe.airlines.dao.DaoException;
import max.rzhe.airlines.entity.Entity;
import max.rzhe.airlines.service.exception.ServiceException;
import max.rzhe.airlines.service.transaction.TransactionHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBaseService<T extends Entity, PK extends Serializable, R extends Dao<T, PK>> {
    private R repository;

    private TransactionHandler transactionHandler;

    public void setRepository(R repository) {
        this.repository = repository;
    }

    R getRepository() {
        return repository;
    }

    public void setTransactionHandler(TransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    TransactionHandler getTransactionHandler() {
        return transactionHandler;
    }

    public T findById(PK id) throws ServiceException {
        List<T> list = new ArrayList<>();
        transactionHandler.runWithTransaction(() -> {
            try {
                T entity = repository.findById(id);
                list.add(entity);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        });
        if (list.size() == 0 || list.get(0) == null) {
            return null;
        }
        return list.iterator().next();
    }


    public List<T> findAll() throws ServiceException {
        List<T> list = new ArrayList<>();
        transactionHandler.runWithTransaction(() -> {
            try {
                List<T> entityList = repository.findAll();
                list.addAll(entityList);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        });
        if (list.size() == 0) {
            return null;
        }
        return list;
    }

    public PK add(T entity) throws ServiceException {
        List<PK> idList = new ArrayList<>();
        transactionHandler.runWithTransaction(() -> {
            try {
                PK id = repository.create(entity);
                idList.add(id);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        });
        if (idList.get(0) == null) {
            return null;
        }
        return idList.iterator().next();
    }

    public void edit(T entity) throws ServiceException {
        transactionHandler.runWithTransaction(() -> {
            try {
                repository.update(entity);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        });
    }

    public void delete(PK id) throws ServiceException {
        transactionHandler.runWithTransaction(() -> {
            try {
                repository.delete(id);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        });
    }
}
