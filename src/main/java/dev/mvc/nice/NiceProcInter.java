package dev.mvc.nice;

public interface NiceProcInter {
  
  public int create(NiceVO niceVO);
  
  public int nice_by_mem_cnt(NiceVO niceVO);
  
  public int nice_cnt(int reviewno);

  public int delete(NiceVO niceVO);
}
