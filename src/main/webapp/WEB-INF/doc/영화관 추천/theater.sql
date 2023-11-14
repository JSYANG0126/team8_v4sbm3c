CREATE TABLE theater(
		theaterno                     		NUMBER(10)		 NULL 		 PRIMARY KEY,
		memno                         		NUMBER(10)		 NOT NULL,
		ttitle                        		VARCHAR2(100)		 NULL ,
		tinfo                         		CHAR(200)		 NULL ,
		tdate                         		DATE		 NULL ,
		map                           		VARCHAR2(2000)		 NULL ,
  FOREIGN KEY (memno) REFERENCES MEM (memno)
);

CREATE SEQUENCE THEATER_SEQ
  START WITH 1         -- 시작 번호
  INCREMENT BY 1       -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2              -- 2번은 메모리에서만 계산
  NOCYCLE;             -- 다시 1부터 생성되는 것을 방지

COMMENT ON TABLE theater is '영화관 추천';
COMMENT ON COLUMN theater.theaterno is '영화관 번호';
COMMENT ON COLUMN theater.memno is '회원 번호';
COMMENT ON COLUMN theater.ttitle is '제목';
COMMENT ON COLUMN theater.tinfo is '내용';
COMMENT ON COLUMN theater.tdate is '날짜';
COMMENT ON COLUMN theater.map is '지도';

INSERT INTO theater(theaterno, memno, ttitle, tinfo, tdate, map) 
VALUES(theater_seq.nextval, '1', 'abd' , 15);  

-- READ: LIST
SELECT * FROM theater;
SELECT theaterno, memno, ttitle, tinfo, tdate, map FROM theater ORDER BY theaterno ASC;

-- READ
SELECT theaterno, memno, ttitle, tinfo, tdate, map FROM theater WHERE theaterno=1;

-- UPDATE
UPDATE theater SET ttitle='고길동', tinfo='dvc' WHERE theaterno=1;


-- DELETE
DELETE FROM theater WHERE theaterno=1;

COMMIT;
