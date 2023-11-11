package com.abrar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

public class fetch {
public ArrayList<String []> fetchData() throws ClassNotFoundException, SQLException{
	ArrayList<String[]> data = new ArrayList();
	
	Connection con = new methods().connect();
	String query = "select * from msgs;";
	PreparedStatement stmt = con.prepareStatement(query);
      ResultSet set =  stmt.executeQuery(query);
      while(set.next()) {
    	  String arr[] = new String[3];
    	  arr[0] = set.getString(1);//message 
    	  arr[1] = set.getString(2);//Date
    	  arr[2] = set.getString(3);//UID
    	 // System.out.println(arr[0]);
    	 data.add(arr);
      }
      con.close();
	return data;
}


}
