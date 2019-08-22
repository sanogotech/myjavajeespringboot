package com.macrosoftas.archijee.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macrosoftas.archijee.model.Employee;
import com.macrosoftas.archijee.repository.EmployeeRepository;


@Service
@Transactional
public class EmployeeServiceImplementation implements EmployeeServiceInterface{

	@Autowired
	protected EmployeeRepository employeeRepository;

	public Employee saveEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return employeeRepository.save(emp);
	}

	public Boolean deleteEmployee(String empId) {
		// TODO Auto-generated method stub
		Employee temp = employeeRepository.getOne(empId);
		if(temp!=null){
			 employeeRepository.delete(temp);
			 return true;
		}
		return false;
	}

	public Employee editEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return employeeRepository.save(emp);
	}

	public Collection<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		Iterable<Employee> itr = employeeRepository.findAll();
		return (Collection<Employee>)itr;
	}

	public Employee findEmployee(String empId) {
		// TODO Auto-generated method stub
		return employeeRepository.getOne(empId);
	}
	

}
