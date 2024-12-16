package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Result;
import com.example.demo.entity.Student;
import com.example.demo.repository.IResultRepository;
import com.example.demo.repository.IStudentRepository;

@Service
public class ResultService {
    
    @Autowired
    private IResultRepository rr;
    @Autowired
    private IStudentRepository sr;

    private static final Map<String, String> CORRECT_ANSWERS = Map.of(
        "one", "3",
        "two", "2",
        "three", "4",
        "four", "1",
        "five", "3"
    );

    public void saveResult(String username, String answerOne, String answerTwo, String answerThree, String answerFour, String answerFive) {
        Student student = sr.findByUsername(username);
        if (student == null) {
            throw new IllegalArgumentException("Student not found for username: " + username);
        }

        Result existingResult = rr.findByStudentUsername(username);
        if (existingResult != null) {
            rr.delete(existingResult);
        }

        Map<String, String> answers = new HashMap<>();
        answers.put("one", answerOne);
        answers.put("two", answerTwo);
        answers.put("three", answerThree);
        answers.put("four", answerFour);
        answers.put("five", answerFive);

        int score = calculateScore(answers);

        Result result = new Result(null, student, answerOne, answerTwo, answerThree, answerFour, answerFive, score);
        rr.save(result);
    }

    public Result getExamResult(String username) {
        return rr.findByStudentUsername(username);
    }

    public int calculateScore(Map<String, String> answers) {
        int score = 0;
        for (String question : CORRECT_ANSWERS.keySet()) {
            if (CORRECT_ANSWERS.get(question).equals(answers.get(question))) {
                score++;
            }
        }
        return score * 20;
    }
    
    public List<Result> getScore(){
		return rr.findAll();
	}
}