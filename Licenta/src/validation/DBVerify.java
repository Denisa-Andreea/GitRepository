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
	
	public DBVerify(){
	}
	
	public String titleVerify(String title){
		PreparedStatement statement;
		ResultSet result;
		try {
			statement = con.prepareStatement("SELECT title FROM carti WHERE carti.title='"+title+"'");
			result = statement.executeQuery();
			while(result.next()){
				return "exista";
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "nu exista";
	}
	
	public String publisherVerify(String publisher, String country, String city){
		PreparedStatement statement;
		ResultSet result;
		String countryName = "";
		try {
			statement = con.prepareStatement("SELECT name FROM country WHERE country.code ='"+country+"'");
			result = statement.executeQuery();
			while(result.next()){
				countryName = result.getString("name");
			}
			result.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			statement = con.prepareStatement("select name from publisher where name='"+publisher+"' and country='"+countryName+"' and city='"+city+"'");
			result = statement.executeQuery();
			while(result.next()){
				return "exista";
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "nu exista";
	}
	
	public User userDB(){
		PreparedStatement statement;
		ResultSet result;
		User user = new User();
		try {
			statement = con.prepareStatement("select user, password from login where id = 1");
			result = statement.executeQuery();
			while(result.next()){
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
