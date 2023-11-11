package com.abrar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InsertToDB {
	public static String getDate() {
		String date = "";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
		   LocalDateTime now = LocalDateTime.now();  
		   date = dtf.format(now);  
		return date;
	}
public int insert(String msg,String UID) throws ClassNotFoundException, SQLException {
	
	Connection con = new methods().connect();
	String query = "insert into msgs(message,date,UID) values(?,?,?);";
	PreparedStatement stmt = con.prepareStatement(query);
	stmt.setString(1, msg);
	stmt.setString(2, getDate());
	stmt.setString(3, UID);
	stmt.execute();
	con.close();
	return 1;

}
}
