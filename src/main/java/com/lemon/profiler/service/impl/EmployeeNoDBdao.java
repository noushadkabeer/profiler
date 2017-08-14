package com.lemon.profiler.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lemon.profiler.model.Department;
import com.lemon.profiler.model.Employee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EmployeeNoDBdao implements EmployeeDao {
    private static Map departmentsMap;
    private static ArrayList employees;

    static {
        employees = new ArrayList();
        employees.add(new Employee(new Integer(1), "John", "Doe", new Integer(36), new Department(new Integer(100), "Accounting")));
        employees.add(new Employee(new Integer(2), "Bob", "Smith", new Integer(25), new Department(new Integer(300), "Sales")));
        employees.add(new Employee(new Integer(4), "Rick", "Reumann", new Integer(30), new Department(new Integer(400), "HR")));
        employees.add(new Employee(new Integer(3), "Andy", "Ma", new Integer(26), new Department(new Integer(200), "R & D")));
        employees.add(new Employee(new Integer(5), "Tony", "Lee", new Integer(40), new Department(new Integer(300), "Sales")));
        DepartmentDao deptDao = new DepartmentNoDBdao();
        departmentsMap = deptDao.getDepartmentsMap();
    }

    Log logger = LogFactory.getLog(this.getClass());

    public List getAllEmployees() {
        return employees;
    }

    public Employee getEmployee(Integer id) {
        Employee emp = null;
        Iterator iter = employees.iterator();
        while (iter.hasNext()) {
            emp = (Employee)iter.next();
            if (emp.getEmployeeId().equals(id)) {
                break;
            }
        }
        return emp;
    }

    public void update(Employee emp) {
        Integer id = emp.getEmployeeId();
        for (int i = 0; i < employees.size(); i++) {
            Employee tempEmp = (Employee)employees.get(i);
            if (tempEmp.getEmployeeId().equals(id)) {
                emp.setDepartment((Department)departmentsMap.get(emp.getDepartment().getDepartmentId()));
                employees.set(i, emp);
                break;
            }
        }
    }

    public void insert(Employee emp) {
        int lastId = 0;
        Iterator iter = employees.iterator();
        while (iter.hasNext()) {
            Employee temp = (Employee)iter.next();
            if (temp.getEmployeeId().intValue() > lastId) {
                lastId = temp.getEmployeeId().intValue();
            }
        }
        emp.setDepartment((Department)departmentsMap.get(emp.getDepartment().getDepartmentId()));
        emp.setEmployeeId(new Integer(lastId + 1));
        employees.add(emp);
    }

    public void delete(Integer id) {
        for (int i = 0; i < employees.size(); i++) {
            Employee tempEmp = (Employee)employees.get(i);
            if (tempEmp.getEmployeeId().equals(id)) {
                employees.remove(i);
                break;
            }
        }
    }
}
