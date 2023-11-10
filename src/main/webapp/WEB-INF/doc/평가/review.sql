CREATE TABLE review(
		reviewno                      		NUMBER(10)		 NULL 		 PRIMARY KEY,
		memno                         		NUMBER(10)		 NOT NULL,
		movieno                       		VARCHAR2(200)		 NOT NULL,
		redate                        		DATE		 NULL ,
		retitle                       		VARCHAR2(30)		 NOT NULL,
		char                          		CHAR(20)		 NULL ,
		pwd                           		VARCHAR2(15)		 NULL ,
		mainimg                       		VARCHAR2(200)		 NULL ,
		savemainimg                   		VARCHAR2(200)		 NULL ,
		lookimg                       		VARCHAR2(200)		 NULL ,
  FOREIGN KEY (memno) REFERENCES MEM (memno)
);

COMMENT ON TABLE review is '평가 게시판';
COMMENT ON COLUMN review.reviewno is '평가번호';
COMMENT ON COLUMN review.memno is '회원 번호';
COMMENT ON COLUMN review.movieno is '내용';
COMMENT ON COLUMN review.redate is '날짜';
COMMENT ON COLUMN review.retitle is '제목';
COMMENT ON COLUMN review.char is '검색단어';
COMMENT ON COLUMN review.pwd is '비밀번호';
COMMENT ON COLUMN review.mainimg is '메인이미지';
COMMENT ON COLUMN review.savemainimg is '저장된 이미지';
COMMENT ON COLUMN review.lookimg is '이미지썸네일';