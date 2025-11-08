package com.vijay.studentmanagementsp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vijay.studentmanagementsp.model.Student;

public interface StudentRepo extends JpaRepository<Student,Integer> {
	
	List<Student> findBySnameContainingIgnoreCase(String keyword);
	   List<Student> findBySnameContainingIgnoreCaseOrEmailContainingIgnoreCase(String sname, String email);




}
