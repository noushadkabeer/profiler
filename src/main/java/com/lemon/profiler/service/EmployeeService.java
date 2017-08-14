package com.lemon.profiler.service;


import java.util.List;

import com.lemon.profiler.model.Employee;

public interface EmployeeService {
    public List getAllEmployees();
    public void updateEmployee(Employee emp);
    public void deleteEmployee(Integer id);
    public com.lemon.profiler.model.Employee getEmployee(Integer id);
    public void insertEmployee(Employee emp);
}
