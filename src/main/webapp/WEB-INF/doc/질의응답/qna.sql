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