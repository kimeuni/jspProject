package study2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.pdstest.FileDownloadCommand;
import study2.pdstest.FileUpload1OkCommand;
import study2.pdstest.FileUpload2OkCommand;
import study2.pdstest.FileUpload3OkCommand;
import study2.pdstest.FileUpload4OkCommand;
import study2.pdstest.JavaFileDownloadCommand;
import study2.pdstest.TempFileDeleteCommand;

@WebServlet("*.st")
public class StudyController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudyInterface command = null;
		String viewPage ="/WEB-INF/study2";
		
		String uri =request.getRequestURI();
		String com = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		if(com.equals("/ajaxTest1")) {
			viewPage += "/ajax1_1108/ajaxTest1.jsp";
		}
		else if(com.equals("/ajaxTest1Ok")) {
			command = new AjaxTest1OkCommand();
			command.execute(request, response);
			viewPage = "/include/msg.jsp";
			
		}
		else if(com.equals("/uuidProcess")) {
			viewPage += "/uuid/uuidForm.jsp";
			
		}
		// 파일 업로드1 View (학원 : 20231116)
		else if(com.equals("/fileUpload1")) {
			viewPage += "/pdstest/fileUpload1.jsp";
			
		}
		// 파일 업로드1 처리 (학원 : 20231116~20231117)
		else if(com.equals("/fileUpload1Ok")) {
			command = new FileUpload1OkCommand();
			command.execute(request, response);
			
			viewPage = "/include/msg.jsp";
		}
		// 파일 업로드2 View (학원 : 20231116)
		else if(com.equals("/fileUpload2")) {
			viewPage += "/pdstest/fileUpload2.jsp";
		}
		// 파일 업로드2 처리 (학원 : 20231117)
		else if(com.equals("/fileUpload2Ok")) {
			command = new FileUpload2OkCommand();
			command.execute(request, response);
			
			viewPage = "/include/msg.jsp";
		}
		// 멀티 파일 업로드 1 (학원 : 20231117)
		else if(com.equals("/fileUpload3")) {
			viewPage += "/pdstest/fileUpload3.jsp";
		}
		else if(com.equals("/fileUpload3Ok")) {
			command = new FileUpload3OkCommand(); 
			command.execute(request, response);
			
			viewPage = "/include/msg.jsp";
		}
		else if(com.equals("/fileUpload4")) {
			viewPage += "/pdstest/fileUpload4.jsp";
		}
		else if(com.equals("/fileUpload4Ok")) {
			command = new FileUpload4OkCommand(); 
			command.execute(request, response);
			
			viewPage = "/include/msg.jsp";
		}
		// 다운로드 (학원 : 20231117)
		else if(com.equals("/fileDownload")) {
			command = new FileDownloadCommand(); 
			command.execute(request, response);
			
			viewPage += "/pdstest/fileDownload.jsp";
		}
		// 파일 삭제 (학원 :20231117)
		else if(com.equals("/tempFileDelete")) {
			command = new TempFileDeleteCommand(); 
			command.execute(request, response);
			return;
		}
		else if(com.equals("/javaFileDownload")) {
			command = new JavaFileDownloadCommand();  
			command.execute(request, response);
			return;
		}
		request.getRequestDispatcher(viewPage).forward(request, response);
		
	}
}
