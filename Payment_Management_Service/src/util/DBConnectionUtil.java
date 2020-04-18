package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * @author Ishanka
 *get the DB connection here
 */

public class DBConnectionUtil {
	
	public Connection connect() {
		
		Connection conn = null;
		
		try {
			

			 Class.forName("com.mysql.jdbc.Driver"); 
			 
			 conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/payments", "root", "");
			
			 
			 //System.out.println("testing");
			 if(conn == null) {
				 System.out.println("Error with connection of database");
			 }
			 else{
				 System.out.println("Successful connection of database");
			 }

		} catch (Exception e) {
			System.out.println("Connection Lost...");
			e.printStackTrace();
		}
		
		return conn;
	}	
}
