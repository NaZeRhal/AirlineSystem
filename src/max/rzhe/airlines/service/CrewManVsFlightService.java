package max.rzhe.airlines.service;

import max.rzhe.airlines.entity.CrewManVsFlight;
import max.rzhe.airlines.entity.Flight;
import max.rzhe.airlines.service.exception.ServiceException;

import java.util.List;

public interface CrewManVsFlightService extends BaseService<CrewManVsFlight, Long> {
    List<CrewManVsFlight> findCrewManOnBoard(Flight flight) throws ServiceException;

    List<CrewManVsFlight> findFreeCrewMan(Flight flight, int timeCorridor) throws ServiceException;

    void deleteByFlightAndCrewMan(Long flightId, Long crewManId) throws ServiceException;
}
