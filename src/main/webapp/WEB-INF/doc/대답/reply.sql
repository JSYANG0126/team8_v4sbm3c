DROP TABLE reply;
DROP SEQUENCE reply_seq;

CREATE TABLE reply(
		replyno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		reviewno                       		NUMBER(10)		 NOT NULL ,
		memno                         		NUMBER(10)		 NOT NULL ,
		reply                      		VARCHAR2(500)		 NOT NULL,
		cdate                          		DATE		 NULL ,
  FOREIGN KEY (reviewno) REFERENCES review (reviewno),
  FOREIGN KEY (memno) REFERENCES MEM (memno)
);

COMMENT ON TABLE reply is '댓글';
COMMENT ON COLUMN reply.replyno is '댓글 번호';
COMMENT ON COLUMN reply.reviewno is '평가 번호';
COMMENT ON COLUMN reply.memno is '회원 번호';
COMMENT ON COLUMN reply.reply is '내용';
COMMENT ON COLUMN reply.cdate is '날짜';


CREATE SEQUENCE reply_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 999999999
    CACHE 2
    NOCYCLE;
    
COMMIT;    

-- 등록
INSERT INTO reply(replyno, reviewno, memno, reply, cdate) VALUES (reply_seq.nextval, 2, 1, '맛있어보여요', sysdate);

-- Read : List
SELECT * FROM reply;
SELECT replyno, reviewno, memno, reply, cdate FROM reply ORDER BY replyno ASC;
SELECT replyno, reviewno, memno, reply, cdate FROM reply
WHERE reviewno=1;
-- Update
UPDATE reply SET reply='맛없어요' WHERE replyno=1;
SELECT replyno, reviewno, memno, reply, cdate, cname FROM reply ORDER BY replyno ASC;

-- Delete
DELETE FROM reply WHERE replyno=4;
SELECT replyno, reviewno, memno, reply, cdate, cname FROM reply ORDER BY replyno ASC;