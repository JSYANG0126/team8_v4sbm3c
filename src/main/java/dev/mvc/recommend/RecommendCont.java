package dev.mvc.recommend;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.genre.GenreVO;
import dev.mvc.manager.ManagerProcInter;
import dev.mvc.genre.GenreProcInter;
import dev.mvc.mem.MemProcInter;
import dev.mvc.movie.MovieVO;
import dev.mvc.reservation.ReservationVO;
import dev.mvc.tool.Tool;


@Controller
public class RecommendCont {
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc") // "dev.mvc.manager.ManagerProc"라고 명명된 클래스
  private ManagerProcInter managerProc; // ManagerProcInter를 구현한 ManagerProc 클래스의 객체를 자동으로 생성하여 할당
  
  @Autowired
  @Qualifier("dev.mvc.genre.GenreProc") 
  private GenreProcInter genreProc;
  
  @Autowired
  @Qualifier("dev.mvc.mem.MemProc")
  private MemProcInter memProc;
  
  @Autowired
  @Qualifier("dev.mvc.recommend.RecommendProc") 
  private RecommendProcInter recommendProc;
  
  public RecommendCont() {
    System.out.println("-> RecommendCont created.");  
  }
  
  /**
   * Post 요청시 JSP 페이지에서 JSTL 호출 기능 지원, 새로고침 방지, EL에서 param으로 접근
   * POST -> url -> GET -> 데이터 전송
   * @param genreno
   * @return
   */
  @RequestMapping(value="/recommend/msg.do", method = RequestMethod.GET)
  public ModelAndView msg(String url) {
//  public ModelAndView create(HttpServletRequest request,  int genreno) {
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav;
  }
  
  // 등록 폼, movie 테이블은 FK로 genreno를 사용함.
  // http://localhost:9092/movie/create.do  X
  // http://localhost:9092/movie/create.do?genreno=1
  // http://localhost:9092/movie/create.do?genreno=2
  // http://localhost:9092/movie/create.do?genreno=3
  @RequestMapping(value="/recommend/create.do", method = RequestMethod.GET)
  public ModelAndView create(int memno, int genreno) {
//  public ModelAndView create(HttpServletRequest request,  int genreno) {
    ModelAndView mav = new ModelAndView();

    //RecommendVO recommendVO = this.recommendProc.read(memno, genreno); // create.jsp에 카테고리 정보를 출력하기위한 목적
    //mav.addObject("recommendVO", recommendVO);

    
    mav.setViewName("/recommend/create"); // /webapp/WEB-INF/views/movie/create.jsp
    
    return mav;
  }
  
  /**
   * 전체 목록
   * http://localhost:9092/recommend/list_all.do
   * @return
   */
  @RequestMapping(value="/recommend/list_all.do", method = RequestMethod.GET)
  public ModelAndView list(HttpSession session) {
    ModelAndView mav = new ModelAndView();

      mav.setViewName("/recommend/list_all"); // /WEB-INF/views/reservation/list_all.jsp
      
      ArrayList<RecommendVO> list = this.recommendProc.list_all();
      mav.addObject("list", list);
      
    
    return mav;
  }

}






