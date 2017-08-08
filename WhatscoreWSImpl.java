/*
* @Author: Ade Akingbade	
* @Date: 1st April 2017
* @IDE: Eclipse Java EE IDE Neon.3
* @DB: MySQL workbench 6.3
* This is only a demonstration and only serves another project of mine.
* 
*/

package whatscoreWebService;

import java.util.*;
import java.sql.*;

public class WhatscoreWSImpl {
	
	private String mysqlDriver = "com.mysql.jdbc.Driver";
	private String mysqlUrl = "jdbc:mysql://localhost:3306/whatscoredb";
	private String dbUser = "laptop";
	private String dbPass = "password";
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private List<String> userD = new ArrayList<>();
	
	public WhatscoreWSImpl(){
		
	}
	
	//Connects to the DB and creates an sql insert query based on user details.
	public boolean createUserDB(String un, String pw, String fn, String ln, String ft){
		user newUser = new user (un, pw, fn, ln, ft);
		try{
			Class.forName(mysqlDriver);
			conn = DriverManager.getConnection(mysqlUrl,dbUser,dbPass);
			stmt = conn.createStatement();
			stmt.executeUpdate("insert into users (userN, pass, firstN, lastN, favTeam) "
					+ "values ('"+newUser.getUN()+"','"+newUser.getPW()+"','"+newUser.getFN()+"','"+newUser.getLN()+"','"+newUser.getFT()+"')");
			stmt.executeUpdate("insert into whatscoredb.state (cstatue, userN)"
					+ "values (false, '"+newUser.getUN()+"');");
			return true;
		}catch(SQLException ex){
			ex.printStackTrace();
		}catch(ClassNotFoundException  ex){
			ex.printStackTrace();
		}catch(NullPointerException ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	//Connects to the DB and creates an sql query to check if user exists and if so sets them online in DB.
	public boolean checkLoginDetails(String un, String pw){
		String checkSQL = "select * from users where userN='"+un+"' and pass='"+pw+"';";
		String setOnlineSQL = "update state set cstatue = 1 where userN = '"+un+"'";
		try{
			Class.forName(mysqlDriver);
			conn = DriverManager.getConnection(mysqlUrl,dbUser,dbPass);
			stmt = conn.createStatement();
			stmt.executeQuery(checkSQL);
			rs = stmt.getResultSet();
			if(rs.first()){
				stmt.executeUpdate(setOnlineSQL);
				return true;
			}
		}catch(SQLException ex){
			ex.printStackTrace();
			
		}catch(ClassNotFoundException  ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	//Connects to the DB and creates an sql select query for user details and returns them in an array list.	
	public List<String> userDetails (String un){
		String userInfoSQL = "select userN, firstN, lastN, favTeam from users where userN = '"+un+"'";
		userD.clear();
		try{
			Class.forName(mysqlDriver);
			conn = DriverManager.getConnection(mysqlUrl,dbUser,dbPass);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(userInfoSQL);
			rs.first();
			userD.add(rs.getString("userN"));
			userD.add(rs.getString("firstN"));
			userD.add(rs.getString("lastN"));
			userD.add(rs.getString("favTeam"));
		}catch(SQLException ex){
			ex.printStackTrace();
		}catch(ClassNotFoundException  ex){
			ex.printStackTrace();
		}
		return userD;
	}
	
	//Connects to the DB and creates an sql update query to update online status of user 
	public boolean logoutUser(String un){
		String setOfflineSQL = "update state set cstatue = 0 where userN = '"+un+"'";
		try{
			Class.forName(mysqlDriver);
			conn = DriverManager.getConnection(mysqlUrl,dbUser,dbPass);
			stmt = conn.createStatement();
			stmt.executeUpdate(setOfflineSQL);
			return true;
		}catch(SQLException ex){
			ex.printStackTrace();
			
		}catch(ClassNotFoundException  ex){
			ex.printStackTrace();
		}
		return false;
	}
	
}
