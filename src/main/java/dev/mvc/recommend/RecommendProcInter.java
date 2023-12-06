package dev.mvc.recommend;

import java.util.ArrayList;

public interface RecommendProcInter {
  
  /**
   * 추천 목록 생성
   * @param recommendVO
   * @return
   */
  public int create(RecommendVO recommendVO);
  
  /**
   * 전체 리스트
   * @return
   */
  public ArrayList<RecommendVO> list_all();
  
  /**
   * 삭제
   * @param recommendno
   * @return
   */
  public int delete(int recommendno);
}
