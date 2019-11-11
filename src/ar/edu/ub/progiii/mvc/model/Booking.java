package ar.edu.ub.progiii.mvc.model;

import java.util.Date;

public class Booking {
	private String BookingCode;
	private String BookingDate;
	private String MovieName;
	private String Show;
	private String Location;
	private int TheatreNumber;
	private int TicketAmount;
	private int ClientNumber;
	private int BookingStatus;
	private int ChannelCode;
	private double TotalValue;
	
	/**
	 *Constructor para Booking
	 *@param bookingCode representa el codigo de reserva.
	 *@param bookingDate representa la fecha de la reserva
	 *@param bookingStatus representa el estado actual de la reserva.
	 *@param movieName representa el nombre de la pelicula en la reserva.
	 *@param show representa en que funcion es la reserva.
	 *@param location representa la sucursal.
	 *@param theatreNumber representa el numero de sala.
	 *@param ticketAmount representa la cantidad de tickets en la reserva.
	 *@param clientNumber representa el numero del cliente que efectuo la reserva.
	 *@param channelCode representa el numero de canal de donde provino la reserva.
	 *@param totalValue representa el importe total de la reserva.
	 */
	public Booking(String bookingCode, String bookingDate, String movieName, String show, String location,
			int theatreNumber, int ticketAmount, int clientNumber, int bookingStatus, int channelCode, double totalValue) {
		
		BookingCode = bookingCode;
		BookingDate = bookingDate;
		MovieName = movieName;
		Show = show;
		Location = location;
		TheatreNumber = theatreNumber;
		TicketAmount = ticketAmount;
		ClientNumber = clientNumber;
		BookingStatus = bookingStatus;
		ChannelCode = channelCode;
		TotalValue = totalValue;
	}

	//Getters y Setters
	public double getTotalValue() {
		return TotalValue;
	}

	public String getBookingCode() {
		return BookingCode;
	}

	public String getBookingDate() {
		return BookingDate;
	}

	public String getMovieName() {
		return MovieName;
	}

	public String getShow() {
		return Show;
	}

	public String getLocation() {
		return Location;
	}

	public int getTheatreNumber() {
		return TheatreNumber;
	}

	public int getTicketAmount() {
		return TicketAmount;
	}

	public int getClientNumber() {
		return ClientNumber;
	}

	public int getBookingStatus() {
		return BookingStatus;
	}

	public int getChannelCode() {
		return ChannelCode;
	}

	private void setBookingCode(String bookingCode) {
		BookingCode = bookingCode;
	}

	private void setBookingDate(String bookingDate) {
		BookingDate = bookingDate;
	}

	private void setMovieName(String movieName) {
		MovieName = movieName;
	}

	private void setShow(String show) {
		Show = show;
	}

	private void setLocation(String location) {
		Location = location;
	}

	private void setTheatreNumber(int theatreNumber) {
		TheatreNumber = theatreNumber;
	}

	private void setTicketAmount(int ticketAmount) {
		TicketAmount = ticketAmount;
	}

	private void setClientNumber(int clientNumber) {
		ClientNumber = clientNumber;
	}

	private void setBookingStatus(int bookingStatus) {
		BookingStatus = bookingStatus;
	}

	private void setChannelCode(int channelCode) {
		ChannelCode = channelCode;
	}
	
	private void setTotalValue(double totalValue) {
		TotalValue = totalValue;
	}

}
