package com.nt.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CLOBRetrieve {

	public static void main(String[] args) {
		int sno=0;
		
	
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
	char[] buffer=null;
		ResultSet rs=null;
		Reader reader=null;
		Writer writer=null;
		int charsRead=0;

	
try{
	sc=new Scanner(System.in);
	if(sc!=null) {
		System.out.println("Enter no");
		sno=sc.nextInt();
		
		
     	}
	//register jdbc driver
	Class.forName("oracle.jdbc.driver.OracleDriver");
	//Establish the connection
	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
	
	
	//create prepared statement object
	if(con!=null) 
		ps=con.prepareStatement("SELECT * FROM STUD2 WHERE SNO=?");
		
		
		
		//set values to query params
		if(ps!=null) {
			ps.setInt(1, sno);
			
		
		//execute the query
	  rs=ps.executeQuery();
		}//if
		//process the resultset 
		if(rs.next()) {
			reader=rs.getCharacterStream(4);		
         }//if
	//create outputstream for dest file
writer=new FileWriter("E:\\d_resume.txt");
//witer buffer based logic to copy file content
buffer=new char[2018];
while((charsRead=reader.read(buffer))!=-1) {
	writer.write(buffer,0,charsRead);
}//while
System.out.println("CLOB Retrived");
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
	
	
	

