package functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conections.DBCon;
import forFields.Codification;

public class FunctionChangePassword {

	DBCon dbcon = DBCon.getConnection();
	Connection con = dbcon.getCon();
	Codification codif =  new Codification();

	public void changePassword(String oldPassword, String newPassword) {
		PreparedStatement statement;
		try {
			statement = con.prepareStatement("update login SET login.password = '"+codif.md5(newPassword)+"' where login.password='"+codif.md5(oldPassword)+"'");
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
