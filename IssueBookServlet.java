

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
@WebServlet("/IssueBookServlet")
public class IssueBookServlet extends HttpServlet {
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
int bookId = Integer.parseInt(request.getParameter("bid"));
String userId=request.getParameter("userid");
Date issuedt=new Date();
try {
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "password");
PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO loans (bid, userid,issuedte) VALUES (?, ?, ?)");
stmt1.setInt(1, bookId);
stmt1.setString(2, userId);
stmt1.setDate(3,issuedte);
stmt1.executeUpdate();
PreparedStatement stmt2 = conn.prepareStatement("UPDATE books SET available = ? WHERE id = ?");
stmt2.setBoolean(1, false);
stmt2.setInt(2, bookId);
stmt2.executeUpdate();
conn.close();
response.sendRedirect("cart.html");
} catch (SQLException e) {
e.printStackTrace();
response.getWriter().println("Error: " + e.getMessage());
}
}
}

