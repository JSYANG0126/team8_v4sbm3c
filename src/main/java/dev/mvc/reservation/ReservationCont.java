package dev.mvc.reservation;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import dev.mvc.manager.ManagerProcInter;


@Controller
public class ReservationCont {
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc") // "dev.mvc.admin.AdminProc"라고 명명된 클래스
  private ManagerProcInter managerProc; // AdminProcInter를 구현한 AdminProc 클래스의 객체를 자동으로 생성하여 할당
  
  @Autowired
  @Qualifier("dev.mvc.reservation.ReservationProc") // @Component("dev.mvc.contents.ContentsProc")
  private ReservationProcInter reservationProc;
  
  public ReservationCont() {
    System.out.println("-> ReservationCont created.");  
  }

  //FORM 출력, http://localhost:9093/reservation/create.do
   @RequestMapping(value="/reservation/create.do", method = RequestMethod.GET)
   public ModelAndView create() {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/reservation/create"); // /WEB-INF/views/reservation/create.jsp
     
     return mav;
   }
   
   // FORM 데이터 처리, http://localhost:9093/reservation/create.do
   @RequestMapping(value="/reservation/create.do", method = RequestMethod.POST)
   public ModelAndView create(ReservationVO reservationVO) { // 자동으로 reservationVO 객체가 생성되고 폼의 값이 할당됨
     ModelAndView mav = new ModelAndView();
     
     int cnt = this.reservationProc.create(reservationVO);
     System.out.println("-> cnt: " + cnt);
     
     if (cnt == 1) {
       mav.setViewName("redirect:/reservation/list_all.do"); // 주소 자동 이동
     } else {
       mav.addObject("code", "create_fail");
       mav.setViewName("/reservation/msg"); // /WEB-INF/views/reservation/msg.jsp
     }
     
     mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);

     return mav;
   }
   /**
   * 전체 목록
   * http://localhost:9093/reservation/list_all.do
   * @return
   */
  @RequestMapping(value="/reservation/list_all.do", method = RequestMethod.GET)
  public ModelAndView list(HttpSession session) {
    ModelAndView mav = new ModelAndView();

      mav.setViewName("/reservation/list_all"); // /WEB-INF/views/reservation/list_all.jsp
      
      ArrayList<ReservationVO> list = this.reservationProc.list_all();
      mav.addObject("list", list);
      
  
    
    return mav;
  }
  
  /**
   * 조회
   * http://localhost:9093/reservation/read.do?reservationno=1
   * @return
   */
  @RequestMapping(value="/reservation/read.do", method = RequestMethod.GET)
  public ModelAndView read(int reservationno) { // int genreno = (int)request.getParameter("genreno");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/reservation/read"); // /WEB-INF/views/genre/read.jsp
    
    ReservationVO reservationVO = this.reservationProc.read(reservationno);
    mav.addObject("reservationVO", reservationVO);
    
    return mav;
  }
  
  /**
   * 수정폼
   * http://localhost:9093/reservation/update.do?reservationno=1
   * @return
   */
  @RequestMapping(value="/reservation/update.do", method = RequestMethod.GET)
  public ModelAndView update(int reservationno) { 
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/reservation/update");
    
    ReservationVO reservationVO = this.reservationProc.read(reservationno);
    mav.addObject("reservationVO", reservationVO);
    
    ArrayList<ReservationVO> list = this.reservationProc.list_all();
    mav.addObject("list", list);
    
    return mav;
  }
  
  /**
   * 수정 처리, http://localhost:9093/reservation/update.do
   * @param reservationVO 수정할 내용
   * @return
   */
  @RequestMapping(value="/reservation/update.do", method = RequestMethod.POST)
  public ModelAndView update(ReservationVO reservationVO) { // 자동으로 reservationVO 객체가 생성되고 폼의 값이 할당됨
    ModelAndView mav = new ModelAndView();
    System.out.println(reservationVO);
    int cnt = this.reservationProc.update(reservationVO); // 수정 처리
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      mav.setViewName("redirect:/reservation/list_all.do"); 
    }else{
      mav.addObject("code", "update_fail");
      mav.setViewName("/reservation/msg"); // /WEB-INF/views/reservation/msg.jsp
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
  
  /**
  * 삭제폼 
  * http://localhost:9093/reservation/delete.do
  * @return
   */
   @RequestMapping(value="/reservation/delete.do", method = RequestMethod.GET)
   public ModelAndView delete(int reservationno) { // int cateno = (int) request.getParemeter("cateno");
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/reservation/delete"); // /WEB-INF/views/cate/delete.jsp
     
     ReservationVO reservationVO = this.reservationProc.read(reservationno);
     
     mav.addObject("reservationVO", reservationVO); // 
     return mav;
   }
   /**
    * 삭제처리, http://localhost:9093/reservation/delete.do?reservationno=8
    * @param cateno 삭제할 내용
    * @return  
    */
   @RequestMapping(value="/reservation/delete.do", method = RequestMethod.POST)
   public ModelAndView delete_proc(int reservationno) { // int cateno = (int) request.getParemeter("cateno");
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/reservation/msg"); // /WEB-INF/views/cate/delete.jsp
     
     ReservationVO reservationVO = this.reservationProc.read(reservationno); // 삭제할 레코드 정보 읽기
     
     int cnt = this.reservationProc.delete(reservationno);
     System.out.println("->cnt" + cnt);
     
     if (cnt == 1) {
       mav.addObject("code","delete_success"); // 키, 값
       mav.addObject("name", reservationVO.getTname()); // 카테고리 이름 jsp로 전송
     } else {
       mav.addObject("code", "delete_fail");
     }
     
     mav.addObject("cnt", cnt);
     return mav;
   }

}






