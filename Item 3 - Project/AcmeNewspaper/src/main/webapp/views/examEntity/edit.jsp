<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib" %>

<div class="well col-md-6 col-md-offset-3">
	<form:form action="admin/examEntity/save.do" modelAttribute="examEntity">
	<jstl:set var='model' value='examEntity' scope='request'/>
	
		<!-- Hidden Attributes -->
		<lib:input name="id" type="hidden" />
		
		<jstl:if test="${examEntity.id eq 0 or isDraft}">
			<lib:input type="text" name='title' required="true"/>	
			<lib:input type="text" name='description' required="true"/>
			<lib:input type="number" name='gauge' min="1" max="3"/>
			<lib:input type="moment" name="displayMoment" />
			<lib:input type="checkBox" name="draft"/>
		</jstl:if>
		<jstl:if test="${examEntity.id ne 0 and not isDraft}">
			<div class="form-group row">
			<spring:message code="examEntity.newspaper"/>
				<select id="newspaper" name="newspaper" class="selectpicker col-xs-12">
					<option>----</option>
					<jstl:forEach items="${newspapers}" var="news">
						<option value="${news.id}" <jstl:if test="${examEntity.newspaper.id eq news.id}">selected="selected"</jstl:if> >${news.title}</option>
					</jstl:forEach>
				</select>
				<form:errors cssClass="error" path="newspaper" />
			</div>
		</jstl:if>
		
		<lib:button model="examEntity" id="${examEntity.id}" cancelUri="/AcmeNewspaper" noDelete="true" />
	</form:form>
</div>
<jstl:if test="${examEntity.id eq 0}">
	<script>
		$(document).ready(function () {
	    	$('.dtPicker').data("DateTimePicker").minDate(new Date());
		});
	</script>
</jstl:if>