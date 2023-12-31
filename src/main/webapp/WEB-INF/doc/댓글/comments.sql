DROP TABLE comments;
DROP SEQUENCE comments_seq;

CREATE TABLE comments(
		commentno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		movieno                       		NUMBER(10)		 NOT NULL ,
		memno                         		NUMBER(10)		 NOT NULL ,
		reply                      		VARCHAR2(100)		 NOT NULL,
		cdate                          		DATE		 NULL ,
		favorite                          		NUMBER(10)	DEFAULT 0	 NULL,
        cname                           VARCHAR2(30)        NOT NULL,
  FOREIGN KEY (movieno) REFERENCES movie (movieno),
  FOREIGN KEY (memno) REFERENCES MEM (memno)
);

COMMENT ON TABLE comments is '댓글';
COMMENT ON COLUMN comments.commentno is '댓글 번호';
COMMENT ON COLUMN comments.movieno is '영화번호';
COMMENT ON COLUMN comments.memno is '회원 번호';
COMMENT ON COLUMN comments.reply is '내용';
COMMENT ON COLUMN comments.cdate is '날짜';
COMMENT ON COLUMN comments.favorite is '좋아요';
COMMENT ON COLUMN comments.cname is '작성자';


CREATE SEQUENCE comments_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 999999999
    CACHE 2
    NOCYCLE;
    
COMMIT;    

-- 등록
INSERT INTO comments(commentno, movieno, memno, reply, cdate, cname) VALUES (comments_seq.nextval, 2, 1, '맛있어보여요', sysdate, '난쟁이');

-- Read : List
SELECT * FROM comments;
SELECT commentno, movieno, memno, reply, cdate, favorite, cname FROM comments ORDER BY commentno ASC;
SELECT commentno, movieno, memno, reply, cdate, favorite, cname FROM comments
WHERE commentno=1;
-- Update
UPDATE comments SET reply='맛없어요' WHERE commentno=5;
SELECT commentno, movieno, memno, reply, cdate, favorite, cname FROM comments ORDER BY commentno ASC;

-- Delete
DELETE FROM comments WHERE commentno=4;
SELECT commentno, movieno, memno, reply, cdate, favorite FROM comments ORDER BY commentno ASC;