package dev.mvc.reservation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//Controller가 사용하는 이름
@Component("dev.mvc.reservation.ReservationProc")
public class ReservationProc implements ReservationProcInter  {
  @Autowired // GenreDAOInter interface 구현한 객체를 만들어 자동으로 할당해라.
  private ReservationDAOInter reservationDAO;

  @Override
  public int create(ReservationVO reservationVO) {
    int cnt = this.reservationDAO.create(reservationVO);
    return cnt;
  }
  
  @Override
  public ArrayList<ReservationVO> list_all() {
    ArrayList<ReservationVO> list = this.reservationDAO.list_all();
    return list;
  }
  
  @Override
  public ReservationVO read(int reservationno) {
    ReservationVO reservationVO = this.reservationDAO.read(reservationno);
    return reservationVO;
  }

  @Override
  public int update(ReservationVO reservationVO) {
    int cnt = this.reservationDAO.update(reservationVO);
    return cnt;
  }

  @Override
  public int delete(int reservationno) {
    int cnt = this.reservationDAO.delete(reservationno);
    return cnt;
  }

 
 

  
  

}
