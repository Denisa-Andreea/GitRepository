package functions;

import iteme.Authors;
import iteme.BookAuthor;
import iteme.Books;

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
	
	private int numberOfRecords;

	/**
	 * afisarea cartilor
	 */

	public ArrayList<BookAuthor> fetchBooks(int beginRecord, int numberOfRecords) {
		try {
			PreparedStatement selectBooks = con
					.prepareStatement("select SQL_CALC_FOUND_ROWS carti.id_carte,carti.title,GROUP_CONCAT(CONCAT_WS(' ', autori.lastname, autori.firstname) ORDER BY autori.firstname SEPARATOR ', '),carti.year,publisher.name,publisher.address, carti.volume,carti.series,carti.edition,carti.month, carti.note from (carti left join carte_autor on carti.id_carte = carte_autor.id_carte) left join autori on carte_autor.id_autor = autori.id_autor left join publisher on publisher.id_publisher = carti.id_publisher group by carti.id_carte limit "+beginRecord+","+numberOfRecords);
			ResultSet resultBooks = selectBooks.executeQuery();
			BookAuthor bookAuthor;
			ArrayList<BookAuthor> listBook = new ArrayList<BookAuthor>();

			while (resultBooks.next()) {
				bookAuthor = new BookAuthor();

				bookAuthor.setIdBook(Integer.parseInt(resultBooks
						.getString("carti.id_carte")));
				bookAuthor.setTitle(resultBooks.getString("carti.title"));
				bookAuthor
						.setAutors(resultBooks
								.getString("GROUP_CONCAT(CONCAT_WS(' ', autori.lastname, autori.firstname) ORDER BY autori.firstname SEPARATOR ', ')"));
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
			
			PreparedStatement statement = con.prepareStatement("SELECT FOUND_ROWS()");
			resultBooks = statement.executeQuery();
			if(resultBooks.next()){
				this.numberOfRecords = resultBooks.getInt(1);
			}
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
			int idPubliaher, int volume, int year, String series,
			String edition, String month, String note) {
		int idBook;
		int idAuthor;
		try {
			insertBooks.insertIntoBooks(title, idPubliaher, year, volume, series, edition, month, note);
			idBook = ties.getIdBook(title);

			/**
			 * pentru fiecare autor se verifica daca exista si se adauga n baza
			 * de date ..adaugandu's si legatura cu id-ul cartii
			 */
			int count = 0;
			while (count < authorList.size()) {
				String authorFN = authorList.get(count).getFirstName();
				String authorLN = authorList.get(count).getLastName();
				authorFN = authorFN.replaceAll("^ +| +$|( )+", "$1");
				authorLN = authorLN.replaceAll("^ +| +$|( )+", "$1");
				insertBooks.insertIntoAuthor(authorFN, authorLN);
				idAuthor = ties.getIdAuthor(authorFN, authorLN);
				ties.insertTiesBook(idBook, idAuthor);
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Books> selectBook(int idBook){
		ArrayList<Books> bookList = new ArrayList<Books>();
		ArrayList<Authors> authorList = new ArrayList<Authors>();
		Books book;
		Authors author;
		
		try {
			PreparedStatement selectAuthors = con.prepareStatement("select autori.id_autor,autori.firstname,autori.lastname from (carte_autor LEFT JOIN autori On carte_autor.id_autor = autori.id_autor) where carte_autor.id_carte = "+idBook);
			ResultSet resultAuthors = selectAuthors.executeQuery();
			
			while(resultAuthors.next()){
				author = new Authors();
				
				author.setFirstName(resultAuthors.getString("autori.firstname"));
				author.setLastName(resultAuthors.getString("autori.lastname"));
				authorList.add(author);
			}
			resultAuthors.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			PreparedStatement selectBook = con.prepareStatement("select publisher.id_publisher,publisher.name,publisher.address,carti.title,carti.year,carti.volume,carti.series,carti.edition,carti.month,carti.note FROM (carti LEFT JOIN publisher on carti.id_publisher = publisher.id_publisher) WHERE carti.id_carte = " + idBook);
			ResultSet resultBook = selectBook.executeQuery();
			
			while(resultBook.next()){
				book = new Books();
				
				book.setAutors(authorList);
				book.setTitle(resultBook.getString("carti.title"));
				book.setId_publisher(Integer.parseInt(resultBook.getString("publisher.id_publisher")));
				book.setPublisher(resultBook.getString("publisher.name"));
				book.setAddress(resultBook.getString("publisher.address"));
				book.setYear(Integer.parseInt(resultBook.getString("carti.year")));
				book.setVolume(Integer.parseInt(resultBook.getString("carti.volume")));
				book.setSeries(resultBook.getString("carti.series"));
				book.setEdition(resultBook.getString("carti.edition"));
				book.setMonth(resultBook.getString("carti.month"));
				book.setNote(resultBook.getString("carti.note"));
				book.setIdBook(idBook);
				
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bookList;
	}

	public int getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

}
