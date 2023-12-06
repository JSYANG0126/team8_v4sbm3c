package dev.mvc.answer;

import java.util.ArrayList;

public interface AnswerProcInter {
  
  public int create(AnswerVO answerVO);
  
  public ArrayList<AnswerVO>list();
    
  public ArrayList<AnswerVO> list_by_qnano(int qnano);
    
  public AnswerVO read(int answerno);
    
  public int update(AnswerVO answerVO);
    
  public int delete(int answerno);
  
  public int delete_by_qnano(int qnano);
}
