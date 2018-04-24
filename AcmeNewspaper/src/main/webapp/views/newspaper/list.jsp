<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib" %>
<%@taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<jstl:if test="${requestUri eq 'newspaper/list.do' }">
	<form action="newspaper/list.do">
	  <div class="input-group col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1" style="margin-bottom:15px">
	    <input type="text" name="keyword" class="form-control" placeholder="Search" value="${keyword}">
	    <div class="input-group-btn">
	      <button class="btn btn-default" type="submit">
	        <i class="glyphicon glyphicon-search"> </i>
	      </button>
	    </div>
	  </div>
	</form>
</jstl:if>

<div class="col-sm-10 col-sm-offset-1 well">
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