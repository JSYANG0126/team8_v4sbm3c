/************************************/
/* Table Name: 회원 로그인 내역 */
/************************************/

DROP TABLE mlogin;
DROP TABLE mlogin CASCADE CONSTRAINTS;

CREATE TABLE mlogin(
  mloginno                    NUMBER(10) NOT NULL PRIMARY KEY,
  memno                NUMBER(10) NOT NULL,
  ip                            VARCHAR2(15) NOT NULL,
  logindate                  DATE NOT NULL,
  FOREIGN KEY (memno) REFERENCES mem (memno)
);

COMMENT ON TABLE mlogin is '로그인 내역';
COMMENT ON COLUMN mlogin.mloginno is '로그인 번호';
COMMENT ON COLUMN mlogin.memno is '회원 번호';
COMMENT ON COLUMN mlogin.ip is '접속 IP';
COMMENT ON COLUMN mlogin.logindate is '로그인 날짜';

DROP SEQUENCE MLOGIN_SEQ;

CREATE SEQUENCE MLOGIN_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 99999            -- 최대값: 99999 --> NUMBER(5) 대응
  CACHE 2                   -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지
  
INSERT INTO mlogin(mloginno, memno, ip, logindate)
VALUES(mlogin_seq.nextval, 1, '0.0.0.0', sysdate);
  
select *
from mlogin

commit;
