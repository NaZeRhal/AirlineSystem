package max.rzhe.airlines.service;

import max.rzhe.airlines.entity.User;
import max.rzhe.airlines.service.exception.ServiceException;

public interface UserService extends BaseService<User, Long> {
    User findByLogin(String login) throws ServiceException;
}
