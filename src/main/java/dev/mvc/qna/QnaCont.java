package dev.mvc.qna;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import dev.mvc.qna.QnaVO;

@Controller
public class QnaCont {
  @Autowired
  @Qualifier("dev.mvc.qna.QnaProc")
  private QnaProcInter qnaProc;
  
  public QnaCont() {
    System.out.println("-> QnaCont created.");  
  }
  // 폼
  @RequestMapping(value="/qna/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/create"); // /WEB-INF/views/cate/create.jsp
    
    return mav;
  }
  
  // 등록 처리
  @RequestMapping(value="/qna/create.do", method = RequestMethod.POST)
  public ModelAndView create(QnaVO qnaVO) { // 자동으로 cateVO 객체가 생성되고 폼의 값이 할당됨
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/msg"); // /WEB-INF/views/cate/msg.jsp
    
    int cnt = this.qnaProc.create(qnaVO);
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      mav.addObject("code", "create_success");
      mav.setViewName("redirect:/qna/list_all.do");
    } else {
      mav.addObject("code", "create_fail");
      mav.setViewName("/qna/msg");
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
  
  // 목록
  @RequestMapping(value="/qna/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_all(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/list_all"); // /WEB-INF/views/cate/list_all.jsp
      
    ArrayList<QnaVO> list = this.qnaProc.list();
    mav.addObject("list", list);

    return mav;
  }
  
  /**
   * 조회
   * http://localhost:9091/qna/read.do?qnano=1
   * @return
   */
  @RequestMapping(value="/qna/read.do", method = RequestMethod.GET)
  public ModelAndView read(int qnano) { // int cateno = (int)request.getParameter("cateno");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/read"); // /WEB-INF/views/cate/read.jsp
    
    QnaVO qnaVO = this.qnaProc.read(qnano);
    mav.addObject("qnaVO", qnaVO);
    
    return mav;
  }

  /**
   * 수정폼
   * http://localhost:9091/qna/update.do?qnano=1
   * @return
   */
  @RequestMapping(value="/qna/update.do", method = RequestMethod.GET)
  public ModelAndView update(int qnano) { // int cateno = (int)request.getParameter("cateno");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/update");
      
    QnaVO qnaVO = this.qnaProc.read(qnano);
    mav.addObject("qnaVO", qnaVO);
        
    ArrayList<QnaVO> list = (ArrayList<QnaVO>)this.qnaProc.list();
    mav.addObject("list", list);
    
    
    
    return mav;
  }
  
  /**
   * 수정 처리, http://localhost:9091/qna/update.do
   * @param cateVO 수정할 내용
   * @return
   */
  
  @RequestMapping(value="/qna/update.do", method = RequestMethod.POST)
  public ModelAndView update(QnaVO qnaVO) { 
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/msg"); 
    
    int cnt = this.qnaProc.update(qnaVO); 
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      mav.addObject("code", "update_success");
      mav.setViewName("redirect:/qna/list_all.do");
    } else {
      mav.setViewName("/qna/msg"); 
      mav.addObject("code", "update_fail");
    }
    
    mav.addObject("cnt", cnt); 
    
    return mav;
  }
  
  /**
   * 삭제폼
   * http://localhost:9091/qna/delete.do?qnano=1
   * @return
   */
  @RequestMapping(value="/qna/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int qnano) { // int cateno = (int)request.getParameter("cateno");
    ModelAndView mav = new ModelAndView();
   
    mav.setViewName("/qna/delete");
      
    QnaVO qnaVO = this.qnaProc.read(qnano);
    mav.addObject("qnaVO", qnaVO);
      
    ArrayList<QnaVO> list = this.qnaProc.list();
    mav.addObject("list", list);
      
      
    
    return mav;
  }
  
 // 삭제 처리, 수정 처리를 복사하여 개발
 // 자식 테이블 레코드 삭제 -> 부모 테이블 레코드 삭제
 /**
  * 카테고리 삭제
  * @param session
  * @param qnano 삭제할 카테고리 번호
  * @return
  */
 @RequestMapping(value="/qna/delete.do", method=RequestMethod.POST)
 public ModelAndView delete_proc(HttpSession session, int qnano) {   
   
   ModelAndView mav = new ModelAndView(); 
   int cnt = this.qnaProc.delete(qnano);
     
   if (cnt == 1) {
     mav.addObject("code", "delete_success");
     mav.setViewName("redirect:/qna/list_all.do");            
   } else {
     mav.addObject("code", "delete_fail");
     mav.setViewName("/qna/msg"); 
   }
     mav.addObject("cnt", cnt);   
     
   return mav;
 }
  
}
