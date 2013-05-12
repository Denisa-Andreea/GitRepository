package functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conections.DBCon;

public class FunctionForUpdatePublisher {

	DBCon dbcon = DBCon.getConnection();
	Connection con = dbcon.getCon();

	public void editPublisher(int idPublisher, String name, String country,
			String city) {
		PreparedStatement statement;
		String countryName = "";
		try {
			statement = con.prepareStatement("SELECT name FROM country WHERE country.code ='"+country+"'");
			ResultSet result = statement.executeQuery();
			while(result.next()){
				countryName = result.getString("name");
			}
			result.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			statement = con
					.prepareStatement("UPDATE publisher SET publisher.name = '"
							+ name + "', publisher.country = '" + countryName
							+ "', publisher.city='" + city
							+ "' where publisher.id_publisher=" + idPublisher);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
