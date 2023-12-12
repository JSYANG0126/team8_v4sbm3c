package dev.mvc.theater;

import java.util.ArrayList;
import java.util.HashMap;

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

  @Override
  public TheaterVO read(int theaterno) {
    TheaterVO theaterVO = this.theaterDAO.read(theaterno);
    return theaterVO;
  }

  @Override
  public int update(TheaterVO theaterVO) {
    int cnt = this.theaterDAO.update(theaterVO);
    return cnt;
  }

  @Override
  public int map(HashMap<String, Object> map) {
    int cnt = this.theaterDAO.map(map);
    return cnt;
  }

  @Override
  public int delete(int theaterno) {
    int cnt = this.theaterDAO.delete(theaterno);
    return cnt;
  }

}
