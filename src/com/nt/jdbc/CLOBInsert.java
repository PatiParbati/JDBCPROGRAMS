package com.nt.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CLOBInsert {

	public static void main(String[] args) {
		int sno=0;
		String sname=null,saddrs=null;
		String resumePath=null;
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		File file=null;
		int result=0;
		Reader reader=null;

	
try{
	sc=new Scanner(System.in);
	if(sc!=null) {
		System.out.println("Enter no");
		sno=sc.nextInt();
		System.out.println("Enter name");
		sname=sc.next();
		System.out.println("Enter address");
		saddrs=sc.next();
		
		System.out.println("Enter resumePath");
		resumePath=sc.next();
		
     	}
	//register jdbc driver
	Class.forName("oracle.jdbc.driver.OracleDriver");
	//Establish the connection
	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
	
	
	//create prepared statement object
	if(con!=null) 
		ps=con.prepareStatement("INSERT INTO STUD2 VALUES(?,?,?,?)");
		//create reader object to hold resume(clob values)
		file=new File(resumePath);
		reader=new FileReader(file);
		//set values to query params
		if(ps!=null) {
			ps.setInt(1, sno);
			ps.setString(2,sname);
			ps.setString(3, saddrs);
			ps.setCharacterStream(4, reader,(int)file.length());
		}//if
		//execute the query
		if(ps!=null) 
			result=ps.executeUpdate();
		//process the result 
		if(result==0) 
			System.out.println("Record not inserted");
		else
			System.out.println("Record inserted successfully");
		
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
if(reader!=null)
	reader.close();
}
catch(Exception e) {
e.printStackTrace();
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
	
	
	

