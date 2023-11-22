package dev.mvc.qna;

import java.util.ArrayList;

public interface QnaDAOInter {
  /**
   * 등록
   * @param qnaVO
   * @return
   */
  public int create(QnaVO qnaVO);
  
  /**
   * 전체 목록
   * @return
   */
  public ArrayList<QnaVO> list();
  
  /**
   * 조회
   * @param qnano
   * @return
   */
  public QnaVO read(int qnano);
  
  /**
   * 수정
   * @param qnaVO
   * @return
   */
  public int update(QnaVO qnaVO);
  
  /**
   * 삭제
   * @param qnano
   * @return
   */
  public int delete(int qnano);
}
