package com.spring.rest.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.spring.rest.model.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	void saveEmployee(Employee employee);
	Employee getEmployeeById(long Id);
	void deleteEmployeeById(long Id);
	Page<Employee> findPaginated(int pageNo, int pageSize);

}
