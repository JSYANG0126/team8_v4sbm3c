package dev.mvc.nice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.nice.NiceProc")
public class NiceProc implements NiceProcInter {
  @Autowired
  private NiceDAOInter niceDAO;
  
  @Override
  public int create(NiceVO niceVO) {
    int cnt = this.niceDAO.create(niceVO);
    return cnt;
  }

  @Override
  public int nice_by_mem_cnt(NiceVO niceVO) {
    int cnt = this.niceDAO.nice_by_mem_cnt(niceVO);
    return cnt;
  }

  @Override
  public int nice_cnt(int reviewno) {
    int cnt = this.niceDAO.nice_cnt(reviewno);
    return cnt;
  }

  @Override
  public int delete(NiceVO niceVO) {
    int cnt = this.niceDAO.delete(niceVO);
    return cnt;
  }

}
