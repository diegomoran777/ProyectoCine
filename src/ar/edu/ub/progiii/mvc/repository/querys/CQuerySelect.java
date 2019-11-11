package ar.edu.ub.progiii.mvc.repository.querys;

import ar.edu.ub.progiii.mvc.repository.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clase para armar queries de tipo select con condicion y sin condicion
 * @author Federico Franco
 *
 */
public class CQuerySelect extends ConditionQueryBuilder {

    private static final String StatementConstant = "Select";
    private static String TableName;
    private static ArrayList<String> Parameters = new ArrayList<>();

    /**
	 *Constructor para query Select
	 *@param tableName representa el nombre de la tabla de la base de datos.
	 *@param parameters representa los parametros del select.
	 */
    public CQuerySelect(String tableName, List<String> parameters) {
        this.TableName = tableName;
        this.Parameters.clear();
        this.Parameters.addAll(parameters);
        setStatementConditions(new ArrayList<>());
    }

    /**
	 *Constructor para query Select
	 *@param tableName representa el nombre de la tabla de la base de datos.
	 *@param parameters representa los parametros del select.
	 */
    public CQuerySelect(String tableName, String parameter) {
        this.TableName = tableName;
        this.Parameters.clear();
        this.Parameters.add(parameter);
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
     * Metodo para construir las condiciones
     *
     * @return
     */
    @Override
    public String buildConditionString() {
        String result=" where ";
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
     * Metodo que construye los parametros
     *
     * @return
     */
    public String buildParametersString(){
        String result="";
        if(Parameters.size()==0){
            return "";
        }
        for (int i=0;i<Parameters.size();i++){
            result += Parameters.get(i);
            if(i!=Parameters.size()-1){
                result += ",";
            }
        }
        return result;
    }
    /**
     * Metodo para construir querys
     *
     * @return
     */
    @Override
    public String Build() {
        return StatementConstant+" "+buildParametersString()+" from "+TableName+" "+buildConditionString();
    }

    /**
     * Metodo para correr querys
     *
     * @return
     */
    @Override
    public ResultSet Run() throws SQLException {
        Statement stm = Data.connection.createStatement();
        ResultSet rst = stm.executeQuery(Build());
        return rst;
    }

}
