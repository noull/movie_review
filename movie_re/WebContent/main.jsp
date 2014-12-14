<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="dao.*, java.util.*"%>
<%
	List<Bang01Vo> lst = (List<Bang01Vo>)request.getAttribute("rl");
	LoginVo log=(LoginVo)request.getAttribute("log");
	String m_id=(String)session.getAttribute("m_id");
	System.out.println(session);
%>
<html>
<link rel="stylesheet" type="text/css" href="main.css">
<head>
<script language="javascript">
var bannum=0;
var pre_page=1;
var ls=null;
var lt=null;
var text_Number = 1;


function lb_pop(tp){
	var pop_up = document.getElementById("lb_pop");
	pop_up.style.visibility = "visible";
	var userid = document.getElementById("userid");
	userid = userid.value;
	var request=new XMLHttpRequest();

	
	request.onreadystatechange=function(){
		if(request.readyState==4){
			if(request.status==200){
				var l=request.responseText;
				onResponse(l);	
				
			}
		}
	};
	request.open("GET","show.do?tpk="+tp+"&userid="+userid,true);
	request.send(null);
}




function onResponse(t){
	var lt=t.split("^^");
	var html="";

	var cate_1 = 0;

	if(lt[0] == 0){
		cate_1 = "영화";
	}else if(lt[0] == 1){
		cate_1 = "드라마";
	}else if(lt[0] == 2){
		cate_1 = "다큐";
	}else if(lt[0] == 3){
		cate_1 = "기타 리뷰";
	}

	document.getElementById("categories").value=cate_1;
	document.getElementById("textpk").value=lt[1];
	document.getElementById("title").value=lt[2];
	document.getElementById("content").value=lt[3];
	document.getElementById("nickpk").value=lt[4];
	document.getElementById("thetime").value=lt[5];
	document.getElementById("readcount").value=lt[6];
	document.getElementById("recomcount").value=lt[7];
	document.getElementById("fileup").value=lt[8];
	document.getElementById("filedown").value=lt[9];
	document.getElementById("nickpk_del").value=lt[4];
	document.getElementById("textpk_del").value=lt[1];
	document.getElementById("NickPK").value=lt[4];
	document.getElementById("recom_textpk").value=lt[1];
	document.getElementById("textpk_re_add").value=lt[1];
	html=lt[10];

	var c = document.getElementById("re_console");
	c.innerHTML = html;
}




function onload(){
	var listview1 = document.getElementById("listview1");
	listview1.style.visibility = "visible";
}




function paging(r){
	var p_paging = document.getElementById("listview"+pre_page);
	p_paging.style.visibility = "hidden";

	var c_paging = document.getElementById("listview"+r);
	c_paging.style.visibility = "visible";
	pre_page=r;
}




function banner_rclick(){
	var recomm = document.getElementById("recomm");
	bannum = (bannum+1)%4;
	recomm.style.backgroundImage ="url(image/ban"+Math.abs(bannum)+".png)"
	setTimeout("banner_rclick()",4000);
}
function banner_lclick(){
	var recomm = document.getElementById("recomm");
	bannum = (bannum-1)%4;
	recomm.style.backgroundImage ="url(image/ban"+Math.abs(bannum)+".png)"
}




function lb_close(){
	var pop_up = document.getElementById("lb_pop");
	pop_up.style.visibility = "hidden";
}
function lb_del(){

	alert();
	document.del_frm.submit();
}
function lb_update(){
	alert();
	document.update_frm.submit();
}




function re_up(re_pk, l ){
	var ff = document.getElementById("re_concon").value;
	var temp=l;
	location.href="re_update.do?repl_pk="+re_pk+"&re_content="+ff;
}
function re_del(rep,texp){
	location.href="re_delete.do?repl_pk="+rep+"&textpk="+texp;
}
function re_add(){
	document.re_frm.submit();
}




function recom_up(){
	document.recom_update.submit();
}




