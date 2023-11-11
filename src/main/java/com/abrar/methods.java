package com.abrar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class methods {
public static Connection connect() throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.cj.jdbc.Driver"); 
	String url = "jdbc:mysql://bs9tvwoauhriygbkmj9l-mysql.services.clever-cloud.com:3306/bs9tvwoauhriygbkmj9l";
	String user = "uodpbw0dt1myvxd4";
	String pswd = "rQTqsZVZYbwwknBkz4Jg";
	Connection con = DriverManager.getConnection(url,user,pswd);
	return con;
}
public String generateUID(String Fname) {
	Random r = new Random();
	String alpha = "QM1234567890";
	String UID = "";
	for(int i =0;i<Fname.length();i++) {
		String temp = "";
		temp = temp.valueOf(alpha.charAt(r.nextInt(Fname.length())));
		UID = UID + temp;
	}
	
	return UID;
}
public String getUID(String number) throws ClassNotFoundException, SQLException {
	String UID = "";
	Connection con = new methods().connect();
	String query = "SELECT * FROM credentials where phone_number = ?;";
	PreparedStatement stmt = con.prepareStatement(query);
	stmt.setString(1, number);
	ResultSet rs = stmt.executeQuery();
	 while(rs.next()) {
		  UID = rs.getString("UID");
	 }
		con.close();

	return UID;
}
public boolean isUser(String email,String number) throws ClassNotFoundException, SQLException {
	boolean isUser = true;
	Connection con = new methods().connect();
	String query = "SELECT * FROM credentials where email_address = ? or phone_number = ?;";
	PreparedStatement stmt = con.prepareStatement(query);
	stmt.setString(1, email);
	stmt.setString(2, number);
 ResultSet rs = stmt.executeQuery();
 String UID = null;
 while(rs.next()) {
	  UID = rs.getString("UID");
 }
 if(UID == null) {
	 isUser = true;
 }else {
	 isUser = false;
 }
 con.close();
 
	return isUser;
	
}
public String currentDate() {
	String date = "";
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
	   LocalDateTime now = LocalDateTime.now();  
	   date = dtf.format(now); 
	return date;
}
String ID = "";
public String getID() {
	return ID;
}
public void setID(String iD) {
	ID = iD;
}

public boolean isValid(String UID) throws ClassNotFoundException, SQLException {
	boolean flag = true;
	methods mthd = new methods();
	Connection con = mthd.connect();
	String query = "select first_name,last_name from credentials where UID = ?";
	PreparedStatement stmt = con.prepareStatement(query);
	stmt.setString(1, UID);
	String fname = "";
	String lname = "";
	ResultSet set = stmt.executeQuery();
	while(set.next()) {
		 fname = set.getString(1);
		lname = set.getString(2);
	}
	if(fname.equals("") && lname.equals("")) {
		flag = false;
	}
	con.close();

	return flag;
}
 String validity = "";
public  String getValidity() {
	return validity;
}
public void setValidity(String validity) {
	this.validity = validity;
}
public String getName(String UID) throws ClassNotFoundException, SQLException {
	String name = "";
	Connection connect = new methods().connect();
	String query = "select first_name,last_name from credentials where UID = ?";
	PreparedStatement stmt = connect.prepareStatement(query);
	stmt.setString(1, UID);
	ResultSet set = stmt.executeQuery();
	while(set.next()) {
		String fname = set.getString(1);
		String lname = set.getString(2);
		name = fname + " " + lname;
	}
	connect.close();
	return name;
}
}
