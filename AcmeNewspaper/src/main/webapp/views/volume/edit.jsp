<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib" %>

<div class="well col-md-6 col-md-offset-3">
	<form:form action="user/volume/save.do" modelAttribute="volume">
		<jstl:set var='model' value='volume' scope='request'/>
		
		
		<lib:input type="hidden" name="id"/>		
		<lib:input type="hidden" name="year"/>
		<lib:input type="hidden" name="volumeNewspapers"/>
		<lib:input type="hidden" name="user"/>
		<lib:input type="hidden" name="subscriptions"/>
		<lib:input type="text" name='title'/>
		<lib:input type="text" name='description'/>



		<lib:button model='volume' noDelete='true' id='${newspaper.id}' cancelUri='volume/list.do'/>
	</form:form>
</div>
