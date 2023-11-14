package dev.mvc.movie;

import org.springframework.web.multipart.MultipartFile;

//CREATE TABLE movie(
//		movieno                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
//		genreno                       		NUMBER(10)		 NOT NULL,
//		managerno                     		NUMBER(10)		 NOT NULL,
//		TITLE                         		VARCHAR2(300)		 NOT NULL,
//		CONTENT                       		CLOB		 NOT NULL,
//		RECOM                         		NUMBER(7)		 DEFAULT 0		 NOT NULL,
//		CNT                           		NUMBER(7)		 DEFAULT 0		 NOT NULL,
//		REPRYCNT                      		NUMBER(7)		 DEFAULT 0		 NOT NULL,
//		PASSWD                        		VARCHAR2(15)		 DEFAULT 0		 NOT NULL,
//		WORD                          		VARCHAR2(300)		 NULL ,
//		RDATE                         		DATE		 NOT NULL,
//		FILE1                         		VARCHAR2(100)		 NULL ,
//		FILE1SAVED                    		VARCHAR2(100)		 NULL ,
//		THUMB1                        		VARCHAR2(100)		 NULL ,
//		SIZE1                         		NUMBER(10)		 DEFAULT 0		 NULL ,
//  FOREIGN KEY (genreno) REFERENCES genre (genreno),
//  FOREIGN KEY (managerno) REFERENCES manager (managerno)
//);
public class MovieVO {
	private int movieno;
	private int genreno;
	private int managerno;
	private String title = "";
	private String content = "";
	private int recom;
	private int cnt;
	private int reprycnt;
	private String passwd = "";
	private String word = "";
	private String rdate;
	private String file1 = "";
	private String file1saved = "";
	private String thumb1;
	private long size1;
	/**
    이미지 파일
    <input type='file' class="form-control" name='file1MF' id='file1MF' 
               value='' placeholder="파일 선택">
    */
    private MultipartFile file1MF;
    /** 메인 이미지 크기 단위, 파일 크기 */
	public int getMovieno() {
		return movieno;
	}
	public void setMovieno(int movieno) {
		this.movieno = movieno;
	}
	public int getGenreno() {
		return genreno;
	}
	public void setGenreno(int genreno) {
		this.genreno = genreno;
	}
	public int getManagerno() {
		return managerno;
	}
	public void setManagerno(int managerno) {
		this.managerno = managerno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRecom() {
		return recom;
	}
	public void setRecom(int recom) {
		this.recom = recom;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getReprycnt() {
		return reprycnt;
	}
	public void setReprycnt(int reprycnt) {
		this.reprycnt = reprycnt;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public String getFile1() {
		return file1;
	}
	public void setFile1(String file1) {
		this.file1 = file1;
	}
	public String getFile1saved() {
		return file1saved;
	}
	public void setFile1saved(String file1saved) {
		this.file1saved = file1saved;
	}
	public String getThumb1() {
		return thumb1;
	}
	public void setThumb1(String thumb1) {
		this.thumb1 = thumb1;
	}
	public long getSize1() {
		return size1;
	}
	public void setSize1(long size1) {
		this.size1 = size1;
	}
	public MultipartFile getFile1MF() {
		return file1MF;
	}
	public void setFile1MF(MultipartFile file1mf) {
		file1MF = file1mf;
	}
	@Override
	public String toString() {
		return "MovieVO [movieno=" + movieno + ", genreno=" + genreno + ", managerno=" + managerno + ", title=" + title
				+ ", content=" + content + ", recom=" + recom + ", cnt=" + cnt + ", reprycnt=" + reprycnt + ", passwd="
				+ passwd + ", word=" + word + ", rdate=" + rdate + ", file1=" + file1 + ", file1saved=" + file1saved
				+ ", thumb1=" + thumb1 + ", size1=" + size1 + ", file1MF=" + file1MF + "]";
	}
	
}
