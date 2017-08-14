package com.lemon.profiler.service.impl;

import java.util.*;

import com.lemon.profiler.model.Department;


public class DepartmentNoDBdao implements DepartmentDao {
    private static List departments;
    private static Map departmentsMap;
    static {
        departments = new ArrayList();
        departments.add(new Department( new Integer(100), "Accounting" ));
        departments.add(new Department( new Integer(200), "R & D"));
        departments.add(new Department( new Integer(300), "Sales" ));
        departments.add(new Department( new Integer(400), "HR"));
        departmentsMap = new HashMap();
        Iterator iter = departments.iterator();
        while( iter.hasNext() ) {
            Department dept = (Department)iter.next();
            departmentsMap.put(dept.getDepartmentId(), dept );
        }

     }
    public List getAllDepartments() {
        return departments;
    }
    public Map getDepartmentsMap() {
        return departmentsMap;
    }
}
