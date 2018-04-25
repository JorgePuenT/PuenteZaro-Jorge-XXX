<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib" %>
<%@taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="col-sm-10 col-sm-offset-1 well">
	<display:table pagesize="5" class="displaytag" keepStatus="true" name="volumes" requestURI="${requestUri}" id="row2">
		<display:setProperty name="paging.banner.onepage" value=""/>
	    <display:setProperty name="paging.banner.placement" value="bottom"/>
	    <display:setProperty name="paging.banner.all_items_found" value=""/>
	    <display:setProperty name="paging.banner.one_item_found" value=""/>
	    <display:setProperty name="paging.banner.no_items_found" value=""/>

	<jstl:set var='model' value='volume' scope='request'/>
	
	<lib:column name="volume" link="volume/display.do?volumeId=${row2.id}" linkName="${row2.title}"/>
	<lib:column name="description"></lib:column>
	<lib:column name="year"></lib:column>
	
	<security:authorize access="hasRole('USER')">
		<display:column>
			<jstl:if test="${pageContext.request.userPrincipal.name eq row2.user.userAccount.username}">
				<a href="user/volume/edit.do?volumeId=${row2.id}"><spring:message code="volume.edit"/></a>
			</jstl:if>
		</display:column>
	</security:authorize>
	
</display:table>
</div>