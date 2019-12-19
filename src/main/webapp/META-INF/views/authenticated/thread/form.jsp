<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not thread any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.thread.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.thread.form.label.moment" path="moment"/>

	<acme:button code="authenticated.thread.form.button.messages" action="/acme-jobs/authenticated/thread-message/list?id=${id}"/>
	<acme:button code="authenticated.thread.form.button.message.create" action="/acme-jobs/authenticated/thread-message/create?threadId=${id}"/>	
  	<acme:form-return code="authenticated.thread.form.button.return"/>
</acme:form>