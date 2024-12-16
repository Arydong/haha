package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Result;

public interface IResultRepository extends JpaRepository<Result, Long>{
	
	public Result findByStudentUsername(String username);
	
	List<Result> findAll();
}
