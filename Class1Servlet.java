

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Class1Servlet
 */
@WebServlet("/Class1Servlet")
public class Class1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Class1Servlet() {
    }

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("bid"));
		String name = request.getParameter("bname");
		String authr = request.getParameter("author");
		String brch = request.getParameter("branch");
		int qty = Integer.parseInt(request.getParameter("quantity"));
		String btype=request.getParameter("type");
		try {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", 
		"root", "password");
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO books (bid, bname, author ,branch ,quantity ,type)VALUES (?, ?, ?, ?, ?, ?)");
		stmt.setInt (1, id);
		stmt.setString(2,name);
		stmt.setString(3, authr);
		stmt.setString (4, brch);
		stmt.setInt(5,qty);
		stmt.setString(6,btype);
		stmt.executeUpdate();
		conn.close();
		response.sendRedirect("index.html");
		} catch (SQLException e) {
		e.printStackTrace();
		response.getWriter().println("Error: " + e.getMessage());
		}
		}
	}
