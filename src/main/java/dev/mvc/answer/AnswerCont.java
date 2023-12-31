package dev.mvc.answer;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.comments.CommentsVO;
import dev.mvc.mem.MemProcInter;
import dev.mvc.mem.MemVO;
import dev.mvc.movie.MovieProcInter;
import dev.mvc.movie.MovieVO;
import dev.mvc.qna.QnaProcInter;
import dev.mvc.qna.QnaVO;

@Controller
public class AnswerCont {
  @Autowired
  @Qualifier("dev.mvc.answer.AnswerProc")
  private AnswerProcInter answerProc;
  @Autowired
  @Qualifier("dev.mvc.mem.MemProc")
  private MemProcInter memProc;
  
  @Autowired
  @Qualifier("dev.mvc.qna.QnaProc") //@component("dev.mvc.comtents.MovieProc")
  private QnaProcInter qnaProc;
  
  public AnswerCont() {
    System.out.println("-> AnswerCont created");
  }
  
  // 폼
  @RequestMapping(value="/answer/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    if (this.memProc.isMem(session) == true) {
      mav.setViewName("/answer/create"); // /WEB-INF/views/cate/create.jsp
    } else {
      mav.setViewName("/mem/login_need");
    }
    return mav;
  }
  
  // 등록 처리
  @RequestMapping(value="/answer/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpSession session, AnswerVO answerVO) { // 자동으로 cateVO 객체가 생성되고 폼의 값이 할당됨
    ModelAndView mav = new ModelAndView();
    
    if (this.memProc.isMem(session)) {
      mav.setViewName("/answer/msg"); // /WEB-INF/views/cate/msg.jsp
      
      int memno = (int)session.getAttribute("memno");
      answerVO.setMemno(memno);
      
      MemVO memVO = this.memProc.read(memno);
      answerVO.setAname(memVO.getMname());
      
      mav.addObject("answerVO", answerVO);
      
      int cnt = this.answerProc.create(answerVO);
      System.out.println("-> cnt: " + cnt);
      
      if (cnt == 1) {
        mav.addObject("code", "create_success");
        mav.setViewName("redirect:/qna/read.do?qnano=" + answerVO.getQnano());
      } else {
        mav.addObject("code", "create_fail");
        mav.setViewName("/answer/msg");
      } 
      mav.addObject("cnt", cnt);
    }  else {
        mav.setViewName("/mem/login_need"); // /WEB-INF/views/manager/login_need.jsp
    }
      
       // request.setAttribute("cnt", cnt);
  //    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
  
  // 수정폼
  @RequestMapping(value="/answer/update.do", method = RequestMethod.GET)
  public ModelAndView update(HttpSession session, int answerno) { // int cateno = (int)request.getParameter("cateno");
    ModelAndView mav = new ModelAndView();
    if (this.memProc.isMem(session)) {
      mav.setViewName("/answer/update");
        
      AnswerVO answerVO = this.answerProc.read(answerno);
      mav.addObject("answerVO", answerVO);
          
      ArrayList<AnswerVO> list = (ArrayList<AnswerVO>)this.answerProc.list();
      mav.addObject("list", list);
    } else {
      mav.setViewName("/mem/login_need"); 
    }
    
    
    return mav;
  }
  
  // 수정처리
  @RequestMapping(value="/answer/update.do", method = RequestMethod.POST)
  public ModelAndView update(HttpSession session, AnswerVO answerVO) { 
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/answer/msg"); 

    int cnt = this.answerProc.update(answerVO); 
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      mav.addObject("code", "update_success");
      mav.setViewName("redirect:/qna/read.do?qnano=" + answerVO.getQnano()); 
    } else {
      mav.setViewName("/answer/msg"); 
      mav.addObject("code", "update_fail");
    }
    
    mav.addObject("cnt", cnt); 
    
    return mav;
  }
  
  // 목록
  @RequestMapping(value="/answer/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_all(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    if (this.memProc.isMem(session)) {
      mav.setViewName("/answer/list_all"); // /WEB-INF/views/cate/list_all.jsp
      
      int memno = (int)session.getAttribute("memno");
      
      MemVO memVO = this.memProc.read(memno);
      String mname = (String)session.getAttribute("mname");
      memVO.setMname(mname);
     
      
      
      ArrayList<AnswerVO> list = this.answerProc.list();
      mav.addObject("list", list);
      mav.addObject("memVO", memVO);
      
    } else {
      mav.setViewName("/mem/login_need"); 
    }
    return mav;
  }
  
  @RequestMapping(value="/answer/list_by_qnano.do", method = RequestMethod.GET)
  public ModelAndView list_by_qnano(int qnano) {
    ModelAndView mav = new ModelAndView();

    mav.setViewName("/answer/list_by_qnano"); // /WEB-INF/views/movie/list_by_genreno.jsp
    
    QnaVO qnaVO = this.qnaProc.read(qnano); // create.jsp에 카테고리 정보를 출력하기위한 목적
    mav.addObject("qnaVO", qnaVO);

    
    // 검색하지 않는 경우
    ArrayList<AnswerVO> list = this.answerProc.list_by_qnano(qnano);

    
    mav.addObject("list", list);
    
    return mav;
  }  
  
  // 삭제폼
  @RequestMapping(value="/answer/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(HttpSession session , int answerno) { 
    ModelAndView mav = new ModelAndView();
    if (this.memProc.isMem(session)) {
      mav.setViewName("/answer/delete");
        
      AnswerVO answerVO = this.answerProc.read(answerno);
      mav.addObject("answerVO", answerVO);
        
      ArrayList<AnswerVO> list = this.answerProc.list();
      mav.addObject("list", list);
    } else {
      mav.setViewName("/mem/login_need"); 
    }
 
    return mav;
  }
  
  // 삭제 처리
  @RequestMapping(value="/answer/delete.do", method=RequestMethod.POST)
  public ModelAndView delete_proc(HttpSession session, int answerno) {   
    
    ModelAndView mav = new ModelAndView(); 
    if (this.memProc.isMem(session)) {

      AnswerVO answerVO = this.answerProc.read(answerno);
      int cnt = this.answerProc.delete(answerno);
        
      if (cnt == 1) {
        mav.addObject("code", "delete_success");
        mav.setViewName("redirect:/qna/read.do?qnano=" + answerVO.getQnano());      
      } else {
        mav.addObject("code", "delete_fail");
        mav.setViewName("/answer/msg"); 
      }
      mav.addObject("cnt", cnt);   
    } else {
      mav.setViewName("/mem/login_need"); 
    }
      
    return mav;
  }
}
