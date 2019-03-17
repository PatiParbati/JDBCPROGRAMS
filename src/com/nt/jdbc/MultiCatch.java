package com.nt.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MultiCatch {




	

	/**App to get Student Details based on rollno
	*Version:10
	*Author:Team-P
	*Date:03/05/2019
	*/

	
		public static void main(String[] args) 
		{
		Scanner sc=null;
		Connection con=null;
	    Statement st=null;
		ResultSet rs=null;
		String query=null;
		boolean flag=false;
		int count=0;
		try{
			//read inputs
			sc=new Scanner(System.in);
			
			if(sc!=null){
			System.out.println("Enter SQL query");
			query=sc.nextLine();
			
			}
			//Register JDBC driver software
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");

			if(con!=null)
				st=con.createStatement();
			

		flag=st.execute(query);
	//process the ResultSet
		if(flag==true) {
			System.out.println("select query is sxecuted");
			
		rs=st.getResultSet();
       while(rs.next()){
			
			System.out.println(rs.getInt(1)+"     "+rs.getString(2)+"     "+rs.getString(3)+"     "+rs.getInt(4)+"  "+rs.getString(5));
		}//while
		
	}//if
	else {
		System.out.println("Non select query is sxecuted");
		count=st.getUpdateCount();
		System.out.println("no of records effected"+count);
	}//else
		}//try
		
		catch(SQLException|ClassNotFoundException se){//known exception
			se.printStackTrace();
			
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
