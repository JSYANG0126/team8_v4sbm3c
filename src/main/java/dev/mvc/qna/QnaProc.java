package dev.mvc.qna;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.qna.QnaProc")
public class QnaProc implements QnaProcInter {
  @Autowired
  private QnaDAOInter qnaDAO;
  
  @Override
  public int create(QnaVO qnaVO) {
    int cnt = this.qnaDAO.create(qnaVO);
    return cnt;
  }

  @Override
  public ArrayList<QnaVO> list() {
    ArrayList<QnaVO> list = this.qnaDAO.list();
    return list;
  }

  @Override
  public QnaVO read(int qnano) {
    QnaVO qnaVO = this.qnaDAO.read(qnano);
    return qnaVO;
  }

  @Override
  public int update(QnaVO qnaVO) {
    int cnt = this.qnaDAO.update(qnaVO);
    return cnt;
  }

  @Override
  public int delete(int qnano) {
    int cnt = this.qnaDAO.delete(qnano);
    return cnt;
  }
  
}
