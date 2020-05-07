package max.rzhe.airlines.dao.daoImpl;

import max.rzhe.airlines.dao.DaoException;
import max.rzhe.airlines.dao.ProfessionDao;
import max.rzhe.airlines.entity.Profession;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ProfessionDaoImpl extends AbstractBaseDao<Profession, Long> implements ProfessionDao {
    @Override
    public String getSelectQuery() {
        return "SELECT id, profession_name FROM professions";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO professions (profession_name) VALUES(?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE professions SET profession_name = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM profession WHERE id = ?";
    }

    @Override
    public Object[] getParametersForCreate(Profession profession) {
        Object[] createParams = getParametersForUpdate(profession);
        createParams = Arrays.copyOf(createParams, createParams.length-1);
        return createParams;
    }

    @Override
    public Object[] getParametersForUpdate(Profession profession) {
        Object[] updateParams = new Object[2];
        updateParams[0] = profession.getName();
        updateParams[1] = profession.getId();
        return updateParams;
    }

    @Override
    protected List<Profession> parseResultSet(ResultSet resultSet) throws DaoException {
        List<Profession> result = new LinkedList<>();
        try {
            while (resultSet.next()) {
                Profession profession = new Profession();
                profession.setId(resultSet.getLong("id"));
                profession.setName(resultSet.getString("profession_name"));
                result.add(profession);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
