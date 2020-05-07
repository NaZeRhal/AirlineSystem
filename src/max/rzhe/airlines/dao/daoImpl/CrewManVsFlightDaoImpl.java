package max.rzhe.airlines.dao.daoImpl;

import max.rzhe.airlines.dao.CrewManVsFlightDao;
import max.rzhe.airlines.dao.DaoException;
import max.rzhe.airlines.entity.CrewMan;
import max.rzhe.airlines.entity.CrewManVsFlight;
import max.rzhe.airlines.entity.Flight;
import max.rzhe.airlines.entity.Profession;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CrewManVsFlightDaoImpl extends AbstractBaseDao<CrewManVsFlight, Long> implements CrewManVsFlightDao {
    @Override
    public String getSelectQuery() {
        return "SELECT fc.flight_id, fc.crewman_id, m.first_name, m.last_name, m.date_of_birth, " +
                "m.profession_id, p.profession_name FROM flight_crewman fc " +
                "JOIN crew_man m on fc.crewman_id = m.id " +
                "JOIN professions p on m.profession_id = p.id";
    }

    protected String getSelectAnotherQuery() {
        return "SELECT cm.id as crewman_id, cm.first_name, cm.last_name, cm.date_of_birth, cm.profession_id, p.profession_name " +
                "FROM crew_man cm JOIN professions p on cm.profession_id = p.id " +
                "WHERE cm.id NOT IN (SELECT actuale_flights.crewman_id FROM (SELECT a.id, a.arrival_time, b.crewman_id " +
                "FROM flights a, flight_crewman b " +
                "WHERE a.id = b.flight_id AND a.flight_status_id != 1) as actuale_flights " +
                "WHERE ABS(TIMESTAMPDIFF(HOUR, actuale_flights.arrival_time, " +
                "(SELECT fl.departure_time FROM flights fl WHERE fl.id = ?))) < ?)";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO flight_crewman (crewman_id, flight_id) VALUES (?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE flight_crewman SET crewman_id = ? WHERE flight_id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM flight_crewman WHERE flight_id = ?";
    }

    private String getDeleteAnotherQuery() {
        return "DELETE FROM flight_crewman WHERE flight_id = ? AND crewman_id = ?";
    }

    @Override
    public Object[] getParametersForCreate(CrewManVsFlight entity) {
        return getParametersForUpdate(entity);
    }

    @Override
    public Object[] getParametersForUpdate(CrewManVsFlight entity) {
        Object[] updateParams = new Object[2];
        updateParams[0] = entity.getCrewMan().getId();
        updateParams[1] = entity.getFlight().getId();
        return updateParams;
    }

    @Override
    protected List<CrewManVsFlight> parseResultSet(ResultSet resultSet) throws DaoException {
        List<CrewManVsFlight> result = new LinkedList<>();
        try {
            while (resultSet.next()) {
                Profession profession = new Profession();
                profession.setId(resultSet.getLong("profession_id"));
                profession.setName(resultSet.getString("profession_name"));

                CrewMan crewMan = new CrewMan();
                crewMan.setId(resultSet.getLong("crewman_id"));
                crewMan.setFirstName(resultSet.getString("first_name"));
                crewMan.setLastName(resultSet.getString("last_name"));
                crewMan.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
                crewMan.setProfession(profession);

                Flight flight = new Flight();
                long flightId;
                try {
                    flightId = resultSet.getLong("flight_id");
                } catch (SQLException e) {
                    flightId = 0L;
                }
                flight.setId(flightId);

                CrewManVsFlight crewManVsFlight = new CrewManVsFlight();
                crewManVsFlight.setCrewMan(crewMan);
                crewManVsFlight.setFlight(flight);
                result.add(crewManVsFlight);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public CrewManVsFlight findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public List<CrewManVsFlight> findAll() throws DaoException {
        return Collections.emptyList();
    }

    @Override
    public Long create(CrewManVsFlight entity) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(getCreateQuery())) {
            setParameters(statement, getParametersForCreate(entity));
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DaoException("On create modify more then 1 record: " + count);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in ResultSet while creating entity", e);
        }
        return null;
    }

    @Override
    public void deleteByFlightAndCrewMan(Long flightId, Long crewManId) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(getDeleteAnotherQuery())) {
            setParameters(statement, flightId, crewManId);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DaoException("On delete modify more then 1 record: " + count);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in PrepareStatement while deleting entity", e);
        }
    }

    @Override
    public List<CrewManVsFlight> findCrewManOnBoard(Flight flight) throws DaoException {
        return select(getSelectQuery() + " WHERE fc.flight_id = ?", flight.getId());
    }

    @Override
    public List<CrewManVsFlight> findFreeCrewMan(Flight flight, int timeCorridor) throws DaoException {
        return select(getSelectAnotherQuery(), flight.getId(), timeCorridor);
    }
}
