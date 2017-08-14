package com.lemon.profiler.service;

import java.util.List;

import com.lemon.profiler.service.impl.DepartmentDao;
import com.lemon.profiler.service.impl.DepartmentNoDBdao;

public class DepartmentDaoService implements DepartmentService {
    private DepartmentDao dao;

    public DepartmentDaoService() {
        this.dao = new DepartmentNoDBdao();
    }

    public List getAllDepartments() {
        return dao.getAllDepartments();
    }
}
