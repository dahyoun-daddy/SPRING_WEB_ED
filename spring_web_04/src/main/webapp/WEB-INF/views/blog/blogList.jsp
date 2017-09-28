<%@page import="com.sist.common.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>       
<%

String page_size  = "10";
String page_num   = "1";
String searchWord = "";
String searchDiv  = "";
int totalCnt      = 0; //총글수
int maxPageNum    = 0; //pagingMax 

page_size = StringUtil.nvl(request.getParameter("page_size"),"10");
page_num  = StringUtil.nvl(request.getParameter("page_num"),"1");
searchWord= StringUtil.nvl(request.getParameter("search_word"),"");
searchDiv = StringUtil.nvl(request.getParameter("search_div"),"");

if(null!=request.getAttribute("totalCnt")){
	totalCnt = Integer.parseInt(
	        StringUtil.nvl(
	        request.getAttribute("totalCnt").toString(),"0"));
	out.print("totalCnt:"+totalCnt);
	int pageSize = Integer.parseInt(page_size);
	maxPageNum = (int)Math.ceil(totalCnt/pageSize);
	
	out.print("maxPageNum:"+maxPageNum);
}

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

	function doSearch(){
	    var frm = document.frm;
	    if(frm.search_word.value =="" || frm.search_word.value.length<=0){
	    	
	    	alert("검색어를 입력하세요");
	    	frm.search_word.focus();
	    	return;
	    }	
	    frm.page_num.value = "1";
	    frm.action = "blogList.do";
	    frm.submit();
	}

</script>

<body>
    <form name="frm" action="blogList.do" method="post" class="form-inline">
      <input type="hidden"  name="page_num" id="page_num" value="<%=page_num %>"  >
<!-- Button Area -->    
    <div class="form-inline pull-right ">
       <select name="search_div" id="search_div" class="form-control input-sm">
              <option value="10"  <%if(searchDiv.equals("10"))out.print("selected='selected'"); %>>daum</option>
              <option value="20"  <%if(searchDiv.equals("20"))out.print("selected='selected'"); %>>naver</option>
       </select>
          
      <select name="page_size" id="page_size" class="form-control input-sm">
              <option value="10"  <%if(page_size.equals("10"))out.print("selected='selected'"); %>>10</option>
              <option value="20"  <%if(page_size.equals("20"))out.print("selected='selected'"); %>>20</option>
      </select>
      
      <input type="text" class="form-control input-sm" name="search_word"  placeholder="검색어"  value="<%=searchWord %>">
      <button class="btn btn-success" 
      onclick="javascript:doSearch()">조회</button>
    </div>
<!--// Button Area --> 

<table id="listTable"  class="table table-bordered table-hover table-striped" >
        <thead>
            <th class="text-center">No</th>
            <th class="text-center">제목</th>
            <th class="text-center">상세</th>
        </thead>
        <tbody >
        <c:choose>
            <c:when test="${daumList.size()>0}" >
                <c:forEach var="item" items="${daumList}" varStatus="status">
                        <tr>
                            <td class="text-left"><c:out value="${status.count}"/></td>
                            <td class="text-left"><a href='<c:out value="${item.link}"/>'><c:out value="${item.title}"/></a></td>
                            <td class="text-left"><c:out value="${item.description}"/></td>

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
    </form> 
</body>
</html>