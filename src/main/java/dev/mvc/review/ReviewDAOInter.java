package dev.mvc.review;

import java.util.ArrayList;
import java.util.HashMap;

import dev.mvc.movie.MovieVO;


public interface ReviewDAOInter {
  /**
   * 등록, 추상메소드
   * @param reviewVO
   * @return
   */
  public int create(ReviewVO reviewVO);
  
  /**
   * 목록
   * @param 
   * @return
   */
  public ArrayList<ReviewVO> list();
  
  /**
   * 검색된 레코드 갯수
   * @param map
   * @return
   */
  public int search_count(HashMap<String, Object> hashMap);
  
  
  /**
   * 검색 + 페이징
   * @param reviewVO
   * @return
   */
  public ArrayList<ReviewVO> list_search_paging(ReviewVO reviewVO);
  
  /**
   * 조회
   * @param reviewno
   * @return
   */
  public ReviewVO read(int reviewno);
  
  /**
   * 글 수정
   * @param reviewVO
   * @return
   */
  public int update(ReviewVO reviewVO);
  
  /**
   * 파일 수정
   * @param reviewVO
   * @return
   */
  public int update_file(ReviewVO reviewVO);
  
  /**
   * 삭제
   * @param reviewno
   * @return
   */
  public int delete(int reviewno);
  
}
