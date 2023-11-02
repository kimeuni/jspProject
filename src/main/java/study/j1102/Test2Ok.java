package study.j1102;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/j1102/test2Ok")
public class Test2Ok extends HttpServlet implements ServletContextListener{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("2. 이곳은 service()메소드 입니다.");
		doPost(request, response);  //나에게 넘어온 모든 자료를 doPost로 보낸다.
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("1. 이곳은 init()메소드 입니다.");
	}
	
	@Override
	public void destroy() {
		System.out.println("5.이곳은 destroy()메소드 입니다.");
	} 
	
	@Override    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("4.이곳은 doGet()메소드 입니다.");
		String content = request.getParameter("content")== null? "" : request.getParameter("content");
		String introduce = request.getParameter("introduce")== null? "" : request.getParameter("introduce");
		
		System.out.println("이곳은 /j1102/Test2Ok.java 입니다.");
		System.out.println("content : " + content);
		System.out.println("introduce : " + introduce);
		
		PrintWriter out = response.getWriter();
		out.print("이곳은 Test1Ok 컨트롤러 입니다.");
		
		request.setAttribute("msg","Test2Ok.java에서 보냅니다.");
		
		String viewPage = "/study/1102_web_xml/test2Res.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
	 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("3. 이곳은 doPost()메소드 입니다.");
		doGet(request, response);  //나에게 넘어온 모든 자료를 doGet으로 보낸다.
	}
	
	@PostConstruct
	public void initPostConstruct() { //initPostConstruct : 변수명임.. 아무렇게나 적어도 됨
		System.out.println("0. 이곳은 @PostConstruct 입니다.");
	}
	
	@PreDestroy
	public void destroyPreDestroy() { 
		System.out.println("6. 이곳은 @PreDestroy 입니다.");
	}  
	
	// web.xml에 선언을 해놔야 사용이 가능하다.
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("00. 이곳은 contextInitialized 입니다.");
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("100. 이곳은 contextDestroyed 입니다.");
	}
}
