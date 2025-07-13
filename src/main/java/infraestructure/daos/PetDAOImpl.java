package infraestructure.daos;

import dao.PetDAO;
import domain.entities.Pet;
import domain.entities.AdoptablePet;
import domain.entities.healthstates.Healthy;
import enums.Species;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PetDAOImpl extends AbstractDAO implements PetDAO {

    public PetDAOImpl() throws SQLException {
        super("pet", "Pet");
    }

    @Override
    public void save(Pet pet) {
        // String sql = getInsertSQL();
        // executeSave(sql,
        // pet.getName(),
        // pet.getBirthDate(),
        // pet.getWeight(),
        // pet.getTemperature(),
        // pet.getSpecie().toString(),
        // pet.getState().getClass().getSimpleName());

        String sql = "INSERT INTO pet (name, birthdate, weight, temperature, specie, health_state) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, pet.getName());
            ps.setDate(2, new java.sql.Date(pet.getBirthDate().getTime()));
            ps.setDouble(3, pet.getWeight());
            ps.setDouble(4, pet.getTemperature());
            ps.setString(5, pet.getSpecie().toString());
            ps.setString(6, pet.getState().getClass().getSimpleName());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                pet.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pet findById(int id) {
        String sql = "SELECT * FROM pet WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Crea el objeto Pet con los datos reales
                Species specie = Species.valueOf(rs.getString("specie"));

                // Mapear el estado de salud desde la base de datos
                String healthState = rs.getString("health_state");
                domain.interfaces.HealthState state;

                switch (healthState) {
                    case "Healthy":
                        state = new domain.entities.healthstates.Healthy();
                        break;
                    case "InObservation":
                        state = new domain.entities.healthstates.InObservation();
                        break;
                    case "SpecialCare":
                        state = new domain.entities.healthstates.SpecialCare();
                        break;
                    default:
                        state = new domain.entities.healthstates.Healthy(); // Estado por defecto
                }

                AdoptablePet pet = new AdoptablePet(
                        rs.getString("name"),
                        rs.getDate("birthdate"),
                        rs.getDouble("weight"),
                        rs.getDouble("temperature"),
                        specie,
                        state);
                pet.setId(rs.getInt("id")); // Asignar el ID
                return pet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Pet findByName(String name) {
        return executeFindByName(name, getFindByNameSQL());
    }

    @Override
    public void createTable() {
        executeCreateTable(getCreateTableSQL());
    }

    @Override
    public void updateHealthState(int petId, String healthState) {
        String sql = "UPDATE pet SET health_state = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, healthState);
            ps.setInt(2, petId);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Estado de salud actualizado correctamente para la mascota ID: " + petId);
            } else {
                System.out.println("No se encontró la mascota con ID: " + petId);
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el estado de salud: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected Pet mapResultSetToEntity(ResultSet rs) throws SQLException {
        Species specie = Species.valueOf(rs.getString("specie").toUpperCase());

        // Mapear el estado de salud desde la base de datos
        String healthState = rs.getString("health_state");
        domain.interfaces.HealthState state;

        switch (healthState) {
            case "Healthy":
                state = new domain.entities.healthstates.Healthy();
                break;
            case "InObservation":
                state = new domain.entities.healthstates.InObservation();
                break;
            case "SpecialCare":
                state = new domain.entities.healthstates.SpecialCare();
                break;
            default:
                state = new domain.entities.healthstates.Healthy(); // Estado por defecto
        }

        AdoptablePet pet = new AdoptablePet(
                rs.getString("name"),
                rs.getDate("birthdate"),
                rs.getDouble("weight"),
                rs.getDouble("temperature"),
                specie,
                state);
        pet.setId(rs.getInt("id")); // Asignar el ID
        return pet;
    }

    @Override
    protected String getInsertSQL() {
        return "INSERT INTO " + tableName
                + " (name, birthdate, weight, temperature, specie, health_state) VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getCreateTableSQL() {
        return """
                    CREATE TABLE IF NOT EXISTS pet (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100),
                        birthdate DATE,
                        weight DOUBLE,
                        temperature DOUBLE,
                        specie VARCHAR(50),
                        health_state VARCHAR(50)
                    );
                """;
    }
}