package dev.mvc.theater;

import java.util.ArrayList;
import java.util.HashMap;

import dev.mvc.movie.MovieVO;

public interface TheaterProcInter {

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
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param genreno          카테고리번호 
   * @param now_page      현재 페이지
   * @param word 검색어
   * @param list_file 목록 파일명
   * @param search_count 검색 레코드수   
   * @return 페이징 생성 문자열
   */ 
  public String pagingBox(int now_page, String word, String list_file, int search_count);
  
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
