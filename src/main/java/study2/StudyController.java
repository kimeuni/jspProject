package study2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.apiTest.SaveCrimeDataCommand;
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
		// 다운로드 이동 (학원 :20231117)
		else if(com.equals("/javaFileDownload")) {
			command = new JavaFileDownloadCommand();  
			command.execute(request, response);
			return;
		}
		// 캘린더(달력) (학원 :20231122)
		else if(com.equals("/calendar1")) {
			command = new Calendar1Command();  
			command.execute(request, response);
			viewPage += "/calendar/calendar1.jsp";
		}
		// 캘린더(달력) (학원 :20231122)
		else if(com.equals("/calendar2")) {
			command = new Calendar2Command();  
			command.execute(request, response);
			viewPage += "/calendar/calendar2.jsp";
		}
		// api (학원 :20231123)
		else if(com.equals("/api1")) {
			viewPage += "/apiTest/api1.jsp";
		}
		// api (학원 :20231123)
		else if(com.equals("/apiFetch")) {
			viewPage += "/apiTest/apiFetch.jsp";
		}
		// api (학원 :20231123)
		else if(com.equals("/apiTest")) {
			viewPage += "/apiTest/apiTest.jsp";
		}
		// api 범죄 DB 저장 (학원 :20231123)
		else if(com.equals("/saveCrimeData")) {
			command = new SaveCrimeDataCommand();  
			command.execute(request, response);
			return;
		}
		// api 범죄 DB 삭제 (학원 :20231123)
		else if(com.equals("/deleteCrimeData")) {
			command = new DeleteCrimeDataCommand();  
			command.execute(request, response);
			return;
		}
		// api 범죄 DB 삭제(전체DB 데이터 중 합계,평균 구해서 뿌리기) (학원 :20231123 /직접 만듦)
		else if(com.equals("/listCrimeDate")) {
			command = new ListCrimeDateCommand();  
			command.execute(request, response);
			viewPage += "/apiTest/apiTest.jsp";
		}
		else if(com.equals("/policeCheck")) {
			command = new PoliceCheckCommand();  
			command.execute(request, response);
			viewPage += "/apiTest/apiTest.jsp";
		}
		else if(com.equals("/yearPoliceCheck")) {
			command = new YearPoliceCheckCommand();  
			command.execute(request, response);
			viewPage += "/apiTest/apiTest.jsp";
		}
		request.getRequestDispatcher(viewPage).forward(request, response);
		
	}
}
