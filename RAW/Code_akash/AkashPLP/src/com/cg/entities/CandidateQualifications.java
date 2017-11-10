package com.cg.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CandidateQualifications implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String qualificationId;
	private String qualificationName;
	private String specializationArea;
	private String collegeName;
	private String universityName;
	private String yearOfPassing;
	private double percentage;
	private String candidate;

	public String getQualificationId() {
		return qualificationId;
	}

	public void setQualificationId(String qualificationId) {
		this.qualificationId = qualificationId;
	}

	public String getQualificationName() {
		return qualificationName;
	}

	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}

	public String getSpecializationArea() {
		return specializationArea;
	}

	public void setSpecializationArea(String specializationArea) {
		this.specializationArea = specializationArea;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public String getYearOfPassing() {
		return yearOfPassing;
	}

	public void setYearOfPassing(String yearOfPassing) {
		this.yearOfPassing = yearOfPassing;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public String getCandidate() {
		return candidate;
	}

	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CandidateQualifications [qualificationId=" + qualificationId
				+ ", qualificationName=" + qualificationName
				+ ", specializationArea=" + specializationArea
				+ ", collegeName=" + collegeName + ", universityName="
				+ universityName + ", yearOfPassing=" + yearOfPassing
				+ ", percentage=" + percentage + ", candidateId=" + candidate
				+ "]" + "\n";
	}

}
