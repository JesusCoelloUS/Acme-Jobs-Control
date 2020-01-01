<%@tag language="java" body-content="empty"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<%@attribute name="path" required="true" type="java.lang.String"%>
<%@attribute name="code" required="true" type="java.lang.String"%>
<%@attribute name="group" required="false" type="java.lang.String"%>

<div id="${group}" class="form-group">
	<label>
		<acme:message code="${code}"/>
	</label>		
   	<input id="${path}" type="text" class="form-control"/>
</div>