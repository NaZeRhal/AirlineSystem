package max.rzhe.airlines.service.serviceImpl;


import max.rzhe.airlines.dao.AirportDao;
import max.rzhe.airlines.dao.DaoException;
import max.rzhe.airlines.entity.Airport;
import max.rzhe.airlines.service.AirportService;
import max.rzhe.airlines.service.exception.ServiceException;

public class AirportServiceImpl extends AbstractBaseService<Airport, Long, AirportDao> implements AirportService {
    @Override
    public void setRepository(AirportDao repository) {
        super.setRepository(repository);
    }

    @Override
    public Airport findByAirportCode(String airportCode) throws ServiceException {
        try {
            return getRepository().findByAirportCode(airportCode);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
