package max.rzhe.airlines.service.serviceImpl;

import max.rzhe.airlines.dao.CrewManDao;
import max.rzhe.airlines.dao.DaoException;
import max.rzhe.airlines.entity.CrewMan;
import max.rzhe.airlines.entity.Flight;
import max.rzhe.airlines.service.CrewManService;
import max.rzhe.airlines.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CrewManServiceImpl extends AbstractBaseService<CrewMan, Long, CrewManDao> implements CrewManService {
    @Override
    public void setRepository(CrewManDao repository) {
        super.setRepository(repository);
    }
}
