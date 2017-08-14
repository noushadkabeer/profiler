package com.lemon.profiler.service.impl;


import java.util.List;

import com.lemon.profiler.model.Employee;

public interface EmployeeDao {
    public List getAllEmployees();
    public Employee getEmployee(Integer id);
    public void update(Employee emp);
    public void insert(Employee emp);
    public void delete(Integer id);
}
