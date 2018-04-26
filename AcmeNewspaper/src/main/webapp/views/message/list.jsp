<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div class="container-fluid">
<jstl:out value="${error}"/>
 
<div class="col-md-2">

<!--  system folders -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="systemFolders" requestURI="${requestUri}" id="rowSystemFolder">
	<display:setProperty name="paging.banner.onepage" value=""/>
	<display:setProperty name="paging.banner.all_items_found" value=""/>
	<spring:message code="folder.systemName" var="nameHeader"/>
	<display:column title="${nameHeader}:">
		<a href="profile/message/list.do?folderId=${rowSystemFolder.id}">
			<jstl:out value="${rowSystemFolder.name}"/>
		</a>
	</display:column>
</display:table>

<!-- to create a new folder in the same view -->
<button type="button" style="width:95%" class="btn btn-primary btn-group-justified" id="newFolderButton"><spring:message code="folder.new"/></button>

<!-- form to create a new folder -->
<div id="newFolderDiv" style="display:none">
	<form:form action="profile/message/newFolder.do" modelAttribute="newFolder" style="width:95%">
	
	<form:hidden path="system"/>
	<form:hidden path="folders"/>
	<form:hidden path="messages"/>
	
	<div class="form-group">
	<form:label path="name" class="control-label">
		<spring:message code="folder.name" />:
	</form:label>
	<form:input class="form-control" path="name" />
	<form:errors cssClass="error" path="name" />
	</div>
	
	<div class="form-group">
	<form:label path="parent">
		<spring:message code="folder.parent"/>
	</form:label>
	<form:select class="form-control" path="parent">
		<option value selected="selected"><spring:message code="folder.none"/></option>
		<jstl:forEach items="${lvl1Folders}" var="existingFolder">
  			<option value="${existingFolder.id}"> <jstl:out value="${existingFolder.name}" /></option>
  		</jstl:forEach>
	</form:select>
	</div>
	
	<div class="btn-group btn-group-justified">
	<div class="btn-group">
		<input class="btn btn-success" type="submit" name="save"
		value="<spring:message code="folder.save"/>" />
	</div>
	<div class="btn-group">
		<input class="btn btn-default" id="newFolderCancel" type="button" name="cancel"
		value="<spring:message code="folder.cancel" />" />
	</div>
	</div>
	</form:form>
</div>

<!-- list of the actor's folders -->
<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="folders" requestURI="${requestUri}" id="rowFolder">
	<display:setProperty name="paging.banner.placement" value="bottom"/>
	<display:setProperty name="basic.empty.showtable" value="true"/>
	
	<jstl:choose>
			<jstl:when test="${actualFolder.system eq true }">
				<spring:message code="folder.nameHeader" var="nameHeader"/>
			</jstl:when>
			<jstl:otherwise>
<!-- 			to return to the list of folders -->
				<jstl:set value='<a href="profile/message/list.do?folderId=${actualFolder.parent.id}">${actualFolder.name}</a>' var="nameHeader"/>
			</jstl:otherwise>
		</jstl:choose>
	
	<display:column title="${nameHeader }">
	<input type="text" class="folderId" readonly="readonly" value="${rowFolder.id}" style="display:none"/>
	<a class="nameLink" href="profile/message/list.do?folderId=${rowFolder.id}">
			<jstl:out value="${rowFolder.name}"/>
	</a>
	<input style="display:none" type="text" class="newName" placeholder="${rowFolder.name}" size="10"/>

	</display:column>
	<display:column>
		<button type="button" class="prepareEdit btn btn-warning btn-block"><spring:message code="folder.edit"/></button>
		<button style="display:none" type="button" class="commitEdit btn btn-success"><spring:message code="folder.edit.save"/></button>
	</display:column>
	<display:column>
		<button type="button" class="deleteFolderButton btn btn-danger btn-block"><spring:message code="message.delete"/></button>
		<button style="display:none" type="button" class="cancelEdit btn btn-default"><spring:message code="folder.cancel"/></button>
	</display:column>
</display:table>

</div> 
<div class="col-md-10">

<!-- form to write a new message -->
<!-- if show message is null, it shows the form to write a message(you have to click on the button to see this form) and the table of messages -->
<jstl:choose>
<jstl:when test="${showMessage eq null}">
<button type="button" class="btn btn-primary btn-group-justified" style="width:95%" id="writeButton"><spring:message code="message.write"/></button>

