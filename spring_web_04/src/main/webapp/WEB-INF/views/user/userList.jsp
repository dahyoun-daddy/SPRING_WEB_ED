<%@page import="com.sist.code.domain.CodeVO"%>
<%@page import="java.util.List"%>
<%@page import="com.sist.common.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
List<CodeVO> codeList = (List<CodeVO>)request.getAttribute("codeList");
int bottomCount   = 10;
String searchDiv  = "";
String searchWord = "";
String page_size  = "10";
String page_num   = "1";
int totalCnt      = 0; //총글수

searchDiv = StringUtil.nvl(request.getParameter("searchDiv"),"");
searchWord= StringUtil.nvl(request.getParameter("searchWord"),"");
page_size = StringUtil.nvl(request.getParameter("page_size"),"10");
page_num  = StringUtil.nvl(request.getParameter("page_num"),"1");

int oPage_size = Integer.parseInt(page_size);
int oPage_num  = Integer.parseInt(page_num);

totalCnt = Integer.parseInt(
		    StringUtil.nvl(
			request.getAttribute("totalCnt").toString(),"0"));

%>

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
<title>:::::사용자관리:::::</title>
<script language="javaScript">
	function do_excelUp() {
        var pop_title = "excelUpload" ;
        var popUrl    = "do_excelUpload.do";   //팝업창에 출력될 페이지 URL
        /*
        open 함수는 반드시 3개의 매개변수가 있고, 매개변수 순서는 지켜야 함.
        name은 팝업창의 이름, 주로 프레임 문서의 Target으로 사용
        name은 사용하지 않으려면 " " 만 표시해줍니다. 반드시 표시해야 함.
        */
        var popOption = "width=370, height=200, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)
        window.open(popUrl,pop_title,popOption);
        
	}
	/* 체크박스 전체선택, 전체해제 */
	function checkAll() {
		
	    if( $("#checkAll").is(':checked') ){
	        $("input[name=check]").prop("checked", true);
	    }else{
	        $("input[name=check]").prop("checked", false);
	    }
	    
	}


	//paiging 이동
	function do_search_page(url, page_num){
	    console.log(url +"\t"+ page_num);
	    var frm = document.frm;
	    frm.page_num.value = page_num;
	    frm.action = url;
	    frm.submit();
	}
	
	function doSearch(){
	    var frm = document.frm;
	    frm.page_num.value = "1";
	    frm.action = "do_search.do";
	    frm.submit();
	}
	/*
	* ExcelDownload
	*/
    function do_excelDown(){
        var frm = document.frm;
        frm.action = "do_excelDown.do";
        frm.submit();
    }	
	
	//jquery document
	$(document).ready(function(){

		
		
		//do_save
		$("#do_save").on("click",function(){
		   
		   if(false==confirm("등록하시겠습니까?"))return;
		   
		   var workDiv = $("#workDiv").val();
		   var insertYN = (workDiv == "")?"":"update";
		   console.log("insertYN: "+insertYN);
	       $.ajax({
               type:"POST",
               url:"do_save.do",
               dataType:"html",// JSON
               async: false,
               data:{
                  "workDiv"  : insertYN,
                  "id"       :$("#u_id").val(),
                  "name"     :$("#u_name").val(),
                  "password" :$("#u_password").val(),
                  "u_level"  :$("#u_level").val(),
                  "login"    :$("#u_login").val(),
                  "recommend":$("#u_recommend").val(),
                  "mail"     :$("#u_mail").val()
               },
               success: function(data){//통신이 성공적으로 이루어 졌을때 받을 함수
                   //console.log("success data: "+data);
                   doSearch();
               },
               complete: function(data){//무조건 수행
                   
               },
               error: function(xhr,status,error){
            	   console.log("error: "+error);
               }
           });			
			
		});//--do_save
		
		//do_searchOne
		$("#listTable>tbody").on("dblclick","tr",function(){
						
			var tr = $(this);
			var td = tr.children();
			var id = td.eq(2).text()
			//console.log("row_index: "+row_index);
			//console.log("td.eq(2): "+td.eq(2).text());
			
			$.ajax({
	               type:"POST",
	               url:"userForm.do",
	               dataType:"html",// JSON
	               data:{
	                  "id" :id
	               },
	               success: function(data){//통신이 성공적으로 이루어 졌을때 받을 함수
	                   //console.log("success data: "+data);
	                   var userVO = $.parseJSON(data);
	                   $("#workDiv").val(userVO.id);
	                   
	                   $("#u_id").val(userVO.id);
	                   $("#u_name").val(userVO.name);
	                   $("#u_password").val(userVO.password);
	                   $("#u_level").val(userVO.ulevel);
	                   $("#u_login").val(userVO.login);
                       $("#u_recommend").val(userVO.recommend);
                       $("#u_mail").val(userVO.mail);
                       $("#u_reg_dt").val(userVO.regDt);	
	               },
	               complete: function(data){//무조건 수행
	            	   
	               },
	               error: function(xhr,status,error){
	                   console.log("error: "+error);
	               }
	           });			
			
		});
		//--do_searchOne
		
		//do_delete
		$("#do_delete").on("click",function(){
			 //console.log("do_delete");//check
			 
			 
			 //checked
			 var idArray = new Array();
			 
			 $("#check:checked").each( function(idx,row){
				 var record = $(row).parents("tr");
				 var id = $(record).find('td').eq(2).text();
				 
				 idArray.push(id);
			 });//--checked
			 
			 if(idArray.length<=0){
				 alert("삭제할 데이터를 선택 하세요.");
				 return false;
			 }
			 
			 
			 
			 var jsonIdList = JSON.stringify(idArray);
			 //console.log("jsonIdList:"+jsonIdList);
			 if(false==confirm("삭제하시겠습니까?\n"+jsonIdList))return;
		     $.ajax({
		    	 type:"POST",
                 url:"do_checkedDelete.do",   
                 dataType:"JSON",// JSON
                 async: false,
                 data:{
                    "idList" :jsonIdList
                 },
                 success: function(data){//통신이 성공적으로 이루어 졌을때 받을 함수
                	 alert($.trim(data)+"건 삭제되었습니다.");
                	 doSearch();
                 },
                 complete: function(data){//무조건 수행
                     
                 },
                 error: function(xhr,status,error){
                     console.log("do_checkedDelete error: "+error);
                 }
             }); 
			 
		});
		//--do_delete
		             
	});//--jquery document

