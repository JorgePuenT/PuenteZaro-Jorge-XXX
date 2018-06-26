<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-sm-10 col-sm-offset-1 well">

	<security:authorize access="hasRole('USER')">
		<jstl:if test="${pageContext.request.userPrincipal.name eq newspaper.user.userAccount.username}">
			<a href="user/newspaper/edit.do?newspaperId=${newspaper.id}" class="btn btn-block btn-warning"><spring:message code="newspaper.edit"/></a>
		
		<a class="btn btn-success btn-block" href="user/article/create.do"><spring:message code="article.create" /></a>
		</jstl:if>
	</security:authorize>
	<security:authorize access="hasRole('CUSTOMER')">
		<jstl:if test="${not isSubscribedNewspaper and newspaper.isPrivate}">
			<a class="btn btn-primary btn-block" href="customer/subscription/create.do?newspaperId=${newspaper.id}"><spring:message code="newspaper.suscribe"/></a>
		</jstl:if>
	</security:authorize>
	<display:table pagesize="1" class="displaytag" keepStatus="true" name="newspaper" id="row">
		<display:setProperty name="paging.banner.onepage" value=""/>
	    <display:setProperty name="paging.banner.placement" value="bottom"/>
	    <display:setProperty name="paging.banner.all_items_found" value=""/>
	    <display:setProperty name="paging.banner.one_item_found" value=""/>
	    <display:setProperty name="paging.banner.no_items_found" value=""/>
	    
		<fmt:formatDate value='${row.publicationDate}' pattern="dd/MM/yyyy" var="formatedDate"/>
		
		<jstl:set var='model' value='newspaper' scope='request'/>
		<lib:column name='title'/>
		<lib:column name='nextIssue' value="${formatedDate}"/>
		<lib:column name='description'/>
		<lib:column name='picture' photoUrl="${row.picture}"/>
		<lib:column name='isPrivate'/>
	</display:table>
</div>

<div class="col-sm-10 col-sm-offset-1 well">
	<h2><spring:message code="newspaper.article.title"/></h2>
	<display:table pagesize="10" class="displaytag" keepStatus="true" name="articles" id="row2">
	
		<display:setProperty name="paging.banner.onepage" value=""/>
	    <display:setProperty name="paging.banner.placement" value="bottom"/>
	    <display:setProperty name="paging.banner.all_items_found" value=""/>
	    <display:setProperty name="paging.banner.one_item_found" value=""/>
	   <display:setProperty name="paging.banner.no_items_found" value=""/>
		
		<jstl:set var='model' value='article' scope='request'/>
		
		<lib:column name="publicationMoment" format="{0,date,dd/MM/yyyy}" group="1"></lib:column>
		
		<jstl:choose>
			<jstl:when test="${not row2.newspaper.isPrivate or isSubscribed or (row2.newspaper.user.userAccount.username eq pageContext.request.userPrincipal.name)}">
				<lib:column name="title" link="article/display.do?articleId=${row2.id}" linkName="${row2.title}"></lib:column>
			</jstl:when>
			<jstl:otherwise>
				<lib:column name="title"></lib:column>
			</jstl:otherwise>
		</jstl:choose>
	
		
		<lib:column name="summary" value="${fn:substring(row2.summary,0,30)}..."/>
	
		<spring:message code="article.writer" var="writer_header"/>
		<display:column title="${writer_header}">
			<a href="user-display.do?userId=${row2.newspaper.user.id}">
			<jstl:out value="${row2.newspaper.user.surnames}, ${row2.newspaper.user.name}"/></a>
		</display:column>
	
	
		<security:authorize access="hasRole('USER')">
		<spring:message code="article.edit" var="article_edit"/>
		<display:column title="${article_edit}">
		<jstl:if test="${row2.newspaper.user.userAccount.username eq pageContext.request.userPrincipal.name}">
			<a href="user/article/edit.do?articleId=${row2.id}">
			<jstl:out value="${article_edit}"/></a>
			</jstl:if>
		</display:column>
		</security:authorize>
	</display:table>
</div>

<div class="col-sm-10 col-sm-offset-1 well">
<h2><spring:message code="newspaper.examEntity.title"/></h2>
	<display:table pagesize="10" class="displaytag" keepStatus="true" name="examEntities" requestURI="${requestUri}" id="row3">
		<display:setProperty name="paging.banner.onepage" value=""/>
	    <display:setProperty name="paging.banner.placement" value="bottom"/>
	    <display:setProperty name="paging.banner.all_items_found" value=""/>
	    <display:setProperty name="paging.banner.one_item_found" value=""/>
	    <display:setProperty name="paging.banner.no_items_found" value=""/>
    
			<spring:message code="examEntity.localizedFormat" var="localizedFormat"/>
		
			<jstl:set var='model' value='examEntity' scope='request'/>
				<jstl:choose>
					<jstl:when test="${row3.gauge eq 1}">
				      <jstl:set var = "colorStatus" value = "lightYellow" />
				    </jstl:when>
				    <jstl:when test="${row3.gauge eq 2}">
				      <jstl:set var = "colorStatus" value = "Moccasin" />
				    </jstl:when>
				    <jstl:when test="${row3.gauge eq 3}">
				      <jstl:set var = "colorStatus" value = "Blue" />
				    </jstl:when>
			    </jstl:choose>
			
				<lib:column name='ticker'/>
				<lib:column name='title'/>
				<lib:column name='description'/>
				<lib:column name='gauge' style="background-color: ${colorStatus};" />
				<lib:column name='displayMoment' format="${localizedFormat}"/>
				<spring:message code="examEntity.draft" var="draftHeader" />
				<display:column title="${draftHeader}" >
					<jstl:if test="${row3.draft}">
						<span class="glyphicon glyphicon-ok"></span>
					</jstl:if>
					<jstl:if test="${not row3.draft}">
						<span class="glyphicon glyphicon-remove"></span>
					</jstl:if>
				</display:column>
				<lib:column name="admin" value="${row3.admin.name} ${row3.admin.surnames}" />
		</display:table>
</div>
