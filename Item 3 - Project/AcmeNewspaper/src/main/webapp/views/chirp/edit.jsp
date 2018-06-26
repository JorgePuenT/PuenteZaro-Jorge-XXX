<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib" %>

<div class="well col-md-6 col-md-offset-3">
	<form:form action="user/chirp/save.do" modelAttribute="chirp">
	
		<lib:input name="id,version,user,creationMoment,inappropriate" type="hidden" />
		
		<jstl:set var='model' value='chirp' scope='request'/>
		<lib:input type="text" name='title'/>
		<lib:input type="text" name='description'/>
		
		<lib:button model='chirp' noDelete='true' id='${chirp.id}' cancelUri='#'/>
</form:form>
</div>