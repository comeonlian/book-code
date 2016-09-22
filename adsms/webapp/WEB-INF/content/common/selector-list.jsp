<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<security:authorize ifAnyGranted="ROLE_ANONYMOUS"><c:redirect url="/common/403.jsp"/></security:authorize>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>油品选择</title>
<%@ include file="/common/head.jsp"%>
    <script type="text/javascript">
$(function(){
	parent.$("#treeload").hide();
	// 列表
	$("#autocompleteoil").focus();
	var eless = document.getElementsByName("ids"); 
	for(var j=0;j<eless.length;j++){ 
		var selval=eless[j].value;
		var allsel="'"+selval.split('-')[0]+"'";
		if(allsel==parent.$("#${name}").val()){ 
			parent.$("#selnm").val(selval.split("-")[1]);
			eless[j].checked=true;
			break; 
		} 
	} 
	//加油站
	if('gasStation'=='${param.type}'){
		oilautocompletesm();
	}else if('storage'=='${param.type}'){
		oilautocompletesm();
	//发到港站
	}else if('portStationInfo'=='${param.type}'){
		oilautocompletem();	
	}else {
		oilautocompletesm();
	}
});

function oilautocompletesm(){
	$("#autocompleteoil").autocomplete({
		minLength: 1,
		source:function(request, response) {
			$.post("selector!getAcDataSource.action", {filter_LIKES_code_OR_shortName:request.term,type:"${param.type}"},response);
		}, 
		focus: function(event, ui) {
			$('#autocompleteoil').val(ui.item[2]);
			return false;
		}
	}).data( "autocomplete" )._renderItem = function( ul, item ) {
		return $( "<li></li>" )
		.data( "item.autocomplete", item )
		.append( "<a>" + item[2] + "</a>" )
		.appendTo( ul );
	};
}

function oilautocompletem(){
	$("#autocompleteoil").autocomplete({
		minLength: 1,
		source:function(request, response) {
			$.post("selector!getAcDataSource.action", {filter_LIKES_code_OR_name:request.term,type:"${param.type}"},response);
		}, 
		focus: function(event, ui) {
			$('#autocompleteoil').val(ui.item[2]);
			return false;
		}
	}).data( "autocomplete" )._renderItem = function( ul, item ) {
		return $( "<li></li>" )
		.data( "item.autocomplete", item )
		.append( "<a>" + item[2] + "</a>" )
		.appendTo( ul );
	};
}

function radionsel(m){
	if(m.checked){
		parent.$("#${name}").val("'"+m.value.split("-")[0]+"'");
		parent.$("#parent_pageno").val(${page.pageNo});
		parent.$("#selnm").val(m.value.split("-")[1]);
	}
}
</script>
<style type="text/css">
.pageJump{
	padding: 0px;
}
div.pagination{
	padding: 0px;
	margin: 0px;
}

