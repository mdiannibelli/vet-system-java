package dao;

import domain.entities.Employee;

public interface EmployeeDAO {
    void save(Employee employee);

    Employee findByUserAndPassword(String username, String password);

    void createTable();
}
