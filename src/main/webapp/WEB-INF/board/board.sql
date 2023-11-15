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

select * from board order by idx desc limit 0,10;
select *,dateDiff(wDate,now()) from board order by idx desc limit 0,10;
/* new.gif를 24시간동안만 보여주기위한 처리 */
select *,timeStampDiff(hour,wDate,now()) from board order by idx desc limit 0,10;  /* hour로하면 시간으로 본다.. */

/* 이전글,다음글을 꺼내오기 위한 처리 */
select * from board where idx= 5;  /* 현재글 가져오기 */
select * from board where idx < 5 order by idx desc limit 1; /* 이전글 가져오기 */
select * from board where idx > 5 order by idx limit 1; /* 다음글 가져오기 */

select * from board;

/* 날짜 함수 연습 */
select now();
/* date_add() datetime형식 비교 */

/* 오늘보다 하루 앞 날짜 보기 */
select now() as 오늘날짜, date_add(now(),interval 1 day); 
/* 오늘보다 하루 뺀 날짜 보기 */
select now() as 오늘날짜, date_add(now(),interval -1 day);
/* 현재시간 기준 10시간 앞 */
select now() as 오늘날짜, date_add(now(),interval 10 hour);
/* 현재시간 기준 10시간 전 */
select now() as 오늘날짜, date_add(now(),interval -10 hour) as preHour;

/* date_sub() */

/* 오늘보다 하루 뺀 날짜 보기 */
select now() as 오늘날짜, date_sub(now(),interval 1 day); 
/* 오늘보다 하루 앞 날짜 보기 */
select now() as 오늘날짜, date_sub(now(),interval -1 day);

/* board 테이블에 적용 */
-- 게시글중에서 하루전에 올라온 글만 보여주시오.
select substring(wDate,1,10), substring(date_add(now(),interval -1 day), 1 , 10) from board;
select idx,nickName,wDate from board;
select idx,nickName,wDate from board where substring(wDate,1,10) = substring(date_add(now(),interval -1 day), 1 , 10);

/* 날짜차이 계산 : DATEDIFF(시작날짜, 마지막날짜) - int형식 비교 */
select dateDiff('2023-11-14',now()); /* 2023-11-15일 기준 하루차이가 나서 -1이 나옴 */
select dateDiff(now(), wDate) from board;

select timestampDiff(hour, now(), wDate) from board;
select timestampDiff(day, wDate, now()) from board;

/* 날짜형식(date_format(날짜형식,포멧)) : 년도4자리(%Y),월(%m), 일(%d), 시간(%H), 분(%i) 초(%s) */
select wDate, date_format(wDate, '%Y-%m-%d') from board;
select wDate, date_format(wDate, '%Y-%m-%d %H:%i:%s') from board;
select wDate, date_format(wDate, '%Y-%m-%d %H:%i:%s') from board where substring(wDate,1,10) = substring(date_add(now(),interval -1 day), 1 , 10);
