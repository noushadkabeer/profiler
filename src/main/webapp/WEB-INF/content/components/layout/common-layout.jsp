<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<!-- 
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
<link href="<s:url value="/css/adminlte/bootstrap.min.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<s:url value="/css/bootstrap-responsive.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<s:url value="/css/AdminLTE.min.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<s:url value="/css/font-awesome.min.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<s:url value="/css/ionicons.min.css"/>" rel="stylesheet"
	type="text/css" />
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link href="<s:url value="/css/_all-skins.min.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<s:url value="/css/customstyles.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<s:url value="/css/jquery-ui.min.css"/>" rel="stylesheet"
	type="text/css" />
	

     <link href="<s:url value="/css/dropzone.css"/>" rel="stylesheet" type="text/css"/>
     <link rel="shortcut icon" href="<s:url value="/images/favicon/favicon.png"/>" type="image/x-icon">
</head>
<body>

<div class="hold-transition skin-blue sidebar-mini">
	<div>
		<tiles:insertAttribute name="header" />
	</div>
	<tiles:insertAttribute name="menu" />
	<div>
		<tiles:insertAttribute name="body" />
	</div>
	<tiles:insertAttribute name="footer" />
</div>


<div id="pviewer" class="pviewer">
<button id="closeButton">X</button>
<iframe id="preview" name="pdfpreview" width="850" height="625" scrolling="no"   frameborder="0" src="#">
</iframe>
</div>
<script type="text/javascript">  
/*function loadPreview() {   
 var previewDiv = document.getElementById("pviewer");  
 previewDiv.show();
 var previewURL = $('#renderProfile').val();
 alert(previewURL);
 iframe.src = previewURL;
 iframe.style.visibility="visible"; 
 $('#pviewer').show(); 
}  */
</script>



	<script src="<s:url value="/js/jquery-2.2.3.min.js"/>"></script>
	<script src="<s:url value="/js/jquery-ui.min.js"/>"></script>
	<script src="<s:url value="/js/bootstrap.min.js"/>"></script>
	<!-- SlimScroll -->
	<script src="<s:url value="/js/slimScroll/jquery.slimscroll.min.js"/>"></script>
	<!-- FastClick -->
	<script src="<s:url value="/js/fastclick/fastclick.js"/>"></script>
	<!-- AdminLTE App -->
	<script src="<s:url value="/js/adminlte/app.min.js"/>"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="<s:url value="/js/adminlte/demo.js"/>"></script>
    

    <script type="text/javascript" src="<s:url value="/js/tabcontent.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/js/dropzone.js"/>"></script>
	
  <script type="text/javascript" src="<s:url value="/js/chartjs272/Chart.min.js"/>"></script>
  <script type="text/javascript" src="<s:url value="/js/profileRcommon.js"/>"></script>
	
	 <script>
  $(document).ready( function() { 
    $( "#pviewer" ).draggable();
    $( "#preview" ).draggable();
    $('#pviewer').hide(); 
    
    $('#closeButton').on('click', function(e) {  
        $('#pviewer').hide(); 
    });
    $('button.popModal_ex').click(function() {
    	
    	var idaaa = null;
        idaaa = $(this).data('id');
        //alert("clicked2"+idaaa)
     //   var previewURL = $('#renderProfile').val();
        //$('#preview').attr('src', "pdfpreview?profileId="+idaaa);
       // $('#preview').attr('src', "pdfjs/viewer.jsp?file=44e7kJ2F.pdf"); 
       
       $('#preview').attr('src', "pdfpreview.action?profileId="+idaaa);
        $('#pviewer').show();
        return false;
    }); 
    /*
    $("#loadedCon").click(function () {
        $header = $(this);
        //getting the next element
        $content = $header.next();
        //open up the content needed - toggle the slide- if visible, slide up, if not slidedown.
        $content.slideToggle(500, function () {
            //execute this after slideToggle is done
            //change text of header based on visibility of content div
            $header.text(function () {
                //change text based on condition
                return $content.is(":visible") ? "Collapse" : "Expand";
            });
        });

    });
    */
    
    $("#loadedCon").accordion();
    $( "#accordion" ).accordion();
    
    
    
   
    
    
    
  } );
  
  
  
  function fnPagination(type,param){
	  alert("clicked "+document.getElementById("page_number").value + document.getElementById("page_size").value);
	  var oPage_number = document.getElementById("page_number");
	  var page_number = parseInt(oPage_number.value);
	  switch (type)
	  {
	  case 1://Next
	   oPage_number.value = (page_number+1);
	   break;
	  case 2://Last
	  		 oPage_number.value = param;
	   break;
	  case 3://Previous
	   oPage_number.value = (page_number-1);
	   break;
	  case 4://First
	   oPage_number.value = 1;
	   break;
	  case 5://Page size change
	   		oPage_number.value = 1;
	   break;
	  case 6://sort
	   var oSortColumn = document.getElementById("sortColumn");
	   var oSortOrder = document.getElementById("sortOrder");
	   if(oSortColumn.value == param && oSortOrder.value != "DESC") 
	    oSortOrder.value = "DESC";
	   else oSortOrder.value = "ASC";
	   oSortColumn.value = param;
	   break;
	  }
	  document.paginationForm.submit();
	 }
  </script>
</body>