package dev.mvc.comments;

import java.util.ArrayList;

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
import dev.mvc.movie.MovieProcInter;
import dev.mvc.movie.MovieVO;
import dev.mvc.qna.QnaVO;
import dev.mvc.review.ReviewVO;


@Controller
public class CommentsCont {
  @Autowired
  @Qualifier("dev.mvc.comments.CommentsProc")
  private CommentsProcInter commentsProc;
  
  @Autowired
  @Qualifier("dev.mvc.mem.MemProc")
  private MemProcInter memProc;
  
  @Autowired
  @Qualifier("dev.mvc.movie.MovieProc") //@component("dev.mvc.comtents.MovieProc")
  private MovieProcInter movieProc;
  
  public CommentsCont() {
    System.out.println("-> CommentsCont created");
  }
  
  // 폼
  @RequestMapping(value="/comments/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    if (this.memProc.isMem(session) == true) {
      mav.setViewName("/comments/create"); // /WEB-INF/views/cate/create.jsp
    } else {
      mav.setViewName("/mem/login_need");
    }
    return mav;
  }
  
  // 등록 처리
  @RequestMapping(value="/comments/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpSession session, CommentsVO commentsVO) { // 자동으로 cateVO 객체가 생성되고 폼의 값이 할당됨
    ModelAndView mav = new ModelAndView();
    
    if (this.memProc.isMem(session)) {
      mav.setViewName("/comments/msg"); // /WEB-INF/views/cate/msg.jsp
      
      int memno = (int)session.getAttribute("memno");
      commentsVO.setMemno(memno);
      
      MemVO memVO = this.memProc.read(memno);
      commentsVO.setCname(memVO.getMname());
      

      MovieVO movieVO = this.movieProc.read(commentsVO.getMovieno());
      
      mav.addObject("commentsVO", commentsVO);
      
      int cnt = this.commentsProc.create(commentsVO);
      System.out.println("-> cnt: " + cnt);
      
      if (cnt == 1) {
        mav.addObject("now_page", movieVO.getNow_page());
        mav.setViewName("redirect:/movie/read.do?movieno=" + commentsVO.getMovieno());
      } else {
        mav.addObject("code", "create_fail");
        mav.addObject("cnt", cnt);
        mav.setViewName("/comments/msg");
      } 
    }  else {
        mav.setViewName("/mem/login_need"); // /WEB-INF/views/manager/login_need.jsp
    }
      
       // request.setAttribute("cnt", cnt);
  //    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
  
  // 수정폼
  @RequestMapping(value="/comments/update.do", method = RequestMethod.GET)
  public ModelAndView update(HttpSession session, int commentno) { // int cateno = (int)request.getParameter("cateno");
    ModelAndView mav = new ModelAndView();
    if (this.memProc.isMem(session)) {
      mav.setViewName("/comments/update");
        
      CommentsVO commentsVO = this.commentsProc.read(commentno);
      mav.addObject("commentsVO", commentsVO);
          
      ArrayList<CommentsVO> list = (ArrayList<CommentsVO>)this.commentsProc.list();
      mav.addObject("list", list);
    } else {
      mav.setViewName("/mem/login_need"); 
    }
    
    
    return mav;
  }
  
  // 수정처리
  @RequestMapping(value="/comments/update.do", method = RequestMethod.POST)
  public ModelAndView update(HttpSession session, CommentsVO commentsVO) { 
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/comments/msg"); 

    int cnt = this.commentsProc.update(commentsVO); 
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      mav.addObject("code", "update_success");
      mav.setViewName("redirect:/movie/read.do?movieno=" + commentsVO.getMovieno());   
    } else {
      mav.setViewName("/comments/msg"); 
      mav.addObject("code", "update_fail");
    }
    
    mav.addObject("cnt", cnt); 
    
    return mav;
  }
  
  // 목록
  @RequestMapping(value="/comments/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_all(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    if (this.memProc.isMem(session)) {
      mav.setViewName("/comments/list_all"); // /WEB-INF/views/cate/list_all.jsp
      
      int memno = (int)session.getAttribute("memno");
      
      MemVO memVO = this.memProc.read(memno);
      String mname = (String)session.getAttribute("mname");
      memVO.setMname(mname);
     
      
      
      ArrayList<CommentsVO> list = this.commentsProc.list();
      mav.addObject("list", list);
      mav.addObject("memVO", memVO);
      
    } else {
      mav.setViewName("/mem/login_need"); 
    }
    return mav;
  }
  
  @RequestMapping(value="/comments/list_by_movieno.do", method = RequestMethod.GET)
  public ModelAndView list_by_movieno(int movieno) {
    ModelAndView mav = new ModelAndView();

    mav.setViewName("/comments/list_by_movieno"); // /WEB-INF/views/movie/list_by_genreno.jsp
    
    MovieVO movieVO = this.movieProc.read(movieno); // create.jsp에 카테고리 정보를 출력하기위한 목적
    mav.addObject("movieVO", movieVO);

    
    // 검색하지 않는 경우
    ArrayList<CommentsVO> list = this.commentsProc.list_by_movieno(movieno);

    
    mav.addObject("list", list);
    
    return mav;
  }  
  
  // 삭제폼
  @RequestMapping(value="/comments/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(HttpSession session , int commentno) { 
    ModelAndView mav = new ModelAndView();
    if (this.memProc.isMem(session)) {
      mav.setViewName("/comments/delete");
        
      CommentsVO commentsVO = this.commentsProc.read(commentno);
      mav.addObject("commentsVO", commentsVO);
        
      ArrayList<CommentsVO> list = this.commentsProc.list();
      mav.addObject("list", list);
    } else {
      mav.setViewName("/mem/login_need"); 
    }
 
    return mav;
  }
  
  // 삭제 처리
  @RequestMapping(value="/comments/delete.do", method=RequestMethod.POST)
  public ModelAndView delete_proc(HttpSession session, int commentno) {   
    
    ModelAndView mav = new ModelAndView(); 
    if (this.memProc.isMem(session)) {

      CommentsVO commentsVO = this.commentsProc.read(commentno);
      int cnt = this.commentsProc.delete(commentno);
        
      if (cnt == 1) {
        mav.addObject("code", "delete_success");
        mav.setViewName("redirect:/movie/read.do?movieno=" + commentsVO.getMovieno());        
      } else {
        mav.addObject("code", "delete_fail");
        mav.setViewName("/comments/msg"); 
      }
      mav.addObject("cnt", cnt);   
    } else {
      mav.setViewName("/mem/login_need"); 
    }
      
    return mav;
  }
}
