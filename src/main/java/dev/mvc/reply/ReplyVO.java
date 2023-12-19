package dev.mvc.reply;

public class ReplyVO {
  
  private int replyno;
  private int reviewno;
  private int memno;
  private String reply;
  private String cdate;
  private int favorite;
  private String cname;

  
  public int getReplyno() {
    return replyno;
  }
  public void setReplyno(int replyno) {
    this.replyno = replyno;
  }
  public int getReviewno() {
    return reviewno;
  }
  public void setReviewno(int reviewno) {
    this.reviewno = reviewno;
  }
  public int getMemno() {
    return memno;
  }
  public void setMemno(int memno) {
    this.memno = memno;
  }
  public String getReply() {
    return reply;
  }
  public void setReply(String reply) {
    this.reply = reply;
  }
  public String getCdate() {
    return cdate;
  }
  public void setCdate(String cdate) {
    this.cdate = cdate;
  }
  public int getFavorite() {
    return favorite;
  }
  public void setFavorite(int favorite) {
    this.favorite = favorite;
  }
  public String getCname() {
    return cname;
  }
  public void setCname(String cname) {
    this.cname = cname;
  }
  
  
  
}
