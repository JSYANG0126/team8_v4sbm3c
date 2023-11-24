package dev.mvc.genre;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//Controller가 사용하는 이름
@Component("dev.mvc.genre.GenreProc")
public class GenreProc implements GenreProcInter {
	@Autowired // GenreDAOInter interface 구현한 객체를 만들어 자동으로 할당해라.
	private GenreDAOInter genreDAO;
	
	@Override
	  public int create(GenreVO genreVO) {
	    int cnt = this.genreDAO.create(genreVO);

	    return cnt;
	  }
	
	@Override
	  public ArrayList<GenreVO> list_all() {
	    ArrayList<GenreVO> list = this.genreDAO.list_all();
	    
	    return list;
	  }

	  @Override
	  public GenreVO read(int genreno) {
	    GenreVO genreVO  = this.genreDAO.read(genreno);
	    
	    return genreVO;
	  }

	  @Override
	  public int update(GenreVO genreVO) {
	    int cnt = this.genreDAO.update(genreVO);
	    
	    return cnt;
	  }

	  @Override
	  public int delete(int genreno) {
	    int cnt = this.genreDAO.delete(genreno);
	    
	    return cnt;
	  }

	  @Override
	  public int update_seqno_forward(int genreno) {
	    int cnt = this.genreDAO.update_seqno_forward(genreno);
	    return cnt;
	  }

	  @Override
	  public int update_seqno_backward(int genreno) {
	    int cnt = this.genreDAO.update_seqno_backward(genreno);
	    return cnt;
	  }

	  @Override
	  public int update_visible_y(int genreno) {
	    int cnt = this.genreDAO.update_visible_y(genreno);
	    return cnt;
	  }

	  @Override
	  public int update_visible_n(int genreno) {
	    int cnt = this.genreDAO.update_visible_n(genreno);
	    return cnt;
	  }

	  @Override
	  public ArrayList<GenreVO> list_all_y() {
	    ArrayList<GenreVO> list = this.genreDAO.list_all_y();
	    return list;
	  }
}
