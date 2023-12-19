package dev.mvc.reply;

import java.util.ArrayList;

public interface ReplyDAOInter {
  
  public int create(ReplyVO replyVO);
  
  public ArrayList<ReplyVO>list();
  
  public ArrayList<ReplyVO> list_by_reviewno(int reviewno);
  
  public ReplyVO read(int replyno);
  
  public int update(ReplyVO replyVO);
  
  public int delete(int replyno);
  
  public int delete_by_reviewno(int reviewno);
}
