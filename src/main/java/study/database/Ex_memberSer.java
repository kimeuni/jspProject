package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/database/ex_memberSer")
public class Ex_memberSer extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter("sid")== null ? "" : request.getParameter("sid");
		
//		PrintWriter out = response.getWriter();
//		out.print(sid);
		
		LoginDAO dao = new LoginDAO();
		LoginVO vo = new LoginVO();
		
		vo = dao.getLoginSearch(sid);
		
		if(vo.getMid() != null) {
			request.setAttribute("vo", vo);
		}
		String viewPage = "/study/database/memberMain.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
