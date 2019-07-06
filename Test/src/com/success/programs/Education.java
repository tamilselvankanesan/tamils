package com.success.programs;

public class Education {
	private String toYear;

	private String school;

	private Short fromYear;

	private String degree;

	private String fieldOfStudy;

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Short getFromYear() {
		return fromYear;
	}

	public void setFromYear(Short fromYear) {
		this.fromYear = fromYear;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getFieldOfStudy() {
		return fieldOfStudy;
	}

	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}

	@Override
	public String toString() {
		return "ClassPojo [toYear = " + toYear + ", school = " + school + ", fromYear = " + fromYear + ", degree = "
				+ degree + ", fieldOfStudy = " + fieldOfStudy + "]";
	}
}
