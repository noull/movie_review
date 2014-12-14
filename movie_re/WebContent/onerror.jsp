<html><body>
<% 
	Exception err = (Exception)request.getAttribute("err");
	
	StackTraceElement[] ste = err.getStackTrace();
	for( int i = 0 ; i < ste.length ; i++ ){
		%><%=ste[i].toString()%><br/><%
	}
	%>
</body></html>