package dev.mvc.nice;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.mem.MemProcInter;

@Controller
public class NiceCont {
  @Autowired
  @Qualifier("dev.mvc.nice.NiceProc")
  private NiceProcInter niceProc;
  
  @Autowired
  @Qualifier("dev.mvc.mem.MemProc")
  private MemProcInter memProc;
  public NiceCont() {
    System.out.println("--> NiceCont");
  }
  
  /**
   * 좋아요 등록, Ajax 처리
   * @return
   */
  @RequestMapping(value="/nice/create.do", method=RequestMethod.GET )
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nice/create");
    
    return mav;
  }
  


  @ResponseBody
  @RequestMapping(value="/nice/create.do", method = RequestMethod.POST)
  public Map<String, Object> create(NiceVO niceVO) {   
   Map<String, Object> resultMap = new HashMap<>();
  
   try {
     int mem_cnt = this.niceProc.nice_by_mem_cnt(niceVO);
     if (mem_cnt == 0) {
       int cnt = niceProc.create(niceVO);
       resultMap.put("cnt", cnt);
       int nice_cnt = this.niceProc.nice_cnt(niceVO.getReviewno()); 
       resultMap.put("nice_cnt", nice_cnt);
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
  @RequestMapping(value="/nice/nice_by_mem_cnt.do", method=RequestMethod.GET,
          produces = "text/plain;charset=UTF-8")
  public String nice_by_mem_cnt(NiceVO niceVO) {
    int cnt = this.niceProc.nice_by_mem_cnt(niceVO);
    int nice_cnt = this.niceProc.nice_cnt(niceVO.getReviewno()); 
    // JSON 객체 생성
    JSONObject json = new JSONObject();
    json.put("cnt", cnt);
    json.put("nice_cnt", nice_cnt);
    System.out.println(json);
    // JSON 문자열 반환
    return json.toString();
}
  
  /**
   * 좋아요 삭제, Ajax 처리
   * @return
   */
  @RequestMapping(value="/nice/delete.do", method=RequestMethod.GET )
  public ModelAndView delete() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nice/delete");
    
    return mav;
  }
  


  @ResponseBody
  @RequestMapping(value="/nice/delete.do", method = RequestMethod.POST)
  public Map<String, Object> delete(NiceVO niceVO) {   
   Map<String, Object> resultMap = new HashMap<>();
  
   try {
     // 좋아요 등록 로직을 호출하고 좋아요 수를 가져옴
     int cnt = niceProc.delete(niceVO);
     int nice_cnt = this.niceProc.nice_cnt(niceVO.getReviewno());
     resultMap.put("cnt", cnt);     
     resultMap.put("nice_cnt", nice_cnt);
   } catch (Exception e) {
     resultMap.put("error", "좋아요 등록 실패");
   }
  
   return resultMap;
  }
}
