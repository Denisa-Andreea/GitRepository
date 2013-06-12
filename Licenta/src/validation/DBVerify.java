package validation;

import iteme.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conections.DBCon;

public class DBVerify {

	DBCon dbcon = DBCon.getConnection();
	Connection con = dbcon.getCon();

	public DBVerify() {
	}

	public String titleVerify(String title, String table) {
		PreparedStatement statement;
		ResultSet result;
		try {
			statement = con.prepareStatement("SELECT title FROM " + table
					+ " WHERE title='" + title + "'");
			result = statement.executeQuery();
			while (result.next()) {
				return "exista";
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "nu exista";
	}

	public String titleVerifyConditionat(String title, String table,
			String valoareAnterioara) {
		PreparedStatement statement;
		ResultSet result;
		try {
			statement = con.prepareStatement("SELECT title FROM " + table
					+ " WHERE title='" + title + "' and title != '"
					+ valoareAnterioara + "'");
			result = statement.executeQuery();
			while (result.next()) {
				return "exista";
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "nu exista";
	}

	public String publisherVerify(String publisher, String country, String city) {
		PreparedStatement statement;
		ResultSet result;
		String countryName = "";
		try {
			statement = con
					.prepareStatement("SELECT name FROM country WHERE country.code ='"
							+ country + "'");
			result = statement.executeQuery();
			while (result.next()) {
				countryName = result.getString("name");
			}
			result.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			statement = con
					.prepareStatement("select name from publisher where name='"
							+ publisher + "' and country='" + countryName
							+ "' and city='" + city + "'");
			result = statement.executeQuery();
			while (result.next()) {
				return "exista";
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "nu exista";
	}

	public String publisherVerifyCondito(String publisher, String country,
			String city, String publisherAnt, String countryAnt, String cityAnt) {
		PreparedStatement statement;
		ResultSet result;
		String countryName = "";
		try {
			statement = con
					.prepareStatement("SELECT name FROM country WHERE country.code ='"
							+ country + "'");
			result = statement.executeQuery();
			while (result.next()) {
				countryName = result.getString("name");
			}
			result.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String countryAntName = "";
		try {
			statement = con
					.prepareStatement("SELECT name FROM country WHERE country.code ='"
							+ countryAnt + "'");
			result = statement.executeQuery();
			while (result.next()) {
				countryAntName = result.getString("name");
			}
			result.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
//		System.out.println("Curente: "+publisher+" "+countryName+" "+city);
//		System.out.println("Anterioare: "+publisherAnt+" "+countryAntName+" "+cityAnt);
		try {
			statement = con
					.prepareStatement("select name from publisher where name='"
							+ publisher + "' and country='" + countryName
							+ "' and city='" + city + "' and country != '" + countryAntName
							+ "' and city != '" + cityAnt + "'");
			result = statement.executeQuery();
			while (result.next()) {
				return "exista";
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "nu exista";
	}

	public String journalVerify(String title) {
		PreparedStatement statement;
		ResultSet result;
		try {
			statement = con
					.prepareStatement("SELECT name FROM journal WHERE name='"
							+ title + "'");
			result = statement.executeQuery();
			while (result.next()) {
				return "exista";
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "nu exista";
	}

	public String journalVerifyCondition(String title,String titleAnt) {
		PreparedStatement statement;
		ResultSet result;
		
		try {
			statement = con
					.prepareStatement("SELECT name FROM journal WHERE name='"
							+ title + "' and name!='"+titleAnt+"'");
			result = statement.executeQuery();
			while (result.next()) {
				return "exista";
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "nu exista";
	}

	public User userDB() {
		PreparedStatement statement;
		ResultSet result;
		User user = new User();
		try {
			statement = con
					.prepareStatement("select user, password from login where id = 1");
			result = statement.executeQuery();
			while (result.next()) {
				user.setUser(result.getString("user"));
				user.setPassword(result.getString("password"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
