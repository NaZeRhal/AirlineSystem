package max.rzhe.airlines.service.serviceImpl;

import max.rzhe.airlines.dao.DaoException;
import max.rzhe.airlines.dao.UserDao;
import max.rzhe.airlines.entity.User;
import max.rzhe.airlines.service.UserService;
import max.rzhe.airlines.service.exception.ServiceException;

import java.util.*;

public class UserServiceImpl extends AbstractBaseService<User, Long, UserDao> implements UserService {
    @Override
    public void setRepository(UserDao repository) {
        super.setRepository(repository);
    }

    @Override
    public User findByLogin(String login) throws ServiceException {
        List<User> users = new ArrayList<>();
        getTransactionHandler().runWithTransaction(() -> {
            try {
                User user = getRepository().findByLogin(login);
                users.add(user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        });
        if (users.size() == 0) {
            return null;
        }
        return users.iterator().next();
    }
}
