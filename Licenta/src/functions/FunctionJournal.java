package functions;

import iteme.Journal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conections.DBCon;

public class FunctionJournal {

	DBCon dbcon = DBCon.getConnection();
	Connection con = dbcon.getCon();

	private int numberOfRecords;
	
	public ArrayList<Journal> fetchJournal() {
		ArrayList<Journal> listJournal = new ArrayList<Journal>();
		PreparedStatement select;
		ResultSet result;
		try {
			select = con
					.prepareStatement("select * from journal");
			result = select.executeQuery();
			
			Journal journal;
			
			while(result.next()){
				journal = new Journal();
				journal.setId_journal(Integer.parseInt(result.getString("id_journal")));
				journal.setName(result.getString("name"));
				journal.setDescription(result.getString("description"));
				
				listJournal.add(journal);
			}
			select.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listJournal;
	}

	public int getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}
}
