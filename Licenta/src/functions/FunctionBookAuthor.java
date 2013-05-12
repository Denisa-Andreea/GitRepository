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
	FuntionToUse replace = new FuntionToUse();

	private int numberOfRecords;

	/**
	 * afisarea cartilor
	 */

	public ArrayList<BookAuthor> fetchBooks(int beginRecord, int numberOfRecords) {
		ResultSet resultBooks;
		PreparedStatement statement;
		ArrayList<BookAuthor> listBook = new ArrayList<BookAuthor>();
		try {
			statement = con
					.prepareStatement("SELECT SQL_CALC_FOUND_ROWS carti.id_carte, carti.title,"
							+ " GROUP_CONCAT(CONCAT_WS(' ', autori.lastname, autori.firstname) ORDER BY autori.firstname SEPARATOR ', '),"
							+ "carti.year, publisher.name, publisher.city, publisher.country, carti.volume, carti.series, carti.edition, carti.month,"
							+ " carti.note from (carti LEFT JOIN carte_autor ON carti.id_carte = carte_autor.id_carte)"
							+ " LEFT JOIN autori ON carte_autor.id_autor = autori.id_autor"
							+ " LEFT JOIN publisher ON publisher.id_publisher = carti.id_publisher GROUP BY carti.id_carte limit "
							+ beginRecord + "," + numberOfRecords);
			resultBooks = statement.executeQuery();
			BookAuthor bookAuthor;

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
				bookAuthor.setCountry(resultBooks
						.getString("publisher.country"));
				bookAuthor.setCity(resultBooks.getString("publisher.city"));
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
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			statement = con.prepareStatement("SELECT FOUND_ROWS()");
			resultBooks = statement.executeQuery();
			if (resultBooks.next()) {
				this.numberOfRecords = resultBooks.getInt(1);
			}
			statement.close();
			System.out.println();
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
			insertBooks.insertIntoBooks(title, idPubliaher, year, volume,
					series, edition, month, note);
			idBook = ties.getIdBook(title);

			/**
			 * pentru fiecare autor se verifica daca exista si se adauga n baza
			 * de date ..adaugandu's si legatura cu id-ul cartii
			 */
			int count = 0;
			while (count < authorList.size()) {
				String authorFN = authorList.get(count).getFirstName();
				String authorLN = authorList.get(count).getLastName();
				authorFN = replace.multipleSpaceElim(authorFN);
				authorLN = replace.multipleSpaceElim(authorLN);
				insertBooks.insertIntoAuthor(authorFN, authorLN);
				idAuthor = ties.getIdAuthor(authorFN, authorLN);
				ties.insertTiesBook(idBook, idAuthor);
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Books> selectBook(int idBook, int exist) {
		ArrayList<Books> bookList = new ArrayList<Books>();
		Books book;
		ArrayList<Authors> authorList = selectAuthorByBook(idBook, exist);
		PreparedStatement selectBook = null;

		try {
			selectBook = con
					.prepareStatement("select publisher.id_publisher, publisher.name, publisher.country, publisher.city, carti.title, carti.year,"
							+ "carti.volume,carti.series,carti.edition,carti.month,carti.note FROM "
							+ "(carti LEFT JOIN publisher on carti.id_publisher = publisher.id_publisher) WHERE carti.id_carte = "
							+ idBook);
			ResultSet resultBook = selectBook.executeQuery();

			while (resultBook.next()) {
				book = new Books();

				book.setAutors(authorList);
				book.setTitle(resultBook.getString("carti.title"));
				book.setId_publisher(Integer.parseInt(resultBook
						.getString("publisher.id_publisher")));
				book.setPublisher(resultBook.getString("publisher.name"));
				book.setCountry(resultBook.getString("publisher.country"));
				book.setCity(resultBook.getString("publisher.city"));
				book.setYear(Integer.parseInt(resultBook
						.getString("carti.year")));
				book.setVolume(Integer.parseInt(resultBook
						.getString("carti.volume")));
				book.setSeries(resultBook.getString("carti.series"));
				book.setEdition(resultBook.getString("carti.edition"));
				book.setMonth(resultBook.getString("carti.month"));
				book.setNote(resultBook.getString("carti.note"));
				book.setIdBook(idBook);

				bookList.add(book);
			}
			selectBook.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (selectBook != null) {
					selectBook.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return bookList;
	}

	public int getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	public ArrayList<BookAuthor> sortBooks(int beginRecord,
			int numberOfRecords, String column, String direction) {
		ResultSet resultBooks;
		PreparedStatement statement = null;
		ArrayList<BookAuthor> listBook = new ArrayList<BookAuthor>();
		try {
			statement = con
					.prepareStatement("SELECT SQL_CALC_FOUND_ROWS carti.id_carte,carti.title,"
							+ "GROUP_CONCAT(CONCAT_WS(' ', autori.lastname, autori.firstname) ORDER BY autori.firstname SEPARATOR ', '),"
							+ "carti.year, publisher.name, publisher.city, publisher.country, carti.volume, carti.series, carti.edition, carti.month,carti.note"
							+ " from (carti LEFT JOIN carte_autor ON carti.id_carte = carte_autor.id_carte) "
							+ "LEFT JOIN autori ON carte_autor.id_autor = autori.id_autor "
							+ "LEFT JOIN publisher ON publisher.id_publisher = carti.id_publisher "
							+ "GROUP BY carti.id_carte ORDER BY carti."
							+ column
							+ " "
							+ direction
							+ " LIMIT "
							+ beginRecord + "," + numberOfRecords);
			resultBooks = statement.executeQuery();
			BookAuthor bookAuthor;

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
				bookAuthor.setCity(resultBooks.getString("publisher.city"));
				bookAuthor.setCountry(resultBooks
						.getString("publisher.country"));
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
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			statement = con.prepareStatement("SELECT FOUND_ROWS()");
			resultBooks = statement.executeQuery();
			if (resultBooks.next()) {
				this.numberOfRecords = resultBooks.getInt(1);
			}
			statement.close();
			return listBook;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ArrayList<Authors> selectAuthorByBook(int idBook, int exist) {
		Authors author;
		ArrayList<Authors> authorList = new ArrayList<Authors>();
		PreparedStatement selectAuthors = null;

		try {
			selectAuthors = con
					.prepareStatement("select autori.id_autor,autori.firstname,autori.lastname from "
							+ "(carte_autor LEFT JOIN autori On carte_autor.id_autor = autori.id_autor) where carte_autor.id_carte = "
							+ idBook + " and autori.id_autor != " + exist);
			ResultSet resultAuthors = selectAuthors.executeQuery();

			while (resultAuthors.next()) {
				author = new Authors();

				author.setFirstName(resultAuthors.getString("autori.firstname"));
				author.setLastName(resultAuthors.getString("autori.lastname"));
				authorList.add(author);
			}
			resultAuthors.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (selectAuthors != null) {
					selectAuthors.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return authorList;
	}

	public Authors existName(int exist){
		Authors author = new Authors();
		PreparedStatement statement = null;
		
		try {
			statement = con.prepareStatement("SELECT autori.firstname,autori.lastname from autori WHERE id_autor = "+exist);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				author.setFirstName(result.getString("autori.firstname"));
				author.setLastName(result.getString("autori.lastname"));
			}
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
		return author;
	}
	public ArrayList<Integer> selectBookByAuthor(int idAuthor) {
		ArrayList<Integer> bookId = new ArrayList<Integer>();
		PreparedStatement statement = null;
		ResultSet result;
		try {
			statement = con
					.prepareStatement("SELECT carti.id_carte FROM carti LEFT JOIN carte_autor ON carte_autor.id_carte = carti.id_carte"
							+ " WHERE id_autor =" + idAuthor);
			result = statement.executeQuery();
			while(result.next()){
				bookId.add(result.getInt("id_carte"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bookId;
	}
}