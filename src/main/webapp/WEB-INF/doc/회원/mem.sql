DROP TABLE mem;
DROP TABLE mem CASCADE CONSTRAINTS;

CREATE TABLE mem(
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

COMMENT ON TABLE mem is '회원';
COMMENT ON COLUMN mem.memno is '회원 번호';
COMMENT ON COLUMN mem.ID is '아이디';
COMMENT ON COLUMN mem.PASSWD is '패스워드';
COMMENT ON COLUMN mem.MNAME is '성명';
COMMENT ON COLUMN mem.TEL is '전화번호';
COMMENT ON COLUMN mem.ZIPCODE is '우편번호';
COMMENT ON COLUMN mem.ADDRESS1 is '주소1';
COMMENT ON COLUMN mem.ADDRESS2 is '주소2';
COMMENT ON COLUMN mem.MDATE is '가입일';
COMMENT ON COLUMN mem.GRADE is '등급';
COMMENT ON COLUMN mem.qnano is '질문번호';

DROP SEQUENCE MEM_SEQ;

CREATE SEQUENCE MEM_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 99999            -- 최대값: 99999 --> NUMBER(5) 대응
  CACHE 2                   -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지
  
-- CREATE
-- 회원 관리용 계정, Q/A 용 계정
INSERT INTO mem(memno, id, passwd, mname, tel, zipcode, address1, address2, mdate, grade)
VALUES (mem_seq.nextval, 'commentsmanager', '1234', '댓글관리자', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate, 1);
 
INSERT INTO mem(memno, id, passwd, mname, tel, zipcode, address1, address2, mdate, grade)
VALUES (mem_seq.nextval, 'qnamanager', '1234', '질문관리자', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate, 1);
 
-- 개인 회원 테스트 계정
INSERT INTO mem(memno, id, passwd, mname, tel, zipcode, address1, address2, mdate, grade)
VALUES (mem_seq.nextval, 'user1@gmail.com', '1234', '왕눈이', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate, 15);

-- READ
SELECT * FROM mem;
     MEMNO ID                             PASSWD                                                       MNAME                          TEL            ZIPCO ADDRESS1                                                                         ADDRESS2                                           MDATE                    GRADE
---------- ------------------------------ ------------------------------------------------------------ ------------------------------ -------------- ----- -------------------------------------------------------------------------------- -------------------------------------------------- ------------------- ----------
         1 qnaadmin                       1234                                                         질문답변관리자                 000-0000-0000  12345 서울시 종로구                                                                    관철동                                             2023-11-09 05:04;46          1
         2 crm                            1234                                                         고객관리자                     000-0000-0000  12345 서울시 종로구                                                                    관철동                                             2023-11-09 05:04;46          1
         3 user1@gmail.com                1234                                                         왕눈이                         000-0000-0000  12345 서울시 종로구                                                                    관철동                                             2023-11-14 11:29;02         15


-- UPDATE
UPDATE mem
SET grade = 2
WHERE mname = '질문관리자';
     MEMNO ID                             PASSWD                                                       MNAME                          TEL            ZIPCO ADDRESS1                                                                         ADDRESS2                                           MDATE                    GRADE
---------- ------------------------------ ------------------------------------------------------------ ------------------------------ -------------- ----- -------------------------------------------------------------------------------- -------------------------------------------------- ------------------- ----------
         1 qnaadmin                       1234                                                         질문답변관리자                 000-0000-0000  12345 서울시 종로구                                                                    관철동                                             2023-11-09 05:04;46          1
         2 crm                            1234                                                         고객관리자                     000-0000-0000  12345 서울시 종로구                                                                    관철동                                             2023-11-09 05:04;46          1
         3 user1@gmail.com                123                                                          왕눈이                         000-0000-0000  12345 서울시 종로구                                                                    관철동                                             2023-11-14 11:29;02         15

--DELETE
delete from mem
where memno = 1;

SELECT memno, id, passwd, mname, tel, zipcode, address1, address2, mdate, grade
FROM mem
WHERE mname = '홍길동' AND grade = 99;

commit;