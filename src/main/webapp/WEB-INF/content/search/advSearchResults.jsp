<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Advanced Search
        <small>Profile Search</small> 
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">My Dashboard</a></li>
        <li class="active">Advanced Search</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
      <div class="box">
        <div class="box-header with-border">
          <h3 class="box-title">Search Results</h3>

          <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
              <i class="fa fa-minus"></i></button>
            <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
              <i class="fa fa-times"></i></button>
          </div>
        </div>
        <div class="box-body">
         
 

	<div>
		<div class="row-fluid">
			<div class="span12">
				<div class="quickMenu" align="right"> <a href="setUpForInsertOrUpdateProfile" class="btn btn-primary">
					<i class="icon icon-file"></i>Upload Resume</a> <a href="setUpForInsertOrUpdateJob"	class="btn btn-primary">
					<i class="icon icon-file"></i>Add Job</a> <a href="setUpForInsertOrUpdateEvent" class="btn btn-primary">
					<i class="icon icon-file"></i>Add Event</a> <a href="setUpForInsertOrUpdateTask" class="btn btn-primary">
					<i class="icon icon-file"></i>Add Task</a> 
				</div>				
			</div> 
			<div>
			<s:form action="advancedSearch" method="post" name="paginationForm"> 
     			<%@ include file="../common/pagination.jsp" %>
     			<s:textfield name="profile.name" size="30" />
     			<s:property value="profile.name"/>
   			</s:form>
			</div>
			
				<div style="width: 850px; margin: 5px; padding: 60px 0 400px;">
			       <!-- <ul class="tabs" data-persist="true">
			            <li><a href="#view1">Jobs On Board</a></li>
			            <li><a href="#view2">Profiles</a></li>
			            <li><a href="#view3">Tasks</a></li>
			            <li><a href="#view4">Events</a></li>
			        </ul> --> 
			        <div class="tabcontents">
			            
			            <div id="view1">     
			            <h3><strong>My Profiles</strong>  </h3>
			            <table class="table table-striped">
					<tr> <th>Sl.no</th>
						<th>Name</th>
						<th>Experience</th>
						<th>Education</th>
						<th>Skills</th><!-- 
						<th>Interests</th>
						<th>Address</th> -->
						<th>Attachments</th>
						<th>Actions</th>
					</tr>
					<s:iterator value="profileList" var="doe" status="status">
						<tr> <td><s:property value="%{#status.count}" /></td>
							<td><s:property value="#doe.name" /></td>
							<td><s:property value="#doe.experience" /></td>
							<td><s:property value="#doe.education" /></td>
							<td><s:property value="#doe.skills" /></td><!-- 
							<td><s:property value="#doe.interests" /></td> 
							<td><s:property value="#doe.address" /></td>  -->
							<td><a href='<s:property value="#doe.attachments.downloadURL"/>'>
								<s:property value="#doe.attachments.name"/></a>
							</td>
							<td> 
								<div class="btn-group"> 
								<s:url id="renderProfile" action="pdfpreview">
		       		   						<s:param name="profileId" value="attachments.id"/>
		       							</s:url>  		       							 
										<button id='<s:property value="id"/>' class="btn btn-primary popModal_ex" data-popmodal-bind="#content_blob" data-id='<s:property value="attachments.id"/>'>P</button>
								<s:url id="viewProfile" action="viewProfile">
		       		   						<s:param name="profile.id" value="id"/>
		       							</s:url> <s:a href="%{viewProfile}" cssClass="btn"><i
										class="icon icon-eye-open"></i>V</s:a> 
										<s:url id="update" action="setUpForInsertOrUpdateProfile">
		       		   						<s:param name="profile.id" value="id"/>
		       							</s:url> <s:a href="%{update}" cssClass="btn"><i
										class="icon icon-edit"></i>E</s:a>
										<s:url id="delete" action="deleteProfileConfirm">
		       		   						<s:param name="profile.id" value="id"/>
		       							</s:url> <s:a href="%{delete}" cssClass="btn btn-danger"><i
										class="icon icon-trash"></i>D</s:a> 										
								</div>
							</td>
						</tr>
					</s:iterator>
					<hr/>
				</table>        
			            </div>
			            
			           
			            
			        </div>
			    </div>
			
			<!--/row-->
		</div>
		<!--/span-->
	</div>







        </div>
        <!-- /.box-body --> 
      <!-- /.box -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

