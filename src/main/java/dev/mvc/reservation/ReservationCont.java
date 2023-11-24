package dev.mvc.reservation;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.genre.GenreVO;
import dev.mvc.manager.ManagerProcInter;
import dev.mvc.tool.Tool;

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
       mav.setViewName("redirect:/reservation/list.do"); // 주소 자동 이동
     } else {
       mav.addObject("code", "create_fail");
       mav.setViewName("/reservation/msg"); // /WEB-INF/views/reservation/msg.jsp
     }
     
     mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);

     return mav;
   }
   /**
   * 전체 목록
   * http://localhost:9091/reservation/list_all.do
   * @return
   */
  @RequestMapping(value="/reservation/list_all.do", method = RequestMethod.GET)
  public ModelAndView list(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if (this.managerProc.isManager(session) == true) {
      mav.setViewName("/reservation/list_all"); // /WEB-INF/views/reservation/list_all.jsp
      
      ArrayList<ReservationVO> list = this.reservationProc.list_all();
      mav.addObject("list", list);
      
    } else {
      mav.setViewName("/manager/login_need"); // /WEB-INF/views/manager/login_need.jsp
      
    }
    
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
  public ModelAndView update(int reservationno) { // int genreno = (int)request.getParameter("genreno");
    ModelAndView mav = new ModelAndView();
    // mav.setViewName("/reservation/update"); // /WEB-INF/views/genre/update.jsp
    mav.setViewName("/reservation/list_all"); // /WEB-INF/views/genre/list_all_update.jsp
    
    ReservationVO reservationVO = this.reservationProc.read(reservationno);
    mav.addObject("reservationVO", reservationVO);
    
    ArrayList<ReservationVO> list = this.reservationProc.list_all();
    mav.addObject("list", list);
    
    return mav;
  }
  
  /**
   * 수정 처리, http://localhost:9092/genre/update.do
   * @param genreVO 수정할 내용
   * @return
   */
  @RequestMapping(value="/reservation/update.do", method = RequestMethod.POST)
  public ModelAndView update(ReservationVO reservationVO) { // 자동으로 genreVO 객체가 생성되고 폼의 값이 할당됨
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.reservationProc.update(reservationVO); // 수정 처리
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      // mav.addObject("code", "update_success"); // 키, 값
      // mav.addObject("name", genreVO.getName()); // 카테고리 이름 jsp로 전송
      mav.setViewName("redirect:/reservation/list.do"); 
      
    } else {
      mav.addObject("code", "update_fail");
      mav.setViewName("/reservation/msg"); // /WEB-INF/views/genre/msg.jsp
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
    
    return mav;
  }

}






