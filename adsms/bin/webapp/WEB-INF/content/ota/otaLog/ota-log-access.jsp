<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>内部办公自动化系统</title>
<%@ include file="/common/head.jsp" %>
<link rel="stylesheet" href="${ctx}/css/content.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jQuery/ui/jquery.ui.datepicker-zh-CN.js"></script>

<script type="text/javascript">
$(function(){
	
	var dates =$('#begindate, #enddate').datepicker({
	    changeMonth: true,
	    showButtonPanel: true,
	    numberOfMonths: 1,
	    prevText:'前一月', 
	    nextText:'后一月', 
	    //minDate:dateAdd("d", -3),
	    //maxDate:dateAdd("m", 2),
	    onSelect:function(selectedDate) {
	        var option = this.id == "begindate" ? "minDate" : "maxDate";
	        var instance = $(this).data("datepicker");
	        var date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, selectedDate, instance.settings);
	        dates.not(this).datepicker("option", option, date);
	    }
	});
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
   <form id="mainForm" action="ota-log!access.action" method="post">
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
        	<span>客户ID:</span>
        	<input type="text" class="small" name="filter_EQS_customerid" value="${param['filter_EQS_customerid']}" size="30" />
        </div>
        <div class="and">
        	<span>IMSI:</span>
        	<input type="text" class="small" name="filter_EQS_imsi" value="${param['filter_EQS_imsi']}" size="30" />
        </div>
        <div class="and">
          <span>开始时间:</span>
          <input type="text" class="small" name="begindate" id="begindate" value="${begindate}" readonly="readonly"/>
        </div>
        <div class="and">
          <span>结束时间:</span><input type="text" class="small" name="enddate" id="enddate" value="${enddate}" readonly="readonly"/>
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
   <!-- 增删改查${ctx}.操作菜单-->
  <div id="operate" class="operate">
  </div>
    <!-- start--------------------------------------- -->
    <div id="indiv" style="width:100%;OVERFLOW-X:auto;">
      <table class="mtab" cellpadding="2" cellspacing="1" border="0">
			<tr align="center">
				<th>客户id</th>
				<th>访问时间</th>
				<th>设备ID</th>
				<th>ip</th>
				<th>SDK版本</th>
				<th>平台</th>
				<th>平台版本</th>
				<th>待机时间</th>
				<th>IMSI</th>
				<th>SC</th>
				<th>IMSI1</th>
				<th>SC1</th>
				<th>IMSI2</th>
				<th>SC2</th>
				<th>监控步骤</th>
			</tr>
			<c:forEach items="${page.result}" var="a" varStatus="c">
				<tr align="center">
					<td>${a.customerid}</td>
					<td><fmt:formatDate value="${a.accesstime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${a.deviceid}</td>
					<td>${a.ip}</td>
					<td>${a.ver}</td>
					<td>${a.plat}</td>
					<td>${a.platver}</td>
					<td>${a.pot}</td>
					<td>${a.imsi}</td>
					<td>${a.sc}</td>
					<td>${a.imsi1}</td>
					<td>${a.sc1}</td>
					<td>${a.imsi2}</td>
					<td>${a.sc2}</td>
					<td>${a.runlevel} 
						<c:if test="${a.runlevel == 1}">客户未配置</c:if>
						<c:if test="${a.runlevel == 2}">客户状态关闭</c:if>
						<c:if test="${a.runlevel == 3}">客户时间拦截</c:if>
						<c:if test="${a.runlevel == 4}">客户省市拦截</c:if>
						<c:if test="${a.runlevel == 5}">运营商拦截</c:if>
						<c:if test="${a.runlevel == 6}">无sim卡信息</c:if>
						<c:if test="${a.runlevel == 21}">设备黑名单</c:if>
						<c:if test="${a.runlevel == 22}">月值</c:if>
						<c:if test="${a.runlevel == 23}">间隔拦截</c:if>
						<c:if test="${a.runlevel == 61}">无符合条件的数据</c:if>
						<c:if test="${a.runlevel == 161}">无符合条件的IVR数据</c:if>
						<c:if test="${a.runlevel == 261}">无符合条件的SMS数据</c:if>
						<c:if test="${a.runlevel > 1009}">成功</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		</div>
		</form>
	  <!-- 分页区域-->
	  <div id="page" class="page">
	  <wlps:page page="${page}" showPageSize="true" excelExp="false" /></div>
    <!-- end--------------------------------------- -->

</body>
</html>
