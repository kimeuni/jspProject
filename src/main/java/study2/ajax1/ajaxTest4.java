package study2.ajax1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import study2.login.LoginDAO;
import study2.login.LoginVO;

@WebServlet("/ajaxTest4")
public class ajaxTest4 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")== null ? "" : request.getParameter("mid");
		
		LoginDAO dao = new LoginDAO();
		
		LoginVO vo = dao.getLoginSearch(mid);
		
		// aJax는 객체를 가져갈 수 없기 때문에 key,value로 나눠서 전송한다.
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mid", vo.getMid());
		map.put("name", vo.getName());
		map.put("point", vo.getPoint()+"");  // point는 int타입이기 때문에 String형식으로 형변환 시켜준다. 
		map.put("todayCount", vo.getTodayCount()+"");
		System.out.println("map : " + map);
		
		// JSON 형식의 자료로 변경처리한다. 라이브러리 주소 : https://code.google.com/archive/p/json-simple/downloads 
		// 이 라이브러리를 WEB-INF -> lib 에 넣어놔야 JSONObject를 사용할 수 있다.
		JSONObject jobj = new JSONObject(map);
		System.out.println("jobj : " + jobj);  
		
		// JSON객체를 문자열로 변경처리...
		String str = jobj.toJSONString();  //String으로 변경시켜줌
		System.out.println("str : " + str);  
		
		
		// 공공자료는 JSON 형식으로 제공된다고 함?
		
		response.getWriter().write(str);
	}
}
