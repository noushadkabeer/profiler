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
        Contact Candidate
        <small>Email Content</small> 
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">Email</a></li>
        <li class="active">Profiles</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
      <div class="box">
        <div class="box-header with-border">
          <h3 class="box-title"> Contact Candidate</h3>

          <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
              <i class="fa fa-minus"></i></button>
            <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
              <i class="fa fa-times"></i></button>
          </div>
        </div>
        <div class="box-body">
         
		<div class="row-fluid">
			<div style="width: 850px; margin: 5px; padding: 60px 0 400px;">
			

	<s:form>
 <div class="social-auth-links text-center">
   <b><s:actionerror /><br/><s:actionmessage /></b> 
    </div>
		<table class="borderAll">
			<tr>
				<td class="tdLabel"><s:text name="From" /></td>
				<td><s:textfield name="from" size="24"
						/></td>
			<tr />
			<tr>
				<td class="tdLabel"><s:text name="To" /></td>
				<td><s:textfield name="to" size="24"/></td>
			<tr />
			<tr>
				<td class="tdLabel"><s:text name="CC" /></td>
				<td><s:textfield name="cc" size="24"/></td>
			<tr />
			<tr>
				<td class="tdLabel"><s:text name="BCC" /></td>
				<td><s:textfield name="bcc" size="24"/></td>
			<tr />
			<tr>
				<td class="tdLabel"><s:text name="Subject" /></td>
				<td><s:textfield name="subject" size="24"/></td>
			<tr />
			<tr>
				<td class="tdLabel"><s:text name="Content" /></td>
				<td><s:textarea name="content" cols="30" rows="6"/></td>
			<tr />
				
		</table>
		<table><tr>
				<td align="left" style="font: bold; color: red"><s:fielderror />
					<s:actionerror /> <s:actionmessage /></td>
			</tr></table>

		<table >
			<tr>
				<td><s:submit action="emailCandidate"
						key="button.label.submit" cssClass="butStnd" /></td>
				<td><s:reset key="button.label.cancel" cssClass="butStnd" /></td>
			<tr>
		</table>
	</s:form>

	                
			</div>
			           
			            
			        </div>
			    </div>
			
			<!--/row-->
		</div>
		<!--/span-->
	</div>

      <!-- /.box -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
</body>
</html>
