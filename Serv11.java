

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/Serv11")
public class Serv11 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Serv11() {
            }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","root","12891289");
			String name=request.getParameter("uname");
			String pwd=request.getParameter("passwrd");
			PreparedStatement pr=con.prepareStatement("select username from users where username=? and password=?");
			pr.setString(1,name);
			pr.setString(2,pwd);
			System.out.println(name);
			System.out.println(pwd);

			ResultSet rs=pr.executeQuery();
			if(rs.next())
			{
				RequestDispatcher rd=request.getRequestDispatcher("index.html");
				rd.forward(request, response);
			}
			else
			{
				out.println("Login failed");
			}
			}catch (Exception e)
			{System.out.println("Exception");
			e.printStackTrace();
			}
			}
	}
