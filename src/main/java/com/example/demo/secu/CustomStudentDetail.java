package com.example.demo.secu;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.Student;

public class CustomStudentDetail implements UserDetails {

	private Student student;
	
	public CustomStudentDetail(Student student) {
		this.student = student;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new GrantedAuthority() {

			@Override
			public String getAuthority() {
				return student.getRole();
			}
		});
		
		return collection;
	}

	@Override
	public String getPassword() {
		return student.getPassword();
	}

	@Override
	public String getUsername() {
		return student.getUsername();
	}
	
	public String getName() {
		return student.getName();
	}
	
	public String getRole() {
		return student.getRole();
	}
	
	
}
