package functions;

import iteme.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conections.DBCon;

public class GetCountryCity {

	DBCon dbcon = DBCon.getConnection();
	Connection con = dbcon.getCon();

	public ArrayList<Country> fetchCountry() {
		PreparedStatement statement;
		ResultSet result;
		ArrayList<Country> countryList = new ArrayList<Country>();
		Country country;
		try {
			statement = con.prepareStatement("select code, name from country");
			result = statement.executeQuery();

			while (result.next()) {
				country = new Country();
				country.setCode(result.getString("code"));
				country.setName(result.getString("name"));
				countryList.add(country);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return countryList;
	}

	public ArrayList<String> fetchCity(String code) {
		PreparedStatement statement;
		ResultSet result;
		ArrayList<String> cityList = new ArrayList<String>();
		try {
			statement = con.prepareStatement("SELECT name FROM city WHERE city.CountryCode='"+code+"'");
			result = statement.executeQuery();
			
			while(result.next()){
				cityList.add(result.getString("name"));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cityList;
	}
}