function logout(){
	var con = confirm("접속을 종료하시겠습니까");
	if(con){
		alert("접속이 종료되었습니다.");
		location.href="logout_session.jsp";  
	}else{
	}
}
</script>
</head>
<body onload="onload();banner_rclick()">
	<div id="fullview">
		<div id="id_print"><%=m_id%>님 환영합니다.</div>
		<div id="maintop">
		</div>
			<div id="recomm">
				<div id="ban_r" onclick="banner_rclick()"></div>
				<div id="ban_m"></div>
				<div id="ban_l" onclick="banner_lclick()"></div>
			</div>	
			
			

			
			<div id="ssearch">
				<form method="post" action="search2.do">
					<input type="radio" name="search_con" value="search_title" checked="true"/>글 제목
					<input type="radio" name="search_con" value="search_content"/>글 내용
					<input type="radio" name="search_con" value="search_nick_pk"/>글쓴이
					<input type="text" name="search_text" size="40"/>
					<input type="submit" value="검색"/>
	
				</form>
			</div>
			
			
			
			
			

		<div id="lb_pop">	
			<form method="post" name="update_frm" action="update2.do">
				<input type = "hidden" name= "nickpk" id="nickpk"/>	
				<input type = "hidden" name= "userid" id="userid" value="<%=m_id%>"/>
				<input type = "hidden" name= "fileup" id="fileup"/>	
				<input type = "hidden" name= "filedown" id="filedown"/>		
				<input type = "text" name = "categories" id = "categories" />
				<input type = "text" name = "textpk" id = "textpk"/><!-- 이부분은 글번호 -->
				<input type = "text" name = "title" id = "title"/>	
				<textarea  name = "content" id= "content"></textarea>		
				<input type = "text" name = "thetime" id = "thetime"/>		
				<input type = "text" name = "readcount" id = "readcount"/>
				<input type = "text" name = "recomcount" id = "recomcount"/>				
			</form>
			
	
			<form method="post" name="recom_update" action="recomContent.do">
				<input type = "hidden" name= "NickPK" id="NickPK"/>	
				<input type = "hidden" name= "recom_id" id="recom_id" value="<%=m_id%>"/>
				<input type = "hidden" name = "recom_textpk" id = "recom_textpk"/>
			</form>
			<input type="button" value="추천" id="recom" onclick="recom_up()"/>

			<form method="post" name="del_frm" action="del2.do">
				<input type = "hidden" name= "nickpk_del" id="nickpk_del"/>	
				<input type = "hidden" name= "userid_del" id="userid_del" value="<%=m_id%>"/>
				<input type = "hidden" name = "textpk_del" id = "textpk_del"/>
			</form>
		
			<input type = "button" value = "수정" id = "lb_update" onclick="lb_update()"/>
			<input type = "button" value = "삭제" id = "lb_del" onclick="lb_del()"/>	
			<input type = "button" value = "닫기" id = "lb_close" onclick="lb_close()"/>
		
			<form method="post" action="reple_add.do" name="re_frm">
				<input type="hidden" name="re_m_id" value="<%=m_id %>">
				<input type="text" id="re_add" name="re_content"/>
				<input type = "hidden" name = "textpk_re_add" id = "re_add"/>
			</form>

			<input type="button" onclick="re_add()" value="댓글" id="re_abu"/> 
			<div id="re_console"></div>
		
		
		</div>
			<%
			Iterator<Bang01Vo> it = lst.iterator();
			int k = 0;
			int i = 0;
			int count =0;
			for( i = 0; it.hasNext(); i++)
			{
				if(i%8==0)
				{
					k=k+1;%>
				<div class ="listview" id = "listview<%=k%>"><%
				}
				Bang01Vo vo = it.next();%>
				<div class = "list<%=i%8%>" onclick = "lb_pop(<%=vo.getTextPK()%>)">
				<img src="upload\<%=i%>.jpg" width="235" height="320">
				<%=vo.getTitle()%></div><br/>
				<%if(i%8==7){%></div><%}
				count=count+1;	
			}%>
		</div>
		<div id="paging">
			<%int page_c=((int)count/8)+1; 
			for(int r=1; r<(page_c+1); r++)
				{%><a href = "javascript:paging(<%=r%>)"><%=r%></a>
				<%}%>
		</div>
		<a href="writeForm.jsp" id="lwrite">글쓰기</a>
		<a href="list.do" align ="right" id="listlist">목록보기</a>
		<div id="logout">
			<form method="post" name="frm1" action="join.do">
			<input type="button" value="logout" id="llogo" onClick="logout();"/>
			</form>
		</div>

	
</body>
</html>