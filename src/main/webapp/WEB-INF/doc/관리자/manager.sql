DROP TABLE manager;
DROP TABLE manager CASCADE CONSTRAINTS;

CREATE TABLE manager(
		managerno                     		NUMBER(10)		 NOT NULL,
		id                            		VARCHAR2(20)	 NOT NULL UNIQUE,
		passwd                        		VARCHAR2(15)	 NOT NULL ,
		mname                         		VARCHAR2(20)	 NOT NULL ,
		mdate                         		DATE		     NOT NULL ,
		grade                         		NUMBER(2)		 NOT NULL,
        PRIMARY KEY (managerno)
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
  
-- CREATE
INSERT INTO manager(managerno, id, passwd, mname, mdate, grade)
VALUES(manager_seq.nextval, 'manager1', '1234', '관리자1', sysdate, 1);

INSERT INTO manager(managerno, id, passwd, mname, mdate, grade)
VALUES(manager_seq.nextval, 'manager2', '1234', '관리자2', sysdate, 1);

INSERT INTO manager(managerno, id, passwd, mname, mdate, grade)
VALUES(manager_seq.nextval, 'manager3', '1234', '관리자3', sysdate, 1);

-- READ
SELECT * FROM manager;
 MANAGERNO ID                   PASSWD          MNAME                MDATE         GRADE
---------- -------------------- --------------- -------------------- -------- ----------
         1 manager1             1234            관리자1              23/11/10          1
         2 manager2             1234            관리자2              23/11/10          1
         3 manager3             1234            관리자3              23/11/10          1
         
-- UPDATE
UPDATE manager
SET passwd = '123'
WHERE managerno = 3;
 MANAGERNO ID                   PASSWD          MNAME                MDATE         GRADE
---------- -------------------- --------------- -------------------- -------- ----------
         3 manager3             123             관리자3              23/11/10          1
         
-- DELETE
DELETE FROM manager
WHERE managerno = 10;

COMMIT;


commit;