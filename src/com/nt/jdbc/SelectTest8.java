package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*App to get Highest salary Employee details
 * version:2.0
 * author:Team-p
 * Date:2019/05/03
 */
public class SelectTest8 {
public static void main(String args[]) {
	
	Connection con=null;
     Statement st=null;
	ResultSet rs=null;
	Scanner sc=new Scanner(System.in);
    System.out.println("Enter the nth salary");
	int n=sc.nextInt();
	
	boolean flag=false;
	try{

	//Register JDBC driver software
	Class.forName("oracle.jdbc.driver.OracleDriver");
		//Establish connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");

		if(con!=null)
			st=con.createStatement();
		//prepare the query
String query="SELECT * FROM EMP E1 WHERE "+n+" =(SELECT COUNT(SAL) FROM EMP E2 WHERE E2.SAL>E1.SAL)";
System.out.println();
System.out.println(query);

if(st!=null)
	rs=st.executeQuery(query);
//process the ResultSet
if(rs!=null){
	while(rs.next()){
		flag=true;
		System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5)+" "+rs.getInt(6)+" "+rs.getInt(7)+" "+rs.getInt(8));
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

try{
	if(sc!=null)
	sc.close();
}
catch(Exception e)
{
	e.printStackTrace();
}



}//finally
}//main
}//class

	


