<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Job ${id}</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" rel="stylesheet">
</head>
<body>

						<td><s:property value="jobId" /></td>
							<td><s:property value="jobExperience" /></td>
							<td><s:property value="jobTitle" /></td>
							<td><s:property value="location" /></td>
							<td><s:property value="company" /></td>
							
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="page-header">
                <h1>Job ${id}</h1>
            </div>
            <table class="table table-striped">
            	<tr>
                    <td class="span3">Title</td>
                    <td class="span9"><s:property value="jobTitle"/></td>
                </tr>
                <tr>
                    <td class="span3">Job ID</td>
                    <td class="span9"><s:property value="jobId"/></td>
                </tr>                
                <tr>
                    <td class="span3">Experience</td>
                    <td class="span9"><s:property value="jobExperience"/></td>
                </tr>
                <tr>
                    <td class="span3">Location</td>
                    <td class="span9"><s:property value="location"/></td>
                </tr>
                <tr>
                    <td class="span3">Company</td>
                    <td class="span9"><s:property value="company"/></td>
                </tr>
            </table>
	        <a href="${pageContext.request.contextPath}/orders" class="btn btn-info">
		        <i class="icon icon-arrow-left"></i> Back to Dashboad
	        </a>
        </div><!--/row-->
    </div><!--/span-->
</div><!--/row-->
</body>
</html>
	