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
		������ : <input type="text" name="title" size="30"/><br/><br/>
		�帣 : <select name="categories">
			  <OPTION value="movie">��ȭ</OPTION>
			  <OPTION value="drama">���</OPTION>
			  <OPTION value="documentery">��ť</OPTION>
			  <OPTION value="etc">��Ÿ</OPTION>
			  </select><br/><br/>
		���� : <input type="file" name="list_file" /><br/><br/>
		 <input type="hidden" name="id" value="<%=m_id%>">
		 <textarea rows="10" cols="50" name="content"></textarea><br/>
		 <input type="submit" value="�۾���">
	</form>

</body>
</html>