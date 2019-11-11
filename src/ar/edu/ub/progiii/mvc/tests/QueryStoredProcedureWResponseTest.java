package ar.edu.ub.progiii.mvc.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.repository.querys.QueryStoredProcedure;
import ar.edu.ub.progiii.mvc.repository.querys.QueryStoredProcedureWResponse;

class QueryStoredProcedureWResponseTest {
	Data data;
	
	@BeforeEach
	void setUp() throws Exception {
		data = new Data();
	}
	
	@Test
	void QueryStoredProcedureWResponsetest() throws SQLException {
		String result = "";
		QueryStoredProcedureWResponse queryStoredProcedureWResponse = new QueryStoredProcedureWResponse("ventasempleado",Arrays.asList("2"));
    	ResultSet rst = queryStoredProcedureWResponse.Run();
    	while(rst.next()) {
            result += (rst.getString("ventasempleado"));
        }
		assertEquals(result,"0");
	}
	
	@Test
	void buildParameterstest() throws SQLException {
		String result = "";
		QueryStoredProcedureWResponse queryStoredProcedureWResponse = new QueryStoredProcedureWResponse("ventasempleado",Arrays.asList("2","3"));
   	    result = queryStoredProcedureWResponse.BuildParameters();
   	    assertEquals(result,"2,3");
	}
	
	@Test
	void Buildtest() throws SQLException {
		String result = "";
		QueryStoredProcedureWResponse queryStoredProcedureWResponse = new QueryStoredProcedureWResponse("ventasempleado",Arrays.asList("2"));
  	    result = queryStoredProcedureWResponse.Build();
   	    assertEquals(result, "EXEC ventasempleado 3");
	}
	
	@Test
	void Runtest() throws SQLException {
		String result = "";
		QueryStoredProcedureWResponse queryStoredProcedureWResponse = new QueryStoredProcedureWResponse("ventasempleado",Arrays.asList("2"));
		ResultSet rst = queryStoredProcedureWResponse.Run();
		while(rst.next()) {
            result += (rst.getString("ventasempleado"));
        }
		assertEquals(result,"0");
		assertNotNull(queryStoredProcedureWResponse.Run());
		assertTrue(queryStoredProcedureWResponse.Run() instanceof ResultSet);
	}
}
