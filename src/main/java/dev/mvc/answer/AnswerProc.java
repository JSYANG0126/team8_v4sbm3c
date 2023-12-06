package dev.mvc.answer;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.answer.AnswerProc")
public class AnswerProc implements AnswerProcInter {
  @Autowired
  private AnswerDAOInter answerDAO;
  
  @Override
  public int create(AnswerVO answerVO) {
    int cnt = this.answerDAO.create(answerVO);
    return cnt;
  }

  @Override
  public ArrayList<AnswerVO> list() {
    ArrayList<AnswerVO> list = this.answerDAO.list();
    return list;
  }

  @Override
  public ArrayList<AnswerVO> list_by_qnano(int qnano) {
    ArrayList<AnswerVO> list_by_qnano = this.answerDAO.list_by_qnano(qnano);
    return list_by_qnano;
  }

  @Override
  public AnswerVO read(int answerno) {
    AnswerVO read = this.answerDAO.read(answerno);
    return read;
  }

  @Override
  public int update(AnswerVO answerVO) {
    int cnt = this.answerDAO.update(answerVO);
    return cnt;
  }

  @Override
  public int delete(int answerno) {
    int cnt = this.answerDAO.delete(answerno);
    return cnt;
  }

  @Override
  public int delete_by_qnano(int qnano) {
    int cnt = this.answerDAO.delete_by_qnano(qnano);
    return cnt;
  }
  

}
