package ar.edu.ub.progiii.mvc.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import ar.edu.ub.progiii.mvc.dto.BookingDTO;
import ar.edu.ub.progiii.mvc.dto.BranchDTO;
import ar.edu.ub.progiii.mvc.dto.CinemaShowDTO;
import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import ar.edu.ub.progiii.mvc.dto.EmployeeDTO;
import ar.edu.ub.progiii.mvc.dto.EmployeeReportDTO;
import ar.edu.ub.progiii.mvc.dto.FilmDTO;
import ar.edu.ub.progiii.mvc.dto.RateCategoryDTO;
import ar.edu.ub.progiii.mvc.dto.TicketDTO;
import ar.edu.ub.progiii.mvc.mapping.MappingTool;
import ar.edu.ub.progiii.mvc.repository.Connection;
import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.service.ClientService;

class ClientServiceTest {

	Connection connection;
	Data dataManager;
	ClientDTO clientDto;
	EmployeeDTO employeeDTO;
	TicketDTO ticketDTO;
	MappingTool mapping;
	ClientService clientService;
	BookingDTO bookingDTO;
	
	
	@BeforeEach
	void setUp() throws Exception {
		dataManager = new Data();
		mapping = new MappingTool();
		clientService = new ClientService();
	}
	
	@Test
	void IsEmployeeAlowedtest() {
		assertTrue(clientService.IsEmployeeAlowed(2));
		assertFalse(clientService.IsEmployeeAlowed(6));
		assertFalse(clientService.IsEmployeeAlowed(7));
	}
	
	@Test
	void verifyEmployeeLogintest() {
		assertTrue(clientService.verifyEmployeeLogin("2","p3p3", false));
		assertFalse(clientService.verifyEmployeeLogin("2","p4p4", false));
		assertFalse(clientService.verifyEmployeeLogin("6","d13g0", false));
		assertFalse(clientService.verifyEmployeeLogin("7","g9s7a9o", false));
	}
	
	@Test
	void GetClientByUIDtest() {
	    clientDto = new ClientDTO("Diego Moran","11 de septiembre", "1530042275","diegomoran_777@hotmail.com","1997-11-08 00:00:00.0");
		assertNotNull(clientService.GetClientByUID("3"));
		assertNull(clientService.GetClientByUID("100"));
		assertTrue(clientService.GetClientByUID("2") instanceof ClientDTO);
		assertEquals(clientService.GetClientByUID("10003").getFullName(),clientDto.getFullName());
	}
	
	@Test
	void GetAllFilmstest() {
		String nombres = "";
		assertEquals(clientService.GetAllFilms().size(),2);
		assertNotEquals(clientService.GetAllFilms().size(),3);
		for (FilmDTO film : clientService.GetAllFilms()) {
			nombres += film.getFilmName();
		}
		assertEquals(nombres,"BambiTerminator 8");
		assertNotNull(clientService.GetAllFilms());
	}
	
	@Test
	void GetBookingByIdtest() {
	     bookingDTO = new BookingDTO("2", "2019-08-13", "1", "1", "1",1, 3, 2, 2, 1,350.55);
		assertNotNull(clientService.GetBookingById("2"));
		assertNull(clientService.GetBookingById("100"));
		assertTrue(clientService.GetBookingById("2") instanceof BookingDTO);
		assertEquals(clientService.GetBookingById("2").getBookingCode(),bookingDTO.getBookingCode());
	}
	
	@Test
	void CreateNewClienttest() {
		clientDto = new ClientDTO("Samanta Wallace","11 de septiembre", "1564760343","sam@hotmail.com","1997-11-08");
		assertTrue(clientService.CreateNewClient(clientDto));
		assertNotNull(clientService.CreateNewClient(clientDto));
	}
	
	@Test
	void GetAllClientstest() {
		String numbers = "";
		assertEquals(clientService.GetAllClients().size(),5);
		assertNotEquals(clientService.GetAllClients().size(),3);
		for (ClientDTO client : clientService.GetAllClients()) {
			numbers += client.getClientNumber();
		}
		assertEquals(numbers,"2351000310005");
		assertNotNull(clientService.GetAllClients());
	}
	
	@Test
	void GetEmployeeCategorytest() {
		assertEquals(clientService.GetEmployeeCategory(2),2);
		assertNull(clientService.GetEmployeeCategory(100));
		assertNotNull(3);
	}
	
