package dev.mvc.genre;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.manager.ManagerProcInter;
import dev.mvc.movie.Movie;
import dev.mvc.movie.MovieProcInter;
import dev.mvc.movie.MovieVO;
import dev.mvc.tool.Tool;

@Controller
public class GenreCont {
  @Autowired // GenreProcInter interface 구현한 객체를 만들어 자동으로 할당해라.
  @Qualifier("dev.mvc.genre.GenreProc")
  private GenreProcInter genreProc;
  
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc") // "dev.mvc.admin.AdminProc"라고 명명된 클래스
  private ManagerProcInter managerProc; // AdminProcInter를 구현한 AdminProc 클래스의 객체를 자동으로 생성하여 할당
  
  @Autowired
  @Qualifier("dev.mvc.movie.MovieProc") // @Component("dev.mvc.contents.ContentsProc")
  private MovieProcInter movieProc;
  
  public GenreCont() {
    System.out.println("-> GenreCont created.");  
  }

//  // FORM 출력, http://localhost:9092/genre/create.do
//  @RequestMapping(value="/genre/create.do", method = RequestMethod.GET)
//  @ResponseBody // 단순 문자열로 출력, jsp 파일명 조합이 발생하지 않음.
//  public String create() {
//    return "<h3>GET 방식 FORM 출력</h3>";
//  }

  // FORM 출력, http://localhost:9092/genre/create.do
  @RequestMapping(value="/genre/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/genre/create"); // /WEB-INF/views/genre/create.jsp
    
