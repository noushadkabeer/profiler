<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ProfileR - Login</title>
<!-- <link href="<s:url value="/css/main.css"/>" rel="stylesheet"
	type="text/css" />-->
	
	  <link href="<s:url value="/css/adminlte/bootstrap.min.css"/>" rel="stylesheet" 	type="text/css" /> 
  <link href="<s:url value="/css/AdminLTE.min.css"/>" rel="stylesheet" 	type="text/css" />
    <link href="<s:url value="/css/font-awesome.min.css"/>" rel="stylesheet" 	type="text/css" />
      <link href="<s:url value="/css/ionicons.min.css"/>" rel="stylesheet" 	type="text/css" />
        <link href="<s:url value="/css/blue.css"/>" rel="stylesheet" 	type="text/css" />
</head>


<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <a href="../../index2.html">Profile<b>R</b></a>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">Sign in to start your session</p>

    <form action="login.action" method="post">
      <div class="form-group has-feedback">
      <s:textfield name="userName"  size="20" cssClass="form-control" placeholder="Username" />
        <!-- <input type="email" class="form-control" placeholder="Email">-->
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
      <s:password name="password" cssClass="form-control" placeholder="Password" size="20" />
        <!-- <input type="password" class="form-control" placeholder="Password">-->
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox icheck">
            <label>
              <input type="checkbox"> Remember Me
            </label>
          </div>
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
          <s:submit cssClass="btn btn-primary btn-block btn-flat" value="Sign In"/>
        </div>
        <!-- /.col -->
      </div>
    </form>

    <div class="social-auth-links text-center">
    <s:actionerror /><br/><s:actionmessage />
    </div>
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
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' // optional
    });
  });
</script>
</body>



</html>