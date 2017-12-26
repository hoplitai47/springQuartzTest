package com.example.model;

import java.util.HashSet;
import java.util.Set;


public class PersonRange {

	private String companyUnit;
	private Set<Person> people;

	public PersonRange() {
		this("");
	}
	
	public PersonRange(String companyUnit) {
		this.companyUnit = companyUnit;
		this.people = new HashSet<>();
	}
	
	public String getCompanyUnit() {
		return companyUnit;
	}
	public void setCompanyUnit(String companyUnit) {
		this.companyUnit = companyUnit;
	}
	public Set<Person> getPeople() {
		return people;
	}
	public void setPeople(Set<Person> people) {
		this.people = people;
	}
	
	
}
