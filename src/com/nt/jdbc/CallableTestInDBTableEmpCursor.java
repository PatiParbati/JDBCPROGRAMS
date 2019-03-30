package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;

import oracle.jdbc.oracore.OracleType;
/*CREATE OR REPLACE PROCEDURE FETCH_ALLEMPDETAILS(initChars in varchar,details out sys_refcursor) as
  2  begin
  3  open details for
  4  select * from emp where ename like initChars;
  5* end;
SQL> /*/

public class CallableTestInDBTableEmpCursor {

	public static void main(String[] args) {
		Scanner sc=null;
		String initChars=null;
		Connection con=null;
		String query=null;
		CallableStatement cs=null;
		ResultSet rs=null;
	boolean flag=false;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter initial chars of Emp name");
				initChars=sc.next().toUpperCase()+"%";
			}
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
		//prepare Sql query callin PL/SQL procedure
			query="{call FETCH_ALLEMPDETAILS(?,?)}";
			//create callable statement object
			if(con!=null) {
				cs=con.prepareCall(query);
			//register OUT parameters with jdbc types
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			
			//set values to in params
			cs.setString(1,initChars);
			//execute PL/SQL query
			cs.execute();
			//gather results from OUT params
			rs=(ResultSet)cs.getObject(2);
		}//if
			if(rs!=null) {
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+"     "+rs.getString(2)+"     "+rs.getString(3)+"     "+rs.getInt(4));
				}//while
				if(flag==false)
					System.out.println("No record found");
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
try {
	if(rs!=null)
		rs.close();
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
