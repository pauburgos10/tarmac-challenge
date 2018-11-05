package com.tarmac.challenge.service;

import com.tarmac.challenge.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee findByName(String name);
    List<Employee> findAll(int page, int size);
    List<Employee> findByRole(String role, int page, int size);
    List<Employee> findByCity(String city, int page, int size);
    List<Employee> findByRoleAndCity(String role, String city, int page, int size);
    List<String> getCities();
    List<String> getRoles();
    void deleteByName(String name);
    void saveAll(List<Employee> employeeList);
    void save(Employee employee);
}
