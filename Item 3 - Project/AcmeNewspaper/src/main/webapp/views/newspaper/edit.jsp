<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib" %>

<div class="well col-md-6 col-md-offset-3">
	<form:form action="user/newspaper/save.do" modelAttribute="newspaper">
		<jstl:set var='model' value='newspaper' scope='request'/>

		<jstl:if test="${newspaper.id eq 0 or empty newspaper.id }">
			<lib:input name="version,subscriptionss,user,inappropriate,articless,volumes,advertisements" type="hidden" />

			<lib:input type="text" name='title'/>
		</jstl:if>

		<lib:input type="hidden" name="id"/>
		<lib:input type="date" name='publicationDate'/>
		<p><spring:message code="newspaper.explanation"/></p>

		<jstl:if test="${newspaper.id eq 0 or empty newspaper.id }">
			<lib:input type="text" name='description'/>
			<lib:input type="text" name='picture'/>
			<lib:input type="number" name='price' min="0" step="0.01"/>
			<lib:input type="checkBox" name='isPrivate'/>
		</jstl:if>

		<lib:button model='newspaper' noDelete='true' id='${newspaper.id}' cancelUri='newspaper/list.do'/>
</form:form>
</div>

<script>
	$(function(){
		var tomorrow = new Date();
		tomorrow.setDate(tomorrow.getDate() + 1);
		$('.dtPicker').data("DateTimePicker").minDate(tomorrow);
	});
</script>
