package dev.mvc.recommend;

import java.util.ArrayList;

import dev.mvc.movie.MovieVO;

public interface RecommendDAOInter {
  
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
   * 전체 리스트 + 페이징
   * @return
   */
  public ArrayList<RecommendVO> list_recom(MovieVO movieVO);
  
  /**
   * 조회
   * @param recommendno
   * @return
   */
  public RecommendVO read(int recommendno);
  
  /**
   * 삭제
   * @param recommendno
   * @return
   */
  public int delete(int recommendno);
  
  

}
