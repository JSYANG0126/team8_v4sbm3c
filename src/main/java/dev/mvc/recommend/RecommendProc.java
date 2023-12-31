package dev.mvc.recommend;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.movie.MovieVO;


@Component("dev.mvc.recommend.RecommendProc")
public class RecommendProc implements RecommendProcInter {
  @Autowired // GenreDAOInter interface 구현한 객체를 만들어 자동으로 할당해라.
  private RecommendDAOInter recommendDAO;
  
  @Override
  public int create(RecommendVO recommendVO) {
    int cnt = this.create(recommendVO);
    return cnt;
  }

  @Override
  public ArrayList<RecommendVO> list_all() {
    ArrayList<RecommendVO> list = this.recommendDAO.list_all();
    return list;
  }

  @Override
  public ArrayList<RecommendVO> list_recom(MovieVO movieVO) {
    ArrayList<RecommendVO> list = this.recommendDAO.list_recom(movieVO);
    return list;
  }
  
  @Override
  public ArrayList<MovieVO> recom_cnt() {
    ArrayList<MovieVO> list = this.recommendDAO.recom_cnt();
    return list;
  }

  
  @Override
  public RecommendVO read(int recommendno) {
    RecommendVO recommendVO = this.recommendDAO.read(recommendno);
    return recommendVO;
  }

  @Override
  public int delete(int recommendno) {
    int cnt = this.recommendDAO.delete(recommendno);
    return cnt;
  }

  @Override
  public ArrayList<MovieVO> recom_good(int genreno) {
    ArrayList<MovieVO> list = this.recommendDAO.recom_good(genreno);
    return list;
  }

  @Override
  public RecommendVO read_by_memno(int memno) {
    RecommendVO recommendVO = this.recommendDAO.read_by_memno(memno);
    return recommendVO;
  }









}
