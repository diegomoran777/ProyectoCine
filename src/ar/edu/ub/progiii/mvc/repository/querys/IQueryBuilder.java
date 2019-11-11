package ar.edu.ub.progiii.mvc.repository.querys;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Federico Franco
 *
 * @param <T>
 */
public interface IQueryBuilder <T>{

    /**
     * Metodo para construir querys
     * @return
     */
    public String Build();

    /**
     * Metodo para correr querys
     * @return
     */
    public T Run() throws SQLException;

}
