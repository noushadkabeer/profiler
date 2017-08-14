<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- Content Wrapper. Contains page content -->
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
				<div align="right"> <a href="setUpForInsertOrUpdateProfile" class="btn btn-primary"><i
					class="icon icon-file"></i>Upload Resume</a> <a href="setUpForInsertOrUpdateJob"
					class="btn btn-primary"><i class="icon icon-file"></i>Add Job</a> 
					<a href="setUpForInsertOrUpdateEvent" class="btn btn-primary"><i class="icon icon-file"></i>Add Event</a> 
					<a href="setUpForInsertOrUpdateTask" class="btn btn-primary"><i class="icon icon-file"></i>Add Task</a> </div>
				<div class="page-header">
					<h2>Search Results==</h2>
				</div>
				<s:actionmessage cssClass="alert alert-error" />
				<table class="table table-striped">
					<tr>					
						<th>ID</th>
						<th>Name</th> 										
						<th>Last Modified By</th>
						<th>Size</th>
						<th>Path</th>
						<th>Actions</th>						
					</tr>
					<s:iterator value="nodes">
						<tr>
							<td><s:property value="id" /></td>
							<td><s:property value="name" /></td> 
							<td><s:property value="modifier" /></td>
							<td><s:property value="size" /></td>
							<td><s:property value="path" /></td>
							<td>
								<div class="btn-group">
									<a href="viewJob" class="btn"><i
										class="icon icon-eye-open"></i>View</a> 
										<s:url id="update" action="setUpForInsertOrUpdateJob">
		       		   						<s:param name="job.jobId" value="jobId"/>
		       							</s:url> <s:a href="%{update}" cssClass="btn"><i
										class="icon icon-edit"></i>Edit</s:a>
										<s:url id="delete" action="deleteJobConfirm">
		       		   						<s:param name="job.jobId" value="jobId"/>
		       							</s:url> <s:a href="%{delete}" cssClass="btn btn-danger"><i
										class="icon icon-trash"></i>Delete</s:a> 										 
								</div>
							</td>
						</tr>
					</s:iterator>
				</table>
				
			
				
			</div>
			<!--/row-->
		</div>
		<!--/span-->
	</div>
	
	
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
