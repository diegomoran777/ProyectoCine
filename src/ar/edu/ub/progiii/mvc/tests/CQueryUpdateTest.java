package ar.edu.ub.progiii.mvc.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.repository.querys.CQuerySelect;
import ar.edu.ub.progiii.mvc.repository.querys.CQueryUpdate;

class CQueryUpdateTest {
	Data data;
	
	@BeforeEach
	void setUp() throws Exception {
		data = new Data();
	}
	
	@Test
	void CQueryUpdatetest() throws SQLException {
		CQueryUpdate cQueryUpdate = new CQueryUpdate("empleado", "CodRol=4");
    	cQueryUpdate.addStatementCondition("NroEmpleado=2");
		assertTrue(cQueryUpdate.Run() instanceof Integer);
		assertEquals(cQueryUpdate.Run(), 1);
		assertNotNull(cQueryUpdate.Run());
	}
	
	@Test
	void buildConditionStringtest() throws SQLException {
		String result = "";
		CQueryUpdate cQueryUpdate = new CQueryUpdate("empleado", "CodRol=4");
		cQueryUpdate.addStatementCondition(Arrays.asList("nroempleado=2","nroempleado=3"));
   	    result = cQueryUpdate.buildConditionString();
   	    assertEquals(result, "nroempleado=2 and nroempleado=3");
	}
	
	@Test
	void buildColumnValueStringtest() throws SQLException {
		String result = "";
		CQueryUpdate queryUpdate = new CQueryUpdate("empleado", Arrays.asList("nroEmpleado","Telefono","direccion"));
   	    queryUpdate.addStatementCondition(Arrays.asList("nroempleado=2","nroempleado=3"));
   	    result = queryUpdate.buildColumnValueString();
   	    assertEquals(result, "nroEmpleado,Telefono,direccion");
	}
	
	@Test
	void Buildtest() throws SQLException {
		String result = "";
		CQueryUpdate queryUpdate = new CQueryUpdate("empleado", Arrays.asList("nroEmpleado","Telefono","direccion"));
   	    queryUpdate.addStatementCondition(Arrays.asList("nroempleado=2","nroempleado=3"));
   	    result = queryUpdate.Build();
   	    assertEquals(result, "update empleado set nroEmpleado,Telefono,direccion where nroempleado=2 and nroempleado=3");
   	    assertNotNull(result);
	}
	
	@Test
	void Runtest() throws SQLException {
		CQueryUpdate cQueryUpdate = new CQueryUpdate("empleado", "CodRol=4");
    	cQueryUpdate.addStatementCondition("NroEmpleado=2");
		assertEquals(cQueryUpdate.Run(),1);
	}
}
