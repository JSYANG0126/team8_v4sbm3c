package dev.mvc.treply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.mem.MemVO;
import dev.mvc.tool.Tool;

@Component("dev.mvc.treply.TreplyProc")
public class TreplyProc implements TreplyProcInter {
  @Autowired
  private TreplyDAOInter treplyDAO;
  
  @Override
  public int create(TreplyVO treplyVO) {
    int cnt = this.treplyDAO.create(treplyVO);
    return cnt;
  }
  
  @Override
  public List<TreplyVO> list() {
    List<TreplyVO> list = treplyDAO.list();
    return list;
  }

  @Override
  public List<TreplyVO> list_by_theaterno(int theaterno) {
    List<TreplyVO> list = treplyDAO.list_by_theaterno(theaterno);
    String treply = "";
    
    // 특수 문자 변경
    for (TreplyVO replyVO:list) {
      treply = replyVO.getTreply();
      treply = Tool.convertChar(treply);
      replyVO.setTreply(treply);
    }
    return list;
  }


  @Override
  public int checkPw(Map<String, Object> map) {
    int count = treplyDAO.checkPw(map);
    return count;
  }

  @Override
  public int delete(int treplyno) {
    int count = treplyDAO.delete(treplyno);
    return count;
  }

  @Override
  public List<MemVO> list_by_theaterno_join(int theaterno) {
    List<MemVO> list = treplyDAO.list_by_theaterno_join(theaterno);
    return list;
  }

 
}
