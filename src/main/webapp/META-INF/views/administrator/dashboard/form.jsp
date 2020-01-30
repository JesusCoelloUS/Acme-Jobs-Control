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

<h2>
	<acme:message code="administrator.dashboard.form.title.dashboard"/>
</h2>

<div>
	<canvas id="canvas"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
				labels: [
					"<acme:message code='administrator.dashboard.form.label.ratioOfJobsThatHaveABisit'/>",
					"<acme:message code='administrator.dashboard.form.label.ratioOfBisitsThatHaveATracer'/>",
					"<acme:message code='administrator.dashboard.form.label.ratioOfApplicationsThatHaveAPasswordProtectedTracer'/>"
				],
				datasets: [
					{
						data:[
							<jstl:out value="${ratioOfJobsThatHaveABisit}"/>,
							<jstl:out value="${ratioOfBisitsThatHaveATracer}"/>,
							<jstl:out value="${ratioOfApplicationsThatHaveAPasswordProtectedTracer}"/>
						]
					}
				]
		};
		
		var options = {
				scales: {
					yAxes:[
						{
							ticks: {
								suggestedMin: 0.0,
								suggestedMax: 1.0
							}
						}
					]
				},
				legend: {
					display: false
				}
		};
		
		var canvas,context;
		
		canvas = document.getElementById("canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type: "bar",
			data: data,
			options: options
		});
	});
</script>
