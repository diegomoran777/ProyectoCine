package ar.edu.ub.progiii.mvc.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.repository.querys.CQuerySelect;
import ar.edu.ub.progiii.mvc.repository.querys.QueryStoredProcedure;

class QueryStoredProcedureTest {
	Data data;
	
	@BeforeEach
	void setUp() throws Exception {
		data = new Data();
	}
	
	@Test
	void QueryStoredProceduretest() throws SQLException {
		int result = -1;
		QueryStoredProcedure queryStoredProcedure = new QueryStoredProcedure("CrearTicket" ,Arrays.asList("#345", "lautaro fenandez", "Ticket prueba"));
    	result = queryStoredProcedure.Run();
		assertEquals(result,1);
	}
	
	@Test
	void buildParameterstest() throws SQLException {
		String result = "";
		QueryStoredProcedure queryStoredProcedure = new QueryStoredProcedure("CrearTicket" ,Arrays.asList("#345", "lautaro fenandez", "Ticket prueba"));
   	    result = queryStoredProcedure.BuildParameters();
   	    assertEquals(result,"#345,lautaro fenandez,Ticket prueba" );
	}
	
	@Test
	void Buildtest() throws SQLException {
		String result = "";
		QueryStoredProcedure queryStoredProcedure = new QueryStoredProcedure("CrearTicket" ,Arrays.asList("#345", "lautaro fenandez", "Ticket prueba"));
   	    result = queryStoredProcedure.Build();
   	    assertEquals(result, "EXEC CrearTicket #345,lautaro fenandez,Ticket prueba");
	}
	
	@Test
	void Runtest() throws SQLException {
		QueryStoredProcedure queryStoredProcedure = new QueryStoredProcedure("CrearTicket" ,Arrays.asList("#345", "lautaro fenandez", "Ticket prueba"));
		assertEquals(queryStoredProcedure.Run(),1);
		assertNotNull(queryStoredProcedure.Run());
		assertTrue(queryStoredProcedure.Run() instanceof Integer);
	}
}
