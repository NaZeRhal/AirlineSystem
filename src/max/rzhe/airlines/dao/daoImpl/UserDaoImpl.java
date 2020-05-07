package max.rzhe.airlines.dao.daoImpl;


import max.rzhe.airlines.dao.DaoException;
import max.rzhe.airlines.dao.UserDao;
import max.rzhe.airlines.entity.User;
import max.rzhe.airlines.entity.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl extends AbstractBaseDao<User, Long> implements UserDao {

    @Override
    public String getSelectQuery() {
        return "SELECT a.id, a.first_name, a.last_name, a.login, a.password, b.id as user_type_id, b.user_type_name FROM `user` a, user_type b " +
                "WHERE a.user_type_id = b.id";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO `user` (first_name, last_name, login, `password`, user_type_id) " +
                "VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE `user` SET first_name = ?, last_name = ?, login = ?, `password` = ?," +
                "user_type_id = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM user WHERE id = ?";
    }


    @Override
    public Object[] getParametersForCreate(User user) {
        Object[] createParams = getParametersForUpdate(user);
        return Arrays.copyOf(createParams, createParams.length - 1);
    }

    @Override
    public Object[] getParametersForUpdate(User user) {
        Object[] updateParams = new Object[6];
        updateParams[0] = user.getFirstName();
        updateParams[1] = user.getLastName();
        updateParams[2] = user.getLogin();
        updateParams[3] = user.getPassword();
        updateParams[4] = user.getUserType().getId();
        updateParams[5] = user.getId();
        return updateParams;
    }

    @Override
    public User findById(Long id) throws DaoException {
        return selectOne(getSelectQuery() + " AND a.id = ?", id);
    }

    @Override
    public User findByLogin(String login) throws DaoException {
        return selectOne(getSelectQuery() + " AND login = ?", login);
    }

    @Override
    protected List<User> parseResultSet(ResultSet resultSet) throws DaoException {
        List<User> result = new LinkedList<>();
        try {
            while (resultSet.next()) {
                UserType userType = new UserType();
                userType.setId(resultSet.getLong("user_type_id"));
                userType.setName(resultSet.getString("user_type_name"));

                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setUserType(userType);
                result.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
