package dev.mvc.mem;

import java.util.ArrayList;
import java.util.HashMap;  // class
import java.util.List;
// interface, 인터페이스를 사용하는 이유는 다른 형태의 구현 클래스로 변경시 소스 변경이 거의 발생 안됨
// 예) 2022년 세금 계산 방법 구현 class, 2023년 세금 계산 방법 구현 class
// 인터페이스 = 구현 클래스
// Payend pay = new Payend2022();
// Payend pay = new Payend2023();
// Payend pay = new Payend2024();
// pay.calc();
import java.util.Map;         

public interface MemDAOInter {
  /**
   * 중복 아이디 검사
   * @param id
   * @return 중복 아이디 갯수
   */
  public int checkID(String id);
  
  /**
   * 회원 가입
   * @param memVO
   * @return
   */
  public int create(MemVO memVO);
  
  /**
   * 회원 전체 목록
   * @return
   */
  public ArrayList<MemVO> list();
  
  /**
   * memno로 회원 정보 조회
   * @param memberno
   * @return
   */
  public MemVO read(int memno);
  
  /**
   * id로 회원 정보 조회
   * @param id
   * @return
   */
  public MemVO readById(String id);
  
  /**
   * 회원 이름으로 회원 정보 조회
   * @param mname
   * @return
   */
  public MemVO readByMname(String mname);
  
  /**
   * 수정 처리
   * @param memVO
   * @return
   */
  public int update(MemVO memVO);
  
  /**
   * 회원 삭제 처리
   * @param memno
   * @return
   */
  public int delete(int memno);
  
  /**
   * 로그인 처리
   */
  public int login(HashMap<String, Object> map);
  
  /**
   * 현재 패스워드 검사
   * @param map
   * @return 0: 일치하지 않음, 1: 일치함
   */
  public int passwd_check(HashMap<String, Object> map);
  
  /**
   * 패스워드 변경
   * @param map
   * @return 변경된 패스워드 갯수
   */
  public int passwd_update(HashMap<String, Object> map);
  
  /**
   * 아이디로 회원번호 조회
   * @param id
   * @result 회원번호
   */
  public int readByMemno(String id);
  
  /**
   * 회원 정지검사
   * @param memVO
   * @return cnt
   */
  public int sp_check(MemVO memVO);
  
  /**
   * 회원 탈퇴검사
   * @param memVO
   * @return cnt
   */
  public int wd_check(MemVO memVO);
  
  /**
   * 회원 탈퇴
   * @param memno
   * @return cnt
   */
  public int mem_unregister(int memno);
  
}