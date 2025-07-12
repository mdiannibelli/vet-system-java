package infraestructure.daos;

import dao.AdopterDAO;
import domain.entities.Adopter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdopterDAOImpl extends AbstractDAO implements AdopterDAO {

    public AdopterDAOImpl() throws SQLException {
        super("adopter", "Adopter");
    }

    @Override
    public void save(Adopter adopter) {
        String sql = getInsertSQL();
        executeSave(sql,
                adopter.getName(),
                adopter.getAge(),
                adopter.getBirthDate(),
                adopter.getAddress());
    }

    @Override
    public Adopter findById(int id) {
        return executeFindById(id, getFindByIdSQL());
    }

    @Override
    public Adopter findByName(String name) {
        return executeFindByName(name, getFindByNameSQL());
    }

    @Override
    public void createTable() {
        executeCreateTable(getCreateTableSQL());
    }

    @Override
    protected Adopter mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Adopter(
                rs.getString("name"),
                rs.getInt("age"),
                rs.getDate("birthdate"),
                rs.getString("address"));
    }

    @Override
    protected String getInsertSQL() {
        return "INSERT INTO " + tableName + " (name, age, birthdate, address) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String getCreateTableSQL() {
        return """
                    CREATE TABLE IF NOT EXISTS adopter (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100),
                        age INT,
                        birthdate DATE,
                        address VARCHAR(150)
                    );
                """;
    }
}