<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not thread-message any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.thread-message.form.label.title" path="title"/>
	<jstl:if test="${command != 'create'}">
	<acme:form-moment code="authenticated.thread-message.form.label.creationMoment" path="creationMoment"/>
	</jstl:if>
	<acme:form-textarea code="authenticated.thread-message.form.label.tags" path="tags"/>
	<acme:form-textarea code="authenticated.thread-message.form.label.body" path="body"/>

	<acme:form-submit test="${command == 'create'}" code="authenticated.thread-message.form.button.create" action="/authenticated/thread-message/create?threadId=${threadId}"/>	
  	<acme:form-return code="authenticated.thread-message.form.button.return"/>
</acme:form>
