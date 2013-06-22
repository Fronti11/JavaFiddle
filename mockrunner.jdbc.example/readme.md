This project illustrates the use of the mockrunner framework to mock out a database connection.
For more information about the framework <a href="http://mockrunner.sourceforge.net" target="_blank">click here</a>

1. The jdbc part of mockrunner is used to mock out the database interaction.
* The main purpose of this framework is to write jdbc data access layer before creating the database
of even deciding which database and which jdbc driver to use.
* As long as we know which database behaviour to expect, we can completely write and test the data access object.

####How it works
When we are building an application we use separation of concerns(SoC) to divide responsibilities into distinct sections
of code.  Our sql data access class abstracts all sql database interaction away from the normal flow of the program.
Just as we unit test the business logic layer of our application, we would also like to verify and test the sql and 
the results it gives of the database access layer.

####Let's get started
Download the mockrunner bundle <a href="http://sourceforge.net/projects/mockrunner/files/" target="_blank">here</a> 
and from this add the following jars to your project:

1. jakarta-oro-2.0.8.jar
* junit.jar
* mockrunner-jdbc.jar

Lets create the test first:

```java
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
        
        resultSetHandler.prepareResultSet("SELECT * from user", result);
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
```

You can see that in the test we prepare the result of a select statement.  We are mocking a result from a select.
We pass a mock jdbc connection into the Dao and which uses it to connect to our mock database.  We can run
verification commands used within the Dao method. Lets look at the Dao:

```java
package za.co.microcode.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import za.co.microcode.model.*;


public class Dao {
  
	private Connection conn;
	
	//The constructor allows us to pass in a connection of our choice
	public Dao(Connection conn){
		this.conn = conn;
	}
	
	public List<User> getAll() throws SQLException{
		List<User> userList = new ArrayList<User>();
    	ResultSet rs = null;
    	try {
			String sql = "SELECT * from user";
            rs = this.conn.createStatement().executeQuery(sql);
            if (rs.next()) {
                 userList.add(new User(
                		 	rs.getString(1),
                		 	rs.getString(2),
                		 	rs.getString(3)
                		 ));
            }
    	}catch(SQLException exception){
    		throw exception;
    	} finally {
    		conn.close();
    	}
		return userList;
	}

}

```

In the "getAll" method we use the passed in connection and execute a sql statement based on this connection.  The result 
returned from the sql statement will be the prepared resultset.  The fills the userlist collection which is returned.

