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

	public ArrayList<Publisher> fetchPublisher() {
		ArrayList<Publisher> listPublisher = new ArrayList<Publisher>();
		try {
			PreparedStatement selectPublisher = con
					.prepareStatement("select * from publisher");
			ResultSet resultPublisher = selectPublisher.executeQuery();
			Publisher publisher;

			while (resultPublisher.next()) {
				publisher = new Publisher();
				publisher.setId_publisher(Integer.parseInt(resultPublisher
						.getString("id_publisher")));
				publisher.setName(resultPublisher.getString("name"));
				publisher.setCountry(resultPublisher.getString("country"));
				listPublisher.add(publisher);
			}
			selectPublisher.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listPublisher;
	}
	
	public void insertPublisher(String name, String address){
		try {
			PreparedStatement insertPublisher = con.prepareStatement("insert into publisher(name,address) values(?,?)");
			if(!existPublisher(name, address)){
				insertPublisher.setString(1, name);
				insertPublisher.setString(2, address);
				insertPublisher.executeUpdate();
				insertPublisher.close();
			}
			insertPublisher.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean existPublisher(String name, String address){
		try {
			PreparedStatement verify = con.prepareStatement("select * from publisher where name='"+name+"' and address='"+address+"'");
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

}
