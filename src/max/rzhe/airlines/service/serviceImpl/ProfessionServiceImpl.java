package max.rzhe.airlines.service.serviceImpl;

import max.rzhe.airlines.dao.ProfessionDao;
import max.rzhe.airlines.entity.Profession;
import max.rzhe.airlines.service.ProfessionService;

public class ProfessionServiceImpl extends AbstractBaseService<Profession, Long, ProfessionDao> implements ProfessionService {
    @Override
    public void setRepository(ProfessionDao repository) {
        super.setRepository(repository);
    }
}
