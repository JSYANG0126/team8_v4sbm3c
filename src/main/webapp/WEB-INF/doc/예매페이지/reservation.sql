CREATE TABLE reservation(
		reservationno                 		NUMBER(10)		 NULL 		 PRIMARY KEY,
		tname                         		VARCHAR2(20)		 NOT NULL,
		link                          		CHAR(100)		 NULL 
);

COMMENT ON TABLE reservation is '예매페이지';
COMMENT ON COLUMN reservation.reservationno is '예매번호';
COMMENT ON COLUMN reservation.tname is '이름';
COMMENT ON COLUMN reservation.link is '주소';

CREATE SEQUENCE RESERVATION_SEQ
  START WITH 1         -- 시작 번호
  INCREMENT BY 1       -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2              -- 2번은 메모리에서만 계산
  NOCYCLE;             -- 다시 1부터 생성되는 것을 방지
  
INSERT INTO reservation(reservationno, tname, link) 
VALUES(reservation_seq.nextval, 'CGV', 'www.cgv.com');  

-- READ: LIST
SELECT * FROM reservation;
SELECT reservationno, tname, link FROM reservation ORDER BY reservationno ASC;

-- READ
SELECT reservationno, tname, link FROM reservation WHERE reservationno=1;

-- UPDATE
UPDATE reservation SET tname='lotte cinema', link='www.lotte cinema' WHERE reservationno=1;


    UPDATE reservation 
    SET tname='메가박스', link='www.'
    WHERE reservationno= 5;

-- DELETE
DELETE FROM reservation WHERE reservationno=1;

COMMIT;