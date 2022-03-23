package com.spring.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.rest.model.Employee;
import com.spring.rest.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
//	Get All Employees
	@GetMapping("/home")
	public String viewHomePage(Model model)
	{
//		model.addAttribute("listOfEmployees", employeeService.getAllEmployees());
//		return "home";
		return findPaginated(1, model);
	}
//	form to add new employee
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model)
	{
//		Create model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute("employee",employee);
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee)
	{
		employeeService.saveEmployee(employee);
		return "redirect:home";
	}
	
	@GetMapping("/showFormForUpdate/{Id}")
	public String showFormForUpdate(@PathVariable (value = "Id") Long Id, Model model)
	{
		Employee employee= employeeService.getEmployeeById(Id);
		model.addAttribute("employee",employee);
		return "update_employee";
	}
	@GetMapping("/deleteEmployee/{Id}")
	public String deleteEmployee(@PathVariable (value = "Id") Long Id)
	{
		employeeService.deleteEmployeeById(Id);
		return "redirect:/home";
	}
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, Model model )
	{
		int pageSize=5;
		Page<Employee> page = employeeService.findPaginated(pageNo, pageSize);
		List<Employee>listEmployees = page.getContent();
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listOfEmployees", listEmployees);
		return "home";
	}
	
	
}
