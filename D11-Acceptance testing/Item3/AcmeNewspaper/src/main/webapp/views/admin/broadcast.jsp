
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<form:form action="admin/broadcast.do" method="POST">

	<label>
		<spring:message code="admin.broadcast.subject"/>
	</label>
	<input name="subject"/>
	
	<label>
		<spring:message code="admin.broadcast.body"/>
	</label>
	<input name="messageBody"/>
	
	<input class="btn btn-primary" type="submit" name="send"
		value='<spring:message code="admin.broadcast.send"/>' />

	<input class="btn btn-primary" type="button"
		value="<spring:message code="admin.broadcast.cancel" />"	
		onclick="javascript: relativeRedir('/');" />
		
</form:form>