package study.j1101;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/j1101/ex5Ok")
public class Ex5Ok extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String name = request.getParameter("name")== null ? "" : request.getParameter("name");
		String gender = request.getParameter("gender")== null ? "" : request.getParameter("gender");
		
		ArrayList<Jstl4VO> vos = new ArrayList<Jstl4VO>();
		
		Jstl4VO vo = new Jstl4VO();
		vo.setName("홍길동");
		vo.setAge(25);
		vo.setGender("남자");
		vo.setJob("학생");
		vo.setAddress("서울");
		vos.add(vo);
		
		vo = new Jstl4VO();
		vo.setName("김말숙");
		vo.setAge(22);
		vo.setGender("여자");
		vo.setJob("회사원");
		vo.setAddress("청주");
		vos.add(vo);
		
		vo = new Jstl4VO();
		vo.setName("이기자");
		vo.setAge(35);
		vo.setGender("남자");
		vo.setJob("공무원");
		vo.setAddress("제주");
		vos.add(vo);
		
		vo = new Jstl4VO();
		vo.setName("오하늘");
		vo.setAge(19);
		vo.setGender("여자");
		vo.setJob("학생");
		vo.setAddress("청주");
		vos.add(vo);
		
		vo = new Jstl4VO();
		vo.setName("고인돌");
		vo.setAge(55);
		vo.setGender("남자");
		vo.setJob("자영업");
		vo.setAddress("서울");
		vos.add(vo);
		
		vo = new Jstl4VO();
		vo.setName("고은별");
		vo.setAge(29);
		vo.setGender("여자");
		vo.setJob("연예인");
		vo.setAddress("부산");
		vos.add(vo);
		
		// 해결
		PrintWriter out = response.getWriter();
		if(!name.isEmpty()) {
			ArrayList<Jstl4VO> vosN = new ArrayList<Jstl4VO>(); 
			for(int i=0; i<vos.size(); i++) {
				if(vos.get(i).getName().equals(name)) {
					vosN.add(vos.get(i));  //새로 생성한 ArrayList(voss)에 vos.get(i)(이게 vo임)를 담는다.  //같은 이름(vos)으로 ArrayList를 생성하면 위에 저장한 vos를 삭제해(덮어씌우기) 버리기 때문에 다른 이름으로 새로 생성한다.
//					System.out.println(vos.get(i));
//					out.print(vos.get(i)); 
				}
			}	
			request.setAttribute("vos", vosN );
		}
		else if(!gender.isEmpty()) {
			ArrayList<Jstl4VO> vosG = new ArrayList<Jstl4VO>(); 
			for(int i=0; i<vos.size(); i++) {
				if(vos.get(i).getGender().equals(gender)) {
					vosG.add(vos.get(i));
//					out.print(vos.get(i)); 
				}
			}
			request.setAttribute("vos", vosG );
		}
		else {
			
		}
		
		/*
		 * String viewPage = request.getContextPath()+"/study/1101_JSTL/jstl5Res.jsp";
		 */
		
//		String viewPage = "/study/1101_JSTL/jstl5Res.jsp"; 
		 
//		request.setAttribute("vos", vos);
		String viewPage = "/study/1101_JSTL/ex5_jstl5Res.jsp";
//		String viewPage = "/study/1101_JSTL/ex.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
}
