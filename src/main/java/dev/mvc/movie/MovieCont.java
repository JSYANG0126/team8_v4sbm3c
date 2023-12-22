package dev.mvc.movie;

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

import dev.mvc.comments.CommentsProcInter;
import dev.mvc.comments.CommentsVO;
import dev.mvc.genre.GenreProcInter;
import dev.mvc.genre.GenreVO;
import dev.mvc.good.GoodProcInter;
import dev.mvc.good.GoodVO;
import dev.mvc.manager.ManagerProcInter;
import dev.mvc.mem.MemProcInter;
import dev.mvc.mem.MemVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class MovieCont {
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc") //@component("dev.mvc.manager.ManagerProc")
  private ManagerProcInter managerProc;
  
  @Autowired
  @Qualifier("dev.mvc.genre.GenreProc") //@component("dev.mvc.genre.GerneProc")
  private GenreProcInter genreProc;
  
  @Autowired
  @Qualifier("dev.mvc.movie.MovieProc") //@component("dev.mvc.comtents.MovieProc")
  private MovieProcInter movieProc;
  
  @Autowired
  @Qualifier("dev.mvc.comments.CommentsProc")
  private CommentsProcInter commentsProc;
  
  @Autowired
  @Qualifier("dev.mvc.mem.MemProc")
  private MemProcInter memProc;

  @Autowired
  @Qualifier("dev.mvc.good.GoodProc")
  private GoodProcInter goodProc;
  
  public MovieCont () {
    System.out.println("-> MovieCont created.");
  }
  
  
  /**
   * Post 요청시 JSP 페이지에서 JSTL 호출 기능 지원, 새로고침 방지, EL에서 param으로 접근
   * POST -> url -> GET -> 데이터 전송
   * @param genreno
   * @return
   */
  @RequestMapping(value="/movie/msg.do", method = RequestMethod.GET)
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
  @RequestMapping(value="/movie/create.do", method = RequestMethod.GET)
  public ModelAndView create(int genreno) {
//  public ModelAndView create(HttpServletRequest request,  int genreno) {
    ModelAndView mav = new ModelAndView();

    GenreVO genreVO = this.genreProc.read(genreno); // create.jsp에 카테고리 정보를 출력하기위한 목적
    mav.addObject("genreVO", genreVO);
    genreVO.setCnt(this.movieProc.count_by_genreno(genreno));
//    request.setAttribute("genreVO", genreVO);
    
    mav.setViewName("/movie/create"); // /webapp/WEB-INF/views/movie/create.jsp
    
    return mav;
  }

  
  /**
   * 등록 처리 http://localhost:9092/movie/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/movie/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, HttpSession session, MovieVO movieVO) {
    ModelAndView mav = new ModelAndView();
    
    if (managerProc.isManager(session)) { // 관리자로 로그인한경우
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 시작
      // ------------------------------------------------------------------------------
      String file1 = "";          // 원본 파일명 image
      String file1saved = "";   // 저장된 파일명, image
      String thumb1 = "";     // povie image

      String upDir =  Movie.getUploadDir(); //파일을 업로드할 폴더 준비
      System.out.println("-> upDir: " + upDir);
      
      // 전송 파일이 없어도 file1MF 객체가 생성됨.
      // <input type='file' class="form-control" name='file1MF' id='file1MF' 
      //           value='' placeholder="파일 선택">
      MultipartFile mf = movieVO.getFile1MF();
      
      file1 = mf.getOriginalFilename(); // 원본 파일명 산출, 01.png
      System.out.println("-> 원본 파일명 산출 file1: " + file1);
      
      long size1 = mf.getSize();  // 파일 크기
      
      if (size1 > 0) { // 파일 크기 체크
        // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
        file1saved = Upload.saveFileSpring(mf, upDir); 
        
        if (Tool.isImage(file1saved)) { // 이미지인지 검사
          // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
          thumb1 = Tool.preview(upDir, file1saved, 200, 150); 
        }
        
      }    
      
      movieVO.setFile1(file1);   // 순수 원본 파일명
      movieVO.setFile1saved(file1saved); // 저장된 파일명(파일명 중복 처리)
      movieVO.setThumb1(thumb1);      // 원본이미지 축소판
      movieVO.setSize1(size1);  // 파일 크기
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 종료
      // ------------------------------------------------------------------------------
      
      // Call By Reference: 메모리 공유, Hashcode 전달
      int managerno = (int)session.getAttribute("managerno"); // managerno FK
      movieVO.setManagerno(managerno);
      int cnt = this.movieProc.create(movieVO); 
      
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
      mav.addObject("genreno", movieVO.getGenreno()); // redirect parameter 적용
      
      mav.addObject("url", "/movie/msg"); // msg.jsp, redirect parameter 적용
      mav.setViewName("redirect:/movie/msg.do"); 

    } else {
      mav.addObject("url", "/manager/login_need"); // /WEB-INF/views/manager/login_need.jsp
      mav.setViewName("redirect:/movie/msg.do"); 
    }
    
    return mav; // forward
  }
  
  /**
   * 전체 목록
   * http://localhost:9092/movie/list_all.do
   * @return
   */
  @RequestMapping(value="/movie/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_all(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if (this.managerProc.isManager(session) == true) {
      mav.setViewName("/movie/list_all"); // /WEB-INF/views/movie/list_all.jsp
      
      ArrayList<MovieVO> list = this.movieProc.list_all();
      
   // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
      for (MovieVO movieVO : list) {
        String title = movieVO.getTitle();
        String content = movieVO.getContent();
        
        title = Tool.convertChar(title);  // 특수 문자 처리
        content = Tool.convertChar(content); 
        
        movieVO.setTitle(title);
        movieVO.setContent(content);  

      }
      
      mav.addObject("list", list);
      
    } else {
      mav.setViewName("/manager/login_need"); // /WEB-INF/views/manager/login_need.jsp
      
    }
    
    return mav;
  }
  
