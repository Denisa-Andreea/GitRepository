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
		PreparedStatement select = null;
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
		}finally {
			try {
				if (select != null) {
					select.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listJournal;
	}

	public int getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}
	
	public void insertJournal(String name, String description){
		
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("insert into journal(name,description) values(?,?)");
			if(!existJournal(name)){
				statement.setString(1, name);
				statement.setString(2, description);
				statement.executeUpdate();
				statement.close();
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean existJournal(String name){
		try {
			PreparedStatement verify = con.prepareStatement("select * from journal where name='"+name+"'");
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
	
	public ArrayList<Journal> fetchJournalSort(int beginRecord, int numberOfRecords, String column, String direction) {
		ArrayList<Journal> listJournal = new ArrayList<Journal>();
		PreparedStatement statement = null;
		ResultSet result;
		try {
			statement = con
					.prepareStatement("select SQL_CALC_FOUND_ROWS * from journal ORDER BY journal."+column+" "+direction+" limit "
							+ beginRecord + "," + numberOfRecords);
			result = statement.executeQuery();
			Journal journal;

			while (result.next()) {
				journal = new Journal();
				journal.setId_journal(Integer.parseInt(result
						.getString("id_journal")));
				journal.setName(result.getString("name"));
				journal.setDescription(result.getString("description"));
				listJournal.add(journal);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement = con.prepareStatement("SELECT FOUND_ROWS()");
			result = statement.executeQuery();
			if (result.next()) {
				this.setNumberOfRecords(result.getInt(1));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listJournal;
	}
	
	public Journal selectJournal(int idJournal){
		PreparedStatement statement;
		ResultSet result;
		Journal journal = new Journal();
		try {
			statement = con.prepareStatement("SELECT * FROM journal WHERE journal.id_journal="+idJournal);
			result = statement.executeQuery();
			while (result.next()) {
				journal.setName(result.getString("name"));
				journal.setDescription(result.getString("description"));
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return journal;
	}

}
