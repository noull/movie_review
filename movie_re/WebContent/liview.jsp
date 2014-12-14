<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="dao.*, java.util.*"%>

	<% List<Bang01Vo> lst = (List<Bang01Vo>)request.getAttribute("sr");
	List<Reple_List> re_list=(List<Reple_List>)request.getAttribute("re_list");
	
	int read_textPk = 0;
	String nick_Id = null;
	String read_Id = (String)request.getAttribute("userid");

	String l = null;
	String html="";
	Iterator<Bang01Vo> it = lst.iterator();
			for( int i = 0; it.hasNext(); i++)
			{
				Bang01Vo vo = it.next();	
				l = vo.getCategories()+"^^"
				+ vo.getTextPK()+"^^"
				+vo.getTitle()+"^^"
				+vo.getContent()+"^^"
				+vo.getNickPK().trim()+"^^"
				+vo.getTheTime()+"^^"
				+vo.getReadCount()+"^^"
				+vo.getRecomCount()+"^^"
				+vo.getFileUp()+"^^"
				+vo.getFileDown();
				read_textPk = vo.getTextPK();
				nick_Id = vo.getNickPK();
			}
			

			Iterator<Reple_List> r_it = re_list.iterator();	
			for( int j = 0 ; r_it.hasNext();j++)
			{
				
				Reple_List rvo = r_it.next();
				html=html+"<tr>"+
								"<td><input type=\"text\" class=\"re_nick\" name=\"re_m_id\" value=\""+rvo.getNickPK()+"\"/></td>"+
								"<td><input type=\"text\" class=\"re_context\" id=\"re_concon\" value=\""+rvo.getRepl_content()+"\"/></td>"+
								"<td><input type=\"button\" class=\"re_update\" value=\"update\" onclick=\"re_up("+rvo.getRepl_PK()+",'"+rvo.getRepl_content()+"')\"/></td>"+
								"<td><input type=\"button\" class=\"re_del\" value=\"del\" onclick=\"re_del("+rvo.getRepl_PK()+","+rvo.getTextPK()+")\"/></td>"+
							"</tr>";

			}
			
	Bang01Dao bangDao = new Bang01Dao_OracleImpl();
	boolean read_TF = false;
	System.out.println(read_Id+ read_textPk+ nick_Id);
	bangDao.readContent(read_Id, read_textPk, nick_Id);
	System.out.println(html);


%><%=l%>^^<table boarder="1"><%=html%></table>