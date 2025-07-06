package infraestructure.daos;

import config.DatabaseConnection;
import dao.EmployeeDAO;
import domain.entities.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeIMPL implements EmployeeDAO {
    private Connection connection;

    public EmployeeIMPL() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    @Override
    public void save(Employee employee) {
        String sql = "INSERT INTO employee (name, username, password, age, birthdate, address, charge) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getUsername());
            ps.setString(3, employee.getPassword());
            ps.setInt(4, employee.getAge());
            ps.setDate(5, new java.sql.Date(employee.getBirthDate().getTime()));
            ps.setString(6, employee.getAddress());
            ps.setString(7, employee.getCharge());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error at saving employee: " + e.getMessage());
        }
    }

    @Override
    public Employee findByUserAndPassword(String username, String password) {
        String sql = "SELECT * FROM employee WHERE username = ? AND password = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Employee emp = new Employee(rs.getString("name"), rs.getString("username"), rs.getString("password"),
                        rs.getInt("age"), rs.getDate("birthdate"), rs.getString("address"), rs.getString("charge"));
                return emp;
            }
        } catch (SQLException e) {
            System.err.println("Error at finding employee: " + e.getMessage());
        }
        return null;
    }
}
