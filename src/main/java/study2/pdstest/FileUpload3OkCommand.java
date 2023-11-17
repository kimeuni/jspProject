package study2.pdstest;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import study2.StudyInterface;

public class FileUpload3OkCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/images/pdstest");  //이미지 저장할 파일경로 설정 and 경로를 변수에 담음
		int maxSize = 1024*1024*10; //서버에 저장시킬 파일의 최대용량을 10MB로 제한한다.(1회저장용량)
		String encoding = "UTF-8";
		
		// 파일 업로드 처리... (객체가 생성되면서 바로 파일이 업로드 처리된다.)
		MultipartRequest multipartRequest = new MultipartRequest(request, realPath,maxSize,encoding, new DefaultFileRenamePolicy());
		
		// 업로드된 파일의 정보를 추출해보자...  / 
		Enumeration fileNames = multipartRequest.getFileNames(); // getFileNames(): 이미지 여러개 넣었을 때 처리 가능
		String file = "";
		String originalFileName = "";
		String filesystemName ="";
		
		System.out.println("realPath : " + realPath); // 경로 확인용 sysout
		while(fileNames.hasMoreElements()) { //iterator 혹은 Enumeration에 들어있는 것은 이런식으로 값을 가져올 수 있다.
			file = (String)fileNames.nextElement();
			originalFileName = multipartRequest.getOriginalFileName(file);
			filesystemName = multipartRequest.getFilesystemName(file);
			
			System.out.println("원본 파일명 : " + originalFileName);
			System.out.println("서버에 저장된 파일명 : " + filesystemName);
		}
		
		// 파일이 제대로 왔는지 확인을 위한 처리
		if(originalFileName != null && !originalFileName.equals("")) {
			request.setAttribute("msg", "파일이 업로드되었습니다.");
		}
		else {
			request.setAttribute("msg", "파일 업로드 실패.");
		}
		request.setAttribute("url", "fileUpload3.st");
		
	}

}
