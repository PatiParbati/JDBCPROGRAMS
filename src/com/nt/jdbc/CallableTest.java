package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
/*CREATE OR REPLACE PROCEDURE P_FIRST_PRO1(x IN NUMBER,y OUT NUMBER) AS
2  BEGIN
3  y:=x*x;
4* END;
SQL> /*/

public class CallableTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		String query=null;
		CallableStatement cs=null;
		int result=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter no");
				no=sc.nextInt();
			}
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
		//prepare Sql query callin PL/SQL procedure
			query="{call P_FIRST_PRO1(?,?)}";
			//create callable statement object
			if(con!=null) {
				cs=con.prepareCall(query);
			//register OUT parameters with jdbc types
			cs.registerOutParameter(2, Types.INTEGER);
			//set values to in params
			cs.setInt(1,no);
			//execute PL/SQL query
			cs.execute();
			//gather results from OUT params
			result=cs.getInt(2);
			System.out.println("Result of Sqare value::"+result);
		}//if
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
	if(cs!=null)
		cs.close();
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
