<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h3>VIEW->Controller->Service->DAO</h3>
    <hr/>
    <!-- 
    private String id     ;
    private String name   ;
    private String email  ;
    private String passwd ;
     -->
    <form method="POST" action="doSelectOne.do">
        아이디 : <input type="text" name="id" value="${memberVO.id}"/><br/>
        이름    : <input type="text" name="name"  value="${memberVO.name}"/><br/>
        이메일 : <input type="text" name="email"  value="${memberVO.email}"/><br/>
        비밀번호 :<input type="text" name="passwd"  value="${memberVO.passwd}"/><br/>
        <input type="submit" value="전송" />
    </form>
</body>
</html>