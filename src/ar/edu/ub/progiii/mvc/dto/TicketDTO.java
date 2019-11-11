package ar.edu.ub.progiii.mvc.dto;

public class TicketDTO {

    public String TicketID;
    public String TicketTitle;
    public String TicketAuthor;
    public String TicketContent;
    public String TicketDate;
    public String TicketStatus;


    public TicketDTO(){}

    public TicketDTO(String ticketTitle, String ticketAuthor, String ticketContent, String ID, String date, String status) {
        TicketTitle = ticketTitle;
        TicketAuthor = ticketAuthor;
        TicketContent = ticketContent;
        TicketID = ID;
        TicketDate = date;
        TicketStatus = status;
    }

    public String getTicketTitle() {
        return TicketTitle;
    }

    public void setTicketTitle(String ticketTitle) {
        TicketTitle = ticketTitle;
    }

    public String getTicketAuthor() {
        return TicketAuthor;
    }

    public void setTicketAuthor(String ticketAuthor) {
        TicketAuthor = ticketAuthor;
    }

    public String getTicketContent() {
        return TicketContent;
    }

    public void setTicketContent(String ticketContent) {
        TicketContent = ticketContent;
    }

    public String getTicketID() {
        return TicketID;
    }

    public void setTicketID(String ticketID) {
        TicketID = ticketID;
    }

    public String getTicketDate() {
        return TicketDate;
    }

    public void setTicketDate(String ticketDate) {
        TicketDate = ticketDate;
    }

    public String getTicketStatus() {
        return TicketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        TicketStatus = ticketStatus;
    }
}
