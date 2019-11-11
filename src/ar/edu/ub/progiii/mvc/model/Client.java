package ar.edu.ub.progiii.mvc.model;

import java.sql.Date;

public class Client extends Person{
	private int ClientNumber;

	/**
	 * Constructor
	 * @param fullName representa el nombre completo del cliente.
	 * @param address representa la direccion del cliente.
	 * @param phoneNumber representa el numero de telefono del cliente.
	 * @param email representa el email del cliente.
	 * @param dateOfBirth representa la fecha de nacimiento del cliente.
	 * @param clientNumber representa el numero de cliente.
	 */
	public Client(String fullName, String address, String phoneNumber, String email, String dateOfBirth, int clientNumber) {
		super(fullName, address, phoneNumber, email, dateOfBirth);
		this.ClientNumber = clientNumber;
	}

	//Getters y Setters
	public int getClientNumber() {
		return ClientNumber;
	}

	private void setClientNumber(int clientNumber) {
		ClientNumber = clientNumber;
	}

}
