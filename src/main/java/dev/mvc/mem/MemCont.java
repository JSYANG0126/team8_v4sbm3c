package dev.mvc.mem;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.manager.ManagerProcInter;
import dev.mvc.mlogin.MloginProcInter;
import dev.mvc.mlogin.MloginVO;
import dev.mvc.tool.MailTool;
 
@Controller
public class MemCont {
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc") //@component("dev.mvc.manager.ManagerProc")
  private ManagerProcInter managerProc;
	
  @Autowired
  @Qualifier("dev.mvc.mem.MemProc")
  private MemProcInter memProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.mlogin.MloginProc")
  private MloginProcInter mloginProc;
  
  public MemCont(){
    System.out.println("-> MemCont created.");
  }
  
  // http://localhost:9092/mem/checkID.do?id=user1
  /**
  * ID 중복 체크, JSON 출력
  * @return
  */
  @ResponseBody
  @RequestMapping(value="/mem/checkID.do", method=RequestMethod.GET ,
                         produces = "text/plain;charset=UTF-8" )
  public String checkID(String id) {
	  System.out.println("-> id: " + id);
	    
	    try {
	      Thread.sleep(3000); // 3 초 지연
	    }catch(Exception e) {
	      
	    }
	    
	    int cnt = this.memProc.checkID(id);
	   
	    JSONObject json = new JSONObject();
	    json.put("cnt", cnt); // 키와 값 = HashMap
	   
	    return json.toString(); // {"cnt":1} 
  }

  // http://localhost:9092/mem/create.do
  /**
  * 등록 폼
  * @return
  */
  @RequestMapping(value="/mem/create.do", method=RequestMethod.GET )
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mem/create"); // /WEB-INF/views/mem/create.jsp
   
