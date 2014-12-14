<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% 
	String m_id=(String)session.getAttribute("m_id");
%>
<html>
<head>


</head>
<body>
	<form method="post" action="add2.do" enctype="multipart/form-data">
		글제목 : <input type="text" name="title" size="30"/><br/><br/>
		장르 : <select name="categories">
			  <OPTION value="movie">영화</OPTION>
			  <OPTION value="drama">드라마</OPTION>
			  <OPTION value="documentery">다큐</OPTION>
			  <OPTION value="etc">기타</OPTION>
			  </select><br/><br/>
		파일 : <input type="file" name="list_file" /><br/><br/>
		 <input type="hidden" name="id" value="<%=m_id%>">
		 <textarea rows="10" cols="50" name="content"></textarea><br/>
		 <input type="submit" value="글쓰기">
	</form>

</body>
</html>