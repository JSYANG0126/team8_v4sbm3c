<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>

<style>
  .top_menu_link:link{  /* 방문전 상태 */
    text-decoration: none; /* 밑줄 삭제 */
    color: #FFFFFF;
    font-weight: bold;
  }

  .top_menu_link:visited{  /* 방문후 상태 */
    text-decoration: none; /* 밑줄 삭제 */
    color: #FFFFFF;
    font-weight: bold;
  }

  .top_menu_link:hover{  /* A 태그에 마우스가 올라간 상태 */
    text-decoration: none; /* 밑줄 출력 */
    color: #FFFFFF;
    font-size: 1.05em;
  }
  
	.table-hover tbody tr:hover td, .table-hover tbody tr:hover th {
	  background-color: #cdcdcd;
	}
</style> 
<script type="text/javascript">
  function chatbot() {
    // 챗봇을 개발한 사람의 AWS IP
    // "http://localhost:5000/chatbot/?memberno=${sessionScope.memberno }"  
    var url = 'http://43.203.63.165:5000/chatbot?memno=${sessionScope.memno }';
    var win = window.open(url, '챗봇', 'width=1300px, height=850px');
       
    var x = (screen.width - 1300) / 2;
    var y = (screen.height - 850) / 2;
       
    win.moveTo(x, y); // 화면 중앙으로 이동
  }
  function recommend() {
    // 추천을 개발한 사람의 AWS IP
    // "http://localhost:8000/ais/recommend_form/?memberno=${sessionScope.memberno }"  
    var url = 'http://43.203.63.165:8000/ais/recommend_form/?memno=${sessionScope.memno }';
    var win = window.open(url, '공지 사항', 'width=1300px, height=850px');
       
    var x = (screen.width - 1300) / 2;
    var y = (screen.height - 850) / 2;
       
    win.moveTo(x, y); // 화면 중앙으로 이동
  }

  function issue() {
	    // 추천을 개발한 사람의 AWS IP
	    // "http://localhost:8000/ais/recommend_form/?memberno=${sessionScope.memberno }"  
	    var url = 'http://43.203.63.165:3000';
	    var win = window.open(url, '공지사항', 'width=1300px, height=850px');
	       
	    var x = (screen.width - 1300) / 2;
	    var y = (screen.height - 850) / 2;
	       
	    win.moveTo(x, y); // 화면 중앙으로 이동
	}
</script>


