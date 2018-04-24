<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib" %>
<%@taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jstl:if test="${requestUri eq 'article/list.do' }">
	<form action="article/list.do">
	  <div class="input-group col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1" style="margin-bottom:15px">
	    <input type="text" name="keyword" class="form-control" placeholder="Search" value="${keyword}">
	    <div class="input-group-btn">
	      <button class="btn btn-default" type="submit">
	        <i class="glyphicon glyphicon-search"> </i>
	      </button>
	    </div>
	  </div>
	</form>
</jstl:if>

<div class="col-sm-10 col-sm-offset-1 well">
	<display:table pagesize="5" class="displaytag" keepStatus="true" name="articles" requestURI="${requestUri}" id="row2">
		<display:setProperty name="paging.banner.onepage" value=""/>
	    <display:setProperty name="paging.banner.placement" value="bottom"/>
	    <display:setProperty name="paging.banner.all_items_found" value=""/>
	    <display:setProperty name="paging.banner.one_item_found" value=""/>
	    <display:setProperty name="paging.banner.no_items_found" value=""/>

	<jstl:set var='model' value='article' scope='request'/>
	
	<lib:column name="title"></lib:column>
	<lib:column name="publicationMoment" format="{0,date,dd/MM/yyyy}"></lib:column>
	<lib:column name="summary" value="${fn:substring(row2.summary,0,30)}..."/>
	
	<lib:column name="newspaper" link="newspaper/display.do?newspaperId=${row2.newspaper.id}" linkName="${row2.newspaper.title}"/>
	
	<spring:message code="article.display" var="display_header"/>
	<display:column title="${display_header}">
		<jstl:if test="${not row2.newspaper.isPrivate or articlesMap[row2]}">
			<a href="article/display.do?articleId=${row2.id}">${display_header}</a>
		</jstl:if>
	</display:column>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<jstl:if test="${not row2.inappropriate}">
				<a href="admin/article/inappropriate.do?articleId=${row2.id}"><spring:message code="admin.markInappropriate"/></a>
			</jstl:if>
		</display:column>
	</security:authorize>
	<security:authorize access="hasRole('USER')">
		<display:column>
			<jstl:if test="${pageContext.request.userPrincipal.name eq row2.newspaper.user.userAccount.username and not row2.finalMode}">
				<a href="user/article/edit.do?articleId=${row2.id}"><spring:message code="article.edit"/></a>
			</jstl:if>
		</display:column>
	</security:authorize>
	
</display:table>
</div>