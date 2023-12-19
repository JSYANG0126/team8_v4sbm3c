DROP TABLE good;
DROP SEQUENCE good_seq;

CREATE TABLE good(
		goodno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		movieno                       		NUMBER(10)		 NOT NULL ,
		memno                         		NUMBER(10)		 NOT NULL ,
		rdate                         	VARCHAR2(100)		 NOT NULL,
  FOREIGN KEY (movieno) REFERENCES movie (movieno),
  FOREIGN KEY (memno) REFERENCES MEM (memno)
);

COMMENT ON TABLE good is '좋아요';
COMMENT ON COLUMN good.goodno is '좋아요 번호';
COMMENT ON COLUMN good.movieno is '영화번호';
COMMENT ON COLUMN good.memno is '회원 번호';
COMMENT ON COLUMN good.rdate is '좋아요날짜';

CREATE SEQUENCE good_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 999999999
    CACHE 2
    NOCYCLE;
    
COMMIT;    

-- 등록
INSERT INTO good(goodno, movieno, memno, rdate) VALUES (good_seq.nextval, 2, 4, sysdate);

-- Read : List
SELECT * FROM good;
SELECT goodno, movieno, memno, rdate FROM good ORDER BY goodno ASC;

-- 본문 글의 총 조회수
SELECT COUNT(*) as cnt FROM good
WHERE movieno=2;

-- 1번 회원이 2번 상품을 좋아요 클릭했는지 카운트, 1: 좋아요했음, 0은 자료 없음
SELECT COUNT(*) as cnt 
FROM good
WHERE movieno=6 AND memno=3;

-- Delete
DELETE FROM good WHERE movieno=2 and memno=3;