package study.j1026;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.ant.jmx.JMXAccessorQueryTask;

@WebServlet("/j1026/test8Ok")
public class Test8Ok extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 이런식으로 만약 값이 없이 넘어왔을 때 에러(500에러)가 뜨기 때문에 에러를 없애기 위해서 이런식으로 처리한다. (백엔트 체크)
		/* if문 사용하여 백엔트체크
		String name = request.getParameter("name");
		if(name == null) name = "";
		String ageStr = request.getParameter("age");
		int age;
		if(ageStr == null || ageStr.equals("")) age= 0;
		else age = Integer.parseInt(request.getParameter("age"));
		*/
		
		// 삼항연산자 사용하여 백엔드 체크 (위와 내용은 동일)
		String name = request.getParameter("name")==null ? "" : request.getParameter("name");
		// age가 문자로 넘어오기 때문에 바로 여기서 비교체크를 못함 (20살 이상만 가입 가능 관련)
		int age = (request.getParameter("age")== null || request.getParameter("age").equals("")) ? 0 : Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender")== null ? "남자" : request.getParameter("gender");
//		String hobby = request.getParameter("hobby")== null ? "" : request.getParameter("hobby"); (잘못된 방법 : 여러개 값이 넘어올 수 있기 때문에)
		
		String[] hobbys = request.getParameterValues("hobby");   // getParameterValues : 배열로 받는다.
		String job = request.getParameter("job");
		
//		if(age < 20) {
//			//이럴 경우에만 out.println() 을 사용! (다른 경우도 있지만 아직은 안 배움~)
//			PrintWriter out = response.getWriter();
//			out.println("<script>");
//			out.println("alert('미성년자는 가입할 수 없습니다.');");
//			out.println("history.back();");
//			out.println("</script>");	
//		}
		if(name.equals("") || age < 20) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('입력자료가 잘못되었습니다. 확인후 다시 가입하세요.');");  //백엔드에서는 뭉뚱그려서 전달해도 괜찮다
			out.println("history.back();");  //history.back() : 바로 전단계로 감.. (브라우저의 전단계: 브라우저에서 뒤로가기 눌렀을때와 같음)
			out.println("</script>");	
//			return;  //작업진행 종료  (이걸 안하면 가입처리되므로 return으로 멈춰야함)
		}
		else {
			// 이부분이 백엔드 체크가 끝나고 DB에 저장하는 내용이라고 생각하면 됨!
			// DB에 저장시키고 있다...
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
			
			// DB저장완료 후 jsp로 이동처리한다.
			out.println("<script>");
			out.println("alert('회원가입이 성공적으로 되었습니다.');");  
			out.println("location.href='"+request.getContextPath()+"/study/1026/test8.jsp';");  
			out.println("</script>");	
		}
	}
}
