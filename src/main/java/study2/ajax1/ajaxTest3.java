package study2.ajax1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.login.LoginDAO;
import study2.login.LoginVO;

@WebServlet("/ajaxTest3")
public class ajaxTest3 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")== null ? "" : request.getParameter("mid");
		
		LoginDAO dao = new LoginDAO();
		
		LoginVO vo = dao.getLoginSearch(mid);
		
		// ajax는 객체를 넘길 수 없기 때문에... vo.toString()으로 넘김..(문자형식) 하지만 객체가 아니기 때문에 vo.getName 등으로 값을 읽어올 수 없음.
		response.getWriter().write(vo.toString());  //값을 넘겨줌
		
	}
}
