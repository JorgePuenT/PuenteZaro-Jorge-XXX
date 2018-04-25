<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-sm-10 col-sm-offset-1 well">
<display:table pagesize="9" class="displaytag" keepStatus="true" name="avgs" id="row">
	 <display:caption> 
	 	<spring:message code='admin.title1'/>
	 </display:caption>
	<display:column>
	<jstl:choose>
		<jstl:when test="${row_rowNum == 1}">
			<spring:message code="admin.query1"/>
		</jstl:when>
		<jstl:when test="${row_rowNum == 2}">
			<spring:message code="admin.query2"/>
		</jstl:when>
		<jstl:when test="${row_rowNum == 3}">
			<spring:message code="admin.query3"/>
		</jstl:when>
		<jstl:when test="${row_rowNum == 4}">
			<spring:message code="admin.query7"/>
		</jstl:when>
		<jstl:when test="${row_rowNum == 5}">
			<spring:message code="admin.query8"/>
		</jstl:when>
		<jstl:when test="${row_rowNum == 6}">
			<spring:message code="admin.query9"/>
		</jstl:when>
		<jstl:when test="${row_rowNum == 7}">
			<spring:message code="admin.query10"/>
		</jstl:when>
		<jstl:when test="${row_rowNum == 8}">
			<spring:message code="admin.query13"/>
		</jstl:when>
		<jstl:when test="${row_rowNum == 9}">
			<spring:message code="admin.query14"/>
		</jstl:when>
	</jstl:choose>	
</display:column>
	<display:column><jstl:out value="${row}"/></display:column>
</display:table>
</div>


<div class="col-sm-10 col-sm-offset-1 well">
<display:table pagesize="4" class="displaytag" keepStatus="true" name="stddevs" id="row2">
	 <display:caption> 
	 	<spring:message code='admin.title2'/>
	 </display:caption>
	<display:column>
	<jstl:choose>
		<jstl:when test="${row2_rowNum == 1}">
			<spring:message code="admin.query1"/>
		</jstl:when>
		<jstl:when test="${row2_rowNum == 2}">
			<spring:message code="admin.query2"/>
		</jstl:when>
		<jstl:when test="${row2_rowNum == 3}">
			<spring:message code="admin.query3"/>
		</jstl:when>
		<jstl:when test="${row2_rowNum == 4}">
			<spring:message code="admin.query10"/>
		</jstl:when>
	</jstl:choose>	
</display:column>
	<display:column><jstl:out value="${row2}"/></display:column>
</display:table>
</div>


<div class="col-sm-10 col-sm-offset-1 well">
<display:table pagesize="7" class="displaytag" keepStatus="true" name="ratios" id="row3">
	 <display:caption> 
	 	<spring:message code='admin.title3'/>
	 </display:caption>
	<display:column>
	<jstl:choose>
		<jstl:when test="${row3_rowNum == 1}">
			<spring:message code="admin.query6"/>
		</jstl:when>
		<jstl:when test="${row3_rowNum == 2}">
			<spring:message code="admin.query11"/>
		</jstl:when>
		<jstl:when test="${row3_rowNum == 3}">
			<spring:message code="admin.query12"/>
		</jstl:when>
		<jstl:when test="${row3_rowNum == 4}">
			<spring:message code="admin.query15"/>
		</jstl:when>
		<jstl:when test="${row3_rowNum == 5}">
			<spring:message code="admin.query16"/>
		</jstl:when>
		<jstl:when test="${row3_rowNum == 6}">
			<spring:message code="admin.query17"/>
		</jstl:when>
		<jstl:when test="${row3_rowNum == 7}">
			<spring:message code="admin.query18"/>
		</jstl:when>
	</jstl:choose>
</display:column>
	<display:column><jstl:out value="${row3}"/></display:column>
</display:table>
</div>

<div class="col-sm-10 col-sm-offset-1 well">
		<display:table pagesize="10" class="displaytag" keepStatus="true" name="newspapersOverAvg" id="row4">
			 <display:caption> 
			 	<spring:message code='admin.query4'/>
			 </display:caption>
			<display:setProperty name="paging.banner.onepage" value=""/>
		    <display:setProperty name="paging.banner.placement" value="bottom"/>
		    <display:setProperty name="paging.banner.all_items_found" value=""/>
		    <display:setProperty name="paging.banner.one_item_found" value=""/>
		    <display:setProperty name="paging.banner.no_items_found" value=""/>
	
			<jstl:set var='model' value='newspaper' scope='request'/>
			<lib:column name='title'/>
			<lib:column name='publicationDate'/>
			<lib:column name='description'/>
			<lib:column name='picture'/>
			<lib:column name='price'/>
			<lib:column name='isPrivate'/>
			<lib:column name='display' link='newspaper/display.do?newspaperId=${row4.id}' linkSpringName='display' />
		</display:table>
</div>


<div class="col-sm-10 col-sm-offset-1 well">
		<display:table pagesize="10" class="displaytag" keepStatus="true" name="newspapersUnderAvg" id="row5">
			 <display:caption> 
			 	<spring:message code='admin.query5'/>
			 </display:caption>
			<display:setProperty name="paging.banner.onepage" value=""/>
		    <display:setProperty name="paging.banner.placement" value="bottom"/>
		    <display:setProperty name="paging.banner.all_items_found" value=""/>
		    <display:setProperty name="paging.banner.one_item_found" value=""/>
		    <display:setProperty name="paging.banner.no_items_found" value=""/>
	
			<jstl:set var='model' value='newspaper' scope='request'/>
			<lib:column name='title'/>
			<lib:column name='publicationDate'/>
			<lib:column name='description'/>
			<lib:column name='picture'/>
			<lib:column name='price'/>
			<lib:column name='isPrivate'/>
			<lib:column name='display' link='newspaper/display.do?newspaperId=${row5.id}' linkSpringName='display' />
		</display:table>
</div>
