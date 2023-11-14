package dev.mvc.comments;

public class CommentsVO {
  private int commentno;
  private int movieno;
  private int memno;
  private String reply;
  private String cdate;
  private int favorite;
  public int getCommentno() {
    return commentno;
  }
  public void setCommentno(int commentno) {
    this.commentno = commentno;
  }
  public int getMovieno() {
    return movieno;
  }
  public void setMovieno(int movieno) {
    this.movieno = movieno;
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
  
  
}
