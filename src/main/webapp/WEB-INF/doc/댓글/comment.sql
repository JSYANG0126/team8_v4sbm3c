CREATE TABLE comments(
		commentno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		movieno                       		NUMBER(10)		 NULL ,
		memno                         		NUMBER(10)		 NULL ,
		comment                       		VARCHAR2(100)		 NOT NULL,
		date                          		DATE		 NULL ,
		like                          		NUMBER(10)		 NULL ,
  FOREIGN KEY (movieno) REFERENCES movie (movieno),
  FOREIGN KEY (memno) REFERENCES MEM (memno)
);

COMMENT ON TABLE comments is '댓글';
COMMENT ON COLUMN comments.commentno is '댓글 번호';
COMMENT ON COLUMN comments.movieno is '영화번호';
COMMENT ON COLUMN comments.memno is '회원 번호';
COMMENT ON COLUMN comments.comment is '내용';
COMMENT ON COLUMN comments.date is '날짜';
COMMENT ON COLUMN comments.like is '좋아요';