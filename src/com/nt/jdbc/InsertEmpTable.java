



package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**App to develope Delete student record from Student table based on student no
 * version:1.0 
 * @author:Team-p
 * Date:08/03/2019
 */




public class InsertEmpTable {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		int eno=0;
		String ename=null,job=null;
		int sal=0;
		int mgr=0;
		int deptno=0;
		int comm=0;
		String hiredate=null;
		
	int result=0;
		
		try {
			//read inputs
			sc=new Scanner(System.in);
	
		if(sc!=null)
			{
			System.out.println("Enter Emp no");
			
			eno=sc.nextInt();
			
			System.out.println("Enter emp name");
			ename=sc.next();
			
			System.out.println("Enter  emp job");
			job=sc.next();
			
			System.out.println("Enter emp salary");
		sal=sc.nextInt();
		
		System.out.println("Enter emp mgr");
		mgr=sc.nextInt();
		System.out.println("Enter emp comm");
		comm=sc.nextInt();
		System.out.println("Enter emp deptno");
		deptno=sc.nextInt();
		System.out.println("Enter emp hiredate");
		hiredate=sc.next();
			}
		   
		//convert input values as required for  the SQL query   
		   ename="'"+ename+"'";
		  job="'"+job+"'";
		  hiredate="'"+hiredate+"'";
			
			
			
			
			
			
			
			//register jdbc driver software
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
			
			
			//Create Statement Object
			if(con!=null)
				st=con.createStatement();
			
			
			//prepare SQL query
			String query="INSERT INTO EMP VALUES("+eno+","+ename+","+job+","+mgr+","+hiredate+","+sal+","+comm+","+deptno+")";
			
			//send and execute SQL query in datebase software
			if(st!=null)
				result=st.executeUpdate(query);
			
			//Process the result
			if(result==0)
				System.out.println("No records inserted");
			else 
				System.out.println(result+":  records inserted successfully");
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
		if(st!=null)
			st.close();
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
