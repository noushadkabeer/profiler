package com.lemon.profiler.service;


import java.util.List;

import com.lemon.profiler.model.Employee;
import com.lemon.profiler.service.impl.EmployeeDao;
import com.lemon.profiler.service.impl.EmployeeNoDBdao;


public class EmployeeDaoService implements EmployeeService {
    private EmployeeDao dao;

    public EmployeeDaoService() {
        this.dao = new EmployeeNoDBdao();
    }

    public List getAllEmployees() {
        return dao.getAllEmployees();
    }

    public void updateEmployee(Employee emp) {
        dao.update(emp);
    }

    public void deleteEmployee(Integer id) {
        dao.delete(id);
    }

    public Employee getEmployee(Integer id) {
        return dao.getEmployee(id);
    }

    public void insertEmployee(Employee emp) {
        dao.insert(emp);
    }
}
