package ar.edu.ub.progiii.mvc.repository.querys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import ar.edu.ub.progiii.mvc.repository.Data;

/**
 * Clase que arma las queries insert
 * @author Diego Moran
 *
 */
public class QueryInsert implements IQueryBuilder<Boolean> {
	
	private static final String StatementConstant = "INSERT INTO";
	private static String TableName;
	private static ArrayList<String> Columns = new ArrayList<>();
	private static ArrayList<String> Values = new ArrayList<>();
	
	/**
	 *Constructor para query insert
	 *@param tableName representa el nombre de la tabla de la base de datos.
	 *@param columns representa las columnas de la base de datos
	 *@param values representa los valores.
	 */
	public QueryInsert(String tableName, List<String> columns, List<String> values) {
		this.TableName = tableName;
		Columns.clear();
		Values.clear();
        this.Columns.addAll(columns);
        this.Values.addAll(values); 
	}
	
	/**
	 *Constructor para query insert
	 *@param tableName representa el nombre de la tabla de la base de datos.
	 *@param columns representa las columnas de la base de datos
	 *@param values representa los valores.
	 */
	public QueryInsert(String tableName, String column, String value) {
		this.TableName = tableName;
        Columns.clear();
        Values.clear();
		this.Columns.add(column);
		this.Values.add(value);
	}
	
	public static ArrayList<String> getColumns() {
		return Columns;
	}

	public static void setColumns(ArrayList<String> columns) {
		Columns = columns;
	}

	public static ArrayList<String> getValues() {
		return Values;
	}

	public static void setValues(ArrayList<String> values) {
		Values = values;
	}
	
	public void addColumns(String column) {
        this.Columns.add(column);
    }
	
	public void addValuies(String value) {
        this.Values.add(value);
    }

	/**
     * Metodo para construir la/s Columna/s
     * a ser insertadas
     *
     * @return string result
     */
    public String buildColumnString(){
        String result="";
        if(Columns.size()==0){
            return "";
        }
        for (int i=0;i<Columns.size();i++){
            result += Columns.get(i);
            if(i!=Columns.size()-1){
                result += ",";
            }
        }
        return result;
    }
    
    /**
     * Metodo para construir lo/s Valor/es
     * a ser insertados
     *
     * @return string result
     */
    public String buildValueString(){
        String result="";
        if(Values.size()==0){
            return "";
        }
        for (int i=0;i<Values.size();i++){
            result += Values.get(i);
            if(i!=Values.size()-1){
                result += ",";
            }
        }
        return result;
    }
    
    /**
     * Metodo para construir querys
     *
     * @return string query
     */
    @Override
    public String Build() {
        return StatementConstant+" "+TableName+" "+"("+buildColumnString()+")"+" values ("+buildValueString()+")";
    }

    /**
     * Metodo para correr querys
     *
     * @return boolean result
     * @throws SQLException 
     */
    @Override
    public Boolean Run() throws SQLException {
        PreparedStatement stm = Data.connection.prepareStatement(Build()); 
        return stm.execute();
    }
}
