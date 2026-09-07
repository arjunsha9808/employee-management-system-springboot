package com.example.employeesystem.controller;

import com.example.employeesystem.dto.EmployeeDTO;

import com.example.employeesystem.entity.Department;

import com.example.employeesystem.entity.Employee;

import com.example.employeesystem.repository.DepartmentRepository;

import com.example.employeesystem.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.stream.Collectors;

@RestController

@RequestMapping("/employees")

public class EmployeeController {

    private final EmployeeService employeeService;

    private final DepartmentRepository departmentRepository;

    public EmployeeController(

            EmployeeService employeeService,

            DepartmentRepository departmentRepository) {

        this.employeeService = employeeService;

        this.departmentRepository = departmentRepository;

    }

    // =========================

    // GET - Get all employees

    // =========================

    @GetMapping

    public List<EmployeeDTO> getAllEmployees() {

        return employeeService.getAllEmployees()

                .stream()

                .map(this::convertToDTO)

                .collect(Collectors.toList());

    }

    // =========================

    // GET - Get employee by ID

    // =========================

    @GetMapping("/{id}")

    public ResponseEntity<EmployeeDTO> getEmployeeById(

            @PathVariable int id) {

        Employee employee = employeeService.getEmployeeById(id);

        return ResponseEntity.ok(convertToDTO(employee));

    }

    // =========================

    // POST - Add employee

    // =========================

    @PostMapping

    public ResponseEntity<EmployeeDTO> addEmployee(

            @Valid @RequestBody EmployeeDTO employeeDTO) {

        Department department = departmentRepository

                .findById(Integer.parseInt(employeeDTO.getDepartment()))

                .orElseThrow(() ->

                        new RuntimeException("Department not found"));

        Employee employee = new Employee();

        employee.setName(employeeDTO.getName());

        employee.setEmail(employeeDTO.getEmail());

        employee.setDepartment(department);

        Employee savedEmployee =

                employeeService.addEmployee(employee);

        return ResponseEntity.ok(convertToDTO(savedEmployee));

    }

    // =========================

    // PUT - Update employee

    // =========================

    @PutMapping("/{id}")

    public ResponseEntity<EmployeeDTO> updateEmployee(

            @PathVariable int id,

            @Valid @RequestBody EmployeeDTO employeeDTO) {

        Employee existingEmployee =

                employeeService.getEmployeeById(id);

        existingEmployee.setName(employeeDTO.getName());

        existingEmployee.setEmail(employeeDTO.getEmail());

        Department department = departmentRepository

                .findById(Integer.parseInt(employeeDTO.getDepartment()))

                .orElseThrow(() ->

                        new RuntimeException("Department not found"));

        existingEmployee.setDepartment(department);

        Employee updatedEmployee =

                employeeService.updateEmployee(id, existingEmployee);

        return ResponseEntity.ok(

                convertToDTO(updatedEmployee)

        );

    }

    // =========================

    // DELETE - Delete employee

    // =========================

    @DeleteMapping("/{id}")

    public ResponseEntity<String> deleteEmployee(

            @PathVariable int id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.ok(

                "Employee deleted successfully"

        );

    }

    // =========================

    // Convert Employee -> DTO

    // =========================

    private EmployeeDTO convertToDTO(Employee employee) {

        String departmentId = null;

        if (employee.getDepartment() != null) {

            departmentId =

                    String.valueOf(

                            employee.getDepartment().getId()

                    );

        }

        return new EmployeeDTO(

                employee.getId(),

                employee.getName(),

                employee.getEmail(),

                departmentId

        );

    }

}