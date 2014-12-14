<%@ page language="java" import="java.sql.*" contentType="text/html;charset=EUC-KR" %>
<%request.setCharacterEncoding("euc-kr");%>
<% String id = request.getParameter("id");%>

<%
Connection conn=null;
PreparedStatement pstmt=null;
ResultSet rs=null;
int check_Id=0;



try{
	Class.forName("oracle.jdbc.driver.OracleDriver");
 conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ora15","ora15");

 String sql="select COUNT(*) AS COUNT from user_list where nickpk ='"+id+"'";
 pstmt=conn.prepareStatement(sql);
 rs=pstmt.executeQuery();

}catch(ClassNotFoundException e1) {
    // 드라이버 로딩 실패
    System.out.println("드라이버 로딩 에러 : " + e1.toString());
    return ;
}catch(Exception e2){
    // DB 접속 실패
    System.out.println("드라이버 로딩 에러 : " + e2.toString());
    return ;
}
if(rs.next()){
	check_Id = rs.getInt("COUNT");
}

if(check_Id != 0){
 out.println( "(("+ id +"))" + " 는 중복된 아이디 입니다.");
}
else {
 out.println(  "(("+ id +"))" + "  는 사용가능 합니다.");
}

%>
<center><a href="" onClick="javascript:self.close()">창닫기</a></center>

