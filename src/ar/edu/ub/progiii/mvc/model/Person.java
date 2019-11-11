package ar.edu.ub.progiii.mvc.model;

import java.util.Date;

import javax.print.attribute.standard.DateTimeAtCompleted;

public class Person {

	private String FullName;
	private String Address;
	private String PhoneNumber;
	private String Email;
	private String DateOfBirth;
	
	public Person() {}

	/**
	 * Constuctor
	 * @param fullName representa el nombre completo de la persona.
	 * @param address representa la direccion de la persona.
	 * @param phoneNumber representa el numero de telefono de la persona.
	 * @param email representa el email de la persona.
	 * @param dateOfBirth representa la fecha de nacimiento de la persona.
	 */
	public Person(String fullName, String address, String phoneNumber, String email, String dateOfBirth) {
	
		FullName = fullName;
		Address = address;
		PhoneNumber = phoneNumber;
		Email = email;
		DateOfBirth = dateOfBirth;
	}

	//Getters y Setters
	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	
}
