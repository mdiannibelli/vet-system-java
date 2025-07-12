package infraestructure.daos;

import config.DatabaseConnection;
import dao.AdoptionDAO;
import domain.entities.Adoption;
import domain.entities.DogAdoption;
import domain.entities.CatAdoption;
import domain.entities.RabbitAdoption;
import domain.entities.Adopter;
import domain.entities.Employee;
import domain.entities.Pet;
import enums.Species;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdoptionDAOImpl implements AdoptionDAO {
    private Connection connection;

    public AdoptionDAOImpl() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    @Override
    public void save(Adoption adoption) {
        String sql = "INSERT INTO adoption (adopter_id, employee_id, pet_id, date_adoption, adoption_type) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Por simplicidad, usamos IDs fijos ya que no tenemos las tablas completas
            ps.setInt(1, 1); // adopter_id
            ps.setInt(2, 1); // employee_id
            ps.setInt(3, 1); // pet_id
            ps.setDate(4, java.sql.Date.valueOf(adoption.getDateAdoption()));
            ps.setString(5, adoption.getClass().getSimpleName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al guardar adopción: " + e.getMessage());
        }
    }

    @Override
    public Adoption findById(int id) {
        String sql = "SELECT * FROM adoption WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Por simplicidad, creamos objetos mock ya que no tenemos las tablas completas
                Adopter adopter = new Adopter("Mock Adopter", 25, new java.util.Date(), "Mock Address");
                Employee employee = new Employee("Mock Employee", "user", "pass", 30, new java.util.Date(), "Address",
                        "Vet");
                Pet pet = new domain.entities.AdoptablePet("Mock Pet", new java.util.Date(), 5.0, 37.0, Species.DOG,
                        new domain.entities.healthstates.Healthy());

                String adoptionType = rs.getString("adoption_type");

                return switch (adoptionType) {
                    case "DogAdoption" -> new DogAdoption(adopter, employee, pet);
                    case "CatAdoption" -> new CatAdoption(adopter, employee, pet);
                    case "RabbitAdoption" -> new RabbitAdoption(adopter, employee, pet);
                    default -> new DogAdoption(adopter, employee, pet);
                };
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar adopción por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void createTable() {
        try {
            Statement stmt = DatabaseConnection.getConnection().createStatement();

            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS adoption (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            adopter_id INT,
                            employee_id INT,
                            pet_id INT,
                            date_adoption DATE,
                            adoption_type VARCHAR(50)
                        );
                    """);

            stmt.close();
            System.out.println("Table ADOPTION created successfully.");
        } catch (Exception e) {
            System.out.println("Table adoption not created. Error: " + e.getMessage());
        }
    }
}