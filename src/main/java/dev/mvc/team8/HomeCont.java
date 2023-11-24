package dev.mvc.team8;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeCont {
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

   mav.setViewName("/menu/top"); // /WEB-INF/views/menu/top.jsp
   
   return mav;
 }
  
}
