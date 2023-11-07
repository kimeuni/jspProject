package study2.mapping2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("*.re") 
public class Test5ReController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// interface만들기
		Test5ReInterface command = null;
		
		
		String viewPage = "/WEB-INF/study2/mapping2_20231107";
		String uri = request.getRequestURI();
		String com = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
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
			// 시리얼라이즈? 직열화?
			// 위에 있는 인터페이스(Test5ReInterface command = null;)에 의존하는 구현객체야...
			command = new Test5_4Command(); //얘는 생성만 함 // 이렇게 만들면 create class를 하면 자동으로 Test5ReInterface(인터페이스)가 들어간다..
			command.execute(request, response); // 넘어온 거(값) 그대로 넘긴다.. (직열화)
			
			viewPage += "/test5.jsp";
		}
		else if(com.equals("/test5_5")) {
			command = new Test5_5Command();
			command.execute(request, response);
			
			viewPage += "/test5_5.jsp";
		}
		else if(com.equals("/test5_6")) {
			command = new Test5_6Command();
			command.execute(request, response);
			
			// 메세지로 보내는 건 += 로 적으면 안됨~~!~!!
			viewPage = "/include/msg.jsp";  // include에 있는 msg.jsp는 메세지를 띄우기 위해서 만든 jsp 파일이다. msg(출력할 메세지)와 url(전송할 url)을 넘긴다. 
			
//			 viewPage += "/test5.jsp"; 
		}
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
