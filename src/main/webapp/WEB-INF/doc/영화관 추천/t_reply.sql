DROP TABLE treply;
DROP SEQUENCE treply_seq;

CREATE TABLE treply(
		treplyno                     	NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		theaterno                       NUMBER(10)		 NOT NULL ,
		memno                         	NUMBER(10)		 NOT NULL ,
		treply                      	VARCHAR2(100)		 NOT NULL,
        pw                      	    VARCHAR2(20)		 NOT NULL,
		cdate                          	DATE		 NULL ,
  FOREIGN KEY (theaterno) REFERENCES theater (theaterno),
  FOREIGN KEY (memno) REFERENCES MEM (memno)
);

COMMENT ON TABLE treply is '영화관 댓글';
COMMENT ON COLUMN treply.commentno is '영화관 댓글 번호';
COMMENT ON COLUMN treply.movieno is '영화관 번호';
COMMENT ON COLUMN treply.memno is '회원 번호';
COMMENT ON COLUMN treply.reply is '내용';
COMMENT ON COLUMN treply.pw is '비밀번호';
COMMENT ON COLUMN treply.cdate is '날짜';


CREATE SEQUENCE treply_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 999999999
    CACHE 2
    NOCYCLE;
    
COMMIT;    

SELECT id, treplyno, theaterno, memno, treply, pw, cdate, r
FROM (
    SELECT id, treplyno, theaterno, memno, treply, pw, cdate, rownum as r
    FROM (
        SELECT m.id, t.treplyno, t.theaterno, t.memno, t.treply, t.pw, t.cdate
        FROM mem m,  treply t
        WHERE (m.memno = t.memno) AND t.theaterno=5
        ORDER BY t.treplyno DESC
    )
)
WHERE r <= 1000;


INSERT INTO treply(treplyno, theaterno, memno, treply, pw, cdate) 
VALUES (treply_seq.nextval, 3, 1, '좋아보여요',1234, sysdate);

    SELECT m.id,r.replyno, r.contentsno, r.memberno, r.content, r.passwd, r.rdate
    FROM member m,  reply r
    WHERE (m.memno = r.memno) AND r.contentsno=#{contentsno}
    ORDER BY r.replyno DESC

