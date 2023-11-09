/**********************************/
/* Table Name: 영화리뷰 */
/**********************************/
DROP TABLE mreview CASCADE CONSTRAINTS; -- 자식 무시하고 삭제 가능
DROP TABLE mreview;

CREATE TABLE mreview(
		mreviewno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		genreno                       		NUMBER(10)		 NOT NULL,
		managerno                     		NUMBER(10)		 NOT NULL,
		TITLE                         		VARCHAR2(300)		 NOT NULL,
		CONTENT                       		CLOB		 NOT NULL,
		RECOM                         		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		CNT                           		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		REPLYCNT                      		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		PASSWD                        		VARCHAR2(15)		 DEFAULT 0		 NOT NULL,
		WORD                          		VARCHAR2(300)		 NULL ,
		RDATE                         		DATE		 NOT NULL,
		FILE1                         		VARCHAR2(100)		 NULL ,
		FILE1SAVED                    		VARCHAR2(100)		 NULL ,
		THUMB1                        		VARCHAR2(100)		 NULL ,
		SIZE1                         		NUMBER(10)		 DEFAULT 0		 NULL ,
		PRICE                         		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		DC                            		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		SALEPRICE                     		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		POINT                         		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		SALECNT                       		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		MAP                           		VARCHAR2(1000)		 NULL ,
		YOUTUBE                       		VARCHAR2(1000)		 NULL ,
  FOREIGN KEY (genreno) REFERENCES genre (genreno),
  FOREIGN KEY (managerno) REFERENCES manager (managerno)
);

COMMENT ON TABLE mreview is '영화리뷰';
COMMENT ON COLUMN mreview.mreviewno is '영화리뷰번호';
COMMENT ON COLUMN mreview.genreno is '장르번호';
COMMENT ON COLUMN mreview.managerno is '관리자번호';
COMMENT ON COLUMN mreview.TITLE is '제목';
COMMENT ON COLUMN mreview.CONTENT is '내용';
COMMENT ON COLUMN mreview.RECOM is '추천수';
COMMENT ON COLUMN mreview.CNT is '조회수';
COMMENT ON COLUMN mreview.REPLYCNT is '댓글수';
COMMENT ON COLUMN mreview.PASSWD is '패스워드';
COMMENT ON COLUMN mreview.WORD is '검색어';
COMMENT ON COLUMN mreview.RDATE is '등록일';
COMMENT ON COLUMN mreview.FILE1 is '메인이미지';
COMMENT ON COLUMN mreview.FILE1SAVED is '실제 저장된 메인 이미지';
COMMENT ON COLUMN mreview.THUMB1 is '메인 이미지 Preview';
COMMENT ON COLUMN mreview.SIZE1 is '메인 이미지 크기';
COMMENT ON COLUMN mreview.PRICE is '정가';
COMMENT ON COLUMN mreview.DC is '할인률';
COMMENT ON COLUMN mreview.SALEPRICE is '판매가';
COMMENT ON COLUMN mreview.POINT is '포인트';
COMMENT ON COLUMN mreview.SALECNT is '수량';
COMMENT ON COLUMN mreview.MAP is '지도';
COMMENT ON COLUMN mreview.YOUTUBE is '유튜브';

DROP SEQUENCE mreview_seq;

CREATE SEQUENCE mreview_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지
  
-- 등록 화면 유형 1: 커뮤니티(공지사항, 게시판, 자료실, 갤러리,  Q/A...)글 등록
INSERT INTO mreview(mreviewno, managerno, genreno, title, content, recom, cnt, replycnt, passwd, 
                     word, rdate, file1, file1saved, thumb1, size1)
VALUES(mreview_seq.nextval, 1, 1, '엘리멘탈', '영화 엘리멘탈 리뷰', 0, 0, 0, '123',
       '애니메이션', sysdate, 'elemental.png', 'elemental_1.png', 'elemental_t.png', 1000);
       
-- 유형 1 전체 목록
SELECT mreviewno, managerno, genreno, title, content, recom, cnt, replycnt, passwd, word, rdate,
           file1, file1saved, thumb1, size1
FROM mreview
ORDER BY mreviewno DESC;

-- 유형 2 카테고리별 목록
INSERT INTO mreview(mreviewno, managerno, genreno, title, content, recom, cnt, replycnt, passwd, 
                     word, rdate, file1, file1saved, thumb1, size1)
VALUES(mreview_seq.nextval, 2, 1, '트랜스포머1', '영화 트랜스포머1 리뷰', 0, 0, 0, '123',
       'SF', sysdate, 'elemental.png', 'elemental_1.png', 'elemental_t.png', 1000);
       
INSERT INTO mreview(mreviewno, managerno, genreno, title, content, recom, cnt, replycnt, passwd, 
                     word, rdate, file1, file1saved, thumb1, size1)
VALUES(mreview_seq.nextval, 2, 1, '트랜스포머2', '영화 트랜스포머2 리뷰', 0, 0, 0, '123',
       'SF', sysdate, 'elemental.png', 'elemental_1.png', 'elemental_t.png', 1000);
       
INSERT INTO mreview(mreviewno, managerno, genreno, title, content, recom, cnt, replycnt, passwd, 
                     word, rdate, file1, file1saved, thumb1, size1)
VALUES(mreview_seq.nextval, 2, 1, '트랜스포머2', '영화 트랜스포머2 리뷰', 0, 0, 0, '123',
       'SF', sysdate, 'elemental.png', 'elemental_1.png', 'elemental_t.png', 1000);

-- 모든 레코드 삭제
DELETE FROM mreview;

-- 삭제
DELETE FROM mreview
WHERE mreviewno = 25;
commit;

COMMIT;
