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
	var value=parent.$("#${name}").val().split(",");
	var selArray = [];
	if(value!=""){$(value).each(function(i,item){selArray[i]=item.replace(/^\'/,'').replace(/\'$/,'');});}
	for(var j=0;j<eless.length;j++){ 
		var selval=eless[j].value;
		var allsel="'"+selval.split('-')[0]+"'";
		for(var k=0;k<selArray.length;k++){
			if(allsel==value[k]){ 
				var name = parent.$("#selnm").val();
				var newname="";
				var b = false;
				if(name!==''){
					for(var l =0;l<name.split(",").length;l++){
						if(name.split(",")[l]==selval.split("-")[1]){
							b=true;
							break;
						}
				    }
				    if(!b){
					newname=name+","+selval.split("-")[1];
				    }else{
				    	newname=name;
					 }
				}else{
					newname=selval.split("-")[1];
				}
				parent.$("#selnm").val(newname);
				eless[j].checked=true;
				break; 
			} 
		}
	} 
    // 自动提交
    <c:if test="${param.type!='pipelineStop'}">
		oilautocompletem();
	</c:if>
    <c:if test="${param.type=='pipelineStop'}">
    	oilautocompletemps();
	</c:if>
});

function oilautocompletem(){
	$("#autocompleteoil").autocomplete({
		minLength: 1,
		source:function(request, response) {
			$.post("selector!getAcDataSource.action", {filter_LIKES_code_OR_name:request.term,type:"${param.type}",filter_EQB_outset:"${param.filter_EQB_outset}"},response);
		}, 
		focus: function(event, ui) {
			$('#autocompleteoil').val(ui.item[2]);
			return false;
		}
	}).data( "autocomplete" )._renderItem = function( ul, item ) {
		return $( "<li></li>" )
		.data( "item.autocomplete", item )
		.append( "<a>" +item[1]+"-----"+item[2] + "</a>")
		.appendTo( ul );
	};
}
function oilautocompletemps(){
	$("#autocompleteoil").autocomplete({
		minLength: 1,
		source:function(request, response) {
			$.post("selector!getAcDataSource.action", {filter_LIKES_stopCode_OR_stopName:request.term,type:"${param.type}",filter_EQB_outset:"${param.filter_EQB_outset}"},response);
		}, 
		focus: function(event, ui) {
			$('#autocompleteoil').val(ui.item[2]);
			return false;
		}
	}).data( "autocomplete" )._renderItem = function( ul, item ) {
		return $( "<li></li>" )
		.data( "item.autocomplete", item )
		.append( "<a>"+item[1]+"-----"+item[2] + "</a>" )
		.appendTo( ul );
	};
}

function checksel(m){
	if(m.checked){
		var initdata=parent.$("#${name}").val();
		var nowdata=initdata==''? "'"+m.value.split("-")[0]+"'":initdata+",'"+m.value.split("-")[0]+"'";
		var name = parent.$("#selnm").val();
		var newname= name==''?m.value.split("-")[1]:name+","+m.value.split("-")[1];
		parent.$("#${name}").val(nowdata);
		//parent.$("#parent_pageno").val(${page.pageNo});
		parent.$("#selnm").val(newname);
	}else{
		var pvalue=m.value;
		var selArray = [];
		var newValue="",newName="";
		var value=parent.$("#${name}").val().split(",");
		var name=parent.$("#selnm").val().split(",");
		if(value!=""){$(value).each(function(i,item){selArray[i]=item.replace(/^\'/,'').replace(/\'$/,'');});}
		for(var i=0;i<selArray.length;i++){
			if(selArray[i]!=pvalue.split("-")[0]){
				newValue=newValue+"'"+selArray[i]+"',";
			}
			if(name[i]!=pvalue.split("-")[1]){
				newName = newName+name[i]+",";
			}
		}
		parent.$("#${name}").val(newValue.substring(0,newValue.length-1));
		parent.$("#selnm").val(newName.substring(0,newName.length-1));
		
	}
}
function listsSubmit(type){
	$("#mainForm_lists").get(0).action="selector!lists.action?filter_EQB_outset="+type;
	$("#mainForm_lists").get(0).submit();
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
    <form id="mainForm_lists" action="selector!lists.action" method="post">
	 	<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>
		<input type="hidden" name="authId" id="authId" value="${authId}"/>
		<input type="hidden" name="type" id="type" value="${param.type}"/>
		<input type="hidden" name="name" id="name" value="${name}"/>
		<input type="hidden" name="page.pageSize" id="pageSize" value="${page.pageSize}" />
	
    <c:if test="${param.type=='flow'}">
    <div class="autosel">
    	  流向编码：<input id="autocompleteoil" type="text" name="filter_LIKES_code_OR_name" value="${param.filter_LIKES_code_OR_name}" class="middle"/>  <input type="submit" value="提交"/>
    </div>
    <table class="tab_cont" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
    <tbody>
		  <tr>
		      <th class="first"></th>
		      <th>流向简称</th>
		      <th class="last">流向编码</th>
		    </tr>
			<c:forEach items="${page.result}" var="c">
				<tr>
					<td><input type="checkbox" onclick="checksel(this);"  value="${c.code}-${c.name}" id="id_${c.id}" name="ids"/></td>
				    <td>${c.name}</td>
					<td>${c.code}</td>
				</tr>
			</c:forEach>
    </tbody>
    </table>
    </c:if>
    <c:if test="${param.type=='dic'}">
    <div class="autosel" style="display: none">
    	  运输方式：<input id="autocompleteoil" type="text" name="filter_LIKES_code_OR_name" value="${param.filter_LIKES_code_OR_name}" class="middle"/>  <input type="submit" value="提交"/>
    </div>
    <table class="tab_cont" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
    <tbody>
		    <tr>
		      <th class="first"></th>
		      <th>运输名称</th>
		      <th class="last">运输编码</th>
		    </tr>
			<c:forEach items="${page.result}" var="c">
			    <c:if test="${c.code=='trans_type'}">
			    <c:forEach items="${c.dicitem}" var="d">
				<tr>
					<td><input type="checkbox" onclick="checksel(this);"  value="${d.code}-${d.name}" id="id_${d.id}" name="ids"/></td>
					<td>${d.name}</td>
					<td height="22">${d.code}</td>
				</tr>
				</c:forEach>
				</c:if>
			</c:forEach>
    </tbody>
    </table>
    </c:if>
    <c:if test="${param.type=='portStationInfo'}">
    <div class="autosel">
    	  <c:if test="${!param.filter_EQB_outset}">发站：</c:if> <c:if test="${param.filter_EQB_outset}">到站：</c:if>
    	  <input id="autocompleteoil" class="middle" name="filter_LIKES_code_OR_name" value="${param.filter_LIKES_code_OR_name}"  />  <input type="button" onclick="listsSubmit(${param.filter_EQB_outset})" value="提交"/>
    </div>
    <table class="tab_cont" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
    <tbody>
		  <tr>
		      <th class="first"></th>
		      <th><c:if test="${!param.filter_EQB_outset}">发站</c:if><c:if test="${param.filter_EQB_outset}">到站</c:if>简称</th>
		      <th class="last"><c:if test="${!param.filter_EQB_outset}">发站</c:if><c:if test="${param.filter_EQB_outset}">到站</c:if>编码</th>
		  </tr>
			<c:forEach items="${page.result}" var="c">
				<tr>
					<td><input  type="checkbox" onclick="checksel(this);" value="${c.code}-${c.name}" id="id_${c.id}" name="ids"/></td>
					<td>${c.name}</td>
					<td>${c.code}</td>
		
				</tr>
			</c:forEach>
    </tbody>
    </table>
    </c:if>
    <c:if test="${param.type=='pipeline'}">
    <div class="autosel" style="display: none">
    	  管道：<input id="autocompleteoil" type="text" name="filter_LIKES_code_OR_label" value="${param.filter_LIKES_code_OR_label}" class="middle"/>  <input type="submit" value="提交"/>
    </div>
    <table class="tab_cont" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
    <tbody>
		  <tr>
		      <th class="first"></th>
		      <th>管道名称</th>
		      <th class="last">管道编码</th>
		    </tr>
			<c:forEach items="${page.result}" var="c">
				<tr>
					<td><input type="checkbox" onclick="checksel(this);"  value="${c.code}-${c.label}" id="id_${c.id}" name="ids"/></td>
					<td>${c.label}</td>
					<td>${c.code}</td>
				</tr>
			</c:forEach>
    </tbody>
    </table>
    </c:if>
    <c:if test="${param.type=='pipelineStop'}">
    <div class="autosel">
    	  管线站：<input id="autocompleteoil" type="text" name="filter_LIKES_stopCode_OR_stopName" value="${param.filter_LIKES_stopCode_OR_stopName}" class="middle"/>  <input type="submit" value="提交"/>
    </div>
    <table class="tab_cont" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
    <tbody>
		  <tr>
		      <th class="first"></th>
		      <th>管线站名称</th>
		      <th class="last">管线站编码</th>
		      
		    </tr>
			<c:forEach items="${page.result}" var="c">
				<tr>
					<td><input type="checkbox" onclick="checksel(this);"  value="${c.stopCode}-${c.stopName}" id="id_${c.id}" name="ids"/></td>
					<td>${c.stopName}</td>
					<td>${c.stopCode}</td>
				</tr>
			</c:forEach>
    </tbody>
    </table>
    </c:if>
    </form>
    </div>
  </div>
  <!-- 分页区域
  <div id="page" class="page">
  	<wlps:page page="${page}" simpleTheme="true" />
  </div> -->
</div>
</body>
</html>