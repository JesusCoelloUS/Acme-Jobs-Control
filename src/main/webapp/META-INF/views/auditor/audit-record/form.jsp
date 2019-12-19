<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<jstl:if test="${canBeUpdated || command == 'update'}">
		<jstl:set var="option" value="false"/>
	</jstl:if>
	<jstl:if test="${command == 'show' && !canBeUpdated}">
		<jstl:set var="option" value="true"/>
	</jstl:if>

	<acme:form-textbox code="auditor.audit-record.form.label.title" path="title" readonly="${option}"/>
	<jstl:choose>
		<jstl:when test="${command == 'create' || command == 'update' || (command == 'show' && canBeUpdated)}">
			<acme:form-select code="auditor.audit-record.form.label.status" path="status">
				<acme:form-option code="auditor.audit-record.form.label.draft" value="DRAFT"/>
				<acme:form-option code="auditor.audit-record.form.label.published" value="PUBLISHED"/>
			</acme:form-select>
			
		</jstl:when>
		<jstl:otherwise>
			<acme:form-textbox code="auditor.audit-record.form.label.status" path="status" readonly="true"/>
		</jstl:otherwise>
	</jstl:choose>
	<jstl:if test="${command != 'create'}">
	<acme:form-moment code="auditor.audit-record.form.label.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>
	<acme:form-textarea code="auditor.audit-record.form.label.body" path="body"/>
	<jstl:if test="${command == 'create' || command == 'update' || (command == 'show' && canBeUpdated)}">
	<acme:form-select code="auditor.audit-record.form.label.finalMode" path="finalMode">
		<acme:form-option code="auditor.audit-record.form.label.false" value="false"/>
		<acme:form-option code="auditor.audit-record.form.label.true" value="true"/>
	</acme:form-select>
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}" code="auditor.audit-record.form.button.create" action="/auditor/audit-record/create?jobId=${jobId}"/>
	<acme:form-submit test="${command == 'show' && !finalMode}" code="auditor.audit-record.form.button.update" action="/auditor/audit-record/update"/>
	<acme:form-submit test="${command == 'update'}" code="auditor.audit-record.form.button.update" action="/auditor/audit-record/update"/>
  	<acme:form-return code="auditor.audit-record.form.button.return"/>
</acme:form>
