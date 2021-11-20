package loja.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    // Info DB server
    private static final String USERNAME =  "root";
    private static final String PASSWORD = "";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/loja";

    // conexão
    public static Connection createConnectionMYSQL() throws Exception{
        Class.forName("com.mysql.jdbc.Driver"); //classe carregada pela JVM

        //Criando conexao
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

        return connection;
    }

    public static void main(String []args) throws Exception {// caso haja uma conexão
        Connection con = createConnectionMYSQL();
        if(con!=null) {
            System.out.println("Conexão feita!");
            con.close();
        }
    }
}