	@Test
	void GetAllEmployeestest() {
		String numbers = "";
		assertEquals(clientService.GetAllEmployees().size(),5);
		assertNotEquals(clientService.GetAllEmployees().size(),3);
		for (EmployeeDTO employee : clientService.GetAllEmployees()) {
			numbers += employee.getEmployeeNumber();
		}
		assertEquals(numbers,"23456");
		assertNotNull(clientService.GetAllClients());
		assertTrue(clientService.GetAllClients() instanceof ArrayList);
	}
	
	@Test
	void BanEmployeetest() {
		assertEquals(clientService.BanEmployee(6),1);
		assertEquals(clientService.BanEmployee(100),0);
	}
	
	@Test
	void DeleteEmployeetest() {
		assertEquals(clientService.DeleteEmployee(5),1);
		assertEquals(clientService.DeleteEmployee(100),0);
	}
	
	@Test
	void GetEmployeetest() {
		assertEquals(clientService.GetEmployee(6).getEmployeeNumber(),6);
		assertNull(clientService.GetEmployee(100));
		assertTrue(clientService.GetEmployee(6) instanceof EmployeeDTO);
	}
	
	@Test
	void UpdateEmployeetest() {
		employeeDTO = new EmployeeDTO();
		employeeDTO.setAddress("uruguay");
		employeeDTO.setEmail("rick@prueba.com");
		employeeDTO.setPhoneNumber("1530042888");
		employeeDTO.setFullName("rick wakem");
		employeeDTO.setDateOfBirth("2000-10-22");
		clientService.UpdateEmployee(employeeDTO);
		assertEquals(clientService.GetEmployee(8).getAddress(),"uruguay");
		assertEquals(clientService.GetEmployee(8).getEmail(),"rick@prueba.com");
		assertEquals(clientService.GetEmployee(8).getPhoneNumber(),"1530042888");
	}
	
	@Test
	void GetAllTicketstest() {
		String numbers = "";
		assertEquals(clientService.GetAllTickets().size(),11);
		assertNotEquals(clientService.GetAllTickets().size(),3);
		for (TicketDTO ticket : clientService.GetAllTickets()) {
			numbers += ticket.getTicketID();
		}
		assertEquals(numbers,"1234567891011");
		assertNotNull(clientService.GetAllTickets());
		assertTrue(clientService.GetAllTickets() instanceof ArrayList);
	}
	
	@Test
	void GetActiveTicketstest() {
		String numbers = "";
		int count = 0;
		assertEquals(clientService.GetActiveTickets().size(),5);
		assertNotEquals(clientService.GetActiveTickets().size(),3);
		for (TicketDTO ticketA : clientService.GetActiveTickets()) {
			if(!ticketA.getTicketStatus().equalsIgnoreCase("activo")) {
				count += 1;
			}
			numbers += ticketA.getTicketID();
		}
		assertEquals(numbers,"3451011");
		assertNotNull(clientService.GetActiveTickets());
		assertTrue(clientService.GetActiveTickets() instanceof ArrayList);
		assertEquals(count, 0);
	}
	
	@Test
	void IsActiveUsertest() {
		clientService.currentEmployee  = new EmployeeDTO();
		assertFalse(clientService.IsActiveUser());
		clientService.currentEmployee  = new  EmployeeDTO("rick wakem","uruguay","1530042277","ricks@test.com","2001022",8,"5");
		assertTrue(clientService.IsActiveUser());
	}
	
	@Test
	void CreateTickettest() {
		boolean result = false;
		TicketDTO ticket = new TicketDTO();
		ticket.setTicketAuthor("diego");
		ticket.setTicketContent("testing");
		ticket.setTicketDate("20190820");
		ticket.setTicketStatus("activo");
		ticket.setTicketTitle("test");
		assertEquals(clientService.CreateTicket(ticket), 1);
		for (TicketDTO tickets : clientService.GetAllTickets()) {
			if(tickets.getTicketID().equals("12") && tickets.getTicketAuthor().equalsIgnoreCase("diego")) {
				result = true;
			}
		}
		assertTrue(result);
	}
	
