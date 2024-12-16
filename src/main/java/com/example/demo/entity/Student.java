package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "tbl_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	
	@Id
	private String username;
	private String password;
	private String name;
	private String role;

}
