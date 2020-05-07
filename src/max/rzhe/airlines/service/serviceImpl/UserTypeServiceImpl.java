package max.rzhe.airlines.service.serviceImpl;

import max.rzhe.airlines.dao.UserTypeDao;
import max.rzhe.airlines.entity.UserType;
import max.rzhe.airlines.service.UserTypeService;

public class UserTypeServiceImpl extends AbstractBaseService<UserType, Long, UserTypeDao> implements UserTypeService {
    @Override
    public void setRepository(UserTypeDao repository) {
        super.setRepository(repository);
    }
}
