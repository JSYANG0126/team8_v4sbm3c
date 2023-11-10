CREATE TABLE manager(
		managerno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		id                            		VARCHAR2(20)		 NULL ,
		passwd                        		VARCHAR2(15)		 NULL ,
		mname                         		VARCHAR2(20)		 NULL ,
		mdate                         		DATE		 NULL ,
		grade                         		NUMBER(2)		 NULL 
);

COMMENT ON TABLE manager is '관리자';
COMMENT ON COLUMN manager.managerno is '관리자번호';
COMMENT ON COLUMN manager.id is '아이디';
COMMENT ON COLUMN manager.passwd is '패스워드';
COMMENT ON COLUMN manager.mname is '성명';
COMMENT ON COLUMN manager.mdate is '가입일';
COMMENT ON COLUMN manager.grade is '등급';