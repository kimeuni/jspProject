package pds;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PdsTotalDownCommand implements PdsInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")== null ? 0 : Integer.parseInt(request.getParameter("idx"));
		
		// DB에 저장된 파일의 정보(원본파일/시스템에 저장된파일)을 가져와서 각각의 파일로 분리작업한다.
		PdsDAO dao = new PdsDAO();
		PdsVO vo = dao.getPdsIdxSearch(idx);
		System.out.println("vo : " + vo);
		
		String[] fNames = vo.getfName().split("/");
		String[] fSNames = vo.getfSName().split("/");
		
		// 파일 압축에 필요한 객체들을 선언준비한다.
		FileInputStream fis = null;
		FileOutputStream fos = null;  //zip파일에 넣는다..?
		ZipOutputStream zos = null; // 암축x 합치는거o
		ServletOutputStream sos = null;
		
		String realPath = request.getServletContext().getRealPath("/images/pds/");  // 이미지가 저장된 공간
		String zipPath = request.getServletContext().getRealPath("/images/pds/temp/"); // 압축한 파일 저장한 공간
		String zipName = vo.getTitle() + ".zip";

		fos  = new FileOutputStream(zipPath+zipName); //zip파일 만들기(서버 안에서 만들기)
		zos = new ZipOutputStream(fos); // zip파일 안에 파일을 넣는 준비를 함.
		
		byte[] b = new byte[2048];
		int data = 0;
		
		// 각각의 파일을 압축처리한다.
		for(int i=0; i<fNames.length; i++) {
			File file = new File(realPath+fSNames[i]);
			
			fis = new FileInputStream(file); //저장되어있는 이미지 생성후 넣기 , 껍데기에 내용을 넣을 준비가 끝냄 (아직은 껍데기)
			zos.putNextEntry(new ZipEntry(fSNames[i])); // 위에서 가공처리한 내용은 여기에 객체가 들어간다. (내용은 아직 안들어감)
			
			while((data = fis.read(b, 0, b.length)) != -1) { //내용 넣기
				zos.write(b,0,data);
			}
			zos.finish(); //남은 찌꺼기가 있으면 보내라
			zos.closeEntry(); 
			fis.close();
			//1개의 파일이 끝남
		}
		// 다 끝났으면 zip파일 닫기
		zos.close();
		
		// 서버에서 압축작업이 완료되면, 압축파일을 클라이언트로 전송하고, 서버에 존재하는 압축파일을 삭제한다.
		/* 프로토콜형식에 맞도록 헤더에 정보를 제공해 준다. */
		String downLoadName = "";
		// 브라우저의 정보
		if(request.getHeader("user-agent").indexOf("MSIE") == -1) { //MSIE: 마이크로소프트 인터넷 익스플로어
			downLoadName = new String(zipName.getBytes("UTF-8"), "8859_1");  // 인터넷익스플로어가 아니면 UTF-8형식으로 해주세요.
		}
		else {
			downLoadName = new String(zipName.getBytes("EUC-KR"), "8859_1"); 
		}
		
		// 헤더에 정보를 첨부...    // 전부 예약어임.. (Content-Disposition , attachment;filename)
		response.setHeader("Content-Disposition", "attachment;filename="+downLoadName);
		/* ==================================================================================================== */
		
		// FileInputStream 서버 파일
		fis = new FileInputStream(zipPath+zipName);
		// 클라이언트로 보내기(전송)
		sos = response.getOutputStream();
		
		// 전송할 객체를 생성한 후, 실제 전송은 Byte단위로 처리한다.
		while((data = fis.read(b,0,b.length)) != -1) { // 2048바이트만큼 있으면 값저장
			sos.write(b,0,data); //데이터에 담은만큼 넣어주세요.
		}
		sos.flush(); // 혹시라도 찌꺼기(?)가 있으면 그것까지 담아줘.. (적어주는게 좋다고함)
		// 여기까지 다운로드가 완료된다.
		
		//IO에 관한건 강제로 닫아주는게 좋다.
		sos.close();
		fis.close();
		
		// 클라이언트에 압축파일을 전송 완료한다.
		
		// 서버에 존재하는 zip파일을 삭제처리한다.
		new File(zipPath+zipName).delete();
		
		// 다운로드 횟수 증가처리...
		dao.setPdsDownNumCheck(idx);
	}
}
