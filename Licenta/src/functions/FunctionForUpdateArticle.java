package functions;

import iteme.Authors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import conections.DBCon;

public class FunctionForUpdateArticle {

	DBCon dbcon = DBCon.getConnection();
	Connection con = dbcon.getCon();

	FunctionForDelete delete = new FunctionForDelete();
	FunctionForInsertTies function = new FunctionForInsertTies();
	FunctionForInsert insertFunction = new FunctionForInsert();
	FunctionForUpdateBooks verif =  new FunctionForUpdateBooks();

	public void editArticle(int idArticle, String title,
			ArrayList<Authors> authorList, ArrayList<Authors> authorOldList,
			int idJournal, int year, int volume, int number, String month,
			String note) {
		updateArticle(idArticle, title, idJournal, year, volume, number, month, note);
		updateArticleAuthorTies(idArticle, authorList, authorOldList);
	}

	public void updateArticle(int idArticle, String title, int idJournal,
			int year, int volume, int number, String month, String note) {

		try {
			PreparedStatement update = con
					.prepareStatement("update articole SET articole.title = '"
							+ title + "', articole.id_journal='" + idJournal
							+ "', articole.year='" + year + "', articole.volume='"
							+ volume + "', articole.number='" + number
							+ "', articole.month='" + month + "', articole.note='"
							+ note + "' where articole.id_article='" + idArticle + "'");
			update.executeUpdate();
			update.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateArticleAuthorTies(int idArticle, ArrayList<Authors> authorList,
			ArrayList<Authors> authorOldList) {

		/**
		 * parcurg fiecare element din vechea lista de autori, adica lista ce
		 * exista in baza de date: -verific daca elementele din aceasta lista
		 * exista in lista de autori modificata: --daca elementul nu exista
		 * atunci: din tabela de legatura a articolelor cu autorii sterg linia ce
		 * contine id-ul autorului si al articolului
		 */
		int index = 0;
		while (index < authorOldList.size()) {
			if (verif.notExistInList(authorOldList.get(index), authorList)) {
				int idAuthor = function
						.getIdAuthor(authorOldList.get(index).getFirstName().replaceAll("^ +| +$|( )+", "$1"),
								authorOldList.get(index).getLastName().replaceAll("^ +| +$|( )+", "$1"));
				delete.deleteTieArticle(idArticle, idAuthor);
			} 
				index++;
		}

		/**
		 * parcurg noua lista de autori verific daca autorul curent exista in
		 * vechea lista daca nu exista atunci verific daca exista in baza de
		 * date daca nu exista il inserez si ii i-au id-ul daca exista ii i-au
		 * id-ul si il inserez in tabela de legatura dintre articole si autori daca
		 * exista nu ii fac nimic deoarece\ deja figureaza in tabela de
		 * lergatura
		 */

		index = 0;
		while (index < authorList.size()) {
			if (verif.notExistInList(authorList.get(index), authorOldList)) {
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
				function.insertTiesArticle(idArticle, idAuthor);

			}
			index++;
		}
	}
}
