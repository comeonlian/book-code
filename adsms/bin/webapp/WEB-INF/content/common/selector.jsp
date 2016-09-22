<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="com.game.modules.utils.SpringContextHolder" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>油品选择</title>
<%@ include file="/common/head.jsp"%>
    <!-- jsTree  -->
    <c:if test="${division=='0'}">
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/lib/jquery.cookie.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/lib/jquery.hotkeys.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/lib/jquery.metadata.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/lib/sarissa.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/jquery.tree.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/plugins/_jquery.tree.rtl.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/plugins/jquery.tree.checkbox.report.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/plugins/jquery.tree.contextmenu.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/plugins/jquery.tree.cookie.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/plugins/jquery.tree.hotkeys.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/plugins/jquery.tree.metadata.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/plugins/jquery.tree.themeroller.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/plugins/jquery.tree.xml_flat.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/plugins/jquery.tree.xml_nested.js"></script>
    <link rel="stylesheet" href="${ctx}/js/jQuery/jsTree/themes/checkbox/style.css" type="text/css"></link>
    </c:if>
    <script type="text/javascript">

		//权限树 
		$(function (){
			parent.$("#treeload").hide();
			<c:if test="${division=='0'}">
			var seln = parent.$("#hidden_${oiltype}").val().split(",");
			var selArray = [];
			if(seln!=""){seln = $(seln).each(function(i,item){selArray[i]=item.replace(/^\'/,'').replace(/\'$/,'');});}
				$("#oiltree").tree({
		   			selected:selArray,
		    		data : { type : "json", async : false }, ui : { theme_name : "checkbox" },
			 		types:{ "default" : { draggable : false} }, plugins : { checkbox : {three_state : false } },  // false can not cascading
			 		callback : { 
						onload : function (t) { 
							t.settings.data.opts.static = true; 
							t.settings.plugins.checkbox.three_state = true;
					    },
						beforedata : function (node, tree) { if(node == false) {tree.settings.data.opts.static = ${oilTree }; } },//初始显示 
						onchange : function() { 
							var idArray = [];  //保存选中的id
							var nameArray = [];
							//if (jQuery.tree.plugins.checkbox.get_checked().length==0){return;}
							jQuery.tree.plugins.checkbox.get_checked().each( function (i,node){
																			idArray.push("'"+node.id+"'");
																			var nodename = $("#"+node.id+" a").first().text();
																			nameArray.push(nodename);
																		 });
							//把所有的被选中的id 赋值给隐藏域
							parent.$("#hidden_${oiltype}").val(idArray.toString());
							parent.$("#selnm").val(nameArray.toString());
						}
					}
				});
			</c:if>

			// 列表
			<c:if test="${division=='1'}">
				$("#autocompleteoil").focus();
				var eless = document.getElementsByName("ids"); 
				for(var j=0;j<eless.length;j++){ 
					var selval=eless[j].value;
					var allsel="'"+selval.split('-')[0]+"'";
					if(allsel==parent.$("#hidden_${oiltype}").val()){ 
						parent.$("#selnm").val(selval.split("-")[1]);
						eless[j].checked=true;
						break; 
					} 
				} 
				if('gasStationManager'=='${oiltype}'){
					oilautocompletesm("filter_LIKES_code_OR_shortName");
				}else if('storageManager'=='${oiltype}'){
					oilautocompletesm("filter_LIKES_code_OR_shortName");
				}else if('portStationInfoManager'=='${oiltype}'){
					oilautocompletem();	
				}else {
					oilautocompletesm("filter_LIKES_code_OR_shortName");
				}
			</c:if>
		});

		function oilautocompletesm(parameter){
			$("#autocompleteoil").autocomplete({
				minLength: 1,
				source:function(request, response) {
					$.post("selector!getAcDataSource.action", {filter_LIKES_code_OR_shortName:request.term,oiltype:"${oiltype}"},response);
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
					$.post("selector!getAcDataSource.action", {filter_LIKES_code_OR_name:request.term,oiltype:"${oiltype}"},response);
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
				parent.$("#hidden_${oiltype}").val("'"+m.value.split("-")[0]+"'");
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
	<c:if test="${division=='0'}">
		<!-- 内容区域 -->
		<div id="oiltree" class="treeDiv"></div>
	</c:if>
	
	<c:if test="${division=='1'}">
	<div id="content" class="content">
    <div id="indiv" style="width:100%;overflow:auto;height:252px">
    <form id="mainForm1" action="selector.action" method="post">
	 	<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>
		<input type="hidden" name="authId" id="authId" value="${authId}"/>
		<input type="hidden" name="division" id="division" value="${division}"/>
		<input type="hidden" name="oiltype" id="oiltype" value="${oiltype}"/>
		<input type="hidden" name="page.pageSize" id="pageSize" value="${page.pageSize}" />
	
    <c:if test="${oiltype=='gasStationManager'}">
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
    <c:if test="${oiltype=='storageManager'}">
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
    <c:if test="${oiltype=='factoryManager'}">
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
    
    <c:if test="${oiltype=='portStationInfoManager'}">
    <div class="autosel">
    	  发到港站编码：<input id="autocompleteoil" class="middle" name="filter_LIKES_code_OR_name" value="${param.filter_LIKES_code_OR_name}"  />  <input type="submit" value="提交"/>
    </div>
    <table class="tab_cont" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
    <tbody>
		  <tr>
		      <th class="first"></th>
		      <th><a href="javascript:sort('id','asc')">序号</a></th>
		      <th><a href="javascript:sort('code','asc')">发到港站编码</a></th>
		      <th><a href="javascript:sort('name','asc')">发到港站简称</a></th>
		    </tr>
			
			<c:forEach items="${page.result}" var="c">
				<tr>
					<td><input type="radio" onclick="radionsel(this);" value="${c.id}-${c.name}" id="id_${c.id}" name="ids"/></td>
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
  </c:if>
</div>
</body>
</html>