package org.apache.struts.example.crud.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts.example.crud.model.Employee;
import org.apache.struts.example.crud.service.DefaultDepartmentService;
import org.apache.struts.example.crud.service.DefaultEmployeeService;
import org.apache.struts.example.crud.service.EmployeeService;
import org.apache.struts.example.crud.service.DepartmentService;

import java.util.List;

public class EmployeeAction extends ActionSupport implements Preparable {
    private EmployeeService empService = new DefaultEmployeeService();
    private DepartmentService deptService = new DefaultDepartmentService();
    
    private Employee employee;
    private List employees;
    private List departments;

    /**
     * Loads employee data in case of editing, and loads departments in any case,
     * to be displayed even in case validation fails.
     * 
     * @throws Exception 
     */
    @Override
    public void prepare() throws Exception {
        //deparments list will be always displayed, even if validation fails
        departments = deptService.getAllDepartments();
        if (employee != null && employee.getEmployeeId() != null) {
            //retrieves the employee from data source in case of editing and 
            //employee id. exists
            employee = empService.getEmployee(employee.getEmployeeId());
        }
    }
    
    /**
     * Adds or updates the employee given by getEmployee().
     */
    public String save() {
        if (employee.getEmployeeId() == null) {
            empService.insertEmployee(employee);
        } else {
            empService.updateEmployee(employee);
        }
        return SUCCESS;
    }

    /**
     * Delete employee which ID is getEmployee().getEmployeeId()
     */
    public String delete() {
        empService.deleteEmployee(employee.getEmployeeId());
        return SUCCESS;
    }

    /**
     * Returns all employees
     */
    public String list() {
        employees = empService.getAllEmployees();
        return SUCCESS;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List getEmployees() {
        return employees;
    }

    public List getDepartments() {
        return departments;
    }

}
