show tables;
/* 학과(hakkwa) 테이블 */
create table hakkwa (
	code int not null primary key, /* 학과 코드 */
	name varchar(20) /* 학과명 */
);

desc hakkwa

drop table hakkwa;

-- unique key로 name을 추가하시오.
alter table hakkwa add unique key (name);

insert into hakkwa values(101,'컴퓨터공학과');
insert into hakkwa values(102,'멀티미디어과');
insert into hakkwa values(103,'경영학과');
insert into hakkwa values(104,'생물학과');
insert into hakkwa values(105,'기계공학과');
insert into hakkwa values(106,'영문학과');
insert into hakkwa values(107,'일본학과');

delete from hakkwa where code = 106;  /* 삭제 가능 */
delete from hakkwa where code = 101;  /* student에서 외래키로 사용중이기 때문에 삭제 불가능 (on delete set null이 적혀있으면 삭제 가능 대신 값이 null로 바뀜) */
update hakkwa set code = 105 where code = 101; /* 101번을 105번으로 바꾸기 == > 안됨 primary로 지정되어있기 때문에 기존에 101이 있어서 불가능 */
update hakkwa set code = 107 where code = 103; /* on update cascade 적혀 있으면 변경 가능 */
update hakkwa set code = 108 where code = 101; /* 변경 불가능 ==> 데이터에 101학번 코드를 가지고 있기 때문에 불가능... */ 

select * from hakkwa;

drop table hakkwa;

/* 학생 정보 테이블 */
create table student (
	st_code int not null auto_increment primary key, /* 학생 고유 번호 */
	name varchar(20) not null, /* 학생 성명 */
	hakkwa_code int,
	age int default 20,  /* 학생 나이 */
	foreign key(hakkwa_code) references hakkwa (code) /* 외래키 설정 => hakkwa 테이블에 있는 학과 코드 */
	on update cascade  /* 관련된 정보가 바뀌면 내것도 바꿔줘 라는 의미의 명령어 */
	on delete set null /* 앞에서 데이터가 지워져도 외래키의 값은 null로 들어간다. */
);

desc student;

insert into student values (default, '홍길동',101,default);
insert into student values (default, '김말숙',101,default);
insert into student values (default, '이기자',102,default);
insert into student values (default, '오하늘',102,default);
insert into student values (default, '고인돌',103,default);
insert into student values (default, '강하늘',103,default);
insert into student values (default, '배서현',104,default);
insert into student values (default, '신성우',104,default);
insert into student values (default, '박정환',105,default);
insert into student values (default, '유승선',105,default);
insert into student values (default, '김은이',106,default);
insert into student values (default, '허성진',106,default);
insert into student values (default, '연습이',107,default); 
/* 107번 필드값이 hakkwa에 없으면 오류가 뜬다. */

select * from student;

drop table student;

-- 현재 시스템(존재하는 모든 Database)의 모든 제약조건을 확인?
select * from information_schema.table_constraints;
select * from information_schema.table_constraints where constraint_schema='javaProject';
select * from information_schema.table_constraints where constraint_schema='javaProject' and table_name = 'member';

/* ------------- 조인(관련된 테이블간의 정보를 검색 처리할때 사용...) ------------------ */
-- 일반조인(Inner Join) : 각 테이블에는 서로 매칭이 되는 필드가 포함되어 있어야 한다.
select * from hakkwa;
select * from student;

/* 학과와 관련된 애를 보고 싶을때는 학과를 기준으로 join을 사용 */
select * from hakkwa Inner Join student;  /* 전체보기 */
/* hakkwa와 student를 Inner Join하겠다. */

select * from hakkwa Inner Join student on hakkwa.code = student.hakkwa_code;  /* Join에서의 where명령어는 on 이다.  hakkwa.code와 hakkwa_code가 일치되는 것만 보여줘 */ 
select * from hakkwa Join student on hakkwa.code = student.hakkwa_code; 
select * from hakkwa as h Inner Join student as s on h.code = s.hakkwa_code; 
select * from hakkwa as h, student as s where h.code = s.hakkwa_code; /* join이 안들어가면 where사용 가능 대신 as h(변수명 준거에) ,(쉼표)를 줘야한다. (원래는 inner Join으로 hakkwa와 student를 가르고 있었음)  */

/* 내부조인 : LEFT JOIN, RIGHT JOIN */
-- LEFT JOIN(왼쪽 기준) : 왼쪽 자료를 모두 가져오고 오른쪽은 만족하는것만 가져온다. 없으면 NULL로 채운다.
select * from hakkwa h left join student s on h.code = s.hakkwa_code;

-- RIGHT JOIN(오른쪽 기준) : 오른쪽 자료를 모두 가져오고 오른쪽은 만족하는것만 가져온다. 없으면 NULL로 채운다.
select * from hakkwa h right join student s on h.code = s.hakkwa_code;

/* Cross Join(곱집합) : 모든행의 자료들을 다 가져온다. */
select * from hakkwa, student;

/* 합집합 : Full outer Join(Mysql에는 없음), Mysql에서는 UNION명령을 사용한다. */
select * from hakkwa h union select hakkwa_code,name from student s;










/* 연습자료 */
create table student2 (
	st_code int not null auto_increment primary key, /* 학생 고유 번호 */
	name varchar(20) not null, /* 학생 성명 */
	hakkwa_code int
);

insert into student2 values(default,'가가가',100);
insert into student2 values(default,'나나나',200);
insert into student2 values(default,'다다다',300);
insert into student2 values(default,'라라라',400);
insert into student2 values (default, '홍길동',101);

select * from student2;

/* 합집합 */
select name,hakkwa_code from student union select name,hakkwa_code from student2;

/* 응용 */
select * from student2 s2, student s1
select * from student2 s2, student s1 where s2.name = s1.name;  /* s2에 있는 name과 s1에 이는 name이 같은게 있으면 보여줘 */
select s1.hakkwa_code,s2.name from student2 s2, student s1 where s2.name = s1.name; 
select s1.hakkwa_code,h.name,s2.name from student2 s2, student s1, hakkwa h where s2.name = s1.name and s1.hakkwa_code =h.code; /* 101번이 무슨 학과인지... (테이블3개 join) */
