package za.co.microcode.tests;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import za.co.microcode.domain.Dao;
import za.co.microcode.model.User;
import com.mockrunner.jdbc.*;
import com.mockrunner.mock.jdbc.*;

public class TestDB extends BasicJDBCTestCaseAdapter{
	
	private Connection jdbcConnection;
	
	private void prepareResultSet(){
	   	MockConnection connection = getJDBCMockObjectFactory().getMockConnection();
    	StatementResultSetHandler resultSetHandler = connection.getStatementResultSetHandler(); 
    	jdbcConnection = connection;
        
        MockResultSet result = resultSetHandler.createResultSet();
        result.addRow(new Object[] {"paul", "allies", "paul.allies@gmail.com"});
        
        resultSetHandler.prepareResultSet("SELECT *", result);
	}
	
	public void test1(){
		//We prepare the data to verify what we should get back from the database for a specific select
		prepareResultSet();
		Dao dao = new Dao(jdbcConnection); //we pass a mock connection to the dal
		try {
			List<User> userList = dao.getAll();
			assertEquals(userList.size(), 1);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
		verifySQLStatementExecuted("select * from user");
	}
}
