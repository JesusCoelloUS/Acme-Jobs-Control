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
	<acme:form-textbox code="administrator.challenge.form.label.title" path="title"/>
	<acme:form-moment code="administrator.challenge.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="administrator.challenge.form.label.description" path="description"/>
	<acme:form-integer code="administrator.challenge.form.label.goldReward" path="goldReward"/>
	<acme:form-integer code="administrator.challenge.form.label.silverReward" path="silverReward"/>
	<acme:form-integer code="administrator.challenge.form.label.bronzeReward" path="bronzeReward"/>
	<acme:form-textbox code="administrator.challenge.form.label.goldGoal" path="goldGoal"/>
	<acme:form-textbox code="administrator.challenge.form.label.silverGoal" path="silverGoal"/>
	<acme:form-textbox code="administrator.challenge.form.label.bronzeGoal" path="bronzeGoal"/>
	
	<acme:form-submit code="administrator.challenge.form.button.submit" action="/administrator/challenge/create"/>
  	<acme:form-return code="administrator.challenge.form.button.return"/>
</acme:form>
