package com.nt.jdbc;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;


public class BLOBPhotoRetieveTest {

	public static void main(String[] args) {

		Scanner sc=null;
		int roll=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
        InputStream is=null;
		OutputStream os=null;
		byte[] buffer=null;
		int byteRead=0;
		
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Student roll");
				roll=sc.nextInt();
			}
			//Register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521","system","tiger");
			//create PreparedStatement object
			
			if(con!=null)
				ps=con.prepareStatement("select * from Student where rollno=?");
			//set param values
			if(ps!=null) {
				ps.setInt(1, roll);
				//execute sql query
				rs=ps.executeQuery();
			}
			//process the resultset
			if(rs.next()) {
				is=rs.getBinaryStream(6);
			}//if
			//create OutputStream for Dest file
			os=new FileOutputStream("F:\\project\\laxmi.jfif");
			//write buffer based logic to copy file content
			buffer=new byte[4096];
			
			
			while((byteRead=is.read(buffer))!=-1) {
				os.write(buffer,0,byteRead);
			}//while
			
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
		if(rs!=null)
			rs.close();
	}
	catch(SQLException se) {
	se.printStackTrace();
     }
	
	
	
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
	try {
		if(is!=null)
			is.close();
	}
	catch(Exception se) {
	se.printStackTrace();
     }
	try {
		if(os!=null)
			os.close();
	}
	catch(Exception se) {
	se.printStackTrace();
     }


}//finally
}//main
}//class

		