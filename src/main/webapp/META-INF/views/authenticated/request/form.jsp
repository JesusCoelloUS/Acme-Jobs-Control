<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not request any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.request.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="authenticated.request.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.request.form.label.creationMoment" path="creationMoment"/>
	<acme:form-moment code="authenticated.request.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.request.form.label.text" path="text"/>
	<acme:form-money code="authenticated.request.form.label.reward" path="reward"/>
		
	<%--Create request --%>
	
	<acme:form>
		<acme:form-textbox code="authenticated.request.form.label.title" path="title"/>
		<jstl:if test="${command != 'create'}">
			<acme:form-moment
				code = "authenticated.request.form.label.creationMoment"
				path = "creationMoment"
				readonly= "true"/>
		</jstl:if>
		
	
		<acme:form-submit test="${command=='create'}"
			code = "authenticated.request.form.button.create"
			action="/authenticated/request/create"/>
			
		<acme:form-return
			code = "authenticated.request.form.button.return"/>
			
	</acme:form>

</acme:form>
