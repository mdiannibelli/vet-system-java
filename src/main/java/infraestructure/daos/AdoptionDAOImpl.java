package infraestructure.daos;

import dao.AdopterDAO;
import dao.AdoptionDAO;
import dao.EmployeeDAO;
import dao.PetDAO;
import domain.entities.Adoption;
import domain.entities.DogAdoption;
import domain.entities.CatAdoption;
import domain.entities.RabbitAdoption;
import domain.entities.Adopter;
import domain.entities.Employee;
import domain.entities.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDate;

public class AdoptionDAOImpl extends AbstractDAO implements AdoptionDAO {

    public AdoptionDAOImpl() throws SQLException {
        super("adoption", "Adoption");
    }

    @Override
    public void save(Adoption adoption) {
        String sql = getInsertSQL();
        executeSave(sql,
                adoption.getAdopter().getId(),
                adoption.getEmployee().getId(),
                adoption.getPet().getId(),
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
        int id = rs.getInt("id");
        int adopterId = rs.getInt("adopter_id");
        int petId = rs.getInt("pet_id");
        int employeeId = rs.getInt("employee_id");
        LocalDate dateAdoption = rs.getDate("date_adoption").toLocalDate();
        String adoptionType = rs.getString("adoption_type");

        AdopterDAO adopterDAO = new AdopterDAOImpl();
        PetDAO petDAO = new PetDAOImpl();
        EmployeeDAO employeeDAO = new EmployeeIMPL();

        Adopter adopter = adopterDAO.findById(adopterId);
        Pet pet = petDAO.findById(petId);
        Employee employee = employeeDAO.findById(employeeId);

        return switch (adoptionType) {
            case "DogAdoption" -> new DogAdoption(id, adopter, employee, pet, dateAdoption);
            case "CatAdoption" -> new CatAdoption(id, adopter, employee, pet, dateAdoption);
            case "RabbitAdoption" -> new RabbitAdoption(id, adopter, employee, pet, dateAdoption);
            default -> new DogAdoption(id, adopter, employee, pet, dateAdoption);
        };
    }

    @Override
    public List<Adoption> findAll() {
        return executeFindAll("SELECT * FROM adoption");
    }

    @Override
    public boolean deleteAdoption(int id) {
        return deleteById(id, "adoption");
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