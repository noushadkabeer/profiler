<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link href="<s:url value="/css/main.css"/>" rel="stylesheet"
	type="text/css" />


<title><s:text name="label.customers" /></title>
</head>
<body>
	<h1>
		<s:text name="label.myprofile" />
	</h1>

	<table align=center class="borderAll">
		<tr class="even">
			<th><s:text name="label.userId" /></th>
			<th><s:text name="Address 1" /></th>
			<th><s:text name="Address 2" /></th>
			<th><s:text name="City" /></th>
			<th><s:text name="label.customerPin" /></th>
			<th><s:text name="Work Phone" /></th>
			<th><s:text name="Home Phone" /></th>
			<th><s:text name="Interests" /></th>
			<th>Actions&nbsp;</th>
		</tr>
		<tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
			<td><s:text name="profileBean.userId" /></td>
			<td><s:text name="profileBean.address_1" /></td>
			<td><s:text name="profileBean.address_2" /></td>
			<td><s:text name="profileBean.city" /></td>
			<td><s:text name="profileBean.pincode" /></td>
			<td><s:text name="profileBean.workphone" /></td>
			<td><s:text name="profileBean.homephone" /></td>
			<td><s:text name="profileBean.interests" /></td>

			<td class="nowrap"><s:url id="update"
					action="setUpForInsertOrUpdateProfile">
					<s:param name="profileBean.userId" value="profileBean.userId" />
				</s:url> <s:a href="%{update}">Edit</s:a> &nbsp;				
    		</td>
		</tr>

	</table>
</body>
</html>