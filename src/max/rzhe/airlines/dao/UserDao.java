package max.rzhe.airlines.dao;

import max.rzhe.airlines.entity.User;
import max.rzhe.airlines.entity.UserType;

import java.util.List;

public interface UserDao extends Dao<User, Long> {
    User findByLogin(String login) throws DaoException;
}
