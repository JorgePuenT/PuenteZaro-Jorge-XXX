<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<div style="margin-right: 10px" class="col-sm-5 col-sm-offset-1 well">
	<security:authorize access="hasRole('USER')">
		<jstl:if test="${user ne logged}">
			<jstl:choose>
				<jstl:when test="${follows}">
					<a class="btn btn-primary btn-block" href="user/un-follow.do?userId=${user.id}"><spring:message code="user.unfollow"/></a>	
				</jstl:when>
				<jstl:otherwise>	
					<a class="btn btn-primary btn-block" href="user/follow.do?userId=${user.id}"><spring:message code="user.follow"/></a>
				</jstl:otherwise>
			</jstl:choose>
		</jstl:if>
	</security:authorize>

	<!-- PERSONAL DATA -->
	<h1><spring:message code="heading.personalData" /></h1>
	<display:table pagesize="10" class="displaytag" keepStatus="true"
		name="user" id="row">
		<display:setProperty name="paging.banner.onepage" value="" />
		<display:setProperty name="paging.banner.placement" value="bottom" />
		<display:setProperty name="paging.banner.all_items_found" value="" />
		<display:setProperty name="paging.banner.one_item_found" value="" />
		<display:setProperty name="paging.banner.no_items_found" value="" />

		<jstl:set var='model' value='user' scope='request' />

		<lib:column name="name"></lib:column>
		<lib:column name="surnames"></lib:column>
		<spring:message code="user.addressess" var="addressess_header" />
		<display:column title="${addressess_header}">
			<jstl:forEach items="${row.addressess}" var="address">
				<jstl:out value="${address}" />
				<br />
			</jstl:forEach>
		</display:column>
		<spring:message code="user.phoness" var="phoness_header" />
		<display:column title="${phoness_header}">
			<jstl:forEach items="${row.phoness}" var="phone">
				<jstl:out value="${phone}" />
				<br />
			</jstl:forEach>
		</display:column>
		<spring:message code="user.emailss" var="emailss_header" />
		<display:column title="${emailss_header}">
			<jstl:forEach items="${row.emailss}" var="email">
				<jstl:out value="${email}" />
				<br />
			</jstl:forEach>
		</display:column>

	</display:table>

	<!-- ARTICLES -->
	<h1><spring:message code="heading.articles" /></h1>
	<display:table pagesize="10" class="displaytag" keepStatus="true"
		name="articles" id="row2">
		<display:setProperty name="paging.banner.onepage" value="" />
		<display:setProperty name="paging.banner.placement" value="bottom" />
		<display:setProperty name="paging.banner.all_items_found" value="" />
		<display:setProperty name="paging.banner.one_item_found" value="" />
		<display:setProperty name="paging.banner.no_items_found" value="" />

		<jstl:set var='model' value='article' scope='request' />

		<lib:column name="title"></lib:column>
		<lib:column name="publicationMoment" format="{0,date,dd/MM/yyyy}"></lib:column>
		<lib:column name="summary"
			value="${fn:substring(row2.summary,0,30)}..." />

		<lib:column name="newspaper"
			link="newspaper/display.do?newspaperId=${row2.newspaper.id}"
			linkName="${row2.newspaper.title}" />

		<spring:message code="article.display" var="display_header" />
		<display:column title="${display_header}">
			<jstl:if test="${not row2.newspaper.isPrivate or articlesMap[row2] }">
				<a href="article/display.do?articleId=${row2.id}">${display_header}</a>
			</jstl:if>
		</display:column>


	</display:table>
</div>

<div style="margin: 0 10px" class="col-sm-5 well">

	<!-- FOLLOWED ACTORS -->
	<h1><spring:message code="heading.followed" /></h1>
	<display:table pagesize="10" class="displaytag" keepStatus="true" name="user.follows" id="row3">
		<display:setProperty name="paging.banner.onepage" value="" />
		<display:setProperty name="paging.banner.placement" value="bottom" />
		<display:setProperty name="paging.banner.all_items_found" value="" />
		<display:setProperty name="paging.banner.one_item_found" value="" />
		<display:setProperty name="paging.banner.no_items_found" value="" />

		<jstl:set var='model' value='user' scope='request' />

		<lib:column name="name"></lib:column>
		<lib:column name="surnames"></lib:column>
		<spring:message code="user.emailss" var="emailss_header" />
		<display:column title="${emailss_header}">
			<jstl:forEach items="${row.emailss}" var="email">
				<jstl:out value="${email}" />
				<br />
			</jstl:forEach>
		</display:column>
		
		<spring:message code="user.profile" var="Header" />
		<display:column title="${Header}">
			<a href="user-display.do?userId=${row3.id}"><spring:message code="user.display" /></a>
		</display:column>
	</display:table>
	<!-- ACTORS FOLLOWING YOU -->
	<h1><spring:message code="heading.followers" /></h1>
	<display:table pagesize="10" class="displaytag" keepStatus="true" name="user.followedBy" id="row4">
		<display:setProperty name="paging.banner.onepage" value="" />
		<display:setProperty name="paging.banner.placement" value="bottom" />
		<display:setProperty name="paging.banner.all_items_found" value="" />
		<display:setProperty name="paging.banner.one_item_found" value="" />
		<display:setProperty name="paging.banner.no_items_found" value="" />

		<jstl:set var='model' value='user' scope='request' />

		<lib:column name="name"></lib:column>
		<lib:column name="surnames"></lib:column>
		<spring:message code="user.emailss" var="emailss_header" />
		<display:column title="${emailss_header}">
			<jstl:forEach items="${row.emailss}" var="email">
				<jstl:out value="${email}" />
				<br />
			</jstl:forEach>
		</display:column>
	</display:table>
</div>

<!-- CHIRPS -->
<div class="col-sm-10 col-sm-offset-1 well">
	<h1><spring:message code="heading.chirps" /></h1>
	<display:table pagesize="10" class="displaytag" keepStatus="true"
		name="chirps" id="row3">
		<display:setProperty name="paging.banner.onepage" value="" />
		<display:setProperty name="paging.banner.placement" value="bottom" />
		<display:setProperty name="paging.banner.all_items_found" value="" />
		<display:setProperty name="paging.banner.one_item_found" value="" />
		<display:setProperty name="paging.banner.no_items_found" value="" />

		<jstl:set var='model' value='chirp' scope='request' />

		<lib:column name="title"></lib:column>
		<lib:column name="creationMoment" format="{0,date,dd/MM/yyyy}"></lib:column>
		<lib:column name="description"/>
	</display:table>
</div>
