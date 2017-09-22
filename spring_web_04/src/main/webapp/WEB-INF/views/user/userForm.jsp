<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>:::User:::</title>
<script type="text/javascript">
  //삭제
  function do_delete(id){
	  console.log("id: " + id);
	  if(id == null)return;
	  var fm = document.frm;
	  fm.action = "do_delete.do";
	  fm.id.value = id;
	  console.log("id2: " + id);
	  fm.submit();
  }
</script>
</head>
<body>
    <h3>User</h3>
    <hr/>
    <form name="frm" method="post" action="do_save.do">
      <input type="hidden"  name="workDiv" value="${userVO.id}"/> 
아이디: <input type="text" name="id"  value="${userVO.id}"/><br/>
이름: <input type="text" name="name" value="${userVO.name}"/><br/>
비번: <input type="text" name="password" value="${userVO.password}"/><br/>
Level: <input type="text" name="level" value="${userVO.levelIntValue}"/><br/>
로그인: <input type="text" name="login" value="${userVO.login}"/><br/>
추천: <input type="text" name="recommend" value="${userVO.recommend}"/><br/>
이메일: <input type="text" name="mail" value="${userVO.mail}"/><br/>
     <input type="submit" value="등록" />
     <input type="button" value="삭제"  
        onclick="do_delete('${userVO.id}')"/>
    </form>
</body>
</html>



