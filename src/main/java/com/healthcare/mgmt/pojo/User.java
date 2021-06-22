package com.healthcare.mgmt.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Required;

@Entity
@Table(name="USER")
public class User implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long userId;
	
	@Column(name="firstName", nullable=false)
	@NotNull
	private String firstName;
	
	@Column(name="lastName", nullable=false)
	@NotNull
	private String lastName;
	
	@Column(name="AGE")
	@NotNull
	private int age;
	
	@Column(name="GENDER")
	@NotNull
	private String gender;
	
	@Column(name="dateOfBirth")
	private Date dateOfBirth;
	
	@Id
	@Column(name="email_Id")
	@NotNull
	private String email;
	
	@Column(name="PASSWORD")
	@NotNull
	private String password;
	
	@Id
	@NotNull
	private String role;
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
		
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}