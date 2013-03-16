package functions;

import iteme.Authors;
import iteme.BookAuthor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conections.DBCon;

public class FunctionBookAuthor {

	DBCon dbcon = DBCon.getConnection();
	Connection con = dbcon.getCon();
	
	FunctionForInsert insertBooks = new FunctionForInsert();
	FunctionForInsertTies ties = new FunctionForInsertTies();
	
	/**
	 * afisarea cartilor
	 */
	
	public ArrayList<BookAuthor> fetchBooks() {
		try {
			PreparedStatement selectBooks = con
					.prepareStatement("select carti.id_carte,carti.title,GROUP_CONCAT(CONCAT_WS(' ', autori.lastname, autori.firstname) SEPARATOR ','),carti.year,publisher.name,publisher.address, carti.volume,carti.series,carti.edition,carti.month, carti.note from (carti left join carte_autor on carti.id_carte = carte_autor.id_carte) left join autori on carte_autor.id_autor = autori.id_autor left join publisher on publisher.id_publisher = carti.id_publisher group by carti.id_carte");
			ResultSet resultBooks = selectBooks.executeQuery();
			BookAuthor bookAuthor;
			ArrayList<BookAuthor> listBook = new ArrayList<BookAuthor>();

			while (resultBooks.next()) {
				bookAuthor = new BookAuthor();

				bookAuthor.setTitle(resultBooks.getString("carti.title"));
				bookAuthor
						.setAutors(resultBooks
								.getString("GROUP_CONCAT(CONCAT_WS(' ', autori.lastname, autori.firstname) SEPARATOR ',')"));
				bookAuthor
						.setPublisher(resultBooks.getString("publisher.name"));
				bookAuthor.setAddress(resultBooks
						.getString("publisher.address"));
				bookAuthor.setYear(Integer.parseInt(resultBooks
						.getString("carti.year")));
				bookAuthor.setSeries(resultBooks.getString("carti.series"));
				bookAuthor.setEdition(resultBooks.getString("carti.edition"));
				bookAuthor.setMonth(resultBooks.getString("carti.month"));
				bookAuthor.setNote(resultBooks.getString("carti.note"));
				bookAuthor.setVolume(Integer.parseInt(resultBooks
						.getString("carti.volume")));

				listBook.add(bookAuthor);
			}
			selectBooks.close();
			return listBook;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * inserarea unei carti
	 */
	public void insertBook(String title, ArrayList<Authors> authorList,
			int idPubliaher, int volume, int year) {
		int idBook;
		int idAuthor;
		try {			
			insertBooks.insertIntoBooks(title, idPubliaher, year, volume);
			idBook = ties.getIdBook(title);
			
			/**
			 * pentru fiecare autor se verifica daca exista si se adauga n baza de date ..adaugandu's si legatura cu id-ul cartii
			 */
			int count = 0;
			while(count < authorList.size()){
				String authorFN = authorList.get(count).getFirstName();
				String authorLN = authorList.get(count).getLastName();
				authorFN = authorFN.replaceFirst("\\s","");
				authorLN = authorLN.replaceFirst("\\s", "");
				insertBooks.insertIntoAuthor(authorFN, authorLN);			
				idAuthor = ties.getIdAuthor(authorFN, authorLN);
				ties.insertTiesBook(idBook, idAuthor);
				count ++;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
