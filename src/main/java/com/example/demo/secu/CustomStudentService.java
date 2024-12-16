package com.example.demo.secu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.IStudentRepository;

@Service
public class CustomStudentService implements UserDetailsService {

	@Autowired
	IStudentRepository sr;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Student student = sr.findByUsername(username);
		
		return new CustomStudentDetail(student);
	}

}
