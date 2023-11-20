package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.SecurityUtil;

public class MemberJoinOkCommand implements memberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사진 값을 가져오기 위해서  enctype="multipart/form-data" 이걸 사용하여 밑에와 같이 적을 수 없다.
//		String mid = request.getParameter("mid")== null ? "" : request.getParameter("mid");
//		String pwd = request.getParameter("pwd")== null ? "" : request.getParameter("pwd");
//		String nickName = request.getParameter("nickName")== null ? "" : request.getParameter("nickName");
//		String name = request.getParameter("name")== null ? "" : request.getParameter("name");
//		String gender = request.getParameter("gender")== null ? "" : request.getParameter("gender");
//		String birthday = request.getParameter("birthday")== null ? "" : request.getParameter("birthday");
//		String tel = request.getParameter("tel")== null ? "" : request.getParameter("tel");
//		String address = request.getParameter("address")== null ? "" : request.getParameter("address");
//		String email = request.getParameter("email")== null ? "" : request.getParameter("email");
//		String homePage = request.getParameter("homePage")== null ? "" : request.getParameter("homePage");
//		String job = request.getParameter("job")== null ? "" : request.getParameter("job");
//		String content = request.getParameter("content")== null ? "" : request.getParameter("content");
//		String userInfor = request.getParameter("userInfor")== null ? "" : request.getParameter("userInfor");
		
		// 사진파일에 저장하기 위해서 만듦 (파일주소 , 파일최대크기, 인코딩방식)
		String realPath = request.getServletContext().getRealPath("/images/member");
		int maxSize = 1024*1024*5;
		String encoding = "UTF-8";
		
		MultipartRequest multipartRequest = new MultipartRequest(request, realPath,maxSize,encoding,new DefaultFileRenamePolicy());
		
		multipartRequest.getOriginalFileName("fName"); //오리지널 이름
		String  filesystemName =multipartRequest.getFilesystemName("fName")== null ? "noimage.jpg" : multipartRequest.getFilesystemName("fName"); // 서버 파일 이름
		// 사진 파일에 저장
		
		// request가 아닌, multipartRequest로 적어주어야 넘긴 값을 받을 수 있음 (enctype="multipart/form-data" 이걸 사용하여 request로 적으면 값을 가져올 수 없음.)
		String mid = multipartRequest.getParameter("mid")== null ? "" : multipartRequest.getParameter("mid");
		String pwd = multipartRequest.getParameter("pwd")== null ? "" : multipartRequest.getParameter("pwd");
		String nickName = multipartRequest.getParameter("nickName")== null ? "" : multipartRequest.getParameter("nickName");
		String name = multipartRequest.getParameter("name")== null ? "" : multipartRequest.getParameter("name");
		String gender = multipartRequest.getParameter("gender")== null ? "" : multipartRequest.getParameter("gender");
		String birthday = multipartRequest.getParameter("birthday")== null ? "" : multipartRequest.getParameter("birthday");
		String tel = multipartRequest.getParameter("tel")== null ? "" : multipartRequest.getParameter("tel");
		String address = multipartRequest.getParameter("address")== null ? "" : multipartRequest.getParameter("address");
		String email = multipartRequest.getParameter("email")== null ? "" : multipartRequest.getParameter("email");
		String homePage = multipartRequest.getParameter("homePage")== null ? "" : multipartRequest.getParameter("homePage");
		String job = multipartRequest.getParameter("job")== null ? "" : multipartRequest.getParameter("job");
		String content = multipartRequest.getParameter("content")== null ? "" : multipartRequest.getParameter("content");
		String userInfor = multipartRequest.getParameter("userInfor")== null ? "" : multipartRequest.getParameter("userInfor");
		
		
		// 취미 전송에 대한 처리(여러개가 올 수 있기에 배열로 처리)
		String[] hobbys = multipartRequest.getParameterValues("hobby");
		String hobby = "";
		if(hobbys.length != 0) {
			for(String h : hobbys) {
				hobby += h + "/";
			}
		}
		hobby.substring(0, hobby.lastIndexOf("/"));
		
		// Back End 체크... (DB에 저장된 자료들 중에서 Null값과 길이에 대한 체크... 중복체크(아이디,닉네임)..)
		
		// 아이디/닉네임 중복체크
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.getMemberMidCheck(mid);
		if(vo.getMid() != null) {
			request.setAttribute("msg", "이미 사용중인 아이디 입니다.");
			request.setAttribute("url", "memberJoin.mem");
			return;   //return 반드시 적어줘야 함.
		}
		vo = dao.getMemberNickCheck(nickName);
		if(vo.getNickName() != null) {
			request.setAttribute("msg", "이미 사용중인 닉네임 입니다.");
			request.setAttribute("url", "memberJoin.mem");
			return;   //return 반드시 적어줘야 함.
		}
		
		// 비밀번호 암호화처리(sha256방식)
		SecurityUtil security = new SecurityUtil();
		pwd = security.encryptSHA256(pwd);
		
		// 체크가 모두 끝난 자료들을 VO에 담아서 DB에 저장시켜준다.
		vo = new MemberVO();
		vo.setMid(mid);
		vo.setPwd(pwd);
		vo.setNickName(nickName);
		vo.setName(name);
		vo.setGender(gender);
		vo.setBirthday(birthday);
		vo.setTel(tel);
		vo.setAddress(address);
		vo.setEmail(email);
		vo.setHomePage(homePage);
		vo.setJob(job);
		vo.setHobby(hobby);
		vo.setPhoto(filesystemName);
		vo.setContent(content);
		vo.setUserInfor(userInfor);
		
		int res = dao.setMemberJoinOk(vo);
		
		if(res != 0) {
			request.setAttribute("msg", "회원에 가입되셨습니다.\\n 다시 로그인해 주세요.");
			request.setAttribute("url", "memberLogin.mem");
		}
		else {
			request.setAttribute("msg", "회원에 가입실패.");
			request.setAttribute("url", "memberJoin.mem");
			
		}
		
	}

}
