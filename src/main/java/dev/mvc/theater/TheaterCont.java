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


import dev.mvc.manager.ManagerProcInter;
import dev.mvc.mem.MemProcInter;
import dev.mvc.mem.MemVO;
import dev.mvc.movie.Movie;
import dev.mvc.movie.MovieVO;
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
  public ModelAndView list_by_search_paging(HttpSession session, TheaterVO theaterVO) {
    ModelAndView mav = new ModelAndView();
    
      if (this.memProc.isMem(session) == true) {
       
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
    
      mav.setViewName("/theater/list_by_grid");  // /theater/list_by_grid.jsp
    
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
   * 수정 처리
   * http://localhost:9091/movie/update_text.do?movieno=1
   * 
   * @return
   */
  @RequestMapping(value = "/theater/update.do", method = RequestMethod.POST)
  public ModelAndView update(HttpSession session, TheaterVO theaterVO) {
    ModelAndView mav = new ModelAndView();
    
    // System.out.println("-> word: " + movieVO.getWord());
    
    if (this.memProc.isMem(session)) { // 관리자 로그인 확인
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("theaterno", theaterVO.getTheaterno());
      hashMap.put("passwd", theaterVO.getPasswd());
      
      if (this.theaterProc.password_check(hashMap) == 1) { // 패스워드 일치
        this.theaterProc.update(theaterVO); // 글수정  
         
        // mav 객체 이용
        mav.addObject("theaterno", theaterVO.getTheaterno());
        mav.setViewName("redirect:/theater/read.do"); // 페이지 자동 이동
        
      } else { // 패스워드 불일치
        mav.addObject("code", "passwd_fail");
        mav.addObject("cnt", 0);
        mav.addObject("url", "/movie/msg"); // msg.jsp, redirect parameter 적용
        mav.setViewName("redirect:/movie/msg.do");  // POST -> GET -> JSP 출력
      }
    } else { // 정상적인 로그인이 아닌 경우 로그인 유도
      mav.addObject("url", "/mem/login_need"); // /WEB-INF/views/manager/login_need.jsp
      mav.setViewName("redirect:/movie/msg.do"); 
    }
    
    mav.addObject("now_page", theaterVO.getNow_page()); // POST -> GET: 데이터 분실이 발생함으로 다시하번 데이터 저장 ★
    
    // URL에 파라미터의 전송
    // mav.setViewName("redirect:/movie/read.do?movieno=" + movieVO.getMovieno() + "&genreno=" + movieVO.getGenreno());             
    
    return mav; // forward
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
   * 파일 수정 폼
   * http://localhost:9092/theater/update_file.do?theaterno=1
   * 
   * @return
   */
  @RequestMapping(value = "/theater/update_file.do", method = RequestMethod.GET)
  public ModelAndView update_file(HttpSession session, int theaterno) {
    ModelAndView mav = new ModelAndView();
    
    if (managerProc.isManager(session)) { // 관리자로 로그인한경우
      TheaterVO theaterVO = this.theaterProc.read(theaterno);
      mav.addObject("theaterVO", theaterVO);
      
      mav.setViewName("/theater/update_file"); // /WEB-INF/views/theater/update_file.jsp
      
    } else {
      mav.addObject("url", "/manager/login_need"); // /WEB-INF/views/manager/login_need.jsp
      mav.setViewName("redirect:/theater/msg.do"); 
    }


    return mav; // forward
  }
  
  /**
   * 파일 수정 처리 http://localhost:9091/theater/update_file.do
   * 
   * @return
   */
  @RequestMapping(value = "/theater/update_file.do", method = RequestMethod.POST)
  public ModelAndView update_file(HttpSession session, TheaterVO theaterVO) {
    ModelAndView mav = new ModelAndView();
    
    if (this.memProc.isMem(session)) {
      // 삭제할 파일 정보를 읽어옴, 기존에 등록된 레코드 저장용
      TheaterVO theaterVO_old = theaterProc.read(theaterVO.getTheaterno());
      
      // -------------------------------------------------------------------
      // 파일 삭제 시작
      // -------------------------------------------------------------------
      String img1saved = theaterVO_old.getImg1saved();  // 실제 저장된 파일명
      String thumbimg1 = theaterVO_old.getThumbimg1();       // 실제 저장된 povie 이미지 파일명
      long size1 = 0;
         
      String upDir =  Movie.getUploadDir(); // C:/kd/deploy/resort_v3sbm3c/theater/storage/
      
      Tool.deleteFile(upDir, img1saved);  // 실제 저장된 파일삭제
      Tool.deleteFile(upDir, thumbimg1);     // povie 이미지 삭제
      // -------------------------------------------------------------------
      // 파일 삭제 종료
      // -------------------------------------------------------------------
          
      // -------------------------------------------------------------------
      // 파일 전송 시작
      // -------------------------------------------------------------------
      String file1 = "";          // 원본 파일명 image

      // 전송 파일이 없어도 file1MF 객체가 생성됨.
      // <input type='file' class="form-control" name='file1MF' id='file1MF' 
      //           value='' placeholder="파일 선택">
      MultipartFile mf = theaterVO.getFile1MF();
          
      file1 = mf.getOriginalFilename(); // 원본 파일명
      size1 = mf.getSize();  // 파일 크기
          
      if (size1 > 0) { // 폼에서 새롭게 올리는 파일이 있는지 파일 크기로 체크 ★
        // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
        img1saved = Upload.saveFileSpring(mf, upDir); 
        
        if (Tool.isImage(img1saved)) { // 이미지인지 검사
          // thumb 이미지 생성후 파일명 리턴됨, width: 250, height: 200
          thumbimg1 = Tool.preview(upDir, img1saved, 250, 200); 
        }
        
      } else { // 파일이 삭제만 되고 새로 올리지 않는 경우
        file1="";
        img1saved="";
        thumbimg1="";
        size1=0;
      }
          
      theaterVO.setImg1(file1);
      theaterVO.setImg1saved(img1saved);
      theaterVO.setThumbimg1(thumbimg1);
      theaterVO.setSize1(size1);
      // -------------------------------------------------------------------
      // 파일 전송 코드 종료
      // -------------------------------------------------------------------
          
      this.theaterProc.update_file(theaterVO); // Oracle 처리

      mav.addObject("theaterno", theaterVO.getTheaterno());
      mav.setViewName("redirect:/theater/read.do"); // request -> param으로 접근 전환
                
    } else {
      mav.addObject("url", "/manager/login_need"); // login_need.jsp, redirect parameter 적용
      mav.setViewName("redirect:/theater/msg.do"); // GET
    }

    // redirect하게되면 전부 데이터가 삭제됨으로 mav 객체에 다시 저장
    mav.addObject("now_page", theaterVO.getNow_page());
    
    return mav; // forward
  }   
  
  /**
   * 파일 삭제 폼
   * http://localhost:9092/theater/delete.do?theaterno=1
   * 
   * @return
   */
  @RequestMapping(value = "/theater/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(HttpSession session, int theaterno) {
    ModelAndView mav = new ModelAndView();
    
    if (memProc.isMem(session)) { // 관리자로 로그인한경우
      TheaterVO theaterVO = this.theaterProc.read(theaterno);
      mav.addObject("theaterVO", theaterVO);
      
      mav.setViewName("/theater/delete"); // /WEB-INF/views/theater/delete.jsp
      
    } else {
      mav.addObject("url", "/mem/login_need"); // /WEB-INF/views/manager/login_need.jsp
      mav.setViewName("redirect:/theater/msg.do"); 
    }


    return mav; // forward
  }
  
  
  /**
   * 삭제 처리 http://localhost:9092/movie/delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/theater/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(TheaterVO theaterVO) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // 파일 삭제 시작
    // -------------------------------------------------------------------
    // 삭제할 파일 정보를 읽어옴.
    TheaterVO theaterVO_read = theaterProc.read(theaterVO.getTheaterno());
        
    String img1saved = theaterVO.getImg1saved();
    String thumbimg1 = theaterVO.getThumbimg1();
    
    String uploadDir = Theater.getUploadDir();
    Tool.deleteFile(uploadDir, img1saved);  // 실제 저장된 파일삭제
    Tool.deleteFile(uploadDir, thumbimg1);     // povie 이미지 삭제
    // -------------------------------------------------------------------
    // 파일 삭제 종료
    // -------------------------------------------------------------------
      
    this.theaterProc.delete(theaterVO.getTheaterno()); // DBMS 삭제
        
    // -------------------------------------------------------------------------------------
    // 마지막 페이지의 마지막 레코드 삭제시의 페이지 번호 -1 처리
    // -------------------------------------------------------------------------------------    
    // 마지막 페이지의 마지막 10번째 레코드를 삭제후
    // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면
    // 페이지수를 4 -> 3으로 감소 시켜야함, 마지막 페이지의 마지막 레코드 삭제시 나머지는 0 발생
    int now_page = theaterVO.getNow_page();
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", theaterVO.getWord());
    
    if (theaterProc.search_count(hashMap) % Theater.RECORD_PER_PAGE == 0) {
      now_page = now_page - 1; // 삭제시 DBMS는 바로 적용되나 크롬은 새로고침등의 필요로 단계가 작동 해야함.
      if (now_page < 1) {
        now_page = 1; // 시작 페이지
      }
    }
    // -------------------------------------------------------------------------------------

    mav.addObject("now_page", now_page);
    mav.setViewName("redirect:/theater/list_by_search_paging.do"); 
    
    return mav;
  } 
  


}