    return mav; // forward
  }

  /**
   * 등록 처리
   * @param memVO
   * @return
   */
  @RequestMapping(value="/mem/create.do", method=RequestMethod.POST)
  public ModelAndView create(MemVO memVO){
    ModelAndView mav = new ModelAndView();
    
    // 중복 ID 검사
    int checkID_cnt = this.memProc.checkID(memVO.getId());
    if (checkID_cnt == 0) {
      // System.out.println("id: " + memVO.getId());
      
      memVO.setGrade(15); // 기본 회원 가입 등록 15 지정
      
      int cnt= memProc.create(memVO); // SQL insert
      
      if (cnt == 1) { // insert 레코드 개수, 회원 가입 성공
        
        mav.addObject("code", "create_success");
        mav.addObject("mname", memVO.getMname());  // 홍길동님(user4) 회원 가입을 축하합니다.
        mav.addObject("id", memVO.getId());
      } else { // 회원 가입 실패
        mav.addObject("code", "create_fail");
        // mav.addObject("cnt", 0);  // 추가된 레코드 없음.  
      }
      
      mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
      
    } else {
      mav.addObject("code", "duplicate_fail"); // 이미 사용중인 id임으로 가입 실패
      mav.addObject("cnt", 0);                       // 추가된 레코드 없음.      
    }

    mav.addObject("url", "/mem/msg");  // /mem/msg -> /mem/msg.jsp
    
    mav.setViewName("redirect:/mem/msg.do"); // POST -> GET -> /mem/msg.jsp
    
    return mav;
  }
  
  /**
   * 새로고침 방지, EL에서 param으로 접근, POST -> GET -> /mem/msg.jsp
   * @return
   */
  @RequestMapping(value="/mem/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav; // forward
  }
  
  /**
   * 목록 출력 가능
   * @param session
   * @return
   */
   @RequestMapping(value="/mem/list.do", method=RequestMethod.GET)
   public ModelAndView list(HttpSession session) {
     ModelAndView mav = new ModelAndView();
     
     if (this.managerProc.isManager(session) == true) {
       ArrayList<MemVO> list = this.memProc.list();
       mav.addObject("list", list);

       mav.setViewName("/mem/list"); // /webapp/WEB-INF/views/mem/list.jsp

     } else {
       mav.setViewName("/manager/login_need"); // /WEB-INF/views/manager/login_need.jsp
     }
         
     return mav;
   } 
   
   /**
    * 회원 조회
    * http://localhost:9092/mem/read.do?memno=1
    * 관리자, 회원 본인만 가능
    * @param memno
    * @return
    */
   @RequestMapping(value="/mem/read.do", method=RequestMethod.GET)
   public ModelAndView read(HttpSession session, HttpServletRequest request){
     ModelAndView mav = new ModelAndView();
     
     int memno = 0;
     if (this.memProc.isMem(session) || this.managerProc.isManager(session)) { 
       // 로그인한 경우

       if (this.memProc.isMem(session)) { // 회원으로 로그인
    	 // session을 사용하여 현재 로그인한 사용자의 memno 값만 읽음으로 다른 사용자의 
         // 정보를 조회 할 수 없음.
         memno = (int)session.getAttribute("memno");
         
       } else if (this.managerProc.isManager(session)) { // 관리자로 로그인
         // 관리자는 모든 회원의 정보를 조회 할 수 있어야함으로 parameter로 회원번호를 이용하여 조회
         memno = Integer.parseInt(request.getParameter("memno"));
         
       }

       MemVO memVO = this.memProc.read(memno);
       mav.addObject("memVO", memVO);
       mav.setViewName("/mem/read"); // /mem/read.jsp
       
     } else {
       // 로그인을 하지 않은 경우
       mav.setViewName("/mem/login_need"); // /webapp/WEB-INF/views/mem/login_need.jsp
     }
     
     return mav; // forward
   }
   
   /**
    * 회원 정보 수정 처리
    * @param memVO
    * @return
    */
   @RequestMapping(value="/mem/update.do", method=RequestMethod.POST)
   public ModelAndView update(MemVO memVO){
     ModelAndView mav = new ModelAndView();
     
     // System.out.println("id: " + memVO.getId());
     
     int cnt= this.memProc.update(memVO);
     
     if (cnt == 1) {
       mav.addObject("code", "update_success");
       mav.addObject("mname", memVO.getMname());  // 홍길동님(user4) 회원 정보를 변경했습니다.
       mav.addObject("id", memVO.getId());
     } else {
       mav.addObject("code", "update_fail");
     }

     mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
     mav.addObject("url", "/mem/msg");  // /mem/msg -> /mem/msg.jsp
     
     mav.setViewName("redirect:/mem/msg.do");
     
     return mav;
   }
   
   /**
    * 회원 삭제
    * @param memno
    * @return
    */
   @RequestMapping(value="/mem/delete.do", method=RequestMethod.GET)
   public ModelAndView delete(int memno){
     ModelAndView mav = new ModelAndView();
     
     MemVO memVO = this.memProc.read(memno); // 삭제할 레코드를 사용자에게 출력하기위해 읽음.
     mav.addObject("memVO", memVO);

     mav.setViewName("/mem/delete"); // /mem/delete.jsp
     
     return mav; // forward
   }
  
   /**
    * 회원 삭제 처리
    * @param memVO
    * @return
    */
   @RequestMapping(value="/mem/delete.do", method=RequestMethod.POST)
   public ModelAndView delete_proc(int memno){
     ModelAndView mav = new ModelAndView();
     
     // System.out.println("id: " + memVO.getId());
     // 삭제된 정보를 msg.jsp에 출력하기 위해, 삭제전에 회원 정보를 읽음.
     MemVO memVO = this.memProc.read(memno); 
     
     int cnt= this.memProc.delete(memno);

     if (cnt == 1) {
       mav.addObject("code", "delete_success");
       mav.addObject("mname", memVO.getMname());  // 홍길동님(user4) 회원 정보를 삭제했습니다.
       mav.addObject("id", memVO.getId());
       memVO.setGrade(99); // 탈퇴 회원 등록 99 지정
     } else {
       mav.addObject("code", "delete_fail");
     }

     mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
     mav.addObject("url", "/mem/msg");  // /mem/msg -> /mem/msg.jsp
     
     mav.setViewName("redirect:/mem/msg.do");
     
     return mav;
   }
   
