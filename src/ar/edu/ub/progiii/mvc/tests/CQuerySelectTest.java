package ar.edu.ub.progiii.mvc.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.ub.progiii.mvc.mapping.MappingTool;
import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.repository.querys.CQuerySelect;
import ar.edu.ub.progiii.mvc.service.ClientService;

class CQuerySelectTest {
	Data data;
	
	@BeforeEach
	void setUp() throws Exception {
		data = new Data();
	}
	
	@Test
	void CQuerySelecttest() throws SQLException {
		String result = "";
		CQuerySelect querySelect = new CQuerySelect("empleado", "*");
   	    querySelect.addStatementCondition(Arrays.asList("nroempleado="+2));
   	    ResultSet rst = querySelect.Run();
   	    while(rst.next()) {
   	    	result += (rst.getString("NombreCompleto"));
   	    }
		assertEquals(result,"Juan Perez");
	}
	
	@Test
	void buildConditionStringtest() throws SQLException {
		String result = "";
		CQuerySelect querySelect = new CQuerySelect("empleado", "*");
   	    querySelect.addStatementCondition(Arrays.asList("nroempleado=2","nroempleado=3"));
   	    result = querySelect.buildConditionString();
   	    assertEquals(result, "nroempleado=2 and nroempleado=3");
	}
	
	@Test
	void buildParametersStringtest() throws SQLException {
		String result = "";
		CQuerySelect querySelect = new CQuerySelect("empleado", Arrays.asList("nroEmpleado","Telefono","direccion"));
   	    querySelect.addStatementCondition(Arrays.asList("nroempleado=2","nroempleado=3"));
   	    result = querySelect.buildParametersString();
   	    assertEquals(result, "nroEmpleado,Telefono,direccion");
	}
	
	@Test
	void Buildtest() throws SQLException {
		String result = "";
		CQuerySelect querySelect = new CQuerySelect("empleado", Arrays.asList("nroEmpleado","Telefono","direccion"));
   	    querySelect.addStatementCondition(Arrays.asList("nroempleado=2","nroempleado=3"));
   	    result = querySelect.Build();
   	    assertEquals(result, "Select nroEmpleado,Telefono,direccion from empleado where nroempleado=2 and nroempleado=3");
	}
	
	@Test
	void Runtest() throws SQLException {
		String result = "";
		CQuerySelect querySelect = new CQuerySelect("empleado","*");
   	    querySelect.addStatementCondition(Arrays.asList("nroempleado=2"));
   	    ResultSet rst = querySelect.Run();
	    while(rst.next()) {
	    	result += (rst.getString("NombreCompleto"));
	    }
		assertEquals(result,"Juan Perez");
	}
}
