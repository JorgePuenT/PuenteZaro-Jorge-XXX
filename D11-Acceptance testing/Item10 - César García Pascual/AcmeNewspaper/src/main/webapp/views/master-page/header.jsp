<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Modal to display the notifications from an ajax method -->
   		<div id="notificationDiv">
   			<div id="notificationAlert" class=""></div>
   		</div>
<!-- Function to receive parameters and message for modal ajax notifications -->
		<script>
   			function notify(i,msg){
   				if(!(typeof notificationTimeout ==="undefined"))clearTimeout(notificationTimeout);
   				$('#notificationAlert').html(''); 
   				$('#notificationAlert').attr('class','alert alert-'+i);
				$('#notificationAlert').prepend(msg);
				$('#notificationDiv').show('slow');
				notificationTimeout = setTimeout(function() {
			        $("#notificationDiv").hide('slow');
			        $('#notificationAlert').html('');  
			        }, 7000);
   			}
   		</script>
<!-- Notification For Ajax Commit Error Messages -->
<jstl:if test="${message ne null}">
	<script>
	$(function(){
		notify('danger',"<spring:message code="${message}"/>");
	});
	</script>
</jstl:if>

<!-- NAVBAR -->
<nav class="navbar navbar-inverse navbar-fixed-top" style="z-index:1500">
	<div class="containter-fluid">
		<!-- Navbar collapsed open button -->
		<div class="navbar-header">
		 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>                        
	      </button>
			<a class="navbar-brand" href="#">AcmeNewspaper</a>
		</div>
		<!-- Navbar Content -->
		<div class="collapse navbar-collapse" id="myNavbar">
		
		<!-- Left-side main content -->
		
		<ul class="nav navbar-nav">	
			
			<li><button onClick="javascript:window.location.href = 'lucene/search.do'" class="btn btn-success navbar-btn"><spring:message code="master.page.lucene.search" /></button></li>		
			<li><a href="newspaper/list.do"><spring:message code="master.page.newspaper.list" /></a></li>
			<li><a href="volume/list.do"><spring:message code="master.page.volume.list" /></a></li>
			
			<security:authorize access="hasRole('USER')">
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="master.page.article.list" />
					<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="article/list.do"><spring:message code="master.page.article.list" /></a></li>
							<li><a href="user/article/list.do"><spring:message code="master.page.myArticle.list" /></a></li>
						</ul>
				</li>
			</security:authorize>
			<security:authorize access="!hasRole('USER')">
				<li><a href="article/list.do"><spring:message code="master.page.article.list" /></a></li>
			</security:authorize>
			<li><a href="user-list.do"><spring:message code="master.page.actor.list" /></a></li>
			<security:authorize access="hasRole('USER')">
				<li><a href="user/chirp/timeline.do"><spring:message code="master.page.chirp.list" /></a></li>
				<li class="btn-group">
					<button onClick="javascript:window.location.href = 'user/newspaper/create.do'" class="btn btn-success navbar-btn"><spring:message code="master.page.newspaper.new" /></button>
					<button onClick="javascript:window.location.href = 'user/article/create.do'" class="btn btn-success navbar-btn"><spring:message code="master.page.article.new" /></button>
					<button onClick="javascript:window.location.href = 'user/chirp/create.do'" class="btn btn-success navbar-btn"><spring:message code="master.page.chirp.new" /></button>
					<button onClick="javascript:window.location.href = 'user/volume/create.do'" class="btn btn-success navbar-btn"><spring:message code="master.page.volume.new" /></button>
				</li>
			</security:authorize>
			
			<security:authorize access="hasRole('AGENT')">
				<button onClick="javascript:window.location.href = 'agent/advertisement/create.do'" class="btn btn-success navbar-btn"><spring:message code="master.page.advertisement.new" /></button>
			</security:authorize>
			
			
			<security:authorize access="hasRole('ADMIN')">
				<li><a href="admin/chirp/list.do"><spring:message code="master.page.chirp.list" /></a></li>
				<li><a href="admin/advertisement/list.do"><spring:message code="master.page.advertisement.list" /></a></li>
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="master.page.taboo" />
					<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="admin/article/taboo-list.do"><spring:message code="master.page.article.list.taboo" /></a></li>
							<li><a href="admin/newspaper/taboo-list.do"><spring:message code="master.page.newspaper.list.taboo" /></a></li>
							<li><a href="admin/chirp/taboo-list.do"><spring:message code="master.page.chirp.list.taboo" /></a></li>
							<li><a href="admin/advertisement/taboo-list.do"><spring:message code="master.page.advertisement.list.taboo" /></a></li>
						</ul>
				</li>
				<li><a href="systemConfig/edit.do"><spring:message code="master.page.systemConfig" /></a></li>
				<li><a href="admin/dashboard.do"><spring:message code="master.page.dashboard" /></a></li>
				<button onClick="javascript:window.location.href = 'admin/broadcast.do'" class="btn btn-success navbar-btn"><spring:message code="master.page.broadcast" /></button>
			</security:authorize>
		</ul>
	
		<!-- Right-side content -->	
		<ul class="nav navbar-nav navbar-right">
		
			<!-- Language Buttons -->
			<security:authorize access="isAuthenticated()">
				<li style="margin-right:5px">
					<button onClick="javascript:window.location.href = 'profile/message/list.do'" class="btn navbar-btn btn-primary" >&nbsp; <span class="glyphicon glyphicon-envelope"></span> &nbsp;</button>
				</li>
			</security:authorize>
			
			<li class="btn-group">	
				<button onClick="javascript:window.location.href = '?language=en'" class="btn navbar-btn btn-info" >en</button>
				<button onClick="javascript:window.location.href = '?language=es'" class="btn navbar-btn btn-info" >es</button>
			</li>
			
			
		
			
				<security:authorize access="hasRole('USER')">
					<li><a href="user-display.do"><span class="glyphicon glyphicon-user"></span> <security:authentication property="principal.username"/></a></li>
					
				</security:authorize>
				
				<security:authorize access="hasRole('CUSTOMER')">
					<li><a href="#"><span class="glyphicon glyphicon-user"></span> <security:authentication property="principal.username"/></a></li>
				</security:authorize>
								<security:authorize access="hasRole('ADMIN')">
					<li><a href="user-display.do"><span class="glyphicon glyphicon-user"></span> <security:authentication property="principal.username"/></a></li>
				</security:authorize>
				
					
				
				
			<security:authorize access="isAuthenticated()">
				<li><a href="j_spring_security_logout"><span class="glyphicon glyphicon-log-out"> </span><spring:message code="master.page.logout" /> &nbsp; </a></li>
			</security:authorize>
			
			<security:authorize access="isAnonymous()">
				<li><a href="register/user.do"><span class="glyphicon glyphicon-user"></span> <spring:message code="master.page.registerUser" /> &nbsp;</a></li>
				<li><a href="register/customer.do"><span class="glyphicon glyphicon-user"></span> <spring:message code="master.page.registerCustomer" /> &nbsp;</a></li>
				<li><a href="register/agent.do"><span class="glyphicon glyphicon-user"></span> <spring:message code="master.page.registerAgent" /> &nbsp;</a></li>
				<li><a  href="security/login.do"><span class="glyphicon glyphicon-log-in"></span> <spring:message code="master.page.login" /> &nbsp; </a></li>
			</security:authorize>
		</ul>
		</div>
	</div>
	
	
</nav>

<%-- ---------------Templates---------------- 
		
		------------ Normal Link (PermitAll)-------------
		<li><a href=""><spring:message code="--" /></a></li>
		
		------------ Normal Link (Security) -------------
		<security:authorize access="hasRole('--')">
		<li><a href=""><spring:message code="--" /></a></li>
		</security:authorize>
		
		------------ Button ------------------------------
		<button onClick="javascript:window.location.href = '--'" class="btn btn-danger navbar-btn"><spring:message code="--" /></button>
		
		--------------- Search Input With Button ---------
		<form class="navbar-form navbar-left" action="--">
		  <div class="input-group">
		    <input id="--" type="text" class="form-control" name="--" placeholder='<spring:message code="--"/>'>
		    <div class="input-group-btn">
		      <button class="btn btn-default" type="submit">
		       <spring:message code="--"/>
		      </button>
		    </div>
		  </div>
		</form>
		
		 --%>
