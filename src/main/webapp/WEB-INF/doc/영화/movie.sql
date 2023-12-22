DROP TABLE movie CASCADE CONSTRAINTS; -- 자식 무시하고 삭제 가능
DROP TABLE movie;

CREATE TABLE movie(
		movieno                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		genreno                       		NUMBER(10)		 NOT NULL,
		managerno                     		NUMBER(10)		 NOT NULL,
		TITLE                         		VARCHAR2(300)		 NOT NULL,
		CONTENT                       		CLOB		 NOT NULL,
		RECOM                         		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		CNT                           		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		REPLYCNT                      		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		PASSWD                        		VARCHAR2(15)		 DEFAULT 0		 NOT NULL,
		WORD                          		VARCHAR2(300)		 NULL ,
		RDATE                         		DATE		 NOT NULL,
		FILE1                         		VARCHAR2(100)		 NULL ,
		FILE1SAVED                    		VARCHAR2(100)		 NULL ,
		THUMB1                        		VARCHAR2(100)		 NULL ,
		SIZE1                         		NUMBER(10)		 DEFAULT 0		 NULL ,
  FOREIGN KEY (genreno) REFERENCES genre (genreno),
  FOREIGN KEY (managerno) REFERENCES manager (managerno)
);

COMMENT ON TABLE movie is '영화';
COMMENT ON COLUMN movie.movieno is '영화리뷰번호';
COMMENT ON COLUMN movie.genreno is '장르번호';
COMMENT ON COLUMN movie.managerno is '관리자번호';
COMMENT ON COLUMN movie.TITLE is '제목';
COMMENT ON COLUMN movie.CONTENT is '내용';
COMMENT ON COLUMN movie.RECOM is '추천수';
COMMENT ON COLUMN movie.CNT is '조회수';
COMMENT ON COLUMN movie.REPLYCNT is '댓글수';
COMMENT ON COLUMN movie.PASSWD is '패스워드';
COMMENT ON COLUMN movie.WORD is '검색어';
COMMENT ON COLUMN movie.RDATE is '등록일';
COMMENT ON COLUMN movie.FILE1 is '메인이미지';
COMMENT ON COLUMN movie.FILE1SAVED is '실제 저장된 메인 이미지';
COMMENT ON COLUMN movie.THUMB1 is '메인 이미지 Preview';
COMMENT ON COLUMN movie.SIZE1 is '메인 이미지 크기';

DROP SEQUENCE MOVIE_SEQ;

CREATE SEQUENCE MOVIE_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 99999            -- 최대값: 99999 --> NUMBER(5) 대응
  CACHE 2                   -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지
  
commit;
  
-- CREATE
INSERT INTO movie(movieno, managerno, genreno, title, content, recom, cnt, replycnt, passwd, 
                     word, rdate, file1, file1saved, thumb1, size1)
VALUES(movie_seq.nextval, 1, 1, '엘리멘탈', '영화 엘리멘탈 리뷰', 0, 0, 0, '123',
       '애니메이션', sysdate, 'elemental.png', 'elemental_1.png', 'elemental_t.png', 1000);
       
INSERT INTO movie(movieno, managerno, genreno, title, content, recom, cnt, replycnt, passwd, 
                     word, rdate, file1, file1saved, thumb1, size1)
VALUES(movie_seq.nextval, 2, 1, '트랜스포머1', '영화 트랜스포머1 리뷰', 0, 0, 0, '123',
       'SF', sysdate, 'elemental.png', 'elemental_1.png', 'elemental_t.png', 1000);
       
INSERT INTO movie(movieno, managerno, genreno, title, content, recom, cnt, replycnt, passwd, 
                     word, rdate, file1, file1saved, thumb1, size1)
VALUES(movie_seq.nextval, 2, 1, '트랜스포머2', '영화 트랜스포머2 리뷰', 0, 0, 0, '123',
       'SF', sysdate, 'elemental.png', 'elemental_1.png', 'elemental_t.png', 1000);
       
-- READ
SELECT movieno, managerno, genreno, title, content, recom, cnt, replycnt, passwd, word, rdate,
           file1, file1saved, thumb1, size1
FROM movie
ORDER BY movieno DESC;

SELECT *
FROM movie

-- UPDATE
UPDATE movie
SET title = '트랜스포머3'
WHERE title = '트랜스포머2';

	   SELECT movieno, managerno, genreno, title, content, recom, cnt, replycnt, rdate, file1, file1saved, thumb1, size1, r
		FROM (
		           SELECT movieno, managerno, genreno, title, content, recom, cnt, replycnt, rdate, file1, file1saved, thumb1, size1, rownum as r
		           FROM (
		                     SELECT movieno, managerno, genreno, title, content, recom, cnt, replycnt, rdate, file1, file1saved, thumb1, size1
		                     FROM movie
		                     WHERE genreno=1 
                         ORDER BY recom DESC
		           )          
		)
		WHERE  r >= 1 AND r <= 5; 

-- UPDATE
UPDATE movie
SET recom = 3
WHERE movieno = 10;


UPDATE movie
SET cnt = cnt + 1
WHERE movieno = 13;

-- DELETE
DELETE FROM movie
WHERE movieno = 10;

commit;
