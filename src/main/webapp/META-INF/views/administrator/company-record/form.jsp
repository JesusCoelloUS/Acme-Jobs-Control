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
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.company-record.form.label.company" path="company"/>
	<acme:form-textbox code="administrator.company-record.form.label.sector" path="sector"/>
	<acme:form-textbox code="administrator.company-record.form.label.CEO" path="CEO"/>
	<acme:form-textarea code="administrator.company-record.form.label.description" path="description"/>
	<acme:form-textbox code="administrator.company-record.form.label.phone" path="phone"/>
	<acme:form-textbox code="administrator.company-record.form.label.email" path="email"/>
	<acme:form-url code="administrator.company-record.form.label.website" path="website"/>
	<acme:form-integer code="administrator.company-record.form.label.stars" path="stars"/>
	
	<acme:form-submit code="administrator.company-record.form.button.create" action="/administrator/company-record/create"/>	
  	<acme:form-return code="administrator.company-record.form.button.return"/>
</acme:form>
