package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/*App to get Highest salary Employee details
 * version:2.0
 * author:Team-p
 * Date:2019/05/03
 */
public class SelectTest7 {
public static void main(String args[]) {
	
	Connection con=null;
     Statement st=null;
	ResultSet rs=null;
	
	boolean flag=false;
	try{

	//Register JDBC driver software
	Class.forName("oracle.jdbc.driver.OracleDriver");
		//Establish connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");

		if(con!=null)
			st=con.createStatement();


if(st!=null)
	rs=st.executeQuery("SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)");
//process the ResultSet
if(rs!=null){
	while(rs.next()){
		flag=true;
		System.out.println(rs.getInt(1)+"     "+rs.getString(2)+"     "+rs.getString(3)+"     "+rs.getInt(4));
	}//while
	if(flag==false)
		System.out.println("no record found");
}//if
	}//try
	catch(SQLException se){//known exception
		se.printStackTrace();
	}
	catch(ClassNotFoundException cnf){//known exception
		cnf.printStackTrace();
	}

	catch(Exception e){ //unknown exception
		e.printStackTrace();
	}
finally{
	//close jdbc objects
	try{
		if(rs!=null)
			rs.close();
	}
catch(SQLException se)
	{
		se.printStackTrace();
	}


try{
		if(st!=null)
			st.close();
	}
catch(SQLException se)
	{
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




}//finally
}//main
}//class

	


