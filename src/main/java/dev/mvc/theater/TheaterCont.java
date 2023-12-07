package dev.mvc.theater;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.manager.ManagerProcInter;
import dev.mvc.mem.MemProcInter;
import dev.mvc.movie.MovieVO;
import dev.mvc.reservation.ReservationVO;
import dev.mvc.tool.Tool;


@Controller
public class TheaterCont {
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc") // "dev.mvc.admin.AdminProc"라고 명명된 클래스
  private ManagerProcInter managerProc; // AdminProcInter를 구현한 AdminProc 클래스의 객체를 자동으로 생성하여 할당
  
  @Autowired
  @Qualifier("dev.mvc.theater.TheaterProc") // @Component("dev.mvc.contents.ContentsProc")
  private TheaterProcInter theaterProc;
  
  @Autowired
  @Qualifier("dev.mvc.mem.MemProc") // @Component("dev.mvc.contents.ContentsProc")
  private MemProcInter memProc;
  
  public TheaterCont() {
    System.out.println("-> TheaterCont created.");  
  }
  
//FORM 출력, http://localhost:9093/theater/create.do
  @RequestMapping(value="/theater/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/theater/create"); // /WEB-INF/views/reservation/create.jsp
    
    return mav;
  }
  
  // FORM 데이터 처리, http://localhost:9093/theater/create.do
  @RequestMapping(value="/theater/create.do", method = RequestMethod.POST)
  public ModelAndView create(TheaterVO theaterVO) { // 자동으로 reservationVO 객체가 생성되고 폼의 값이 할당됨
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.theaterProc.create(theaterVO);
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      mav.setViewName("redirect:/theater/list_all.do"); // 주소 자동 이동
    } else {
      mav.addObject("code", "create_fail");
      mav.setViewName("/theater/msg"); // /WEB-INF/views/reservation/msg.jsp
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);

    return mav;
  }
  
  /**
   * 전체 목록
   * http://localhost:9093/theater/list_all.do
   * @return
   */
  @RequestMapping(value="/theater/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_all(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if (this.memProc.isMem(session) == true) {
      mav.setViewName("/theater/list_all"); // /WEB-INF/views/movie/list_all.jsp
      
      ArrayList<TheaterVO> list = this.theaterProc.list_all();
      
      
      mav.addObject("list", list);
      
    } else {
      mav.setViewName("/mem/login_need"); // /WEB-INF/views/manager/login_need.jsp
      
    }
    
    return mav;
  }
  

}
