package ar.edu.ub.progiii.mvc.model;

import java.util.Date;

public class OnlineBooking extends Booking{

	/**
	 * Constructor que hereda de Booking
	 */
	public OnlineBooking(String bookingCode, String bookingDate, String movieName, String show, String location,
			int theatreNumber, int ticketAmount, int clientNumber, int bookingStatus, int channelCode,
			double totalValue) {
		
		super(bookingCode, bookingDate, movieName, show, location, theatreNumber, ticketAmount, clientNumber, bookingStatus,
				channelCode, totalValue);
		
	}	
}
