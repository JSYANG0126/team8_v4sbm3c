package dev.mvc.good;

public interface GoodDAOInter {
  
  public int create(GoodVO goodVO);
  
  public int good_by_mem_cnt(GoodVO goodVO);
  
  public int good_cnt(int movieno);

  public int delete(GoodVO goodVO);
}
