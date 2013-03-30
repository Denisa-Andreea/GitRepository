package functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conections.DBCon;

public class FunctionForDeleteBooks {

	DBCon dbcon = DBCon.getConnection();
	Connection con = dbcon.getCon();
	
	public void deleteTie(int idBook, int idAuthor){
		try {
			PreparedStatement deleteTie = con.prepareStatement("DELETE FROM carte_autor WHERE carte_autor.id_carte="+idBook+" and carte_autor.id_autor="+idAuthor);
			deleteTie.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
