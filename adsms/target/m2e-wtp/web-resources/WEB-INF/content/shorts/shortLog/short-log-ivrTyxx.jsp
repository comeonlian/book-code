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
   <form id="mainForm" action="short-log!ivrTyxx.action" method="post">
    <input type="hidden" name="pageIvrTyxx.pageNo" id="pageNo" value="${pageIvrTyxx.pageNo}"/>
    <input type="hidden" name="pageIvrTyxx.orderBy" id="orderBy" value="${pageIvrTyxx.orderBy}"/>
    <input type="hidden" name="pageIvrTyxx.order" id="order" value="${pageIvrTyxx.order}"/>
    <input type="hidden" name="authId" id="authId" value="${authId}"/>
    <input type="hidden" name="pageIvrTyxx.pageSize" id="pageSize" value="${pageIvrTyxx.pageSize}" />
    <input type="hidden" name="pageIvrTyxx.excelExp" id="excelExp" value="${pageIvrTyxx.excelExp}" />
   <!-- 查询条件 -->
   <div id="filter" class="filter">
    <div id="item" class="item">
    <div class="where">
        <div class="and">
        	<span>流水号:</span>
        	<input type="text" class="small" name="filter_EQS_linkid" value="${param['filter_EQS_linkid']}" size="30" />
        </div>
        <div class="and">
        	<span>主叫号码:</span>
        	<input type="text" class="small" name="filter_LIKES_mobile" value="${param['filter_LIKES_mobile']}" size="30" />
        </div>
        <div class="and">
        	<span>被叫号码:</span>
        	<input type="text" class="small" name="filter_LIKES_spnumber" value="${param['filter_LIKES_spnumber']}" size="30" />
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
				<th>流水号</th>
				<th>访问时间</th>
				<th>主叫号码</th>
				<th>被叫号码</th>
				<th>记费起始时间</th>
				<th>记费结束时间</th>
				<th>记费时长(单位：分钟)</th>
				<th>ip</th>
			</tr>
			<c:forEach items="${pageIvrTyxx.result}" var="a" varStatus="c">
				<tr align="center">
					<td>${a.linkid}</td>
					<td><fmt:formatDate value="${a.accesstime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${a.mobile}</td>
					<td>${a.spnumber}</td>
					<td>${a.starttime}</td>
					<td>${a.stoptime}</td>
					<td>${a.feetime}</td>
					<td>${a.ip}</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		</div>
		</form>
	  <!-- 分页区域-->
	  <div id="page" class="page">
	  <wlps:page page="${pageIvrTyxx}" showPageSize="true" excelExp="false" /></div>
    <!-- end--------------------------------------- -->

</body>
</html>
