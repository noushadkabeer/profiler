
<%@ taglib prefix="s" uri="/struts-tags"%>	
<!-- 
<div id="leftColumn">Quick Links
	<div id="navcolumn"> 
			<!-- <img class="micon-mydashicon" src="../images/cssicons/sharing.jpg"></img><s:a href="identifyAndTakeHome.action">Dashboard</s:a> <br/> - ->
			<i class="micon-filesicon"></i><s:a href="getAllCustomers.action">My Files</s:a><br/>
			<i class="micon-sharingicon"></i> <s:a href="myProfile.action">Sharing</s:a><br/>	
			<i class="micon-eventicon"></i> <s:a href="myProfile.action">Event</s:a><br/>  
	</div>
</div> -->


<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="<s:url value="/images/user2-160x160.jpg"/>" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Nishad Kabeer</p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
      <!-- search form -->
      <form action="textSearch" method="get" class="sidebar-form">
        <div class="input-group">
          <s:textfield name="textToSearch" cssClass="form-control" placeholder="Search..."/>
              <span class="input-group-btn">
                <button type="submit"  id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="header">MAIN NAVIGATION</li>
        <!--<li class="treeview active">
          <a href="agencydashboard">
            <i class="fa fa-dashboard"></i> <span>My Dashboard</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
           <ul class="treeview-menu">
            <li><a href="../../index.html"><i class="fa fa-circle-o"></i> Dashboard v1</a></li>
            <li><a href="../../index2.html"><i class="fa fa-circle-o"></i> Dashboard v2</a></li>
          </ul>
        </li>-->
        <li><a href="agencydashboard"><i class="fa fa-dashboard"></i> My Dashboard</a>
        <li><a href="advancedSearch"><i class="fa fa-circle-o text-green"></i> Advanced Search</a>

         </li>
         <li><a href="setUpForInsertOrUpdateJob"><i class="fa fa-circle-o text-yellow"></i> Add Job</a>
         </li>
          
      <!-- <li>
          <a href="setUpForInsertOrUpdateJob">
            <i class="fa fa-th"></i> <span>Add Job</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-green">Hot</small>
            </span>
          </a>
        </li>
              
        <li class="treeview">
          <a href="advancedSearch">
            <i class="fa fa-edit"></i> <span>Advanced Search</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="../forms/general.html"><i class="fa fa-circle-o"></i> General Elements</a></li>
            <li><a href="../forms/advanced.html"><i class="fa fa-circle-o"></i> Advanced Elements</a></li>
            <li><a href="../forms/editors.html"><i class="fa fa-circle-o"></i> Editors</a></li>
          </ul>
        </li>
         
         <li>
          <a href="../calendar.html">
            <i class="fa fa-calendar"></i> <span>Calendar</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-red">3</small>
              <small class="label pull-right bg-blue">17</small>
            </span>
          </a>
        </li>
        <li>
          <a href="../mailbox/mailbox.html">
            <i class="fa fa-envelope"></i> <span>Mailbox</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-yellow">12</small>
              <small class="label pull-right bg-green">16</small>
              <small class="label pull-right bg-red">5</small>
            </span>
          </a>
        </li> -->
        
        <li><s:a href="setUpForInsertOrUpdateProfile.action"><i class="fa fa-book"></i> <span>Upload Resume</span></s:a></li>
         <li><s:a href="bulkUploadProcessor.action"><i class="fa fa-circle-o text-red"></i> <span>Bulk Upload</span></s:a></li>
         <li><s:a href="bulkUploadProcessor.action"><i class="fa fa-circle-o text-aqua"></i> <span>Reports</span></s:a></li>
       <!-- <li class="header">LABELS</li>
        <li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a></li>
        <li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
        <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li> --> 
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>