<div id="writeDiv" style="display:none; width:95%">
	<form:form action="profile/message/send.do" modelAttribute="newMessage">
	
	
	<form:hidden path="actorFrom"/>
	<form:hidden path="sentTime"/>
	<form:hidden path="folder"/>
	<div id="actorToErrorAlert" style="display:none" class="alert alert-danger">
		<strong><spring:message code="message.actorToErrorHeader"/>!</strong> <spring:message code="message.actorToErrorMsg"/>
	</div>
	<div class="form-inline" style="width:100%">
	<div class="form-group">
	<form:label path="subject">
		<spring:message code="message.subject" />:
	</form:label>
	<form:input class="form-control" path="subject" />
	<form:errors cssClass="error" path="subject" />
	</div>
	
	<div class="form-group" >
	<form:label path="actorTo" class="control-label">
		<spring:message code="message.to" />:
	</form:label>
	<div class="form-group">
		<select class="selectpicker" data-live-search="true" name="actorTo">
			<option value="" >alskdjf</option>
			<jstl:forEach items="${actorsTo}" var="receiver">
				<option value="${receiver.id}">${receiver.userAccount.username}</option>
			</jstl:forEach>
		</select>
	</div>
	<form:errors cssClass="error" path="actorTo" />
	</div>
	<div class="form-group">
	<form:label path="priority">
		<spring:message code="message.priority" />:
	</form:label>
	<form:select class="form-control" path="priority">
  		<option value="LOW"><spring:message code="message.priority.low"/></option>
	  	<option value="NEUTRAL" selected="selected"><spring:message code="message.priority.neutral"/></option>
	  	<option value="HIGH"><spring:message code="message.priority.high"/></option>
	</form:select>
	</div>
	</div>
	
	<div class="form-group" style="margin-top:10px">
	<form:textarea  class="form-control" path="body" rows="5" cols="100"/>
	<form:errors cssClass="error" path="body" />
	</div>
	
	<div class="btn-group btn-group-justified">
	<div class="btn-group">
		<input id="newMessageSubmitButton" class="btn btn-success" type="submit" name="save"
		value="<spring:message code="message.save"/>" />
	</div>
	<div class="btn-group">
		<input class="btn btn-default" id="writeCancel" type="button" name="cancel"
		value="<spring:message code="message.cancel" />" />
	</div>
	</div>
	</form:form>
</div>

<div class="table-responsive">
<!-- messages of the folder you are in -->
<display:table pagesize="20" class="displaytag" keepStatus="true"
	name="messages" requestURI="${requestUri}" id="row">
	<display:setProperty name="paging.banner.placement" value="bottom"/>
	<display:setProperty name="basic.empty.showtable" value="true"/>
	<spring:message code="message.noneFound" var="noneFound"/>
	<display:setProperty name="basic.msg.empty_list_row" value='<tr class="empty">
		<td colspan="7" style="text-align:center"> ${noneFound} </td></tr></tr>' />
	
	
	<spring:message code="message.priority" var="priorityHeader"/>
	<display:column property="priority" title="${priorityHeader}" sortable="true"/>
	
