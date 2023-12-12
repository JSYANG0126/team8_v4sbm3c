package dev.mvc.chat;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.answer.AnswerVO;
import dev.mvc.manager.ManagerProcInter;
import dev.mvc.mem.MemProcInter;
import dev.mvc.mem.MemVO;
import dev.mvc.movie.Movie;
import dev.mvc.movie.MovieVO;
import dev.mvc.tool.Tool;

@Controller
public class ChatCont {
  @Autowired
  @Qualifier("dev.mvc.chat.ChatProc")
  private ChatProcInter chatProc;
  
  @Autowired
  @Qualifier("dev.mvc.mem.MemProc")
  private MemProcInter memProc;
  
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc") // "dev.mvc.admin.AdminProc"라고 명명된 클래스
  private ManagerProcInter managerProc; // AdminProcInter를 구현한 AdminProc 클래스의 객체를 자동으로 생성하여 할당
  
    // 채팅 목록
   @RequestMapping(value="/chat/list.do", method = RequestMethod.GET)
   public ModelAndView list_all(HttpSession session) {
     ModelAndView mav = new ModelAndView();
     if (this.memProc.isMem(session)) {
       mav.setViewName("/chat/list"); // /WEB-INF/views/cate/list_all.jsp
       
       int memno = (int)session.getAttribute("memno");
    
       ArrayList<ChatVO> list = this.chatProc.list();
       mav.addObject("list", list);
       
     } else {
       mav.setViewName("/mem/login_need"); 
     }
     return mav;
   }
   
  // 삭제폼
  @RequestMapping(value="/chat/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(HttpSession session , int chatno) { 
    ModelAndView mav = new ModelAndView();
    if (this.managerProc.isManager(session) == true) {
      mav.setViewName("/chat/delete");
        
      ChatVO chatVO = this.chatProc.read(chatno);
      mav.addObject("chatVO", chatVO);
        
      ArrayList<ChatVO> list = this.chatProc.list();
      mav.addObject("list", list);
    } else {
      mav.setViewName("/manager/login_need"); 
    }
 
    return mav;
  }
  
  // 삭제 처리
  @RequestMapping(value="/chat/delete.do", method=RequestMethod.POST)
  public ModelAndView delete_proc(HttpSession session, int chatno) { // <form> 태그의 값이 자동으로 저장됨
    ModelAndView mav = new ModelAndView();
    
    if (this.managerProc.isManager(session) == true) {
      int cnt = this.chatProc.delete(chatno); // 카테고리 삭제
      
      if (cnt == 1) {
        mav.setViewName("redirect:/chat/list.do");       // 자동 주소 이동, Spring 재호출 
      } else {
        mav.addObject("code", "delete_fail");
        mav.setViewName("/chat/msg"); // /WEB-INF/views/genre/msg.jsp
      }
      mav.addObject("cnt", cnt);   
    } else {
      mav.setViewName("/manager/login_need"); // /WEB-INF/views/manager/login_need.jsp
    }
    
    return mav;
  }
  
}
