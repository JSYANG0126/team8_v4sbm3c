package dev.mvc.genre;

import java.util.ArrayList;

public interface GenreProcInter {
	  /**
	   * 등록, 추상 메소드 -> Spring Boot이 구현함.
	   * @param genreVO 객체
	   * @return 등록된 레코드 갯수
	   */
	  public int create(GenreVO genreVO);
	  
	  /**
	   * 전체 목록
	   * @return
	   */
	  public ArrayList<GenreVO> list_all();
	  
	  /**
	   * cnt 반영된 전체 목록
	   * @return
	   */
	  public ArrayList<GenreVO> list_all_cnt();
	  
	  /**
	   * 조회
	   * @param genreno
	   * @return
	   */
	  public GenreVO read(int genreno);
	  
	  /**
	   * 수정   
	   * @param genreVO
	   * @return 수정된 레코드 갯수
	   */
	  public int update(GenreVO genreVO);

	  /**
	   * 삭제
	   * @param genreno 삭제할 레코드 PK 번호
	   * @return 삭제된 레코드 갯수
	   */
	  public int delete(int genreno);
	  
	  /**
	   * 우선 순위 높임, 10 등 -> 1 등   
	   * @param genreno
	   * @return 수정된 레코드 갯수
	   */
	  public int update_seqno_forward(int genreno);

	  /**
	   * 우선 순위 낮춤, 1 등 -> 10 등   
	   * @param genreno
	   * @return 수정된 레코드 갯수
	   */
	  public int update_seqno_backward(int genreno);
	  
	  /**
	   * 카테고리 공개 설정
	   * @param genreno
	   * @return
	   */
	  public int update_visible_y(int genreno);
	  
	  /**
	   * 카테고리 비공개 설정
	   * @param genreno
	   * @return
	   */
	  public int update_visible_n(int genreno);
	  
	  /**
	   * 비회원/회원 SELECT LIST
	   * @return
	   */
	  public ArrayList<GenreVO> list_all_y();
}
