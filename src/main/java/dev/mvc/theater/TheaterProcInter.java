package dev.mvc.theater;

import java.util.ArrayList;
import java.util.HashMap;

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
   * 수정
   * @param theaterno
   * @return
   */
  public int update(TheaterVO theaterVO);
  
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
