package infraestructure.daos;

import dao.AdoptionDAO;
import domain.entities.Adoption;
import domain.entities.DogAdoption;
import domain.entities.CatAdoption;
import domain.entities.RabbitAdoption;
import domain.entities.Adopter;
import domain.entities.Employee;
import domain.entities.Pet;
import enums.Species;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdoptionDAOImpl extends AbstractDAO implements AdoptionDAO {

    public AdoptionDAOImpl() throws SQLException {
        super("adoption", "Adoption");
    }

    @Override
    public void save(Adoption adoption) {
        String sql = getInsertSQL();
        // Por simplicidad, usamos IDs fijos ya que no tenemos las tablas completas
        executeSave(sql,
                1, // adopter_id
                1, // employee_id
                1, // pet_id
                java.sql.Date.valueOf(adoption.getDateAdoption()),
                adoption.getClass().getSimpleName());
    }

    @Override
    public Adoption findById(int id) {
        return executeFindById(id, getFindByIdSQL());
    }

    @Override
    public void createTable() {
        executeCreateTable(getCreateTableSQL());
    }

    @Override
    protected Adoption mapResultSetToEntity(ResultSet rs) throws SQLException {
        // Por simplicidad, creamos objetos mock ya que no tenemos las tablas completas
        Adopter adopter = new Adopter("Mock Adopter", 25, new java.util.Date(), "Mock Address");
        Employee employee = new Employee("Mock Employee", "user", "pass", 30, new java.util.Date(), "Address", "Vet");
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

    @Override
    protected String getInsertSQL() {
        return "INSERT INTO " + tableName
                + " (adopter_id, employee_id, pet_id, date_adoption, adoption_type) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    protected String getCreateTableSQL() {
        return """
                    CREATE TABLE IF NOT EXISTS adoption (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        adopter_id INT,
                        employee_id INT,
                        pet_id INT,
                        date_adoption DATE,
                        adoption_type VARCHAR(50)
                    );
                """;
    }
}