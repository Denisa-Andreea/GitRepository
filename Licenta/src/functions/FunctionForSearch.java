package functions;

import iteme.Authors;
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

	/**
	 * cauta in tabelul specificat toate numele care incep cu word
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
					+ word + "' OR CONCAT_WS(' ' ,autori.firstname, autori.lastname) LIKE '"+word+" '";
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
}
