package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Student;
import com.example.demo.repository.IStudentRepository;
import com.example.demo.service.StudentService;

@Controller
public class MainController {
	
	@Autowired
	StudentService ss;
	@Autowired
	IStudentRepository sr;
	
	
	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	@GetMapping("/registForm")
	public String registForm() {
		return "registFrom";
	}
	
	@PostMapping("/registProc")
	public String registProc(Student student) {
		ss.regist(student);
		return "redirect:/loginForm";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
}
