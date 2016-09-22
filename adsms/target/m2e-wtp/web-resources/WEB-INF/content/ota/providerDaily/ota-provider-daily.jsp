<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>自动化办公系统</title>
<%@ include file="/common/head.jsp" %>
<link rel="stylesheet" href="${ctx}/css/content.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jQuery/ui/jquery.ui.datepicker-zh-CN.js"></script>

<script type="text/javascript">
	$(function() {
		$("#status").val("${param.filter_EQI_status}");
		
		$("#beginDate").css("width", "300px");
		
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
   <form id="mainForm" action="ota-provider-daily!list.action" method="post">
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
	    <div class="where">
	        <div class="and" id="beginDate">
	           <span>开始时间从:</span><input type="text" style="width:90px;" class="small" id="begindate" name="begindate" value="${begindate}" />
	           &nbsp;~&nbsp;<input type="text" style="width:90px;" class="small" id="enddate" name="enddate" value="${enddate}" />
	        </div>
	    </div>
        <div class="and">
           <span>客户ID:</span>
           <input type="text" name="filter_EQS_customerId" id="customerId" value="${param.filter_EQS_customerId}" />
        </div>
    	<div class="and">
           <span>状态:</span><select name="filter_EQI_status" class="small" id="status">
           		<option value="">全部</option>
           		<option value="0">关闭</option>
           		<option value="1">开放</option>
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
    <%-- <div><img src="${ctx}/images/b_add.gif" alt="增加" onclick="opr_input('ota-provider-daily!input.action',false,'${authId}');"/></div> --%>
    <div><img src="${ctx}/images/b_edit.gif" alt="修改" onclick="opr_update('ota-provider-daily!input.action','mainForm',false,'${authId}');" /></div>
    <%-- <div><img src="${ctx}/images/b_del.gif" alt="删除" onclick="opr_delete('ota-provider-daily!delAll.action','mainForm');" /></div> --%>
  </div>
    <!-- start--------------------------------------- -->
      <table class="mtab" cellpadding="2" cellspacing="1" border="0">
			<tr align="center">
				<th class="first"><input type="checkbox" id="box" name="box" onclick="checkedAll('ids')"/></th>
				<th>日期</th>
				<th>客户ID</th>
				<th>真实用户数</th>
				<th>开放用户数</th>
				<th>单价(元)</th>
				<th>开放收益(元)</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${page.result}" var="a" varStatus="c">
				<tr align="center">
					<td><input type="checkbox" value="${a.id}" id="id_${a.id}" name="ids"/></td>
					<td>${a.currentdate}</td>
					<td>${a.customerId}</td>
					<td>${a.realnum}</td>
					<td>${a.opennum}</td>
					<td><fmt:formatNumber value="${a.unitprice / 10}" pattern="#.##"/></td>
					<td><fmt:formatNumber value="${a.opennum * a.unitprice / 10}" pattern="#.##"/></td>
					<td>
						<c:if test="${a.status == 0}"><span style="color:red;">关闭</span></c:if>
						<c:if test="${a.status == 1}">开放</c:if>
					</td>
					<td>
                        <a href="ota-provider-daily!input.action?id=${a.id}&authId=${authId}">
                        	<img src="${ctx}/images/edt.gif" width="16" height="16" border="0" alt="修改" />
                        </a>&nbsp;
					</td>
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
