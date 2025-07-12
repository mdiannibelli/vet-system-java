package infraestructure.daos;

import config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase abstracta base que implementa el patrón Template Method
 * para generalizar las operaciones comunes de los DAOs
 */
public abstract class AbstractDAO {

    protected Connection connection;
    protected final String tableName;
    protected final String entityName;

    public AbstractDAO(String tableName, String entityName) throws SQLException {
        this.tableName = tableName;
        this.entityName = entityName;
        this.connection = DatabaseConnection.getConnection();
    }

    protected final void executeSave(String sql, Object... params) {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setParameters(ps, params);
            ps.executeUpdate();
            System.out.println(entityName + " saved sucessfully.");
        } catch (SQLException e) {
            handleSQLException("Error at saving " + entityName.toLowerCase(), e);
        }
    }

    protected final <T> T executeFindById(int id, String sql) {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToEntity(rs);
            }
        } catch (SQLException e) {
            handleSQLException("Error at finding " + entityName.toLowerCase() + " by ID", e);
        }
        return null;
    }

    protected final <T> T executeFindByName(String name, String sql) {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToEntity(rs);
            }
        } catch (SQLException e) {
            handleSQLException("Error at searching " + entityName.toLowerCase() + " by name", e);
        }
        return null;
    }

    protected final boolean executeExists(String sql, Object... params) {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setParameters(ps, params);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            handleSQLException("Error al verificar existencia de " + entityName.toLowerCase(), e);
        }
        return false;
    }

    protected final void executeCreateTable(String createTableSQL) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Table " + tableName.toUpperCase() + " created sucessfully.");
        } catch (SQLException e) {
            handleSQLException("Error at creating table " + tableName.toLowerCase(), e);
        }
    }

    /**
     * Hook method: Configurar parámetros en PreparedStatement
     * Las subclases pueden sobrescribir este método para lógica específica
     */
    protected void setParameters(PreparedStatement ps, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            Object param = params[i];
            if (param instanceof String) {
                ps.setString(i + 1, (String) param);
            } else if (param instanceof Integer) {
                ps.setInt(i + 1, (Integer) param);
            } else if (param instanceof Double) {
                ps.setDouble(i + 1, (Double) param);
            } else if (param instanceof java.util.Date) {
                ps.setDate(i + 1, new java.sql.Date(((java.util.Date) param).getTime()));
            } else if (param instanceof java.sql.Date) {
                ps.setDate(i + 1, (java.sql.Date) param);
            } else {
                ps.setString(i + 1, param.toString());
            }
        }
    }

    /**
     * Hook method: Mapear ResultSet a entidad
     * Las subclases DEBEN implementar este método
     */
    protected abstract <T> T mapResultSetToEntity(ResultSet rs) throws SQLException;

    /**
     * Hook method: Obtener SQL de inserción
     * Las subclases pueden sobrescribir este método para SQL específico
     */
    protected abstract String getInsertSQL();

    /**
     * Hook method: Obtener SQL de búsqueda por ID
     * Las subclases pueden sobrescribir este método para SQL específico
     */
    protected String getFindByIdSQL() {
        return "SELECT * FROM " + tableName + " WHERE id = ?";
    }

    /**
     * Hook method: Obtener SQL de búsqueda por nombre
     * Las subclases pueden sobrescribir este método para SQL específico
     */
    protected String getFindByNameSQL() {
        return "SELECT * FROM " + tableName + " WHERE name = ?";
    }

    /**
     * Hook method: Obtener SQL de creación de tabla
     * Las subclases DEBEN implementar este método
     */
    protected abstract String getCreateTableSQL();

    /**
     * Template Method para manejo de excepciones SQL
     */
    protected void handleSQLException(String message, SQLException e) {
        System.err.println(message + ": " + e.getMessage());
        e.printStackTrace();
    }

    /**
     * Método de utilidad para obtener la conexión
     */
    protected Connection getConnection() {
        return connection;
    }
}