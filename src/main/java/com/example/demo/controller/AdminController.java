package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Result;
import com.example.demo.repository.IResultRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	IResultRepository rr;
	
	@RequestMapping("/dashboard")
	public String dashboard() {
		return "/admin/dashboard";
	}
	
	@RequestMapping("/viewScore")
	public String viewScore(Model model) {
		List<Result> result = rr.findAll();
		model.addAttribute("result", result);
		return "/admin/viewScore";
	}

}
