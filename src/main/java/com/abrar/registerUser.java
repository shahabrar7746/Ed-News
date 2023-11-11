package com.abrar;

import java.sql.*;

public class registerUser {
	public String  register(String FName,String LName,String email,String number) throws ClassNotFoundException, SQLException {
		String registered = "";
		methods mthd = new methods();
		Connection con = mthd.connect();
		
		if(mthd.isUser(email, number)) {
			String query = "insert into credentials(first_name,last_name,phone_number,email_address,UID) values(?,?,?,?,?);";
			PreparedStatement stmt = con.prepareStatement(query);
			String UID = mthd.generateUID(FName);
			stmt.setString(1, FName);
			stmt.setString(2, LName);
			stmt.setString(3, number);
			stmt.setString(4,email);
			stmt.setString(5, UID);
			stmt.execute();
			con.close();
			
			registered = UID;
			
		}else {
			registered = "null";
		}
		con.close();
		return registered;
	}

}
