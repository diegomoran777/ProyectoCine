package ar.edu.ub.progiii.mvc.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.repository.querys.CQueryUpdate;
import ar.edu.ub.progiii.mvc.repository.querys.QueryInsert;

class QueryInsertTest {
	Data data;
	
	@BeforeEach
	void setUp() throws Exception {
		data = new Data();
	}
	
	@Test
	void CQueryInserttest() throws SQLException {
		QueryInsert queryInsert = new QueryInsert("Cliente", "NombreCompleto,Telefono,Email,Direccion,FechaNac", "Roberto gomez,1530045756,roberto@gmail.com,lavalle4345,20192210");
		assertEquals(queryInsert.Run(), true);
		assertFalse(queryInsert.Run());
		assertNotNull(queryInsert.Run());
	}
	
	@Test
	void buildColumnStringtest() throws SQLException {
		String result = "";
		String resultWarray = "";
		QueryInsert queryInsert = new QueryInsert("Cliente", "NombreCompleto,Telefono,Email,Direccion,FechaNac", "Roberto gomez,1530045756,roberto@gmail.com,lavalle4345,20192210");
		QueryInsert queryInsertWithArray = new QueryInsert("Cliente", Arrays.asList("NombreCompleto","Telefono","Email","Direccion","FechaNac"), Arrays.asList("Roberto gomez","1530045756","roberto@gmail.com","lavalle4345","20192210"));  
   	    result = queryInsert.buildColumnString();
   	    resultWarray = queryInsertWithArray.buildColumnString();
   	    assertEquals(result, "NombreCompleto,Telefono,Email,Direccion,FechaNac");
   	    assertEquals(resultWarray, "NombreCompleto,Telefono,Email,Direccion,FechaNac");
	}
	
	@Test
	void buildValueStringtest() throws SQLException {
		String result = "";
		String resultWarray = "";
		QueryInsert queryInsert = new QueryInsert("Cliente", "NombreCompleto,Telefono,Email,Direccion,FechaNac", "Roberto gomez,1530045756,roberto@gmail.com,lavalle4345,20192210");
		QueryInsert queryInsertWithArray = new QueryInsert("Cliente", Arrays.asList("NombreCompleto","Telefono","Email","Direccion","FechaNac"), Arrays.asList("Roberto gomez","1530045756","roberto@gmail.com","lavalle4345","20192210"));  
   	    result = queryInsert.buildValueString();
   	    resultWarray = queryInsertWithArray.buildValueString();
   	    assertEquals(result, "Roberto gomez,1530045756,roberto@gmail.com,lavalle4345,20192210");
   	    assertEquals(resultWarray, "Roberto gomez,1530045756,roberto@gmail.com,lavalle4345,20192210");
	}
	
	@Test
	void Buildtest() throws SQLException {
		String result = "";
		QueryInsert queryInsert = new QueryInsert("Cliente", "NombreCompleto,Telefono,Email,Direccion,FechaNac", "Roberto gomez,1530045756,roberto@gmail.com,lavalle4345,20192210");
   	    result = queryInsert.Build();
   	    assertEquals(result, "INSERT INTO Cliente (NombreCompleto,Telefono,Email,Direccion,FechaNac) values (Roberto gomez,1530045756,roberto@gmail.com,lavalle4345,20192210)");
   	    assertNotNull(result);
	}
	
	@Test
	void Runtest() throws SQLException {
		QueryInsert queryInsert = new QueryInsert("Cliente", "NombreCompleto,Telefono,Email,Direccion,FechaNac", "Roberto gomez,1530045756,roberto@gmail.com,lavalle4345,20192210");
		assertEquals(queryInsert.Run(),true);
	}
}
