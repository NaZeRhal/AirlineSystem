package max.rzhe.airlines.service;

import max.rzhe.airlines.entity.Entity;
import max.rzhe.airlines.service.exception.ServiceException;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T extends Entity, PK extends Serializable> {
    T findById(PK id) throws ServiceException;

    List<T> findAll() throws ServiceException;

    Long add(T entity) throws ServiceException;

    void edit(T entity) throws ServiceException;

    void delete(PK id) throws ServiceException;

}
