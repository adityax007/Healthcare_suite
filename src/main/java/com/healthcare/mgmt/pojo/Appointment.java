package com.healthcare.mgmt.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="appointment_details")
public class Appointment {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="appointment_number")
	long appointment_number;
	
	@Column(name="doctor_name")
	String doctor_name;
	
	@Column(name="appointment_date")
	String appointment_date;
	
	@Column(name="appointment_time")
	String appointment_time;
	
	@Column(name="appointment_location")
	String appointment_location;
	
	@Column(name="patient_name")
	String patient_name;
	
	@Column(name="patient_id")
	long patient_id;
	
	@Column(name="doctor_id")
	long doctor_id;
	
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(long patient_id) {
		this.patient_id = patient_id;
	}
	public long getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(long doctor_id) {
		this.doctor_id = doctor_id;
	}
	
	public long getAppointment_number() {
		return appointment_number;
	}
	public void setAppointment_number(long appointment_number) {
		this.appointment_number = appointment_number;
	}
	
	public String getDoctor_name() {
		return doctor_name;
	}
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	public String getAppointment_date() {
		return appointment_date;
	}
	public void setAppointment_date(String appointment_date) {
		this.appointment_date = appointment_date;
	}
	public String getAppointment_time() {
		return appointment_time;
	}
	public void setAppointment_time(String appointment_time) {
		this.appointment_time = appointment_time;
	}
	public String getAppointment_location() {
		return appointment_location;
	}
	public void setAppointment_location(String appointment_location) {
		this.appointment_location = appointment_location;
	}
}
