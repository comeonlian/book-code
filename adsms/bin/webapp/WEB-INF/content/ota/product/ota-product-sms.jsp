<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>内部办公自动化系统</title>
<%@ include file="/common/head.jsp" %>
<link rel="stylesheet" href="${ctx}/css/content.css" type="text/css" />

<script type="text/javascript">
	$(function() {
		$("#status").val("${param.filter_EQI_status}");
		$("#replyType").val("${param.filter_EQB_replyType}");
		$("#provider").val("${param.filter_EQI_provider}");
		$("#provinceid").val("${param.provinceid}");
	});
</script>
</head>
<body>
<div class="currloca">
  <p>${auth.fullMenu}</p>
  <div class="sitemap">
    <span style="display:block;float:left"><s:actionmessage theme="custom"/></span>
    <span id="add2custom"><img class="pointer" onclick="add2custom('${authId}');return false;" id="aCustom" width="24" align="absmiddle" height="24" title="添加到常用操作" src="${ctx}/images/favorite.png"/></span>
  </div>
</div>
   <div class="titt">
     <h2><img alt="" src="${ctx}/images/web/311.gif" align="absmiddle" />${auth.label}</h2>
   </div>
   <form id="mainForm" action="ota-product-sms!list.action" method="post">
    <input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
    <input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
    <input type="hidden" name="page.order" id="order" value="${page.order}"/>
    <input type="hidden" name="authId" id="authId" value="${authId}"/>
    <input type="hidden" name="page.pageSize" id="pageSize" value="${page.pageSize}" />
    <input type="hidden" name="page.excelExp" id="excelExp" value="${page.excelExp}" />
   <!-- 查询条件 -->
   <div id="filter" class="filter">
    <div id="item" class="item">
    <div class="where">
    	<div class="and">
           <span>通道代号:</span><input type="text" class="small" name="filter_LIKES_serviceCode" value="${param['filter_LIKES_serviceCode']}" />
        </div>
        <div class="and">
           <span>通道名称:</span><input type="text" class="small" name="filter_LIKES_serviceName" value="${param['filter_LIKES_serviceName']}" />
        </div>
        <div class="and">
           <span>客户ID:</span><input type="text" class="small" name="filter_LIKES_customid" value="${param['filter_LIKES_customid']}" />
        </div>
        <div class="and">
           <span>回复类型:</span><select name="filter_EQB_replyType" class="small" id="replyType">
           		<option value="">全部</option>
           		<option value="0">随机</option>
           		<option value="1">答题</option>
           		<option value="2">截取</option>
           </select>
        </div>
        <div class="and">
           <span>运营商:</span><select name="filter_EQI_provider" class="small" id="provider">
           		<option value="">全部</option>
           		<option value="1">移动</option>
           		<option value="2">联通</option>
           </select>
        </div>
        <div class="and">
           <span>省份:</span><select name="provinceid" class="small" id="provinceid">
           		<option value="">全部</option>
           		<c:forEach items="${provincelist}" var="pv">
           			<option value="${pv.id}">${pv.name}</option>
           		</c:forEach>
           </select>
        </div>
        <div class="and">
           <span>状态:</span><select name="filter_EQI_status" class="small" id="status">
           		<option value="">全部</option>
           		<option value="1">生效</option>
           		<option value="0">失效</option>
           </select>
        </div>
    </div>
      <div class="space"></div>
      <div style="text-align: center;">
      <img src="${ctx}/images/b_select.gif" alt="" onclick="search();" class="pointer" align="middle"/>
      <img src="${ctx}/images/b_reset.gif" alt="" onclick="resetb();" class="pointer"align="middle"/>
      </div>
    </div>
    <div id="contral" class="contral pointer" onclick="contral(this);"><img src="${ctx}/images/f_close.gif" title="收起查询面板"/></div>
  </div>
  
  
   <div class="mainC">
   <!-- 增删改查...操作菜单-->
  <div id="operate" class="operate">
    <div><img src="${ctx}/images/b_add.gif" alt="增加" onclick="opr_input('ota-product-sms!input.action',false,'${authId}');"/></div>
    <div><img src="${ctx}/images/b_edit.gif" alt="修改" onclick="opr_update('ota-product-sms!input.action','mainForm',false,'${authId}');" /></div>
    <!--  <div><img src="${ctx}/images/b_del.gif" alt="删除" onclick="opr_delete('ota-product-sms!delAll.action','mainForm');" /></div>-->
  </div>
    <!-- start--------------------------------------- -->
      <table class="mtab" cellpadding="2" cellspacing="1" border="0">
			<tr align="center">
				<th class="first"><input type="checkbox" id="box" name="box" onclick="checkedAll('ids')"/></th>
				<th>通道代号</th>
				<th>通道名称</th>
				<th>单价(元)</th>
				<th>回复类型</th>
				<th>优先级</th>
				<th>运营商</th>
				<th>状态</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>开始时刻</th>
				<th>结束时刻</th>
				<th>间隔(天)</th>
				<th>配置次数</th>
				<th>已下载次数</th>
				<th>客户ID</th>
			</tr>
			<c:forEach items="${page.result}" var="a" varStatus="c">
				<tr align="center">
					<td><input type="checkbox" value="${a.id}" id="id_${a.id}" name="ids"/></td>
					<td>${a.serviceCode}</td>
					<td>${a.serviceName}</td>
					<td>${a.price}</td>
					<td>
					    <c:if test="${a.replyType == 0}">随机</c:if>
					    <c:if test="${a.replyType == 1}">答题</c:if>
						<c:if test="${a.replyType == 2}">截取</c:if>
					</td>
					<td>${a.priority}</td>
					<td>
					    <c:if test="${a.provider == 0}">未设</c:if>
						<c:if test="${a.provider == 1}">移动</c:if>
						<c:if test="${a.provider == 2}">联通</c:if>
					</td>
					<td>
						<c:if test="${a.status == 0}"><span style="color:red;">失效</span></c:if>
						<c:if test="${a.status == 1}">生效</c:if>
					</td>
					<td>${a.begindate}</td>
					<td>${a.enddate}</td>
					<td>${a.begintime}</td>
					<td>${a.endtime}</td>
					<td>${a.feeDays}</td>
					<td>${a.confignum}</td>
					<td>${a.downnum}</td>
					<td>${fn:substring(a.customid, 0, 32)}</td>
				</tr>
			</c:forEach>
		</table>
		</form>
	  <!-- 分页区域-->
	  <div id="page" class="page">
	  <wlps:page page="${page}" showPageSize="true" excelExp="false" /></div>
    <!-- end--------------------------------------- -->
</div>

</body>
</html>
