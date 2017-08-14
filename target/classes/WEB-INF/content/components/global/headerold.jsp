<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<div id="logo">
	<img src="<s:url value="/images/mintapple-logo.png"/>" width="200px" height="200px" alt="headerimage" />
</div>
<div id="searchForm">
Welcome <%request.getSession().getAttribute("username");%>
      <a href="http://localhost:8080/sied">Noushad</a>      |
      <a href="<s:url value="/logout"/>">Logout</a>
<s:form action="#" style="display:inline-block"><span>Search <i class="icon-search"></i><s:textfield name="textToSearch" size="20" /><s:submit action="textSearch" key="button.label.submit" cssClass="btn btn-info" /></span></s:form>  
</div>

<!-- 
<div id="banner">
  
  <div id="breadcrumbs">
  <!-- 
    <div class="xright">
      Welcome <% request.getSession().getAttribute("username");%>
      <a href="http://localhost:8080/sied">Noushad</a>      |
      <a href="<s:url value="/logout"/>">Logout</a>
    </div>
	<div align="left">
    <a href="" id="bannerRight" >
      <img src="<s:url value="/images/mintapple-logo.png"/>" width="200px" height="200px" alt="headerimage" />
    </a></div>
   
  <div id="headeractions">
		<s:form action="#" style="display:inline-block"><span><i class="icon-search"></i><s:textfield name="textToSearch" size="20" /><s:submit action="textSearch" key="button.label.submit" cssClass="btn btn-info" /></span></s:form>    
		
		<!-- <input type="text" size="10"><a href="${pageContext.request.contextPath}/textSearch" class="btn btn-info">Search</a> - ->		
		            
          <hr /> 
  </div>
  </div>  
   
</div>

   -->
