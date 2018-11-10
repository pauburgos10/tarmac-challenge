package com.tarmac.challenge.dao;

import java.util.List;
import com.tarmac.challenge.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Transactional
@Repository
public class EmployeeDaoImpl implements EmployeeDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> findAll(int page, int size) {
        String hql = "FROM Employee as emp";
        Query query = entityManager.createQuery(hql);
        query.setFirstResult((page-1) * size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public Employee findByName(String name) {
        return entityManager.find(Employee.class, name);
    }

    @Override
    public List<Employee> findByRole(String role, int page, int size) {
        String hql = "FROM Employee as emp where emp.role = :role";
        Query query = entityManager.createQuery(hql);
        query.setParameter("role", role);
        query.setFirstResult((page-1) * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    @Override
    public List<Employee> findByCity(String city, int page, int size) {
        String hql = "FROM Employee as emp where emp.city = :city";
        Query query = entityManager.createQuery(hql);
        query.setParameter("city", city);
        query.setFirstResult((page-1) * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    @Override
    public List<Employee> findByRoleAndCity(String role, String city, int page, int size) {
        String hql = "FROM Employee as emp where emp.role = :role and emp.city = :city";
        Query query = entityManager.createQuery(hql);
        query.setParameter("role", role);
        query.setParameter("city", city);
        query.setFirstResult((page-1) * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    @Override
    public List<String> getCities() {
        String hql = "Select distinct emp.city FROM Employee as emp";
        Query query = entityManager.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public List<String> getRoles() {
        String hql = "Select distinct emp.role FROM Employee as emp";
        Query query = entityManager.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public void saveEmployees(List<Employee> employeesList) {
        for(Employee employee : employeesList){
            entityManager.merge(employee);
        }
    }

    @Override
    public void save(Employee employee) {
        entityManager.merge(employee);
    }

    @Override
    public void delete(String name) {
        Employee employee = findByName(name);
        entityManager.remove(employee);
    }

}
