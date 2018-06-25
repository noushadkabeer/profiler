<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags"%>



  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        My Dashboard
        <small>Welcome Back!</small> 
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">My Dashboard</a></li>
        <li class="active">Profiles</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
      <div class="box">
        <div class="box-header with-border">
          <h3 class="box-title">Title</h3>

          <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
              <i class="fa fa-minus"></i></button>
            <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
              <i class="fa fa-times"></i></button>
          </div>
        </div>
        <div class="box-body">


		<div class="row-fluid">
			<div class="span12">

				<div class="page-header">
					<h2><s:if test="!job.jobId.equals('')">
					Edit Job 
				</s:if>
				<s:else>
					New Job
				</s:else></h2>
				</div>
				<s:fielderror cssClass="alert alert-error" />
				<s:form>
					<table class="table table-striped" width="300px;"> 
					<tr>
							<td class="tdLabel"><s:text name="label.jobId" /></td>
							<td><s:textfield name="job.jobId" size="30" readonly="true"/></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.jobExperience" /></td>
							<td><s:textfield name="job.jobExperience" size="30" />(No of Years)</td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.jobTitle" /></td>
							<td><s:textfield name="job.jobTitle" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.company" /></td>
							<td><s:textfield name="job.company" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.location" /></td>
							<td><s:textfield name="job.location" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.jobType" /></td>
							<td><s:textfield name="job.jobType" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.salary" /></td>
							<td><s:textfield name="job.salary" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.referenceCode" /></td>
							<td><s:textfield name="job.referenceCode" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.contactInfo" /></td>
							<td><s:textfield name="job.contactInfo" size="30" /></td>
						</tr> 
						<tr>
							<td class="tdLabel"><s:text name="label.aboutJob" /></td>
							<td><s:textfield name="job.aboutJob" size="30" /></td>
						</tr> 
						<tr>
							<td class="tdLabel"><s:text name="label.noOfVaccancies" /></td>
							<td><s:textfield name="job.noOfVaccancies" size="30" /></td>
						</tr> 
						</table>
						<table>
						<tr>
							<td><s:submit action="insertOrUpdateJob"
									key="button.label.submit" cssClass="butStnd" /> <s:reset key="button.label.cancel" cssClass="butStnd" /></td>
						<tr>
					</table>
				</s:form>

				<a href="${pageContext.request.contextPath}/agencydashboard"
					class="btn btn-info"> <i class="icon icon-arrow-left"></i> Back
					to Dashboard
				</a>
			</div>
			<!--/row-->
		</div>
		<!--/span-->
	</div>




     <!-- /.box-body --> 
      <!-- /.box -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->


	
