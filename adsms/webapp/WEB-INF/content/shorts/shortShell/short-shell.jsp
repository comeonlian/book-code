<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>自动化办公系统</title>
<%@ include file="/common/head.jsp" %>
<link rel="stylesheet" href="${ctx}/css/content.css" type="text/css" />

<script type="text/javascript">
	$(function() {
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
   <form id="mainForm" action="short-shell!list.action" method="post">
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
            <span>命令名称:</span>
			<input type="text" class="small" name="filter_LIKES_title" value="${param.filter_LIKES_title}" />
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
    <div><img src="${ctx}/images/b_add.gif" alt="增加" onclick="opr_input('short-shell!input.action',false,'${authId}');"/></div>
    <div><img src="${ctx}/images/b_edit.gif" alt="修改" onclick="opr_update('short-shell!input.action','mainForm',false,'${authId}');" /></div>
    <!-- <div><img src="${ctx}/images/b_del.gif" alt="删除" onclick="opr_delete('short-shell!delAll.action','mainForm');" /></div> -->
  </div>
    <!-- start--------------------------------------- -->
      <table class="mtab" cellpadding="2" cellspacing="1" border="0">
			<tr align="center">
				<th class="first"><input type="checkbox" id="box" name="box" onclick="checkedAll('ids')"/></th>
				<th>名称</th>
				<th>包名</th>
				<th width="20%">监听哪些广播</th>
				<th width="20%">执行命令</th>
				<th>等待时间(秒)</th>
				<th>成功标志 </th>
				<th>是否失败反馈</th>
				<th>执行方式</th>
				<th>状态</th>
			</tr>
			<c:forEach items="${page.result}" var="a" varStatus="c">
				<tr align="center">
					<td><input type="checkbox" value="${a.id}" id="id_${a.id}" name="ids"/></td>
					<td>${a.title}</td>
					<td>${a.pkg}</td>
					<td width="20%">${fn:substring(a.filters, 0, 35)}</td>
					<td width="20%">${fn:substring(a.cmd, 0, 35)}</td>
					<td>${a.delay}</td>
					<td>${a.ssign}</td>
					<td>
						<c:if test="${a.fback == 1}">反馈</c:if>
						<c:if test="${a.fback == 0}"><span style="color:red;">不反馈</span></c:if>
					</td>
					<td>
						<c:if test="${a.type == 1}">shell脚本</c:if>
						<c:if test="${a.type == 0}">直接命令</c:if>
					</td>
					<td>
						<c:if test="${a.status == 1}">开放</c:if>
						<c:if test="${a.status == 0}"><span style="color:red;">关闭</span></c:if>
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
