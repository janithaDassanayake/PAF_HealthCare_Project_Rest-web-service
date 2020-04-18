package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaredb", "root", "");
			System.out.println("Successfully Connected");

		} catch (Exception e) {
			System.out.println("Connect Fail");
			e.printStackTrace();
			System.out.println(e);
		}

		return con;
	}
}
