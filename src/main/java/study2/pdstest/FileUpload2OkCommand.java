package study2.pdstest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import study2.StudyInterface;

public class FileUpload2OkCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/images/pdstest");  //이미지 저장할 파일경로 설정 and 경로를 변수에 담음
		int maxSize = 1024*1024*10; //서버에 저장시킬 파일의 최대용량을 10MB로 제한한다.(1회저장용량)
		String encoding = "UTF-8";
		
		// 파일 업로드 처리... (객체가 생성되면서 바로 파일이 업로드 처리된다.)
		MultipartRequest multipartRequest = new MultipartRequest(request, realPath,maxSize,encoding, new DefaultFileRenamePolicy());
		
		// 업로드된 파일의 정보를 추출해보자...  / getOriginalFileName: 올렸을 때 원본파일 명 (같은 이름이 중복되면 서버에 저장되는 이미지에 이름이 달라지기 때문에 원본파일명과 서버에 저장되는 파일명 2개를 잘 알아야한다.)
	 	String originalFileName = multipartRequest.getOriginalFileName("fName");
		String filesystemName = multipartRequest.getFilesystemName("fName"); // getFilesystemName :서버에 올라가 있는 파일명
		
		// 파일이 제대로 왔는지 확인을 위한 처리
		if(originalFileName != null && !originalFileName.equals("")) {
			request.setAttribute("msg", "파일이 업로드되었습니다.");
		}
		else {
			request.setAttribute("msg", "파일 업로드 실패.");
		}
		request.setAttribute("url", "fileUpload2.st");
		
	}

}
