package dev.mvc.treply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.mvc.mem.MemVO;

public interface TreplyDAOInter {
  
  public int create(TreplyVO treplyVO);

  public List<TreplyVO> list();
  
  public List<TreplyVO> list_by_theaterno(int theaterno);
  
  public int checkPw(Map<String, Object> map);

  public int delete(int treplyno);
  
  public List<TreplyVO> list_by_theaterno_join(int theaterno);
  
  /**
   * 특정글 관련 전체 댓글 목록
   * @param theaterno
   * @return
   */
  public List<TreplyVO> list_by_theaterno_join_add(int theaterno);
  

}
