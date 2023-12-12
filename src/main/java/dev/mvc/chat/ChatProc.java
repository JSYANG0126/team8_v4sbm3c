package dev.mvc.chat;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.chat.ChatProc")
public class ChatProc implements ChatProcInter {
  @Autowired
  private ChatDAOInter chatDAO;

  @Override
  public ArrayList<ChatVO> list() {
    ArrayList<ChatVO> list = this.chatDAO.list();
    return list;
  }
  
  @Override
  public ChatVO read(int chatno) {
    ChatVO chatVO = this.chatDAO.read(chatno);
    return chatVO;
  }

  @Override
  public int delete(int chatno) {
    int cnt = this.chatDAO.delete(chatno);
    return cnt;
  }

  

}
