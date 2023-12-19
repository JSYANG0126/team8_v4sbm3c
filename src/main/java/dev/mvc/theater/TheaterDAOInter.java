package dev.mvc.theater;

import java.util.ArrayList;
import java.util.HashMap;

import dev.mvc.movie.MovieVO;

public interface TheaterDAOInter {
  
  /**
   * 영화관 추가
   * @param theaterVO
   * @return
   */
  public int create(TheaterVO theaterVO);
  
  /**
   * 전체 목록
   * @return
   */
  public ArrayList<TheaterVO> list_all(); 
  
  /**
   * 특정 목록
   * @param theaterno
   * @return
   */
  public TheaterVO read(int theaterno);
  
  /**
   * 카테고리별 검색 목록
   * @param hashMap
   * @return
   */
  public ArrayList<TheaterVO> list_by_search(HashMap<String, Object> hashMap);
  
  /**
   * 카테고리별 검색된 레코드 갯수
   * @param map
   * @return
   */
  public int search_count(HashMap<String, Object> hashMap);
  
  /**
   * 카테고리별 검색 목록 + 페이징
   * @param theaterVO
   * @return
   */
  public ArrayList<TheaterVO> list_by_search_paging(TheaterVO theaterVO);
  
  /**
   * 수정
   * @param theaterno
   * @return
   */
  public int update(TheaterVO theaterVO);
  
  /**
   * 파일 수정
   * @param theaterVO
   * @return
   */
  public int update_file(TheaterVO theaterVO);
  
  /**
   * 패스워드 검사
   */
  public int password_check(HashMap<String, Object> hashMap);
  
    /**
   * map 등록 수정 삭제
   * @param map
   * @return 수정된 레코드 갯수
   */
  public int map(HashMap<String, Object> map);
  
  /**
   * 삭제
   * @param theaterno
   * @return
   */
  public int delete(int theaterno);
    

}
