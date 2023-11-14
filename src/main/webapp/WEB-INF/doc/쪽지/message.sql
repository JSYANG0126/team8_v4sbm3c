DROP TABLE MESSAGE;

CREATE TABLE message(
		messageno                     		NUMBER(10)		 NULL 		 PRIMARY KEY,
		memno                         		NUMBER(10)		 NULL ,
		mtitle                        		VARCHAR2(100)		 NULL ,
		minfo                         		VARCHAR2(200)		 NULL ,
        gdate                               DATE            NOT NULL ,
  FOREIGN KEY (memno) REFERENCES MEM (memno)
);

COMMENT ON TABLE message is '쪽지';
COMMENT ON COLUMN message.messageno is '쪽지번호';
COMMENT ON COLUMN message.memno is '회원 번호';
COMMENT ON COLUMN message.mtitle is '제목';
COMMENT ON COLUMN message.minfo is '내용';
COMMENT ON COLUMN message.gdate is '보낸 날짜';

CREATE SEQUENCE message_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 999999999
    CACHE 2
    NOCYCLE;
    
COMMIT;    

-- 등록
INSERT INTO message(messageno, memno, mtitle, minfo, gdate) VALUES (message_seq.nextval, 1, '김밥', '맛있어', sysdate);

-- Read : List
SELECT * FROM message;
SELECT messageno, memno, mtitle, minfo, gdate FROM message ORDER BY messageno ASC;

-- Update
UPDATE message SET mtitle='분식' WHERE messageno=1;
SELECT messageno, memno, mtitle, minfo, gdate FROM message ORDER BY messageno ASC;

-- Delete
DELETE FROM message WHERE messageno=1;
SELECT messageno, memno, mtitle, minfo, gdate FROM message ORDER BY messageno ASC;