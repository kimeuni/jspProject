package guest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/guest/guestContent")
public class GuestContent extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name")== null ? "" : request.getParameter("name");
		String email = request.getParameter("email")== null ? "" : request.getParameter("email");
		String homeP = request.getParameter("homeP")== null ? "" : request.getParameter("homeP");
		String cont = request.getParameter("cont")== null ? "" : request.getParameter("cont");
		String hIp = request.getParameter("hIp")== null ? "" : request.getParameter("hIp");
	
		// name부분에 태그를 사용할 수 없도록 막는다..
		name = name.replace("<", "&lt;");
		name = name.replace(">", "&gt;");
		
		GuestDAO dao = new GuestDAO();
		
		int res = dao.setContentW(name,email,homeP,cont,hIp);
		
		PrintWriter out = response.getWriter();
		if(res != 0) {
			out.println("<script>");
			out.println("alert('글이 등록되었습니다.')");
			out.println("location.href='"+request.getContextPath()+"/GuestList'");
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('등록에 실패하였습니다..')");
			out.println("location.href='"+request.getContextPath()+"/GuestList'");
			out.println("</script>");
		}
	}
}
