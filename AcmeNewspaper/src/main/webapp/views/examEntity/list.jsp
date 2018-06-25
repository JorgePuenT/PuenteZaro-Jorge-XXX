<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib" %>
<%@taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-sm-10">
	<display:table pagesize="10" class="displaytag" keepStatus="true" name="examEntities" requestURI="${requestUri}" id="row">
		<display:setProperty name="paging.banner.onepage" value=""/>
	    <display:setProperty name="paging.banner.placement" value="bottom"/>
	    <display:setProperty name="paging.banner.all_items_found" value=""/>
	    <display:setProperty name="paging.banner.one_item_found" value=""/>
	    <display:setProperty name="paging.banner.no_items_found" value=""/>
    


	<jstl:set var='model' value='examEntity' scope='request'/>
		<lib:column name='ticker'/>
		<lib:column name='title'/>
		<lib:column name='description'/>
		<lib:column name='gauge'/>
		<lib:column name='displayMoment'/>
		<lib:column name="newspaper" link='newspaper/display.do?newspaperId=${row.newspaper.id}' linkName='${row.newspaper.title}'/>
		<jstl:if test="${row.draft}">
			<lib:column name="edit" link="admin/examEntity/edit.do?examEntityId=${row.id}" linkSpringName="edit"/>
			<lib:column name="delete" link="admin/examEntity/delete.do?examEntityId=${row.id}" linkSpringName="delete"/>
		</jstl:if>
		<jstl:if test="${not row.draft}">
			<lib:column name="assignNewspaper" link="admin/examEntity/edit.do?examEntityId=${row.id}" linkSpringName="assignNewspaper"/>
		</jstl:if>
</display:table>
</div>