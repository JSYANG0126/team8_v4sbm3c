package dev.mvc.theater;

public interface TheaterDAOInter {
  
  /**
   * 영화관 추가
   * @param theaterVO
   * @return
   */
  public int create(TheaterVO theaterVO);

}
