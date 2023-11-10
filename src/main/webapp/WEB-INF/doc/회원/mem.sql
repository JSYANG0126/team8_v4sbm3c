CREATE TABLE MEM(
		memno                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		ID                            		VARCHAR2(30)		 NOT NULL,
		PASSWD                        		VARCHAR2(60)		 NOT NULL,
		MNAME                         		VARCHAR2(30)		 NOT NULL,
		TEL                           		VARCHAR2(14)		 NOT NULL,
		ZIPCODE                       		VARCHAR2(5)		 NULL ,
		ADDRESS1                      		VARCHAR2(80)		 NULL ,
		ADDRESS2                      		VARCHAR2(50)		 NULL ,
		MDATE                         		DATE		 NOT NULL,
		GRADE                         		NUMBER(2)		 NOT NULL,
		qnano                         		NUMBER(10)		 NULL 
);

COMMENT ON TABLE MEM is '회원';
COMMENT ON COLUMN MEM.memno is '회원 번호';
COMMENT ON COLUMN MEM.ID is '아이디';
COMMENT ON COLUMN MEM.PASSWD is '패스워드';
COMMENT ON COLUMN MEM.MNAME is '성명';
COMMENT ON COLUMN MEM.TEL is '전화번호';
COMMENT ON COLUMN MEM.ZIPCODE is '우편번호';
COMMENT ON COLUMN MEM.ADDRESS1 is '주소1';
COMMENT ON COLUMN MEM.ADDRESS2 is '주소2';
COMMENT ON COLUMN MEM.MDATE is '가입일';
COMMENT ON COLUMN MEM.GRADE is '등급';
COMMENT ON COLUMN MEM.qnano is '질문번호';

DROP SEQUENCE MEM_SEQ;

CREATE SEQUENCE MEM_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 99999            -- 최대값: 99999 --> NUMBER(5) 대응
  CACHE 2                   -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지
  
commit;