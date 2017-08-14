<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>


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


	        <form action="deleteProfile" method="post">
	        
	        <div class="page-header">
		        <h1>Delete Profile <s:text name="profile.name"></s:text> </h1>
	        </div>
	        
                <p>
                    Are You Sure You Want To Delete Profile ID : ${profile.id}?
                </p>
                <s:hidden name="profile.id"></s:hidden>
                <div class="btn-group">
                    <input type="submit" value="Delete" class="btn btn-danger" />
                    <input type="button" value="Cancel" class="btn btn-success" onclick="window.location.href = '#'" />
                </div>
               </form>
            <br />
            <a href="${pageContext.request.contextPath}/agencydashboard" class="btn btn-info">
	            <i class="icon icon-arrow-left"></i> Back to Dashboard
            </a>
        </div><!--/row-->
    </div><!--/span-->
</div><!--/row--> 


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


	