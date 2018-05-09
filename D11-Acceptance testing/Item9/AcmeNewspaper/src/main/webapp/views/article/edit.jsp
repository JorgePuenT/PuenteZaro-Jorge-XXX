<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib" %>

<div class="well col-md-6 col-md-offset-3">
	<form:form action="user/article/save.do" modelAttribute="article">
	<jstl:set var='model' value='article' scope='request'/>
	
		<!-- Hidden Attributes -->
		<lib:input name="id,version,publicationMoment,inappropriate,followUps" type="hidden" />
		<jstl:if test="${article.id ne 0}">
		<lib:input name="newspaper" type="hidden"/>
		</jstl:if>
		
		<!-- Hidden Attributes -->
		<lib:input type="text" name='title'/>		
		<lib:input type="text" name='summary'/>
		<lib:input type="text" name='body'/>
		<spring:message code="article.pictures.placeholder" var="picturesPlaceholder" />
		<lib:input type="text" placeholder="${picturesPlaceholder}" name='picturess'/>
		
		<jstl:if test="${article.id eq 0}">
		<div class="form-group row">
		<spring:message code="article.newspaper"/>
			<select id="newspaper" name="newspaper" class="selectpicker col-xs-12">
				<option>----</option>
				<jstl:forEach items="${nonPublishedNewspapers}" var="news">
					<option value="${news.id}" <jstl:if test="${article.newspaper.id eq news.id}">selected="selected"</jstl:if> >${news.title}</option>
				</jstl:forEach>
			</select>
			<form:errors cssClass="error" path="newspaper" />
		</div>
		<p><spring:message code="article.explanation"/></p>
		</jstl:if>
		
		
		<lib:input type="checkBox" name='finalMode'/>
		
		<lib:button model="article" id="${id}" cancelUri="/AcmeNewspaper" noDelete="false" />
</form:form>
</div>