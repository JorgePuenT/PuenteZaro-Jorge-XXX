<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib" %>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>



<div class="well col-md-8 col-md-offset-2">

	
	<form:form action="user/volume/save.do" modelAttribute="volume">
		<jstl:set var='model' value='volume' scope='request'/>		
		
		<lib:input type="hidden" name="id,version"/>		
		<lib:input type="hidden" name="year"/>
		<lib:input type="hidden" name="newspapers"/>
		<lib:input type="hidden" name="user"/>
		<lib:input type="hidden" name="subscriptions"/>
		<lib:input type="text" name='title'/>
		<lib:input type="text" name='description'/>
		
		<div id="app">
			<h2><spring:message code="volume.newspapers"/></h2>
			<div class="well col-md-6">
				<h4><spring:message code="volume.newspapers.all"/></h4>
				<ol>
					<li v-for="item in allNP">
						<a v-on:click="left(item.id)">{{ item.name }}</a>
					</li>
				</ol>
			</div>
			<div class="well col-md-6">
				<h4><spring:message code="volume.newspapers.selected"/></h4>
				<ol>
					<li v-for="item in selectedNP">
						<a v-on:click="right(item.id)">{{ item.name }}</a>
					</li>
				</ol>
		</div>
		</div>

		

		<lib:button model='volume' noDelete='true' id='${newspaper.id}' cancelUri='volume/list.do'/>
	</form:form>
	<div id="app">
	</div>
	
	<script>
	
		//Creación del objeto Vue y introduccion de la lista de los periodicos
		var app = new Vue({
			el: '#app',
			data: {
				allNP: [
					<jstl:forEach items="${allNewspapers}" var="item">
						{
							id : '${item.id}',
							version : '${item.version}',
							name: '${item.title}',
						}<jstl:if test="${not x.last}">,</jstl:if>
					</jstl:forEach>
				],
				selectedNP:[],		
			},
			methods: {
				left: function (id) {
					console.log(id);
					for(var i=0; i< this.allNP.length; i++){
						if(this.allNP[i].id == id){
							this.selectedNP.push(this.allNP[i]);
							this.allNP.splice(i,1);
						}
					}
				},
				right: function (id) {
					console.log(id);
					for(var i=0; i< this.selectedNP.length; i++){
						if(this.selectedNP[i].id == id){
							this.allNP.push(this.selectedNP[i]);
							this.selectedNP.splice(i,1);
						}
					}
				}
			}
		});		

		
		/* //Template
		Vue.component('item', {
			props: ['prop'],
			template: '<li>{{prop.name}}</li>'
		}); */
		
		$('#volume').submit(function(){
			var list = "";
			for(var i=0; i< app.selectedNP.length; i++){
				list += app.selectedNP[i].id;
				if(i != app.selectedNP.length-1) list+=",";
			}
			$('#newspapers').val(list);
			return true;
		});
		
		$(function(){
			if($('#newspapers').val().length > 0){
				var newspapersId = $('#newspapers').val().split(",");
				$.each(newspapersId,function(key,val){
					app.left(val);
				});
			}
		});
		
		
		
		
	</script>
</div>
