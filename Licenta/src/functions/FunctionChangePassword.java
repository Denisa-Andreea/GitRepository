package functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conections.DBCon;

public class FunctionChangePassword {

	DBCon dbcon = DBCon.getConnection();
	Connection con = dbcon.getCon();

	public void changePassword(String oldPassword, String newPassword) {
		PreparedStatement statement;
		try {
			statement = con.prepareStatement("update login SET login.password = '"+newPassword+"' where login.password='"+oldPassword+"'");
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
