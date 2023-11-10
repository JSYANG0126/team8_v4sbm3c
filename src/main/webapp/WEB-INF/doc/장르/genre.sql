CREATE TABLE genre(
		genreno                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		name                          		VARCHAR2(30)		 NOT NULL,
		cnt                           		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		rdate                         		DATE		 NOT NULL,
		SEQNO                         		NUMBER(5)		 NULL ,
		VISIBLE                       		CHAR(1)		 NULL 
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