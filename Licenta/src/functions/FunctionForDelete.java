package functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conections.DBCon;

public class FunctionForDelete {

	DBCon dbcon = DBCon.getConnection();
	Connection con = dbcon.getCon();

	public void deleteTieBook(int idBook, int idAuthor) {
		PreparedStatement deleteTie = null;
		try {
			deleteTie = con
					.prepareStatement("DELETE FROM carte_autor WHERE carte_autor.id_carte="
							+ idBook + " and carte_autor.id_autor=" + idAuthor);
			deleteTie.executeUpdate();
			deleteTie.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (deleteTie != null) {
					deleteTie.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteTieArticle(int idArticle, int idAuthor) {
		PreparedStatement deleteTie = null;
		try {
			deleteTie = con
					.prepareStatement("DELETE FROM articol_autor WHERE articol_autor.id_article="
							+ idArticle + " and articol_autor.id_autor=" + idAuthor);
			deleteTie.executeUpdate();
			deleteTie.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (deleteTie != null) {
					deleteTie.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteBook(int idBook) {
		PreparedStatement delete = null;
		try {
			delete = con
					.prepareStatement("DELETE FROM carti WHERE carti.id_carte="
							+ idBook);
			delete.executeUpdate();
			delete.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (delete != null) {
					delete.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteArticle(int idArticle) {
		PreparedStatement delete = null;
		try {
			delete = con
					.prepareStatement("DELETE FROM articole WHERE articole.id_article="
							+ idArticle);
			delete.executeUpdate();
			delete.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (delete != null) {
					delete.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteAllBook(String lista) {
		PreparedStatement deleteAll = null;
		try {
			deleteAll = con
					.prepareStatement("DELETE from carti WHERE carti.id_carte IN ("
							+ lista + ")");
			deleteAll.executeUpdate();
			deleteAll.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (deleteAll != null) {
					deleteAll.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteAllArticle(String lista) {
		PreparedStatement deleteAll = null;
		try {
			deleteAll = con
					.prepareStatement("DELETE from articole WHERE articole.id_article IN ("
							+ lista + ")");
			deleteAll.executeUpdate();
			deleteAll.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (deleteAll != null) {
					deleteAll.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
