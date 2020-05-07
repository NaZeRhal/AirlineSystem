package max.rzhe.airlines.dao.daoImpl;

import max.rzhe.airlines.dao.AirportDao;
import max.rzhe.airlines.dao.DaoException;
import max.rzhe.airlines.entity.Airport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AirportDaoImpl extends AbstractBaseDao<Airport, Long> implements AirportDao {
    @Override
    public String getSelectQuery() {
        return "SELECT id, city, airport_code FROM airport";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO airport (city, airport_code) VALUES (?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE airport SET city = ?, airport_code = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM airport WHERE id = ?";
    }

    @Override
    public Object[] getParametersForCreate(Airport airport) {
        Object[] createParams = getParametersForUpdate(airport);
        return Arrays.copyOf(createParams, createParams.length - 1);
    }

    @Override
    public Object[] getParametersForUpdate(Airport airport) {
        Object[] updateParams = new Object[3];
        updateParams[0] = airport.getCity();
        updateParams[1] = airport.getAirportCode();
        updateParams[2] = airport.getId();
        return updateParams;
    }

    @Override
    public Airport findByAirportCode(String airportCode) throws DaoException {
        return selectOne(getSelectQuery() + " WHERE airport_code = ?", airportCode);
    }

    @Override
    protected List<Airport> parseResultSet(ResultSet resultSet) throws DaoException {
        List<Airport> result = new LinkedList<>();
        try {
            while (resultSet.next()) {
                Airport airport = new Airport();
                airport.setId(resultSet.getLong("id"));
                airport.setCity(resultSet.getString("city"));
                airport.setAirportCode(resultSet.getString("airport_code"));
                result.add(airport);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
