package com.example.demo.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class UserEntity {

	@Id
	@GeneratedValue
	private int userId;
	
	@NotEmpty
	@Email(message = "enter a valid email")
	private String email;
	@Size(min=8,max=15, message="Min 8 characters required")
	private String password;
	private String role;
	private boolean loginStatus;
	
	public UserEntity() {
		super();
	}

	public UserEntity(int userId, @NotEmpty String email,
			@Size(min = 8, max = 15, message = "Min 8 characters required") String password, String role,
			boolean loginStatus) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.role = role;
		this.loginStatus = loginStatus;
	}
	
	
	
}
