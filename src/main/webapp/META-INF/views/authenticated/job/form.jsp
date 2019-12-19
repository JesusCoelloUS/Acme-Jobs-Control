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
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.job.form.label.reference" path="reference"/>
	<acme:form-textbox code="authenticated.job.form.label.status" path="status"/>
	<acme:form-textbox code="authenticated.job.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.job.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.job.form.label.description" path="description"/>
	<acme:form-money code="authenticated.job.form.label.salary" path="salary"/>
	<acme:form-url code="authenticated.job.form.label.moreInfo" path="moreInfo"/>
	
	<acme:button code="authenticated.job.form.button.duties" action="/acme-jobs/authenticated/duty/list?id=${id}"/>
	<acme:button code="authenticated.job.form.button.auditRecords" action="/acme-jobs/authenticated/audit-record/list?id=${id}"/>
	
	<security:authorize access="hasRole('Auditor')">
		<acme:button code="authenticated.job.form.button.audit-record.create" action="/acme-jobs/auditor/audit-record/create?jobId=${id}"/>
	</security:authorize>
	
	<jstl:if test="${hasADaring}">
  		<acme:button code="authenticated.job.form.button.daring" action="/acme-jobs/authenticated/daring/show?id=${daringId}"/>
  	</jstl:if>
  	<acme:form-return code="authenticated.job.form.button.return"/>
</acme:form>