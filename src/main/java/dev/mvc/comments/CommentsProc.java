package dev.mvc.comments;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.comments.CommentsProc")
public class CommentsProc implements CommentsProcInter {
  @Autowired
  private CommentsDAOInter commentsDAO;
  
  @Override
  public int create(CommentsVO commentsVO) {
    int cnt = this.commentsDAO.create(commentsVO);
    return cnt;
  }

  @Override
  public ArrayList<CommentsVO> list() {
    ArrayList<CommentsVO> list = this.commentsDAO.list();
    return list;
  }
  
  @Override
  public ArrayList<CommentsVO> list_by_movieno(int movieno) {
    ArrayList<CommentsVO> list_by_movieno = this.commentsDAO.list_by_movieno(movieno);
    return list_by_movieno;
  }

  @Override
  public CommentsVO read(int commentno) {
    CommentsVO read = this.commentsDAO.read(commentno);
    return read;
  }

  @Override
  public int update(CommentsVO commentsVO) {
    int cnt = this.commentsDAO.update(commentsVO);
    return cnt;
  }

  @Override
  public int delete(int commentno) {
    int cnt = this.commentsDAO.delete(commentno);
    return cnt;
  }

  @Override
  public int delete_by_movieno(int movieno) {
    int cnt = this.commentsDAO.delete_by_movieno(movieno);
    return cnt;
  }
}
