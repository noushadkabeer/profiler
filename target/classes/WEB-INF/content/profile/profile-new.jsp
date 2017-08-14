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
		        <h2>Edit Profile Ne</h2>
	        </div>
	        <s:fielderror  cssClass="alert alert-error"/>
<s:form>
     <table align="center" class="borderAll">
     	 
         <tr><td class="tdLabel"><s:text name="label.firstName"/></td>
         	        <td><s:textfield name="profile.name" size="30"/></td>
         </tr>
    </table>
    		 <br/>
    <table> 
    	     <tr>
    		    <td><s:submit action="insertOrUpdateProfile" key="button.label.submit" cssClass="butStnd"/></td>
    	        <td><s:reset key="button.label.cancel" cssClass="butStnd"/></td>
    		 <tr>
    </table> 		  		 
    	</s:form>
	

	        <a href="${pageContext.request.contextPath}/agencydashboard" class="btn btn-info">
		        <i class="icon icon-arrow-left"></i> Back to Dashboard
	        </a>
        </div><!--/row-->
    </div><!--/span-->
</div><!--/row-->
</body>
</html>
	