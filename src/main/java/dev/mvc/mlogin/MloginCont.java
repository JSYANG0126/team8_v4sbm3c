package dev.mvc.mlogin;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.genre.GenreVO;
import dev.mvc.mem.MemProcInter;

@Controller
public class MloginCont {
	@Autowired
	  @Qualifier("dev.mvc.mlogin.MloginProc")
	  private MloginProcInter mloginProc;
	
	@Autowired
	  @Qualifier("dev.mvc.mem.MemProc")
	  private MemProcInter memProc;
	
	public MloginCont() {
		System.out.println("-> MloginCont created");
	}
	
	//http://localhost:9093/list_mlogin.do
	/**
	 * 로그인 내역 조회 목록
	 */
	@RequestMapping(value="/mlogin/list_mlogin.do", method=RequestMethod.GET )
	  public ModelAndView list_mlogin(HttpSession session) {
	    ModelAndView mav = new ModelAndView();
	    int memno = 0;
	    if(this.memProc.isMem(session) == true) {
	    	mav.setViewName("/mlogin/list_mlogin"); // /WEB-INF/views/mlogin/list_mlogin.jsp
	    	
	    	memno = (int)session.getAttribute("memno");
	    	
	    	ArrayList<MloginVO> list = this.mloginProc.list_mlogin(memno);
	    	mav.addObject("list", list);
	    	
	    } else {
	    	mav.setViewName("mem/login_need");
	    }
	   
	    return mav; // forward
	  }

}
