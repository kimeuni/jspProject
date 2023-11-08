package study2.ajax2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.us")
public class UserController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserInterface command = null ;
		String viewPage ="/WEB-INF/study2/ajax2_1108";
		
		String com = request.getRequestURI();
		com = com.substring(com.lastIndexOf("/"),com.lastIndexOf("."));
		
		if(com.equals("/userList")) {
			command = new UserListCommand();
			command.execute(request, response);
			viewPage += "/userList.jsp";
		} 
		else if(com.equals("/userInput")) {
			command = new UserInputCommand();   //처리는 여기서 끝내야 한다. 값은 UserInputCommand 안에서 response로 보낸다..
			command.execute(request, response);
			return;
		} 
		else if(com.equals("/userSearch")) {
			command = new UserSearchCommand();   //처리는 여기서 끝내야 한다.
			command.execute(request, response);
			return;
		} 
		else if(com.equals("/userUpdate")) {
			command = new UserUpdateCommand();   //처리는 여기서 끝내야 한다.
			command.execute(request, response);
			return;
		} 
		else if(com.equals("/userDel")) {
			command = new UserDelCommand();   //처리는 여기서 끝내야 한다.
			command.execute(request, response);
			return;
		} 
		
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
