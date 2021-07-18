package com.higradius;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteData
 */
@WebServlet("/DeleteData")
public class DeleteData extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/data";
	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";
  
    public DeleteData() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		response.setContentType("test/HTML");
		//PrintWriter out = response.getWriter(); 
		
		try{
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			// Open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			PreparedStatement stmt=conn.prepareStatement("DELETE FROM data WHERE FIELD1= ?;"); 
			
			
			String id =request.getParameter("id");
			Integer FIELD1 = Integer.parseInt(id);
			
		
			stmt.setInt(1,FIELD1);
			if(stmt.executeUpdate()>0) {
				System.out.println("InvoiceDeleted");
			}
				else
				{
					System.out.print("Not deleted");
			}
			conn.close();
			//out.close();
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
