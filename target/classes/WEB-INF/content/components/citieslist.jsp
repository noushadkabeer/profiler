<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="cities != null">
	<s:select name="cityName" onchange="javascript:populateUserCode();return false;" list="cities"></s:select>
</s:if>
