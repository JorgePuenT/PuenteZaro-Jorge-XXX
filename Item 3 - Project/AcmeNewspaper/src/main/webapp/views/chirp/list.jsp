<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib" %>
<%@taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-sm-10 col-sm-offset-1 well">
	<display:table pagesize="10" class="displaytag" keepStatus="true" name="chirps" requestURI="${requestUri}" id="row">
		<display:setProperty name="paging.banner.onepage" value=""/>
	    <display:setProperty name="paging.banner.placement" value="bottom"/>
	    <display:setProperty name="paging.banner.all_items_found" value=""/>
	    <display:setProperty name="paging.banner.one_item_found" value=""/>
	    <display:setProperty name="paging.banner.no_items_found" value=""/>
    


	<jstl:set var='model' value='chirp' scope='request'/>
		<lib:column name='title'/>
		<lib:column name='creationMoment' format='{0,date,dd/MM/yyyy hh:mm}'/>
		<lib:column name='description'/>
		
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<jstl:if test="${not row.inappropriate }">
				<a href="admin/chirp/inappropriate.do?chirpId=${row.id}"><spring:message code="admin.markInappropriate"/></a>
			</jstl:if>
		</display:column>
	</security:authorize>
</display:table>
</div>