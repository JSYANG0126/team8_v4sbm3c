package dev.mvc.mlogin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;

@Component("dev.mvc.mlogin.MloginProc")
public class MloginProc implements MloginProcInter {
  @Autowired
  private MloginDAOInter mloginDAO;
  
  @Override
  public int create(MloginVO mloginVO) {
  	int cnt = this.mloginDAO.create(mloginVO);
  	return cnt;
  }

  @Override
  public ArrayList<MloginVO> list_mlogin(int memno) {
	ArrayList<MloginVO> list = this.mloginDAO.list_mlogin(memno);
	return list;
  }
  
  

}