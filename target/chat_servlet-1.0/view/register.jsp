<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="simple-login-container">
	<h2>Sign up Form</h2>
	<c:if test="${not empty message }">
		<div class="alert alert-${alert }" role="alert">
		  	${message}
		</div>
	</c:if>
	<form method="post" action="<c:url value='/dang-ky'/>">
		<div class="row">
			<div class="col-md-12 form-group">
				<input id="user" type="text" class="form-control" required="required" name="username" placeholder="Create your Username">
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 form-group">
				<input id="pass" type="password" class="form-control" data-type="password" required="required" name="password" placeholder="Create your password">
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 form-group">
				<input id="user" type="text" class="form-control" required="required" name="fullName" placeholder="Your name">
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 form-group">
				<input id="pass" type="email" class="form-control" required="required" name="email" placeholder="Enter your email address">
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 form-group">
				<input type="submit" class="btn btn-block btn-login" placeholder="Sign up">
			</div>
		</div>
		<input type="hidden" value="reg" name="action" />
	</form>
	<div class="row">
		<div class="col-md-12">
			<a href="<c:url value='/dang-nhap?action=login'/>">Login</a>
		</div>
	</div>
</div>