<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>


 <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        My Jobs 
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">My Dashboard</a></li>
        <li class="active">Job</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
      <div class="box">
        <div class="box-header with-border">
          <h3 class="box-title">View Job</h3>

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
                <h1>Job ${job.jobId}</h1>
            </div>
            <table class="table table-striped">
               <tr>
                    <td class="span3">Title</td>
                    <td class="span9"><s:property value="job.jobTitle"/></td>
                </tr>
                <tr>
                    <td class="span3">Job ID</td>
                    <td class="span9"><s:property value="job.jobId"/></td>
                </tr>                
                <tr>
                    <td class="span3">Experience</td>
                    <td class="span9"><s:property value="job.jobExperience"/></td>
                </tr>
                <tr>
                    <td class="span3">Location</td>
                    <td class="span9"><s:property value="job.location"/></td>
                </tr>
                <tr>
                    <td class="span3">Company</td>
                    <td class="span9"><s:property value="job.company"/></td>
                </tr>
            </table>
	        <a href="${pageContext.request.contextPath}/agencydashboard" class="btn btn-info">
		        <i class="icon icon-arrow-left"></i> Back to Dashboard
	        </a>
        </div><!--/row-->
    </div><!--/span-->
</div><!--/row-->



     

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->


	
	