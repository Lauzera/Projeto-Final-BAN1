package persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao{
    
    public static Connection getConexao() throws ClassNotFoundException, SQLException{
        
            String url = "jdbc:postgresql://localhost:5432/mecanica";
            String usuario = "postgres";
            Class.forName("org.postgresql.Driver");
        
        return DriverManager.getConnection(url, usuario, "udesc");
    }

}
