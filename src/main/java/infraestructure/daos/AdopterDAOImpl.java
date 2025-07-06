package infraestructure.daos;

import config.DatabaseConnection;
import dao.AdopterDAO;
import domain.entities.Adopter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdopterDAOImpl implements AdopterDAO {
    private Connection connection;

    public AdopterDAOImpl() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    @Override
    public void save(Adopter adopter) {
        String sql = "INSERT INTO adopter (name, age, birthdate, address) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, adopter.getName());
            ps.setInt(2, adopter.getAge());
            ps.setDate(3, new java.sql.Date(adopter.getBirthDate().getTime()));
            ps.setString(4, adopter.getAddress());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al guardar adoptante: " + e.getMessage());
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
            System.err.println("Error al buscar adoptante por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Adopter findByName(String name) {
        String sql = "SELECT * FROM adopter WHERE name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Adopter(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDate("birthdate"),
                        rs.getString("address"));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar adoptante por nombre: " + e.getMessage());
        }
        return null;
    }
}