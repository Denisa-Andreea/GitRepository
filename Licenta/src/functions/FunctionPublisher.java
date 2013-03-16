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
				publisher.setAddress(resultPublisher.getString("address"));
				listPublisher.add(publisher);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listPublisher;
	}
	
	public void insertPublisher(){
		
	}

}
