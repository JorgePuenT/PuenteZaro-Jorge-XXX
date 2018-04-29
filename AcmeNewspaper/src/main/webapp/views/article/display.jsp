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
	<div class="col-sm-12">
	<jstl:if test="${not empty advertisement}">
		<a href="${advertisement.targetUrl}"><img style="max-height:200px" src="${advertisement.bannerUrl}" alt="${advertisement.title}"/></a>
	</jstl:if>
	<fmt:formatDate var="publicationMoment" type = "date" dateStyle = "long" value="${article.publicationMoment}"/>
	
	<h1 style="text-align:center;"><jstl:out value="${article.title}" /></h1>	
	<h2 style="text-align:right;"><small><jstl:out value="${publicationMoment}" /></small></h2>&nbsp;
	<h4 style="text-align:left;"><jstl:out value="${article.summary}" /></h4>&nbsp; 
	<h4 style="text-align:left;"><jstl:out value="${article.body}" /></h4>	
	
	</div>
	<div id="myCarousel" class="carousel slide col-sm-12" data-ride="carousel">
	<h1 style="text-align:center;"><spring:message code="article.picturess" /></h1>
		<!-- Wrapper for slides -->
		<div class="carousel-inner">
			<jstl:forEach items="${article.picturess}" var="picture"
				varStatus="index">
				<jstl:choose>
					<jstl:when test="${index.index == 0}">
						<div class="item active">
							<img style="width:100%;" src="${picture}" />
						</div>
					</jstl:when>
					<jstl:otherwise>
						<div class="item">
							<img style="width:100%;" src="${picture}" />
						</div>
					</jstl:otherwise>
				</jstl:choose>
			</jstl:forEach>
			<!-- Left and right controls -->
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
	</div>
</div>

<div class="container col-sm-8 col-sm-offset-2 well">
	<h1 style="text-align:center;">FollowUps</h1>
</div>

<jstl:forEach items="${followUps}" var="followUp" varStatus="index">

	<div class="container col-sm-8 col-sm-offset-2 well">
		<div class="col-sm-12">
		<fmt:formatDate var="followUp.publicationMoment" type = "date" dateStyle = "long" value="${followUp.publicationMoment}"/>
		
		<h1 style="text-align:center;"><jstl:out value="${followUp.title}" /></h1>	
		<h2 style="text-align:right;"><small><jstl:out value="${followUp.publicationMoment}" /></small></h2>&nbsp;
		<h4 style="text-align:left;"><jstl:out value="${followUp.summary}" /></h4>&nbsp; 
		<h4 style="text-align:left;"><jstl:out value="${followUp.body}" /></h4>	
		
		</div>
		<div id="myCarousel2" class="carousel slide col-sm-12" data-ride="carousel2">
		<h1 style="text-align:center;"><spring:message code="article.picturess" /></h1>
			<!-- Wrapper for slides -->
			<div class="carousel-inner">
				<jstl:forEach items="${followUp.picturess}" var="picture2"	varStatus="index2">
					<jstl:choose>
						<jstl:when test="${index2.index == 0}">
							<div class="item active">
								<img style="width:100%;" src="${picture2}" />
							</div>
						</jstl:when>
						<jstl:otherwise>
							<div class="item">
								<img style="width:100%;" src="${picture2}" />
							</div>
						</jstl:otherwise>
					</jstl:choose>
				</jstl:forEach>
				<!-- Left and right controls -->
				<a class="left carousel-control" href="#myCarousel2" data-slide="prev">
					<span class="glyphicon glyphicon-chevron-left"></span> <span
					class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#myCarousel2"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>
		</div>
	</div>
	
</jstl:forEach>

<jstl:if test="${pageContext.request.userPrincipal.name eq article.newspaper.user.userAccount.username }">
	<a href="user/followUp/create.do?articleId=${article.id}">
		<spring:message code="followUp.new"/>
	</a>
</jstl:if>
