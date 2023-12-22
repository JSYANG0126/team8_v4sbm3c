package dev.mvc.reply;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import dev.mvc.mem.MemProcInter;
import dev.mvc.mem.MemVO;
import dev.mvc.review.ReviewProcInter;
import dev.mvc.review.ReviewVO;


@Controller
public class ReplyCont {
  @Autowired
  @Qualifier("dev.mvc.reply.ReplyProc")
  private ReplyProcInter replyProc;
  
  @Autowired
  @Qualifier("dev.mvc.mem.MemProc")
  private MemProcInter memProc;
  
  @Autowired
  @Qualifier("dev.mvc.review.ReviewProc") //@component("dev.mvc.comtents.MovieProc")
  private ReviewProcInter reviewProc;
  
  public ReplyCont() {
    System.out.println("-> ReplyCont created");
  }
  
  // 폼
  @RequestMapping(value="/reply/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    if (this.memProc.isMem(session) == true) {
      mav.setViewName("/reply/create"); // /WEB-INF/views/cate/create.jsp
    } else {
      mav.setViewName("/mem/login_need");
    }
    return mav;
  }
  
  // 등록 처리
  @RequestMapping(value="/reply/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpSession session, ReplyVO replyVO) { // 자동으로 cateVO 객체가 생성되고 폼의 값이 할당됨
    ModelAndView mav = new ModelAndView();
    
    if (this.memProc.isMem(session)) {
      mav.setViewName("/reply/msg"); // /WEB-INF/views/cate/msg.jsp
      
      int memno = (int)session.getAttribute("memno");
      replyVO.setMemno(memno);
      
      
      ReviewVO reviewVO = this.reviewProc.read(replyVO.getReviewno());
      int now_page = reviewVO.getNow_page();
      System.out.println("현재 페이지" + now_page);
      mav.addObject("replyVO", replyVO);
      
      int cnt = this.replyProc.create(replyVO);
      System.out.println("-> cnt: " + cnt);
      
      if (cnt == 1) {
        mav.addObject("now_page", now_page);
        mav.setViewName("redirect:/review/read.do?reviewno=" + replyVO.getReviewno());
      } else {
        mav.addObject("code", "create_fail");
        mav.addObject("cnt", cnt);
        mav.setViewName("/reply/msg");
      } 
    }  else {
        mav.setViewName("/mem/login_need"); // /WEB-INF/views/manager/login_need.jsp
    }
      
       // request.setAttribute("cnt", cnt);
  //    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
  
  // 수정폼
  @RequestMapping(value="/reply/update.do", method = RequestMethod.GET)
  public ModelAndView update(HttpSession session, int replyno) { // int cateno = (int)request.getParameter("cateno");
    ModelAndView mav = new ModelAndView();
    if (this.memProc.isMem(session)) {
      mav.setViewName("/reply/update");
        
      ReplyVO replyVO = this.replyProc.read(replyno);
      mav.addObject("replyVO", replyVO);
          
      ArrayList<ReplyVO> list = (ArrayList<ReplyVO>)this.replyProc.list();
      mav.addObject("list", list);
    } else {
      mav.setViewName("/mem/login_need"); 
    }
    
    
    return mav;
  }
  
  // 수정처리
  @RequestMapping(value="/reply/update.do", method = RequestMethod.POST)
  public ModelAndView update(HttpSession session, ReplyVO replyVO) { 
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/reply/msg"); 

    int cnt = this.replyProc.update(replyVO); 
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      mav.addObject("code", "update_success");
      mav.setViewName("redirect:/review/read.do?reviewno=" + replyVO.getReviewno());   
    } else {
      mav.setViewName("/reply/msg"); 
      mav.addObject("code", "update_fail");
    }
    
    mav.addObject("cnt", cnt); 
    
    return mav;
  }
  
  // 목록
  @RequestMapping(value="/reply/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_all(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    if (this.memProc.isMem(session)) {
      mav.setViewName("/reply/list_all"); // /WEB-INF/views/cate/list_all.jsp
      
      int memno = (int)session.getAttribute("memno");
      
      MemVO memVO = this.memProc.read(memno);
      String mname = (String)session.getAttribute("mname");
      memVO.setMname(mname);
     
      
      
      ArrayList<ReplyVO> list = this.replyProc.list();
      mav.addObject("list", list);
      mav.addObject("memVO", memVO);
      
    } else {
      mav.setViewName("/mem/login_need"); 
    }
    return mav;
  }
  
//  @RequestMapping(value="/reply/list_by_reviewno.do", method = RequestMethod.GET)
//  public ModelAndView list_by_reviewno(HashMap<Object,Object> hashMap) {
//    ModelAndView mav = new ModelAndView();
//    
//    mav.setViewName("/reply/list_by_reviewno"); // /WEB-INF/views/movie/list_by_genreno.jsp
//    
////    ReviewVO reviewVO = this.reviewProc.read(); // create.jsp에 카테고리 정보를 출력하기위한 목적
////    mav.addObject("reviewVO", reviewVO);
//
//    
//    // 검색하지 않는 경우
////    ArrayList<ReplyVO> list = this.replyProc.list_by_reviewno(replyVO);
//
//    
////    mav.addObject("list", list);
//    
//    return mav;
//  }  
  
  // 삭제폼
  @RequestMapping(value="/reply/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(HttpSession session , int replyno) { 
    ModelAndView mav = new ModelAndView();
    if (this.memProc.isMem(session)) {
      mav.setViewName("/reply/delete");
        
      ReplyVO replyVO = this.replyProc.read(replyno);
      mav.addObject("replyVO", replyVO);
        
      ArrayList<ReplyVO> list = this.replyProc.list();
      mav.addObject("list", list);
    } else {
      mav.setViewName("/mem/login_need"); 
    }
 
    return mav;
  }
  
  // 삭제 처리
  @RequestMapping(value="/reply/delete.do", method=RequestMethod.POST)
  public ModelAndView delete_proc(HttpSession session, int replyno) {   
    
    ModelAndView mav = new ModelAndView(); 
    if (this.memProc.isMem(session)) {

      ReplyVO replyVO = this.replyProc.read(replyno);
      int cnt = this.replyProc.delete(replyno);
        
      if (cnt == 1) {
        mav.addObject("code", "delete_success");
        mav.setViewName("redirect:/review/read.do?reviewno=" + replyVO.getReviewno());        
      } else {
        mav.addObject("code", "delete_fail");
        mav.setViewName("/reply/msg"); 
      }
      mav.addObject("cnt", cnt);   
    } else {
      mav.setViewName("/mem/login_need"); 
    }
      
    return mav;
  }
}
