package conections;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBCon {

	private Connection con = null;
	private static DBCon connection = null;

	private DBCon() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			setCon(DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/licenta", "root", ""));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static DBCon getConnection() {
		if (connection == null) {
			connection = new DBCon();
		}
		return connection;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
}