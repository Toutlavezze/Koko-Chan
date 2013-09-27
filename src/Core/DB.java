package Core;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB 
{
    private static Connection con;
    private static Statement statement;
    
    public static void init()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://" + Globals.MSQLHOST + ":" + Globals.MSQLPORT + "/" + Globals.MSQLDATA, Globals.MSQLUSER, Globals.MSQLPASS);
            statement = (Statement) con.createStatement();
            System.out.println("[INFO]: Connection succeeded!");
        } catch (ClassNotFoundException | SQLException e) { }
    }
    
    public static synchronized void exec(String query) throws SQLException
    {
        statement.execute(query);
    }
    
    public static synchronized ResultSet get(String query) throws SQLException
    {
        return (ResultSet) statement.executeQuery(query);
    }
}