//  /**
//   * 특정 카테고리의 검색 목록
//   * http://localhost:9092/movie/list_by_genreno.do?genreno=1
//   * @return
//   */
//  @RequestMapping(value="/movie/list_by_genreno.do", method = RequestMethod.GET)
//  public ModelAndView list_by_genreno(int genreno, String word) {
//    ModelAndView mav = new ModelAndView();
//
//    mav.setViewName("/movie/list_by_genreno"); // /WEB-INF/views/movie/list_by_genreno.jsp
//    
//    GenreVO genreVO = this.genreProc.read(genreno); // create.jsp에 카테고리 정보를 출력하기위한 목적
//    mav.addObject("genreVO", genreVO);
//    // request.setAttribute("genreVO", genreVO);
//    
//    // 검색하지 않는 경우
//    // ArrayList<MovieVO> list = this.movieProc.list_by_genreno(genreno);
//
//    // 검색하는 경우
//    HashMap<String, Object> hashMap = new HashMap<String, Object>();
//    hashMap.put("genreno", genreno);
//    hashMap.put("word", word);
//    
//    int search_count = this.movieProc.search_count(hashMap);  // 검색된 레코드 갯수 ->  전체 페이지 규모 파악
//    mav.addObject("search_count", search_count);
//    
//    ArrayList<MovieVO> list = this.movieProc.list_by_genreno_search(hashMap);
//    
//    // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
//    for (MovieVO movieVO : list) {
//      String title = movieVO.getTitle();
//      String content = movieVO.getContent();
//      
//      title = Tool.convertChar(title);  // 특수 문자 처리
//      content = Tool.convertChar(content); 
//      
//      movieVO.setTitle(title);
//      movieVO.setContent(content);  
//
//    }
//    
//    mav.addObject("list", list);
//    
//    return mav;
//  }  
  
  /**
   * 목록 + 검색 + 페이징 지원
   * http://localhost:9092/movie/list_by_genreno.do?genreno=1&word=스위스&now_page=1
   * 
   * @param genreno
   * @param word
   * @param now_page
   * @return
   */
  @RequestMapping(value = "/movie/list_by_genreno.do", method = RequestMethod.GET)
  public ModelAndView list_by_genreno(MovieVO movieVO) {
    ModelAndView mav = new ModelAndView();
  
    // 검색 목록
    ArrayList<MovieVO> list = movieProc.list_by_genreno_search_paging(movieVO);
    
    // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
    for (MovieVO vo : list) {
      String title = vo.getTitle();
      String content = vo.getContent();
      
      title = Tool.convertChar(title);  // 특수 문자 처리
      content = Tool.convertChar(content); 
      
      vo.setTitle(title);
      vo.setContent(content);  
  
    }
    
    mav.addObject("list", list);
  
    GenreVO genreVO = genreProc.read(movieVO.getGenreno());
    mav.addObject("genreVO", genreVO);
  
    genreVO.setCnt(this.movieProc.count_by_genreno(movieVO.getGenreno()));
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("genreno", movieVO.getGenreno());
    hashMap.put("word", movieVO.getWord());
    
    int search_count = this.movieProc.search_count(hashMap);  // 검색된 레코드 갯수 ->  전체 페이지 규모 파악
    mav.addObject("search_count", search_count);
    
    /*
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16 17
     * 18 19 20 [다음]
     * @param genreno 카테고리번호
     * @param now_page 현재 페이지
     * @param word 검색어
     * @return 페이징용으로 생성된 HTML/CSS tag 문자열
     */
    String paging = movieProc.pagingBox(movieVO.getGenreno(), movieVO.getNow_page(), movieVO.getWord(), "list_by_genreno.do", search_count);
    mav.addObject("paging", paging);
  
    // mav.addObject("now_page", now_page);
    
    mav.setViewName("/movie/list_by_genreno");  // /movie/list_by_genreno.jsp
  
    return mav;
  }
  
  /**
   * 목록 + 검색 + 페이징 지원 + Grid
   * 검색하지 않는 경우
   * http://localhost:9092/movie/list_by_genreno_grid.do?genreno=2&word=&now_page=1
   * 검색하는 경우
   * http://localhost:9092/movie/list_by_genreno_grid.do?genreno=2&word=탐험&now_page=1
   * 
   * @param genreno
   * @param word
   * @param now_page
   * @return
   */
   @RequestMapping(value = "/movie/list_by_genreno_grid.do", method = RequestMethod.GET)
   public ModelAndView list_by_genreno_grid(MovieVO movieVO) {
      ModelAndView mav = new ModelAndView();
    
      // 검색 목록
    ArrayList<MovieVO> list = movieProc.list_by_genreno_search_paging(movieVO);
    
    // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
    for (MovieVO vo : list) {
      String title = vo.getTitle();
      String content = vo.getContent();
      
      title = Tool.convertChar(title);  // 특수 문자 처리
        content = Tool.convertChar(content); 
        
        vo.setTitle(title);
        vo.setContent(content);  
    
      }
      
      mav.addObject("list", list);
    
      GenreVO genreVO = genreProc.read(movieVO.getGenreno());
      genreVO.setCnt(this.movieProc.count_by_genreno(movieVO.getGenreno()));
      mav.addObject("genreVO", genreVO);
      
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("genreno", movieVO.getGenreno());
      hashMap.put("word", movieVO.getWord());
      
      int search_count = this.movieProc.search_count(hashMap);  // 검색된 레코드 갯수 ->  전체 페이지 규모 파악
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
    String paging = movieProc.pagingBox(movieVO.getGenreno(), movieVO.getNow_page(), movieVO.getWord(), "list_by_genreno_grid.do", search_count);
    mav.addObject("paging", paging);
    
      // mav.addObject("now_page", now_page);
    
    mav.setViewName("/movie/list_by_genreno_grid");  // /movie/list_by_genreno_grid.jsp
    
      return mav;
    }
   
  /**
   * 조회
   * http://localhost:9092/movie/read.do?movieno=17
   * @return
   */
  @RequestMapping(value="/movie/read.do", method = RequestMethod.GET)
  public ModelAndView read(HttpSession session, int movieno) { // int genreno = (int)request.getParameter("genreno");
    ModelAndView mav = new ModelAndView();
    if (this.managerProc.isManager(session) == true || this.memProc.isMem(session)) {
      
      mav.setViewName("/movie/read"); // /WEB-INF/views/movie/read.jsp
      
      MovieVO movieVO = this.movieProc.read(movieno);
      
      
      String title = movieVO.getTitle();
      String content = movieVO.getContent();
      long size1 = movieVO.getSize1();
      
      title = Tool.convertChar(title);  // 특수 문자 처리
      content = Tool.convertChar(content); 
      String size1_label = Tool.unit(size1);
      
      movieVO.setTitle(title);
      movieVO.setContent(content);  
      movieVO.setSize1_label(size1_label);
      
      ArrayList<CommentsVO> list_comments = this.commentsProc.list_by_movieno(movieno);
      
      
      int good_cnt = this.goodProc.good_cnt(movieno);
      mav.addObject("good_cnt", good_cnt);
      mav.addObject("list_comments", list_comments);
      mav.addObject("movieVO", movieVO);
    } else {
        mav.setViewName("/mem/login_need");
    }
    
    return mav;
  }
  
  /**
   * 맵 등록/수정/삭제 폼
   * http://localhost:9092/movie/map.do?movieno=1
   * @return
   */
  @RequestMapping(value="/movie/map.do", method=RequestMethod.GET )
  public ModelAndView map(int movieno) {
    ModelAndView mav = new ModelAndView();

    MovieVO movieVO = this.movieProc.read(movieno); // map 정보 읽어 오기
    mav.addObject("movieVO", movieVO); // request.setAttribute("movieVO", movieVO);

    GenreVO genreVO = this.genreProc.read(movieVO.getGenreno()); // 그룹 정보 읽기
    mav.addObject("genreVO", genreVO); 

    mav.setViewName("/movie/map"); // /WEB-INF/views/movie/map.jsp
        
    return mav;
  }
  
  /**
   * MAP 등록/수정/삭제 처리
   * http://localhost:9092/movie/map.do
   * @param movieVO
   * @return
   */
  @RequestMapping(value="/movie/map.do", method = RequestMethod.POST)
  public ModelAndView map_update(int movieno, String map) {
    ModelAndView mav = new ModelAndView();
    
    HashMap<String,Object> hashMap = new HashMap<String,Object>();
    hashMap.put("movieno", movieno);
    hashMap.put("map", map);
    
    this.movieProc.map(hashMap);
    
    mav.setViewName("redirect:/movie/read.do?movieno=" + movieno); 
    // /webapp/WEB-INF/views/movie/read.jsp
    
    return mav;
  }
  
  /**
   * Youtube 등록/수정/삭제 폼
   * http://localhost:9092/movie/youtube.do?movieno=1
   * @return
   */
  @RequestMapping(value="/movie/youtube.do", method=RequestMethod.GET )
  public ModelAndView youtube(int movieno) {
    ModelAndView mav = new ModelAndView();

    MovieVO movieVO = this.movieProc.read(movieno); // youtube 정보 읽어 오기
    mav.addObject("movieVO", movieVO); // request.setAttribute("movieVO", movieVO);

    GenreVO genreVO = this.genreProc.read(movieVO.getGenreno()); // 그룹 정보 읽기
    mav.addObject("genreVO", genreVO); 

    mav.setViewName("/movie/youtube"); // /WEB-INF/views/movie/youtube.jsp
        
    return mav;
  }
  
  /**
   * Youtube 등록/수정/삭제 처리
   * http://localhost:9092/movie/youtube.do
   * @param movieno 글 번호
   * @param youtube Youtube url의 소스 코드
   * @return
   */
  @RequestMapping(value="/movie/youtube.do", method = RequestMethod.POST)
  public ModelAndView youtube_update(int movieno, String youtube) {
    ModelAndView mav = new ModelAndView();
    
    if (youtube.trim().length() > 0) {  // 삭제 중인지 확인, 삭제가 아니면 youtube 크기 변경
      youtube = Tool.youtubeResize(youtube, 640);  // youtube 영상의 크기를 width 기준 640 px로 변경
    }    
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("movieno", movieno);
    hashMap.put("youtube", youtube);
    
    this.movieProc.youtube(hashMap);
    
    mav.setViewName("redirect:/movie/read.do?movieno=" + movieno); 
    // /webapp/WEB-INF/views/movie/read.jsp
    
    return mav;
  }
  
  /**
   * 수정 폼
   * http://localhost:9091/movie/update_text.do?movieno=1
   * 
   * @return
   */
  @RequestMapping(value = "/movie/update_text.do", method = RequestMethod.GET)
  public ModelAndView update_text(HttpSession session, int movieno) {
    ModelAndView mav = new ModelAndView();
    
    if (managerProc.isManager(session)) { // 관리자로 로그인한경우
    	MovieVO movieVO = this.movieProc.read(movieno);
      mav.addObject("movieVO", movieVO);
      
      GenreVO genreVO = this.genreProc.read(movieVO.getGenreno());
      genreVO.setCnt(this.movieProc.count_by_genreno(movieVO.getGenreno()));
      mav.addObject("genreVO", genreVO);
      
      mav.setViewName("/movie/update_text"); // /WEB-INF/views/movie/update_text.jsp
      // String content = "장소:\n인원:\n준비물:\n비용:\n기타:\n";
      // mav.addObject("content", content);

    } else {
      mav.addObject("url", "/manager/login_need"); // /WEB-INF/views/manager/login_need.jsp
      mav.setViewName("redirect:/movie/msg.do"); 
    }

    return mav; // forward
  }
  
  /**
   * 수정 처리
   * http://localhost:9091/movie/update_text.do?movieno=1
   * 
   * @return
   */
  @RequestMapping(value = "/movie/update_text.do", method = RequestMethod.POST)
  public ModelAndView update_text(HttpSession session, MovieVO movieVO) {
    ModelAndView mav = new ModelAndView();
    
    // System.out.println("-> word: " + movieVO.getWord());
    
    if (this.managerProc.isManager(session)) { // 관리자 로그인 확인
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("movieno", movieVO.getMovieno());
      hashMap.put("passwd", movieVO.getPasswd());
      
      if (this.movieProc.password_check(hashMap) == 1) { // 패스워드 일치
        this.movieProc.update_text(movieVO); // 글수정  
         
        // mav 객체 이용
        mav.addObject("movieno", movieVO.getMovieno());
        mav.addObject("genreno", movieVO.getGenreno());
        mav.setViewName("redirect:/movie/read.do"); // 페이지 자동 이동
        
      } else { // 패스워드 불일치
        mav.addObject("code", "passwd_fail");
        mav.addObject("cnt", 0);
        mav.addObject("url", "/movie/msg"); // msg.jsp, redirect parameter 적용
        mav.setViewName("redirect:/movie/msg.do");  // POST -> GET -> JSP 출력
      }
    } else { // 정상적인 로그인이 아닌 경우 로그인 유도
      mav.addObject("url", "/manager/login_need"); // /WEB-INF/views/manager/login_need.jsp
      mav.setViewName("redirect:/movie/msg.do"); 
    }
    
    mav.addObject("now_page", movieVO.getNow_page()); // POST -> GET: 데이터 분실이 발생함으로 다시하번 데이터 저장 ★
    
    // URL에 파라미터의 전송
    // mav.setViewName("redirect:/movie/read.do?movieno=" + movieVO.getMovieno() + "&genreno=" + movieVO.getGenreno());             
    
    return mav; // forward
  }
  
    /**
     * 파일 수정 폼
     * http://localhost:9092/movie/update_file.do?movieno=1
     * 
     * @return
     */
    @RequestMapping(value = "/movie/update_file.do", method = RequestMethod.GET)
    public ModelAndView update_file(HttpSession session, int movieno) {
      ModelAndView mav = new ModelAndView();
      
      if (managerProc.isManager(session)) { // 관리자로 로그인한경우
        MovieVO movieVO = this.movieProc.read(movieno);
        mav.addObject("movieVO", movieVO);
        
        GenreVO genreVO = this.genreProc.read(movieVO.getGenreno());
        mav.addObject("genreVO", genreVO);
        
        mav.setViewName("/movie/update_file"); // /WEB-INF/views/movie/update_file.jsp
        
      } else {
        mav.addObject("url", "/manager/login_need"); // /WEB-INF/views/manager/login_need.jsp
        mav.setViewName("redirect:/movie/msg.do"); 
      }


      return mav; // forward
    }
    
    /**
     * 파일 수정 처리 http://localhost:9091/movie/update_file.do
     * 
     * @return
     */
    @RequestMapping(value = "/movie/update_file.do", method = RequestMethod.POST)
    public ModelAndView update_file(HttpSession session, MovieVO movieVO) {
      ModelAndView mav = new ModelAndView();
      
      if (this.managerProc.isManager(session)) {
        // 삭제할 파일 정보를 읽어옴, 기존에 등록된 레코드 저장용
    	  MovieVO movieVO_old = movieProc.read(movieVO.getMovieno());
        
        // -------------------------------------------------------------------
        // 파일 삭제 시작
        // -------------------------------------------------------------------
        String file1saved = movieVO_old.getFile1saved();  // 실제 저장된 파일명
        String thumb1 = movieVO_old.getThumb1();       // 실제 저장된 povie 이미지 파일명
        long size1 = 0;
           
        String upDir =  Movie.getUploadDir(); // C:/kd/deploy/resort_v3sbm3c/movie/storage/
        
        Tool.deleteFile(upDir, file1saved);  // 실제 저장된 파일삭제
        Tool.deleteFile(upDir, thumb1);     // povie 이미지 삭제
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
        MultipartFile mf = movieVO.getFile1MF();
            
        file1 = mf.getOriginalFilename(); // 원본 파일명
        size1 = mf.getSize();  // 파일 크기
            
        if (size1 > 0) { // 폼에서 새롭게 올리는 파일이 있는지 파일 크기로 체크 ★
          // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
          file1saved = Upload.saveFileSpring(mf, upDir); 
          
          if (Tool.isImage(file1saved)) { // 이미지인지 검사
            // thumb 이미지 생성후 파일명 리턴됨, width: 250, height: 200
            thumb1 = Tool.preview(upDir, file1saved, 250, 200); 
          }
          
        } else { // 파일이 삭제만 되고 새로 올리지 않는 경우
          file1="";
          file1saved="";
          thumb1="";
          size1=0;
        }
            
        movieVO.setFile1(file1);
        movieVO.setFile1saved(file1saved);
        movieVO.setThumb1(thumb1);
        movieVO.setSize1(size1);
        // -------------------------------------------------------------------
        // 파일 전송 코드 종료
        // -------------------------------------------------------------------
            
        this.movieProc.update_file(movieVO); // Oracle 처리

        mav.addObject("movieno", movieVO.getMovieno());
        mav.addObject("genreno", movieVO.getGenreno());
        mav.setViewName("redirect:/movie/read.do"); // request -> param으로 접근 전환
                  
      } else {
        mav.addObject("url", "/manager/login_need"); // login_need.jsp, redirect parameter 적용
        mav.setViewName("redirect:/movie/msg.do"); // GET
      }

      // redirect하게되면 전부 데이터가 삭제됨으로 mav 객체에 다시 저장
      mav.addObject("now_page", movieVO.getNow_page());
      
      return mav; // forward
    }   
    
    /**
     * 파일 삭제 폼
     * http://localhost:9092/movie/delete.do?movieno=1
     * 
     * @return
     */
    @RequestMapping(value = "/movie/delete.do", method = RequestMethod.GET)
    public ModelAndView delete(HttpSession session, int movieno) {
      ModelAndView mav = new ModelAndView();
      
      if (managerProc.isManager(session)) { // 관리자로 로그인한경우
        MovieVO movieVO = this.movieProc.read(movieno);
        mav.addObject("movieVO", movieVO);
        
        GenreVO genreVO = this.genreProc.read(movieVO.getGenreno());
        genreVO.setCnt(this.movieProc.count_by_genreno(movieVO.getGenreno()));
        mav.addObject("genreVO", genreVO);
        
        mav.setViewName("/movie/delete"); // /WEB-INF/views/movie/delete.jsp
        
      } else {
        mav.addObject("url", "/manager/login_need"); // /WEB-INF/views/manager/login_need.jsp
        mav.setViewName("redirect:/movie/msg.do"); 
      }


      return mav; // forward
    }
    
    /**
     * 삭제 처리 http://localhost:9092/movie/delete.do
     * 
     * @return
     */
    @RequestMapping(value = "/movie/delete.do", method = RequestMethod.POST)
    public ModelAndView delete(MovieVO movieVO) {
      ModelAndView mav = new ModelAndView();

      // -------------------------------------------------------------------
      // 파일 삭제 시작
      // -------------------------------------------------------------------
      // 삭제할 파일 정보를 읽어옴.
      MovieVO movieVO_read = movieProc.read(movieVO.getMovieno());
      GenreVO genreVO = this.genreProc.read(movieVO.getGenreno());
      genreVO.setCnt(this.movieProc.count_by_genreno(movieVO.getGenreno()));
      
      String file1saved = movieVO.getFile1saved();
      String thumb1 = movieVO.getThumb1();
      
      String uploadDir = Movie.getUploadDir();
      Tool.deleteFile(uploadDir, file1saved);  // 실제 저장된 파일삭제
      Tool.deleteFile(uploadDir, thumb1);     // povie 이미지 삭제
      // -------------------------------------------------------------------
      // 파일 삭제 종료
      // -------------------------------------------------------------------
      
      this.goodProc.delete_by_movieno(movieVO.getMovieno());
      this.commentsProc.delete_by_movieno(movieVO.getMovieno());
      this.movieProc.delete(movieVO.getMovieno()); // DBMS 삭제
          
      // -------------------------------------------------------------------------------------
      // 마지막 페이지의 마지막 레코드 삭제시의 페이지 번호 -1 처리
      // -------------------------------------------------------------------------------------    
      // 마지막 페이지의 마지막 10번째 레코드를 삭제후
      // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면
      // 페이지수를 4 -> 3으로 감소 시켜야함, 마지막 페이지의 마지막 레코드 삭제시 나머지는 0 발생
      int now_page = movieVO.getNow_page();
      
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("genreno", movieVO.getGenreno());
      hashMap.put("word", movieVO.getWord());
      
      if (movieProc.search_count(hashMap) % Movie.RECORD_PER_PAGE == 0) {
        now_page = now_page - 1; // 삭제시 DBMS는 바로 적용되나 크롬은 새로고침등의 필요로 단계가 작동 해야함.
        if (now_page < 1) {
          now_page = 1; // 시작 페이지
        }
      }
      // -------------------------------------------------------------------------------------

      mav.addObject("genreno", movieVO.getGenreno());
      mav.addObject("now_page", now_page);
      mav.setViewName("redirect:/movie/list_by_genreno.do"); 
      
      return mav;
    }   
  
    // http://localhost:9092/movie/delete_by_genreno.do?genreno=1
    // 파일 삭제 -> 레코드 삭제
    @RequestMapping(value = "/movie/delete_by_genreno.do", method = RequestMethod.GET)
    public String delete_by_genreno(int genreno) {
      ArrayList<MovieVO> list = this.movieProc.list_by_genreno(genreno);
      
      for(MovieVO movieVO : list) {
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

      int cnt = this.movieProc.delete_by_genreno(genreno);
      System.out.println("-> count: " + cnt);
      
      return "";
    
    }
    
}