package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
/*CREATE OR REPLACE PROCEDURE P_GET_EMPDETAILS(no IN NUMBER,name OUT VARCHAR2,salary OUT NUMBER) AS
2      BEGIN
3      SELECT ENAME,SAL INTO NAME,SALARY FROM EMP WHERE EMPNO=NO;
4*    END;
SQL> /*/

public class CallableTestInDBTableEmp {

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
			query="{call P_GET_EMPDETAILS(?,?,?,?)}";
			//create callable statement object
			if(con!=null) {
				cs=con.prepareCall(query);
			//register OUT parameters with jdbc types
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.INTEGER);
			//set values to in params
			cs.setInt(1,no);
			//execute PL/SQL query
			cs.execute();
			//gather results from OUT params
			System.out.println("Emp name:"+cs.getString(2));
			System.out.println("Emp job:"+cs.getString(3));
			System.out.println("Emp salary:"+cs.getInt(4));
		}//if
	}//try
			
		
		
		catch(SQLException se) {
			System.out.println("...no data found...");
			//se.printStackTrace();
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
