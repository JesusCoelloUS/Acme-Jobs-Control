<%@tag language="java" body-content="empty"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
 
<%@attribute name="code" required="true" type="java.lang.String"%>
<%@attribute name="action" required="false" type="java.lang.String"%>
<%@attribute name="id" required="false" type="java.lang.String"%>


<jstl:if test="${action != null}">
	<button type="button" class="btn btn-default" id="${id}" onclick="javascript: window.location.replace('${action}')">
		<acme:message code="${code}"/>
	</button>
</jstl:if>
<jstl:if test="${action == null}">
	<button type="button" class="btn btn-default" id="${id}">
		<acme:message code="${code}"/>
	</button>
</jstl:if>
