show tables;

create table crime(
	idx int not null auto_increment primary key,
	year int,						/* 강력범죄 발생년도 */
	police varchar(20) not null, 	/* 경찰서명 */
	robbery int,					/* 강도 건수 */
	murder int,						/* 살인 건수 */
	theft int,						/* 절도 건수 */
	violence int					/* 폭력 건수 */
);

desc crime

select * from crime;

update crime set year=2015;

select *,year,count(*) from crime group by year;
select *,year,count(*) from crime where year=2016;
select *,year count(*) from crime group by year having year=2016;

select *,year from crime where year=2022 and police like '%충북%' group by year, police having police='충북청주상당서' order by year;

-- 합계와 평균을 같이 구한 sql문
select year,sum(robbery)as totRobbery,sum(murder)as totMurder,sum(theft)as totTheft,sum(violence)as totViolence,
	avg(robbery)as avgRobbery,avg(murder)as avgMurder,avg(theft)as avgTheft,avg(violence)as avgViolence
	 from crime where year=2022;

-- 년도별 범죄 건수 (crime에 있는 전체를 보겠다.. year(년도)별로 묶어서...)
select * from crime group by year;

-- 충북에서 일어난 범죄 건수 년도별로 묶어서 출력
select * from crime where police like '%충북%' group by year;