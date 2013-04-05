package functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conections.DBCon;

public class FunctionForInsertTies {

	DBCon dbcon = DBCon.getConnection();
	Connection con = dbcon.getCon();

	/**
	 * selecteaza id-ul cartii inserate sau care se dorea sa se insereze dar
	 * exista deja...
	 * */

	public int getIdBook(String title) {
		try {
			PreparedStatement selectBookId = con
					.prepareStatement("select id_carte from carti where title = '"
							+ title + "'");
			ResultSet resultSelect = selectBookId.executeQuery();
			resultSelect.next();
			return Integer.parseInt(resultSelect.getString("id_carte"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * selecteaza id-ul autorului inserat pentru o carte
	 * */

	public int getIdAuthor(String authorFN, String authorLN) {
		try {
			PreparedStatement selectAuthorId = con
					.prepareStatement("select id_autor from autori where firstname = '"
							+ authorFN + "' and lastname = '" + authorLN + "'");
			ResultSet resultSelect = selectAuthorId.executeQuery();
			resultSelect.next();
			return Integer.parseInt(resultSelect.getString("id_autor"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * inseram legaturile intre cate si autorii ei in tabela de legaturi
	 */

	public void insertTiesBook(int idBook, int idAuthor) {
		try {
			PreparedStatement insertTie = con
					.prepareStatement("insert into carte_autor(id_carte,id_autor) values(?,?)");
			insertTie.setInt(1, idBook);
			insertTie.setInt(2, idAuthor);
			/**
			 * verific daca legatura ai exista si daca nu exista atunci o adaug
			 */
			if (!existTies(idBook, idAuthor, "carte_autor", "id_carte")) {
				insertTie.executeUpdate();
				insertTie.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * verifica daca legatura exista
	 */

	public boolean existTies(int idObject, int idAuthor, String table,
			String object) {
		try {
			PreparedStatement ties = con
					.prepareStatement("select id_autor,id_carte from "+table+" where "+object+" = '"
							+ idObject + "' and id_autor ='" + idAuthor + "'");
			ResultSet result = ties.executeQuery();
			if (!result.next()) {
				ties.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
