package dev.mvc.comments;

import java.util.ArrayList;

public interface CommentsProcInter {
  public int create(CommentsVO commentsVO);
  
  public ArrayList<CommentsVO>list();
  
  public ArrayList<CommentsVO> list_by_movieno(int movieno);
  
  public CommentsVO read(int commentno);
  
  public int update(CommentsVO commentsVO);
  
  public int delete(int commentno);
}
