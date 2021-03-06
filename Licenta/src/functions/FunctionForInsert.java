package functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conections.DBCon;

public class FunctionForInsert {

	DBCon dbcon = DBCon.getConnection();
	Connection con = dbcon.getCon();

	/**
	 * insereaza in baza de date cartea doar daca aceasta nu exista deja
	 * */

	public void insertIntoBooks(String title, int id, int year, int volume,
			String series, String edition, String month, String note) {
		try {
			PreparedStatement insertBook = con
					.prepareStatement("insert into carti(title, id_publisher, year, volume, series, edition, month, note)"
							+ " values(?,?,?,?,?,?,?,?)");
			if (!existBook(title)) {
				insertBook.setString(1, title);
				insertBook.setInt(2, id);
				insertBook.setInt(3, year);
				insertBook.setInt(4, volume);
				insertBook.setString(5, series);
				insertBook.setString(6, edition);
				insertBook.setString(7, month);
				insertBook.setString(8, note);
				insertBook.executeUpdate();
				insertBook.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * insereaza in baza de date un articol doar daca acesta nu exista deja
	 */

	public void insertIntoArticle(String title, int id, int year, int volume,
			int number, String month, String note) {
		
		try {
			PreparedStatement insert = con.prepareStatement("insert into articole(title, id_journal, year, volume, number, month, note)"
								+ " values(?,?,?,?,?,?,?)");
			if(!existArticle(title)){
				insert.setString(1, title);
				insert.setInt(2, id);
				insert.setInt(3, year);
				insert.setInt(4, volume);
				insert.setInt(5, number);
				insert.setString(6, month);
				insert.setString(7, note);
				insert.executeUpdate();
				insert.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * inserarea unui autor in baza de date doar daca acesta nu exista deja
	 * */

	public void insertIntoAuthor(String authorFN, String authorLN) {
		try {
			PreparedStatement insertAuthor = con
					.prepareStatement("insert into autori(firstname, lastname) values(?,?)");
			if (!existAuthor(authorFN, authorLN)) {
				insertAuthor.setString(1, authorFN);
				insertAuthor.setString(2, authorLN);
				insertAuthor.executeUpdate();
				insertAuthor.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * verifica daca exista cartea cu titlul respectiv in bazaa de date
	 * */

	public boolean existBook(String title) {
		try {
			PreparedStatement exist = con
					.prepareStatement("select title,id_carte from carti where title = '"
							+ title + "'");
			ResultSet resultExist = exist.executeQuery();
			if (!resultExist.next()) {
				exist.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * verifica daca exista articolul cu titlul respectiv in baza de date
	 */
	
	public boolean existArticle(String title){
		try {
			PreparedStatement exist = con
					.prepareStatement("select title,id_article from articole where title = '"
							+ title + "'");
			ResultSet resultExist = exist.executeQuery();
			if(!resultExist.next()){
				exist.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * verifica daca autorul pe care dorim sa il adaugam exista sau nu in baza
	 * de date
	 * */

	public boolean existAuthor(String authorFN, String authorLN) {
		try {
			PreparedStatement existAuthor = con
					.prepareStatement("select firstname,lastname from autori where firstname = '"
							+ authorFN + "' and lastname = '" + authorLN + "'");
			ResultSet resultexist = existAuthor.executeQuery();
			if (!resultexist.next()) {
				existAuthor.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

}
