package com.spring.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.rest.model.Employee;
import com.spring.rest.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		this.employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(long Id) {
		Optional<Employee> optional = employeeRepository.findById(Id);
		Employee employee = new Employee();
		if(optional.isPresent())
			employee=optional.get();
		else throw new RuntimeException("Employee Not Found for Id: "+Id);
		return employee;
	}

	@Override
	public void deleteEmployeeById(long Id) {
		this.employeeRepository.deleteById(Id);
	}

	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize) {
		Pageable pagable = PageRequest.of(pageNo-1, pageSize);
		return this.employeeRepository.findAll(pagable);
	}
	
	

}