<!-- 	to display the info of a message -->
	<spring:message code="message.subject" var="subjectHeader"/>
	<display:column title="${subjectHeader}" href="profile/message/list.do?actualFolder=${actualFolder.id}" paramId="messageId" paramProperty="id">
		<jstl:choose>
		<jstl:when test="${empty row.subject}" >
			<spring:message code="message.emptySubject"/>
		</jstl:when>
		<jstl:otherwise>
			<jstl:out value="${row.subject}"/>
		</jstl:otherwise>
		</jstl:choose>
	</display:column>
	
	<spring:message code="message.sentTime" var="sentTimeHeader"/>
	<display:column property="sentTime" title="${sentTimeHeader}" sortable="true"/>
	
	<spring:message code="message.from" var="fromHeader"/>
	<display:column title="${fromHeader}:">
		<jstl:choose>
		<jstl:when test="${pageContext.request.userPrincipal.name eq row.actorFrom.userAccount.username }">
			<spring:message code="message.me"/>
		</jstl:when>
		<jstl:otherwise>
			<jstl:out value="${row.actorFrom.name} ${row.actorFrom.surnames}"/>
		</jstl:otherwise>
		</jstl:choose>	
	</display:column>
	
	<spring:message code="message.to" var="toHeader"/>
	<display:column title="${toHeader}:">
		<jstl:choose>
		<jstl:when test="${pageContext.request.userPrincipal.name eq row.actorTo.userAccount.username }">
			<spring:message code="message.me"/>
		</jstl:when>
		<jstl:otherwise>
			<jstl:out value="${row.actorTo.name} ${row.actorTo.surnames}"/>
		</jstl:otherwise>
		</jstl:choose>	
	</display:column>
	
	<display:column>
	<div class="input-group btn-group-justified" style="max-width:250px; min-width:100px">
		<input type="text" class="moveId" style="display:none" value="${row.id}"/> 
		<select class="moveSelector  form-control">
		<option value selected="true" disabled="true" hidden="true">--<spring:message code="message.selectFolder"/>--</option>
			<jstl:forEach items="${lvl1Folders}" var="fol">
				<option value="${fol.id}">${fol.name}</option>
				<jstl:if test="${!empty fol.folders}">
					<jstl:forEach items="${fol.folders}" var="subFol">
						<option value="${subFol.id}">&emsp;${subFol.name}</option>
					</jstl:forEach>
				</jstl:if>
			</jstl:forEach>
		</select>
		<div class="input-group-btn">
			<button type="button" class="moveButton btn btn-primary"><spring:message code="message.move"/></button>
		</div>
	</div>
	</display:column>
	<display:column>
		<button type="button" class="deleteMessageButton btn btn-danger btn-block" ><spring:message code="message.delete"/></button>
	</display:column>
	
</display:table>
</div>

</jstl:when>
<jstl:otherwise>
<!-- // if show message is set, we show a form to display the message info(readonly=true) -->
<div style="width:95%" align="center">
<form:form action="" modelAttribute="showMessage" >
	
	<div class="form-inline text-center" style="width:100%;margin-bottom:10px;display:flex;justify-content:space-around">
	<div class="form-group">
	<form:label class="control-label" path="subject">
		<spring:message code="message.subject" />:
	</form:label>
	<form:input class="form-control" path="subject" readonly="true"/>
	<form:errors cssClass="error" path="subject" />
	</div>
	<div class="form-group">
	<form:label class="control-label" path="sentTime">
		<spring:message code="message.sentTime" />:
	</form:label>
	<form:input class="form-control" path="sentTime" readonly="true"/>
	<form:errors cssClass="error" path="sentTime" />
	</div>
	<div class="form-group">
	<form:label class="control-label" path="folder">
		<spring:message code="message.folder" />:
	</form:label>
	<form:input class="form-control" path="folder.name" readonly="true" />
	<form:errors cssClass="error" path="folder" />
	</div>
	</div>
	
	<div class="form-inline text-center" style="width:100%;margin-bottom:10px;display:flex;justify-content:space-around">
	<div class="form-group">	
	<jstl:choose>
		<jstl:when test="${pageContext.request.userPrincipal.name eq showMessage.actorFrom.userAccount.username }">
			<spring:message code="message.me" var="actorFromPlaceholder"/>
		</jstl:when>
		<jstl:otherwise>
			<jstl:set value='${showMessage.actorFrom.name} ${showMessage.actorFrom.surnames }' var="actorFromPlaceholder"/>
		</jstl:otherwise>
	</jstl:choose>	
	<form:label path="actorFrom">
		<spring:message code="message.from" />:
	</form:label>
	<form:input  class="form-control" path="actorFrom" readonly="true" value="${actorFromPlaceholder}"/>
	<form:errors cssClass="error" path="actorFrom" />
	</div>
	<div class="form-group">
	<jstl:choose>
		<jstl:when test="${pageContext.request.userPrincipal.name eq showMessage.actorTo.userAccount.username }">
			<spring:message code="message.me" var="actorToPlaceholder"/>
		</jstl:when>
		<jstl:otherwise>
			<jstl:set value='${showMessage.actorTo.name} ${showMessage.actorTo.surnames }' var="actorToPlaceholder"/>
		</jstl:otherwise>
	</jstl:choose>	
	<form:label path="actorTo">
		<spring:message code="message.to" />:
	</form:label>
	<form:input class="form-control" path="actorTo" readonly="true" value="${actorToPlaceholder}"/>
	<form:errors cssClass="error" path="actorTo" />
	</div>
	<div class="form-group">
	<form:label path="priority">
		<spring:message code="message.priority" />:
	</form:label>
	<form:input class="form-control" path="priority" readonly="true"/>
	<form:errors cssClass="error" path="priority" />
	</div>
	</div>
	
	<div class="form-group">
	<form:textarea  class="form-control" path="body" rows="10" cols="100" readonly="true"/>
	<form:errors cssClass="error" path="body" />
	</div>

	</form:form>
	<div class="input-group" style="width:40%;min-width:250px">
	<!-- 	to move a folder -->
		<input type="text" class="moveId" style="display:none" value="${showMessage.id}"/> 
		<select class="moveSelector  form-control">
		<option value selected="true" disabled="true" hidden="true">--Select a folder--</option>
			<jstl:forEach items="${lvl1Folders}" var="fol">
				<option value="${fol.id}">${fol.name}</option>
				<jstl:if test="${!empty fol.folders}">
					<jstl:forEach items="${fol.folders}" var="subFol">
						<option value="${subFol.id}">&emsp;${subFol.name}</option>
					</jstl:forEach>
				</jstl:if>
			</jstl:forEach>
		</select>
		<div class="input-group-btn">
		<button type="button" class="moveButton btn btn-primary">Move</button>

		<button type="button" class="deleteViewMessageButton btn btn-danger">Delete</button>
		</div>
	</div>
	</div>
	


