<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib" %>

<div class="well col-md-6 col-md-offset-3">
	<form:form action="agent/advertisement/save.do" modelAttribute="advertisement" method="post">
	<jstl:set var='model' value='advertisement' scope='request'/>
	
		<!-- Hidden Attributes -->
		<lib:input name="id,version,inappropriate,agent" type="hidden" />
		
		<!-- Attributes -->
		<lib:input type="text" name='title'/>		
		<lib:input type="text" name='bannerUrl'/>
		<lib:input type="text" name='targetUrl'/>
		
		<div class="form-group">
		<form:label class="control-label" path="newspaper">
			<spring:message code="advertisement.newspaper"/>
		</form:label>
			<select id="newspaper" name="newspaper" class="selectpicker col-xs-12">
				<option>----</option>
				<jstl:forEach items="${newspapers}" var="news">
					<option value="${news.id}" <jstl:if test="${advertisement.newspaper ne null and news.id eq advertisement.newspaper.id}"> selected="selected" </jstl:if> ><jstl:out value="${news.title}"/></option>
				</jstl:forEach>
			</select>
			<form:errors cssClass="error" path="newspaper" />
		</div>
		<lib:input type="text" name='creditCard.holderName'/>	
		<spring:message code="creditCard.brand.placeholder" var="brandNamePlaceholder" />
		<lib:input type="text" placeholder="${brandNamePlaceholder}" name='creditCard.brandName'/>
		<spring:message code="creditCard.number.placeholder" var="numberPlaceholder" />
		<lib:input type="text" placeholder="${numberPlaceholder}" name='creditCard.number'/>
		<spring:message code="creditCard.expirationMonth.placeholder" var="expirationMonthPlaceholder" />
		<lib:input type="number" placeholder="${expirationMonthPlaceholder}" name="creditCard.expirationMonth" />
		<spring:message code="creditCard.expirationYear.placeholder" var="expirationYearPlaceholder" />
		<lib:input type="number" placeholder="${expirationYearPlaceholder}" name="creditCard.expirationYear" />
		<spring:message code="creditCard.cvv.placeholder" var="cvvPlaceholder" />
		<lib:input type="number" placeholder="${cvvPlaceholder}" name="creditCard.cvvCode" />
		
		
		<lib:button model="advertisement" id="${id}" cancelUri="/AcmeNewspaper" noDelete="false" />
</form:form>
</div>