<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户分析-总用户</title>
<%@ include file="/common/head.jsp" %>
	<link rel="stylesheet" href="${ctx}/css/content.css" type="text/css" />
	<link rel='STYLESHEET' type='text/css' href='${tree}/common/style.css' />
    <script src="${ctx}/js/jQuery/validate/jquery.validate.js" type="text/javascript"></script>
	<link href="${ctx}/js/jQuery/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${ctx}/js/jQuery/ui/jquery.ui.datepicker-zh-CN.js"></script>

<script type="text/javascript">
$(function(){
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
   <form id="mainForm" action="provider-daily!statTotalUser.action" method="post">
    <input type="hidden" name="authId" id="authId" value="${authId}"/>
   	<input type="hidden" name="userPage.pageNo" id="pageNo" value="${userPage.pageNo}"/>
    <input type="hidden" name="userPage.orderBy" id="orderBy" value="${userPage.orderBy}"/>
    <input type="hidden" name="userPage.order" id="order" value="${userPage.order}"/>
    <input type="hidden" name="userPage.pageSize" id="pageSize" value="${userPage.pageSize}" />
    <input type="hidden" name="userPage.excelExp" id="excelExp" value="${userPage.excelExp}" />
   <!-- 查询条件 -->
   <div id="filter" class="filter">
    <div id="item" class="item">
    <div class="where"> 
         <div class="and">
           <span>渠道号：</span>
           <input name="customerid" class="small" id="customerid" value="${param.customerid}"/>
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
		<span style="font-size:24px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;总用户：${userTotal}</b></span>
    </div>
    <!-- start--------------------------------------- -->
      <table class="mtab" cellpadding="2" cellspacing="1" border="0">
			<tr align="center">
				<th>渠道号</th>
				<th>总用户数</th>
			</tr>
			<c:forEach items="${userPage.result}" var="a" varStatus="c">
				<tr align="center">
					<td>${a.customid}</td>
					<td>${a.total}</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		</form>
	  <!-- 分页区域-->
	  <div id="page" class="page">
	  <wlps:page page="${userPage}" showPageSize="true" excelExp="false" /></div>
    <!-- end--------------------------------------- -->

</body>
</html>
