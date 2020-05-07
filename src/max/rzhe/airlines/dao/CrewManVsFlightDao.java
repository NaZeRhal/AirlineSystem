package max.rzhe.airlines.dao;

import max.rzhe.airlines.entity.CrewManVsFlight;
import max.rzhe.airlines.entity.Flight;

import java.util.List;

public interface CrewManVsFlightDao extends Dao<CrewManVsFlight, Long> {
    List<CrewManVsFlight> findCrewManOnBoard(Flight flight) throws DaoException;

    List<CrewManVsFlight> findFreeCrewMan(Flight flight, int timeCorridor) throws DaoException;

    void deleteByFlightAndCrewMan(Long flightId, Long crewManId) throws DaoException;


}
