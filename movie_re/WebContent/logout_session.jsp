<?xml version="1.0" encoding="EUC-KR" ?>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
    session.invalidate();
    response.sendRedirect("join.jsp");
%>
<html>
<head>
<script language="javascript">
 function logout(){
		alert("�α׾ƿ� �Ǿ����ϴ�.");
	}
</script>
</head><body onload="logout()">

</body></html>