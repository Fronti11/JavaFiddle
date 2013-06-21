package app1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dal  {
	
    private Connection connection;
    
    public dal() throws SQLException
    {
        disconnect();
        connection = DriverManager.getConnection("jdbc:mysql://localhost/dstv?user=root&password=root");
        connection.setAutoCommit(false);
    }
        
    public void disconnect() throws SQLException
    {
        if(null != connection)
        {
            connection.close();
            connection = null;
        }        
    }
	
	public void getAll() throws SQLException
	{  
		PreparedStatement preparedStatement = null;
		try
		{
			String sql =  "select * from macs";
			System.out.println("Statement: " + sql);
			preparedStatement =	connection.prepareStatement(sql);
			preparedStatement.executeQuery();
			connection.commit();
		}catch(SQLException exc)
		{
			System.out.print(exc.getMessage() + "\n");
			System.out.print("Rolling back...");
			connection.rollback();
		}
		finally
		{
			if(null != preparedStatement) 
				preparedStatement.close();
		}
	}
}