	@Test
	void ChangePasstest() {
		HttpServletRequest request = null;
		int result = clientService.changePass("6", "d13g1", "d13g2", request);
		int resultTwo = clientService.changePass("6", "d13g3", "d13g2", request);
		int resultThree = clientService.changePass("6", "d13g3", "d13g2", request);
		int resultFour = clientService.changePass("6", "d13g3", "d13g2", request);
		assertEquals(result, 1);
		assertEquals(resultTwo, 2);
		assertEquals(resultThree, 2);
		assertEquals(resultFour, 3);
	}
	
	@Test
	void GetServerDatestest() {
		assertEquals(clientService.GetServerDate(), dataManager.GetServerDate());
		assertNotNull(clientService.GetServerDate());
	}
	
	@Test
	void GetHourTodaytest() {
		assertEquals(clientService.GetHourToday() , dataManager.GetHourNow());
		assertNotNull(clientService.GetHourToday());
	}
	
	@Test
	void GetAllShowstest() {
		String numbers = "";
		assertEquals(clientService.GetAllShows().size(),5);
		assertNotEquals(clientService.GetAllShows().size(),3);
		for (CinemaShowDTO shows : clientService.GetAllShows()) {
			numbers += shows.getCodeShow();
		}
		assertEquals(numbers,"12345");
		assertNotNull(clientService.GetAllShows());
		assertTrue(clientService.GetAllShows() instanceof ArrayList);
	}
	
	@Test
	void GetShowsByHourtest() {
		String numbers = "";
		assertEquals(clientService.GetShowsByHour().size(),1);
		assertNotEquals(clientService.GetShowsByHour().size(),3);
		for (CinemaShowDTO shows : clientService.GetShowsByHour()) {
			numbers += shows.getCodeShow();
		}
		assertEquals(numbers,"5");
		assertNotNull(clientService.GetShowsByHour());
		assertTrue(clientService.GetShowsByHour() instanceof ArrayList);
	}
	
	@Test
	void AddDaystest() {
		assertEquals(clientService.AddDays("2019-09-21", 1), LocalDate.parse("2019-09-21").plusDays(1));
		assertNotNull(clientService.AddDays("2019-09-21", 1));
	}
	
	@Test
	void RemoveDaystest() {
		assertEquals(clientService.RemoveDays("2019-09-21", 1), LocalDate.parse("2019-09-20").minusDays(1));
		assertNotNull(clientService.RemoveDays("2019-09-21", 1));
	}
	
	@Test
	void CanDaysBeAddedtest() {
		assertEquals(clientService.CanDaysBeAdded("2019-09-21"), true);
		assertEquals(clientService.CanDaysBeAdded("2019-09-25"), false);
	}
	
	@Test
	void CanDaysBeRemovedtest() {
		assertEquals(clientService.CanDaysBeRemoved("2019-09-21"), true);
		assertEquals(clientService.CanDaysBeRemoved("2019-09-22"), false);
	}
	
	@Test
	void RedirectToBeginningtest() {
		assertEquals(clientService.RedirectToBeginning("2019-09-23"), true);
		assertEquals(clientService.RedirectToBeginning("2019-09-22"), false);
	}
	
	@Test
	void BanClienttest() throws SQLException {
		String result="";
		Statement stm = connection.getConnection().createStatement();
        String query="select * from clientStatus where id=1";
        ResultSet rst = stm.executeQuery(query);
        while(rst.next()) {
            result += (rst.getString("CodRol").trim());
        }
        assertEquals(result, "1004");
		assertEquals(clientService.BanClient(2), 1);
		String result2="";
		Statement stm2 = connection.getConnection().createStatement();
        String query2="select * from clientStatus where id=1";
        ResultSet rst2 = stm2.executeQuery(query);
        while(rst2.next()) {
            result2 += (rst2.getString("CodRol").trim());
        }
		assertEquals(result2, "1005");
	}
	
	@Test
	void DeleteClienttest() throws SQLException {
		String result="";
		Statement stm = connection.getConnection().createStatement();
        String query="select * from clientStatus where id=1";
        ResultSet rst = stm.executeQuery(query);
        while(rst.next()) {
            result += (rst.getString("CodRol").trim());
        }
        assertEquals(result, "1004");
		assertEquals(clientService.DeleteClient(2), 1);
		String result2="";
		Statement stm2 = connection.getConnection().createStatement();
        String query2="select * from clientStatus where id=1";
        ResultSet rst2 = stm2.executeQuery(query);
        while(rst2.next()) {
            result2 += (rst2.getString("CodRol").trim());
        }
		assertEquals(result2, "1006");
	}
	
