package dev.mvc.team8;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.genre.GenreVO;
import dev.mvc.genre.GenreProcInter;


@Controller
public class HomeCont {
  @Autowired //GenreProcInter interface 구현한 객체를 만들어 자동으로 할당
  @Qualifier("dev.mvc.genre.GenreProc")
  private GenreProcInter genreProc;
  
  public HomeCont() {
    System.out.println("---> HomeCont created");
  }
  
  //http://localhost:9093/
  @RequestMapping(value = {"", "/","/index.do"}, method=RequestMethod.GET)
  public ModelAndView home() {
    System.out.println("---> home()");
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/index"); //WEB-INF/views/index.jsp
    // spring.mvc.view.prefix=/WEB-INF/views/
    // spring.mvc.view.suffix=.jsp
    
    return mav;
  }
  
//http://localhost:9093/menu/top.do
 @RequestMapping(value= {"/menu/top.do"}, method=RequestMethod.GET)
 public ModelAndView top() {
   ModelAndView mav = new ModelAndView();

   ArrayList<GenreVO> list_top = this.genreProc.list_all_y();
   mav.addObject("list_top", list_top);
   
   mav.setViewName("/menu/top"); // /WEB-INF/views/menu/top.jsp
   
   return mav;
 }
}
