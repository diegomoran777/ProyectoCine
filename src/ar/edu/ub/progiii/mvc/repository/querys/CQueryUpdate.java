package ar.edu.ub.progiii.mvc.repository.querys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.ub.progiii.mvc.repository.Data;

/**
 * Clase para contruir las quieries con update
 * @author Diego Moran
 *
 */
public class CQueryUpdate extends ConditionQueryBuilder {
	
	 private static final String StatementConstant = "update";
	 private static String TableName;
	 private static ArrayList<String> ColumnValue = new ArrayList<>();
	 
	 /** 
		 *Constructor para query update
		 *@param tableName representa el nombre de la tabla de la base de datos.
		 *@param columnValue representa los valores de las columnas.
		 */
	public CQueryUpdate(String tableName, List<String> columnValue) {
		this.TableName = tableName;
		this.ColumnValue.clear();
        this.ColumnValue.addAll(columnValue);
        setStatementConditions(new ArrayList<>());
	}
	 
	/**
	 *Constructor para query update
	 *@param tableName representa el nombre de la tabla de la base de datos.
	 *@param columnValue representa los valores de las columnas.
	 */
	 public CQueryUpdate(String tableName, String columnValue) {
		 this.TableName = tableName;
         this.ColumnValue.clear();
		 this.ColumnValue.add(columnValue);
		 setStatementConditions(new ArrayList<>());
	 }
	 
    @Override
    public ArrayList<String> getStatementConditions() {
        return null;
    }

    @Override
    public void setStatementConditions(ArrayList<String> statementConditions) {
    	StatementConditions = statementConditions;
    }

    @Override
    public void addStatementCondition(String Condition) {
    	StatementConditions.add(Condition);
    }

    @Override
    public void addStatementCondition(List<String> Conditions) {
    	StatementConditions.addAll(Conditions);
    }

    /**
     * Metodo para construir la condicion
     *
     * @return string result
     */
    @Override
    public String buildConditionString() {
    	String result="";
        if(StatementConditions.size()==0){
            return "";
        }
        for (int i=0;i<StatementConditions.size();i++){
            result += StatementConditions.get(i);
            if(i!=StatementConditions.size()-1){
                result += " and ";
            }
        }
        return result;
    }
    
    /**
     * Metodo para construir Columna y valor
     * a ser actualizado
     *
     * @return string result
     */
    public String buildColumnValueString(){
        String result="";
        if(ColumnValue.size()==0){
            return "";
        }
        for (int i=0;i<ColumnValue.size();i++){
            result += ColumnValue.get(i);
            if(i!=ColumnValue.size()-1){
                result += ",";
            }
        }
        return result;
    }
    
    /**
     * Metodo para construir querys
     *
     * @return int query
     */
    @Override
    public String Build() {
        return StatementConstant+" "+TableName+" set "+buildColumnValueString()+" where "+buildConditionString();
    }

    /**
     * Metodo para correr querys
     *
     * @return int result
     */
    @Override
    public Integer Run() throws SQLException {
    	int result= -1;
        PreparedStatement stm = Data.connection.prepareStatement(Build());
        result = stm.executeUpdate();
        return result;
    }
}
