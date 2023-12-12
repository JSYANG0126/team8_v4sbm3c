package dev.mvc.theater;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.comments.CommentsVO;
import dev.mvc.genre.GenreVO;
import dev.mvc.manager.ManagerProcInter;
import dev.mvc.mem.MemProcInter;
import dev.mvc.mem.MemVO;
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
      ArrayList<MemVO> list2 = this.memProc.list();
      
      mav.addObject("list", list);
      mav.addObject("list2",list2);
      
    } else {
      mav.setViewName("/mem/login_need"); // /WEB-INF/views/manager/login_need.jsp
      
    }
    
    return mav;
  }
  
  /**
   * 조회
   * http://localhost:9092/movie/read.do?movieno=17
   * @return
   */
  @RequestMapping(value="/theater/read.do", method = RequestMethod.GET)
  public ModelAndView read(int theaterno) { // int genreno = (int)request.getParameter("genreno");
    ModelAndView mav = new ModelAndView();
    

      mav.setViewName("/theater/read"); // /WEB-INF/views/movie/read.jsp
      
      TheaterVO theaterVO = this.theaterProc.read(theaterno);
      mav.addObject("theaterVO", theaterVO);
      

    
    return mav;
  }
   
   
  
  /**
   * 수정폼
   * http://localhost:9093/theater/update.do?reservationno=1
   * @return
   */
  @RequestMapping(value="/theater/update.do", method = RequestMethod.GET)
  public ModelAndView update(int theaterno, HttpSession session) { 
    ModelAndView mav = new ModelAndView();
    
    if (this.memProc.isMem(session) == true) {
      mav.setViewName("/theater/update");
      
      TheaterVO theaterVO = this.theaterProc.read(theaterno);
      mav.addObject("theaterVO", theaterVO);
      
      ArrayList<TheaterVO> list = this.theaterProc.list_all();
      mav.addObject("list", list);
      
    } else {
      mav.setViewName("/mem/login_need"); // /WEB-INF/views/manager/login_need.jsp
      
    }
    
   
    
    return mav;
  }
  
  /**
   * 수정 처리, http://localhost:9093/theater/update.do
   * @param reservationVO 수정할 내용
   * @return
   */
  @RequestMapping(value="/theater/update.do", method = RequestMethod.POST)
  public ModelAndView update(TheaterVO theaterVO) { // 자동으로 reservationVO 객체가 생성되고 폼의 값이 할당됨
    ModelAndView mav = new ModelAndView();
    System.out.println(theaterVO);
    int cnt = this.theaterProc.update(theaterVO); // 수정 처리
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      mav.setViewName("redirect:/theater/list_all.do"); 
    }else{
      mav.addObject("code", "update_fail");
      mav.setViewName("/theater/msg"); // /WEB-INF/views/reservation/msg.jsp
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
  
  /**
   * 맵 등록/수정/삭제 폼
   * http://localhost:9092/theater/map.do?movieno=1
   * @return
   */
  @RequestMapping(value="/theater/map.do", method=RequestMethod.GET )
  public ModelAndView map(int theaterno) {
    ModelAndView mav = new ModelAndView();

    TheaterVO theaterVO = this.theaterProc.read(theaterno); // map 정보 읽어 오기
    mav.addObject("theaterVO", theaterVO); // request.setAttribute("movieVO", movieVO);

    mav.setViewName("/theater/map"); // /WEB-INF/views/movie/map.jsp
        
    return mav;
  }
  
  /**
   * MAP 등록/수정/삭제 처리
   * http://localhost:9092/movie/map.do
   * @param movieVO
   * @return
   */
  @RequestMapping(value="/theater/map.do", method = RequestMethod.POST)
  public ModelAndView map_update(int theaterno, String map) {
    ModelAndView mav = new ModelAndView();
    
    HashMap<String,Object> hashMap = new HashMap<String,Object>();
    hashMap.put("theaterno", theaterno);
    hashMap.put("map", map);
    
    this.theaterProc.map(hashMap);
    
    mav.setViewName("redirect:/theater/read.do?theaterno=" + theaterno); 
    // /webapp/WEB-INF/views/movie/read.jsp
    
    return mav;
  }
  
  /**
   * 삭제폼 
   * http://localhost:9093/theater/delete.do
   * @return
    */
    @RequestMapping(value="/theater/delete.do", method = RequestMethod.GET)
    public ModelAndView delete(int theaterno) { // int cateno = (int) request.getParemeter("cateno");
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/theater/delete"); // /WEB-INF/views/cate/delete.jsp
      
      TheaterVO theaterVO = this.theaterProc.read(theaterno);
      
      mav.addObject("theaterVO", theaterVO); // 
      return mav;
    }
    /**
     * 삭제처리, http://localhost:9093/theater/delete.do?reservationno=8
     * @param cateno 삭제할 내용
     * @return  
     */
    @RequestMapping(value="/theater/delete.do", method = RequestMethod.POST)
    public ModelAndView delete_proc(int theaterno) { // int theaterno = (int) request.getParemeter("cateno");
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/theater/msg"); // /WEB-INF/views/theater/delete.jsp
      
      TheaterVO theaterVO = this.theaterProc.read(theaterno); // 삭제할 레코드 정보 읽기
      
      int cnt = this.theaterProc.delete(theaterno);
      System.out.println("->cnt" + cnt);
      
      if (cnt == 1) {
        mav.addObject("code","delete_success"); // 키, 값
        mav.addObject("name", theaterVO.getTname()); // 카테고리 이름 jsp로 전송
      } else {
        mav.addObject("code", "delete_fail");
      }
      
      mav.addObject("cnt", cnt);
      return mav;
    }
  

}
