package study.j1026;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/j1026/test10Ok")
public class Test10Ok extends HttpServlet{
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
			response.sendRedirect(request.getContextPath()+"/study/1026/test10.jsp?flag=no");
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
			
//			response(레스판스) 객체에 의한 전송 : location.href와 비슷하다.
			name  = URLEncoder.encode(name, "utf-8");   //name을 utf-8형식으로 바꿔주세요.. 라는 의미
			gender = URLEncoder.encode(gender, "utf-8");
			hobby = URLEncoder.encode(hobby, "utf-8");
			job = URLEncoder.encode(job, "utf-8");
			//정상처리 되었을 경우 (test10Res.jsp 파일로 가서 넘긴 값을 출력하도록 할 예정이다)
//			response.sendRedirect(request.getContextPath()+"/study/1026/test10Res.jsp?flag="+name); 
			response.sendRedirect(request.getContextPath()+"/study/1026/test10Res.jsp?name="+name+"&age="+age+"&gender="+gender+"&hobby="+gender+"&job="+job); 
		}
	}
}
