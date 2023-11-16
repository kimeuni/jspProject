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

/* 게시판에 닷글 달기 */
create table boardReply(
	idx int not null auto_increment,  /* 댓글의 고유번호 */
	boardIdx int not null, 			  /* 원본글(부모글/들어간 게시판의 idx)의 고유번호(외래키로 설정) */
	mid varchar(30) not null,		  /* 닉네임은 바뀌지만, 아이디를 바뀌지 않기에 내가쓴 댓글을 보려면 mid를 저장한다. */
	nickName varchar(30) not null, 	  /* 댓글 올린이의 닉네임 */
	wDate datetime default now(), 	  /* 댓글 작성한 날짜 */
	hostIp varchar(50) not null, 	  /* 댓글 올린 PC의 고유 hostIp */
	content text not null, 			  /* 댓글 내용 */
	
	primary key(idx),
	foreign key(boardIdx) references board (idx)
	on update cascade  /* 부모필드 수정하면 함께 영향을 받는다. */
	on delete restrict /* 부모필드를 함부로 삭제할 수 없다. */
);

desc boardReply;

insert into boardReply values(default,11,'kms1234','말쑥',default,'210.100.20.25','댓글 연습...입니..다...');
insert into boardReply values(default,11,'hkd1234','홍장군',default,'210.130.25.2','좋은 글 감사합니다.');

select * from boardReply;


-- JOIN 이용하여 board idx와 boardReply의 boardIdx가 같으면 보여주도록 처리 (+ board는 모두 출력 and boardReply는 nickName만 출력)
select b.*,br.nickName from board b, boardReply br where b.idx = br.boardIdx;
-- 
select b.*,br.nickName,br.boardIdx from board b, boardReply br where b.idx =(select boardIdx from boardReply where boardIdx=11 limit 1);
-- 서브쿼리 사용   			boardIdx(boardReply)와 idx(board) 같을 시 그것만 출력
select b.*,br.nickName,br.boardIdx from board b, (select * from boardReply where boardIdx=11) br where b.idx =11; 

/*------------------------------------------------------------------------------------------------------*/

-- 댓글 수 연습...
-- 게시판(board)리스트 화면에서 글제목옆에 해당글의 댓글(boardReply)수를 출력해보자..
-- 전체 board테이블의 내용을 최신순으로 출력?
select * from board order by idx desc;

-- board테이블 고유번호 18번에 해당하는 댓글테이블의 댓글수는?
select Count(*) from boardReply where boardIdx=11;

-- 앞의 예에서 원본글의 고유번호(11)와 함께, 총 댓글의 갯수는 replyCnt 란 변수로 출력하시오/
select boardIdx,Count(*) as replyCnt from boardReply where boardIdx=11;

-- 앞의 예제에 이어서, 원본글을 쓴 닉네임도 함께 출력시켜보자. (여기서 닉네임은 부모테이블에서 가져와서 출력한다.)  / 서브쿼리
select boardIdx,Count(*) as replyCnt, (select nickName from board where idx=11) as nickName from boardReply where boardIdx=11;

-- 앞의 내용을 부모관점(board)에서 처리..
-- 18번 게시글의 mid와 닉네임을 출력
select mid,nickName from board where idx = 11;

-- 앞에 이어서 닉네임을 자식(댓글테이블)에서 가져와서 보여주시오. (limit 1를 설정한 이유.. board테이블의 mid는 1개인데, boardReply(댓글 여러개 달아서)에 나오는 nickName은 여러개이기 때문에 1개만 출력되도록 하였다.)
select mid,(select nickName from boardReply where boardIdx=11 limit 1) as ReplyNick from board where idx = 11;

-- 부모글(원본 게시글)에 해당하는 자식글(댓글)의 갯수를 부모글과 함께 출력하시오.
select idx, (select count(*) from boardReply where boardIdx=11) as replyCnt from board where idx=11;
-- 부모글(원본 게시글) 모두 출력 및 부모글에 해당하는 댓글 수 출력.
select *, (select count(*) from boardReply where boardIdx=11) as replyCnt from board where idx=11;

/*------------------------------------------------------------------------------------------------------*/


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
