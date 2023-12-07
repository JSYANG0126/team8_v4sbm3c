package dev.mvc.theater;

import java.util.ArrayList;

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

}
