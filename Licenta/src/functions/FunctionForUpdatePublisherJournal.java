package functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conections.DBCon;

public class FunctionForUpdatePublisherJournal {

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
	
	public void editJournal(int id,String name, String description) {
		PreparedStatement statement;
		try {
			statement = con
					.prepareStatement("UPDATE journal SET journal.name = '"
							+ name + "', journal.description = '" + description
							+ "' where journal.id_journal='" + id+"'");
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
