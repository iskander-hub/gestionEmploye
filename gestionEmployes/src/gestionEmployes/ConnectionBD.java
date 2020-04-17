package gestionEmployes;


import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;



public class ConnectionBD {
	
	com.mysql.jdbc.Connection con =null;
	
	public static Connection connectDb(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con =  DriverManager.getConnection("jdbc:mysql://localhost/dbemployes", "root","");
			return  con;
			
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

}
