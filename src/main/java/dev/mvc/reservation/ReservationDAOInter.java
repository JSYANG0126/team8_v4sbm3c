package dev.mvc.reservation;

import java.util.ArrayList;


public interface ReservationDAOInter {
  /**
   * 목록 생성
   * @param reservationVO
   * @return
   */
  public int create(ReservationVO reservationVO);
  
  /**
   * 전체 목록
   * @return
   */
  public ArrayList<ReservationVO> list_all();
  
  /**
   * 조회
   * @return
   */
  public ReservationVO read(int reservationno);
  
  /**
   * 수정
   * @param reservationVO
   * @return
   */
  public int update(ReservationVO reservationVO);
}
