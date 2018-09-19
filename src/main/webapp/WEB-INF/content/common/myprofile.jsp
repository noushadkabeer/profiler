<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags"%>


<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			My Dashboard <small>My Profile</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
			<li><a href="#">Profile</a></li>
			<li class="active">Settings</li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">

		<!-- Default box -->
		<div class="box">
			<div class="box-header with-border">
				<h3 class="box-title">My Profile</h3>
				<s:fielderror  cssClass="alert alert-error"/>

<s:form>
				<strong>Personal Information </strong>
				<s:label name="userid" value="User ID"/> <s:textfield name="user.userId" readonly="true" size="70"/>
				<s:label name="username" value="User Name :"/><s:textfield name="user.userName" readonly="true" size="70"/>
				<s:label name="firstname" value="First Name:"/> <s:textfield name="user.firstName" size="70"/>
				<s:label value="Last Name"/><s:textfield name="user.lastName" size="70"/>
				<s:label value="Email :"/><s:textfield name="user.userEmail" size="70"/>
				<s:label value="Mobile :"/><s:textfield name="user.mobile" size="70"/>
				<s:label value="Avatar :"/><s:textfield name="user.avatar" size="70"/>
				<s:label value="Instant Msg:"/> <s:textfield name="user.instantmsg" size="70"/>
				<s:label value="About you :"/> <s:textarea name="user.persondescription" />

				<strong>Organization Information</strong>
				<s:label value="Organization ID:"/>  <s:textfield name="user.userCompanyID" readonly="true" size="70"/>
				<s:label value="Organization Name:"/> <s:textfield name="user.userOrganization" size="70"/>
				<s:label value="Telephone :"/><s:textfield name="user.companypTelephone" size="70"/>
				<s:label value="Email ID:"/> <s:textfield name="user.companyemail" size="70"/>
				<s:label value="Post Code:"/> <s:textfield name="user.companypostcode" size="70"/>
</s:form>


			</div>
			<!--/row-->



		</div>
		<!--/span-->


	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

