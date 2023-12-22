package dev.mvc.movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface MovieDAOInter {
  /**
   * 등록, 추상메소드
   * @param movieVO
   * @return
   */
  public int create(MovieVO movieVO);

  /**
   * 모든 카테고리에 등록된 글목록
   * @return
   */
  public ArrayList<MovieVO> list_all();
  
  /**
   * 특정 카테고리의 등록된 글목록
   * @return
   */
  public ArrayList<MovieVO> list_by_genreno(int genreno);
  
  /**
   * 조회
   * @param movieno
   * @return
   */
  public MovieVO read(int movieno);
  
  /**
   * map 등록 수정 삭제
   * @param map
   * @return 수정된 레코드 갯수
   */
  public int map(HashMap<String, Object> map);
  
  /**
   * youtube 등록 수정 삭제
   * @param youtube
   * @return 수정된 레코드 갯수
   */
  public int youtube(HashMap<String, Object> youtube);
  
  /**
   * 카테고리별 검색 목록
   * @param hashMap
   * @return
   */
  public ArrayList<MovieVO> list_by_genreno_search(HashMap<String, Object> hashMap);
  
  /**
   * 카테고리별 검색된 레코드 갯수
   * @param map
   * @return
   */
  public int search_count(HashMap<String, Object> hashMap);
  
  /**
   * 카테고리별 검색 목록 + 페이징
   * @param movieVO
   * @return
   */
  public ArrayList<MovieVO> list_by_genreno_search_paging(MovieVO movieVO);
  
  /**
   * 패스워드 검사
   * @param hashMap
   * @return
   */
  public int password_check(HashMap<String, Object> hashMap);
  
  /**
   * 글 정보 수정
   * @param movieVO
   * @return 처리된 레코드 갯수
   */
  public int update_text(MovieVO movieVO);
  
  /**
   * 파일 정보 수정
   * @param movieVO
   * @return 처리된 레코드 갯수
   */
  public int update_file(MovieVO movieVO);
  
  /**
   * 삭제
   * @param movieno
   * @return 삭제된 레코드 갯수
   */
  public int delete(int movieno);
  
  /**
   * FK genreno 값이 같은 레코드 갯수 산출
   * @param genreno
   * @return
   */
  public int count_by_genreno(int genreno);
  
  /**
   * 특정 카테고리에 속한 모든 레코드 삭제
   * @param genreno
   * @return 삭제된 레코드 갯수
   */
  public int delete_by_genreno(int genreno);
  
  public int read_count(MovieVO movieVO);
  
}
 