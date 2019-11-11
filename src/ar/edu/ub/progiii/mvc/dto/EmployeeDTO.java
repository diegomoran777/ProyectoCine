package ar.edu.ub.progiii.mvc.dto;

import ar.edu.ub.progiii.mvc.model.Employee;

import java.sql.Date;
import java.util.Objects;

public class EmployeeDTO extends PersonDTO{
	private int EmployeeNumber;
	private String Rank;
	private int failed=0;
	
	/**
	 * Constructor
	 * @param fullName representa el nombre del empleado.
	 * @param address representa la direccion del empleado.
	 * @param phoneNumber representa el numero de telefono del empleado.
	 * @param email representa el email del empleado.
	 * @param dateOfBirth representa la fecha de nacimiento del empleado.
	 * @param EmployeeNumber representa el numero de empleado.
	 * @param Rank representa la categoria del empleado.
	 */
	public EmployeeDTO(String fullName, String address, String phoneNumber, String email, String dateOfBirth,int EmployeeNumber,String Rank) {
		super(fullName, address, phoneNumber, email, dateOfBirth);
		this.EmployeeNumber = EmployeeNumber;
		this.Rank = Rank;
	}

	public EmployeeDTO(){}
	
	//Getters y Setters
	public int getEmployeeNumber() {
		return EmployeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		EmployeeNumber = employeeNumber;
	}

	public String getRank() {
		return Rank;
	}

	public void setRank(String rank) {
		Rank = rank;
	}

	public int getFailed() {
		return failed;
	}

	public void setFailed(int failed) {
		this.failed = failed;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof EmployeeDTO)) return false;
		EmployeeDTO emp = (EmployeeDTO) o;
		if(emp.getFullName().equals(this.getFullName()) && emp.getDateOfBirth().equals(this.getDateOfBirth())){
			return true;
		}
		return false;
	}

}
