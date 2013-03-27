package functions;

import java.sql.Connection;

import conections.DBCon;

public class FunctionForUpdates {

	DBCon dbcon = DBCon.getConnection();
	Connection con = dbcon.getCon();
	
}
