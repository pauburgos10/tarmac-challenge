package com.tarmac.challenge.service;

import com.tarmac.challenge.dao.EmployeeDao;
import com.tarmac.challenge.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeServiceImpl implements EmployeeService{

    public static final String PIC_PREFIX = "http://tarmac.io/assets/members/";
    public static final String PIC_SUFFIX = ".png";

    @Autowired
    EmployeeDao employeeDao;



    @Override
    public Employee findByName(String name) {
        return employeeDao.findByName(name);
    }

    @Override
    public List<Employee> findAll(int page, int size) {
        return employeeDao.findAll(page, size);
    }

    @Override
    public List<Employee> findByRole(String role, int page, int size) {
        return employeeDao.findByRole(role, page, size);
    }

    @Override
    public List<Employee> findByCity(String city, int page, int size) {
        return employeeDao.findByCity(city, page, size);
    }

    @Override
    public List<Employee> findByRoleAndCity(String role, String city, int page, int size) {
        return employeeDao.findByRoleAndCity(role, city, page, size);
    }

    @Override
    public List<String> getCities() {
        return employeeDao.getCities();
    }

    @Override
    public List<String> getRoles() {
        return employeeDao.getRoles();
    }

    @Override
    public void deleteByName(String name) {
        employeeDao.delete(name);
    }

    @Override
    public void saveAll(List<Employee> employeeList) {
        for (Employee emp : employeeList){
            validateAndBuildPicUrl(emp);
        }
        employeeDao.saveEmployees(employeeList);
    }

    private void validateAndBuildPicUrl(Employee emp) {
        //in case the pic url is not included in json object it is built according to the given pattern
        if (emp.getPicture_url() == null || emp.getPicture_url().isEmpty()){
            emp.setPicture_url(buildPictureUrl(emp.getName()));
        }
        //but in case pic url is given it will replace spaces with  hyphen to make sure is in the right format
        emp.setPicture_url(emp.getPicture_url().replace(" ", "-"));
    }

    private static String buildPictureUrl(String name) {
       String picUrl = PIC_PREFIX + name.toLowerCase().replace(" ", "-") + PIC_SUFFIX;
       return picUrl;
    }

    @Override
    public void save(Employee employee) {
        validateAndBuildPicUrl(employee);
        employeeDao.save(employee);
    }

}
