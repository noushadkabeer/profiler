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
					
					<div class="errors">
				<s:if test="hasActionErrors()">
					<div class="errors" style="font-weight: bold; color:red;">
						<s:actionerror />
					</div>
				</s:if>
					</div>
				<s:form action="insertOrUpdateUser">
					<s:label value="Personal Information" />
					<br />
					<s:hidden name="user.userId" />
					<s:label name="username" value="User Name :" />
					<s:textfield readonly="true" name="user.userName" size="70" />
					<s:label name="firstname" value="First Name:" />
					<s:textfield name="user.firstName" size="70" />
					<s:label value="Last Name" />
					<s:textfield name="user.lastName" size="70" />
					<s:label value="Email :" />
					<s:textfield name="user.userEmail" size="70" />
					<s:label value="Mobile :" />
					<s:textfield name="user.mobile" size="70" />
					<s:label value="Email Password:" />
					<s:password name="user.instantmsg" size="70" />
					<s:label value="Confirm Email Password:" />
					<s:password name="confirmEmailPassword" size="70" />
					<s:label value="Avatar :" />
					<s:textfield name="user.avatar" size="70" />

					<s:label value="About you (Roles/Responsibilities):" />
					<s:textarea name="user.persondescription" />
					<s:label value="Organization Information" />
					<br />
					<s:label value="Organization ID:" />
					<s:textfield name="user.userCompanyID" size="70" />
					<s:label value="Organization Name:" />
					<s:textfield name="user.userOrganization" size="70" />
					<s:label value="Organization Title:" />
					<s:textfield name="org.title" size="70" />
					<s:label value="Organization Description:" />
					<s:textfield name="org.description" size="70" />
					<s:label value="Telephone :" />
					<s:textfield name="user.companyTelephone" size="70" />
					<s:label value="Email ID:" />
					<s:textfield name="user.companyemail" size="70" />
					<s:label value="Post Code:" />
					<s:textfield name="user.companypostcode" size="70" />
					<s:submit cssClass="btn btn-primary popModal_ex"></s:submit>
				</s:form>


			</div>
			<!--/row-->



		</div>
		<!--/span-->


	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

