show tables;

create table pds(
	idx int not null auto_increment, /* 자료실 고유번호 */
	mid varchar(30) not null, /* 올린이 아이디 */	
	nickName varchar(30) not null, /* 올린이 닉네임 */
	fName varchar(200) not null, /* 업로드시의 파일명 */
	fSName varchar(200) not null, /* 실제 파일서버에 저장되는 파일명 */
	fSize int not null, /* 업로드된 파일의 총 사이즈 */
	title varchar(100) not null, /* 업로드파일의 제목 */
	part varchar(20) not null, /* 파일분류(학습/여행/음식/기타) */
	pwd varchar(100) not null, /* 비밀번호(sha256암호화 처리) */
	fDate datetime default now(), /* 파일 업로드한 날짜 */
	downNum int default 0, /* 파일 다운로드 횟수 */
	openSw char(6) default '공개', /* 파일 공개(비공개) 여부 */
	content text, /* 업로드 파일의 상세설명 */
	hostIp varchar(50) not null, /* 업로드시 클라이언트 IP주소 */
	primary key(idx)
);

desc pds;

select * from pds order by idx desc; 

select *,datediff(fDate,now()) as day_diff , timestampdiff(hour,fDate,now()) as hour_diff from pds order by idx desc limit 0,5