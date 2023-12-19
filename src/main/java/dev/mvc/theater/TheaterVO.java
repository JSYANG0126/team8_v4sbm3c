package dev.mvc.theater;

import org.springframework.web.multipart.MultipartFile;

public class TheaterVO {
  private int theaterno;
  private int memno;
  private String tname = "";
  private String word = "";
  private String tinfo = "";
  private String tdate;
  private String map = "";
  
  
//파일 업로드 관련
  // -----------------------------------------------------------------------------------
  /**
  이미지 파일
  <input type='file' class="form-control" name='file1MF' id='file1MF' 
             value='' placeholder="파일 선택">
  */
  private MultipartFile file1MF;
  /** 메인 이미지 크기 단위, 파일 크기 */

  private long size1;
  
  private String imgsize1 = "";
  /** 메인 이미지 */
  private String img1 = "";
  /** 실제 저장된 메인 이미지 */
  private String img1saved = "";
  private String size1_label = "";
/** 메인 이미지 preview */
  private String thumbimg1 = "";

  
  
public MultipartFile getFile1MF() {
    return file1MF;
  }
  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }
  public String getImgsize1() {
    return imgsize1;
  }
  public void setImgsize1(String imgsize1) {
    this.imgsize1 = imgsize1;
  }
  public String getImg1() {
    return img1;
  }
  public void setImg1(String img1) {
    this.img1 = img1;
  }
  public String getImg1saved() {
    return img1saved;
  }
  public void setImg1saved(String img1saved) {
    this.img1saved = img1saved;
  }
  public String getThumbimg1() {
    return thumbimg1;
  }
  public void setThumbimg1(String thumbimg1) {
    this.thumbimg1 = thumbimg1;
  }
  public long getSize1() {
    return size1;
  }
  public void setSize1(long size1) {
    this.size1 = size1;
  }
  
  public String getSize1_label() {
    return size1_label;
  }
  public void setSize1_label(String size1_label) {
    this.size1_label = size1_label;
  }

  //페이징 관련
  // -----------------------------------------------------------------------------------
  /** 시작 rownum */
  private int start_num;    
  /** 종료 rownum */
  private int end_num;    
  /** 현재 페이지 */
  private int now_page = 1;
  
  public int getStart_num() {
    return start_num;
  }
  public void setStart_num(int start_num) {
    this.start_num = start_num;
  }
  public int getEnd_num() {
    return end_num;
  }
  public void setEnd_num(int end_num) {
    this.end_num = end_num;
  }
  public int getNow_page() {
    return now_page;
  }
  public void setNow_page(int now_page) {
    this.now_page = now_page;
  }
  
  
  public int getTheaterno() {
    return theaterno;
  }
  public void setTheaterno(int theaterno) {
    this.theaterno = theaterno;
  }
  public int getMemno() {
    return memno;
  }
  public void setMemno(int memno) {
    this.memno = memno;
  }
  public String getTname() {
    return tname;
  }
  public void setTname(String tname) {
    this.tname = tname;
  }
  public String getTinfo() {
    return tinfo;
  }
  public void setTinfo(String tinfo) {
    this.tinfo = tinfo;
  }
  public String getTdate() {
    return tdate;
  }
  public void setTdate(String tdate) {
    this.tdate = tdate;
  }
  public String getMap() {
    return map;
  }
  public void setMap(String map) {
    this.map = map;
  }
  public String getWord() {
    return word;
  }
  public void setWord(String word) {
    this.word = word;
  }
  
  @Override
  public String toString() {
    return "TheaterVO [theaterno=" + theaterno + ", memno=" + memno + ", tname=" + tname + ", word=" + word + ", tinfo="
        + tinfo + ", tdate=" + tdate + ", map=" + map + ", file1MF=" + file1MF + ", imgsize1=" + imgsize1 + ", img1="
        + img1 + ", img1saved=" + img1saved + ", thumbimg1=" + thumbimg1 + ", size1=" + size1 + ", start_num="
        + start_num + ", end_num=" + end_num + ", now_page=" + now_page + "]";
  }
  
  
  
}
