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

	<jstl:if test="${canBeUpdated || command == 'update'}">
		<jstl:set var="option" value="false"/>
	</jstl:if>
	<jstl:if test="${command == 'show' && !canBeUpdated}">
		<jstl:set var="option" value="true"/>
	</jstl:if>

	<acme:form-textbox code="employer.job.form.label.reference" path="reference" placeholder="EMPx-JOBx" readonly="${option}"/>
	<jstl:choose>
		<jstl:when test="${command == 'update' || (command == 'show' && canBeUpdated)}">
			<acme:form-select code="employer.job.form.label.status" path="status">
				<acme:form-option code="employer.job.form.label.status.draft" value="DRAFT"/>
				<acme:form-option code="employer.job.form.label.status.published" value="PUBLISHED"/>
			</acme:form-select>
			
		</jstl:when>
		<jstl:otherwise>
			<acme:form-textbox code="employer.job.form.label.status" path="status" readonly="true"/>
		</jstl:otherwise>
	</jstl:choose>
	<acme:form-textbox code="employer.job.form.label.title" path="title" readonly="${option}"/>
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline"  readonly="${option}"/>
	<acme:form-textarea code="employer.job.form.label.description" path="description" readonly="${option}"/>
	<acme:form-money code="employer.job.form.label.salary" path="salary" readonly="${option}"/>
	<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo" readonly="${option}"/>
	<jstl:if test="${command == 'update' || (command == 'show' && canBeUpdated)}">
	<acme:form-select code="employer.job.form.label.finalMode" path="finalMode">
		<acme:form-option code="employer.job.form.label.finalMode.false" value="false"/>
		<acme:form-option code="employer.job.form.label.finalMode.true" value="true"/>
	</acme:form-select>
	</jstl:if>
	
	<jstl:if test="${command != 'create' }">
		<acme:button code="employer.job.form.button.duties" action="/acme-jobs/employer/duty/list?id=${id}"/>
		<acme:button code="employer.job.form.button.duty.create" action="/acme-jobs/employer/duty/create?id=${id}"/>
		<acme:button code="employer.job.form.button.auditRecords" action="/acme-jobs/authenticated/audit-record/list?id=${id}"/>
		<jstl:if test="${hasADaring}">
  			<acme:button code="employer.job.form.button.daring" action="/acme-jobs/employer/daring/show?id=${daringId}"/>
  		</jstl:if>
  		<jstl:if test="${!hasADaring && canBeUpdated}">
  			<acme:button code="employer.job.form.button.daring.create" action="/acme-jobs/employer/daring/create?id=${id}"/>
  		</jstl:if>
	</jstl:if>
	
	<acme:form-submit test="${command == 'show' && canBeUpdated}" code="employer.job.form.button.update" action="/employer/job/update"/>
	<acme:form-submit test="${command == 'show' && canBeDeleted}" code="employer.job.form.button.delete" action="/employer/job/delete"/>
	<acme:form-submit test="${command == 'create' }" code="employer.job.form.button.create" action="/employer/job/create"/>
	<acme:form-submit test="${command == 'update' }" code="employer.job.form.button.update" action="/employer/job/update"/>
	<acme:form-submit test="${command == 'delete'}" code="employer.job.form.button.delete" action="/employer/job/delete"/>
  	<acme:form-return code="employer.job.form.button.return"/>
</acme:form>