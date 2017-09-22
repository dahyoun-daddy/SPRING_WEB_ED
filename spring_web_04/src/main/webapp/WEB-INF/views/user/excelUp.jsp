<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  //contextPath
  String contextPath = request.getContextPath();
  contextPath = "http://localhost:8080/"+contextPath;  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 부트스트랩 -->
  
<link href="<%=contextPath%>/resources/css/bootstrap.css" rel="stylesheet">
<link href="<%=contextPath%>/resources/css/bootstrap-theme.min.css" rel="stylesheet">
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script type="text/javascript" src="<%=contextPath%>/resources/js/jquery-3.2.1.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="<%=contextPath%>/resources/js/bootstrap.min.js"></script>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
	   function do_excelUpload(){
		   alert("do_excelUpload");
		   var frm=document.popFrm;
		   frm.submit();
	   }
	    //jquery document
	   $(document).ready(function(){
	       //do_save
	       $("#do_excelUp").on("click",function(event){
	          
	          if(false==confirm("엑셀 업로드 하시겠습니까?"))return;
              event.preventDefault();//연속수행 방지
              
	          var form = $('form')[0];//Form data read
	          var formData = new FormData(form);
	          $.ajax({
	              type:"POST",
	              url:"do_excelUpload.do",
	              dataType:"html",// JSON
	              async: false,
	              cache: false,
	              contentType: false,
	              processData: false, 
	              data:formData,
	              success: function(data){//통신이 성공적으로 이루어 졌을때 받을 함수
	                    window.opener.doSearch();
	                    self.close();
	              },
	              complete: function(data){//무조건 수행
	            	  //alert("complete"); 
	              },
	              error: function(xhr,status,error){
	            	  alert("error");
	              }
	          });          
	           
	       });//--do_save
       
	   });//--document
	</script>
</head>
<body>

    <form method="post" name="popFrm" action="do_excelUpload.do" encType="multipart/form-data">
        <input type="file" name="file" id=""file"" />
        <button id="do_excelUp">엑셀업로드</button>
    </form>

</body>
</html>