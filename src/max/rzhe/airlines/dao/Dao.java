package max.rzhe.airlines.dao;

import max.rzhe.airlines.entity.Entity;

import java.io.Serializable;
import java.util.List;

public interface Dao<T extends Entity, PK extends Serializable> {
    T findById(PK id) throws DaoException;

    List<T> findAll() throws DaoException;

    PK create(T entity) throws DaoException;

    void update(T entity) throws DaoException;

    void delete(PK id) throws DaoException;

}
