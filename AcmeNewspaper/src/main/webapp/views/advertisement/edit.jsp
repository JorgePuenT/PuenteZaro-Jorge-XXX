<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib" %>

<div class="well col-md-6 col-md-offset-3">
	<form:form action="user/advertisement/save.do" modelAttribute="advertisement">
	<jstl:set var='model' value='advertisement' scope='request'/>
	
		<!-- Hidden Attributes -->
		<lib:input name="id,version,inappropriate" type="hidden" />
		
		<!-- Attributes -->
		<lib:input type="text" name='title'/>		
		<lib:input type="text" name='bannerUrl'/>
		<lib:input type="text" name='targetUrl'/>
		
		<div class="form-group row">
		<spring:message code="advertisement.newspaper"/>
			<select id="newspaper" name="newspaper" class="selectpicker col-xs-12">
				<option>----</option>
				<jstl:forEach items="${newspapers}" var="news">
					<option value="${news.id}"><jstl:out value="${news.title}"/></option>
				</jstl:forEach>
			</select>
			<form:errors cssClass="error" path="newspaper" />
		</div>
		
		
		<lib:button model="advertisement" id="${id}" cancelUri="/AcmeNewspaper" noDelete="false" />
</form:form>
</div>