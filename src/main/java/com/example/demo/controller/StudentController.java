package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Result;
import com.example.demo.entity.Student;
import com.example.demo.service.ResultService;
import com.example.demo.service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService ss;

	@Autowired
	ResultService rs;

	@GetMapping("/mypage")
	public String mypage(Model model, Authentication authentication) {
		String username = authentication.getName();
		model.addAttribute("username", username);
		return "/student/mypage";
	}

	@RequestMapping("/test")
	public String test(HttpSession session, Authentication authentication) {
		String username = authentication.getName();
		session.setAttribute("username", username);
		Result result = rs.getExamResult(username);
		if (result != null) {
	        return "redirect:/student/mypage?solved=true";
	    }
		return "student/test";
	}

	@RequestMapping("/result")
	public String result(Model model, Authentication authentication) {
		String username = authentication.getName();
		Result result = rs.getExamResult(username);
		model.addAttribute("result", result);

		Map<String, String> Answers = new HashMap<>();
		Answers.put("one", "3");
		Answers.put("two", "2");
		Answers.put("three", "4");
		Answers.put("four", "1");
		Answers.put("five", "3");
		model.addAttribute("Answers", Answers);

		return "student/result";
	}

	@RequestMapping("/submitTest")
	public String submitTest(Authentication authentication, 
	                         @RequestParam("one") String answerOne,
	                         @RequestParam("two") String answerTwo,
	                         @RequestParam("three") String answerThree,
	                         @RequestParam("four") String answerFour,
	                         @RequestParam("five") String answerFive) {
	    String username = authentication.getName();
	    Student student = ss.findByUsername(username);
	    int score = 0;
	    if ("3".equals(answerOne)) score += 20;
	    if ("2".equals(answerTwo)) score += 20;
	    if ("4".equals(answerThree)) score += 20;
	    if ("1".equals(answerFour)) score += 20;
	    if ("3".equals(answerFive)) score += 20;

	    Result result = new Result();
	    result.setStudent(student);
	    result.setAnswerOne(answerOne);
	    result.setAnswerTwo(answerTwo);
	    result.setAnswerThree(answerThree);
	    result.setAnswerFour(answerFour);
	    result.setAnswerFive(answerFive);
	    result.setScore(score);

	    rs.saveResult(username, answerOne, answerTwo, answerThree, answerFour, answerFive);

	    return "redirect:/student/result";
	}

}
