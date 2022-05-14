package com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

	
	
public class User {

	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
		 Class.forName("com.mysql.jdbc.Driver");
		 con= DriverManager.getConnection("jdbc:mysql://localhost:3309/electricitymanagmentsystem", 
				 "root", "");
		 
		 
	 //For testing
	 System.out.print("Successfully connected");
	 }
	 
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }

	 return con;
	}
	
	/*public String readUser()
	{ 
			String output = "";
			
			try
			{ 
					Connection con = connect();
					
					if (con == null) 
					{ 
						return "Error while connecting to the database for reading."; 
					} 
				 
		 // Prepare the html table to be displayed
				 output = "<table border='1'><tr><th>Name</th>" 
							 +"<th>Address</th><th>TelephoneNo</th>"
							 + "<th>PremisesId</th>" 
							 + "<th>Update</th><th>Remove</th></tr>"; 
				 
				 String query = "select * from user"; 
				 Statement stmt = con.createStatement(); 
				 ResultSet rs = stmt.executeQuery(query); 
				 
		 // iterate through the rows in the result set
				 while (rs.next()) 
				 { 
					 String UserId = Integer.toString(rs.getInt("UserId")); 
					 String Name = rs.getString("Name"); 
					 String Address = rs.getString("Address"); 
					 String TelephoneNo = Integer.toString(rs.getInt("TelephoneNo")); 
					 String PremisesId = Integer.toString(rs.getInt("PremisesId")); 
					 
		 // Add a row into the html table
					 output += "<tr><td><input id ='hidItemIDUpdate' name ='hidItemIDUpdate' type='hidden' value='" + UserId + " '>"	+ Name + "</td>";
					
					 output += "<td>" + Address + "</td>"; 
					 output += "<td>" + TelephoneNo + "</td>"; 
					 output += "<td>" + PremisesId + "</td>";
		 // buttons
					 output += "<td><input name='btnUpdate' id ='" + UserId + " ' type='button' value='Update' class=' btnUpdate btn btn-secondary'></td><td>"
					 		+ "<input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-UserId='"+ UserId + " '>" + "</td></tr>";  
				 } 
				 con.close(); 
				 
		 // Complete the html table
				 output += "</table>"; 
				 
				 
				 
		 } 
			catch (Exception e) 
			{ 
				output = "Error while reading the items."; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
	}*/
	
	public String readUser()
	{ 
			String output = "";
			
			try
			{ 
					Connection con = connect();
					
					if (con == null) 
					{ 
						return "Error while connecting to the database for reading."; 
					} 
				 
		 // Prepare the html table to be displayed
				 output = "<table border='1'><tr><th>Name</th>" 
							 +"<th>Address</th><th>TelephoneNo</th>"
							 + "<th>PremisesId</th>" 
							 + "<th>Update</th><th>Remove</th></tr>"; 
				 
				 String query = "select * from user"; 
				 Statement stmt = con.createStatement(); 
				 ResultSet rs = stmt.executeQuery(query); 
				 
		 // iterate through the rows in the result set
				 while (rs.next()) 
				 { 
					 String UserId = Integer.toString(rs.getInt("UserId")); 
					 String Name = rs.getString("Name"); 
					 String Address = rs.getString("Address"); 
					 String TelephoneNo = Integer.toString(rs.getInt("TelephoneNo")); 
					 String PremisesId = Integer.toString(rs.getInt("PremisesId")); 
					 
		 // Add a row into the html table
					 output += "<tr><td><input id ='hidItemIDUpdate' name ='hidItemIDUpdate' type='hidden' value='" + UserId + " '>"	+ Name + "</td>";
					
					 output += "<td>" + Address + "</td>"; 
					 output += "<td>" + TelephoneNo + "</td>"; 
					 output += "<td>" + PremisesId + "</td>";
		 // buttons
					 output += "<td><input name='btnUpdate' id ='" + UserId + " ' type='button' value='Update' class=' btnUpdate btn btn-secondary'></td><td>"
					 		+ "<input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-UserId='"+ UserId + " '>" + "</td></tr>";  
				 } 
				 con.close(); 
				 
		 // Complete the html table
				 output += "</table>"; 
				 
				 
				 
		 } 
			catch (Exception e) 
			{ 
				output = "Error while reading the items."; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
	}

	public String insertUser(String Name, String Address,String TelephoneNo, String PremisesId)
    {
			 String output = "";
			 
			 try
			 {
				 Connection con = connect();
				 
				 if (con == null)
				 {
					 return "Error while connecting to the database for inserting.";
				 }
				 
				 // create a prepared statement
				 String query = " insert into user(`UserId`,`Name`,`Address`,`TelephoneNo`,`PremisesId`)"+ " values (?, ?, ?, ?, ?)";
			 
			 
				 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
				 // binding values
				 preparedStmt.setInt(1, 0);
				 preparedStmt.setString(2, Name);
				 preparedStmt.setString(3, Address);
				 preparedStmt.setString(4,TelephoneNo);
				 preparedStmt.setString(5, PremisesId);
			 
			 
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 
				 String newUser = readUser();
				 output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
			 }
			 catch (Exception e)
			 {
				 output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
				 System.err.println(e.getMessage());
				 
			 }
			 return output;
			 
			 
    
     
		
    }
	public String updateUser(int UserId,String Name,String Address,String TelephoneNo,String PremisesId)
	{ 
			String output = ""; 
			try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 { 
					 return "Error while connecting to the database for updating."; 
				 } 
			 // create a prepared statement
				 String query = "update user set  Name = ?,  Address = ?, TelephoneNo = ?, PremisesId = ? where UserId = ?"; 
				 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setString(1, Name);
				 preparedStmt.setString(2, Address);
				 preparedStmt.setString(3, TelephoneNo);
				 preparedStmt.setString(4, PremisesId);
				 preparedStmt.setInt(5, UserId);


				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 String newUser = readUser();
				 output = "{\"status\":\"success\", \"data\": \"" +newUser + "\"}";
				
			 } 
			catch (Exception e) 
			 { 
				output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
				 System.err.println(e.getMessage()); 
			 } 
			return output; 
	}

	public String deleteUser(String userIDData)
	{ 
			String output = ""; 
			try
		    { 
				 Connection con = connect(); 
				 if (con == null) 
				 { 
					 return "Error while connecting to the database for deleting."; 
				 } 
			 // create a prepared statement
				 String query = "delete from user where UserId=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(userIDData)); 

				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				
				 String newUser = readUser();
				 output = "{\"status\":\"success\", \"data\": \"" +
		 newUser + "\"}";
				 
			} 
			catch (Exception e) 
			{ 
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
				 System.err.println(e.getMessage()); 
		    } 
			return output; 
		}


	
}



