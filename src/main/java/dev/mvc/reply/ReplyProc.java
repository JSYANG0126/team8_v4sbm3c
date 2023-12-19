package dev.mvc.reply;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.reply.ReplyProc")
public class ReplyProc implements ReplyProcInter {
  @Autowired
  private ReplyDAOInter replyDAO;
  
  @Override
  public int create(ReplyVO replyVO) {
    int cnt = this.replyDAO.create(replyVO);
    return cnt;
  }

  @Override
  public ArrayList<ReplyVO> list() {
    ArrayList<ReplyVO> list = this.replyDAO.list();
    return list;
  }
  
  @Override
  public ArrayList<ReplyVO> list_by_reviewno(int reviewno) {
    ArrayList<ReplyVO> list_by_reviewno = this.replyDAO.list_by_reviewno(reviewno);
    return list_by_reviewno;
  }

  @Override
  public ReplyVO read(int replyno) {
    ReplyVO read = this.replyDAO.read(replyno);
    return read;
  }

  @Override
  public int update(ReplyVO replyVO) {
    int cnt = this.replyDAO.update(replyVO);
    return cnt;
  }

  @Override
  public int delete(int replyno) {
    int cnt = this.replyDAO.delete(replyno);
    return cnt;
  }

  @Override
  public int delete_by_reviewno(int reviewno) {
    int cnt = this.replyDAO.delete_by_reviewno(reviewno);
    return cnt;
  }
}
