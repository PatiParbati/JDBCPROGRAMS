package com.nt.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Scanner;

/**App to develope Delete student record from Student table based on student no
 * version:1.0 
 * @author:Team-p
 * Date:08/03/2019
 */




public class BLOBPhotonsertTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		
		PreparedStatement ps=null;
		String query=null;
		int roll=0;
		String name=null,branch=null,city=null,photo=null;
		int total=0;
	    int result=0;
	    File file=null;
	    InputStream is=null;
		
try {
			//read inputs
			sc=new Scanner(System.in);
	
	
		
		
		if(sc!=null) {
			
			System.out.println("Enter the Student rollno");
			
			roll=sc.nextInt();
			
			System.out.println("Enter the Student name");
			name=sc.next();
			
			System.out.println("Enter the Student branch");
			branch=sc.next();
			
			System.out.println("Enter the Student totalMark");
		total=sc.nextInt();
			
			System.out.println("Enter the Student city");
		 city=sc.next();
		 System.out.println("Enter the Studentphoto");
		 photo=sc.next();
			}//if
			file=new File(photo);
			is=new FileInputStream(file);			
		 Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
			
			
			query="INSERT INTO STUDENT VALUES(?,?,?,?,?,?)";
			if(con!=null)
				ps=con.prepareStatement(query);
			
			 
		//set the input values read from user to query params
			if(ps!=null) {
		ps.setInt(1,roll);
		ps.setString(2,name);
		ps.setString(3,branch);
		ps.setInt(4,total);
		ps.setString(5,city);
		ps.setBinaryStream(6,is,file.length());
		}
			
		//execute the query
		if(ps!=null)
		result=ps.executeUpdate();
		if(result==0)
			System.out.println("student details are not inserted");
		else
			
			System.out.println("student details are  inserted");
			
			
		
	
}//try
			
			
			
			
		catch(SQLException se) {
				se.printStackTrace();
			}
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
				
			}
		catch(Exception e) {
		e.printStackTrace();
		
		}
		
finally {
	//close jdbc objects
	try {
		if(ps!=null)
			ps.close();
	}
	catch(SQLException se) {
	se.printStackTrace();
     }
	
	try{
		if(con!=null)
			con.close();
	}
	catch(SQLException se) 
	{
	se.printStackTrace();
    }	



	try{
		if(sc!=null)
			sc.close();
	}
	catch(Exception e) {
	e.printStackTrace();
    }	


}//finally
}//main
}//class
