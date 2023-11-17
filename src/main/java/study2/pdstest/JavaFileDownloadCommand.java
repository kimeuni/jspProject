package study2.pdstest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.StudyInterface;

public class JavaFileDownloadCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/images/pdstest/");  //마지막에 / 를 붙였을때도 있고 아닐때도 있는데, 왜 그런지 잘 생각해보고 적기
		
		String fileName = request.getParameter("file")== null ? "" : request.getParameter("file");
		
		File file =new File(realPath+fileName);
		
		/* ==================================================================================================== */
		// 웹에서 사용하기 위한 기본형식
		
		/* 프로토콜형식에 맞도록 헤더에 정보를 제공해 준다. */
		String mimeType = request.getServletContext().getMimeType(file.toString());   //파일형식을 지정해주는 파일?  // getMimeType은 문자형식만 들어갈 수 있으므로 toString()으로 변환시켜준다.
		if(mimeType == null) {
			response.setContentType("application/octet-stream"); // 파일형식이 없으면 2진 바이너리 형식으로 처리하도록 함.
		}
		String downLoadName = "";
		// 브라우저의 정보
		if(request.getHeader("user-agent").indexOf("MSIE") == -1) { //MSIE: 마이크로소프트 인터넷 익스플로어
			downLoadName = new String(fileName.getBytes("UTF-8"), "8859_1");  // 인터넷익스플로어가 아니면 UTF-8형식으로 해주세요.
		}
		else {
			downLoadName = new String(fileName.getBytes("EUC-KR"), "8859_1"); 
		}
		
		// 헤더에 정보를 첨부...    // 전부 예약어임.. (Content-Disposition , attachment;filename)
		response.setHeader("Content-Disposition", "attachment;filename="+downLoadName);
		/* ==================================================================================================== */
		
		// FileInputStream 서버 파일
		FileInputStream fis = new FileInputStream(file);
		// 서버에 보낼때 사용 (복사의 개념으로 사용)
//		FileOutputStream fos = new FileOutputStream(file);
		
		// 클라이언트로 보내기(전송)
		ServletOutputStream sos = response.getOutputStream();
		
		// 전송할 객체를 생성한 후, 실제 전송은 Byte단위로 처리한다.
		byte[] b = new byte[2048];  //2K  // 많이하고 적게하고는 안전성..을 위해
		
		int data = 0;
		while((data = fis.read(b,0,b.length)) != -1) { // 2048바이트만큼 있으면 값저장
			sos.write(b,0,data); //데이터에 담은만큼 넣어주세요.
		}
		sos.flush(); // 혹시라도 찌꺼기(?)가 있으면 그것까지 담아줘.. (적어주는게 좋다고함)
		// 여기까지 다운로드가 완료된다.
		
		//IO에 관한건 강제로 닫아주는게 좋다.
		sos.close();
		fis.close();
		
	}

}
