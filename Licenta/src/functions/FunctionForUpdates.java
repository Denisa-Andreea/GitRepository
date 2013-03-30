package functions;

import iteme.Authors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import conections.DBCon;

public class FunctionForUpdates {

	DBCon dbcon = DBCon.getConnection();
	Connection con = dbcon.getCon();

	FunctionForDeleteBooks delete = new FunctionForDeleteBooks();
	FunctionForInsertTies function = new FunctionForInsertTies();
	FunctionForInsert insertFunction = new FunctionForInsert();

	public void editBooks(int idBook, String title,
			ArrayList<Authors> authorList, ArrayList<Authors> authorOldList,
			int idPublisher, int year, int volume, String series,
			String edition, String month, String note) {

		updateBooks(idBook, title, idPublisher, year, volume, series, edition,
				month, note);
		updateBookAuthorTies(idBook, authorList, authorOldList);
	}

	public void updateBooks(int idBook, String title, int idPublisher,
			int year, int volume, String series, String edition, String month,
			String note) {
		
		try {
			PreparedStatement updateBook = con
					.prepareStatement("update carti SET carti.title = '"
							+ title + "', carti.id_publisher='" + idPublisher
							+ "', carti.year='" + year + "', carti.volume='"
							+ volume + "', carti.series='" + series
							+ "', carti.month='" + month + "', carti.note='"
							+ note + "' where carti.id_carte='" + idBook + "'");
			updateBook.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateBookAuthorTies(int idBook, ArrayList<Authors> authorList,
			ArrayList<Authors> authorOldList) {

		/**
		 * parcurg fiecare element din vechea lista de autori, adica lista ce
		 * exista in baza de date: -verific daca elementele din aceasta lista
		 * exista in lista de autori modificata: --daca elementul nu exista
		 * atunci: din tabela de legatura a cartilor cu autorii sterg linia ce
		 * contine id-ul autorului si al cartii
		 */
		int index = 0;
		System.out.println("verifi in old list");
		while (index < authorOldList.size()) {
			if (notExistInList(authorOldList.get(index), authorList)) {
				int idAuthor = function
						.getIdAuthor(authorOldList.get(index).getFirstName().replaceAll("^ +| +$|( )+", "$1"),
								authorOldList.get(index).getLastName().replaceAll("^ +| +$|( )+", "$1"));
				delete.deleteTie(idBook, idAuthor);
			} 
				index++;
		}

		/**
		 * parcurg noua lista de autori verific daca autorul curent exista in
		 * vechea lista daca nu exista atunci verific daca exista in baza de
		 * date daca nu exista il inserez si ii i-au id-ul daca exista ii i-au
		 * id-ul si il inserez in tabela de legatura dintre carti si autori daca
		 * exista nu ii fac nimic deoarece\ deja figureaza in tabela de
		 * lergatura
		 */

		index = 0;
		System.out.println("verify in the new list");
		while (index < authorList.size()) {
			if (notExistInList(authorList.get(index), authorOldList)) {
				int idAuthor;
				insertFunction.insertIntoAuthor(
						authorList.get(index).getFirstName()
								.replaceAll("^ +| +$|( )+", "$1"),
						authorList.get(index).getLastName()
								.replaceAll("^ +| +$|( )+", "$1"));
				idAuthor = function.getIdAuthor(
						authorList.get(index).getFirstName()
								.replaceAll("^ +| +$|( )+", "$1"),
						authorList.get(index).getLastName()
								.replaceAll("^ +| +$|( )+", "$1"));
				function.insertTiesBook(idBook, idAuthor);

			}
			index++;
		}
	}

	public boolean notExistInList(Authors author, ArrayList<Authors> authorList) {
		int index = 0;
		while (index < authorList.size()) {
			if (author.getFirstName().equals(
					authorList.get(index).getFirstName())
					&& author.getLastName().equals(
							authorList.get(index).getLastName())) {
				return false;
			}
			index++;
		}
		return true;
	}
}
