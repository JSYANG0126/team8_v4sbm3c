package dev.mvc.recommend;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
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
import dev.mvc.movie.MovieProcInter;
import dev.mvc.movie.MovieVO;



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
  @Qualifier("dev.mvc.movie.MovieProc")
  private MovieProcInter movieProc;
  
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
   * 등록 처리 http://localhost:9093/recommend/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/recommend/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, HttpSession session, RecommendVO recommendVO) {
    ModelAndView mav = new ModelAndView();
    
    if (memProc.isMem(session)) { // 회원으로 로그인한경우
      
      mav.addObject("url", "/recommend/msg"); // 
      mav.setViewName("redirect:/recommend/msg.do"); 

    } else {
      mav.addObject("url", "/mem/login_need"); 
      mav.setViewName("redirect:/recommend/msg.do"); 
    }
    
    return mav; // forward
  }
  
  
  /**
   * 전체 목록 (좋아요 순)
   * http://localhost:9093/recommend/recom_good.do
   * @return
   */
  @RequestMapping(value="/recommend/recom_good.do", method = RequestMethod.GET)
  public ModelAndView recom_good(HttpSession session) {
    ModelAndView mav = new ModelAndView();

      mav.setViewName("/recommend/recom_good"); // /WEB-INF/views/reservation/list_all.jsp
      
      int memno = (int)session.getAttribute("memno");
      mav.addObject("memno", memno);
      
      RecommendVO recommendVO = this.recommendProc.read_by_memno(memno);
     
      if (recommendVO != null) {
        int genreno = recommendVO.getGenreno();
        ArrayList<MovieVO> list = this.recommendProc.recom_good(genreno);    
        if (list.size() > 5) {
           list = new ArrayList<>(list.subList(0, 5));
           System.out.println("<<<<<<<<< test1" + list);          
         }
        mav.addObject("list", list);         
      } else {
        ArrayList<MovieVO> list = this.movieProc.list_all();
        if (list.size() > 5) {
          list = new ArrayList<>(list.subList(0, 5));
        }
        mav.addObject("list", list);
      }
      return mav;
  }
  
  /**
   * 전체 목록
   * http://localhost:9092/recommend/list_all.do
   * @return
   */
  @RequestMapping(value="/recommend/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_all(HttpSession session) {
    ModelAndView mav = new ModelAndView();

      mav.setViewName("/recommend/list_all"); // /WEB-INF/views/reservation/list_all.jsp
      
      ArrayList<MovieVO> list = this.movieProc.list_all();
      
      if (list.size() > 5) {
        list = new ArrayList<>(list.subList(0, 5));
      }

      mav.addObject("list", list);
      
    return mav;
  }
  
  /**
   * 파일 삭제 폼
   * http://localhost:9092/recommend/delete.do?recommendno=1
   * 
   * @return
   */
  @RequestMapping(value = "/recommend/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(HttpSession session, int recommendno) {
    ModelAndView mav = new ModelAndView();
    
    if (memProc.isMem(session)) { // 회원으로 로그인한경우
      RecommendVO recommendVO = this.recommendProc.read(recommendno);
      mav.addObject("recommendVO", recommendVO);
      
      GenreVO genreVO = this.genreProc.read(recommendVO.getGenreno());
      mav.addObject("genreVO", genreVO);
      
      mav.setViewName("/recommend/delete"); // /WEB-INF/views/movie/delete.jsp
      
    } else {
      mav.addObject("url", "/mem/login_need"); // /WEB-INF/views/manager/login_need.jsp
      mav.setViewName("redirect:/recommend/msg.do"); 
    }


    return mav; // forward
  }
  
  // 삭제 처리, 수정 처리를 복사하여 개발
  // 자식 테이블 레코드 삭제 -> 부모 테이블 레코드 삭제
  /**
   * 카테고리 삭제
   * @param session
   * @param genreno 삭제할 카테고리 번호
   * @return
   */
  @RequestMapping(value="/recommend/delete.do", method=RequestMethod.POST)
  public ModelAndView delete_proc(HttpSession session, int genreno) { // <form> 태그의 값이 자동으로 저장됨
    ModelAndView mav = new ModelAndView();
    
    if (this.memProc.isMem(session) == true) {
      ArrayList<RecommendVO> list = this.recommendProc.list_all();
      
      int cnt = this.genreProc.delete(genreno); 
      
      if (cnt == 1) {
        mav.setViewName("redirect:/index.do");     
        
      } else {
        mav.addObject("code", "delete_fail");
        mav.setViewName("/recommend/msg"); 
      }
      
      mav.addObject("cnt", cnt);
      
    } else {
      mav.setViewName("/mem/login_need"); 
    }
    
    return mav;
  }
  


}






