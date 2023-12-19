package dev.mvc.mlogin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface MloginDAOInter {

	/**
	 * 로그인 내역 등록 
	 * @param MloginVO
	 * @return
	 */
	public int create(MloginVO mloginVO);
	
	/**
	* 특정 회원번호의 로그인 내역 리스트
	* @return
	*/
	public ArrayList<MloginVO> list_mlogin(int memno);
	  
}
 