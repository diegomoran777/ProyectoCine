package ar.edu.ub.progiii.mvc.repository;

import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**

 * Esta clase realiza una conexion con la base de datos

 * @author: Federico Franco , Diego Moran
 */
public  class Connection {

    public static java.sql.Connection connection;
    public static ArrayList<String> Connections = new ArrayList<>();
    
    /**
     * Metodo estatico que realiza la conexion con la base de datos,  de no realizarse devuelve null
     * @return connection
     */
    public static java.sql.Connection getConnection() throws SQLException {
        try {
            FillConnectionsList();
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(Connections.get(0),"sa","f3d3fr4nc0");
        } catch (Exception e) {
            try {
                connection = DriverManager.getConnection(Connections.get(1),"sa","f3d3fr4nc0");
            }catch(Exception e2){
                connection = null;
            }
        }
        return connection;
    }

    /**
     * Metodo que lee el archivo de posibles conexiones que puede intentar para la base de datos
     * por el momento es la conexion remota y la conexion local
     * @throws IOException
     */
    public static void FillConnectionsList() throws IOException {
        File file = new File("Connection.txt");
        @SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null){
            Connections.add(st);
        }
    }
}
