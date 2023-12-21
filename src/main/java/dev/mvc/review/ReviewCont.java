package dev.mvc.review;

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
import dev.mvc.manager.ManagerProcInter;
import dev.mvc.mem.MemProcInter;
import dev.mvc.mem.MemVO;
import dev.mvc.nice.NiceProcInter;
import dev.mvc.reply.ReplyProcInter;
import dev.mvc.reply.ReplyVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class ReviewCont {
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc") 
  private ManagerProcInter managerProc;
   
  @Autowired
  @Qualifier("dev.mvc.mem.MemProc") 
  private MemProcInter memProc;
  
  @Autowired
  @Qualifier("dev.mvc.review.ReviewProc")
  private ReviewProcInter reviewProc;
  
  @Autowired
  @Qualifier("dev.mvc.reply.ReplyProc") 
  private ReplyProcInter replyProc;

  @Autowired
  @Qualifier("dev.mvc.nice.NiceProc") 
  private NiceProcInter niceProc;
  
  public ReviewCont () {
    System.out.println("-> ReviewCont created.");
  }
  
  
  /**
   * Post 요청시 JSP 페이지에서 JSTL 호출 기능 지원, 새로고침 방지, EL에서 param으로 접근
   * POST -> url -> GET -> 데이터 전송
   * @param genreno
   * @return
   */
  @RequestMapping(value="/review/msg.do", method = RequestMethod.GET)
  public ModelAndView msg(String url) {
//  public ModelAndView create(HttpServletRequest request,  int genreno) {
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav;
  }
  
  // 등록 폼, movie 테이블은 FK로 genreno를 사용함.
  @RequestMapping(value="/review/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/review/create"); 
    
    return mav;
  }

  
  /**
   * 등록 처리 http://localhost:9092/movie/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/review/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, HttpSession session, ReviewVO reviewVO) {
    ModelAndView mav = new ModelAndView();
    
    if (memProc.isMem(session)) { // 관리자로 로그인한경우
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 시작
      // ------------------------------------------------------------------------------
      String file1 = "";          // 원본 파일명 image
      String file1saved = "";   // 저장된 파일명, image
      String thumb1 = "";     // povie image

      String upDir =  Review.getUploadDir(); //파일을 업로드할 폴더 준비
      System.out.println("-> upDir: " + upDir);
      
      // 전송 파일이 없어도 file1MF 객체가 생성됨.
      // <input type='file' class="form-control" name='file1MF' id='file1MF' 
      //           value='' placeholder="파일 선택">
      MultipartFile mf = reviewVO.getFile1MF();
      
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
      
      reviewVO.setFile1(file1);   // 순수 원본 파일명
      reviewVO.setFile1saved(file1saved); // 저장된 파일명(파일명 중복 처리)
      reviewVO.setThumb1(thumb1);      // 원본이미지 축소판
      reviewVO.setSize1(size1);  // 파일 크기
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 종료
      // ------------------------------------------------------------------------------
      
      // Call By Reference: 메모리 공유, Hashcode 전달
      int memno = (int)session.getAttribute("memno"); // managerno FK
      reviewVO.setMemno(memno);
      MemVO memVO = this.memProc.read(memno);
      reviewVO.setRname(memVO.getMname());
      
      int cnt = this.reviewProc.create(reviewVO); 
      
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

      mav.addObject("url", "/review/msg"); // msg.jsp, redirect parameter 적용
      mav.setViewName("redirect:/review/msg.do"); 

    } else {
      mav.addObject("url", "/mem/login_need"); // /WEB-INF/views/manager/login_need.jsp
      mav.setViewName("redirect:/review/msg.do"); 
    }
    
    return mav; // forward
  }
  
//  /**
//   * 전체 목록
//   * http://localhost:9092/movie/list_all.do
//   * @return
//   */
//  @RequestMapping(value="/review/list_all.do", method = RequestMethod.GET)
//  public ModelAndView list_all(HttpSession session) {
//    ModelAndView mav = new ModelAndView();
//    
//    if (this.memProc.isMem(session) == true) {
//      mav.setViewName("/review/list_all"); // /WEB-INF/views/movie/list_all.jsp
//      
//      ArrayList<ReviewVO> list = this.reviewProc.list();
//      
//   // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
//      for (ReviewVO reviewVO : list) {
//        String rtitle = reviewVO.getRtitle();
//        String rinfo = reviewVO.getRinfo();
//        
//        rtitle = Tool.convertChar(rtitle);  // 특수 문자 처리
//        rinfo = Tool.convertChar(rinfo); 
//        
//        reviewVO.setRtitle(rtitle);
//        reviewVO.setRinfo(rinfo);  
//
//      }
//      
//      mav.addObject("list", list);
//      
//    } else {
//      mav.setViewName("/mem/login_need"); // /WEB-INF/views/manager/login_need.jsp
//      
//    }
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
  @RequestMapping(value = "/review/list_paging.do", method = RequestMethod.GET)
  public ModelAndView list_by_genreno(ReviewVO reviewVO) {
    ModelAndView mav = new ModelAndView();
  
    // 검색 목록
    ArrayList<ReviewVO> list = reviewProc.list_search_paging(reviewVO);
    
    // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
    for (ReviewVO vo : list) {
      String rtitle = vo.getRtitle();
      String rinfo = vo.getRinfo();
      
      rtitle = Tool.convertChar(rtitle);  // 특수 문자 처리
      rinfo = Tool.convertChar(rinfo); 
      
      vo.setRtitle(rtitle);
      vo.setRinfo(rinfo);  
  
    }
    
    mav.addObject("list", list);
  
  
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", reviewVO.getWord());
    int search_count = this.reviewProc.search_count(hashMap);  // 검색된 레코드 갯수 ->  전체 페이지 규모 파악
    mav.addObject("search_count", search_count);
    
    
    /*
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16 17
     * 18 19 20 [다음]
     * @param genreno 카테고리번호
     * @param now_page 현재 페이지
     * @param word 검색어
     * @return 페이징용으로 생성된 HTML/CSS tag 문자열
     */
    String paging = reviewProc.pagingBox(reviewVO.getNow_page(), reviewVO.getWord(), "list_paging.do", search_count);
    mav.addObject("paging", paging);
  
    // mav.addObject("now_page", now_page);
    
    mav.setViewName("/review/list_paging");  // /movie/list_by_genreno.jsp
  
    return mav;
  }
   
  /**
   * 갤러리형 목록 만들기
   * @param substancesVO
   * @return
   */
  @RequestMapping(value="/review/list_grid.do", method = RequestMethod.GET)
  public ModelAndView list_by_kindno_grid(ReviewVO reviewVO) {
    ModelAndView mav = new ModelAndView();
    
    ArrayList<ReviewVO> list = this.reviewProc.list_search_paging(reviewVO);

    for(ReviewVO vo : list) {
      
      String rtitle = vo.getRtitle();
      String rinfo = vo.getRinfo();
        
      rtitle = Tool.convertChar(rtitle);
      rinfo = Tool.convertChar(rinfo);
        
      vo.setRtitle(rtitle);
      vo.setRinfo(rinfo);
    }
    mav.addObject("list", list);
  
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", reviewVO.getWord());
   
    int search_count = this.reviewProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
    
    
    String paging = reviewProc.pagingBox(reviewVO.getNow_page(), reviewVO.getWord(), "list_grid.do", search_count);
    mav.addObject("paging", paging);
    
    mav.setViewName("/review/list_grid");
  
    return mav;
  }    
  /**
   * 조회
   * http://localhost:9092/movie/read.do?movieno=17
   * @return
   */
  @RequestMapping(value="/review/read.do", method = RequestMethod.GET)
  public ModelAndView read(int reviewno) { // int genreno = (int)request.getParameter("genreno");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/review/read"); // /WEB-INF/views/movie/read.jsp
    
    ReviewVO reviewVO = this.reviewProc.read(reviewno);
    
    String rtitle = reviewVO.getRtitle();
    String rinfo = reviewVO.getRinfo();
    long size1 = reviewVO.getSize1();
    
    rtitle = Tool.convertChar(rtitle);  // 특수 문자 처리
    rinfo = Tool.convertChar(rinfo); 
    String size1_label = Tool.unit(size1);
    
    reviewVO.setRtitle(rtitle);
    reviewVO.setRinfo(rinfo);  
    reviewVO.setSize1_label(size1_label);

    ArrayList<ReplyVO> list_reply = this.replyProc.list_by_reviewno(reviewno);
    int nice_cnt = this.niceProc.nice_cnt(reviewno);
    mav.addObject("nice_cnt", nice_cnt);
    mav.addObject("list_reply", list_reply);
    mav.addObject("reviewVO", reviewVO);
    
    return mav;
  }
  

  /**
   * 수정 폼
   * http://localhost:9091/movie/update_text.do?movieno=1
   * 
   * @return
   */
  @RequestMapping(value = "/review/update_text.do", method = RequestMethod.GET)
  public ModelAndView update_text(HttpSession session, int reviewno) {
    ModelAndView mav = new ModelAndView();
    
    if (memProc.isMem(session)) { 
      ReviewVO reviewVO = this.reviewProc.read(reviewno);
      mav.addObject("reviewVO", reviewVO);
      
      
      mav.setViewName("/review/update_text"); // /WEB-INF/views/movie/update_text.jsp
      // String content = "장소:\n인원:\n준비물:\n비용:\n기타:\n";
      // mav.addObject("content", content);

    } else {
      mav.addObject("url", "/mem/login_need"); // /WEB-INF/views/manager/login_need.jsp
      mav.setViewName("redirect:/review/msg.do"); 
    }

    return mav; // forward
  }
  
  /**
   * 수정 처리
   * http://localhost:9091/movie/update_text.do?movieno=1
   * 
   * @return
   */
  @RequestMapping(value = "/review/update_text.do", method = RequestMethod.POST)
  public ModelAndView update_text(HttpSession session, ReviewVO reviewVO) {
    ModelAndView mav = new ModelAndView();
    
    // System.out.println("-> word: " + movieVO.getWord());
    
    if (this.memProc.isMem(session)) { // 관리자 로그인 확인
      
      
      this.reviewProc.update(reviewVO); // 글수정  
         
        
      mav.addObject("reviewno", reviewVO.getReviewno());
      mav.setViewName("redirect:/review/read.do"); // 페이지 자동 이동
        


    } else { // 정상적인 로그인이 아닌 경우 로그인 유도
      mav.addObject("url", "/mem/login_need"); // /WEB-INF/views/manager/login_need.jsp
      mav.setViewName("redirect:/review/msg.do"); 
    }
    
    mav.addObject("now_page", reviewVO.getNow_page()); // POST -> GET: 데이터 분실이 발생함으로 다시하번 데이터 저장 ★
           
    
    return mav; // forward
  }
 
    /**
     * 파일 수정 폼
     * http://localhost:9092/movie/update_file.do?movieno=1
     * 
     * @return
     */
    @RequestMapping(value = "/review/update_file.do", method = RequestMethod.GET)
    public ModelAndView update_file(HttpSession session, int reviewno) {
      ModelAndView mav = new ModelAndView();
      
      if (memProc.isMem(session)) { // 관리자로 로그인한경우
        ReviewVO reviewVO = this.reviewProc.read(reviewno);
        mav.addObject("reviewVO", reviewVO);
        
        
        mav.setViewName("/review/update_file"); // /WEB-INF/views/movie/update_file.jsp
        
      } else {
        mav.addObject("url", "/mem/login_need"); // /WEB-INF/views/manager/login_need.jsp
        mav.setViewName("redirect:/review/msg.do"); 
      }


      return mav; 
    }
    
    /**
     * 파일 수정 처리 http://localhost:9091/movie/update_file.do
     * 
     * @return
     */
    @RequestMapping(value = "/review/update_file.do", method = RequestMethod.POST)
    public ModelAndView update_file(HttpSession session, ReviewVO reviewVO) {
      ModelAndView mav = new ModelAndView();
      
      if (this.memProc.isMem(session)) {
        // 삭제할 파일 정보를 읽어옴, 기존에 등록된 레코드 저장용
        ReviewVO reviewVO_old = reviewProc.read(reviewVO.getReviewno());
        
        // -------------------------------------------------------------------
        // 파일 삭제 시작
        // -------------------------------------------------------------------
        String file1saved = reviewVO_old.getFile1saved();  // 실제 저장된 파일명
        String thumb1 = reviewVO_old.getThumb1();       // 실제 저장된 povie 이미지 파일명
        long size1 = 0;
           
        String upDir =  Review.getUploadDir(); // C:/kd/deploy/resort_v3sbm3c/movie/storage/
        
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
        MultipartFile mf = reviewVO.getFile1MF();
            
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
            
        reviewVO.setFile1(file1);
        reviewVO.setFile1saved(file1saved);
        reviewVO.setThumb1(thumb1);
        reviewVO.setSize1(size1);
        // -------------------------------------------------------------------
        // 파일 전송 코드 종료
        // -------------------------------------------------------------------
            
        this.reviewProc.update_file(reviewVO); // Oracle 처리

        mav.addObject("reviewno", reviewVO.getReviewno());
        mav.setViewName("redirect:/review/read.do"); // request -> param으로 접근 전환
                  
      } else {
        mav.addObject("url", "/mem/login_need"); // login_need.jsp, redirect parameter 적용
        mav.setViewName("redirect:/review/msg.do"); // GET
      }

      // redirect하게되면 전부 데이터가 삭제됨으로 mav 객체에 다시 저장
      mav.addObject("now_page", reviewVO.getNow_page());
      
      return mav; // forward
    }   
    
    /**
     * 파일 삭제 폼
     * http://localhost:9092/movie/delete.do?movieno=1
     * 
     * @return
     */
    @RequestMapping(value = "/review/delete.do", method = RequestMethod.GET)
    public ModelAndView delete(HttpSession session, int reviewno) {
      ModelAndView mav = new ModelAndView();
      
      if (memProc.isMem(session)) { // 관리자로 로그인한경우
        ReviewVO reviewVO = this.reviewProc.read(reviewno);
        mav.addObject("reviewVO", reviewVO);
        
        mav.setViewName("/review/delete"); // /WEB-INF/views/movie/delete.jsp
        
      } else {
        mav.addObject("url", "/mem/login_need"); // /WEB-INF/views/manager/login_need.jsp
        mav.setViewName("redirect:/review/msg.do"); 
      }


      return mav; // forward
    }
    
    /**
     * 삭제 처리 http://localhost:9092/movie/delete.do
     * 
     * @return
     */
    @RequestMapping(value = "/review/delete.do", method = RequestMethod.POST)
    public ModelAndView delete(ReviewVO reviewVO) {
      ModelAndView mav = new ModelAndView();

      // -------------------------------------------------------------------
      // 파일 삭제 시작
      // -------------------------------------------------------------------
      // 삭제할 파일 정보를 읽어옴.
      ReviewVO reviewVO_read = reviewProc.read(reviewVO.getReviewno());
          
      String file1saved = reviewVO_read.getFile1saved();
      String thumb1 = reviewVO_read.getThumb1();
      
      String uploadDir = Review.getUploadDir();
      Tool.deleteFile(uploadDir, file1saved);  // 실제 저장된 파일삭제
      Tool.deleteFile(uploadDir, thumb1);     // povie 이미지 삭제
      // -------------------------------------------------------------------
      // 파일 삭제 종료
      // -------------------------------------------------------------------
      
      this.niceProc.delete_by_reviewno(reviewVO.getReviewno());
      this.replyProc.delete_by_reviewno(reviewVO.getReviewno());
      this.reviewProc.delete(reviewVO.getReviewno()); // DBMS 삭제
          
      // -------------------------------------------------------------------------------------
      // 마지막 페이지의 마지막 레코드 삭제시의 페이지 번호 -1 처리
      // -------------------------------------------------------------------------------------    
      // 마지막 페이지의 마지막 10번째 레코드를 삭제후
      // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면
      // 페이지수를 4 -> 3으로 감소 시켜야함, 마지막 페이지의 마지막 레코드 삭제시 나머지는 0 발생
      int now_page = reviewVO.getNow_page();
      
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("word", reviewVO.getWord());
      
      if (reviewProc.search_count(hashMap) % Review.RECORD_PER_PAGE == 0) {
        now_page = now_page - 1; // 삭제시 DBMS는 바로 적용되나 크롬은 새로고침등의 필요로 단계가 작동 해야함.
        if (now_page < 1) {
          now_page = 1; // 시작 페이지
        }
      }
      // -------------------------------------------------------------------------------------

      mav.addObject("now_page", now_page);
      mav.setViewName("redirect:/review/list_paging.do"); 
      
      return mav;
    }   
  
//    // http://localhost:9092/movie/delete_by_genreno.do?genreno=1
//    // 파일 삭제 -> 레코드 삭제
//    @RequestMapping(value = "/movie/delete_by_genreno.do", method = RequestMethod.GET)
//    public String delete_by_genreno(int genreno) {
//      ArrayList<MovieVO> list = this.movieProc.list_by_genreno(genreno);
//      
//      for(MovieVO movieVO : list) {
//        // -------------------------------------------------------------------
//        // 파일 삭제 시작
//        // -------------------------------------------------------------------
//        String file1saved = movieVO.getFile1saved();
//        String thumb1 = movieVO.getThumb1();
//        
//        String uploadDir = Movie.getUploadDir();
//        Tool.deleteFile(uploadDir, file1saved);  // 실제 저장된 파일삭제
//        Tool.deleteFile(uploadDir, thumb1);     // povie 이미지 삭제
//        // -------------------------------------------------------------------
//        // 파일 삭제 종료
//        // -------------------------------------------------------------------
//      }
//
//      int cnt = this.movieProc.delete_by_genreno(genreno);
//      System.out.println("-> count: " + cnt);
//      
//      return "";
//    
//    }
//    
}