package dev.mvc.message;

public class MessageVO {
  private int messageno;
  private int memno;
  private String mtitle;
  private String minfo;
  private String gdate;
  public int getMessageno() {
    return messageno;
  }
  public void setMessageno(int messageno) {
    this.messageno = messageno;
  }
  public int getMemno() {
    return memno;
  }
  public void setMemno(int memno) {
    this.memno = memno;
  }
  public String getMtitle() {
    return mtitle;
  }
  public void setMtitle(String mtitle) {
    this.mtitle = mtitle;
  }
  public String getMinfo() {
    return minfo;
  }
  public void setMinfo(String minfo) {
    this.minfo = minfo;
  }
  public String getGdate() {
    return gdate;
  }
  public void setGdate(String gdate) {
    this.gdate = gdate;
  }
  
  
}