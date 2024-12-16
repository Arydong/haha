package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Result;
import com.example.demo.entity.Student;
import com.example.demo.repository.IResultRepository;
import com.example.demo.repository.IStudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	IStudentRepository sr;
	@Autowired
	IResultRepository rr;
	
	public Student findByUsername(String username) {
        return sr.findByUsername(username);
    }
	
	@Transactional
    public void saveResult(Result result) {
        rr.save(result);
    }
	
	public void regist(Student student) {
		
		String encodedPassword = bCryptPasswordEncoder.encode(student.getPassword());
		student.setPassword(encodedPassword);
		
		if(student.getRole() == null) {
			student.setRole("ROLE_STUDENT");
		}
		
		sr.save(student);
	}
	

}
