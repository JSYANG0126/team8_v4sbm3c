CREATE TABLE QNA(
		qnano                         		NUMBER(10)		 NULL 		 PRIMARY KEY,
		memno                         		NUMBER(10)		 NOT NULL,
		qnatitle                      		VARCHAR2(100)		 NULL ,
		qnainfo                       		VARCHAR2(200)		 NULL ,
  FOREIGN KEY (memno) REFERENCES MEM (memno)
);

COMMENT ON TABLE QNA is '질의응답';
COMMENT ON COLUMN QNA.qnano is '질문번호';
COMMENT ON COLUMN QNA.memno is '회원 번호';
COMMENT ON COLUMN QNA.qnatitle is '질문거리';
COMMENT ON COLUMN QNA.qnainfo is '질문내용';

CREATE SEQUENCE QNA_SEQ
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 999999999
    CACHE 2
    NOCYCLE;
    
COMMIT;  

-- 등록
INSERT INTO QNA(qnano, memno, qnatitle, qnainfo) VALUES (QNA_SEQ.nextval, 1, '궁금이', '돈 잘버는 방법');

-- Read : List
SELECT * FROM QNA;
SELECT qnano, memno, qnatitle, qnainfo FROM QNA ORDER BY qnano ASC;
SELECT qnano, memno, qnatitle, qnainfo FROM QNA
WHERE qnano=1;

-- Update
UPDATE QNA SET qnatitle='분식', qnainfo='내용' WHERE qnano=1;
SELECT qnano, memno, qnatitle, qnainfo FROM QNA ORDER BY qnano ASC;

-- Delete
DELETE FROM QNA WHERE qnano=1;
SELECT qnano, memno, qnatitle, qnainfo FROM QNA ORDER BY qnano ASC;
