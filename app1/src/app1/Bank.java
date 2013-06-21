package app1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app1.DatabaseConnectionHandler;


public class Bank
{

    private Connection conn;
	
	public Bank(Connection conn){
		this.conn = conn;
	}
	    
    public boolean select(String mac) throws SQLException
    {  
    	Statement statement = null;
    	ResultSet rs = null;
    	
    	try {
    			statement = conn.createStatement();	
    			String sql = "SELECT * from macs";
                rs = conn.createStatement().executeQuery(sql);
                if (rs.next()) {
                     rs.close();
                     return true;
                }
                rs.close();
                return false;
        }catch(SQLException exception){
        	exception.printStackTrace();
        } finally {
        	DatabaseConnectionHandler.closeConnection(conn);
        }
		return false;
    }
    
}