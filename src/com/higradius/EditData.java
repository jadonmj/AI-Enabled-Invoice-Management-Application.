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
 * Servlet implementation class AddData
 */
@WebServlet("/EditData")
public class EditData extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/data";
	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";
  
    public EditData() {
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
			
			PreparedStatement stmt=conn.prepareStatement("UPDATE data SET total_open_amount = ?, Notes = ? WHERE FIELD1= ?;"); 
			
			
			String total_open_amount = request.getParameter("invoiceAmt");
			String Notes = request.getParameter("add_notes");
			String id =request.getParameter("id");
			Integer FIELD1 = Integer.parseInt(id);
			
			Collection edobj = new Collection();
			edobj.setTotal_open_amount(total_open_amount);
			edobj.setNotes(Notes);
			
			stmt.setString(1, total_open_amount);
			stmt.setString(2, Notes);
			stmt.setInt(3,FIELD1);
			if(stmt.executeUpdate()>0) {
				System.out.println("Invoice Updated");
			}
				else
				{
					System.out.print("Not updated");
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
