package com.tarmac.challenge.dao;

import java.util.List;
import com.tarmac.challenge.model.Employee;

public interface EmployeeDao {

    List<Employee> findAll(int page, int size);
    Employee findByName(String name);
    List<Employee> findByRole(String role, int page, int size);
    List<Employee> findByCity(String city, int page, int size);
    List<Employee> findByRoleAndCity(String role, String city, int page, int size);
    List<String> getCities();
    List<String> getRoles();
    void saveEmployees(List<Employee> employeesList);
    void save(Employee employee);
    void delete(String name);
}
