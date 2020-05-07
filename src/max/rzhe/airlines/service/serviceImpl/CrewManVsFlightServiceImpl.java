package max.rzhe.airlines.service.serviceImpl;

import max.rzhe.airlines.dao.CrewManVsFlightDao;
import max.rzhe.airlines.dao.DaoException;
import max.rzhe.airlines.entity.CrewManVsFlight;
import max.rzhe.airlines.entity.Flight;
import max.rzhe.airlines.service.CrewManVsFlightService;
import max.rzhe.airlines.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CrewManVsFlightServiceImpl extends AbstractBaseService<CrewManVsFlight, Long, CrewManVsFlightDao>
        implements CrewManVsFlightService {
    @Override
    public void setRepository(CrewManVsFlightDao repository) {
        super.setRepository(repository);
    }

    @Override
    public List<CrewManVsFlight> findCrewManOnBoard(Flight flight) throws ServiceException {
        List<CrewManVsFlight> list = new ArrayList<>();
        getTransactionHandler().runWithTransaction(() -> {
            try {
                List<CrewManVsFlight> crewManVsFlights = getRepository().findCrewManOnBoard(flight);
                list.addAll(crewManVsFlights);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        });
        if (list.size() == 0) {
            return Collections.emptyList();
        }
        return list;
    }

    @Override
    public List<CrewManVsFlight> findFreeCrewMan(Flight flight, int timeCorridor) throws ServiceException {
        List<CrewManVsFlight> list = new ArrayList<>();
        getTransactionHandler().runWithTransaction(() -> {
            try {
                List<CrewManVsFlight> crewManVsFlights = getRepository().findFreeCrewMan(flight, timeCorridor);
                list.addAll(crewManVsFlights);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        });
        if (list.size() == 0) {
            return Collections.emptyList();
        }
        return list;
    }

    @Override
    public void deleteByFlightAndCrewMan(Long flightId, Long crewManId) throws ServiceException {
        getTransactionHandler().runWithTransaction(() -> {
            try {
                getRepository().deleteByFlightAndCrewMan(flightId, crewManId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        });
    }
}