//   /**
//    * 로그인 폼
//    * @return
//    */
//   // http://localhost:9091/mem/login.do 
//   @RequestMapping(value = "/mem/login.do", 
//                              method = RequestMethod.GET)
//   public ModelAndView login() {
//     ModelAndView mav = new ModelAndView();
//   
//     mav.setViewName("/mem/login_form");
//     return mav;
//   }
//
//   /**
//    * 로그인 처리
//    * @return
//    */
//   // http://localhost:9091/mem/login.do 
//   @RequestMapping(value = "/mem/login.do", 
//                              method = RequestMethod.POST)
//   public ModelAndView login_proc(HttpSession session,
//                                                    String id, 
//                                                    String passwd) {
//     ModelAndView mav = new ModelAndView();
//     HashMap<String, Object> map = new HashMap<String, Object>();
//     map.put("id", id);
//     map.put("passwd", passwd);
//     
//     int count = this.memProc.login(map); // id, passwd 일치 여부 확인
//     if (count == 1) { // 로그인 성공
//       // System.out.println(id + " 로그인 성공");
//       MemVO memVO = memProc.readById(id); // 로그인한 회원의 정보 조회
//       session.setAttribute("memno", memVO.getMemno());
//       session.setAttribute("id", id);
//       session.setAttribute("mname", memVO.getMname());
//       session.setAttribute("grade", memVO.getGrade());
//       
//       mav.setViewName("redirect:/index.do"); // 시작 페이지로 이동  
//     } else {
//       mav.addObject("url", "/mem/login_fail_msg"); // login_fail_msg.jsp, redirect parameter 적용
//      
//       mav.setViewName("redirect:/mem/msg.do"); // 새로고침 방지
//     }
//         
//     return mav;
//   }
   
   /**
    * 로그인 폼
    * @return
    */
   // http://localhost:9091/mem/login.do 
   @RequestMapping(value = "/mem/login.do", 
                              method = RequestMethod.GET)
   public ModelAndView login_cookie(HttpServletRequest request) {
     ModelAndView mav = new ModelAndView();
     
     Cookie[] cookies = request.getCookies();
     Cookie cookie = null;
   
     String ck_id = ""; // id 저장
     String ck_id_save = ""; // id 저장 여부를 체크
     String ck_passwd = ""; // passwd 저장
     String ck_passwd_save = ""; // passwd 저장 여부를 체크
   
     if (cookies != null) { // 쿠키가 존재한다면
       for (int i=0; i < cookies.length; i++){
         cookie = cookies[i]; // 쿠키 객체 추출
       
         if (cookie.getName().equals("ck_id")){
           ck_id = cookie.getValue(); 
         }else if(cookie.getName().equals("ck_id_save")){
           ck_id_save = cookie.getValue();  // Y, N
         }else if (cookie.getName().equals("ck_passwd")){
           ck_passwd = cookie.getValue();         // 1234
         }else if(cookie.getName().equals("ck_passwd_save")){
           ck_passwd_save = cookie.getValue();  // Y, N
         }
       }
     }
   
     //    <input type='text' class="form-control" name='id' id='id' 
     //            value='${ck_id }' required="required" 
     //            style='width: 30%;' placeholder="아이디" autofocus="autofocus">
     mav.addObject("ck_id", ck_id);
   
     //    <input type='checkbox' name='id_save' value='Y' 
     //            ${ck_id_save == 'Y' ? "checked='checked'" : "" }> 저장
     mav.addObject("ck_id_save", ck_id_save);
   
     mav.addObject("ck_passwd", ck_passwd);
     mav.addObject("ck_passwd_save", ck_passwd_save);
   
     mav.setViewName("/mem/login_form_ck"); // /mem/login_form_ck.jsp
     return mav;
   }
    
   /**
   * Cookie 기반 로그인 처리
   * @param request Cookie를 읽기위해 필요
   * @param response Cookie를 쓰기위해 필요
   * @param session 로그인 정보를 메모리에 기록
   * @param id  회원 아이디
   * @param passwd 회원 패스워드
   * @param id_save 회원 아이디 Cookie에 저장 여부
   * @param passwd_save 패스워드 Cookie에 저장 여부
   * @return
   */
   // http://localhost:9091/mem/login.do 
   @RequestMapping(value = "/mem/login.do", 
                             method = RequestMethod.POST)
   public ModelAndView login_cookie_proc(
                             HttpServletRequest request,
                             HttpServletResponse response,
                             HttpSession session,
                             String id,
                             String passwd,
                             @RequestParam(value="id_save", defaultValue="") String id_save,
                             @RequestParam(value="passwd_save", defaultValue="") String passwd_save) {
     ModelAndView mav = new ModelAndView();
     
     HashMap<String, Object> map = new HashMap<String, Object>();
     map.put("id", id);
     map.put("passwd", passwd);
     
     MemVO memVO = memProc.readById(id);
    
     int cnt = memProc.login(map);
     if (cnt == 1 && memProc.wd_check(memVO) == 0) { // 로그인 성공
       // System.out.println(id + " 로그인 성공");
    	
       // 로그인 내역 저장
       int memno = memProc.readByMemno(id);
       MloginVO mloginVO = new MloginVO();
       mloginVO.setMemno(memno);
       String ip=request.getRemoteAddr();
       mloginVO.setIp(ip);
       this.mloginProc.create(mloginVO);
       
       session.setAttribute("memno", memVO.getMemno()); // 서버의 메모리에 기록
       session.setAttribute("id", id);
       session.setAttribute("mname", memVO.getMname());
       session.setAttribute("grade", memVO.getGrade());
    
       // -------------------------------------------------------------------
       // id 관련 쿠기 저장
       // -------------------------------------------------------------------
       if (id_save.equals("Y")) { // id를 저장할 경우, Checkbox를 체크한 경우
         Cookie ck_id = new Cookie("ck_id", id);
         ck_id.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
         ck_id.setMaxAge(60 * 60 * 24 * 30); // 30 day, 초단위
         response.addCookie(ck_id); // id 저장
       } else { // N, id를 저장하지 않는 경우, Checkbox를 체크 해제한 경우
         Cookie ck_id = new Cookie("ck_id", "");
         ck_id.setPath("/");
         ck_id.setMaxAge(0);
         response.addCookie(ck_id); // id 저장
       }
       
       // id를 저장할지 선택하는  CheckBox 체크 여부
       Cookie ck_id_save = new Cookie("ck_id_save", id_save);
       ck_id_save.setPath("/");
       ck_id_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
       response.addCookie(ck_id_save);
       // -------------------------------------------------------------------
   
       // -------------------------------------------------------------------
       // Password 관련 쿠기 저장
       // -------------------------------------------------------------------
       if (passwd_save.equals("Y")) { // 패스워드 저장할 경우
         Cookie ck_passwd = new Cookie("ck_passwd", passwd);
         ck_passwd.setPath("/");
         ck_passwd.setMaxAge(60 * 60 * 24 * 30); // 30 day
         response.addCookie(ck_passwd);
       } else { // N, 패스워드를 저장하지 않을 경우
         Cookie ck_passwd = new Cookie("ck_passwd", "");
         ck_passwd.setPath("/");
         ck_passwd.setMaxAge(0);
         response.addCookie(ck_passwd);
       }
       // passwd를 저장할지 선택하는  CheckBox 체크 여부
       Cookie ck_passwd_save = new Cookie("ck_passwd_save", passwd_save);
       ck_passwd_save.setPath("/");
       ck_passwd_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
       response.addCookie(ck_passwd_save);
       // -------------------------------------------------------------------
    
       mav.setViewName("redirect:/index.do");  
     } else if(memProc.wd_check(memVO) == 1) {
    	 
    	 mav.addObject("code", "wd_check_fail");
    	 mav.addObject("url", "/mem/msg");  // /mem/msg -> /mem/msg.jsp
         
         mav.setViewName("redirect:/mem/msg.do");
     } else {
     
       mav.addObject("url", "/mem/login_fail_msg");
       mav.setViewName("redirect:/mem/msg.do"); 
     }
        
     return mav;
   }
   /**
    * 로그아웃 처리
    * @param session
    * @return
    */
   @RequestMapping(value="/mem/logout.do", 
                              method=RequestMethod.GET)
   public ModelAndView logout(HttpSession session){
     ModelAndView mav = new ModelAndView();
     session.invalidate(); // 모든 session 변수 삭제
     
     mav.setViewName("redirect:/index.do"); 
     
     return mav;
   }
   
   /**
   * 패스워드를 변경합니다.
   * http://localhost:9092/mem/passwd_update.do
   * @param memno
   * @return
   */
  @RequestMapping(value="/mem/passwd_update.do", method=RequestMethod.GET)
  public ModelAndView passwd_update(){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mem/passwd_update"); // passwd_update.jsp
    
    return mav;
  }
  
  /**
   * 패스워드 검사 
   * 로그인 실행 -> http://localhost:9092/mem/passwd_check.do?current_passwd=1234
   * @param session
   * @param current_passwd 현재 패스워드
   * @return 1: 일치, 0: 불일치
   */
  @ResponseBody
  @RequestMapping(value="/mem/passwd_check.do", method=RequestMethod.GET)
  public String passwd_check(HttpSession session, String current_passwd) {
    try {
      Thread.sleep(3000); // 3초
    } catch(Exception e) {

    }
    
    int memno = (int)session.getAttribute("memno");
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("memno", memno);
    map.put("passwd", current_passwd);
    int cnt = this.memProc.passwd_check(map);
    
    JSONObject json = new JSONObject();
    json.put("cnt", cnt);
    
    return json.toString();
  }
  
  /**
   * 패스워드 변경 처리
   * @param memno 회원 번호
   * @param current_passwd 현재 패스워드
   * @param new_passwd 새로운 패스워드
   * @return
   */
  @RequestMapping(value="/mem/passwd_update.do", method=RequestMethod.POST)
  public ModelAndView passwd_update(HttpSession session, String current_passwd, String new_passwd){
    ModelAndView mav = new ModelAndView();

    // int memno = 3; // 테스트
    int memno = (int)session.getAttribute("memno"); // 현재 로그인한 회원의 정보만 패스워드 변경 가능
    
    MemVO memVO = this.memProc.read(memno); // 패스워드를 변경하려는 회원 정보를 읽음
    mav.addObject("mname", memVO.getMname());  
    mav.addObject("id", memVO.getId());
    
    // 현재 패스워드 검사용 데이터
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("memno", memno);
    map.put("passwd", current_passwd);
    
    int cnt = memProc.passwd_check(map); // 현재 패스워드 검사
    int update_cnt = 0; // 변경된 패스워드 수
    
    if (cnt == 1) { // 현재 패스워드가 일치하는 경우
      map.put("passwd", new_passwd); // 새로운 패스워드를 저장
      update_cnt = this.memProc.passwd_update(map); // 패스워드 변경 처리
      
      if (update_cnt == 1) {
        mav.addObject("code", "passwd_update_success"); // 패스워드 변경 성공
      } else {
        cnt = 0;  // 패스워드는 일치했으나 변경하지는 못함.
        mav.addObject("code", "passwd_update_fail");       // 패스워드 변경 실패
      }
      
      mav.addObject("update_cnt", update_cnt);  // 변경된 패스워드의 갯수    
    } else {
      mav.addObject("code", "passwd_fail"); // 패스워드가 일치하지 않는 경우
    }

    mav.addObject("cnt", cnt); // 패스워드 일치 여부
    mav.addObject("url", "/mem/msg");  // /mem/msg -> /mem/msg.jsp
    
    mav.setViewName("redirect:/mem/msg.do");
    
    return mav;
  }

  /**
   * 아이디 찾기 폼
   */
  @RequestMapping(value="/mem/id_find.do", method=RequestMethod.GET )
  public ModelAndView id_find() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mem/id_find"); // /WEB-INF/views/mem/id_find.jsp
   
    return mav; // forward
  }
  
