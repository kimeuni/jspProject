package pds;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.SecurityUtil;

public class PdsInputOkCommand implements PdsInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/images/pds");  //이미지 저장할 파일경로 설정 and 경로를 변수에 담음
		int maxSize = 1024*1024*30; //서버에 저장시킬 파일의 최대용량을 10MB로 제한한다.(1회저장용량)
		String encoding = "UTF-8";
		
		// 파일 업로드 처리... (객체가 생성되면서 바로 파일이 업로드 처리된다.)
		MultipartRequest multipartRequest = new MultipartRequest(request, realPath,maxSize,encoding, new DefaultFileRenamePolicy());
		
		// 업로드된 파일의 정보를 추출해보자...  /   Enumeration : 여러개 파일 넣을 시 적음(열거형으로 처리)
		Enumeration fileNames = multipartRequest.getFileNames(); // getFileNames(): 이미지 여러개 넣었을 때 처리 가능
		String file = "";
		String originalFileName = "";
		String filesystemName ="";
		
		while(fileNames.hasMoreElements()) { //iterator 혹은 Enumeration에 들어있는 것은 이런식으로 값을 가져올 수 있다.
			file = (String)fileNames.nextElement(); //첫번째 파일 꺼냄
			
			// 파일이 제대로 업로드 되었는지 확인 후 DB에 저장한다.
			if(multipartRequest.getFilesystemName(file) != null) { 
				originalFileName += multipartRequest.getOriginalFileName(file) + "/";
				filesystemName += multipartRequest.getFilesystemName(file) + "/";
				
				
			}
		}
		// "/" 넣은거 빼기
		originalFileName = originalFileName.substring(0, originalFileName.length()-1);
		filesystemName = filesystemName.substring(0, filesystemName.length()-1);
		
//		System.out.println("원본 파일명 : " + originalFileName);
//		System.out.println("서버에 저장된 파일명 : " + filesystemName);
		
		HttpSession session = request.getSession();
		String mid = (String)session.getAttribute("sMid");
		String nickName = (String)session.getAttribute("sNickName");
		
		// 업로드시킨 파일을 DB에 저장시키기 위해서 전송된 폼의 내용들을 모두 변수에 받아준다.
		int fileSize = multipartRequest.getParameter("fileSize")== null ? 0 : Integer.parseInt(multipartRequest.getParameter("fileSize"));
		String title = multipartRequest.getParameter("title")== null ? "" : multipartRequest.getParameter("title");
		String part = multipartRequest.getParameter("part")== null ? "" : multipartRequest.getParameter("part");
		String pwd = multipartRequest.getParameter("pwd")== null ? "" : multipartRequest.getParameter("pwd");
		String openSw = multipartRequest.getParameter("openSw")== null ? "" : multipartRequest.getParameter("openSw");
		String content = multipartRequest.getParameter("content")== null ? "" : multipartRequest.getParameter("content");
		String hostIp = multipartRequest.getParameter("hostIp")== null ? "" : multipartRequest.getParameter("hostIp");
	
		// 비밀번호 암호화(sha256)
		SecurityUtil security =  new SecurityUtil();
		pwd = security.encryptSHA256(pwd);
		
		// 가공처리된 모든 자료들을 VO에 담아서 DB로 넘겨준다.
		PdsVO vo = new PdsVO();
		vo.setMid(mid);
		vo.setNickName(nickName);
		vo.setfName(originalFileName);
		vo.setfSName(filesystemName);
		vo.setfSize(fileSize);
		vo.setTitle(title);
		vo.setPart(part);
		vo.setPwd(pwd);
		vo.setOpenSw(openSw);
		vo.setContent(content);
		vo.setHostIp(hostIp);
		
		PdsDAO dao = new PdsDAO();
		
		int res = dao.setPdsInputOk(vo);
		
		if(res == 1) {
			request.setAttribute("msg", "자료실에 자료가 업로드 되었습니다.");
			request.setAttribute("url", "pdsList.pds");
		}
		else {
			request.setAttribute("msg", "자료실에 자료 업로드 실패.");
			request.setAttribute("url", "pdsInput.pds");
		}
		
	}
}