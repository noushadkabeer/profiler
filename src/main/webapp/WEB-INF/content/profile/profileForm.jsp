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
        <li><a href="#">Profile</a></li>
        <li class="active"><s:if test="!profile.id.equals('')">
					Edit Profile 
				</s:if>
				<s:else>
					New Profile
				</s:else></li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
      <div class="box">
        <div class="box-header with-border">
          <h3 class="box-title"><s:if test="!profile.id.equals('')">
					Edit Profile 
				</s:if>
				<s:else>
					New Profile
				</s:else></h3>

          <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
              <i class="fa fa-minus"></i></button>
            <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
              <i class="fa fa-times"></i></button>
          </div>
        </div>
        <div class="box-body">
        				
				<s:fielderror cssClass="alert alert-error" />
				
				<s:form>
					<table class="table table-striped">
					<s:if test="!profile.id.equals('')">
						<tr>
							<td class="tdLabel"><s:text name="label.id" /></td>
							<td><s:textfield name="profile.id" size="30" readonly="true" /></td>
						</tr>
					</s:if>
						<tr>
							<td class="tdLabel"><s:text name="label.name" /></td>
							<td><s:textfield name="profile.name" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.experience" /></td>
							<td><s:textfield name="profile.experience" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.education" /></td>
							<td><s:textfield name="profile.education" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.skills" /></td>
							<td><s:textfield name="profile.skills" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.interests" /></td>
							<td><s:textfield name="profile.interests" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.location" /></td>
							<td><s:textfield name="profile.location" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.address" /></td>
							<td><s:textfield name="profile.address" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.resumeSummary" /></td>
							<td><s:textfield name="profile.resumeSummary" size="30" /></td>
						</tr>		
										
						<s:if test="!profile.id.equalsIgnoreCase('')">
					      <tr>
								<td>Attach resume</td>
								<td>
								<s:url id="attachResume" action="attachResume">
			       		   						<s:param name="profile.id" value="profile.id"/>
			       							</s:url> <s:a href="%{attachResume}" cssClass="btn btn-info"><i
											class="icon icon-briefcase"></i>Attach Resume</s:a> 														
								
								</td>
							</tr>
					   </s:if> 
						
					</table>		
					<div align="center">			
					<s:submit action="insertOrUpdateProfile" key="button.label.submit" cssClass="btn btn-info" /> 
					<s:reset key="button.label.cancel" cssClass="btn btn-info" />
					
					</div>
				</s:form>
				
				<!-- <s:form id="formUp2" action="uploadDoc.action" namespace="/"
								method="POST" enctype="multipart/form-data">
								<s:file name="fileUpload" label="Select a File to upload" size="40" />
								<!- -<s:submit value="submit" name="submit" /> - ->
								<s:submit id="formSubmit1" formIds="formUp2"
									value="Upload Submit" indicator="indicator" button="true" />
							</s:form>
				
				<!-- 
				<div style="width:400px;height:300px;" align="center"><form action="/file-upload" class="dropzone"
						id="my-awesome-dropzone"></form></div> -->
<a href="${pageContext.request.contextPath}/agencydashboard" class="btn btn-info">
		        <i class="icon icon-arrow-left"></i> Back to Dashboard
	        </a>
			

	




        </div>
        <!-- /.box-body -->
        <div class="box-footer">
          Footer
        </div>
        <!-- /.box-footer-->
      </div>
      <!-- /.box -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->


