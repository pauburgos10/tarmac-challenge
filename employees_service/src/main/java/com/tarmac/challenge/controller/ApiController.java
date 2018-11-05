package com.tarmac.challenge.controller;

import com.tarmac.challenge.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.tarmac.challenge.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("employee")
public class ApiController {

    @Autowired
    private EmployeeService employeeService;

    @CrossOrigin
    @GetMapping("{name}")
    public ResponseEntity<Employee> findByName(@PathVariable("name") String name) {
        Employee employee = employeeService.findByName(name);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("listCities")
    public ResponseEntity<List<String>>  getCities() {
        List<String> cities = employeeService.getCities();
        return new ResponseEntity<List<String>>(cities, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("listRoles")
    public ResponseEntity<List<String>>  getRoles() {
        List<String> cities = employeeService.getRoles();
        return new ResponseEntity<List<String>>(cities, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("list")
    public ResponseEntity<List<Employee>>  findAll(@RequestParam(name = "page", required = true) int page,
                                                   @RequestParam(name = "size", required = true) int size) {
        List<Employee> employeeList = employeeService.findAll(page, size);
        return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("getByRole")
    public ResponseEntity<List<Employee>>  findByRole(@RequestParam(value = "role", required = false) String role,
                                                      @RequestParam(name = "page", required = true) int page,
                                                      @RequestParam(name = "size", required = true) int size) {
        List<Employee> employeeList = employeeService.findByRole(role, page, size);
        return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("getByCity")
    public ResponseEntity<List<Employee>>  findByCity(@RequestParam(value = "city", required = false) String city,
                                                      @RequestParam(name = "page", required = true) int page,
                                                      @RequestParam(name = "size", required = true) int size) {
        List<Employee> employeeList = employeeService.findByCity(city, page, size);
        return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("list/{role}/{city}")
    public ResponseEntity<List<Employee>>  findByRoleAndCity(@PathVariable("role") String role,
                                                      @PathVariable("city") String city,
                                                      @RequestParam(name = "page", required = true) int page,
                                                      @RequestParam(name = "size", required = true) int size) {
        List<Employee> employeeList = employeeService.findByRoleAndCity(role,city, page, size);
        return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("saveAll")
    public ResponseEntity<Void> saveEmployees(@RequestBody List<Employee> employeeList) {
        employeeService.saveAll(employeeList);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @CrossOrigin
    @PostMapping("saveEmployee")
    public ResponseEntity<Void> saveEmployee(@RequestBody Employee employee) {
        employeeService.save(employee);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @CrossOrigin
    @DeleteMapping("{name}")
    public ResponseEntity<Void> delete(@PathVariable("name") String name) {
        employeeService.deleteByName(name);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
