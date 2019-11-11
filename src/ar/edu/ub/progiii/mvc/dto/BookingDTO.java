package ar.edu.ub.progiii.mvc.dto;

import java.util.Date;

public class BookingDTO {
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
	 *Constructor para BookingDTO
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
	public BookingDTO(String bookingCode, String bookingDate, String movieName, String show, String location,
					   int theatreNumber, int ticketAmount, int clientNumber, int bookingStatus, int channelCode,double totalValue) {

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

	public BookingDTO(BookingDTO bookingDTO) {

		BookingCode = bookingDTO.getBookingCode();
		BookingDate = bookingDTO.getBookingDate();
		MovieName = bookingDTO.getMovieName();
		Show = bookingDTO.getShow();
		Location = bookingDTO.getLocation();
		TheatreNumber = bookingDTO.getTheatreNumber();
		TicketAmount = bookingDTO.getTheatreNumber();
		ClientNumber = bookingDTO.getClientNumber();
		BookingStatus = bookingDTO.getBookingStatus();
		ChannelCode = bookingDTO.getChannelCode();
		TotalValue = bookingDTO.getTotalValue();
	}

	/**
	 * Constructor por defecto.
	 */
	public BookingDTO(){}

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

	public void setBookingCode(String bookingCode) {
		BookingCode = bookingCode;
	}

	public void setBookingDate(String bookingDate) {
		BookingDate = bookingDate;
	}

	public void setMovieName(String movieName) {
		MovieName = movieName;
	}

	public void setShow(String show) {
		Show = show;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public void setTheatreNumber(int theatreNumber) {
		TheatreNumber = theatreNumber;
	}

	public void setTicketAmount(int ticketAmount) {
		TicketAmount = ticketAmount;
	}

	public void setClientNumber(int clientNumber) {
		ClientNumber = clientNumber;
	}

	public void setBookingStatus(int bookingStatus) {
		BookingStatus = bookingStatus;
	}

	public void setChannelCode(int channelCode) {
		ChannelCode = channelCode;
	}
	
	public void setTotalValue(double totalValue) {
		TotalValue = totalValue;
	}

	

}