</script>
</head>
<body>  
    <h3>UserList</h3>
    <hr/>

    <form name="frm" action="do_search.do" method="post" class="form-inline">
         <input type="hidden"  name="page_num" id="page_num" value="<%=page_num %>"  >
         <input type="hidden"  name="file_nm"  id="file_nm"  >
<!-- Button Area -->    
	<div class="form-inline pull-right ">
<%--       <select name="page_size" id="page_size" class="form-control input-sm">
              <option value="10"  <%if(page_size.equals("10"))out.print("selected='selected'"); %>>10</option>
              <option value="20"  <%if(page_size.equals("20"))out.print("selected='selected'"); %>>20</option>
              <option value="50"  <%if(page_size.equals("50"))out.print("selected='selected'"); %>>50</option>
      </select>	 --%>
      <%=StringUtil.mkSelect(codeList, page_size, "page_size", false) %>
      
	  <button class="btn btn-success" 
	  onclick="javascript:doSearch()">조회</button>
	  <button class="btn btn-success"  id="do_save">등록</button>
	  <button class="btn btn-success" id="do_delete">삭제</button>
	  <button class="btn btn-success" onclick="do_excelUp()">ExcelUp</button>
	  <button class="btn btn-success" onclick="do_excelDown()" >ExcelDown</button>
	</div>
