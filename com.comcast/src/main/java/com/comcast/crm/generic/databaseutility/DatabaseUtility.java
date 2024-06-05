package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Driver;

public class DatabaseUtility {
	Connection con;

	public void getConnection(String url,String username, String password) 
	{
		try {
			Driver driverref= new Driver();
			DriverManager.registerDriver(driverref);
			con = DriverManager.getConnection(url, username, password);
		}catch (Exception e) {

		}
	}
	public void getdirectConnection() 
	{
		try {
			Driver driverref= new Driver();
			DriverManager.registerDriver(driverref);
			con = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%","root");
		}catch (Exception e) {

		}
	}
	public ResultSet excuteSelectQuery(String query)
	{
		ResultSet result = null;
		try {
			result = con.createStatement().executeQuery(query);

		}catch (Exception e) {	
		}

		return result;
	}

	public int excuteNonSelectQuery(String query)
	{
		int result = 0;
		try {
			result = con.createStatement().executeUpdate(query);
		}catch (Exception e) {
		}
		return result;
	}

	public void closeConnection()
	{
		try {
			con.close();
		} catch (Exception e) {
			
		}
		

	}
}
