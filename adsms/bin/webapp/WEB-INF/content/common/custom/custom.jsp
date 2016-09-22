<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>游戏资源管理</title>
<%@ include file="/common/head.jsp" %>
<link rel="stylesheet" href="${ctx}/css/content.css" type="text/css" />
<link rel='STYLESHEET' type='text/css' href='${tree}/common/style.css' />
	<link rel="STYLESHEET" type="text/css" href="${tree}/dhtmlxtree.css" />
	<script type="text/javascript" src="${tree}/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${tree}/dhtmlxtree.js"></script>
    <script src="${ctx}/js/jQuery/validate/jquery.validate.js" type="text/javascript"></script>
	<link href="${ctx}/js/jQuery/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${ctx}/js/jQuery/ui/jquery.ui.datepicker-zh-CN.js"></script>

<script type="text/javascript">
	
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
   <form id="mainForm" action="custom!list.action" method="post">
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
           <span>客户名称：</span><input style="width:100px" name="filter_LIKES_name" class="small" id="name" value="${param['filter_LIKES_name']}"/>
        </div>  
         <div class="and">
           <span>客户编号 ：</span>
           <input style="width:100px" name="filter_LIKES_customid" class="small" id="name" value="${param['filter_LIKES_customid']}"/>
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
    <div><img src="${ctx}/images/b_add.gif" alt="增加" onclick="opr_input('custom!input.action',false,'${authId}');"/></div>
    <div><img src="${ctx}/images/b_edit.gif" alt="修改" onclick="opr_update('custom!input.action','mainForm',false,'${authId}');" /></div>
    <div><img src="${ctx}/images/b_del.gif" alt="删除" onclick="opr_delete('custom!delAll.action','mainForm');" /></div>
    </div>
    <!-- start--------------------------------------- -->
      <table class="mtab" cellpadding="2" cellspacing="1" border="0">
			<tr align="center">
				<th class="first"><input type="checkbox" id="box" name="box" onclick="checkedAll('ids')"/></th>
				<th>客户名称</th>
				<th>客户编号</th>
				<th>联系人</th>
				<th>电话</th>
				<th>是否默认</th>
				<th>创建时间</th>
				<th>状态</th>
				
			</tr>
			<c:forEach items="${page.result}" var="a" varStatus="c">
				<tr align="center">
					<td><input type="checkbox" value="${a.id}" id="id_${a.id}" name="ids"/></td>
					<td>${a.name}</td>			
					<td>${a.customid}</td>
					<td>${a.connectname}</td>
					<td>${a.phone}</td>
					<td>					
						<c:if test="${a.isdefault eq 1}"><span class="black">是</span></c:if>
						<c:if test="${a.isdefault eq 0}"></c:if>
					</td>
					<td>${a.createtime}</td>
					<td>
						<c:if test="${a.status eq 1}">上线</c:if>
						<c:if test="${a.status eq 0}"><span class="red">下线</span></c:if>
					</td>
					
					<!-- <td><a href="${ctx}/gameplat/adverflash/gameadverflash!list.action">查看</a></td> -->
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
