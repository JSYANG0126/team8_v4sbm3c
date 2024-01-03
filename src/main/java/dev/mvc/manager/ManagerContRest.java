package dev.mvc.manager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.tool.Tool;

@RestController // REST 컨트롤러 선언
public class ManagerContRest {
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc") // "dev.mvc.manager.ManagerProc"라고 명명된 클래스
  private ManagerProcInter managerProc; // ManagerProcInter를 구현한 AdminProc 클래스의 객체를 자동으로 생성하여 할당

  public ManagerContRest() {
    System.out.println("-> ManagerContRest created.");
  }

  /**
   * Cookie 기반 로그인 처리
   * http://localhost:9091/admin/login_rest.do
   {
     
   }
   * @return
   */
  @PostMapping(path = "/manager/login_rest.do")
  public String login_proc(HttpServletResponse response, HttpSession session, @RequestBody ManagerVO managerVO) {

    JSONObject json = new JSONObject();

    int cnt = managerProc.login(managerVO);
    if (cnt == 1) { // 로그인 성공시 회원 정보 조회
      ManagerVO managerVO_read = managerProc.read_by_id(managerVO.getId()); // DBMS에서 id를 이용한 회원 조회
//      session.setAttribute("adminno", adminVO_read.getAdminno()); // 서버의 메모리에 기록
//      session.setAttribute("admin_id", adminVO_read.getId());
//      session.setAttribute("admin_mname", adminVO_read.getMname());
//      session.setAttribute("admin_grade", adminVO_read.getGrade());

      json.put("sw", true);
      json.put("adminno", managerVO_read.getManagerno());
    } else {
      json.put("sw", false);
    }

    return json.toString();
  }

  /**
   * 로그아웃 처리
   * @param session
   * @return
   */
  @GetMapping(path="/manager/logout_rest.do")
  public String logout(HttpSession session){
    JSONObject json = new JSONObject();
    // session.invalidate(); // 모든 session 변수 삭제
    
    json.put("logout", true);
    
    return json.toString();
  }
}