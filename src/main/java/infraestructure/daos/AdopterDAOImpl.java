package infraestructure.daos;

import dao.AdopterDAO;
import domain.entities.Adopter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdopterDAOImpl extends AbstractDAO implements AdopterDAO {

    public AdopterDAOImpl() throws SQLException {
        super("adopter", "Adopter");
    }

    @Override
    public void save(Adopter adopter) {
        // String sql = getInsertSQL();
        // executeSave(sql,
        // adopter.getName(),
        // adopter.getAge(),
        // adopter.getBirthDate(),
        // adopter.getAddress());

        String sql = "INSERT INTO adopter (name, age, birthdate, address) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, adopter.getName());
            ps.setInt(2, adopter.getAge());
            ps.setDate(3, new java.sql.Date(adopter.getBirthDate().getTime()));
            ps.setString(4, adopter.getAddress());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                adopter.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Adopter findById(int id) {
        String sql = "SELECT * FROM adopter WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Adopter(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDate("birthdate"),
                        rs.getString("address"));
            }
        } catch (SQLException e) {
            System.out.println("error at founding adopter");
            e.printStackTrace();
        }
        return null;
        // return executeFindById(id, getFindByIdSQL());
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