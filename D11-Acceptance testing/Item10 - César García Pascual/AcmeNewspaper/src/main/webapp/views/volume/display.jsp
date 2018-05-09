<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib"%>
<%@ taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<div class="container col-sm-8 col-sm-offset-2 well">

	<security:authorize access="hasRole('CUSTOMER')">
		<jstl:if test="${not isSubscribed}">
			<a class="btn btn-primary btn-block" href="customer/subscription/create.do?volumeId=${volume.id}"><spring:message code="volume.subscribe"/></a>
		</jstl:if>
	</security:authorize>
	
	<div class="col-sm-12">
	
	<h1 style="text-align:center;"><jstl:out value="${volume.title}. (${volume.year})" /></h1>	
	<h2 style="text-align:center;"><small><jstl:out value="${volume.description}" /></small></h2>&nbsp;
	
	</div>
</div>



<div class="col-sm-10 col-sm-offset-1 well">
	<h1 style="text-align:center;"><spring:message code="volume.newspapers"/></h1>
	<display:table pagesize="6" class="displaytag" keepStatus="true" name="newspapers" requestURI="${requestUri}" id="row">
		<display:setProperty name="paging.banner.onepage" value=""/>
	    <display:setProperty name="paging.banner.placement" value="bottom"/>
	    <display:setProperty name="paging.banner.all_items_found" value=""/>
	    <display:setProperty name="paging.banner.one_item_found" value=""/>
	    <display:setProperty name="paging.banner.no_items_found" value=""/>

		<jstl:set var='model' value='newspaper' scope='request'/>
		<lib:column name='title'/>
		<lib:column name='publicationDate'/>
		<lib:column name='description'/>
		<lib:column name='picture' photoUrl="${row.picture}"/>
		<lib:column name='price'/>
		<lib:column name='isPrivate'/>
		<lib:column name='display' link='newspaper/display.do?newspaperId=${row.id}' linkSpringName='display' />
		
		<security:authorize access="hasRole('ADMIN')">
			<display:column>
				<jstl:if test="${not row.inappropriate}">
					<a href="admin/newspaper/inappropriate.do?newspaperId=${row.id}"><spring:message code="admin.markInappropriate"/></a>
				</jstl:if>
			</display:column>
		</security:authorize>
</display:table>
</div>

