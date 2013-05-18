package functions;

import iteme.Article;
import iteme.ArticleAuthor;
import iteme.Authors;
import iteme.BookAuthor;
import iteme.Books;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import conections.DBCon;

public class FunctionForSearch {

	DBCon dbcon = DBCon.getConnection();
	Connection con = dbcon.getCon();

	FunctionBookAuthor function = new FunctionBookAuthor();
	FunctionArticle functionArticle = new FunctionArticle();

	/**
	 * cauta in tabelele specificate in numele metodei toate numele care incep
	 * cu word
	 * 
	 * @param tabel
	 * @param word
	 * @return
	 * @return un item de tipul search care contine rezultatul cautarii
	 */
	public ArrayList<Books> searchBook(String word) {
		ArrayList<Integer> resultWord = new ArrayList<Integer>();

		ArrayList<Books> resultOfSearch = new ArrayList<Books>();

		String regex = " +[[:<:]]" + word + "[- a-z]";

		String selectBooks = "SELECT id_carte FROM carti WHERE title LIKE '"
				+ word + "%' OR title REGEXP '" + regex + " | [[:<:]]" + word
				+ "'";

		PreparedStatement statement = null;
		ResultSet result;

		try {
			statement = con.prepareStatement(selectBooks);
			result = statement.executeQuery();
			while (result.next()) {
				resultWord.add(result.getInt("id_carte"));
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
		for (int element : resultWord) {
			resultOfSearch.addAll(function.selectBook(element, 0));
		}
		return resultOfSearch;
	}

	public ArrayList<Article> searchArticle(String word) {
		ArrayList<Integer> resultWord = new ArrayList<Integer>();

		ArrayList<Article> resultOfSearch = new ArrayList<Article>();

		String regex = " +[[:<:]]" + word + "[- a-z]";

		String selectBooks = "SELECT id_article FROM articole WHERE title LIKE '"
				+ word
				+ "%' OR title REGEXP '"
				+ regex
				+ " | [[:<:]]"
				+ word
				+ "'";

		PreparedStatement statement = null;
		ResultSet result;

		try {
			statement = con.prepareStatement(selectBooks);
			result = statement.executeQuery();
			while (result.next()) {
				resultWord.add(result.getInt("id_article"));

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
		for (int element : resultWord) {
			resultOfSearch.addAll(functionArticle.selectArticle(element, 0));
		}
		return resultOfSearch;
	}

	public HashMap<Authors, ArrayList<Books>> searchAuthor(String word,
			Boolean split) {
		ArrayList<Integer> resultOfAthors;
		if (split) {
			resultOfAthors = wordByWord(word);
		} else {
			resultOfAthors = searchWord(word, true);
		}
		ArrayList<Integer> resultOfBooks = new ArrayList<Integer>();
		ArrayList<Books> booksOfAuthor;
		HashMap<Authors, ArrayList<Books>> resultOfSearch = new HashMap<Authors, ArrayList<Books>>();
		for (int i = 0; i < resultOfAthors.size(); i++) {
			resultOfBooks = function.selectBookByAuthor(resultOfAthors.get(i));
			booksOfAuthor = new ArrayList<Books>();
			for (int j = 0; j < resultOfBooks.size(); j++) {
				booksOfAuthor.addAll(function.selectBook(resultOfBooks.get(j),
						resultOfAthors.get(i)));
			}
			resultOfSearch.put(function.existName(resultOfAthors.get(i)),
					booksOfAuthor);
		}
		return resultOfSearch;
	}

	public ArrayList<Integer> searchWord(String word, Boolean exactly) {

		ArrayList<Integer> resultAuthors = new ArrayList<Integer>();

		String regex = " +[[:<:]]" + word + "[- a-z]";
		String selectAuthors;
		if (exactly) {
			selectAuthors = "SELECT id_autor, firstname, lastname FROM autori WHERE CONCAT_WS(' ' ,autori.firstname, autori.lastname) LIKE '"
					+ word
					+ "' OR CONCAT_WS(' ' ,autori.firstname, autori.lastname) LIKE '"
					+ word + " '";
		} else {
			selectAuthors = "SELECT id_autor, firstname, lastname FROM autori WHERE lastname LIKE '"
					+ word
					+ "%' OR lastname REGEXP '"
					+ regex
					+ " | +[[:<:]]"
					+ word
					+ "' OR firstname LIKE '"
					+ word
					+ "%' OR firstname REGEXP ' "
					+ regex
					+ " | +[[:<:]]"
					+ word + "'";
		}
		PreparedStatement statement = null;
		ResultSet result;

		try {
			statement = con.prepareStatement(selectAuthors);
			result = statement.executeQuery();
			while (result.next()) {
				resultAuthors.add(result.getInt("id_autor"));
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
		return resultAuthors;
	}

	public ArrayList<Integer> wordByWord(String word) {

		ArrayList<Integer> oldList = new ArrayList<Integer>();
		ArrayList<Integer> notOldList = new ArrayList<Integer>();

		String[] wordArray = word.split(" ");
		notOldList = searchWord(wordArray[0], false);
		for (int i = 1; i < wordArray.length; i++) {
			oldList = notOldList;
			notOldList = searchWord(wordArray[i], false);
			for (int j = 0; j < notOldList.size(); j++) {
				if (!oldList.contains(notOldList.get(j))) {
					notOldList.remove(j);
				}
			}
		}
		return notOldList;
	}

	public ArrayList<BookAuthor> advanceSearchBook(String title, String author,
			String yearUp, String yearDown) {
		PreparedStatement statement = null;
		ResultSet result;
		BookAuthor book;
		ArrayList<BookAuthor> bookList = new ArrayList<BookAuthor>();
		try {
			
			statement = con
					.prepareStatement("CREATE OR REPLACE VIEW tabelaInterm AS SELECT carti.title,"
							+ "GROUP_CONCAT(CONCAT_WS(' ',autori.lastname, autori.firstname) SEPARATOR ',') AS author,"
							+ "carti.year, publisher.name, publisher.city, publisher.country, "
							+ "carti.volume, carti.series, carti.edition, carti.month,carti.note FROM "
							+ "(carti LEFT JOIN carte_autor ON carti.id_carte = carte_autor.id_carte "
							+ "LEFT JOIN autori ON carte_autor.id_autor = autori.id_autor "
							+ "LEFT JOIN publisher ON publisher.id_publisher = carti.id_publisher) "
							+ "WHERE (title LIKE '"
							+ title
							+ "%' OR title REGEXP ' +[[:<:]]"
							+ title
							+ "[- a-z] | [[:<:]]"
							+ title
							+ "') "
							+ "AND (carti.year BETWEEN "
							+ yearDown
							+ " AND "
							+ yearUp + ") " + "GROUP BY carti.id_carte;");
			statement.executeUpdate();
			statement = con
					.prepareStatement("SELECT title, author,year,name,city,country,volume,series,edition,month,note"
							+ " FROM tabelaInterm WHERE tabelaInterm.author LIKE '"
							+ author
							+ "%' OR tabelaInterm.author REGEXP ' +[[:<:]]"
							+ author + "[- a-z] | [[:<:]]" + author + "'");
			result = statement.executeQuery();
			while (result.next()) {
				book = new BookAuthor();
				book.setTitle(result.getString(1));
				book.setAutors(result.getString(2));
				book.setYear(result.getInt(3));
				book.setPublisher(result.getString(4));
				book.setCity(result.getString(5));
				book.setCountry(result.getString(6));
				book.setVolume(result.getInt(7));
				book.setSeries(result.getString(8));
				book.setMonth(result.getString(9));
				book.setNote(result.getString(10));
				bookList.add(book);
			}

			statement.close();
			return bookList;
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

	public ArrayList<ArticleAuthor> advanceSearchArticle(String title,
			String author, String yearUp, String yearDown) {
		PreparedStatement statement = null;
		ResultSet result;
		ArticleAuthor article;
		ArrayList<ArticleAuthor> articleList = new ArrayList<ArticleAuthor>();
		try {
			
			statement = con
					.prepareStatement("CREATE OR REPLACE VIEW tabelaInterm AS SELECT SQL_CALC_FOUND_ROWS articole.id_article, articole.title,"
							+ "GROUP_CONCAT(CONCAT_WS(' ',autori.lastname, autori.firstname) SEPARATOR ',') AS author,"
							+ "articole.year, journal.name, journal.description,articole.volume, articole.number, articole.month,articole.note"
							+ " FROM (articole LEFT JOIN articol_autor ON articole.id_article = articol_autor.id_article "
							+ "LEFT JOIN autori ON articol_autor.id_autor = autori.id_autor "
							+ "LEFT JOIN journal ON journal.id_journal = articole.id_journal) WHERE (title LIKE '"
							+ title
							+ "%' OR title REGEXP ' +[[:<:]]"
							+ title
							+ "[- a-z] | [[:<:]]"
							+ title
							+ "')"
							+ "AND (articole.year BETWEEN "
							+ yearDown
							+ " AND "
							+ yearUp
							+ ")GROUP BY articole.id_article;");
			statement.executeUpdate();
			statement = con
					.prepareStatement("SELECT title, author,year,name,description,volume,number,month,note"
							+ " FROM tabelaInterm WHERE tabelaInterm.author LIKE '"
							+ author
							+ "%' OR tabelaInterm.author REGEXP ' +[[:<:]]"
							+ author + "[- a-z] | [[:<:]]" + author + "' ");
			result = statement.executeQuery();
			while (result.next()) {
				article = new ArticleAuthor();
				article.setTitle(result.getString(1));
				article.setAutors(result.getString(2));
				article.setYear(result.getInt(3));
				article.setJournal(result.getString(4));
				article.setDescription(result.getString(5));
				article.setVolume(result.getInt(6));
				article.setNumber(result.getInt(7));
				article.setMonth(result.getString(8));
				article.setNote(result.getString(9));
				articleList.add(article);
			}
			statement.close();
			return articleList;
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
}
