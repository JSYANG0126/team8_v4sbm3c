DROP TABLE REVIEW;
DROP SEQUENCE review_seq;
CREATE TABLE review(
		reviewno                      		NUMBER(10)		 NULL 		 PRIMARY KEY,
		memno                         		NUMBER(10)		 NOT NULL,
        retitle                       		VARCHAR2(30)		 NOT NULL,
		movieinfo                       		VARCHAR2(200)		 NOT NULL,
		redate                        		DATE		 NULL ,	
		word                          		CHAR(20)		 NULL ,
		pwd                           		VARCHAR2(15)		 NULL ,
		mainimg                       		VARCHAR2(200)		 NULL ,
		savemainimg                   		VARCHAR2(200)		 NULL ,
		lookimg                       		VARCHAR2(200)		 NULL ,
  FOREIGN KEY (memno) REFERENCES MEM (memno)
);

COMMENT ON TABLE review is '평가 게시판';
COMMENT ON COLUMN review.reviewno is '평가번호';
COMMENT ON COLUMN review.memno is '회원 번호';
COMMENT ON COLUMN review.retitle is '제목';
COMMENT ON COLUMN review.movieinfo is '내용';
COMMENT ON COLUMN review.redate is '날짜';
COMMENT ON COLUMN review.word is '검색단어';
COMMENT ON COLUMN review.pwd is '비밀번호';
COMMENT ON COLUMN review.mainimg is '메인이미지';
COMMENT ON COLUMN review.savemainimg is '저장된 이미지';
COMMENT ON COLUMN review.lookimg is '이미지썸네일';

CREATE SEQUENCE review_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 999999999
    CACHE 2
    NOCYCLE;
    
COMMIT;  

-- 등록
INSERT INTO review(reviewno, memno, retitle, movieinfo, redate, word, pwd, mainimg, savemainimg, lookimg) VALUES (review_seq.nextval, 1, '인터스텔라',  'SF', sysdate, '재ㅔ밌다','1234', 'photo1.jpg', 'photo1_t.jpg', 'photo1_m.jpg');

-- Read : List
SELECT * FROM review;
SELECT reviewno, memno, retitle, movieinfo, redate, word, pwd, mainimg, savemainimg, lookimg FROM review ORDER BY reviewno ASC;

-- Update
UPDATE review SET retitle='마션' WHERE reviewno=2;
SELECT reviewno, memno, retitle, movieinfo, redate, word, pwd, mainimg, savemainimg, lookimg FROM review ORDER BY reviewno ASC;

-- Delete
DELETE FROM review WHERE reviewno=2;
SELECT reviewno, memno, retitle, movieinfo, redate, word, pwd, mainimg, savemainimg, lookimg FROM review ORDER BY reviewno ASC;