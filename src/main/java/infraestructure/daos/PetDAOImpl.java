package infraestructure.daos;

import dao.PetDAO;
import domain.entities.Pet;
import domain.entities.AdoptablePet;
import domain.entities.healthstates.Healthy;
import enums.Species;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PetDAOImpl extends AbstractDAO implements PetDAO {

    public PetDAOImpl() throws SQLException {
        super("pet", "Pet");
    }

    @Override
    public void save(Pet pet) {
        String sql = getInsertSQL();
        executeSave(sql,
                pet.getName(),
                pet.getBirthDate(),
                pet.getWeight(),
                pet.getTemperature(),
                pet.getSpecie().toString(),
                pet.getState().getClass().getSimpleName());
    }

    @Override
    public Pet findById(int id) {
        return executeFindById(id, getFindByIdSQL());
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
    protected Pet mapResultSetToEntity(ResultSet rs) throws SQLException {
        Species specie = Species.valueOf(rs.getString("specie").toUpperCase());
        return new AdoptablePet(
                rs.getString("name"),
                rs.getDate("birthdate"),
                rs.getDouble("weight"),
                rs.getDouble("temperature"),
                specie,
                new Healthy());
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