//  /**
//   * 아이디 찾기 처리
//   * @param mname
//   * @param tel
//   */
//  @RequestMapping(value="/mem/id_find.do", method=RequestMethod.POST )
//  public ModelAndView id_find(HttpSession session, MemVO memVO) {
//    ModelAndView mav = new ModelAndView();
//    
//    String mname = (String)session.getAttribute("mname");
//    String tel = (String)session.getAttribute("tel");
//    
//    if(memVO.getMname().equals(mname)) {
//    	if(memVO.getTel().equals(tel) ) {
//    		String id = memVO.getId();
//    		System.out.println("회원님의 id는 " + id + "입니다.");
//    	}
//    	else {
//    		
//    	}
//    } else {
//    	
//    }
//    
//    return mav;
//  }
  
  /**
   * 아이디 찾기 처리
   * @param mname
   * @param tel
   */
  @RequestMapping(value="/mem/id_find.do", method=RequestMethod.POST )
  public ModelAndView id_find(@RequestParam("mname") String mname, @RequestParam("tel") String tel) {
	  ModelAndView mav = new ModelAndView();

	  MemVO memVO = this.memProc.readByMname(mname);

//	  System.out.println("mname" + memVO.getMname());
//	  System.out.println("사용자의 tel: " + tel);	//form에서 값을 받아왔는지 확인용
//	  System.out.println("사용자의 찾은 tel: " + memVO.getTel());
	  
	  if(memVO != null &&memVO.getTel().equals(tel)) {
		  
		mav.addObject("id", memVO.getId());
	    mav.addObject("code", "id_find_success");
		mav.setViewName("redirect:/mem/msg.do"); 
	  } else {
		  
		  mav.addObject("code", "id_find_fail");
		  mav.setViewName("redirect:/mem/msg.do"); 
	  }
	  
  	  return mav;
  }
  
  /**
   * 비밀번호 찾기 폼
   * @param mname
   * @param tel
   * @param email
   */
  @RequestMapping(value="/mem/passwd_find.do", method=RequestMethod.GET )
  public ModelAndView passwd_find() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mem/passwd_find"); // /WEB-INF/views/mem/passwd_find.jsp
   
    return mav; // forward
  }
  
  /**
   * 비밀번호 찾기 처리
   * @param id
   * @param mname
   * @param tel
   */
  @RequestMapping(value="/mem/passwd_find.do", method=RequestMethod.POST )
  public ModelAndView passwd_find(@RequestParam("id") String id, @RequestParam("mname") String mname, @RequestParam("tel") String tel) {
	  ModelAndView mav = new ModelAndView();

	  MemVO memVO = this.memProc.readById(id);
	  
//	  System.out.println("받은 id -> " + memVO.getId());
//	  System.out.println("받은 mname -> " + memVO.getMname());
//	  System.out.println("받은 tel -> " + memVO.getTel());
//	  
//	  System.out.println("내 id -> " + memVO.getId());
//	  System.out.println("내 mname -> " + memVO.getMname());
//	  System.out.println("내 tel -> " + memVO.getTel());
	  
	  if(memVO != null &&memVO.getMname().equals(mname)&&memVO.getTel().equals(tel)) {
		  
		  MailTool mailTool = new MailTool();
		  
	      String receiver = memVO.getId();
	      String from = "dong865869@gmail.com";
		  String title = "회원님의 비밀번호입니다.";
		  String content = "회원님의 비밀번호는 " + memVO.getPasswd() + " 입니다.";
		  mailTool.send(receiver, from, title, content); // 메일 전송
		  
		  mav.addObject("code", "passwd_find_success");
		  mav.setViewName("redirect:/mem/msg.do"); 
		  
	  } else {
		  
		  mav.addObject("code", "passwd_find_fail");
		  mav.setViewName("redirect:/mem/msg.do"); 
	  }
	  
  	  return mav;
  }
  
  /**
   * 회원탈퇴 폼
   */
  @RequestMapping(value="/mem/mem_unregister.do", method=RequestMethod.GET )
  public ModelAndView mem_unregister(int memno) {
    ModelAndView mav = new ModelAndView();
    
    MemVO memVO = memProc.read(memno);
    mav.addObject("memVO", memVO);
    
    mav.setViewName("/mem/mem_unregister"); // /WEB-INF/views/mem/mem_unregister.jsp

    return mav; // forward
  }
  
  /**
   * 회원탈퇴 처리
   */
  @RequestMapping(value="/mem/mem_unregister.do", method=RequestMethod.POST )
  public ModelAndView mem_unregister(@RequestParam("passwd") String passwd, int memno, HttpSession session) {
	  ModelAndView mav = new ModelAndView();

	  MemVO memVO = memProc.read(memno);
	  
	  if(passwd.equals(memVO.getPasswd())) {
		  memProc.mem_unregister(memno);
		  
		  mav.addObject("code", "unregister_success");
		  session.invalidate(); // 모든 session 변수 삭제

		  mav.setViewName("redirect:/mem/msg.do"); 

	  } else {
		  mav.addObject("code", "unregister_fail");
		  mav.setViewName("redirect:/mem/msg.do"); 
	  }
	  
	  
  	  return mav;
  }
  
  
}