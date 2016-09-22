<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="com.game.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${auth.label}</title>
<%@ include file="/common/head.jsp"%>
<script type="text/javascript" src="${ctx}/js/jQuery/ui/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/js/jQuery/validate/timepicker.js"></script>

<script src="${ctx}/js/jQuery/validate/jquery.validate.js" type="text/javascript"></script>
<link href="${ctx}/js/jQuery/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#filter_LIKES_businessCode").attr("value",
						"${param['filter_LIKES_businessCode']}");
				$("#filter_LIKES_operationCode").attr("value",
						"${param['filter_LIKES_operationCode']}");
				$("#filter_GED_tradeTime").attr("value",
						"${param['filter_GED_tradeTime']}");
				$("#filter_LED_tradeTime").attr("value",
						"${param['filter_LED_tradeTime']}");
				$("#filter_GED_creatTime").attr("value",
						"${param['filter_GED_creatTime']}");
				$("#filter_LED_creatTime").attr("value",
						"${param['filter_LED_creatTime']}");
				$("#filter_LIKES_operator").attr("value",
						"${param['filter_LIKES_operator']}");
			});
	$(document).ready(function() {
	    
	    //$("#tradeTimeS").datepicker();
	    $("#tradeTimeS").datepicker($.datepicker.regional['zh-CN']);
	    $("#tradeTimeE").datepicker($.datepicker.regional['zh-CN']);
	    $("#creatTimeS").datepicker($.datepicker.regional['zh-CN']);
	    $("#creatTimeE").datepicker($.datepicker.regional['zh-CN']);
	});
</script>
</head>
<body>
<div class="currloca">
<p>${auth.fullMenu}</p>
<div class="sitemap"><span style="display: block; float: left"><s:actionmessage
	theme="custom" /></span> <span id="add2custom"><img class="pointer"
	onclick="add2custom('${authId}');return false;" id="aCustom" width="19"
	height="18" title="添加到常用操作" src="../images/add_custom.gif" /></span> <span
	id="showMap"><img onclick="showMap();return false;" id="sMap"
	class="pointer" width="64" height="18" title="后台导航"
	src="../images/map.gif" /></span></div>
</div>

<div class="container"><!-- 内容区域 -->
		<div class="itemtitle">
		<h2>${auth.label}</h2>
		</div>

	<form id="mainForm" action="integration-log.action" method="post">
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}" /> 
		<input type="hidden" name="authId" id="authId" value="${authId}" />
 		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}" />
 		<input type="hidden" name="page.order" id="order" value="${page.order}" />
   		<input type="hidden" name="page.pageSize" id="pageSize" value="${page.pageSize}" />
    	<input type="hidden" name="page.excelExp" id="excelExp" value="${page.excelExp}" /> 
	
	<!-- 查询条件 -->
	  <div id="filter" class="filter">
	    <div id="item" class="item">
		    <div class="where">
			<div class="and">
				  <span style=" width:80px;">业务信息:</span>
				  <input type="text" name="filter_LIKES_businessCode" value="${param['filter_LIKES_businessCode']}" />
			 </div>
			<div class="and">
				<span style=" width:80px;">日志类型:</span>
		    	<select  class="text middle" name="filter_LIKES_operationCode">
		    	<option value="">---全部---</option>
	                    <c:forEach items="${listType}" var="c">
	                          <option value="${c.value}" <c:if test="${c.value==param['filter_LIKES_operationCode']}">selected="selected"</c:if>>${c.label}</option>
	                    </c:forEach>
	         	 </select>
		    </div>
			<div class="and">
	    	<span style=" width:80px;">起始日志时间:</span>
	    		<input type="text" id="tradeTimeS" name="filter_GED_tradeTime" value="${param['filter_GED_tradeTime']}" 
	    		pattern="yyyy-MM-dd hh:mm:ss"/>
	    	  </div>
			 <div class="and">
	    	<span style=" width:80px;">结束日志时间:</span>
	    		<input type="text" id="tradeTimeE" name="filter_LED_tradeTime" value="${param['filter_LED_tradeTime']}" 
	    		pattern="yyyy-MM-dd hh:mm:ss"/>
	    	  </div>
	    	   <div class="and">
	    	    <span style=" width:80px;">起始创建时间:</span>
	    		<input type="text" id="creatTimeS" name="filter_GED_creatTime" value="${param['filter_GED_creatTime']}" 
	    		pattern="yyyy-MM-dd hh:mm:ss"/>
	    	  </div>
	    	   <div class="and">
	    	     <span style=" width:80px;">结束创建时间:</span>
	    		<input type="text" id="creatTimeE" name="filter_LED_creatTime" value="${param['filter_LED_creatTime']}" 
	    		pattern="yyyy-MM-dd hh:mm:ss"/>
	    	  </div>
	    	   <div class="and">
	    	    <span  style=" width:80px;">操作人:</span><input type="text" name="filter_LIKES_operator" value="${param['filter_LIKES_operator']}" />
	    	  </div>
		     
		     </div>
	      <div class="space"></div>
	      <div style="text-align: center;">
	      <img src="../images/b_select.gif" alt="" onclick="search();" class="pointer" align="middle"/>
	      <img src="../images/b_reset.gif" alt="" onclick="resetb();" class="pointer"align="middle"/>
	      </div>
	    </div>
	    <div id="contral" class="contral pointer" onclick="contral(this);"><img src="../images/f_close.gif" title="收起查询面板"/></div>
	  </div>

