package dev.mvc.review;

import java.util.ArrayList;
import java.util.HashMap;


public interface ReviewProcInter {
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
   * @param hashMap
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
   * 페이지
   * @param now_page
   * @param word
   * @param list_file
   * @return
   */
  public String pagingBox(int now_page, String word, String list_file, int search_count);
  
  
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
