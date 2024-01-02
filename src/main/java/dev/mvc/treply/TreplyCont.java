package dev.mvc.treply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import dev.mvc.mem.MemProcInter;
import dev.mvc.mem.MemVO;
import dev.mvc.review.ReviewProcInter;
import dev.mvc.review.ReviewVO;
import dev.mvc.theater.TheaterProcInter;


@Controller
public class TreplyCont {
  @Autowired
  @Qualifier("dev.mvc.treply.TreplyProc")
  private TreplyProcInter treplyProc;
  
  @Autowired
  @Qualifier("dev.mvc.mem.MemProc")
  private MemProcInter memProc;
  
  @Autowired
  @Qualifier("dev.mvc.theater.TheaterProc")
  private TheaterProcInter theaterProc;
  
  public TreplyCont() {
    System.out.println("-> TreplyCont created");
  }
  
  @ResponseBody
  @RequestMapping(value = "/treply/create.do",
                            method = RequestMethod.POST,
                            produces = "text/plain;charset=UTF-8")
  public String create(TreplyVO treplyVO) {
    int cnt = treplyProc.create(treplyVO);
    
    JSONObject obj = new JSONObject();
    obj.put("cnt",cnt);
 
    return obj.toString(); // {"cnt":1}
  }
  
  @RequestMapping(value="/treply/list.do", method=RequestMethod.GET)
  public ModelAndView list(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if (memProc.isMemManager(session)) {
      List<TreplyVO> list = treplyProc.list();
      
      mav.addObject("list", list);
      mav.setViewName("/reply/list"); // /webapp/reply/list.jsp

    } else {
      mav.addObject("return_url", "/reply/list.do"); // 로그인 후 이동할 주소 ★
      
      mav.setViewName("redirect:/member/login.do"); // /WEB-INF/views/member/login_ck_form.jsp
    }
    
    return mav;
  }

  /**
   <xmp>
   http://localhost:9090/ojt/reply/list_by_contentsno.do?contentsno=1
   글이 없는 경우: {"list":[]}
   글이 있는 경우
   {"list":[
            {"memberno":1,"rdate":"2019-12-18 16:46:43","passwd":"123","replyno":3,"content":"댓글 3","contentsno":1}
            ,
            {"memberno":1,"rdate":"2019-12-18 16:46:39","passwd":"123","replyno":2,"content":"댓글 2","contentsno":1}
            ,
            {"memberno":1,"rdate":"2019-12-18 16:46:35","passwd":"123","replyno":1,"content":"댓글 1","contentsno":1}
            ] 
   }
   </xmp>  
   * @param contentsno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/treply/list_by_theaterno.do",
                            method = RequestMethod.GET,
                            produces = "text/plain;charset=UTF-8")
  public String list_by_theaterno(int theaterno) {
    List<TreplyVO> list = treplyProc.list_by_theaterno(theaterno);
    
    JSONObject obj = new JSONObject();
    obj.put("list", list);
 
    return obj.toString(); 

  }
  
  /**
  컨텐츠별 댓글 목록 
  {
  "list":[
           {
             "memberno":1,
             "rdate":"2019-12-18 16:46:35",
              "passwd":"123",
             "replyno":1,
             "id":"user1",
              "content":"댓글 1",
             "contentsno":1
           }
           ,
           {
             "memberno":1,
             "rdate":"2019-12-18 16:46:35",
             "passwd":"123",
             "replyno":1,
             "id":"user1",
             "content":"댓글 1",
             "contentsno":1
           }
         ]
  }
  * http://localhost:9093/resort/reply/list_by_contentsno_join.do?contentsno=53
  * @param contentsno
  * @return
  */
 @ResponseBody
 @RequestMapping(value = "/treply/list_by_theaterno_join.do",
                             method = RequestMethod.GET,
                             produces = "text/plain;charset=UTF-8")
 public String list_by_theaterno_join(int theaterno) {
   // String msg="JSON 출력";
   // return msg;
   List<MemVO> list = treplyProc.list_by_theaterno_join(theaterno);
   
   JSONObject obj = new JSONObject();
   obj.put("list", list);

   return obj.toString();     
 }
 
 
 /**
  * 패스워드를 검사한 후 삭제 
  * http://localhost:9090/resort/reply/delete.do?replyno=1&passwd=1234
  * {"delete_cnt":0,"passwd_cnt":0}
  * {"delete_cnt":1,"passwd_cnt":1}
  * @param replyno
  * @param passwd
  * @return
  */
 @ResponseBody
 @RequestMapping(value = "/treply/delete.do", 
                             method = RequestMethod.POST,
                             produces = "text/plain;charset=UTF-8")
 public String delete(int treplyno, String pw) {
   Map<String, Object> map = new HashMap<String, Object>();
   map.put("treplyno", treplyno);
   map.put("pw", pw);
   
   int passwd_cnt = treplyProc.checkPw(map); // 패스워드 일치 여부, 1: 일치, 0: 불일치
   int delete_cnt = 0;                                    // 삭제된 댓글
   if (passwd_cnt == 1) { // 패스워드가 일치할 경우
     delete_cnt = treplyProc.delete(treplyno); // 댓글 삭제
   }
   
   JSONObject obj = new JSONObject();
   obj.put("passwd_cnt", passwd_cnt); // 패스워드 일치 여부, 1: 일치, 0: 불일치
   obj.put("delete_cnt", delete_cnt); // 삭제된 댓글
   
   return obj.toString();
 }
 
  
  
}
