package dev.mvc.theater;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.comments.CommentsVO;
import dev.mvc.genre.GenreVO;
import dev.mvc.manager.ManagerProcInter;
import dev.mvc.mem.MemProcInter;
import dev.mvc.mem.MemVO;
import dev.mvc.movie.Movie;
import dev.mvc.movie.MovieVO;
import dev.mvc.reservation.ReservationVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;


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
  
  /**
   * Post 요청시 JSP 페이지에서 JSTL 호출 기능 지원, 새로고침 방지, EL에서 param으로 접근
   * POST -> url -> GET -> 데이터 전송
   * @param genreno
   * @return
   */
  @RequestMapping(value="/theater/msg.do", method = RequestMethod.GET)
  public ModelAndView msg(String url) {
    //  public ModelAndView create(HttpServletRequest request,  int genreno) {
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav;
  }
  /**
   * 등록폼 
   * @return
   */
  
  //FORM 출력, http://localhost:9093/theater/create.do
    @RequestMapping(value="/theater/create.do", method = RequestMethod.GET)
    public ModelAndView create() {
     
      
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/theater/create"); // /WEB-INF/views/reservation/create.jsp
      
      return mav;
    }
  
    /**
     * 등록 처리
     * @return
     */
  // FORM 데이터 처리, http://localhost:9093/theater/create.do
  @RequestMapping(value="/theater/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, HttpSession session,TheaterVO theaterVO) { // 자동으로 reservationVO 객체가 생성되고 폼의 값이 할당됨
    ModelAndView mav = new ModelAndView();
    
    if (memProc.isMem(session)) { // 회원으로 로그인한경우
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 시작
      // ------------------------------------------------------------------------------
      String img1 = "";          // 원본 파일명 image
      String img1saved = "";   // 저장된 파일명, image
      String thumbimg1 = "";     // povie image
        
      String upDir =  Theater.getUploadDir(); //파일을 업로드할 폴더 준비
      System.out.println("-> upDir: " + upDir);
      
      // 전송 파일이 없어도 file1MF 객체가 생성됨.
      // <input type='file' class="form-control" name='file1MF' id='file1MF' 
      //           value='' placeholder="파일 선택">
      MultipartFile mf = theaterVO.getFile1MF();
      
      img1 = mf.getOriginalFilename(); // 원본 파일명 산출, 01.png
      System.out.println("-> 원본 파일명 산출 file1: " + img1);
      
      long size1 = mf.getSize();  // 파일 크기
      
      if (size1 > 0) { // 파일 크기 체크
        // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
        img1saved = Upload.saveFileSpring(mf, upDir); 
        
        if (Tool.isImage(img1saved)) { // 이미지인지 검사
          // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
          thumbimg1 = Tool.preview(upDir, img1saved, 200, 150); 
        }
        
      }    
      
      theaterVO.setImg1(img1);   // 순수 원본 파일명
      theaterVO.setImg1saved(img1saved); // 저장된 파일명(파일명 중복 처리)
      theaterVO.setThumbimg1(thumbimg1);      // 원본이미지 축소판
      theaterVO.setSize1(size1);  // 파일 크기
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 종료
      // ------------------------------------------------------------------------------
      
      // Call By Reference: 메모리 공유, Hashcode 전달
      int memno = (int)session.getAttribute("memno"); // managerno FK
      theaterVO.setMemno(memno);
      int cnt = this.theaterProc.create(theaterVO); 

      // ------------------------------------------------------------------------------
      // PK의 return
      // ------------------------------------------------------------------------------
      // System.out.println("--> movieno: " + movieVO.getMovieno());
      // mav.addObject("movieno", movieVO.getMovieno()); // redirect parameter 적용
      // ------------------------------------------------------------------------------

      if (cnt == 1) {
        mav.addObject("code", "create_success");
        // genreProc.increaseCnt(movieVO.getGenreno()); // 글수 증가
      } else {
          mav.addObject("code", "create_fail");
      }
      mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
      
      // System.out.println("--> genreo: " + movieVO.getGenreno());
      // redirect시에 hidden tag로 보낸것들이 전달이 안됨으로 request에 다시 저장
      mav.addObject("memno", theaterVO.getMemno()); // redirect parameter 적용
      
      mav.addObject("url", "/theater/msg"); // msg.jsp, redirect parameter 적용
      mav.setViewName("redirect:/theater/msg.do"); 
  
    } else {
      mav.addObject("url", "/mem/login_need"); // /WEB-INF/views/mem/login_need.jsp
      mav.setViewName("redirect:/theater/msg.do"); 
    }
    
    return mav; // forward
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
      
      int memno = (int)session.getAttribute("memno"); // memno FK
      MemVO memVO = this.memProc.read(memno);
      ArrayList<TheaterVO> list = this.theaterProc.list_all();
 
      mav.addObject("list", list);
      mav.addObject("memVO", memVO);    
      
    } else {
      mav.setViewName("/mem/login_need"); // /WEB-INF/views/manager/login_need.jsp
      
    }
   
    
    return mav;
  }
  
  /**
   * 조회
   * http://localhost:9093/theater/read.do?movieno=17
   * @return
   */
  @RequestMapping(value="/theater/read.do", method = RequestMethod.GET)
  public ModelAndView read(int theaterno) { // int genreno = (int)request.getParameter("genreno");
    ModelAndView mav = new ModelAndView();
    
    TheaterVO theaterVO = this.theaterProc.read(theaterno);
    
    String tname = theaterVO.getTname();
    String tinfo = theaterVO.getTinfo();
    
    tname = Tool.convertChar(tname);  // 특수 문자 처리
    tinfo = Tool.convertChar(tinfo); 
    
    theaterVO.setTname(tname);
    theaterVO.setTinfo(tinfo);  
    
    long size1 = theaterVO.getSize1();
    String size1_label = Tool.unit(size1);
    theaterVO.setSize1_label(size1_label);
    

    mav.setViewName("/theater/read"); // /WEB-INF/views/movie/read.jsp
      
      
    mav.addObject("theaterVO", theaterVO);
     
    return mav;
  }
   
  /**
   * 목록 + 검색 + 페이징 지원
   * http://localhost:9093/theater/list_by_search_paging.do?word=스위스&now_page=1
   * 
   * @param word
   * @param now_page
   * @return
   */
  @RequestMapping(value = "/theater/list_by_search_paging.do", method = RequestMethod.GET)
  public ModelAndView list_by_genreno(HttpSession session, TheaterVO theaterVO) {
    ModelAndView mav = new ModelAndView();
      if (this.memProc.isMem(session) == true) {
        
        int memno = (int)session.getAttribute("memno"); // memno FK
        MemVO memVO = this.memProc.read(memno);
        // 검색 목록
        ArrayList<TheaterVO> list = theaterProc.list_by_search_paging(theaterVO);
        
        // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
        for (TheaterVO vo : list) {
          String tname = vo.getTname();
          String tinfo = vo.getTinfo();
          
          tname = Tool.convertChar(tname);  // 특수 문자 처리
          tinfo = Tool.convertChar(tinfo); 
          
          vo.setTname(tname);
          vo.setTinfo(tinfo);  
      
        }
        
        mav.addObject("list", list);
        mav.addObject("memVO", memVO);
      
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("word", theaterVO.getWord());
        
        int search_count = this.theaterProc.search_count(hashMap);  // 검색된 레코드 갯수 ->  전체 페이지 규모 파악
        mav.addObject("search_count", search_count);
        
        /*
         * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16 17
         * 18 19 20 [다음]
         * @param genreno 카테고리번호
         * @param now_page 현재 페이지
         * @param word 검색어
         * @return 페이징용으로 생성된 HTML/CSS tag 문자열
         */
        String paging = theaterProc.pagingBox(theaterVO.getNow_page(), theaterVO.getWord(), "list_by_search_paging.do", search_count);
        mav.addObject("paging", paging);
      
        // mav.addObject("now_page", now_page);
        
        mav.setViewName("/theater/list_by_search_paging");  // /movie/list_by_genreno.jsp
        
      } else {
        mav.setViewName("/mem/login_need"); // /WEB-INF/views/manager/login_need.jsp
        
      }
  
   
  
    return mav;
  }
  
  /**
   * 목록 + 검색 + 페이징 지원 + Grid
   * 검색하지 않는 경우
   * http://localhost:9093/theater/list_by_grid.do?word=&now_page=1
   * 검색하는 경우
   * http://localhost:9093/theater/list_by_grid.do?word=탐험&now_page=1
   * 
   * @param word
   * @param now_page
   * @return
   */
   @RequestMapping(value = "/theater/list_by_grid.do", method = RequestMethod.GET)
   public ModelAndView list_by_grid(TheaterVO theaterVO) {
      ModelAndView mav = new ModelAndView();
    
      // 검색 목록
    ArrayList<TheaterVO> list = theaterProc.list_by_search_paging(theaterVO);
    
    // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
    for (TheaterVO vo : list) {
      String tname = vo.getTname();
      String tinfo = vo.getTinfo();
      
      tname = Tool.convertChar(tname);  // 특수 문자 처리
      tinfo = Tool.convertChar(tinfo); 
      
      vo.setTname(tname);
      vo.setTinfo(tinfo);  
  
    }
      
      mav.addObject("list", list);
      
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("word", theaterVO.getWord());
      
      int search_count = this.theaterProc.search_count(hashMap);  // 검색된 레코드 갯수 ->  전체 페이지 규모 파악
      mav.addObject("search_count", search_count);
    
      /*
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16 17
   * 18 19 20 [다음]
   * @param genreno 카테고리번호
   * @param now_page 현재 페이지
   * @param word 검색어
   * @param list_file 목록 파일명
   * @return 페이징용으로 생성된 HTML/CSS tag 문자열
   */
    String paging = theaterProc.pagingBox(theaterVO.getNow_page(), theaterVO.getWord(), "list_by_grid.do", search_count);
    mav.addObject("paging", paging);
    
      // mav.addObject("now_page", now_page);
    
      mav.setViewName("/movie/list_by_grid");  // /theater/list_by_grid.jsp
    
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
