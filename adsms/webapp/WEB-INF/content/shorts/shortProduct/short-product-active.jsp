<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>内部办公自动化系统</title>
<%@ include file="/common/head.jsp" %>
<link rel="stylesheet" href="${ctx}/css/content.css" type="text/css" />

<script type="text/javascript">
	// 返回
	function back(url) {
		url = prepURL(url, "authId", "${authId}");
		location.href = url;
	}
	
</script>
</head>
<body>
<div class="currloca">
  <p>${auth.fullMenu}&nbsp;>>&nbsp;激活信息设置</p>
  <%-- <div class="sitemap">
    <span style="display:block;float:left"><s:actionmessage theme="custom"/></span>
    <span id="add2custom"><img class="pointer" onclick="add2custom('${authId}');return false;" id="aCustom" width="24" align="absmiddle" height="24" title="添加到常用操作" src="../images/favorite.png"/></span>
  </div> --%>
</div>
   <div class="titt">
     <h2><img alt="" src="${ctx}/images/web/311.gif" align="absmiddle" />设置激活信息</h2>
   </div>
   <form id="mainForm" action="short-product-active!list.action" method="post">
    <input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
    <input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
    <input type="hidden" name="page.order" id="order" value="${page.order}"/>
    <input type="hidden" name="authId" id="authId" value="${authId}"/>
    <input type="hidden" name="pid" id="pid" value="${pid}"/>
    <input type="hidden" name="page.pageSize" id="pageSize" value="${page.pageSize}" />
    <input type="hidden" name="page.excelExp" id="excelExp" value="${page.excelExp}" />
   <!-- 查询条件 -->
   <div id="filter" class="filter">
    <div id="item" class="item">
    <div class="where">
        <div class="and">
        	<span>通道名称:</span><input type="text" style="background-color:#EEEEEE;" readonly="readonly" value="${productSms.serviceName}"/>
        </div>
        <div class="and">
        	<span>通道类型:</span><input type="text" style="background-color:#EEEEEE;" readonly="readonly" value="${productSms.smsType == 1 ? 'SMS' : 'WAP'}"/>
        </div>
        <div class="and">
        	<span>运营商:</span><input type="text" style="background-color:#EEEEEE;" readonly="readonly" value="${productSms.provider}"/>
        </div>
        <div class="and">
        	<span>开始时间:</span><input type="text" style="background-color:#EEEEEE;" readonly="readonly" value="${productSms.begintime}"/>
        </div>
        <div class="and">
        	<span>结束时间:</span><input type="text" style="background-color:#EEEEEE;" readonly="readonly" value="${productSms.endtime}"/>
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
    <div><img src="${ctx}/images/b_add.gif" alt="增加" onclick="opr_input('short-product-active!input.action?pid=${pid}',false,'${authId}');"/></div>
    <div><img src="${ctx}/images/b_edit.gif" alt="修改" onclick="opr_update('short-product-active!input.action?pid=${pid}','mainForm',false,'${authId}');" /></div>
    <div><img src="${ctx}/images/b_del.gif" alt="删除" onclick="opr_delete('short-product-active!delAll.action','mainForm');"/></div>
    <div><img src="${ctx}/images/button_back.gif" alt="返回" onclick="back('../shortProduct/short-product-sms.action');" /></div>
  </div>
    <!-- start--------------------------------------- -->
      <table class="mtab" cellpadding="2" cellspacing="1" border="0">
			<tr align="center">
				<th class="first"><input type="checkbox" id="box" name="box" onclick="checkedAll('ids')"/></th>
				<!-- <th>查看详细信息</th> -->
				<th>排序号</th>
				<th>模拟类型</th>
				<th>执行前等待(ms)</th>
			</tr>
			<c:forEach items="${page.result}" var="a" varStatus="c">
				<tr align="center">
					<td><input type="checkbox" value="${a.id}" id="id_${a.id}" name="ids"/></td>
					<%-- <td>
						<a href="short-product-active!detail.action?id=${a.id}&authId=${authId}&pid=${pid}"><b>查看详细信息</b></a>
					</td> --%>
					<td>${a.ordernum}</td>
					<td>
						<c:if test="${a.activeType == 1}">SMS</c:if>
						<c:if test="${a.activeType == 2}"><span style="color:red;">WAP</span></c:if>
					</td>
					<td>${a.delay}</td>
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
