package guest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/guest/guestDe")
public class GuestDe extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idx = request.getParameter("idx")== null ? "" : request.getParameter("idx");
		
		GuestDAO dao = new GuestDAO();
		
		int res = dao.setListDelete(idx);
		
		PrintWriter out = response.getWriter();
		if(res != 0) {
			out.println("<script>");
			out.println("alert('글이 삭제되었습니다.')");
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
