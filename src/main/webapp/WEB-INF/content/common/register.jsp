<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ProfileR - Login</title>
<!-- <link href="<s:url value="/css/main.css"/>" rel="stylesheet"
	type="text/css" />-->

<link href="<s:url value="/css/adminlte/bootstrap.min.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<s:url value="/css/AdminLTE.min.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<s:url value="/css/font-awesome.min.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<s:url value="/css/ionicons.min.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<s:url value="/css/blue.css"/>" rel="stylesheet"
	type="text/css" />
</head>


<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<img alt="" src="<s:url value="/images/pebblsoft.png"/>"> <a
				href="../../index2.html">Profile<b>R</b></a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg"><strong>User registration</strong></p>

			<s:form>
					<s:label name="firstName" value="First Name :" />
						<s:textfield name="user.firstName" size="24" />
					
					<s:label name="lastName" value="Last Name :" />
						<s:textfield name="user.lastName" size="24" />
					
					<s:label name="email" value="Email:" />
						<s:textfield name="user.userEmail" size="24" />
					
					<s:label name="password" value="Password :" />
						<s:password name="user.password" size="24" />
				
				<s:if test="hasActionErrors()">
					<div class="errors" style="font-weight: bold; color:red;">
						<s:actionerror />
					</div>
				</s:if>

				<table>
					<tr>
						<td><s:submit action="registerUserConfirm"
								key="button.label.submit" cssClass="butStnd" /></td>
						<td><a href="${pageContext.request.contextPath}/index" class="btn btn-info">Back</a></td>
					<tr>
				</table>
			</s:form>

			<!-- /.social-auth-links -->

		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->

	<!-- jQuery 2.2.3 -->
	<script src="<s:url value="/js/jquery-2.2.3.min.js"/>"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="<s:url value="/js/bootstrap.min.js"/>"></script>
	<!-- iCheck -->
	<script src="<s:url value="/js/icheck.min.js"/>"></script>
	<script>
		$(function() {
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
				increaseArea : '20%' // optional
			});
		});
	</script>
</body>



</html>