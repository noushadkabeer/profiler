<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags"%>


<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			My Dashboard <small>Welcome Back!</small>
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
				<h3 class="box-title">System Settings</h3>


				<s:fielderror cssClass="alert alert-error" />
				<s:form>
      	Page Size : <s:textfield name="pageSize"
						value="getText('system.config.pageSize')" size="5" />
					<br />
      	Page Number : <s:textfield name="pageNum"
						value="getText('system.config.pageNum')" size="5" />
					<br />
					<s:property value="getText('system.config.pageSize')" />
					<br>

					<s:submit action="bulkUploadProcessor" key="button.label.submit"
						cssClass="btn btn-info" />
				</s:form>

	
	
	
	 <a href="${pageContext.request.contextPath}/agencydashboard"
					class="btn btn-info"> <i class="icon icon-arrow-left"></i> Back
					to Dashboard
				</a>
			</div>
			<!--/row-->



		</div>
		<!--/span-->


	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
