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
    // ����̹� �ε� ����
    System.out.println("����̹� �ε� ���� : " + e1.toString());
    return ;
}catch(Exception e2){
    // DB ���� ����
    System.out.println("����̹� �ε� ���� : " + e2.toString());
    return ;
}
if(rs.next()){
	check_Id = rs.getInt("COUNT");
}

if(check_Id != 0){
 out.println( "(("+ id +"))" + " �� �ߺ��� ���̵� �Դϴ�.");
}
else {
 out.println(  "(("+ id +"))" + "  �� ��밡�� �մϴ�.");
}

%>
<center><a href="" onClick="javascript:self.close()">â�ݱ�</a></center>

