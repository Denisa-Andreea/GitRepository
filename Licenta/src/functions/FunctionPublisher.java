package functions;

import iteme.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conections.DBCon;

public class FunctionPublisher {

	DBCon dbcon = DBCon.getConnection();
	Connection con = dbcon.getCon();

	private int numberOfRecords;
	
	public ArrayList<Publisher> fetchPublisher() {
		ArrayList<Publisher> listPublisher = new ArrayList<Publisher>();
		PreparedStatement selectPublisher;
		ResultSet resultPublisher;
		try {
			selectPublisher = con
					.prepareStatement("select * from publisher");
			resultPublisher = selectPublisher.executeQuery();
			Publisher publisher;

			while (resultPublisher.next()) {
				publisher = new Publisher();
				publisher.setId_publisher(Integer.parseInt(resultPublisher
						.getString("id_publisher")));
				publisher.setName(resultPublisher.getString("name"));
				publisher.setCountry(resultPublisher.getString("country"));
				publisher.setCity(resultPublisher.getString("city"));
				listPublisher.add(publisher);
			}
			selectPublisher.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listPublisher;
	}
	
	public ArrayList<Publisher> fetchPublisherSort(int beginRecord, int numberOfRecords, String column, String direction) {
		ArrayList<Publisher> listPublisher = new ArrayList<Publisher>();
		PreparedStatement selectPublisher;
		ResultSet resultPublisher;
		try {
			selectPublisher = con
					.prepareStatement("select SQL_CALC_FOUND_ROWS * from publisher ORDER BY publisher."+column+" "+direction+" limit "
							+ beginRecord + "," + numberOfRecords);
			resultPublisher = selectPublisher.executeQuery();
			Publisher publisher;

			while (resultPublisher.next()) {
				publisher = new Publisher();
				publisher.setId_publisher(Integer.parseInt(resultPublisher
						.getString("id_publisher")));
				publisher.setName(resultPublisher.getString("name"));
				publisher.setCountry(resultPublisher.getString("country"));
				publisher.setCity(resultPublisher.getString("city"));
				listPublisher.add(publisher);
			}
			selectPublisher.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			selectPublisher = con.prepareStatement("SELECT FOUND_ROWS()");
			resultPublisher = selectPublisher.executeQuery();
			if (resultPublisher.next()) {
				this.setNumberOfRecords(resultPublisher.getInt(1));
			}
			selectPublisher.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listPublisher;
	}
	
	public void insertPublisher(String name, String country, String city){
		PreparedStatement insertPublisher;
		String countryName = "";
		try {
			insertPublisher = con.prepareStatement("SELECT name FROM country WHERE country.code ='"+country+"'");
			ResultSet result = insertPublisher.executeQuery();
			while(result.next()){
				countryName = result.getString("name");
			}
			result.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			insertPublisher = con.prepareStatement("insert into publisher(name,country,city) values(?,?,?)");
			if(!existPublisher(name, country, city)){
				insertPublisher.setString(1, name);
				insertPublisher.setString(2, countryName);
				insertPublisher.setString(3, city);
				insertPublisher.executeUpdate();
				insertPublisher.close();
			}
			insertPublisher.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Publisher selectPublisher(int idPublisher){
		PreparedStatement statement;
		ResultSet result;
		Publisher publisher = new Publisher();
		try {
			statement = con.prepareStatement("SELECT * FROM publisher WHERE publisher.id_publisher="+idPublisher);
			result = statement.executeQuery();
			while (result.next()) {
				publisher.setName(result.getString("name"));
				publisher.setCountry(result.getString("country"));
				publisher.setCity(result.getString("city"));
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return publisher;
	}
	
	public String countryCode(String country){
		PreparedStatement statement;
		ResultSet result;
		String code = null;
		try {
			statement = con.prepareStatement("SELECT code FROM country WHERE country.name = '"+country+"'");
			result = statement.executeQuery();
			while(result.next()){
				code = result.getString("code");
			}
			//System.out.println(code);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return code;
	}
	
	public boolean existPublisher(String name, String country, String city){
		try {
			PreparedStatement verify = con.prepareStatement("select * from publisher where name='"+name+"' and country='"+country+"' and city='"+city+"'");
			ResultSet resultVerify =verify.executeQuery();
			if(!resultVerify.next()){
				verify.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public int getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}
	
}
