<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<nav class="nav">
	<ul class="nav__list">
		<li class="nav__list-item">
			<a href="friends.html" class="nav__list-link"> 
				<i class="far fa-user"></i>
			</a>
		</li>
		<li class="nav__list-item">
			<a href='<c:url value="/home?action=chat" />' class="nav__list-link">
				<!-- <span class="nav__badge">2</span>  -->
				<i class="fas fa-comment"></i>
			</a>
		</li>
		<li class="nav__list-item">
			<a href="find.html" class="nav__list-link"> 
				<i class="fas fa-search"></i>
			</a>
		</li>
		<li class="nav__list-item">
			<a href="more.html" class="nav__list-link"> 
				<i class="fas fa-ellipsis-h"></i>
			</a>
		</li>
	</ul>
</nav>