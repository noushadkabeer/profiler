<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<html>
<head>

</head>
<body>

	<s:form>
		<table align="center" class="borderAll">


			<tr>
				<td class="tdLabel"><s:text name="label.userId" /></td>
				<td><s:textfield name="profileBean.userId" size="24"
						/></td>
			<tr />
			<tr>
				<td class="tdLabel"><s:text name="label.address_1" /></td>
				<td></td>
			<tr />
			<tr>
				<td class="tdLabel"><s:text name="label.address_2" /></td>
				<td> </td>
			<tr />
			<tr>
				<td class="tdLabel"><s:text name="label.city" /></td>
				<td><s:textfield name="profileBean.city" size="30" /></td>
			<tr />
			<tr>
				<td class="tdLabel"><s:text name="label.pincode" /></td>
				<td><s:textfield name="profileBean.pincode" size="10" /></td>
			<tr />
			<tr>
				<td class="tdLabel"><s:text name="label.workphone" /></td>
				<td><s:textfield name="profileBean.workphone" size="10" /></td>
			<tr />
			<tr>
				<td class="tdLabel"><s:text name="label.homephone" /></td>
				<td><s:textfield name="profileBean.homephone" size="10" /></td>
			<tr />
			<tr>
				<td class="tdLabel"><s:text name="label.interests" /></td>
				<td> <sj:textarea id="profileBean.interests"
						name="profileBean.interests" rows="10" cols="80"
						effect="highlight" effectDuration="2500"
						loadingText="Loading.." />
						</td>
			<tr />			
		</table>
		<table><tr>
				<td align="left" style="font: bold; color: red"><s:fielderror />
					<s:actionerror /> <s:actionmessage /></td>
			</tr></table>

		<table >
			<tr>
				<td><s:submit action="insertOrUpdateProfile"
						key="button.label.submit" cssClass="butStnd" /></td>
				<td><s:reset key="button.label.cancel" cssClass="butStnd" /></td>
			<tr>
		</table>
	</s:form>
</body>
</html>