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

<acme:form>

	<jstl:if test="${command == 'create'}">
		<jstl:set var="option" value="false"/>
	</jstl:if>
	<jstl:if test="${command == 'show'}">
		<jstl:set var="option" value="true"/>
	</jstl:if>
	
	<acme:form-textbox code="worker.application.form.label.reference" path="reference" readonly="${option}"/>
	<jstl:if test="${command != 'create'}">
	<acme:form-textbox code="worker.application.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	<jstl:if test="${command != 'create' && status == 'REJECTED'}">
	<acme:form-textarea code="employer.application.form.label.rejectDecision" path="rejectDecision" readonly="true"/>
	</jstl:if>
	<acme:form-textarea code="worker.application.form.label.statement" path="statement" readonly="${option}"/>
	<jstl:if test="${command != 'create'}">
	<acme:form-moment code="worker.application.form.label.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>
	<acme:form-textarea code="worker.application.form.label.skills" path="skills" readonly="${option}"/>
	<acme:form-textarea code="worker.application.form.label.qualifications" path="qualifications" readonly="${option}"/>
	
	<jstl:if test="${command != 'create'}">
	<acme:button code="worker.application.form.button.job" action="/acme-jobs/worker/job/show?id=${jobId }"/>
	</jstl:if>
	
	<acme:form-submit test="${command == 'create' }" code="worker.application.form.button.create" action="/worker/application/create?jobId=${id}"/>
	<acme:form-return code="worker.application.form.button.return"/>
</acme:form>
