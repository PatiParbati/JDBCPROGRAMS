



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




public class DeleteTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		int no=0;
		int result=0;
		
		try {
			//read inputs
			sc=new Scanner(System.in);
			System.out.println("Enter the Student no to delete");
			no=sc.nextInt();
			
			
			//register jdbc driver software
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
			
			
			//Create Statement Object
			if(con!=null)
				st=con.createStatement();
			
			//send and execute SQL query in datebase software
			if(st!=null)
				result=st.executeUpdate("DELETE FROM STUDENT WHERE ROLLNO="+no);
			
			//Process the result
			if(result==0)
				System.out.println("No records found for deletion");
			else 
				System.out.println(result+"No of records found for deletion");
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