.ui-autocomplete {
	max-height: 200px;
	overflow-y: auto;
}
</style>
</head>
<body>
<div id="main">
	<div id="content" class="content">
    <div id="indiv" style="width:100%;overflow:auto;height:100%">
    <form id="mainForm1" action="selector!list.action" method="post">
	 	<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>
		<input type="hidden" name="authId" id="authId" value="${authId}"/>
		<input type="hidden" name="type" id="type" value="${param.type}"/>
		<input type="hidden" name="name" id="name" value="${name}"/>
		<input type="hidden" name="page.pageSize" id="pageSize" value="${page.pageSize}" />
	
    <c:if test="${param.type=='gasStation'}">
    <div class="autosel">
    	  加油站编码：<input id="autocompleteoil" type="text" name="filter_LIKES_code_OR_shortName" value="${param.filter_LIKES_code_OR_shortName}" class="middle"/>  <input type="submit" value="提交"/>
    </div>
    <table class="tab_cont" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
    <tbody>
		  <tr>
		      <th class="first"></th>
		      <th><a href="javascript:sort('id','asc')">序号</a></th>
		      <th><a href="javascript:sort('code','asc')">加油站编码</a></th>
		      <th><a href="javascript:sort('shortName','asc')">加油站简称</a></th>
		      <th>所属区域</th>
		    </tr>
			<c:forEach items="${page.result}" var="c">
				<tr>
					<td><input type="radio" onclick="radionsel(this);" value="${c.id}-${c.shortName}" id="id_${c.id}" name="ids"/></td>
					<td height="22">${c.id}</td>
					<td>${c.code}</td>
					<td>${c.shortName}</td>
					<td>
						<c:forEach items="${domainList}" var="d">
							<c:if test="${d.id==c.domainId}">${d.label}</c:if>										
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
    </tbody>
    </table>
    </c:if>
    <c:if test="${param.type=='storage'}">
   <div class="autosel">
    	  油库编码：<input id="autocompleteoil" name="filter_LIKES_code_OR_shortName" value="${param.filter_LIKES_code_OR_shortName}" class="middle"/>  <input type="submit" value="提交"/>
    </div>
    <table class="tab_cont" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
	    <tbody>
			  <tr>
			      <th class="first"></th>
    			  <th><a href="javascript:sort('id','asc')">序号</a></th>
		          <th><a href="javascript:sort('code','asc')">油库编码</a></th>
		          <th><a href="javascript:sort('shortName','asc')">油库简称</a></th>
		          <th  class="last">所属区域</th>
			    </tr>
				
				<c:forEach items="${page.result}" var="c">
					<tr>
						<td><input type="radio" onclick="radionsel(this);" value="${c.code}-${c.shortName}" id="id_${c.id}" name="ids"/></td>
						<td height="22">${c.id}</td>
						<td>${c.code}</td>
						<td>${c.shortName}</td>
						<td>${c.parent.label}</td>
					</tr>
				</c:forEach>
	    </tbody>
	    </table>
    </c:if>
    <c:if test="${param.type=='factory'}">
    <div class="autosel">
    	  炼厂编码：<input id="autocompleteoil" class="middle" name="filter_LIKES_code_OR_shortName" value="${param.filter_LIKES_code_OR_shortName}"  />  <input type="submit" value="提交"/>
    </div>
    <table class="tab_cont" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
    <tbody>
		  <tr>
		      <th class="first"></th>
		      <th><a href="javascript:sort('id','asc')">序号</a></th>
		      <th><a href="javascript:sort('code','asc')">炼厂编码</a></th>
		      <th><a href="javascript:sort('shortName','asc')">炼厂简称</a></th>
		      <th class="last">所属区域</th>
		    </tr>
			
			<c:forEach items="${page.result}" var="c">
				<tr>
					<td><input type="radio" onclick="radionsel(this);" value="${c.code}-${c.shortName}" id="id_${c.id}" name="ids"/></td>
					<td height="22">${c.id}</td>
					<td>${c.code}</td>
					<td>${c.shortName}</td>
					<td>${c.parent.label}</td>
				</tr>
			</c:forEach>
    </tbody>
    </table>
    </c:if>
    
    <c:if test="${param.type=='portStationInfo'}">
    <div class="autosel">
    	  发到港站编码：<input id="autocompleteoil" class="middle" name="filter_LIKES_code_OR_name" value="${param.filter_LIKES_code_OR_name}"  />  <input type="submit" value="提交"/>
    </div>
    <table class="tab_cont" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
    <tbody>
		  <tr>
		      <th class="first"></th>
		      <th><a href="javascript:sort('id','asc')">序号</a></th>
		      <th><a href="javascript:sort('code','asc')">发到港站编码</a></th>
		      <th class="last"><a href="javascript:sort('name','asc')">发到港站简称</a></th>
		    </tr>
			
			<c:forEach items="${page.result}" var="c">
				<tr>
					<td><input type="radio" onclick="radionsel(this);" value="${c.code}-${c.name}" id="id_${c.id}" name="ids"/></td>
					<td height="22">${c.id}</td>
					<td>${c.code}</td>
					<td>${c.name}</td>
				</tr>
			</c:forEach>
    </tbody>
    </table>
    </c:if>
    </form>
    </div>
  </div>
  <!-- 分页区域-->
  <div id="page" class="page">
  	<wlps:page page="${page}" simpleTheme="true" />
  </div> 
</div>
</body>
</html>