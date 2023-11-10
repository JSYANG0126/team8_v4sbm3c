/**********************************/
/* Table Name: 장르 */
/**********************************/
CREATE TABLE genre(
		genreno                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		name                          		VARCHAR2(30)		 NOT NULL,
		cnt                           		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		rdate                         		DATE		 NOT NULL,
		SEQNO                         		NUMBER(5)		 NULL ,
		VISIBLE                       		CHAR(1)		 NULL 
);

COMMENT ON TABLE genre is '장르';
COMMENT ON COLUMN genre.genreno is '장르번호';
COMMENT ON COLUMN genre.name is '장르이름';
COMMENT ON COLUMN genre.cnt is '관련자료수';
COMMENT ON COLUMN genre.rdate is '등록일';
COMMENT ON COLUMN genre.SEQNO is '출력순서';
COMMENT ON COLUMN genre.VISIBLE is '출력모드';


/**********************************/
/* Table Name: 관리자 */
/**********************************/
CREATE TABLE manager(
		managerno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		id                            		VARCHAR2(20)		 NULL ,
		passwd                        		VARCHAR2(15)		 NULL ,
		mname                         		VARCHAR2(20)		 NULL ,
		mdate                         		DATE		 NULL ,
		grade                         		NUMBER(2)		 NULL 
);

COMMENT ON TABLE manager is '관리자';
COMMENT ON COLUMN manager.managerno is '관리자번호';
COMMENT ON COLUMN manager.id is '아이디';
COMMENT ON COLUMN manager.passwd is '패스워드';
COMMENT ON COLUMN manager.mname is '성명';
COMMENT ON COLUMN manager.mdate is '가입일';
COMMENT ON COLUMN manager.grade is '등급';


/**********************************/
/* Table Name: 영화 */
/**********************************/
CREATE TABLE movie(
		movieno                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		genreno                       		NUMBER(10)		 NOT NULL,
		managerno                     		NUMBER(10)		 NOT NULL,
		TITLE                         		VARCHAR2(300)		 NOT NULL,
		CONTENT                       		CLOB		 NOT NULL,
		RECOM                         		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		CNT                           		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		REPRYCNT                      		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		PASSWD                        		VARCHAR2(15)		 DEFAULT 0		 NOT NULL,
		WORD                          		VARCHAR2(300)		 NULL ,
		RDATE                         		DATE		 NOT NULL,
		FILE1                         		VARCHAR2(100)		 NULL ,
		FILE1SAVED                    		VARCHAR2(100)		 NULL ,
		THUMB1                        		VARCHAR2(100)		 NULL ,
		SIZE1                         		NUMBER(10)		 DEFAULT 0		 NULL ,
  FOREIGN KEY (genreno) REFERENCES genre (genreno),
  FOREIGN KEY (managerno) REFERENCES manager (managerno)
);

COMMENT ON TABLE movie is '영화';
COMMENT ON COLUMN movie.movieno is '영화리뷰번호';
COMMENT ON COLUMN movie.genreno is '장르번호';
COMMENT ON COLUMN movie.managerno is '관리자번호';
COMMENT ON COLUMN movie.TITLE is '제목';
COMMENT ON COLUMN movie.CONTENT is '내용';
COMMENT ON COLUMN movie.RECOM is '추천수';
COMMENT ON COLUMN movie.CNT is '조회수';
COMMENT ON COLUMN movie.REPRYCNT is '댓글수';
COMMENT ON COLUMN movie.PASSWD is '패스워드';
COMMENT ON COLUMN movie.WORD is '검색어';
COMMENT ON COLUMN movie.RDATE is '등록일';
COMMENT ON COLUMN movie.FILE1 is '메인이미지';
COMMENT ON COLUMN movie.FILE1SAVED is '실제 저장된 메인 이미지';
COMMENT ON COLUMN movie.THUMB1 is '메인 이미지 Preview';
COMMENT ON COLUMN movie.SIZE1 is '메인 이미지 크기';


/**********************************/
/* Table Name: 회원 */
/**********************************/
CREATE TABLE MEM(
		memno                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		ID                            		VARCHAR2(30)		 NOT NULL,
		PASSWD                        		VARCHAR2(60)		 NOT NULL,
		MNAME                         		VARCHAR2(30)		 NOT NULL,
		TEL                           		VARCHAR2(14)		 NOT NULL,
		ZIPCODE                       		VARCHAR2(5)		 NULL ,
		ADDRESS1                      		VARCHAR2(80)		 NULL ,
		ADDRESS2                      		VARCHAR2(50)		 NULL ,
		MDATE                         		DATE		 NOT NULL,
		GRADE                         		NUMBER(2)		 NOT NULL,
		qnano                         		NUMBER(10)		 NULL 
);

COMMENT ON TABLE MEM is '회원';
COMMENT ON COLUMN MEM.memno is '회원 번호';
COMMENT ON COLUMN MEM.ID is '아이디';
COMMENT ON COLUMN MEM.PASSWD is '패스워드';
COMMENT ON COLUMN MEM.MNAME is '성명';
COMMENT ON COLUMN MEM.TEL is '전화번호';
COMMENT ON COLUMN MEM.ZIPCODE is '우편번호';
COMMENT ON COLUMN MEM.ADDRESS1 is '주소1';
COMMENT ON COLUMN MEM.ADDRESS2 is '주소2';
COMMENT ON COLUMN MEM.MDATE is '가입일';
COMMENT ON COLUMN MEM.GRADE is '등급';
COMMENT ON COLUMN MEM.qnano is '질문번호';


