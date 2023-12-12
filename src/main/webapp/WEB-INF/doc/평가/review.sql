DROP TABLE REVIEW;
DROP SEQUENCE review_seq;

CREATE TABLE review(
		reviewno                      		NUMBER(10)		 NULL 		 PRIMARY KEY,
		memno                         		NUMBER(10)		 NOT NULL,
        rtitle                       		VARCHAR2(30)	 NOT NULL,
		rinfo                       	VARCHAR2(1000)	 NOT NULL,
		rdate                        		DATE		     NULL ,	
		rname                          	VARCHAR2(30)    NULL ,
		word                          		VARCHAR2(100)	 NULL ,
		FILE1                         		VARCHAR2(100)		 NULL ,
		FILE1SAVED                    		VARCHAR2(100)		 NULL ,
		THUMB1                        		VARCHAR2(100)		 NULL ,
		SIZE1                         		NUMBER(10)		 DEFAULT 0		 NULL ,
  FOREIGN KEY (memno) REFERENCES MEM (memno)
);

COMMENT ON TABLE review is '평가 게시판';
COMMENT ON COLUMN review.reviewno is '평가번호';
COMMENT ON COLUMN review.memno is '회원 번호';
COMMENT ON COLUMN review.rtitle is '제목';
COMMENT ON COLUMN review.rinfo is '내용';
COMMENT ON COLUMN review.rdate is '날짜';
COMMENT ON COLUMN review.rname is '작성자';
COMMENT ON COLUMN review.word is '검색단어';
COMMENT ON COLUMN review.FILE1 is '메인이미지';
COMMENT ON COLUMN review.FILE1SAVED is '저장된 이미지';
COMMENT ON COLUMN review.THUMB1 is '이미지썸네일';
COMMENT ON COLUMN review.SIZE1 is '용량';

CREATE SEQUENCE review_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 999999999
    CACHE 2
    NOCYCLE;
    
COMMIT;  

-- 등록
INSERT INTO review(reviewno, memno, rtitle, rinfo, rdate, rname, word, file1, file1saved, THUMB1, size1) VALUES (review_seq.nextval, 1, '인터스텔라',  'SF', sysdate, '왕눈이', '재ㅔ밌다', 'photo1.jpg', 'photo1_t.jpg', 'photo1_m.jpg', 10);

-- Read : List
SELECT * FROM review;
SELECT reviewno, memno, rtitle, rinfo, rdate, rname, word, file1, file1saved, THUMB1, size1 FROM review ORDER BY reviewno ASC;
SELECT reviewno, memno, rtitle, rinfo, rdate, rname, word, file1, file1saved, THUMB1, size1 FROM review
WHERE reviewno=1;

-- Update
UPDATE review SET rtitle='마션' WHERE reviewno=2;
SELECT reviewno, memno, rtitle, rinfo, rdate, rname, word, file1, file1saved, THUMB1, size1 FROM review ORDER BY reviewno ASC;

-- Delete
DELETE FROM review WHERE reviewno=2;
SELECT reviewno, memno, rtitle, rinfo, rdate, rname, word, file1, file1saved, THUMB1, size1 FROM review ORDER BY reviewno ASC;