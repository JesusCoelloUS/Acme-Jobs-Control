<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not job any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textarea code="employer.bisit.form.label.text" path="text"/>
	<acme:form-textbox code="employer.bisit.form.label.tracer" path="tracer"/>
	
	<acme:form-submit test="${command == 'create'}" code="employer.bisit.form.button.create" action="/employer/bisit/create?id=${id}"/>
	<acme:form-submit test="${command == 'show' && canBeUpdatedOrDeleted}" code="employer.bisit.form.button.update" action="/employer/bisit/update"/>
	<acme:form-submit test="${command == 'update'}" code="employer.bisit.form.button.update" action="/employer/bisit/update"/>
	<acme:form-submit test="${command == 'show' && canBeUpdatedOrDeleted}" code="employer.bisit.form.button.delete" action="/employer/bisit/delete"/>
	<acme:form-submit test="${command == 'delete'}" code="employer.bisit.form.button.delete" action="/employer/bisit/delete"/>
  	<acme:form-return code="employer.bisit.form.button.return"/>
</acme:form>