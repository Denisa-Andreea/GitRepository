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
		PreparedStatement selectBookId = null;
		try {
			selectBookId = con
					.prepareStatement("select id_carte from carti where title = '"
							+ title + "'");
			ResultSet resultSelect = selectBookId.executeQuery();
			resultSelect.next();
			return resultSelect.getInt("id_carte");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (selectBookId != null) {
					selectBookId.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	/**
	 * selecteaza id-ul articolului inserat sau care se dorea sa se insereze dar
	 * exista deja...
	 * */

	public int getIdArticle(String title){
		PreparedStatement statement = null;
		try {
			statement =  con.prepareStatement("SELECT id_article FROM articole where title = '"+title+"'");
			ResultSet result = statement.executeQuery();
			result.next();
			return result.getInt("id_article");
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
		return 0;
	}
	
	/**
	 * selecteaza id-ul autorului inserat pentru o carte
	 * */

	public int getIdAuthor(String authorFN, String authorLN) {
		PreparedStatement selectAuthorId = null;
		try {
			selectAuthorId = con
					.prepareStatement("select id_autor from autori where firstname = '"
							+ authorFN + "' and lastname = '" + authorLN + "'");
			ResultSet resultSelect = selectAuthorId.executeQuery();
			resultSelect.next();
			return Integer.parseInt(resultSelect.getString("id_autor"));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (selectAuthorId != null) {
					selectAuthorId.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * inseram legaturile intre cate si autorii ei in tabela de legaturi
	 */

	public void insertTiesBook(int idBook, int idAuthor) {
		PreparedStatement insertTie = null;
		try {
			insertTie = con
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
		}finally {
			try {
				if (insertTie != null) {
					insertTie.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * inseram legaturile intre articol si autorii lui in tabela de legaturi
	 */
	
	public void insertTiesArticle(int idArticle, int idAuthor){
		PreparedStatement insertTie = null;
		try {
			insertTie = con
					.prepareStatement("insert into articol_autor(id_article,id_autor) values(?,?)");
			insertTie.setInt(1, idArticle);
			insertTie.setInt(2, idAuthor);
			/**
			 * verific daca legatura ai exista si daca nu exista atunci o adaug
			 */
			if (!existTies(idArticle, idAuthor, "articol_autor", "id_article")) {
				insertTie.executeUpdate();
				insertTie.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (insertTie != null) {
					insertTie.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * verifica daca legatura exista
	 */

	public boolean existTies(int idObject, int idAuthor, String table,
			String object) {
		PreparedStatement ties = null;
		try {
			ties = con
					.prepareStatement("select id_autor,"+object+" from "+table+" where "+object+" = '"
							+ idObject + "' and id_autor ='" + idAuthor + "'");
			ResultSet result = ties.executeQuery();
			if (!result.next()) {
				ties.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (ties != null) {
					ties.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
