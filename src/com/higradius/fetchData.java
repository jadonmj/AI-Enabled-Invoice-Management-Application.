package com.higradius;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class fetchData
 */
@WebServlet("/fetchData")
public class fetchData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/data";
	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";
	
    public fetchData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			Connection conn = null;
			Statement stmt = null;
			try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			//STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM data WHERE FIELD1<=10";
			ResultSet rs = stmt.executeQuery(sql);
			List< Collection > listPojo = new ArrayList<>();
			//STEP 5: Extract data from result set
			while(rs.next()) {
				Collection payObj= new Collection();
				payObj.setFIELD1(rs.getInt("FIELD1"));
				payObj.setInvoice_id(rs.getString("invoice_id"));
				payObj.setCust_number(rs.getString("cust_number"));
				payObj.setCustomer_name(rs.getString("customer_name"));
				payObj.setPredicted_date(rs.getString("predicted_date"));
				payObj.setDue_in_date(rs.getString("due_in_date"));
				payObj.setNotes(rs.getString("Notes"));
				payObj.setTotal_open_amount(rs.getString("total_open_amount"));
				listPojo.add(payObj);
				}
			for (Collection obj : listPojo) {
			
			System.out.print("Invoice_id: "+obj.getInvoice_id()+", ");
			}
			String output = getJsonString(listPojo);
			response.setContentType("application/json");
			try {
				response.getWriter().write(output);
			}catch (Exception e){
				e.printStackTrace();
			}
			rs.close();
			stmt.close();
			conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
			

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String getJsonString(List<Collection> genrate) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(genrate);
		System.out.println(json);
		return json;
	}
}
