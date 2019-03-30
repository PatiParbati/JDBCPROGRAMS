



package com.nt.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.Scanner;

/**App to develope Delete student record from Student table based on student no
 * version:1.0 
 * @author:Team-p
 * Date:08/03/2019
 */




public class DATEInsertTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		int no=0;
		String name=null,dob=null,doj=null;
		SimpleDateFormat sdf=null;
		java.util.Date udob=null;
		java.sql.Date sqdob=null,sqdoj=null;
		long ms=0;
		int result=0;
		
		try {
			//read inputs
			sc=new Scanner(System.in);
	
		if(sc!=null)
			{
			System.out.println("Enter the emp no");
			
		no=sc.nextInt();
			
			System.out.println("Enter the emp name");
			name=sc.next();
			
			System.out.println("Enter the Student branch");
			dob=sc.next();
			
			System.out.println("Enter DOB(dd-MM-yyyy)");
		dob=sc.next();
			
			System.out.println("Enter DOJ(yyyy-MM-dd)");
		 doj=sc.next();
			}
		   
		//convert date values as required for  the java.sql.Date class object
		//f
		//for dob
		sdf=new SimpleDateFormat("dd-MM-yyyy");
		if(sdf!=null)
			udob=sdf.parse(dob);
		if(udob!=null)
			ms=udob.getTime();
		sqdob=new java.sql.Date(ms);
		
		
		
		//for doj
		
		sqdoj=java.sql.Date.valueOf(doj);
		   
			
			
			
			
			
			
			
			
			//register jdbc driver software
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
			
			ps=con.prepareStatement("INSERT INTO EMPLOYEE VALUES(?,?,?,?)");
			//Create Statement Object
			if(ps!=null) {
			ps.setInt(1,no);
			ps.setString(2,name);
			ps.setDate(3,sqdob);
			ps.setDate(4,sqdoj);
		}
		if(ps!=null)
			result=ps.executeUpdate();
		if(result==0)
			System.out.println("record insertion failed");
		else
			System.out.println("record inserted");
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
