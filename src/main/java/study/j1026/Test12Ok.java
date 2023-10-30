package study.j1026;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/j1026/test12Ok")
public class Test12Ok extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// Front에서 넘어온 값을 변수에 담아준다.
		String name = request.getParameter("name")==null ? "" : request.getParameter("name");
		int age = (request.getParameter("age")== null || request.getParameter("age").equals("")) ? 0 : Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender")== null ? "남자" : request.getParameter("gender");
		String[] hobbys = request.getParameterValues("hobby");   
		String job = request.getParameter("job");
		
		// 기본적으로 다시 체크해야할 것들을 체크해 준다. (잘못된 자료는 다시 돌려보낸다. 정상적인 자료는 DB에 저장시켜준다..)
		if(name.equals("") || age < 20) {
			// 정상처리 x (가입조건을 만족하지 않았기에 다시 가입창으로 전송시켜준다.)
			response.sendRedirect(request.getContextPath()+"/study/1026/test11.jsp?flag=no");
		}
		else {
			// 가입조건을 만족하였기에, DB에 저장시키고 회원 메인창(test10Res.jsp)으로 이동시킨다.
			System.out.println("name : " + name);
			System.out.println("age : " + age);
			System.out.println("gender : " + gender);
			String hobby = "";
			for(String h : hobbys) {
				System.out.println("h : " + h);
				hobby += h + "/";
			}
			hobby = hobby.substring(0,hobby.length()-1);
			System.out.println("hobby : " + hobby);   //이 부분을 DB에 넣는 것!
			System.out.println("job : " + job);
			PrintWriter out = response.getWriter();
			
			// request저장소에 전송하려고 하는 자료들을 모두 담아준다. : request("변수명",전송값)		// set은 저장
			// 헤드?에 저장 된다? 그렇기 때문에 EL로 표기 가능?
			request.setAttribute("name", name);
			request.setAttribute("age", age);
			request.setAttribute("gender", gender);
			request.setAttribute("hobby", hobby);
			request.setAttribute("job", job);
			
//			String viewPage = "/study/1026/test12Msg.jsp"; // 메세지 컨트롤러  
			String viewPage = "/study/1026/test12Res.jsp";    // study 앞에 있는  /(슬레쉬)는 wabapp을 의미함.
			
			// RequestDispatcher는 변수명을 dispatcher로 적기로 약속(?)되어 있음.. (다른 거 적어도 되긴함.)
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}
}
