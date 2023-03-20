

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Navserv1")
public class Navserv1 extends HttpServlet {
private static final long serialVersionUID = 1L;
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String bookId = request.getParameter("bid");
PrintWriter out = response.getWriter();
try {
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "pwd");
@SuppressWarnings("rawtypes")
Statement stmt = (Statement)con.createStatement();
ResultSet rs = null;
if (bookId != null && !bookId.isEmpty()) {
rs = ((java.sql.Statement) stmt).executeQuery("select * from books where bid = " + bookId);
}
if (rs != null && rs.next()) {
out.println("<html>");
out.println("<body>");
out.println("Title: " + rs.getString("bname") );
out.println("Author: " + rs.getString("author"));
out.println("</body>");
out.println("</html>");
response.sendRedirect("availability.html");
} else {
out.println("<html>");
out.println("<body>");
out.println("<h1>Book not found</h1>");
out.println("</body>");
out.println("</html>");
}
rs.close();
((PrintWriter) stmt).close();
con.close();
} catch ( SQLException e) {
e.printStackTrace();
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
}
