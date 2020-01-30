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

	<jstl:choose>
		<jstl:when test="${status == 'REJECTED' && rejectDecision != ''}">
			<jstl:set var="option" value="true" />
		</jstl:when>
		<jstl:otherwise>
			<jstl:set var="option" value="false" />
		</jstl:otherwise>
	</jstl:choose>

	<acme:form-textbox code="employer.application.form.label.reference" path="reference" readonly="true"/>
	
	<jstl:choose>
		<jstl:when test="${status == 'PENDING' || (status == 'REJECTED' && rejectDecision == '') || (status == 'ACCEPTED' && rejectDecision != '')}">
			<acme:form-select code="employer.application.form.label.status" path="status">
				<acme:form-option code="employer.application.form.label.status.pending" value="PENDING"/>
				<acme:form-option code="employer.application.form.label.status.accepted" value="ACCEPTED"/>
				<acme:form-option code="employer.application.form.label.status.rejected" value="REJECTED"/>
			</acme:form-select>
			<acme:form-textarea code="employer.application.form.label.rejectDecision" path="rejectDecision"/>
		</jstl:when>
		<jstl:when test="${status == 'REJECTED' && rejectDecision != ''}">
			<acme:form-textbox code="employer.application.form.label.status" path="status" readonly="true"/>
			<acme:form-textarea code="employer.application.form.label.rejectDecision" path="rejectDecision" readonly="true"/>
		</jstl:when>
		<jstl:otherwise>
			<acme:form-textbox code="employer.application.form.label.status" path="status" readonly="true"/>
		</jstl:otherwise>
	</jstl:choose>
	
	<acme:form-textarea code="employer.application.form.label.statement" path="statement" readonly="true"/>
	<acme:form-moment code="employer.application.form.label.creationMoment" path="creationMoment" readonly="true"/>
	<acme:form-textarea code="employer.application.form.label.skills" path="skills" readonly="true"/>
	<acme:form-textarea code="employer.application.form.label.qualifications" path="qualifications" readonly="true"/>
	
	<jstl:if test="${hasTracer}">
		<acme:form-url code="employer.application.form.label.tracer" path="tracer" readonly="true"/>
		<jstl:if test="${isProtected}">
			<acme:form-textbox code="employer.application.form.label.password" path="password" readonly="true"/>
		</jstl:if>
	</jstl:if>
	
	<acme:form-submit test="${status == 'PENDING' || (status == 'REJECTED' && rejectDecision == '') || (status == 'ACCEPTED' && rejectDecision != '')}" code="employer.application.form.button.update" action="/employer/application/update"/>
</acme:form>

<jstl:if test="${isProtected}">
	<acme:input type="password" code="employer.form.label.enterPassword" path="enterPassword" group="input"/><acme:button id="button" code="employer.form.button.show"/>

	<script>
		$(document).ready(function(){
			$("#tracer").hide();
			$("#password").hide();
		});
		
		$("#button").click(function(){
			mostrar();
		});
		
		$("#enterPassword").keypress(function(e){
			if(e.which == 13){
				mostrar();
			}
		});
		
		function mostrar(){
			var enterPassword = $("#enterPassword").val();
			var password = $("#password").val();
			if(enterPassword == password){
				$("#tracer").show();
				$("#password").show();
				$("#input").hide();
				$("#button").hide();
			}else{
				alert("<acme:message code='worker.form.label.enterPassword.error'/>");
			}
		}
	</script>
</jstl:if>

<acme:button code="employer.application.form.button.job" action="/acme-jobs/employer/job/show?id=${jobId }"/>
<acme:form-return code="employer.application.form.button.return"/>