<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="well col-md-10 col-md-offset-1">
	
		<div class="col-md-2">
			<a href="admin/purlet/create.do" class="btn btn-primary"><spring:message
					code="purlet.create" /></a>
		</div>
	
		<div class="col-md-12">
			<display:table pagesize="10" class="displaytag" keepStatus="true"
				name="purlets" requestURI="${requestUri}" id="row">
				<display:setProperty name="paging.banner.onepage" value="" />
				<display:setProperty name="paging.banner.placement" value="bottom" />
				<display:setProperty name="paging.banner.all_items_found" value="" />
				<display:setProperty name="paging.banner.one_item_found" value="" />
				<display:setProperty name="paging.banner.no_items_found" value="" />
	
				<spring:message code="purlet.localizedFormat"
					var="localizedFormat" />
	
				<jstl:set var='model' value='purlet' scope='request' />
				<jstl:choose>
					<jstl:when test="${row.gauge eq 1}">
						<jstl:set var="colorStatus" value="lavenderBlush" />
					</jstl:when>
					<jstl:when test="${row.gauge eq 2}">
						<jstl:set var="colorStatus" value="Coral" />
					</jstl:when>
					<jstl:when test="${row.gauge eq 3}">
						<jstl:set var="colorStatus" value="rebeccaPurple" />
					</jstl:when>
				</jstl:choose>
	
				<lib:column name='ticker' />
				<lib:column name='title' />
				<lib:column name='description' />
				<lib:column name='gauge' style="background-color: ${colorStatus};" />
				<lib:column name='displayMoment' format="${localizedFormat}" />
				<spring:message code="purlet.draft" var="draftHeader" />
				<display:column title="${draftHeader}">
					<jstl:if test="${row.draft}">
						<span class="glyphicon glyphicon-ok"></span>
					</jstl:if>
					<jstl:if test="${not row.draft}">
						<span class="glyphicon glyphicon-remove"></span>
					</jstl:if>
				</display:column>
				<lib:column name="newspaper"
					link='newspaper/display.do?newspaperId=${row.newspaper.id}'
					linkName='${row.newspaper.title}' />
				<lib:column name="edit"
					link="admin/purlet/edit.do?purletId=${row.id}"
					linkSpringName="edit" />
				<spring:message code="purlet.delete" var="deleteHeader" />
				<display:column title="${deleteHeader}">
					<jstl:if test="${row.draft}">
						<a href="admin/purlet/delete.do?purletId=${row.id}">
							${deleteHeader} </a>
					</jstl:if>
				</display:column>
			</display:table>
		</div>
	
</div>