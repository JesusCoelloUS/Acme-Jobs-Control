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
	<acme:form-textbox code="worker.job.form.label.reference" path="reference"/>
	<acme:form-textbox code="worker.job.form.label.status" path="status"/>
	<acme:form-textbox code="worker.job.form.label.title" path="title"/>
	<acme:form-moment code="worker.job.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="worker.job.form.label.description" path="description"/>
	<acme:form-money code="worker.job.form.label.salary" path="salary"/>
	<acme:form-url code="worker.job.form.label.moreInfo" path="moreInfo"/>
	
	<acme:button code="worker.job.form.button.duties" action="/acme-jobs/worker/duty/list?id=${id}"/>
	<jstl:if test="${isActive}">
	<acme:button code="worker.job.form.button.application.create" action="/acme-jobs/worker/application/create?jobId=${id}"/>
	</jstl:if>
	<acme:button code="worker.job.form.button.auditRecords" action="/acme-jobs/authenticated/audit-record/list?id=${id}"/>
	<jstl:if test="${hasADaring}">
  		<acme:button code="worker.job.form.button.daring" action="/acme-jobs/worker/daring/show?id=${daringId}"/>
  	</jstl:if>
  	<acme:form-return code="worker.job.form.button.return"/>
</acme:form>