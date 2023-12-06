DROP TABLE answer;
DROP SEQUENCE answer_seq;

CREATE TABLE answer(
		answerno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		qnano                       		NUMBER(10)		 NOT NULL ,
		memno                         		NUMBER(10)		 NOT NULL ,
		reply                      		VARCHAR2(1000)		 NOT NULL,
		adate                          		DATE		 NULL ,
        aname                           VARCHAR2(30)        NOT NULL,
  FOREIGN KEY (qnano) REFERENCES qna (qnano),
  FOREIGN KEY (memno) REFERENCES MEM (memno)
);


COMMENT ON TABLE answer is '답변';
COMMENT ON COLUMN answer.answerno is '댓글 번호';
COMMENT ON COLUMN answer.qnano is '질문 번호';
COMMENT ON COLUMN answer.memno is '회원 번호';
COMMENT ON COLUMN answer.reply is '내용';
COMMENT ON COLUMN answer.adate is '날짜';
COMMENT ON COLUMN answer.aname is '작성자';


CREATE SEQUENCE answer_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 999999999
    CACHE 2
    NOCYCLE;
    
COMMIT;    

-- 등록
INSERT INTO answer(answerno, qnano, memno, reply, adate, aname) VALUES (answer_seq.nextval, 1, 1, '맛있어보여요', sysdate, '난쟁이');

-- Read : List
SELECT * FROM answer;
SELECT answerno, qnano, memno, reply, adate, aname FROM answer ORDER BY answerno ASC;
SELECT answerno, qnano, memno, reply, adate, aname FROM answer
WHERE answerno=1;
-- Update
UPDATE answer SET reply='맛없어요' WHERE answerno=5;
SELECT answerno, qnano, memno, reply, adate, aname FROM answer ORDER BY answerno ASC;

-- Delete
DELETE FROM answer WHERE answerno=2;
SELECT answerno, qnano, memno, reply, adate, aname FROM answer ORDER BY answerno ASC;