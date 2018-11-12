<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<html>
<head>

</head>
<body>

	<s:form>
 <div class="social-auth-links text-center">
   <b><s:actionerror /><br/><s:actionmessage /></b> 
    </div>
		<table class="borderAll">
			<tr>
				<td class="tdLabel"><s:text name="First Name" /></td>
				<td><s:textfield name="user.firstName" size="24"
						/></td>
			<tr />
			<tr>
				<td class="tdLabel"><s:text name="Last Name" /></td>
				<td><s:textfield name="user.lastName" size="24"/></td>
			<tr />
			<tr>
				<td class="tdLabel"><s:text name="Email" /></td>
				<td><s:textfield name="user.userEmail" size="24"/></td>
			<tr />
			<tr>
				<td class="tdLabel"><s:text name="Password" /></td>
				<td><s:textfield name="user.password" size="24"/></td>
			<tr />
				
		</table>
		<table><tr>
				<td align="left" style="font: bold; color: red"><s:fielderror />
					<s:actionerror /> <s:actionmessage /></td>
			</tr></table>

		<table >
			<tr>
				<td><s:submit action="insertOrUpdateUser"
						key="button.label.submit" cssClass="butStnd" /></td>
				<td><s:reset key="button.label.cancel" cssClass="butStnd" /></td>
			<tr>
		</table>
	</s:form>
</body>
</html>