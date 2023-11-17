package study2.pdstest;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.StudyInterface;

public class FileDownloadCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 엄청많이 쓰기 때문에 외우기
		String realPath = request.getServletContext().getRealPath("/images/pdstest");
		
		// files에 정보는 여러개이기 때문에 배열로 받는다.
		String[] files =new File(realPath).list();  // realPath이 경로에 들어있는 list() ==> 목록(정보)을 불러온다.
		
		// 향상된 for문으로 들어간 값 확인해보기
//		for(String file : files) {
//			System.out.println("file : " + file);
//		}
		
		request.setAttribute("files", files);
		
	}

}
