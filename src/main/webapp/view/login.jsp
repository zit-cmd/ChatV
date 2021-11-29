<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="simple-login-container">
	<h2>Login Form</h2>
	<c:if test="${not empty message }">
		<div class="alert alert-${alert }" role="alert">
		  	${message}
		</div>
	</c:if>
	<form method="post" action="<c:url value='/dang-nhap'/>">
		<div class="row">
			<div class="col-md-12 form-group">
				<input type="text" class="form-control" placeholder="Username"
					required name="username">
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 form-group">
				<input type="password" placeholder="Enter your Password"
					class="form-control" required name="password">
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 form-group">
				<input type="submit" class="btn btn-block btn-login"
					placeholder="Enter your Password">
			</div>
		</div>
		<input type="hidden" value="login" name="action" />
	</form>
	<div class="row">
		<div class="col-md-12">
			<a href="<c:url value='/dang-ky?action=reg'/>">Sign up now</a>
		</div>
	</div>
</div>