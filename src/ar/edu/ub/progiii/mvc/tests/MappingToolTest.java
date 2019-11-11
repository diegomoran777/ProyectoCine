package ar.edu.ub.progiii.mvc.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.ub.progiii.mvc.dto.BookingDTO;
import ar.edu.ub.progiii.mvc.dto.BranchDTO;
import ar.edu.ub.progiii.mvc.dto.CinemaShowDTO;
import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import ar.edu.ub.progiii.mvc.dto.EmployeeDTO;
import ar.edu.ub.progiii.mvc.dto.FilmDTO;
import ar.edu.ub.progiii.mvc.dto.RateCategoryDTO;
import ar.edu.ub.progiii.mvc.dto.TicketDTO;
import ar.edu.ub.progiii.mvc.mapping.MappingTool;
import ar.edu.ub.progiii.mvc.model.Employee;
import ar.edu.ub.progiii.mvc.repository.Connection;
import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.service.ClientService;

class MappingToolTest {

	java.sql.Connection connection;
	Data dataManager;
	ClientDTO clientDto;
	EmployeeDTO employeeDTO;
	TicketDTO ticketDTO;
	MappingTool mapping;
	ClientService clientService;
	BookingDTO bookingDTO;
	Employee employee;
	
	
	@BeforeEach
	void setUp() throws Exception {
		dataManager = new Data();
		mapping = new MappingTool();
		clientService = new ClientService();
		connection = ar.edu.ub.progiii.mvc.repository.Connection.getConnection();
	}
	
	@Test
	void MapDTOEmployeetest() {
		employee = new Employee("diego Moran", "11 de septiembre", "153004777", "diego@hotmail.com", "2000-10-2", 5, "ert5", "2");
		assertEquals(mapping.MapDTOEmployee(employee).getFullName(),employee.getFullName());
		assertTrue(mapping.MapDTOEmployee(employee) instanceof EmployeeDTO);
	}
	
	@Test
	void MapSQLBookingDTOtest() {
		String SPsql =dataManager.GetBookingById("2");
		assertEquals(mapping.MapSQLBookingDTO(SPsql).getTotalValue(),350.55);
		assertTrue(mapping.MapSQLBookingDTO(SPsql) instanceof BookingDTO);
	}
	
	@Test
	void MapDTOClientSQLtest() {
		String SQLData = dataManager.GetClientByUId("2");
		assertEquals(mapping.MapDTOClientSQL(SQLData).getFullName(),"Michael Myers");
		assertTrue(mapping.MapDTOClientSQL(SQLData) instanceof ClientDTO);
	}
	
	@Test
	void MapDTOFilmSQLtest() throws SQLException {
		String result = "";
		if(connection != null) {
            Statement stm = connection.createStatement();
            String query="select * from pelicula where codPelicula=2";
            ResultSet rst = stm.executeQuery(query);
            while(rst.next()) {
                result += (rst.getString("NombrePelicula"))+"_";
                result += (rst.getString("CodPelicula"))+"_";
                result += (rst.getString("DuracionMinutos"))+"_";
                result += (rst.getString("Sinopsis"));
            }
       }
		assertEquals(mapping.MapDTOFilmSQL(result).getCode(),2);
		assertTrue(mapping.MapDTOFilmSQL(result) instanceof FilmDTO);
	}

	@Test
	void MapDTOEmployeeSQLtest() {
		String SQLData = dataManager.GetEmployeeByID("2");
		assertEquals(mapping.MapDTOEmployeeSQL(SQLData).getEmployeeNumber(),2);
		assertTrue(mapping.MapDTOEmployeeSQL(SQLData) instanceof EmployeeDTO);
	}
	
	@Test
	void MapDTOTicketSQLtest() throws SQLException {
		String result = "";
		if(connection != null) {
            Statement stm = connection.createStatement();
            String query="select * from tickets where ID=3";
            ResultSet rst = stm.executeQuery(query);
            while(rst.next()) {
                result += (rst.getString("ID"))+"_";
                result += (rst.getString("Title"))+"_";
                result += (rst.getString("Employee"))+"_";
                result += (rst.getString("Mensaje"))+"_";
                result += (rst.getString("Fecha"))+"_";
                result += (rst.getString("Estado"));
            }
       }
		assertEquals(mapping.MapDTOTicketSQL(result).getTicketID(),"3");
		assertTrue(mapping.MapDTOTicketSQL(result) instanceof TicketDTO);
	}
	
	@Test
	void MapEmployeeSQLtest() {
		String SQLData = dataManager.GetEmployeeByID("2");
		assertEquals(mapping.MapEmployeeSQL(SQLData).getEmployeeNumber(),2);
		assertTrue(mapping.MapEmployeeSQL(SQLData) instanceof Employee);
	}
	
	@Test
	void MapDTOBranchSQLtest() throws SQLException {
		String result = "";
		if(connection != null) {
            Statement stm = connection.createStatement();
            String query="select * from sucursal where CodSucursal=1";
            ResultSet rst = stm.executeQuery(query);
            while(rst.next()) {
                result += (rst.getString("CodSucursal"))+"_";
                result += (rst.getString("Nombre"))+"_";
                result += (rst.getString("Direccion"))+"_";
                result += (rst.getString("Telefono"))+"/";
            }
       }
		assertEquals(mapping.MapDTOBranchSQL(result).getBranchNumber(),1);
		assertTrue(mapping.MapDTOBranchSQL(result) instanceof BranchDTO);
	}
	
	@Test
	void MapDTOShowsSQLtest() throws SQLException {
		String result = "";
		if(connection != null) {
            Statement stm = connection.createStatement();
            String query="select * from funcion where CodFuncion=1";
            ResultSet rst = stm.executeQuery(query);
            while(rst.next()) {
                result += (rst.getString("CodFuncion"))+"_";
                result += (rst.getString("HoraComienzo"))+"_";
                result += (rst.getString("HoraFinalizacion"))+"_";
                result += (rst.getString("ComentariosAdicionales"))+"/";
            }
       }
		assertEquals(mapping.MapDTOShowsSQL(result).getCodeShow(),"1");
		assertTrue(mapping.MapDTOShowsSQL(result) instanceof CinemaShowDTO);
	}
	
	@Test
	void MapDTORateCategoriesSQLtest() throws SQLException {
		String result = "";
		if(connection != null) {
            Statement stm = connection.createStatement();
            String query="select * from tarifa where CodTarifa=1";
            ResultSet rst = stm.executeQuery(query);
            while(rst.next()) {
                result += (rst.getString("CodTarifa"))+"_";
                result += (rst.getString("NombreTarifa"))+"_";
                result += (rst.getString("Precio"));
            }
       }
		assertEquals(mapping.MapDTORateCategoriesSQL(result).getRateCode(),"1");
		assertTrue(mapping.MapDTORateCategoriesSQL(result) instanceof RateCategoryDTO);
	}
}
