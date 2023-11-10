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

DROP SEQUENCE MANAGER_SEQ;

CREATE SEQUENCE MANAGER_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 99999            -- 최대값: 99999 --> NUMBER(5) 대응
  CACHE 2                   -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지
  
commit;