package com.healthcare.mgmt.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="SYMPTOMS")
public class Symptoms {
	
	@Id
	@Column(name="symptomId")
	@GeneratedValue (strategy = GenerationType.AUTO)
	long symptomId;
	
	@Column(name="userId")
	long userId;
	
	@Column(name="symptom")
	@NotBlank
	String symptom;
	
	@Column(name="medHistory")
	String medHistory;
	
	@Column(name="symptomDuration")
	@NotBlank
	String symptomDuration;
	
	@Column(name="pastSurgery")
	String pastSurgery;
	
	@Column(name="otherMedicine")
	String otherMedicine;
	
	@Column(name="allergy")
	String allergy;
	
	@Column(name="dosage")
	String dosage;
	
	@Column(name="prescription")
	String prescription;
	
	@Column(name="patient_name")
	String patient_name;
	
	public long getSymptomId() {
		return symptomId;
	}

	public void setSymptomId(long symptomId) {
		this.symptomId = symptomId;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		dosage = dosage;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getMedHistory() {
		return medHistory;
	}

	public void setMedHistory(String medHistory) {
		this.medHistory = medHistory;
	}

	public String getSymptomDuration() {
		return symptomDuration;
	}

	public void setSymptomDuration(String symptomDuration) {
		this.symptomDuration = symptomDuration;
	}

	public String getPastSurgery() {
		return pastSurgery;
	}

	public void setPastSurgery(String pastSurgery) {
		this.pastSurgery = pastSurgery;
	}

	public String getOtherMedicine() {
		return otherMedicine;
	}

	public void setOtherMedicine(String otherMedicine) {
		this.otherMedicine = otherMedicine;
	}

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
}
