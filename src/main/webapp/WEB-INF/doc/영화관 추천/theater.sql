DROP TABLE theater;

CREATE TABLE theater(
		theaterno                     		NUMBER(10)		 NULL 		 PRIMARY KEY,
		memno                         		NUMBER(10)		 NOT NULL,
		tname                        		VARCHAR2(100)		 NULL ,
		tinfo                         		CHAR(300)		 NULL ,
        WORD                          		VARCHAR2(300)		 NULL ,
		tdate                         		DATE		 NULL ,	
        IMG1                         		VARCHAR2(100)		 NULL ,
		IMG1SAVED                    		VARCHAR2(100)		 NULL ,
		THUMBIMG1                        	VARCHAR2(300)		 NULL ,
		SIZE1                         	    NUMBER(10)		 DEFAULT 0		 NULL ,
        SIZE1_LABEL                         NUMBER(10)		 DEFAULT 0		 NULL ,
        passwd                             VARCHAR2(100)		 DEFAULT 0		 NULL ,
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
COMMENT ON COLUMN theater.tname is '영화관 이름';
COMMENT ON COLUMN theater.tinfo is '영화관 설명';
COMMENT ON COLUMN theater.word is '검색어';
COMMENT ON COLUMN theater.tdate is '날짜';
COMMENT ON COLUMN theater.img1 is '메인이미지';
COMMENT ON COLUMN theater.img1saved is '실제 저장된 메인 이미지';
COMMENT ON COLUMN theater.thumbimg1 is '메인 이미지 Preview';
COMMENT ON COLUMN theater.size1 is '메인 이미지 크기';
COMMENT ON COLUMN theater.size1_label is '메인 이미지 용량';
COMMENT ON COLUMN theater.passwd is '메인 이미지 용량';
COMMENT ON COLUMN theater.map is '지도';

COMMIT;

INSERT INTO theater(theaterno, memno, tname, tinfo, tdate) 
VALUES(theater_seq.nextval, '1', 'cgv' , 15,sysdate);  

-- READ: LIST
SELECT * FROM theater;

SELECT theaterno, memno, tname, tinfo, word, tdate, img1, img1saved, thumbimg1, imgsize1, map 
FROM theater 
ORDER BY theaterno ASC;

SELECT t.theaterno, t.memno, t.tname, t.tinfo, t.word, t.tdate, t.img1, t.img1saved, t.thumbimg1, t.size1, t.size1_label, t.passwd, t.map, m.mname
      FROM theater t
      LEFT JOIN mem m ON t.memno = m.memno
      
SELECT theaterno, memno, tname, tinfo, word, tdate, img1, img1saved, thumbimg1, size1, size1_label, passwd, map, mname
    FROM (
      SELECT t.theaterno, t.memno, t.tname, t.tinfo, t.word, t.tdate, t.img1, t.img1saved, t.thumbimg1, t.size1, t.size1_label, t.passwd, t.map, m.mname
      FROM theater t
      LEFT JOIN mem m ON t.memno = m.memno
      WHERE t.memno = 3
    )
WHERE ROWNUM <= 1;

-- READ
SELECT theaterno, memno, tname, tinfo, tdate, map FROM theater WHERE theaterno=1;

-- UPDATE
UPDATE theater SET tname='CGV 용산', tinfo='IMAX가 맛집인 영화관' WHERE theaterno=1;


-- DELETE
DELETE FROM theater WHERE theaterno=1;

COMMIT;
