package cliente;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexao {
   
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/cliente";
    private static final String USER = "adm";
    private static final String PASS = "";    
   //DriverManager.getConnection("jdbc:postgresql://localhost:5432/cliente","postgres", "amstopams");
           
    public Connection getConnection(){

        try {
            Class.forName("org.postgresql.Driver");          
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/cliente","postgres", "amstopams");
         
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexao", ex);

        }
    }
}
