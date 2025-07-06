package infraestructure.daos;

import config.DatabaseConnection;
import dao.PetDAO;
import domain.entities.Pet;
import domain.entities.AdoptablePet;
import domain.entities.healthstates.Healthy;
import enums.Species;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PetDAOImpl implements PetDAO {
    private Connection connection;

    public PetDAOImpl() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    @Override
    public void save(Pet pet) {
        String sql = "INSERT INTO pet (name, birthdate, weight, temperature, specie, health_state) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, pet.getName());
            ps.setDate(2, new java.sql.Date(pet.getBirthDate().getTime()));
            ps.setDouble(3, pet.getWeight());
            ps.setDouble(4, pet.getTemperature());
            ps.setString(5, pet.getSpecie().toString());
            ps.setString(6, pet.getState().getClass().getSimpleName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al guardar mascota: " + e.getMessage());
        }
    }

    @Override
    public Pet findById(int id) {
        String sql = "SELECT * FROM pet WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Species specie = Species.valueOf(rs.getString("specie").toUpperCase());
                return new AdoptablePet(
                        rs.getString("name"),
                        rs.getDate("birthdate"),
                        rs.getDouble("weight"),
                        rs.getDouble("temperature"),
                        specie,
                        new Healthy());
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar mascota por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Pet findByName(String name) {
        String sql = "SELECT * FROM pet WHERE name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Species specie = Species.valueOf(rs.getString("specie").toUpperCase());
                return new AdoptablePet(
                        rs.getString("name"),
                        rs.getDate("birthdate"),
                        rs.getDouble("weight"),
                        rs.getDouble("temperature"),
                        specie,
                        new Healthy());
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar mascota por nombre: " + e.getMessage());
        }
        return null;
    }
}