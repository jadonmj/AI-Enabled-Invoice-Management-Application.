package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet("/AddData")
public class AddData extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/data";
	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddData() {
        super(); //reference variable which is used to refer immediate parent class object
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		response.setContentType("test/HTML");
		PrintWriter out = response.getWriter(); 
		
		try{
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			// Open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			// parameterized statement used to execute the same or similar database statements repeatedly with high efficiency
			PreparedStatement stmt=conn.prepareStatement(
					"INSERT INTO data(customer_name, cust_number, invoice_id, total_open_amount, due_in_date, Notes)values (?,?,?,?,?,?)"); 
			
			String customer_name = request.getParameter("customer_name");
			String cust_number = request.getParameter("customerNo");
			String invoice_id = request.getParameter("invoiceNo");
			String total_open_amount = request.getParameter("invoiceAmt");
			String due_in_date = request.getParameter("dueDate");
			String Notes = request.getParameter("add_notes");
			
			Collection adobj = new Collection();
			adobj.setCustomer_name(customer_name);
			adobj.setCust_number(cust_number);
			adobj.setInvoice_id(invoice_id);
			adobj.setTotal_open_amount(total_open_amount);
			adobj.setDue_in_date(due_in_date);
			adobj.setNotes(Notes);
			
			stmt.setString(1, customer_name);
			stmt.setString(2, cust_number);
			stmt.setString(3, invoice_id);
			stmt.setString(4, total_open_amount);
			stmt.setString(5, due_in_date);
			stmt.setString(6, Notes);
			if(stmt.executeUpdate()>0) {
				System.out.println("Invoice added");
			}
				else
				{
					System.out.print("Not added");
			}
			conn.close();
			out.close();
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}catch(Exception e) {
		e.printStackTrace();
	}
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
