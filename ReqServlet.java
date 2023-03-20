

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ReqServlet")
public class ReqServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ReqServlet() {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookid"));
		String userId= request.getParameter("userid");
		Date returnDate = new Date();

	}

}
