<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib" %>

<div class="well col-md-6 col-md-offset-3">
	<form:form action="user/followUp/save.do" modelAttribute="followUp">
	<jstl:set var='model' value='followUp' scope='request'/>
	
		<lib:input name="id,version,article,publicationMoment" type="hidden" />
	
		<lib:input type="text" name='title'/>
		<lib:input type="text" name='summary'/>
		<lib:input type="text" name='body'/>
		<lib:input type="text" name='picturess'/>
		
		<lib:button model='chirp' noDelete='true' id='${chirp.id}' cancelUri='#'/>
</form:form>
</div>