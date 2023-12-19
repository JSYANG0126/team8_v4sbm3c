DROP TABLE nice;
DROP SEQUENCE nice_seq;

CREATE TABLE nice(
		niceno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		reviewno                       		NUMBER(10)		 NOT NULL ,
		memno                         		NUMBER(10)		 NOT NULL ,
		rdate                         	VARCHAR2(100)		 NOT NULL,
  FOREIGN KEY (reviewno) REFERENCES review (reviewno),
  FOREIGN KEY (memno) REFERENCES MEM (memno)
);

COMMENT ON TABLE nice is '좋아요';
COMMENT ON COLUMN nice.niceno is '좋아요 번호';
COMMENT ON COLUMN nice.reviewno is '평가 번호';
COMMENT ON COLUMN nice.memno is '회원 번호';
COMMENT ON COLUMN nice.rdate is '좋아요날짜';

CREATE SEQUENCE nice_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 999999999
    CACHE 2
    NOCYCLE;
    
COMMIT;    

-- 등록
INSERT INTO nice(niceno, reviewno, memno, rdate) VALUES (nice_seq.nextval, 2, 3, sysdate);

-- Read : List
SELECT * FROM nice;
SELECT niceno, reviewno, memno, rdate FROM nice ORDER BY niceno ASC;

-- 본문 글의 총 조회수
SELECT COUNT(*) as cnt FROM nice
WHERE reviewno=10;

-- 1번 회원이 2번 상품을 좋아요 클릭했는지 카운트, 1: 좋아요했음, 0은 자료 없음
SELECT COUNT(*) as cnt 
FROM nice
WHERE reviewno=2 AND memno=3;

-- Delete
DELETE FROM nice WHERE reviewno=2 and memno=3;