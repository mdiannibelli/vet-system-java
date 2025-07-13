package infraestructure.daos;

import dao.EmployeeDAO;
import domain.entities.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeIMPL extends AbstractDAO implements EmployeeDAO {

    public EmployeeIMPL() throws SQLException {
        super("employee", "Employee");
    }

    @Override
    public void save(Employee employee) {
        String sql = getInsertSQL();
        executeSave(sql,
                employee.getName(),
                employee.getUsername(),
                employee.getPassword(),
                employee.getAge(),
                employee.getBirthDate(),
                employee.getAddress(),
                employee.getCharge());
    }

    @Override
    public Employee findByUserAndPassword(String username, String password) {
        String sql = "SELECT * FROM " + tableName + " WHERE username = ? AND password = ?";
        try (var ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            var rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToEntity(rs);
            }
        } catch (SQLException e) {
            handleSQLException("Error al buscar empleado por usuario y contraseña", e);
        }
        return null;
    }

    @Override
    public void createTable() {
        executeCreateTable(getCreateTableSQL());
    }

    @Override
    public boolean findExistAccount(String username) {
        String sql = "SELECT * FROM " + tableName + " WHERE username = ?";
        return executeExists(sql, username);
    }

    @Override
    protected Employee mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getString("name"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getInt("age"),
                rs.getDate("birthdate"),
                rs.getString("address"),
                rs.getString("charge"));
    }

    @Override
    protected String getInsertSQL() {
        return "INSERT INTO " + tableName
                + " (name, username, password, age, birthdate, address, charge) VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getCreateTableSQL() {
        return """
                    CREATE TABLE IF NOT EXISTS employee (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100),
                        username VARCHAR(50) UNIQUE,
                        password VARCHAR(100),
                        age INT NOT NULL,
                        birthdate DATE NOT NULL,
                        address VARCHAR(100),
                        charge VARCHAR(50) NOT NULL
                    );
                """;
    }

    @Override
    public Employee findById(int id) {
        return executeFindById(id, getFindByIdSQL());
    }
}
