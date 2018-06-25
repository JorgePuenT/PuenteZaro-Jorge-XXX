<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib" %>
<%@taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<div class="row">
	<a href="admin/examEntity/create.do" class="btn btn-primary"><spring:message code="examEntity.create"/></a>
</div>
<div class="col-sm-10">
	<display:table pagesize="10" class="displaytag" keepStatus="true" name="examEntities" requestURI="${requestUri}" id="row">
		<display:setProperty name="paging.banner.onepage" value=""/>
	    <display:setProperty name="paging.banner.placement" value="bottom"/>
	    <display:setProperty name="paging.banner.all_items_found" value=""/>
	    <display:setProperty name="paging.banner.one_item_found" value=""/>
	    <display:setProperty name="paging.banner.no_items_found" value=""/>
    
	<spring:message code="examEntity.localizedFormat" var="localizedFormat"/>

	<jstl:set var='model' value='examEntity' scope='request'/>
		<jstl:choose>
			<jstl:when test="${row.gauge eq 1}">
		      <jstl:set var = "colorStatus" value = "lightYellow" />
		    </jstl:when>
		    <jstl:when test="${row.gauge eq 2}">
		      <jstl:set var = "colorStatus" value = "Moccasin" />
		    </jstl:when>
		    <jstl:when test="${row.gauge eq 3}">
		      <jstl:set var = "colorStatus" value = "Blue" />
		    </jstl:when>
	    </jstl:choose>
	
		<lib:column name='ticker'/>
		<lib:column name='title'/>
		<lib:column name='description'/>
		<lib:column name='gauge' style="background-color: ${colorStatus};" />
		<lib:column name='displayMoment' format="${localizedFormat}"/>
		<lib:column name='draft'/>
		<lib:column name="newspaper" link='newspaper/display.do?newspaperId=${row.newspaper.id}' linkName='${row.newspaper.title}'/>
		<lib:column name="edit" link="admin/examEntity/edit.do?examEntityId=${row.id}" linkSpringName="edit"/>
		<spring:message code="examEntity.delete" var="deleteHeader" />
		<display:column title="${deleteHeader}" >
			<jstl:if test="${row.draft}">
				<a href="admin/examEntity/delete.do?examEntityId=${row.id}">
					${deleteHeader}
				</a>
			</jstl:if>
		</display:column>
</display:table>
</div>