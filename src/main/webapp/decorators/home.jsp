<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <link rel="icon" href='<c:url value="/asset/images/favicon.jpg" />'>
  <script src="https://kit.fontawesome.com/f2f9d53b4c.js"></script>
  <link href="https://unpkg.com/tailwindcss@1.4.6/dist/tailwind.min.css" rel="stylesheet"/>
  <link rel="stylesheet" href="<c:url value='/asset/css/home/styles.css'/>" />
  <title>Messenger</title>
</head>
<body>
	<c:if test='${not empty USER }'>
     	<input type="hidden" value="${USER.id }" id="myid" />
     	<script src="<c:url value='/asset/js/loading.js' />"></script>
     </c:if>
	<dec:body/>
	
	<!-- <nav> -->
	<%@ include file="/common/web/nav.jsp" %>
	<!-- </nav> -->
</body>

</html>
