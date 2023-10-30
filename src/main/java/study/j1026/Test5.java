package study.j1026;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/1026/Test5")
public class Test5 extends HttpServlet{
	/* service :  */
	/* service와 doGet doPost를 같이 써도 상관은 없다고 한다... 하지만 우선순위는 service이기 때문에 굳이 같이 안써줘도 된다. */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String flag = request.getParameter("flag");
		
		System.out.println("name : " + name);
		System.out.println("age : " +age);
		System.out.println("flag : " + flag);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		System.out.println("이곳은 doGet입니다.");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		System.out.println("이곳은 doPost입니다.");
	}
}
