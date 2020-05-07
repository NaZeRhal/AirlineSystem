package max.rzhe.airlines.dao.daoImpl;

import max.rzhe.airlines.dao.DaoException;
import max.rzhe.airlines.dao.FlightDao;
import max.rzhe.airlines.entity.Airport;
import max.rzhe.airlines.entity.Flight;
import max.rzhe.airlines.entity.FlightStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class FlightDaoImpl extends AbstractBaseDao<Flight, Long> implements FlightDao {

    @Override
    public String getSelectQuery() {
        return "SELECT a.id, a.flight_code, b.id as depair_id, b.city as depair_city, b.airport_code as depair_code, " +
                "       c.id as arrair_id, c.city as arrair_city, c.airport_code as arrair_code, " +
                "       a.departure_time, a.arrival_time, a.flight_status_id FROM flights a, airport b, airport c " +
                "WHERE a.departure_airport_id = b.id AND a.arrival_airport_id = c.id";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO flights (flight_code, departure_airport_id, arrival_airport_id, departure_time, " +
                "arrival_time, flight_status_id) VALUES (?,?,?,?,?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE flights SET flight_code = ?, departure_airport_id = ?, arrival_airport_id = ?, departure_time = ?," +
                "arrival_time = ?, flight_status_id = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM flights WHERE id = ?";
    }

    @Override
    public Object[] getParametersForCreate(Flight flight) {
        Object[] createParams = getParametersForUpdate(flight);
        return Arrays.copyOf(createParams, createParams.length - 1);
    }

    @Override
    public Object[] getParametersForUpdate(Flight flight) {
        Object[] updateParams = new Object[7];
        updateParams[0] = flight.getFlightCode();
        updateParams[1] = flight.getDepartureAirport().getId();
        updateParams[2] = flight.getArrivalAirport().getId();
        updateParams[3] = flight.getDepartureTime();
        updateParams[4] = flight.getArrivalTime();
        updateParams[5] = flight.getFlightStatus().ordinal();
        updateParams[6] = flight.getId();
        return updateParams;
    }

    @Override
    protected List<Flight> parseResultSet(ResultSet resultSet) throws DaoException {
        List<Flight> result = new LinkedList<>();
        try {
            while (resultSet.next()) {
                Airport departureAirport = new Airport();
                departureAirport.setId(resultSet.getLong("depair_id"));
                departureAirport.setCity(resultSet.getString("depair_city"));
                departureAirport.setAirportCode(resultSet.getString("depair_code"));
                Airport arrivalAirport = new Airport();
                arrivalAirport.setId(resultSet.getLong("arrair_id"));
                arrivalAirport.setCity(resultSet.getString("arrair_city"));
                arrivalAirport.setAirportCode(resultSet.getString("arrair_code"));

                Flight flight = new Flight();
                flight.setId(resultSet.getLong("id"));
                flight.setFlightCode(resultSet.getString("flight_code"));
                flight.setDepartureAirport(departureAirport);
                flight.setArrivalAirport(arrivalAirport);
                flight.setDepartureTime(resultSet.getTimestamp("departure_time").toLocalDateTime());
                flight.setArrivalTime(resultSet.getTimestamp("arrival_time").toLocalDateTime());
                flight.setFlightStatus(FlightStatus.values()[resultSet.getInt("flight_status_id")]);
                result.add(flight);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public Flight findById(Long id) throws DaoException {
        return selectOne(getSelectQuery() + " AND a.id = ?", id);
    }

    @Override
    public List<Flight> findByFlightStatus(FlightStatus flightStatus) throws DaoException {
        if (flightStatus != null) {
            return select(getSelectQuery() + " AND a.flight_status_id = ?", flightStatus.ordinal());
        }
        return Collections.emptyList();
    }
}
