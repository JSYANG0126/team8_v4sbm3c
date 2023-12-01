DROP TABLE theater;

CREATE TABLE theater(
		theaterno                     		NUMBER(10)		 NULL 		 PRIMARY KEY,
		memno                         		NUMBER(10)		 NOT NULL,
		tname                        		VARCHAR2(30)		 NULL ,
		tinfo                         		CHAR(100)		 NULL ,
		tdate                         		DATE		 NULL ,
		map                           		VARCHAR2(2000)		 NULL ,
  FOREIGN KEY (memno) REFERENCES MEM (memno)
);

DROP SEQUENCE theater_seq;

CREATE SEQUENCE THEATER_SEQ
  START WITH 1         -- 시작 번호
  INCREMENT BY 1       -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2              -- 2번은 메모리에서만 계산
  NOCYCLE;             -- 다시 1부터 생성되는 것을 방지

COMMENT ON TABLE theater is '영화관 추천';
COMMENT ON COLUMN theater.theaterno is '영화관 번호';
COMMENT ON COLUMN theater.memno is '회원 번호';
COMMENT ON COLUMN theater.ttitle is '영화관 이름';
COMMENT ON COLUMN theater.tinfo is '영화관 설명';
COMMENT ON COLUMN theater.tdate is '날짜';
COMMENT ON COLUMN theater.map is '지도';

COMMIT;

INSERT INTO theater(theaterno, memno, tname, tinfo, tdate, map) 
VALUES(theater_seq.nextval, '1', 'abd' , 15,sysdate,0);  

-- READ: LIST
SELECT * FROM theater;
SELECT theaterno, memno, tname, tinfo, tdate, map FROM theater ORDER BY theaterno ASC;

-- READ
SELECT theaterno, memno, tname, tinfo, tdate, map FROM theater WHERE theaterno=1;

-- UPDATE
UPDATE theater SET tname='CGV 용산', tinfo='IMAX가 맛집인 영화관' WHERE theaterno=1;


-- DELETE
DELETE FROM theater WHERE theaterno=1;

COMMIT;