	@Test
	void EmployeeDailySalestest() {
		assertEquals(clientService.EmployeeDailySales(2), "");
		assertNotNull(clientService.EmployeeDailySales(2));
	}
	
	@Test
	void DailySalestest() {
		assertEquals(clientService.EmployeeDailySales(2), "");
		assertNotNull(clientService.EmployeeDailySales(2));
	}
	
	@Test
	void ActiveEmployeestest() throws SQLException {
		String result="";
		Statement stm = connection.getConnection().createStatement();
        String query="select count(e.nroempleado) as CantidadEmpleados from Empleado e inner join VentaPresencial v on e.NroEmpleado=v.NroEmpleado inner join Reserva r on v.CodReserva=r.CodReserva where datediff(day,getdate(),r.fecha)=0";
        ResultSet rst = stm.executeQuery(query);
        while(rst.next()) {
            result += (rst.getString("CantidadEmpleados").trim());
        }
		assertEquals(clientService.ActiveEmployees(), result);
		assertNotNull(clientService.ActiveEmployees());
	}
	
	@Test
	void AmountOnlineTicketstest() throws SQLException {
		String result="";
		Statement stm = connection.getConnection().createStatement();
        String query="select count(codreserva)  as ReservasOnline from reserva where codcanal=2 and CodEstadoReserva=3";
        ResultSet rst = stm.executeQuery(query);
        while(rst.next()) {
            result += (rst.getString("ReservasOnline").trim());
        }
		assertEquals(clientService.AmountOnlineTickets(), result);
		assertNotNull(clientService.AmountOnlineTickets());
	}
	
	
	@Test
	void DayFilmMostWatchedtest() {
		if(dataManager.GetDayMostViewed() == "") {
			assertEquals(mapping.MapDTOFilmSQL(dataManager.GetDayMostViewed()), null);
		}
		assertEquals(clientService.DayFilmMostWatched(), null);
	}
	
	@Test
	void MonthFilmMostWatchedtest() {
		assertEquals(clientService.MonthFilmMostWatched().getFilmName(), dataManager.GetMonthMostViewed());
		assertNotNull(clientService.MonthFilmMostWatched().getFilmName());
	}
	
	@Test
	void CategoryDaytest() {
		assertEquals(clientService.CategoryDay()[0], "");
		assertEquals(clientService.CategoryDay().length, 1);
	}
	
	@Test
	void SupervisorsActiveDaytest() {
		assertEquals(clientService.SupervisorsActiveDay(), "0");
		assertNotNull(clientService.SupervisorsActiveDay());
	}
	
	@Test
	void CloseTickettest() {
		clientService.currentEmployee.setEmployeeNumber(4);
		assertEquals(clientService.CloseTicket(4,4), true);
		clientService.currentEmployee.setEmployeeNumber(1);
		assertEquals(clientService.CloseTicket(1,4), false);
	}
	
	@Test
	void GetMonthlySalestest() throws SQLException {
		String result="";
		Statement stm = connection.getConnection().createStatement();
        String query="exec ventasmes";
        ResultSet rst = stm.executeQuery(query);
        while(rst.next()) {
            result += (rst.getString("VentasDelMes").trim());
        }
		assertEquals(clientService.GetMonthlySales(), "5562.19");
		assertEquals(clientService.GetMonthlySales(), result);
		assertNotNull(clientService.GetMonthlySales());
	}
	
	@Test
	void getEmployeesActiveMonthtest() throws SQLException {
		String result="";
		Statement stm = connection.getConnection().createStatement();
        String query="exec EmpleadosActivosMes";
        ResultSet rst = stm.executeQuery(query);
        while(rst.next()) {
            result += (rst.getString("CantidadEmpleados").trim());
        }
		assertEquals(clientService.GetEmployeesActiveMonth(), result);
		assertEquals(clientService.GetEmployeesActiveMonth(), "4");
		assertNotNull(clientService.GetEmployeesActiveMonth());
	}
	
	@Test
	void GetOnlineBookingsMonthtest() throws SQLException {
		if (dataManager.GetOnlineBooQuantityMonth() == "" || dataManager.GetOnlineBooQuantityMonth() == null) {
			assertEquals(clientService.GetOnlineBookingsMonth(), "0");
		}
        else {
        	assertEquals(clientService.GetOnlineBookingsMonth(), dataManager.GetOnlineBooQuantityMonth());
        }
	}
	