/**********************************/
/* Table Name: 양준석 */
/**********************************/
CREATE TABLE yang(

);

COMMENT ON TABLE yang is '양준석';


/**********************************/
/* Table Name: 챗봇 */
/**********************************/
CREATE TABLE chatbot(

);

COMMENT ON TABLE chatbot is '챗봇';


/**********************************/
/* Table Name: 추천 */
/**********************************/
CREATE TABLE recom(

);

COMMENT ON TABLE recom is '추천';


/**********************************/
/* Table Name: 김영준 */
/**********************************/
CREATE TABLE kim(

);

COMMENT ON TABLE kim is '김영준';


/**********************************/
/* Table Name: 평가 게시판 */
/**********************************/
CREATE TABLE review(
		reviewno                      		NUMBER(10)		 NULL 		 PRIMARY KEY,
		memno                         		NUMBER(10)		 NOT NULL,
		movieno                       		VARCHAR2(200)		 NOT NULL,
		redate                        		DATE		 NULL ,
		retitle                       		VARCHAR2(30)		 NOT NULL,
		char                          		CHAR(20)		 NULL ,
		pwd                           		VARCHAR2(15)		 NULL ,
		mainimg                       		VARCHAR2(200)		 NULL ,
		savemainimg                   		VARCHAR2(200)		 NULL ,
		lookimg                       		VARCHAR2(200)		 NULL ,
  FOREIGN KEY (memno) REFERENCES MEM (memno)
);

COMMENT ON TABLE review is '평가 게시판';
COMMENT ON COLUMN review.reviewno is '평가번호';
COMMENT ON COLUMN review.memno is '회원 번호';
COMMENT ON COLUMN review.movieno is '내용';
COMMENT ON COLUMN review.redate is '날짜';
COMMENT ON COLUMN review.retitle is '제목';
COMMENT ON COLUMN review.char is '검색단어';
COMMENT ON COLUMN review.pwd is '비밀번호';
COMMENT ON COLUMN review.mainimg is '메인이미지';
COMMENT ON COLUMN review.savemainimg is '저장된 이미지';
COMMENT ON COLUMN review.lookimg is '이미지썸네일';


/**********************************/
/* Table Name: 댓글 */
/**********************************/
CREATE TABLE comments(
		commentno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		movieno                       		NUMBER(10)		 NULL ,
		memno                         		NUMBER(10)		 NULL ,
		comment                       		VARCHAR2(100)		 NOT NULL,
		date                          		DATE		 NULL ,
		like                          		NUMBER(10)		 NULL ,
  FOREIGN KEY (movieno) REFERENCES movie (movieno),
  FOREIGN KEY (memno) REFERENCES MEM (memno)
);

COMMENT ON TABLE comments is '댓글';
COMMENT ON COLUMN comments.commentno is '댓글 번호';
COMMENT ON COLUMN comments.movieno is '영화번호';
COMMENT ON COLUMN comments.memno is '회원 번호';
COMMENT ON COLUMN comments.comment is '내용';
COMMENT ON COLUMN comments.date is '날짜';
COMMENT ON COLUMN comments.like is '좋아요';


/**********************************/
/* Table Name: 민동휘 */
/**********************************/
CREATE TABLE dong(

);

COMMENT ON TABLE dong is '민동휘';


/**********************************/
/* Table Name: 추가해야할 내용 */
/**********************************/
CREATE TABLE TABLE_12(

);

COMMENT ON TABLE TABLE_12 is '추가해야할 내용';


/**********************************/
/* Table Name: 질의응답 */
/**********************************/
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


/**********************************/
/* Table Name: 예매페이지 */
/**********************************/
CREATE TABLE reservation(
		reservationno                 		NUMBER(10)		 NULL 		 PRIMARY KEY,
		tname                         		VARCHAR2(20)		 NOT NULL,
		link                          		CHAR(100)		 NULL 
);

COMMENT ON TABLE reservation is '예매페이지';
COMMENT ON COLUMN reservation.reservationno is '예매번호';
COMMENT ON COLUMN reservation.tname is '이름';
COMMENT ON COLUMN reservation.link is '주소';


/**********************************/
/* Table Name: 영화관 추천 */
/**********************************/
CREATE TABLE theater(
		theaterno                     		NUMBER(10)		 NULL 		 PRIMARY KEY,
		memno                         		NUMBER(10)		 NOT NULL,
		ttitle                        		VARCHAR2(100)		 NULL ,
		tinfo                         		CHAR(200)		 NULL ,
		tdate                         		DATE		 NULL ,
		map                           		VARCHAR2(2000)		 NULL ,
  FOREIGN KEY (memno) REFERENCES MEM (memno)
);

COMMENT ON TABLE theater is '영화관 추천';
COMMENT ON COLUMN theater.theaterno is '영화관 번호';
COMMENT ON COLUMN theater.memno is '회원 번호';
COMMENT ON COLUMN theater.ttitle is '제목';
COMMENT ON COLUMN theater.tinfo is '내용';
COMMENT ON COLUMN theater.tdate is '날짜';
COMMENT ON COLUMN theater.map is '지도';


/**********************************/
/* Table Name: 쪽지 */
/**********************************/
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


