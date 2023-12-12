package dev.mvc.chat;

import java.util.ArrayList;

public interface ChatDAOInter {
  /**
   * 채팅 전체 목록
   * @return
   */
  public ArrayList<ChatVO> list();
  
  /**
   * 채팅 단일 목록
   * @param chatno
   * @return
   */
  public ChatVO read(int chatno);
  
  /**
   * 채팅 삭제
   */
  public int delete(int chatno);

}
