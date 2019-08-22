package com.macrosoftas.archijee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.macrosoftas.archijee.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, String>{}
