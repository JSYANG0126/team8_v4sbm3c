CREATE TABLE reservation(
		reservationno                 		NUMBER(10)		 NULL 		 PRIMARY KEY,
		tname                         		VARCHAR2(20)		 NOT NULL,
		link                          		CHAR(100)		 NULL 
);

COMMENT ON TABLE reservation is '예매페이지';
COMMENT ON COLUMN reservation.reservationno is '예매번호';
COMMENT ON COLUMN reservation.tname is '이름';
COMMENT ON COLUMN reservation.link is '주소';