<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>内部办公自动化系统</title>
<%@ include file="/common/head.jsp" %>
<link rel="stylesheet" href="${ctx}/css/content.css" type="text/css" />
<link rel='STYLESHEET' type='text/css' href='${tree}/common/style.css' />
	<link rel="STYLESHEET" type="text/css" href="${tree}/dhtmlxtree.css" />
	<script type="text/javascript" src="${tree}/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${tree}/dhtmlxtree.js"></script>
	
    <script src="${ctx}/js/jQuery/validate/jquery.validate.js" type="text/javascript"></script>
	<link href="${ctx}/js/jQuery/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${ctx}/js/jQuery/ui/jquery.ui.datepicker-zh-CN.js"></script>
	<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgdialog.js?t=self&s=zxyg"></script>

<script type="text/javascript">
	$(function() {
		//$("#status").val("${param.filter_EQI_status}");
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
   <form id="mainForm" action="short-device!list.action" method="post">
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
           <span>mac:</span><input type="text" class="small" name="selectMac" value="${selectMac}" />
        </div>
    	<div class="and">
           <span>imei:</span><input type="text" class="small" name="selectImei" value="${selectImei}" />
        </div>
    	<div class="and">
           <span>androidid:</span><input type="text" class="small" name="selectAndroidid" value="${selectAndroidid}" />
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
  </div>
    <!-- start--------------------------------------- -->
      <table class="mtab" cellpadding="2" cellspacing="1" border="0">
			<tr align="center">
				<th>操作</th>
				<th>mac</th>
				<th>imei</th>
				<th>imsi</th>
				<th>androidid</th>
				<th>客户ID</th>
				<th>创建时间</th>
				<th>黑名单</th>
				<th>设备状态</th>
				<th>测试模式</th>
			</tr>
			<c:forEach items="${page.result}" var="a" varStatus="c">
				<tr align="center">
					<td>
                        <a href="short-device!input.action?id=${a.id}&authId=${authId}&selectImei=${selectImei}&selectMac=${selectMac}">
                        	<img src="${ctx}/images/edt.gif" width="16" height="16" border="0" alt="修改" />
                        </a>
					</td>
					<td>${a.mac}</td>
					<td>${a.imei}</td>
					<td>${a.imsi}</td>
					<td>${a.androidid}</td>
					<td>${a.customerid}</td>
					<td><fmt:formatDate value="${a.accesstime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<c:if test="${a.black == 0}"><span style="color:red;">黑名单</span></c:if>
						<c:if test="${a.black == 1}">否</c:if>
					</td>
					<td>
						<c:if test="${a.status == 0}"><span style="color:red;">关闭</span></c:if>
						<c:if test="${a.status == 1}">开放</c:if>
					</td>
					<td>
						<c:if test="${a.test == 0}">否</c:if>
						<c:if test="${a.test == 1}"><span style="color:red;">是</span></c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		</form>
	  <!-- 分页区域-->
	  <div id="page" class="page">
	  <wlps:page page="${page}" showPageSize="true" excelExp="false" /></div>
    <!-- end--------------------------------------- -->

</body>
</html>
