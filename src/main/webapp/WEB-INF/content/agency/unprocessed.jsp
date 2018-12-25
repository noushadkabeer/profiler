<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html>
<head>
    <sj:head/>
</head>
<body>
<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Unprocessed Profiles
        <small>Profile Management</small> 
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">Unprocessed</a></li>
        <li class="active">Profiles</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
      <div class="box">
        <div class="box-header with-border">
          <h3 class="box-title"> Unprocessed Profiles</h3>

          <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
              <i class="fa fa-minus"></i></button>
            <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
              <i class="fa fa-times"></i></button>
          </div>
        </div>
        <div class="box-body">
         
 

	<div>
	<div>
			<form action="agencydashboard" method="post" name="paginationForm"> 
     			<%@ include file="../common/pagination.jsp" %>
   			</form>
			</div>
		<div class="row-fluid">
			<div style="width: 850px; margin: 5px; padding: 60px 0 400px;">
			       <!-- <ul class="tabs" data-persist="true">
			            <li><a href="#view1">Jobs On Board</a></li>
			            <li><a href="#view2">Profiles</a></li>
			            <li><a href="#view3">Tasks</a></li>
			            <li><a href="#view4">Events</a></li>
			        </ul> --> 
			        <sj:accordion id="accordion" autoHeight="false" collapsible="true" cssClass="mashups_record"> 
					    <s:iterator value="unprocessedProfiles" var="dbpedia"> 
					        <sj:accordionItem title="Profile - %{#dbpedia.name}"> 
					            <hr/> 
					            <p align="justify" class="agrovoc_record">
					            <div class="btn-group">
										    <button id='<s:property value="dbpedia.id"/>' class="btn btn-primary popModal_ex" data-popmodal-bind="#content_blob" data-id='<s:property value="#dbpedia.id"/>'>Preview</button>
										 	<s:url id="delete" action="deleteProfileConfirm">
			       		   						<s:param name="id" value="jobId"/>
			       							</s:url> <s:a href="%{delete}" cssClass="btn btn-danger"><i
											class="icon icon-trash"></i>Delete</s:a>																			 
									</div> 
					                 <s:form action="setUpForInsertOrUpdateUPProfile">  <s:hidden name="upProfile.id" value="%{#dbpedia.id}" />  
							         <s:label value="Name"/><s:textfield name="upProfile.name" value="%{#dbpedia.name}" size="30" placeholder="%{getText('label.name')}"/>  
							         <s:label value="Experience"/><s:textfield name="upProfile.experience" value="%{#dbpedia.experience}" size="30" placeholder="%{getText('label.experience')}" />
							         <s:label value="Education"/><s:textfield name="upProfile.education" value="%{#dbpedia.education}" size="30" placeholder="%{getText('label.education')}"/>
									 <s:label value="Skills"/><s:textfield name="upProfile.skills" value="%{#dbpedia.skills}" size="30" placeholder="%{getText('label.skills')}"/>
									 <s:label value="Interests"/><s:textfield name="upProfile.interests" value="%{#dbpedia.interests}" size="30" placeholder="%{getText('label.interests')}"/> 
									 <s:label value="Address"/><s:textfield name="upProfile.address" value="%{#dbpedia.address}" size="30" placeholder="%{getText('label.address')}"/>  
									 <s:label value="Location"/><s:textfield name="upProfile.location" value="%{#dbpedia.location}" size="30" placeholder="%{getText('label.location')}"/> 
									 <s:label value="Summary"/><s:textfield name="upProfile.resumeSummary" value="%{#dbpedia.resumeSummary}" size="30" placeholder="%{getText('label.resumeSummary')}"/> 
									 <s:submit cssClass="btn btn-primary popModal_ex"></s:submit>
									 
									</s:form>
					        </sj:accordionItem> 
					    </s:iterator>	
					</sj:accordion>
								        
			        	        
			        
			     <!--    <div class="tabcontents">
			            <div id="view1">	
			            
			            <s:iterator value="unprocessedProfiles" var="doe">
							<strong>Profile :: <s:property value="name" /></strong>
							<div id="loadedConForm" class="loadedConForm">
								<div id="loadedCon" class="loadedCon"><span>Expand</span></div>											
								<div class="loadedConDiv">
								<div class="btn-group">
										    <button id='<s:property value="id"/>' class="btn btn-primary popModal_ex" data-popmodal-bind="#content_blob" data-id='<s:property value="attachments.id"/>'>Preview</button>
										 	<s:url id="delete" action="deleteJobConfirm">
			       		   						<s:param name="id" value="jobId"/>
			       							</s:url> <s:a href="%{delete}" cssClass="btn btn-danger"><i
											class="icon icon-trash"></i>Delete</s:a>																			 
									</div> 
									 <form action="setUpForInsertOrUpdateProfile">
							         <s:text name="label.name" /> <s:textfield name="name" size="30" /><br/> 
							         <s:text name="label.experience" /> <s:textfield name="profile.experience" size="30" /><br/>
							         <s:text name="label.education" /> <s:textfield name="profile.education" size="30" /><br/>
									 <s:text name="label.skills" /> <s:textfield name="profile.skills" size="30" /><br/>
									 <s:text name="label.interests" /> <s:textfield name="profile.interests" size="30" /> <br/>
									 <s:text name="label.address" />  <s:textfield name="profile.address" size="30" />  <br/>
									 <s:text name="label.resumeSummary" />  <s:textfield name="profile.resumeSummary" size="30" /> <br/>
									 <button class="btn btn-primary popModal_ex" type="submit" title="Submit">Submit</button>
									 
									</form>
								</div>							   
							</div>								
							 <br/><hr/>
					</s:iterator>
			-->
			            
			            <!-- 
			            
			            <table class="table table-striped">
					<tr>
						<th width="30px">Profile ID</th>
						
						<th>Actions</th>
					</tr>
					<s:iterator value="unprocessedProfiles" var="doe">
						<tr>
							<td><s:property value="id" /><s:property value="name" /></td>
							<td>
								<div class="btn-group">
									<button id='<s:property value="id"/>' class="btn btn-primary popModal_ex" data-popmodal-bind="#content_blob" data-id='<s:property value="attachments.id"/>'>Pr</button>
									<a href="viewJob" class="btn"><i
										class="icon icon-eye-open"></i>View</a> 
										<s:url id="update" action="setUpForInsertOrUpdateJob">
		       		   						<s:param name="id" value="jobId"/>
		       							</s:url> <s:a href="%{update}" cssClass="btn"><i
										class="icon icon-edit"></i>Edit</s:a>
										<s:url id="delete" action="deleteJobConfirm">
		       		   						<s:param name="id" value="jobId"/>
		       							</s:url> <s:a href="%{delete}" cssClass="btn btn-danger"><i
										class="icon icon-trash"></i>Delete</s:a> 
										<div id="loadedConForm" class="loadedConForm">
											<div id="loadedCon" class="loadedCon"><span>Expand</span>
	    									</div>
											
											<div class="loadedConDiv">
										         <s:text name="label.name" /> <s:textfield name="profile.name" size="30" /><br/> 
										         <s:text name="label.experience" /> <s:textfield name="profile.experience" size="30" /><br/>
										         <s:text name="label.education" /> <s:textfield name="profile.education" size="30" /><br/>
												 <s:text name="label.skills" /> <s:textfield name="profile.skills" size="30" /><br/>
												 <s:text name="label.interests" /> <s:textfield name="profile.interests" size="30" /> <br/>
												 <s:text name="label.address" />  <s:textfield name="profile.address" size="30" /> 
												 <s:text name="label.resumeSummary" />  <s:textfield name="profile.resumeSummary" size="30" />
							
										    </div>
									    </div>																				 
								</div>
							</td>
						</tr>
					</s:iterator>
				</table>		    -->                         
			            </div>
			           
			            
			        </div>
			    </div>
			
			<!--/row-->
		</div>
		<!--/span-->
	</div>







        </div>
       
      </div>
      <!-- /.box -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
</body>
</html>
