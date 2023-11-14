show tables;

/* 비밀번호를 넣어도 되고 안넣어도 됨.. (어짜피 회원제이기 때문에 본인이 로그인한 것으로 확인 가능하기 때문에(mid는 중복 불가기 때문) */
create table board(
	idx int not null auto_increment,/* 게시글의 고유번호 */
	mid varchar(30) not null,  		/* 게시글 올린이 아이디 */
	nickName varchar(30) not null,  /* 게시글 올린이 닉네임 */
	title varchar(100) not null,  	/* 게시글 제목 */
	email varchar(60),				/* 이메일 주소 */
	homePage varchar(60),			/* 홈페이지(개인블로그) 주소 */
	content text not null,  		/* 게시글 내용(본문) */
	readNum int not null default 0, /* 글 조회수 */
	hostIp varchar(40) not null,  	/* 글 올린이 hostIP */
	openSw char(2) default 'OK',  	/* 게시글의 공개여부(OK:공개 , NO:비공개) */
	wDate datetime default now(),  	/* 글 올린 날짜(시간) */
	good int default 0,  			/* '좋아요' 클릭 횟수 누적 */
	
	primary key(idx)
);

desc board;

/* 더미 데이터 */
insert into board values (default,'admin','관리자','게시판 서비스를 시작합니다.','axdc1234@naver.com','http://naver.com','게시할 내용들을 입력해주세요.',default,'192.168.50.61',default,default,default);

select * from board;