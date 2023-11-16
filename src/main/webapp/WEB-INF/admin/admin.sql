/* 신고테이블(complaint   or   report 로 테이블명 사용하기) */
create table complaint (
	idx int not null auto_increment, /* 신고테이블 고유번호 */
	part varchar(15) not null, /* 신고항목(게시판:board, 자료실:pds , 방명록:guest) */
	partIdx int not null, /* 해당 항목 게시글의 고유번호 */
	cpMid varchar(30) not null, /* 신고자 아이디 */
	cpContent text not null, /* 신고 사유 */
	cpDate datetime default now(), /* 신고날짜 */
	
	primary key(idx)
);
desc complaint;

select *,timeStampDiff(hour,cpDate,now()) as hour_diff, datediff(cpDate,now()) as date_diff from complaint order by idx desc