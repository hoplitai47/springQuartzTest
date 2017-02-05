package com.example.model;

public class Person {

	private String firstName;
	private String lastName;
	private String sex;
	private Integer id;
	private Float weight;
	
	public Person() {
		this("", "", "", 0f);
	}
	
	public Person(String firstName, String lastName, String sex, Float weight) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.weight = weight;
	}
	
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
