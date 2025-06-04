package com.employee.management.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employee.management.model.Employee;
import com.employee.management.repository.IEmployeeRepository;
import com.employee.management.service.IEmployeeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeService implements IEmployeeService {

    private final IEmployeeRepository repo;

    @Override
    public Employee saveEmployee(Employee employee) {
        return repo.save(employee);
    }

    @Override
    public Employee getEmployee(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return repo.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    @Override
    public Employee findByEmail(String email) {
        return repo.findByEmail(email);
    }
    
}
