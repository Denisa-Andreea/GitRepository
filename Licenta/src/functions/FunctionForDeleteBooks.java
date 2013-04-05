package functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conections.DBCon;

public class FunctionForDeleteBooks {

	DBCon dbcon = DBCon.getConnection();
	Connection con = dbcon.getCon();

	public void deleteTie(int idBook, int idAuthor) {
		try {
			PreparedStatement deleteTie = con
					.prepareStatement("DELETE FROM carte_autor WHERE carte_autor.id_carte="
							+ idBook + " and carte_autor.id_autor=" + idAuthor);
			deleteTie.executeUpdate();
			deleteTie.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteBook(int idBook) {
		try {
			PreparedStatement deleteBook = con
					.prepareStatement("DELETE FROM carti WHERE carti.id_carte="
							+ idBook);
			deleteBook.executeUpdate();
			deleteBook.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAll(String lista) {
		try {
			PreparedStatement deleteAll = con
					.prepareStatement("DELETE from carti WHERE carti.id_carte IN ("
							+ lista + ")");
			deleteAll.executeUpdate();
			deleteAll.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
