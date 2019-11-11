package ar.edu.ub.progiii.mvc.repository.querys;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que contiene metodos abstractos para construir las condiciones
 * @author Federico Franco
 *
 */
public abstract class ConditionQueryBuilder implements IQueryBuilder{

    ArrayList<String> StatementConditions;

    public abstract ArrayList<String> getStatementConditions();

    public abstract void setStatementConditions(ArrayList<String> statementConditions);

    public abstract void addStatementCondition(String Condition);

    public abstract void addStatementCondition(List<String> Conditions);

    public abstract String buildConditionString();
}
