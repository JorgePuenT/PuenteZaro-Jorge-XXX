<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib" %>
<%@taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-sm-10 col-sm-offset-1 well">
	<display:table pagesize="8" class="displaytag" keepStatus="true" name="users" requestURI="${requestUri}" id="row">
		<display:setProperty name="paging.banner.onepage" value=""/>
	    <display:setProperty name="paging.banner.placement" value="bottom"/>
	    <display:setProperty name="paging.banner.all_items_found" value=""/>
	    <display:setProperty name="paging.banner.one_item_found" value=""/>
	    <display:setProperty name="paging.banner.no_items_found" value=""/>
    
	<jstl:set var='model' value='user' scope='request'/>
	
	<lib:column name="name"></lib:column>
	<lib:column name="surnames"></lib:column>
	<spring:message code="user.addressess" var="addressess_header" />
	<display:column title="${addressess_header}">
		<jstl:forEach items="${row.addressess}" var="address">
			<jstl:out value="${address}"/><br/>
		</jstl:forEach>
	</display:column>
	<spring:message code="user.phoness" var="phoness_header" />
	<display:column title="${phoness_header}">
		<jstl:forEach items="${row.phoness}" var="phone">
			<jstl:out value="${phone}"/><br/>
		</jstl:forEach>
	</display:column>
	<spring:message code="user.emailss" var="emailss_header" />
	<display:column title="${emailss_header}">
		<jstl:forEach items="${row.emailss}" var="email">
			<jstl:out value="${email}"/><br/>
		</jstl:forEach>
	</display:column>
	<spring:message code="user.profile" var="profile_header"/>
	<display:column title="${profile_header}">
		<a href="user-display.do?userId=${row.id}">${profile_header}</a>
	</display:column>
	
	
</display:table>
</div>