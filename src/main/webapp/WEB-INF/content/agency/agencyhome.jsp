<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			My Dashboard <small>Welcome Back!</small>
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
					<button type="button" class="btn btn-box-tool"
						data-widget="collapse" data-toggle="tooltip" title="Collapse">
						<i class="fa fa-minus"></i>
					</button>
					<button type="button" class="btn btn-box-tool" data-widget="remove"
						data-toggle="tooltip" title="Remove">
						<i class="fa fa-times"></i>
					</button>
				</div>
			</div>
			<div class="box-body">



				<div>
					<div class="row-fluid">
						<div class="span12">
							<div class="quickMenu" align="right">
								<a href="setUpForInsertOrUpdateProfile" class="btn btn-primary">
									<i class="icon icon-file"></i>Upload Resume
								</a> &nbsp; <a href="setUpForInsertOrUpdateJob"
									class="btn btn-primary"> <i class="icon icon-file"></i>Add
									Job</a>
									<!-- Add task will be a 1.1 version feature. 
								 &nbsp; <a href="setUpForInsertOrUpdateEvent"
									class="btn btn-primary"> <!-- <i class="icon icon-file"></i>Add Event</a> <a href="setUpForInsertOrUpdateTask" class="btn btn-primary"> -- >
									<i class="icon icon-file"></i>Add Task
								</a> -->
							</div>
							<div>
							 <b><s:actionerror /><br/><s:actionmessage /></b> 
							</div>
						</div>


						<div style="width: 850px; margin: 5px;">
							<!-- <ul class="tabs" data-persist="true">
			            <li><a href="#view1">Jobs On Board</a></li>
			            <li><a href="#view2">Profiles</a></li>
			            <li><a href="#view3">Tasks</a></li>
			            <li><a href="#view4">Events</a></li>
			        </ul> -->
							<div class="tabcontents">

								<div id="view1">
									<h3>
										<strong>Candidate Profiles</strong>
									</h3>
									<table class="table table-striped">
										<tr>
											<th>Sl.no</th>
											<th>Name</th>
											<th>Email</th>
											<th>Contact No</th>
											<th>Experience</th>
											<th>Education</th>
											<th>Skills</th>
											<!-- 
						<th>Interests</th>
						<th>Address</th> -->
											<th>Attachments</th>
											<th>Actions</th>
										</tr>
										<s:iterator value="profiles" var="doe" status="status">
											<tr>
												<td><s:property value="%{#status.count}" /></td>
												<td><s:property value="#doe.name" /></td>
												<td><s:property value="#doe.email" /></td>
												<td><s:property value="#doe.contactNo" /></td>
												<td><s:property value="#doe.experience" /></td>
												<td><s:property value="#doe.education" /></td>
												<td><s:property value="#doe.skills" /></td>
												<!-- 
							<td><s:property value="#doe.interests" /></td> 
							<td><s:property value="#doe.address" /></td>  -->
												<td><a
													href='<s:property value="#doe.attachments.downloadURL"/>'>
														<s:property value="#doe.attachments.name" />
												</a></td>
												<td>
													<div class="btn-group  pved_width">
														<s:url id="renderProfile" action="pdfpreview">
															<s:param name="profileId" value="attachments.id" />
														</s:url>
														<button id='<s:property value="id"/>'
															class="btn btn-primary popModal_ex pro_align"
															data-popmodal-bind="#content_blob"
															data-id='<s:property value="attachments.id"/>'>P</button>
														<s:url id="viewProfile" action="viewProfile">
															<s:param name="profile.id" value="id" />
														</s:url>
														<s:a href="%{viewProfile}" cssClass="btn">
															<i class="fa fa-search"></i>
														</s:a>
														<s:url id="update" action="setUpForInsertOrUpdateProfile">
															<s:param name="profile.id" value="id" />
														</s:url>
														<s:a href="%{update}" cssClass="btn">
															<i class="fa fa-pencil-square-o"></i>
														</s:a>
														<s:url id="delete" action="deleteProfileConfirm">
															<s:param name="profile.id" value="id" />
														</s:url>
														<s:a href="%{delete}" cssClass="fa fa-trash fa-3">
															<i class="icon icon-trash"></i>
														</s:a>
														<s:url id="contact" action="draftemailToCandidate">
															<s:param name="to" value="#doe.email" />
														</s:url>
														<s:a href="%{contact}" cssClass="btn btn-danger">
															<i class="fa fa-envelope"></i>
														</s:a>
													</div>
												</td>
											</tr>
										</s:iterator>
										<hr />
									</table>
								</div>





								<!-- 
			           <div id="view4"> 
			            <h3><strong>My Events</strong>  </h3>
							<table class="table table-striped">
								<tr>
									<th>Event ID</th>
									<th>Event Title</th>
									<th>Event Details</th>
									<th>Event Schedule</th>
									<th>Event Status</th>
									<th>Actions</th>
								</tr>
								<s:iterator value="events" status="status">
									<tr>
										<td><s:property value="%{#status.count}" /></td>
										<td><s:property value="eventTitle" /></td>
										<td><s:property value="eventDetails" /></td>
										<td><s:property value="eventSchedule" /></td>
										<td><s:property value="eventStatus" /></td>
										<td> 
											<div class="btn-group">
												<a href="viewProfile" class="btn"><i
													class="icon icon-eye-open"></i>View</a> <a
													href="editProfile" class="btn"><i
													class="icon icon-edit"></i>Edit</a> <a
													href="deleteProfile" class="btn btn-danger"><i
													class="icon icon-trash"></i>Delete</a>
											</div>
										</td>
									</tr>
								</s:iterator>
								<hr/>
							</table>
			            </div>
			             -->
							</div>
						</div>

						<!--/row-->
					</div>
					<!--/span-->
				</div>
				<div>
					<form action="agencydashboard" method="post" name="paginationForm">
						<%@ include file="pagination.jsp"%>
					</form>
				</div>
				<!-- 

