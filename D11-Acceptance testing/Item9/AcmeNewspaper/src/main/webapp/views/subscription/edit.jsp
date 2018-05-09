<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib" %>

<div class="well col-md-6 col-md-offset-3">
	<form:form action="customer/subscription/save.do" modelAttribute="subscription">
	<jstl:set var='model' value='subscription' scope='request'/>
	
		<!-- Hidden Attributes -->
		<lib:input name="newspaper,volume" type="hidden" />
		
		
		<!-- Hidden Attributes -->
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
		
		<lib:button model="subscription" id="${subscription.id}" cancelUri="/AcmeNewspaper" noDelete="true" />
</form:form>
</div>