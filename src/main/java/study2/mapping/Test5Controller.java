package study2.mapping;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 디렉토리패턴은 반드리 / 가 들어가지만, 확장자는 /가 들어가지 않는다.
//@WebServlet("/mapping/test5.do")
@WebServlet("*.do")  // 확장자명이 .do가 붙어있는 것을 모두 읽어온다.
public class Test5Controller extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("이곳은 서블릿 : 확장자 do 입니다.");
		
		String uri = request.getRequestURI();

//		System.out.println("uri : "+uri);
		
		// 디렉토리 패턴처럼 만들어준다.. (ex) /test5 / 앞부분과 .do부분은 다 똑같기 때문에 잘라서 "파일명"부분만 가져옴)
		String com = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));

		System.out.println("com : "+com);
		
		// jsp만 들어갈 때는 이렇게 적어도 감..! (Command 객체를 만들어서 처리하려면 밑에처럼 if문을 사용해야함)
		/* String viewPage = "/WEB-INF/study2/mapping_20231107" + com + ".jsp"; */
		
		String viewPage = "/WEB-INF/study2/mapping_20231107";
		
		if(com.equals("/test5")) {
			viewPage += "/test5.jsp";
		}
		else if(com.equals("/test5_2")) {
			viewPage += "/test5_2.jsp";
		}
		else if(com.equals("/test5_3")) {
			viewPage += "/test5_3.jsp";
		}
		else if(com.equals("/test5_4")) {
			int su1 = request.getParameter("su1")== null ? 0 : Integer.parseInt(request.getParameter("su1"));
			int su2 = request.getParameter("su2")== null ? 0 : Integer.parseInt(request.getParameter("su2"));
			String op = request.getParameter("op")== null ? "+" : request.getParameter("op");
			
			Test5Service t5Service = new Test5Service();
			
			int res = t5Service.calc(su1,su2,op);
			
			request.setAttribute("su1", su1);
			request.setAttribute("su2", su2);
			request.setAttribute("op", op);
			request.setAttribute("res", res);
			
			viewPage += "/test5.jsp";
		}
		else if(com.equals("/test5_5")) {
			int dan = (request.getParameter("dan")== null || request.getParameter("dan").equals("")) ? 1 : Integer.parseInt(request.getParameter("dan"));
			
			Test5_5Service t5_5Service = new Test5_5Service();
			String str = t5_5Service.guguDan(dan);
			
			request.setAttribute("str", str);
			
			viewPage += "/test5_5.jsp";
		}
		
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
