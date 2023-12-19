package dev.mvc.mem;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
@Component("dev.mvc.mem.MemProc")
public class MemProc implements MemProcInter {
  @Autowired
  private MemDAOInter memDAO;
  
  public MemProc(){
    // System.out.println("-> MemberProc created.");
  }

  @Override
  public int checkID(String id) {
    int cnt = this.memDAO.checkID(id);
    return cnt;
  }

  @Override
  public int create(MemVO memVO) {
	int cnt = this.memDAO.create(memVO);
    return cnt;
  }

  @Override
  public ArrayList<MemVO> list() {
	ArrayList<MemVO> list = this.memDAO.list();
    return list;
  }

  @Override
  public MemVO read(int memno) {
	MemVO memVO = this.memDAO.read(memno);
    return memVO;
  }

  @Override
  public MemVO readById(String id) {
	MemVO memVO = this.memDAO.readById(id);
    return memVO;
  }

  @Override
  public boolean isMem(HttpSession session) {
	boolean sw = false; // 로그인하지 않은 것으로 초기화
    int grade = 99;
    
    // System.out.println("-> grade: " + session.getAttribute("grade"));
    if (session != null) {
      String id = (String)session.getAttribute("id");
      if (session.getAttribute("grade") != null) {
        grade = (int)session.getAttribute("grade");
      }
      
      if (id != null && grade <= 20){ // 관리자 + 회원
        sw = true;  // 로그인 한 경우
      }
    }
    
    return sw;
  }

  @Override
  public boolean isMemManager(HttpSession session) {
	boolean sw = false; // 로그인하지 않은 것으로 초기화
    int grade = 99;
    
    // System.out.println("-> grade: " + session.getAttribute("grade"));
    if (session != null) {
      String id = (String)session.getAttribute("id");
      if (session.getAttribute("grade") != null) {
        grade = (int)session.getAttribute("grade");
      }
      
      if (id != null && grade <= 10){ // 관리자 
        sw = true;  // 로그인 한 경우
      }
    }
    
    return sw;
  }

  @Override
  public int update(MemVO memVO) {
	int cnt = this.memDAO.update(memVO);
    return cnt;
  }

  @Override
  public int delete(int memno) {
	  int cnt = this.memDAO.delete(memno);
	  return cnt;
  }
  
  @Override
  public int login(HashMap<String, Object> map) {
    int cnt = this.memDAO.login(map);
    return cnt;
  }

@Override
public int passwd_check(HashMap<String, Object> map) {
	int cnt = this.memDAO.passwd_check(map);
    return cnt;
}

@Override
public int passwd_update(HashMap<String, Object> map) {
	int cnt = this.memDAO.passwd_update(map);
    return cnt;
}

@Override
public int readByMemno(String id) {
	int memno = this.memDAO.readByMemno(id);
	return memno;
}

@Override
public int sp_check(MemVO memVO) {
	int cnt = this.memDAO.sp_check(memVO);
	return cnt;
}

@Override
public int wd_check(MemVO memVO) {
	int cnt = this.memDAO.wd_check(memVO);
	return cnt;
}

@Override
public int mem_unregister(int memno) {
	int cnt = this.memDAO.mem_unregister(memno);
	return cnt;
}

}