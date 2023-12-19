package dev.mvc.good;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.mem.MemProcInter;

@Controller
public class GoodCont {
  @Autowired
  @Qualifier("dev.mvc.good.GoodProc")
  private GoodProcInter goodProc;
  
  @Autowired
  @Qualifier("dev.mvc.mem.MemProc")
  private MemProcInter memProc;
  public GoodCont() {
    System.out.println("--> GoodCont");
  }
  
  /**
   * 좋아요 등록, Ajax 처리
   * @return
   */
  @RequestMapping(value="/good/create.do", method=RequestMethod.GET )
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/good/create");
    
    return mav;
  }
  


  @ResponseBody
  @RequestMapping(value="/good/create.do", method = RequestMethod.POST)
  public Map<String, Object> create(GoodVO goodVO) {   
   Map<String, Object> resultMap = new HashMap<>();
  
   try {
     int mem_cnt = this.goodProc.good_by_mem_cnt(goodVO);
     if (mem_cnt == 0) {
       // 좋아요 등록 로직을 호출하고 좋아요 수를 가져옴
       int cnt = goodProc.create(goodVO);
       resultMap.put("cnt", cnt);
       int good_cnt = this.goodProc.good_cnt(goodVO.getMovieno()); 
       resultMap.put("good_cnt", good_cnt);
       resultMap.put("mem_cnt", 1);
       
     } else {
       resultMap.put("cnt", 0);
       resultMap.put("mem_cnt", 0);
     }
   } catch (Exception e) {
     resultMap.put("error", "좋아요 등록 실패");
   }
  
   return resultMap;
  }

  /**
   * 좋아요 여부 확인
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/good/good_by_mem_cnt.do", method=RequestMethod.GET,
          produces = "text/plain;charset=UTF-8")
  public String good_by_mem_cnt(GoodVO goodVO) {
    int cnt = this.goodProc.good_by_mem_cnt(goodVO);

    // JSON 객체 생성
    JSONObject json = new JSONObject();
    json.put("cnt", cnt);
    System.out.println(json);
    // JSON 문자열 반환
    return json.toString();
}
  
  /**
   * 좋아요 삭제, Ajax 처리
   * @return
   */
  @RequestMapping(value="/good/delete.do", method=RequestMethod.GET )
  public ModelAndView delete() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/good/delete");
    
    return mav;
  }
  


  @ResponseBody
  @RequestMapping(value="/good/delete.do", method = RequestMethod.POST)
  public Map<String, Object> delete(GoodVO goodVO) {   
   Map<String, Object> resultMap = new HashMap<>();
  
   try {
     // 좋아요 등록 로직을 호출하고 좋아요 수를 가져옴
     int cnt = goodProc.delete(goodVO);
     int good_cnt = this.goodProc.good_cnt(goodVO.getMovieno());
     resultMap.put("cnt", cnt);     
     resultMap.put("good_cnt", good_cnt);
   } catch (Exception e) {
     resultMap.put("error", "좋아요 등록 실패");
   }
  
   return resultMap;
  }
}