<div class='container_main'>
  <div class='top_img'>
    <div class="top_menu_label">영화리뷰 블로그</div>      
  </div> <!-- <div class='top_img'></div> 종료 -->
  

  <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #323232;">
      <a class="navbar-brand" href="/"><img src='/css/images/home.png' title="시작페이지" style='display: block; padding-left: 5px;'></a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle Navigation">
        <span class="navbar-toggler-icon"></span>
      </button>    

      <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav mr-auto">
            
            <%-- 게시판 목록 서브메뉴 --%>
            <li class="nav-item dropdown">
              <a class="nav-link top_menu_link dropdown-toggle" data-bs-toggle="dropdown" href="#">장르 목록</a>
              <div class="dropdown-menu">
                <c:forEach var="genreVO" items="${list_top }">
                    <c:set var="genreno" value="${genreVO.genreno }" />
                    <c:set var="name" value="${genreVO.name }" />
                    <a class="dropdown-item" href="/movie/list_by_genreno.do?genreno=${genreVO.genreno }&now_page=1">${genreVO.name }</a> 
                </c:forEach>
              </div>
            </li>
            
            <li class="nav-item"> <%-- 서브 메뉴가 없는 독립메뉴 --%>
              <a class="nav-link top_menu_link" href="/reservation/list_all.do">예매 페이지</a>
            </li>
            
            <li class="nav-item"> <%-- 서브 메뉴가 없는 독립메뉴 --%>
              <a class="nav-link top_menu_link" href="/qna/list_all.do">질문/답변</a>
            </li>


            <li class="nav-item"> <%-- 서브 메뉴가 없는 독립메뉴 --%>
              <a class="nav-link top_menu_link" href="/review/list_paging.do">영화 리뷰</a>
            </li>

            <li class="nav-item"> <%-- 서브 메뉴가 없는 독립메뉴 --%>
              <a class="nav-link top_menu_link" href="/theater/list_by_search_paging.do?now_page=1">영화관</a>
            </li>
            
            <li class="nav-item"> <%-- 서브 메뉴가 없는 독립메뉴 --%>
              <a class="nav-link top_menu_link" href="javascript: chatbot();">챗봇</a>
            </li>
            
             <li class="nav-item"> <%-- 서브 메뉴가 없는 독립메뉴 --%>
              <a class="nav-link top_menu_link" href="javascript: issue();">공지사항</a>
            </li>
            

            <li class="nav-item dropdown"> <%-- 회원 서브 메뉴 --%>
              <a class="nav-link top_menu_link dropdown-toggle" data-bs-toggle="dropdown" href="#">회원</a>
              <div class="dropdown-menu">
                <c:choose>
                  <c:when test="${sessionScope.id == null }">
                    <a class="dropdown-item" href="/mem/create.do">회원 가입</a>
                    <a class="dropdown-item" href="/mem/id_find.do">아이디 찾기</a>
                    <a class="dropdown-item" href="/mem/passwd_find.do">비밀번호 찾기</a>
                  </c:when>
                  <c:otherwise>
                    <a class="dropdown-item" href="javascript: recommend();">관심분야 등록하고 추천받기</a>
                    <a class="dropdown-item" href="/chat/list.do">챗봇 채팅 내역</a>
                    <a class="dropdown-item" href="/mem/read.do">가입 정보</a>
                    <a class="dropdown-item" href="/mem/passwd_update.do">비밀번호 변경</a>
                    <a class="dropdown-item" href="/mem/read.do">회원 정보 수정</a>
                    <a class="dropdown-item" href="/mlogin/list_mlogin.do">로그인 내역</a>
                    <a class="dropdown-item" href="/mem/mem_unregister.do?memno=${sessionScope.memno }">회원 탈퇴</a>
                  </c:otherwise>
                </c:choose>
              </div>
            </li>
          
            <c:choose>
              <c:when test="${sessionScope.manager_id == null }">
                <li class="nav-item">
                  <a class="nav-link top_menu_link" href="/manager/login.do">관리자 로그인</a>
                </li>
              </c:when>
              <c:otherwise>
                <li class="nav-item dropdown"> <%-- 관리자 서브 메뉴 --%>
                  <a class="nav-link top_menu_link dropdown-toggle" data-bs-toggle="dropdown" href="#">관리자</a>
                  <div class="dropdown-menu">               
                    <a class="dropdown-item" href='/genre/list_all.do'>장르 전체 목록</a>
                    <a class="dropdown-item" href="/movie/list_all.do">전체 글 목록</a>
                    <a class="dropdown-item" href='/mem/list.do'>회원 목록</a>
                    <a class="dropdown-item" href='/manager/logout.do'>관리자 ${sessionScope.manager_id } 로그아웃</a>
                  </div>
                </li>
              </c:otherwise>
            </c:choose>
            
            <li class="nav-item"> <%-- 서브 메뉴가 없는 독립메뉴 --%>
              <c:choose>
                  <c:when test="${sessionScope.id == null}">
                      <a class="nav-link top_menu_link" href="/mem/login.do">로그인</a>
                  </c:when>
                  <c:otherwise>
                      <a class="nav-link top_menu_link" href='/mem/logout.do'>${sessionScope.id } 로그아웃</a>
                  </c:otherwise>
              </c:choose>
            </li>     
          </ul>
      </div>    
  </nav>
    
  <div class='content_body'> <!--  내용 시작 -->