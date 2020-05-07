package max.rzhe.airlines.dao.daoImpl;

import max.rzhe.airlines.dao.CrewManDao;
import max.rzhe.airlines.dao.DaoException;
import max.rzhe.airlines.entity.CrewMan;
import max.rzhe.airlines.entity.Profession;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CrewManDaoImpl extends AbstractBaseDao<CrewMan, Long> implements CrewManDao {
    @Override
    public String getSelectQuery() {
        return "SELECT a.id as crewman_id, a.first_name, a.last_name, a.date_of_birth, p.id as profession_id, p.profession_name " +
                "FROM crew_man a JOIN professions p on a.profession_id = p.id";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO crew_man (first_name, last_name, date_of_birth, profession_id) VALUES (?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE crew_man SET first_name = ?, last_name = ?, date_of_birth = ?, profession_id = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM crew_man WHERE id = ?";
    }

    @Override
    public Object[] getParametersForCreate(CrewMan crewMan) {
        Object[] createParams = getParametersForUpdate(crewMan);
        return Arrays.copyOf(createParams, createParams.length - 1);
    }

    @Override
    public Object[] getParametersForUpdate(CrewMan crewMan) {
        Object[] updateParams = new Object[5];
        updateParams[0] = crewMan.getFirstName();
        updateParams[1] = crewMan.getLastName();
        updateParams[2] = crewMan.getDateOfBirth();
        updateParams[3] = crewMan.getProfession().getId();
        updateParams[4] = crewMan.getId();
        return updateParams;
    }

    @Override
    protected List<CrewMan> parseResultSet(ResultSet resultSet) throws DaoException {
        List<CrewMan> result = new LinkedList<>();
        try {
            while (resultSet.next()) {
                CrewMan crewMan = new CrewMan();
                crewMan.setId(resultSet.getLong("crewman_id"));
                crewMan.setFirstName(resultSet.getString("first_name"));
                crewMan.setLastName(resultSet.getString("last_name"));
                crewMan.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());

                Profession profession = new Profession();
                profession.setId(resultSet.getLong("profession_id"));
                profession.setName(resultSet.getString("profession_name"));
                crewMan.setProfession(profession);
                result.add(crewMan);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public CrewMan findById(Long id) throws DaoException {
        return selectOne(getSelectQuery() + " WHERE a.id = ?", id);
    }
}
