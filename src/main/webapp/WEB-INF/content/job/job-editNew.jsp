<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>New Order</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">

	        <div class="page-header">
		        <h2>Job Setup</h2>
	        </div>

	        <s:fielderror  cssClass="alert alert-error"/>

	        <s:form method="post" action="%{#request.contextPath}/orders" cssClass="form-horizontal" theme="simple">
       
                <div class="control-group">
                    <label class="control-label" for="clientName">Title</label>
                    <div class="controls">
                        <s:textfield id="jobTitle" name="jobTitle"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="amount">Experience</label>
                    <div class="controls">
                            <s:textfield id="jobExperience" name="jobExperience" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="clientName">Title</label>
                    <div class="controls">
                        <s:textfield id="jobTitle" name="jobTitle"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="amount">Location</label>
                    <div class="controls">
                            <s:textfield id="location" name="location" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="amount">Company</label>
                    <div class="controls">
                            <s:textfield id="company" name="company" />
                    </div>
                </div>
                <div class="form-actions">
                    <s:submit cssClass="btn btn-primary"/>
                </div>
            </s:form>
	        <a href="${pageContext.request.contextPath}/agencydashboard" class="btn btn-info">
		        <i class="icon icon-arrow-left"></i> Back to Dashboard
	        </a>
        </div><!--/row-->
    </div><!--/span-->
</div><!--/row-->
</body>
</html>
	