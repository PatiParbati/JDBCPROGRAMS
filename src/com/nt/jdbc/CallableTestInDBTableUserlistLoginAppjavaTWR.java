package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import oracle.jdbc.OracleTypes;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;

import oracle.jdbc.oracore.OracleType;
/*1  CREATE OR REPLACE PROCEDURE AUTH_PRO(user in varchar,passs in varchar,result out varchar) as
2  cnt number;
3    begin
4    select count(*) into cnt from userlist where name=user and pass=passs;
5  if(cnt<>0) then
6  result:='valid credentials';
7  else
8  result:='invalid credentials';
9  end if;
10* end;
SQL> /*/
public class CallableTestInDBTableUserlistLoginAppjavaTWR {

	public static void main(String[] args) {
		Scanner sc=null;
		String user=null,passs=null;
		Connection con=null;
		CallableStatement cs=null;
		String result=null;
	
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter User name");
				user=sc.next();
				System.out.println("Enter Password");
				passs=sc.next();
			}
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
		//prepare Sql query callin PL/SQL procedure
			if(con!=null) 
			cs=con.prepareCall("{call AUTH_PRO(?,?,?)}");
			//create callable statement object
			if(cs!=null) {
			
			//register OUT parameters with jdbc types
			cs.registerOutParameter(3, Types.VARCHAR);	
			//set values to in params
			cs.setString(1,user);
			cs.setString(2,passs);
			//execute PL/SQL query
			cs.execute();
			//gather results from OUT params
			result=cs.getString(3);
			System.out.println(result);
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