<table cellpadding="0" cellspacing="0" class="my-table" align="center" 
style="width:"50%">
     <tr>
      <th width="82px" class="sortable 
<s:if test="#attr.pagination.sortColumn.equals('id')">sorted 
<s:property value="#attr.pagination.sortClass"/> </s:if>">
      <a href="#" onclick="fnPagination(6,'id');">ID</a></th>
      
      <th class="sortable <s:if test="#attr.pagination.sortColumn.equals('name')">
sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
      <a href="#" onclick="fnPagination(6,'name');">Name</a></th>
      
      <th class="sortable <s:if test="#attr.pagination.sortColumn.equals('name')">
sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
      <a href="#" onclick="fnPagination(6,'role');">Role</a></th>
     </tr>
     <s:iterator value="profiles" var="user" status="rowstatus">
     <s:if test="#rowstatus.even == true">
     <tr class="ac_odd">
     </s:if>
     <s:else>
     <tr>
     </s:else>
      <td><s:property  value="id" /></td>
      <td><s:property  value="name" /></td>
      <td></td>
     </tr>
     </s:iterator>
    </table>

-->

			</div>
			<!-- /.box-body -->
			<!-- /.box -->

			<div class="box">
				<div class="box-header with-border">
					<!-- <h3 class="box-title">Jobs</h3>-->

					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool"
							data-widget="collapse" data-toggle="tooltip" title="Collapse">
							<i class="fa fa-minus"></i>
						</button>
						<button type="button" class="btn btn-box-tool"
							data-widget="remove" data-toggle="tooltip" title="Remove">
							<i class="fa fa-times"></i>
						</button>
					</div>
				</div>
				<div class="box-body">
					<div>
						<div style="width: 850px; margin: 5px;">
							<!-- <ul class="tabs" data-persist="true">
			            <li><a href="#view1">Jobs On Board</a></li>
			            <li><a href="#view2">Profiles</a></li>
			            <li><a href="#view3">Tasks</a></li>
			            <li><a href="#view4">Events</a></li>
			        </ul> -->
							<div class="tabcontents">

								<div id="view2">
									<h3>
										<strong>Jobs/Positions</strong>
									</h3>
									<table class="table table-striped">
										<tr>
											<th width="70px">Job ID</th>
											<th>Job Experience</th>
											<th>Title</th>
											<th>Location</th>
											<th>Company</th>
											<th>Job Type</th>
											<th>Job Description</th>
											<th>Salary Range</th>
											<th>Referral Code</th>
											<th>Actions</th>
										</tr>
										<s:iterator value="jobs" status="status">
											<tr>
												<td><s:property value="%{#status.count}" /></td>
												<td><s:property value="jobExperience" /></td>
												<td><s:property value="jobTitle" /></td>
												<td><s:property value="location" /></td>
												<td><s:property value="company" /></td>
												<td><s:property value="jobType" /></td>
												<td><s:property value="aboutJob" /></td>
												<td><s:property value="salary" /></td>
												<td><s:property value="referenceCode" /></td>
												<td>
													<div class="btn-group">
														<a href="viewJob" class="btn"><i
															class="icon icon-eye-open"></i>View</a>
														<s:url id="update" action="setUpForInsertOrUpdateJob">
															<s:param name="job.jobId" value="jobId" />
														</s:url>
														<s:a href="%{update}" cssClass="btn">
															<i class="icon icon-edit"></i>Edit</s:a>
														<s:url id="delete" action="deleteJobConfirm">
															<s:param name="job.jobId" value="jobId" />
														</s:url>
														<s:a href="%{delete}" cssClass="btn btn-danger">
															<i class="icon icon-trash"></i>Delete</s:a>
													</div>
												</td>
											</tr>
										</s:iterator>
										<hr />
									</table>
								</div>



							</div>
						</div>


					</div>
				</div>
			</div>
			<!-- 2nd Box Ends here -->
			<div class="box">
				<div class="box-header with-border">
					<!-- <h3 class="box-title">Jobs</h3> -->

					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool"
							data-widget="collapse" data-toggle="tooltip" title="Collapse">
							<i class="fa fa-minus"></i>
						</button>
						<button type="button" class="btn btn-box-tool"
							data-widget="remove" data-toggle="tooltip" title="Remove">
							<i class="fa fa-times"></i>
						</button>
					</div>
				</div>
				<div class="box-body">
					<div>
						<div style="width: 850px; margin: 5px;">

							<div class="tabcontents">

								<div id="view3">
									<h3>
										<strong>My Team</strong>
									</h3>
									<table class="table table-striped">
										<tr>
											<th>Member ID</th>
											<th>First Name</th>
											<th>Last Name</th>
											<th>User Name</th>
											<th>Actions</th>
										</tr>
										<s:iterator value="myTeam" status="status">
											<tr>
												<td><s:property value="userId" /></td>
												<td><s:property value="firstName" /></td>
												<td><s:property value="LastName" /></td>
												<td><s:property value="userName" /></td>
												<td>
													<div class="btn-group">
														<a href="viewProfile-" class="btn"><i
															class="icon icon-eye-open"></i>Activate(ToDo)</a> <a
															href="deleteProfile-" class="btn btn-danger"><i
															class="icon icon-trash"></i>Deactivate(ToDo)</a>
													</div>
												</td>
											</tr>
										</s:iterator>
										<hr />
									</table>
								</div>



							</div>
						</div>


					</div>
				</div>
			</div>
			<!-- 3rd Box Ends here -->

			<div class="box">
				<div class="box-header with-border">
					<!-- <h3 class="box-title">Jobs</h3> -->

					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool"
							data-widget="collapse" data-toggle="tooltip" title="Collapse">
							<i class="fa fa-minus"></i>
						</button>
						<button type="button" class="btn btn-box-tool"
							data-widget="remove" data-toggle="tooltip" title="Remove">
							<i class="fa fa-times"></i>
						</button>
					</div>
				</div>
				<div class="box-body">
					<div>
						<div style="width: 850px; margin: 5px;">
							<!-- <ul class="tabs" data-persist="true">
			            <li><a href="#view1">Jobs On Board</a></li>
			            <li><a href="#view2">Profiles</a></li>
			            <li><a href="#view3">Tasks</a></li>
			            <li><a href="#view4">Events</a></li>
			        </ul> -->
							<div class="tabcontents">

								<div id="view3">
									<h3>
										<strong>My Tasks</strong>
									</h3>
									<table class="table table-striped">
										<tr>
											<th>Task ID</th>
											<th>Task Name</th>
											<th>Task Details</th>
											<th>Task Status</th>
											<th>Actions</th>
										</tr>
										<s:iterator value="tasks" status="status">
											<tr>
												<td><s:property value="%{#status.count}" /></td>
												<td><s:property value="taskName" /></td>
												<td><s:property value="taskDetails" /></td>
												<td><s:property value="taskStatus" /></td>
												<td>
													<div class="btn-group">
														<a href="viewProfile" class="btn"><i
															class="icon icon-eye-open"></i>View</a> <a href="editProfile"
															class="btn"><i class="icon icon-edit"></i>Edit</a> <a
															href="deleteProfile" class="btn btn-danger"><i
															class="icon icon-trash"></i>Delete</a>
													</div>
												</td>
											</tr>
										</s:iterator>
										<hr />
									</table>
								</div>



							</div>
						</div>


					</div>
				</div>
			</div>
			<!-- 4th Box Ends here -->
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