</jstl:otherwise>
</jstl:choose>

</div>


</div>

<script>
	$('#writeButton').click(function(){
		$('#writeDiv').show();
		$('#writeButton').hide();
	});
	$('#writeCancel').click(function(){
		$('#writeDiv').hide();
		$('#writeButton').show();
	});
	$('#newFolderButton').click(function(){
		$('#newFolderDiv').show();
		$('#newFolderButton').hide();
	});
	$('#newFolderCancel').click(function(){
		$('#newFolderDiv').hide();
		$('#newFolderButton').show();
	});
	$('.moveButton').click(function(){
		if($(this).parent().parent().find('.moveSelector').val() != null){
		location.href = "profile/message/moveMessage.do?messageId=" + $(this).parent().parent().find('.moveId').val() + "&folderId=" + $(this).parent().parent().find('.moveSelector').val();
		}else{
			alert("Please select a folder to move this message to.");
		}
	});
	$('.deleteMessageButton').click(function(){
		location.href = "profile/message/deleteMessage.do?messageId=" + $(this).parent().prev().find('.moveId').val();
	});
	$('.deleteViewMessageButton').click(function(){
		location.href = "profile/message/deleteMessage.do?messageId=" + $(this).parent().prev().prev().val();
	});
	$('.prepareEdit').click(function(){
		$(this).parent().prev().find(".nameLink").hide();
		$(this).parent().prev().find(".newName").show();
		$(this).parent().find(".commitEdit").show();
		$(this).parent().next().find(".cancelEdit").show();
		$(this).parent().next().find(".deleteFolderButton").hide();
		$(this).hide();
	});
	$('.commitEdit').click(function(){
		var txtInput = $(this).parent().prev().find(".newName");
		if(txtInput.val().length == 0){
			txtInput.css("border-color","red");
		}else{
			location.href = "profile/message/editFolder.do?folderId=" + $(this).parent().prev().find(".folderId").val() + "&folderName=" + $(this).parent().prev().find(".newName").val();
		}	
	});
	$('.cancelEdit').click(function(){
		$(this).parent().prev().prev().find(".nameLink").show();
		$(this).parent().prev().prev().find(".newName").hide();
		$(this).parent().prev().find(".commitEdit").hide();
		$(this).hide();
		$(this).parent().find(".deleteFolderButton").show();
		$(this).parent().prev().find(".prepareEdit").show();
		$(this).parent().prev().prev().find(".newName").val('');
	});
	$('.deleteFolderButton').click(function(){
		location.href = "profile/message/deleteFolder.do?folderId=" + $(this).parent().prev().prev().find(".folderId").val();
	});
	$('#newFolder').submit(function(e){
		if($('#name').val().length == 0){
			e.preventDefault();
			$('#name').parent().addClass("has-error");
		}
	});
	$('#newMessage').submit(function(e){
		if($('.selectpicker').selectpicker('val').length == 0 ){
			$('#actorTo').parent().addClass("has-error");
			$('#actorToErrorAlert').show();
			return false;
		}else{
			return true;
		}
	});
</script>