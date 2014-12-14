<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<link rel="stylesheet" type="text/css" href="join.css">
<head>
<script language="javascript">

function login_click()
{	
	var id=document.getElementById("login_id");
	var pw=document.getElementById("login_pw");
	
	if(id.value==''){
		alert("ID를 입력하세요");
	}else{
		if(pw.value==''){
			alert("password를 입력하세요");
		}else{
			var request=new XMLHttpRequest();

			
			request.onreadystatechange=function(){
				if(request.readyState==4){
					if(request.status==200){
						var l=request.responseText;
						onResponse(l);	
						
					}
				}
			};
			
			request.open("GET", "checkId.do?id="+id+"&pw="+pw, true);
			request.send(null);
			
			
			document.frm1.submit();
		}
	}
}

function join_click()
{
	var frame_login = document.getElementById("login");
	var frame_join = document.getElementById("joinpop");
	frame_login.style.visibility = "hidden";
	frame_join.style.visibility = "visible";
}

function mem_join(){
	
	var nickPK = document.join_us.nickPK.value;
	var password = document.join_us.password.value;
	var e_mail = document.join_us.e_mail.value;
	var e_mail_check = 0;
	var check = true;
	var where_check = 0;

	for (var i=0  ; i < e_mail.length; i++){
		if("@"== e_mail.charAt(i)){
			e_mail_check++;
		}
		if(e_mail_check == 1 && e_mail.charAt(i)=="."){
			e_mail_check++;
		}
		
	}

	if( e_mail_check != 2){
		where_check = 4;
		check = false;
	}
	if (4 > password.length || 18 < password.length){
		check = false;
		where_check = 3;
	}	
	for (var i = 0; i < nickPK.length; i++){
		if ('0' > nickPK.charAt(i) && '9' < nickPK.charAt(i) && 'A' > nickPK.charAt(i) && 'z' < nickPK.charAt(i)){
			where_check = 2;
			check = false;
		}
	}	
	if (4 > nickPK.length || 18 < nickPK.length){
		where_check = 1;
		check =  false;
	}
		
	if(check == true){
		document.join_us.submit();
	}else if(where_check == 1){
		alert("아이디는 4글자 이상 18자 이하로 입력해주세요.");	
	}else if(where_check == 2){
		alert("아이디는 영문과 숫자만 가능합니다.");
	}else if(where_check == 3){
		alert("암호는 4글자 이상 18자 이하로 입력해주세요.");
	}else if(where_check == 4){
		alert("E Mail 형식이 맞지 않습니다.");
		e_mail_check= 0;
	}	
}


function check_id(n){

	var id=n.nickPK.value;
	if(4>id.length || 18<id.length){
		alert("아이디는 4글자 이상 18자 이하로 입력해주세요.");
		return;
	}
	w=window.open("Join_idCheck.jsp?id="+id,"","width=200,height=150,resizable=no");
}



</script>
</head>
<body id="body">
	<div id="full">
		<div id="joinpop">
			<form method = "post" name="join_us" action = "login_add.do">
				<input type = "text" name = "nickPK" id = "nickPK" size = "20"/><br/>
				<input onclick="check_id(this.form);" id="jung" type="button" value="중복확인" size="20" /><br />
				<input type = "password" name = "password" id = "password" /><br/>
				<input type = "text" name = "e_mail" id = "e_mail" size = "20"/><br/>	
				<div id="join_hobby">
				<input type = "checkbox" name = "favorite_Cate1" value = "movie" size="20"/>영&nbsp&nbsp&nbsp화		
				<input type = "checkbox" name = "favorite_Cate2" value = "drama" size="20"/>드라마<br/>		
				<input type = "checkbox" name = "favorite_Cate3" value = "daq" size="20"/>다&nbsp&nbsp&nbsp큐		
				<input type = "checkbox" name = "favorite_Cate4" value = "etc" size="20"/>기&nbsp&nbsp&nbsp타
				</div>
				<input type="button" onclick="mem_join()" id="jung_go" value="가입" size="20" /><br/>			
			</form>
			
			
			
			</div>
			
		</div>
		
		
		
		<div id="login">
			<form method = "post" name = "frm1" action = "login.do">
				<input type = "text" id = "login_id" name="login_id"/>
				<input type = "password" id = "login_pw" name="login_pw"/>
				<input type = "button" id = "login_sb"  value = "로그인" onclick="login_click()" />
				<input type = "button" id = "login_join"  value = "회원가입" onclick="join_click()"/>
			</form>
			
		</div>
	
</body>
</html>