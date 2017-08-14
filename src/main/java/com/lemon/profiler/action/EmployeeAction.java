package com.lemon.profiler.action;

import java.util.*;

import com.lemon.profiler.model.Department;
import com.lemon.profiler.model.Employee;
import com.lemon.profiler.service.DepartmentDaoService;
import com.lemon.profiler.service.DepartmentService;
import com.lemon.profiler.service.EmployeeDaoService;
import com.lemon.profiler.service.EmployeeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



public class EmployeeAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private static EmployeeService empService = new EmployeeDaoService();
    private static DepartmentService deptService = new DepartmentDaoService();
    private Employee employee;
    private List<Employee> employees;
    private List<Department> departments;
   
	public String getAllEmployees(){
    	 employees = empService.getAllEmployees();
    	 return "success";
    }


	public String setUpForInsertOrUpdate(){
		prep();
		if (employee != null && employee.getEmployeeId() != null) {
			employee = empService.getEmployee(employee.getEmployeeId());
		}
		return "success";
	}
	
	
    public String insertOrUpdate(){
    	if(!validationSuccessful()){
    		return "input";
    	}else{
    		if (employee.getEmployeeId() == null) {
    			empService.insertEmployee(employee);
    		} else {
    			empService.updateEmployee(employee);
    		}
        	
    	}
    	return "success";
    }
    
    public String deleteConfirm(){
    	return "success";
    }
    public String deleteEmployee(){
		empService.deleteEmployee(employee.getEmployeeId());
		return "success";
	}
    
    private void prep() {
		departments = deptService.getAllDepartments();
		Map session = ActionContext.getContext().getSession();
		session.put("departments", departments);
	}
    
   
	private boolean validationSuccessful(){
    	if(employee.getFirstName()==null||employee.getFirstName().trim().length()<1){
    		addActionMessage("FirstName is required");
    	}
    	if(employee.getLastName()==null||employee.getLastName().trim().length()<1){
    		addActionMessage("LastName is required");
    	}
    	if(employee.getAge()!=null){
    		if(employee.getAge()>90||employee.getAge()<15){
    		addActionMessage("Make sure the age U input is correct");
    		}
    	}
    	if(this.hasActionMessages()){
    		return false;
    	}else{
    		return true;
    	}
    		
    	
    }
    
  
    
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	

}