<!-- 附加信息-->
<div id="message" class="message"><span
	style="font-weight: bold; font-size: 14px;"><!-- 提示：资源列表... --></span>
</div>

<!-- 增删改查...操作菜单-->
<div id="operate" class="operate">
 </div>

<!-- 列表区域-->
<div id="content" class="content">
<div id="indiv" >
<table class="tab_cont" width="100%" cellspacing="0" cellpadding="0"
	border="0" align="center">
	<tbody>
		<tr>
			<th class="first" style="text-align:center;"width="35"><input type="checkbox" id="box" name="box"
				onclick="checkedAll('ids')" /></th>
			<th style="text-align:center;"width="35">序号</th>
			<th><a href="javascript:sort('tradeSeq','asc')">接口序号</a></th>
			<th><a href="javascript:sort('businessCode','asc')">业务信息字段</a></th>
			<th><a href="javascript:sort('operationCode','asc')">业务日志类型</a></th>
			<th><a href="javascript:sort('tradeTime','asc')">日志时间</a></th>
			<th><a href="javascript:sort('creatTime','asc')">创建时间</a></th>
			<th><a href="javascript:sort('operator','asc')">操作人</a></th>
			<th>日志信息</th>
		</tr>

		<c:forEach items="${page.result}" varStatus="c" var="s">
			<tr>
				<td style="text-align:center;"width="35"><input type="checkbox" value="${s.id}" id="id_${s.id}"
					name="ids" /></td>
					<td height="22" style="text-align:center;"width="35">${(page.pageNo-1)*page.pageSize+c.index+1}</td>
				<td >
				<a href="${ctx}/system/journal!detail.action?sessionCode=${s.sessionCode}&authId=${authId}">${s.tradeSeq}</a></td>
				<td>${s.businessCode}</td>
				<td><wlps:dic dcode="sys_interface" icode="${s.operationCode}"></wlps:dic></td>
				<td><fmt:formatDate value="${s.tradeTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><fmt:formatDate value="${s.creatTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${s.operator}</td>
				<td>${s.description}</td>
			</tr>
		</c:forEach>

	</tbody>
</table>
</div>
</div>
</form>

<!-- 分页区域-->
<div id="page" class="page"><wlps:page page="${page}"
	showPageSize="true" excelExp="true" /></div>

</div>
</body>
</html>