	@Test
	void CategoryMonthtest() throws SQLException {
		assertEquals(clientService.CategoryMonth()[0], "Jubilado");
		assertEquals(clientService.CategoryMonth()[1], "150.00");
		assertEquals(clientService.CategoryMonth().length, 2);
	}
	
	@Test
	void GetSupervisorsOnlineMonthtest() throws SQLException {
		String result="";
		Statement stm = connection.getConnection().createStatement();
        String query="exec SupervisoresOnlinemes";
        ResultSet rst = stm.executeQuery(query);
        while(rst.next()) {
            result += (rst.getString("Online").trim());
        }
        assertEquals(clientService.GetSupervisorsOnlineMonth(), result);
		assertEquals(clientService.GetSupervisorsOnlineMonth(), "3");
		assertNotNull(clientService.GetSupervisorsOnlineMonth());
	}
	
	@Test
	void GetServerDatetest() throws SQLException {
		assertEquals(clientService.GetServerDate(), "2019-09-22");
		assertNotNull(clientService.GetServerDate());
	}
	
	@Test
	void GetServerMonthtest() throws SQLException, ParseException {
		assertEquals(clientService.GetServerMonth(), "September 2019");
		assertNotNull(clientService.GetServerMonth());
	}
	
	@Test
	void FillAllBranchestest() throws SQLException {
		ArrayList<BranchDTO> list =  clientService.branchDTOArrayList;
		assertEquals(list.size(),2);
		clientService.FillAllBranches();
		ArrayList<BranchDTO> list2 =  clientService.branchDTOArrayList;
		assertTrue(list2.size()>2);
	}
	
	@Test
	void GetEmployeeReporttest() throws SQLException {
		assertTrue(clientService.GetEmployeeReport("3") instanceof EmployeeReportDTO);
		assertEquals(clientService.GetEmployeeReport("3").getEmployeeDayBookings(),"0" );
	}
	
	@Test
	void UpdateLoginStatustest(HttpServletRequest request) throws SQLException {
		request.setAttribute("EmployeeId", 4);
		String result="";
		Statement stm = connection.getConnection().createStatement();
        String query="select * from empleado where NroEmpleado=4";
        ResultSet rst = stm.executeQuery(query);
        while(rst.next()) {
            result += (rst.getString("LoggedIn").trim());
        }
        assertEquals(result, "1");
        clientService.UpdateLoginStatus((int)request.getSession().getAttribute("EmployeeId"));
        String result2="";
		Statement stm2 = connection.getConnection().createStatement();
        String query2="select * from empleado where NroEmpleado=4";
        ResultSet rst2 = stm2.executeQuery(query2);
        while(rst2.next()) {
            result2 += (rst2.getString("LoggedIn").trim());
        }
        assertEquals(result2, "0");
	}
	
	@Test
	void InsertInitialBookingtest(HttpServletRequest request) throws SQLException {
		assertTrue(clientService.InsertInitialBooking("1", "2", "20190101", (int) request.getSession().getAttribute("EmployeeId")));
	}
	
	@Test
	void GetAllRateCategoriestest() {
		String numbers = "";
		assertEquals(clientService.GetAllRateCategories().size(),7);
		assertNotEquals(clientService.GetAllRateCategories().size(),3);
		for (RateCategoryDTO rate : clientService.GetAllRateCategories()) {
			numbers += rate.getRateCode();
		}
		assertEquals(numbers,"1234678");
		assertNotNull(clientService.GetAllRateCategories());
		assertTrue(clientService.GetAllRateCategories() instanceof ArrayList);
	}
	
	@Test
	void GetRateByIdtest() {
	    RateCategoryDTO rate = new RateCategoryDTO("1", "Menor", "150");
		assertNotNull(clientService.GetRateById("1"));
		assertNull(clientService.GetRateById("0"));
		assertTrue(clientService.GetRateById("2") instanceof RateCategoryDTO);
		assertEquals(clientService.GetRateById("2").getRateCode(),rate.getRateCode());
	}
	
	@Test
	void IsTheSameDaytest() throws SQLException {
		assertTrue(clientService.IsTheSameDay(clientService.GetServerDate()));
		assertNotEquals(clientService.IsTheSameDay("20191011"), clientService.GetServerDate());
		assertNotNull(clientService.IsTheSameDay(clientService.GetServerDate()));
	}
}
