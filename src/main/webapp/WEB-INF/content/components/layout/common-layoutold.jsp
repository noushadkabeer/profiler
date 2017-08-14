<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title><!-- 
<style type="text/css" media="all">
      @import url("<s:url value="/css/dsp/maven-base.css"/>"); @import url("<s:url value="/css/dsp/maven-theme.css"/>"); @import url("<s:url value="/css/dsp/site.css"/>"); @import
      url("<s:url value="/css/dsp/screen.css"/>");
    </style>
    -->
    <link href="<s:url value="/css/mastyles.css"/>"  rel="stylesheet" type="text/css" /> 
    <link href="<s:url value="/css/bootstrap.css"/>"  rel="stylesheet" type="text/css" />  
     <link href="<s:url value="/css/bootstrap-responsive.css"/>" rel="stylesheet" type="text/css" />
     <link href="<s:url value="/css/tabcontent.css"/>" rel="stylesheet" type="text/css" />    
    <!-- <link href="<s:url value="/css/dsp/print.css"/>" rel="stylesheet" type="text/css" media="print" />-->
     <link href="<s:url value="/css/dropzone.css"/>" rel="stylesheet" type="text/css"/>
    
    <script type="text/javascript" src="<s:url value="/js/jquery-1.11.0.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/js/tabcontent.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/js/dropzone.js"/>"></script>
    
    <style type="text/css">
    	#container
		{
			margin: 0 auto;
			width: 100%;
			background: #fff;
		}
		#header
		{
			background: #fff; 
			padding: 20px;
		}
		#welcomemsg
		{
			float:right;
			padding: 0px;
		}
		
		#logo
		{
			width:200px;
			margin:0 2%;
			float:left;
		}
		
		#searchForm
		{
			width:200px;
			float:right;
			padding:0px;
			margin: 0 2% 0 0;
		}
		
		#header h1 { margin: 100px; }
		
		#navigation
		{
			float: left;
			width: 20%;
			background: #fff;
		}
		#navigation ul
		{
			margin: 0;
			padding: 0;
		}
		
		#navigation ul li
		{
			list-style-type: none;
			display: inline;
		}
		
		#navigation li a
		{
			display: block;
			float: left;
			padding: 5px 10px;
			color: #fff;
			text-decoration: none;
			border-right: 1px solid #fff;
		}
		
		#navigation li a:hover { background: #383; }
		
		
#content-container1
{
	float: left;
	width: 100%;
}

#content-container2
{
	float: left;
	width: 100%;
}

#section-navigation
{
	float: left;
	width: 10%;
	padding: 20px 0;
	margin: 0 2%;
	display: inline;
}

#section-navigation ul
{
	margin: 0;
	padding: 0;
}

#section-navigation ul li
{
	margin: 0 0 1em;
	padding: 0;
	list-style-type: none;
}

#content
{
	float: left;
	width: 78%;
	padding: 20px 0;
	margin: 0 0 0 2%;
}

#content h2 { margin: 0; }

#footer
{
	clear: both;
	background: #ccc;
	text-align: right;
	padding: 10px;
	height: 1%;
}
		
    </style>
    
    
</head>
<body>

<div id="container">
	<div id="header">			
		<tiles:insertAttribute name="header" />		
	</div>
	<!-- 
	<div id="navigation">
		<ul>
			<li><a href="#">My Files</a></li>
			<li><a href="#">Events</a></li>
		</ul>
	</div> -->
	
	<div id="content-container1">
	
		<div id="content-container2">
			<div id="section-navigation"> 
				<ul>
					<li><a href="#" class="btn btn-mini">My Files</a></li>
					<li><a href="#" class="btn btn-mini">My Events</a></li>
				</ul>
			</div>
			<div id="content">
				<tiles:insertAttribute name="body" />
			</div>
			
			<div id="footer">
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
	</div>

</div>

	<!-- 
	<tile s:insertAttribute name="header" />
        <tile s:insertAttribute name="menu" />
        <tile s:insertAttribute name="body" />
        <tile s:insertAttribute name="footer" />
         -->
        
   <!--      
<table border="0" cellpadding="0" cellspacing="0" align="center" width="1000">
    <tr>
        <td align="right" height="30" colspan="2"><tile s:insertAttribute name="header" />
        </td>
    </tr>    
    <tr>
        <td height="100" width="200"><tile s:insertAttribute name="menu" /></td>
        <td width="650"><tile s:insertAttribute name="body" /></td>
    </tr>
    <tr>
        <td height="30" colspan="2"><tile s:insertAttribute name="footer" />
        </td>
    </tr>
</table> -->
</body>