<!--// Button Area -->    
	     <table  class="table">
	         <tr>
	             <td class="text-center">구분
	                 <select name="searchDiv" class="form-control input-sm">
                        <option value="">전체</option>
                        <option value="10"  <%if(searchDiv.equals("10"))out.print("selected='selected'"); %>>ID</option>
                        <option value="20"  <%if(searchDiv.equals("20"))out.print("selected='selected'"); %>>이름</option>
                        <option value="30"  <%if(searchDiv.equals("30"))out.print("selected='selected'"); %>>레벨</option>
	                 </select>
	                 <input type="text" class="form-control input-sm" name="searchWord"  size="10"  value="<%=searchWord %>">
	                 <c:out value="${list.size()}"/>/<c:out value="${totalCnt}"/>
	             </td>
	             </tr>
	     </table>
     </form>
                 
     <table id="listTable"  class="table table-bordered table-hover table-striped" >
        <thead>
            <th class="text-center">
                <input type="checkbox" id="checkAll" 
                name="checkAll" onclick="checkAll();" />
            </th>
            <th class="text-center">NO.</th>
            <th class="text-center">아이디</th>
            <th class="text-center">이름</th>
            <th class="text-center">Level</th>
            <th class="text-center">로그인</th>
            <th class="text-center">이메일</th>
        </thead>
        <tbody >
        <c:choose>
            <c:when test="${list.size()>0}" >
                <c:forEach var="userVo" items="${list}">
		                <tr><td class="text-center"><input type="checkbox" id="check" name="check" /> </td>
		                    <td class="text-center"><c:out value="${userVo.no}"/></td>
		                    <td class="text-left"><c:out value="${userVo.id}"/></td>
		                    <td class="text-left"><c:out value="${userVo.name}"/></td>
		                    <td class="text-center"><c:out value="${userVo.ulevel}"/></td>
		                    <td class="text-right"><c:out value="${userVo.login}"/></td>
		                    <td class="text-left"><c:out value="${userVo.mail}"/></td>
		                </tr>       
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr >
                    <td colspan="99">등록된 게시글이 없습니다.</td>
                </tr>    
            </c:otherwise>
        </c:choose>
        </tbody>
     </table>
            <!-- Paging << < 1 2 ... > >> -->
            <div class="form-inline text-center ">
                <%=StringUtil.renderPaging(totalCnt, oPage_num, oPage_size, bottomCount, "do_search.do", "do_search_page") %>
            </div>
            <!--// Paging << < 1 2 ... > >> -->
    <!-- edit container -->
     <div class="container">
        <div class="col-lg-12"></div>
        <div class="col-lg-12"></div>
        <div class="panel panel-default"></div>
        <div class="panel-heading text-center">게시판등록</div>
        
        <!-- edit form -->
        <form id="frmEdit"  name="frmEdit"  
         method="post" class="form-horizontal" 
         role="form">
        <input type="hidden"  name="workDiv" id="workDiv" value=""/>   
           <div class="form-group">
               <label class="col-lg-4 control-label">아이디</label>
               <div class="col-lg-8">
                    <input type="text"  
                    name="u_id" id="u_id" 
                    class="form-control input-sm"   />
               </div>
           </div>
           <div class="form-group">
               <label class="col-lg-4 control-label">이름</label>
               <div class="col-lg-8">
                    <input type="text"  
                    name="u_name" id="u_name" 
                    class="form-control input-sm"   />
               </div>
           </div>
           <div class="form-group">
               <label class="col-lg-4 control-label">Email</label>
               <div class="col-lg-8">
                    <input type="text"  
                    name="u_mail" id="u_mail" 
                    class="form-control input-sm"   />
               </div>
           </div>
           <div class="form-group">
               <label class="col-lg-4 control-label">비밀번호</label>
               <div class="col-lg-8">
                    <input type="text"  
                    name="u_password" id="u_password" 
                    class="form-control input-sm"   />
               </div>
           </div>                      
           <div class="form-group">
               <label class="col-lg-4 control-label">Level</label>
               <div class="col-lg-8">
                    <input type="text"  
                    name="u_level" id="u_level" 
                    class="form-control input-sm"   />
               </div>
           </div>
           <div class="form-group">
               <label class="col-lg-4 control-label">로그인(횟수)</label>
               <div class="col-lg-8">
                    <input type="text"  
                    name="u_login" id="u_login" 
                    class="form-control input-sm"   />
               </div>
           </div>
           <div class="form-group">
               <label class="col-lg-4 control-label">추천(횟수)</label>
               <div class="col-lg-8">
                    <input type="text"  
                    name="u_recommend" id="u_recommend" 
                    class="form-control input-sm"   />
               </div>
           </div>           
           <div class="form-group">
               <label class="col-lg-4 control-label">변경일</label>
               <div class="col-lg-8">
                    <input type="text"  readonly="readonly" 
                    name="u_reg_dt" id="u_reg_dt" 
                    class="form-control input-sm"   />
               </div>
           </div> 
                                                       
        </form>  
        <!--// form -->
     </div>
     <!--// edit container -->                
</body>
</html>