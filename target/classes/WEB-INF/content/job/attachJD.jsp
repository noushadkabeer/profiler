<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Attach JD</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">

	        <div class="page-header">
		        <h2>Attach Job Description</h2>
	        </div>

	        <s:fielderror  cssClass="alert alert-error"/>
	<form action="/file-upload"
      class="dropzone"
      id="my-awesome-dropzone">
      
      </form>


	        <s:form method="post" action="%{#request.contextPath}/orders" cssClass="form-horizontal" theme="simple">
       
            s
            </s:form>
	        <a href="${pageContext.request.contextPath}/agencydashboard" class="btn btn-info">
		        <i class="icon icon-arrow-left"></i> Back to Dashboard
	        </a>
        </div><!--/row-->
    </div><!--/span-->
</div><!--/row-->
</body>
</html>
	