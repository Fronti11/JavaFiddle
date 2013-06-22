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
