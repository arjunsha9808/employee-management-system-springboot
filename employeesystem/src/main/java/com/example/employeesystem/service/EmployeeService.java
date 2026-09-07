package com.example.employeesystem.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.employeesystem.entity.Employee;
import com.example.employeesystem.exception.EmployeeNotFoundException;
import com.example.employeesystem.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //Add Employee
    public Employee addEmployee(Employee employee) {
        logger.info("Adding new employee: {}", employee.getName());
        return employeeRepository.save(employee);
    }

    //Get All Employees

    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees");
        return employeeRepository.findAll();
    }

    // Get Employee By ID
    public Employee getEmployeeById(int id) {

        logger.info("Fetching employee with id:{}", id);
        return employeeRepository.findById(id)
                .orElseThrow (() -> {
                    logger.error("Employee not found with id: {}", id);
                    return new EmployeeNotFoundException(
                            "Employee not found with id: " + id
                    );
                });
    }

    // Update Employee

    public Employee updateEmployee(int id, Employee employee) {

        Employee existingEmployee = employeeRepository.findById(id).orElse(null);

        if (existingEmployee == null) {
            return null;
        }

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());

        return employeeRepository.save(existingEmployee);
    }

    // Delete Employee
    public boolean deleteEmployee(int id) {

        logger.info("Deleting employee with id: {}", id);
        if (!employeeRepository.existsById(id)) {
            return false;
        }

        employeeRepository.deleteById(id);

        return true;
    }

}
