package dev.mvc.good;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.good.GoodProc")
public class GoodProc implements GoodProcInter {
  @Autowired
  private GoodDAOInter goodDAO;
  
  @Override
  public int create(GoodVO goodVO) {
    int cnt = this.goodDAO.create(goodVO);
    return cnt;
  }

  @Override
  public int good_by_mem_cnt(GoodVO goodVO) {
    int cnt = this.goodDAO.good_by_mem_cnt(goodVO);
    return cnt;
  }

  @Override
  public int good_cnt(int movieno) {
    int cnt = this.goodDAO.good_cnt(movieno);
    return cnt;
  }

  @Override
  public int delete(GoodVO goodVO) {
    int cnt = this.goodDAO.delete(goodVO);
    return cnt;
  }

  @Override
  public int delete_by_movieno(int movieno) {
    int cnt = this.goodDAO.delete_by_movieno(movieno);
    return cnt;
  }

  
}
