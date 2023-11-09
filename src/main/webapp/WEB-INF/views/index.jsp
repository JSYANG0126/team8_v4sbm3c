<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=5.0, width=device-width" /> 
<title>http://localhost:9093/index.jsp</title>
</head>
<body>
<c:import url="/menu/top.do" />
  <div style="margin: 50px;">
      <H1>깃 테스트</H1>
      <ol>
          <li>팀장: 컴돌이</li>
          <li>팀원1 : 김치찌개</li>
          <li>팀원2 :  짱아</li>
      </ol>
  </div> 
<jsp:include page="./menu/bottom.jsp" flush='false' />   
</body>
</html>