package ar.edu.ub.progiii.mvc.dto;

import java.util.Date;

public class OnSiteBookingDTO extends BookingDTO{

	/**
	 * Constructor que hereda de BookingDTO 
	 */
	public OnSiteBookingDTO(String bookingCode, String bookingDate, String movieName, String show, String location,
			int theatreNumber, int ticketAmount, int clientNumber, int bookingStatus, int channelCode,
			double totalValue) {
		
		super(bookingCode, bookingDate, movieName, show, location, theatreNumber, ticketAmount, clientNumber, bookingStatus,
				channelCode, totalValue);
	}
	
	public OnSiteBookingDTO() {
		
	}
}
