package max.rzhe.airlines.dao.daoImpl;

import ch.qos.logback.classic.Logger;
import max.rzhe.airlines.dao.DaoException;
import max.rzhe.airlines.entity.Entity;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public abstract class AbstractBaseDao<T extends Entity, PK extends Serializable> {
    private static Logger logger = (Logger) LoggerFactory.getLogger(AbstractBaseDao.class.getName());
    private Connection connection;

    Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public abstract String getSelectQuery();

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    public abstract Object[] getParametersForCreate(T entity);

    public abstract Object[] getParametersForUpdate(T entity);

    protected abstract List<T> parseResultSet(ResultSet resultSet) throws DaoException;

    public T findById(PK id) throws DaoException {
        return selectOne(getSelectQuery() + " WHERE id = ?", id);
    }

    public List<T> findAll() throws DaoException {
        return select(getSelectQuery());
    }

    @SuppressWarnings("unchecked")
    public PK create(T entity) throws DaoException {
        PK id;
        try (PreparedStatement statement =
                     getConnection().prepareStatement(getCreateQuery(), PreparedStatement.RETURN_GENERATED_KEYS)) {
            setParameters(statement, getParametersForCreate(entity));
            int count = statement.executeUpdate();
            if (count != 1) {
                logger.error("Error: on create exists more than 1 record: {}", count);
                throw new DaoException("On create exists more than 1 record: " + count);
            }
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    id = (PK) resultSet.getObject(1, Long.class);
                } else {
                    logger.error("Error: no id is returned");
                    throw new DaoException("No id is returned");
                }
                entity.setId(id);
                return id;
            } catch (SQLException e) {
                logger.error("Error while saving to database. Caused by: message='{}', SQLState='{}', ErrorCode='{}'",
                        e.getMessage(), e.getSQLState(), e.getErrorCode());
                throw new DaoException("Error while saving to database", e);
            }
        } catch (SQLException e) {
            logger.error("Error while saving to database. Caused by: message='{}', SQLState='{}', ErrorCode='{}'",
                    e.getMessage(), e.getSQLState(), e.getErrorCode());
            throw new DaoException("Error while saving to database", e);
        }
    }

    public void update(T entity) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(getUpdateQuery())) {
            setParameters(statement, getParametersForUpdate(entity));
            int count = statement.executeUpdate();
            if (count != 1) {
                if (count == 0) {
                    create(entity);
                } else {
                    logger.error("On update exists more than 1 record: {}", count);
                    throw new DaoException("On update exists more than 1 record: " + count);
                }
            }
        } catch (SQLException e) {
            logger.error("Error while updating entity. Caused by: message='{}', SQLState='{}', ErrorCode='{}'",
                    e.getMessage(), e.getSQLState(), e.getErrorCode());
            throw new DaoException("Error while updating entity ", e);
        }
    }

    public void delete(PK id) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(getDeleteQuery())) {
            setParameters(statement, id);
            int count = statement.executeUpdate();
            if (count != 1) {
                logger.error("On delete modify more than 1 record: {}", count);
                throw new DaoException("On delete modify more than 1 record: " + count);
            }
        } catch (SQLException e) {
            logger.error("Error while deleting entity. Caused by: message='{}', SQLState='{}', ErrorCode='{}'",
                    e.getMessage(), e.getSQLState(), e.getErrorCode());
            throw new DaoException("Error while deleting entity", e);
        }
    }

    List<T> select(String sql, Object... parameters) throws DaoException {
        List<T> list;
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            setParameters(statement, parameters);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            logger.error("Error during select query. Caused by: message='{}', SQLState='{}', ErrorCode='{}'",
                    e.getMessage(), e.getSQLState(), e.getErrorCode());
            throw new DaoException("Error during select query", e);
        }
        return list;
    }

    T selectOne(String sql, Object... parameters) throws DaoException {
        List<T> list = select(sql, parameters);
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            logger.error("During selecting one record from database received more than one.");
            throw new DaoException("Received more than one record.");
        }
        return list.iterator().next();
    }

    void setParameters(PreparedStatement statement, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            Object parameter = parameters[i];
            int parameterIndex = i + 1;
            if (null == parameter) {
                statement.setObject(parameterIndex, null);
            } else if (parameter instanceof String) {
                statement.setString(parameterIndex, (String) parameter);
            } else if (parameter instanceof Integer) {
                statement.setInt(parameterIndex, (Integer) parameter);
            } else if (parameter instanceof Long) {
                statement.setLong(parameterIndex, (Long) parameter);
            } else if (parameter instanceof Boolean) {
                statement.setBoolean(parameterIndex, (Boolean) parameter);
            } else if (parameter instanceof LocalDate) {
                statement.setDate(parameterIndex, Date.valueOf((LocalDate) parameter));
            } else if (parameter instanceof LocalDateTime) {
                statement.setTimestamp(parameterIndex, Timestamp.valueOf((LocalDateTime) parameter));
            } else {
                logger.error("Unknown type of the parameter is found, while setting parameters to statement.  [param: {}, paramIndex: {}]", parameter, parameterIndex);
                throw new IllegalArgumentException(String.format(
                        "Unknown type of the parameter is found. [param: %s, paramIndex: %s]", parameter, parameterIndex));
            }
        }
    }
}
