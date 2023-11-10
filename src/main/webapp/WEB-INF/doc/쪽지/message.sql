CREATE TABLE message(
		messageno                     		NUMBER(10)		 NULL 		 PRIMARY KEY,
		memno                         		NUMBER(10)		 NULL ,
		mtitle                        		VARCHAR2(100)		 NULL ,
		minfo                         		VARCHAR2(200)		 NULL ,
  FOREIGN KEY (memno) REFERENCES MEM (memno)
);

COMMENT ON TABLE message is '쪽지';
COMMENT ON COLUMN message.messageno is '쪽지번호';
COMMENT ON COLUMN message.memno is '회원 번호';
COMMENT ON COLUMN message.mtitle is '제목';
COMMENT ON COLUMN message.minfo is '내용';