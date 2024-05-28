package com.imsoftware.students.domain;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class StudentDTO {

	private String studentName;
	private Collection<String> currentSubject;
	private Boolean currentPopularSubject;

	public StudentDTO(String studentName, Collection<String> currentSubject) {
		super();
		this.studentName = studentName;
		this.currentSubject = currentSubject;
	}

	public StudentDTO(String studentName, Boolean currentPopularSubject) {
		super();
		this.studentName = studentName;
		this.currentPopularSubject = currentPopularSubject;
	}

	public String getStudentName() {
		return studentName;
	}

	public Collection<String> getCurrentSubject() {
		return currentSubject;
	}

	public Boolean getCurrentPopularSubject() {
		return currentPopularSubject;
	}

	public void setCurrentPopularSubject(Boolean currentPopularSubject) {
		this.currentPopularSubject = currentPopularSubject;
	}
}