    return mav;
  }
  
  // FORM 데이터 처리, http://localhost:9092/genre/create.do
  @RequestMapping(value="/genre/create.do", method = RequestMethod.POST)
  public ModelAndView create(GenreVO genreVO) { // 자동으로 genreVO 객체가 생성되고 폼의 값이 할당됨
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.genreProc.create(genreVO);
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      //mav.addObject("code", "create_success"); // 키, 값
      //mav.addObject("name", genreVO.getName()); // 카테고리 이름 jsp로 전송
      mav.setViewName("redirect:/genre/list_all.do"); // 주소 자동 이동
    } else {
      mav.addObject("code", "create_fail");
      mav.setViewName("/genre/msg"); // /WEB-INF/views/genre/msg.jsp
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
  
   /**
   * 전체 목록
   * http://localhost:9091/cate/list_all.do
   * @return
   */
  @RequestMapping(value="/genre/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_all(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if (this.managerProc.isManager(session) == true) {
      mav.setViewName("/genre/list_all"); // /WEB-INF/views/genre/list_all.jsp
      
      ArrayList<GenreVO> list = this.genreProc.list_all();
      mav.addObject("list", list);
      
    } else {
      mav.setViewName("/manager/login_need"); // /WEB-INF/views/manager/login_need.jsp
      
    }
    
    return mav;
  }
  
  /**
   * 조회
   * http://localhost:9092/genre/read.do?genreno=1
   * @return
   */
  @RequestMapping(value="/genre/read.do", method = RequestMethod.GET)
  public ModelAndView read(int genreno) { // int genreno = (int)request.getParameter("genreno");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/genre/read"); // /WEB-INF/views/genre/read.jsp
    
    GenreVO genreVO = this.genreProc.read(genreno);
    mav.addObject("genreVO", genreVO);
    
    return mav;
  }

  /**
   * 수정폼
   * http://localhost:9092/genre/update.do?genreno=1
   * @return
   */
  @RequestMapping(value="/genre/update.do", method = RequestMethod.GET)
  public ModelAndView update(int genreno) { // int genreno = (int)request.getParameter("genreno");
    ModelAndView mav = new ModelAndView();
    // mav.setViewName("/genre/update"); // /WEB-INF/views/genre/update.jsp
    mav.setViewName("/genre/list_all_update"); // /WEB-INF/views/genre/list_all_update.jsp
    
    GenreVO genreVO = this.genreProc.read(genreno);
    mav.addObject("genreVO", genreVO);
    
    ArrayList<GenreVO> list = this.genreProc.list_all();
    mav.addObject("list", list);
    
    return mav;
  }
  
  /**
   * 수정 처리, http://localhost:9092/genre/update.do
   * @param genreVO 수정할 내용
   * @return
   */
  @RequestMapping(value="/genre/update.do", method = RequestMethod.POST)
  public ModelAndView update(GenreVO genreVO) { // 자동으로 genreVO 객체가 생성되고 폼의 값이 할당됨
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.genreProc.update(genreVO); // 수정 처리
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      // mav.addObject("code", "update_success"); // 키, 값
      // mav.addObject("name", genreVO.getName()); // 카테고리 이름 jsp로 전송
      mav.setViewName("redirect:/genre/list_all.do"); 
      
    } else {
      mav.addObject("code", "update_fail");
      mav.setViewName("/genre/msg"); // /WEB-INF/views/genre/msg.jsp
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
  
  /**
   * 삭제폼
   * http://localhost:9092/genre/delete.do?genreno=1
   * @return
   */
  @RequestMapping(value="/genre/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(HttpSession session, int genreno) { // int genreno = (int)request.getParameter("genreno");
    ModelAndView mav = new ModelAndView();
    
    if (this.managerProc.isManager(session) == true) {
      // mav.setViewName("/genre/delete"); // /WEB-INF/views/genre/delete.jsp
      mav.setViewName("/genre/list_all_delete"); // /WEB-INF/views/genre/list_all_delete.jsp
      
      GenreVO genreVO = this.genreProc.read(genreno);
      mav.addObject("genreVO", genreVO);
      
      ArrayList<GenreVO> list = this.genreProc.list_all();
      mav.addObject("list", list);
      
      // 특정 카테고리에 속한 레코드 갯수를 리턴
      int count_by_genreno = this.movieProc.count_by_genreno(genreno);
      mav.addObject("count_by_genreno", count_by_genreno);
      
    } else {
      mav.setViewName("/manager/login_need"); // /WEB-INF/views/manager/login_need.jsp
   
    }
    
    return mav;
  }
  
 // 삭제 처리, 수정 처리를 복사하여 개발
 // 자식 테이블 레코드 삭제 -> 부모 테이블 레코드 삭제
 /**
  * 카테고리 삭제
  * @param session
  * @param genreno 삭제할 카테고리 번호
  * @return
  */
 @RequestMapping(value="/genre/delete.do", method=RequestMethod.POST)
 public ModelAndView delete_proc(HttpSession session, int genreno) { // <form> 태그의 값이 자동으로 저장됨
//   System.out.println("-> genreno: " + genreVO.getGenreno());
//   System.out.println("-> name: " + genreVO.getName());
   
   ModelAndView mav = new ModelAndView();
   
   if (this.managerProc.isManager(session) == true) {
     ArrayList<MovieVO> list = this.movieProc.list_by_genreno(genreno); // 자식 레코드 목록 읽기
     
     for(MovieVO movieVO : list) { // 자식 레코드 관련 파일 삭제
       // -------------------------------------------------------------------
       // 파일 삭제 시작
       // -------------------------------------------------------------------
       String file1saved = movieVO.getFile1saved();
       String thumb1 = movieVO.getThumb1();
       
       String uploadDir = Movie.getUploadDir();
       Tool.deleteFile(uploadDir, file1saved);  // 실제 저장된 파일삭제
       Tool.deleteFile(uploadDir, thumb1);     // povie 이미지 삭제
       // -------------------------------------------------------------------
       // 파일 삭제 종료
       // -------------------------------------------------------------------
     }
     
     this.movieProc.delete_by_genreno(genreno); // 자식 레코드 삭제     
           
     int cnt = this.genreProc.delete(genreno); // 카테고리 삭제
     
     if (cnt == 1) {
       mav.setViewName("redirect:/genre/list_all.do");       // 자동 주소 이동, Spring 재호출
       
     } else {
       mav.addObject("code", "delete_fail");
       mav.setViewName("/genre/msg"); // /WEB-INF/views/genre/msg.jsp
     }
     
     mav.addObject("cnt", cnt);
     
   } else {
     mav.setViewName("/manager/login_need"); // /WEB-INF/views/manager/login_need.jsp
   }
   
   return mav;
 }

  /**
   * 우선 순위 높임, 10 등 -> 1 등, http://localhost:9092/genre/update_seqno_forward.do?genreno=1
   * @param genreVO 수정할 내용
   * @return
   */
  @RequestMapping(value="/genre/update_seqno_forward.do", method = RequestMethod.GET)
  public ModelAndView update_seqno_forward(int genreno) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.genreProc.update_seqno_forward(genreno);
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      mav.setViewName("redirect:/genre/list_all.do"); 
      
    } else {
      mav.addObject("code", "update_fail");
      mav.setViewName("/genre/msg"); // /WEB-INF/views/genre/msg.jsp
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
  
  /**
   * 우선 순위 낮춤, 1 등 -> 10 등, http://localhost:9092/genre/update_seqno_forward.do?genreno=1
   * @param genreVO 수정할 내용
   * @return
   */
  @RequestMapping(value="/genre/update_seqno_backward.do", method = RequestMethod.GET)
  public ModelAndView update_seqno_backward(int genreno) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.genreProc.update_seqno_backward(genreno);
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      mav.setViewName("redirect:/genre/list_all.do"); 
      
    } else {
      mav.addObject("code", "update_fail");
      mav.setViewName("/genre/msg"); // /WEB-INF/views/genre/msg.jsp
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
  
  /**
   * 카테고리 공개 설정, http://localhost:9092/genre/update_visible_y.do?genreno=1
   * @param genreno 수정할 레코드 PK 번호
   * @return
   */
  @RequestMapping(value="/genre/update_visible_y.do", method = RequestMethod.GET)
  public ModelAndView update_visible_y(int genreno) { 
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.genreProc.update_visible_y(genreno);
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      mav.setViewName("redirect:/genre/list_all.do"); 
      
    } else {
      mav.addObject("code", "update_fail");
      mav.setViewName("/genre/msg"); // /WEB-INF/views/genre/msg.jsp
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
  
  /**
   * 카테고리 비공개 설정, http://localhost:9092/genre/update_visible_y.do?genreno=1
   * @param genreno 수정할 레코드 PK 번호
   * @return
   */
  @RequestMapping(value="/genre/update_visible_n.do", method = RequestMethod.GET)
  public ModelAndView update_visible_n(int genreno) { 
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.genreProc.update_visible_n(genreno);
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      mav.setViewName("redirect:/genre/list_all.do"); 
      
    } else {
      mav.addObject("code", "update_fail");
      mav.setViewName("/genre/msg"); // /WEB-INF/views/genre/msg.jsp
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
  
}






