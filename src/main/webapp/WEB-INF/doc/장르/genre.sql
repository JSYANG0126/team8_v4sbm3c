DROP TABLE genre CASCADE CONSTRAINTS;

CREATE TABLE genre(
		genreno                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		name                          		VARCHAR2(30)		 NOT NULL,
		cnt                           		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		rdate                         		DATE		 NOT NULL,
		SEQNO                         		NUMBER(5)		 DEFAULT 1 NOT NULL,
		VISIBLE                       		CHAR(1)		 DEFAULT 'N' NOT NULL 
);

COMMENT ON TABLE genre is '장르';
COMMENT ON COLUMN genre.genreno is '장르번호';
COMMENT ON COLUMN genre.name is '장르이름';
COMMENT ON COLUMN genre.cnt is '관련자료수';
COMMENT ON COLUMN genre.rdate is '등록일';
COMMENT ON COLUMN genre.SEQNO is '출력순서';
COMMENT ON COLUMN genre.VISIBLE is '출력모드';

DROP SEQUENCE GENRE_SEQ;

CREATE SEQUENCE GENRE_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 99999            -- 최대값: 99999 --> NUMBER(5) 대응
  CACHE 2                   -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지
  
commit;

-- CREATE
INSERT INTO genre(genreno, name, cnt, rdate) VALUES(genre_seq.nextval, '영화1', 0, sysdate); 
INSERT INTO genre(genreno, name, cnt, rdate) VALUES(genre_seq.nextval, '영화2', 0, sysdate); 
INSERT INTO genre(genreno, name, cnt, rdate) VALUES(genre_seq.nextval, '영화3', 0, sysdate);

-- READ: LIST
SELECT * FROM genre;
SELECT genreno, name, cnt, rdate, seqno, visible FROM genre ORDER BY genreno ASC;
   GENRENO NAME                                  CNT RDATE         SEQNO V
---------- ------------------------------ ---------- -------- ---------- -
         1 영화1                                   0 23/11/10          1 N
         2 영화2                                   0 23/11/10          1 N
         3 영화3                                   0 23/11/10          1 N

-- UPDATE
UPDATE genre SET cnt=6 WHERE genreno=1;
SELECT * FROM genre WHERE genreno=3;
   GENRENO NAME                                  CNT RDATE         SEQNO V
---------- ------------------------------ ---------- -------- ---------- -
         3 영화4                                   1 23/11/10          1 N
        
        
-- DELETE
DELETE FROM genre WHERE genreno=7;
DELETE FROM genre WHERE genreno >= 10;

COMMIT;