package max.rzhe.airlines.dao.daoImpl;

import max.rzhe.airlines.dao.DaoException;
import max.rzhe.airlines.dao.UserTypeDao;
import max.rzhe.airlines.entity.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class UserTypeDaoImpl extends AbstractBaseDao<UserType, Long> implements UserTypeDao {

    @Override
    public String getSelectQuery() {
        return "SELECT id, user_type_name FROM user_type";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO user_type (user_type_name) VALUES (?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE user_type SET user_type_name = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM user_type WHERE id = ?";
    }

    @Override
    public Object[] getParametersForCreate(UserType userType) {
        Object[] createParams = getParametersForUpdate(userType);
        return Arrays.copyOf(createParams, createParams.length - 1);
    }

    @Override
    public Object[] getParametersForUpdate(UserType userType) {
        Object[] updateParams = new Object[2];
        updateParams[0] = userType.getName();
        updateParams[1] = userType.getId();
        return updateParams;
    }

    @Override
    protected List<UserType> parseResultSet(ResultSet resultSet) throws DaoException {
        List<UserType> result = new LinkedList<>();
        try {
            while (resultSet.next()) {
                UserType userType = new UserType();
                userType.setId(resultSet.getLong("id"));
                userType.setName(resultSet.getString("user_type_name"));
                result.add(userType);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
