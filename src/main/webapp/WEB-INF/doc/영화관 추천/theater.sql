CREATE TABLE theater(
		theaterno                     		NUMBER(10)		 NULL 		 PRIMARY KEY,
		memno                         		NUMBER(10)		 NOT NULL,
		ttitle                        		VARCHAR2(100)		 NULL ,
		tinfo                         		CHAR(200)		 NULL ,
		tdate                         		DATE		 NULL ,
		map                           		VARCHAR2(2000)		 NULL ,
  FOREIGN KEY (memno) REFERENCES MEM (memno)
);

COMMENT ON TABLE theater is '영화관 추천';
COMMENT ON COLUMN theater.theaterno is '영화관 번호';
COMMENT ON COLUMN theater.memno is '회원 번호';
COMMENT ON COLUMN theater.ttitle is '제목';
COMMENT ON COLUMN theater.tinfo is '내용';
COMMENT ON COLUMN theater.tdate is '날짜';
COMMENT ON COLUMN theater.map is '지도';