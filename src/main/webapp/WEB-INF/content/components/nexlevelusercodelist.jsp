<%@ taglib prefix="s" uri="/struts-tags"%>
<s:hidden name="selectedCityName" id="selectedCityName" value="something"/>
<s:if test="userNextLevel != null">
	<s:select name="userNext" id="userNextID" list="userNextLevel"></s:select>
</s:if>