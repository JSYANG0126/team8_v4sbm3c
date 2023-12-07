package dev.mvc.theater;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



//Controller가 사용하는 이름
@Component("dev.mvc.theater.TheaterProc")
public class TheaterProc implements TheaterProcInter {
  @Autowired // GenreDAOInter interface 구현한 객체를 만들어 자동으로 할당해라.
  private TheaterDAOInter theaterDAO;

  @Override
  public int create(TheaterVO theaterVO) {
    int cnt = this.theaterDAO.create(theaterVO);
    return cnt;
  }

  @Override
  public ArrayList<TheaterVO> list_all() {
    ArrayList<TheaterVO> list = this.theaterDAO.list_all();
    return list;
  }

}
