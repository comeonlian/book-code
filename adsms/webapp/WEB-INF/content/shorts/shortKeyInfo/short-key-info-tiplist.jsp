<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>内部办公自动化系统</title>
<%@ include file="/common/head.jsp" %>
<link rel="stylesheet" href="${ctx}/css/content.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgdialog.js?t=self&s=zxyg"></script>

<script type="text/javascript">
	// 返回
	function back(url) {
		url = prepURL(url, "authId", "${authId}");
		location.href = url;
	}
	
	// 关键字子信息设置 inputFlag_新增/修改标识
	function keyInfoTip(pid, inputFlag) {
		url = "short-key-info!tipinput.action";
		if (!inputFlag) { // 修改
			var checked = $("#mainForm :checkbox:checked").not($('#box'));
			if (checked.length == 0) {
				alert('请选择需要修改的记录！！！');
				return;
			} else if (checked.length >= 2) {
				alert('一次只能操作一条记录！！！');
				return;
			} else {
				url = prepURL(url, "tipId", checked.val());
			}
		}
		url = prepURL(url, "authId", "${authId}");
		url = prepURL(url, "pid", pid);
		var dg = new $.dialog({
			id : 'poupup',
			skin : 'zxyg',
			rang : true,
			title : "关键字子信息设置",
			fixed : true,
			drag : true,
			resize : true,
			cancelBtn : false,
			iconTitle : false,
			width : 650,
			height : 470,
			page : url,
			top : 100,
			left : 200,
			dgOnLoad : function() {
			}
		});
		dg.ShowDialog();
	}
	
	function opr_delete(url, formId) {
		var checked = $("#" + formId + " :checkbox:checked").not($('#box')).length;
		if (checked === 0) {
			alert("请选择要删除的记录！！！");
			return false;
		} else {
			if (confirm("确定要删除记录吗？ 删除后不能恢复！请谨慎操作！")) {
				$("#" + formId + "").attr('action', url);
				$("#" + formId + "").submit();
			} else {
				$("#" + formId + " :checkbox").attr("checked", false);
			}
		}

	}
</script>
</head>
<body>
<div class="currloca">
  <p>${auth.fullMenu}&nbsp;>>&nbsp;关键字子信息设置</p>
  <%-- <div class="sitemap">
    <span style="display:block;float:left"><s:actionmessage theme="custom"/></span>
    <span id="add2custom"><img class="pointer" onclick="add2custom('${authId}');return false;" id="aCustom" width="24" align="absmiddle" height="24" title="添加到常用操作" src="${ctx}/images/favorite.png"/></span>
  </div> --%>
</div>
   <div class="titt">
     <h2><img alt="" src="${ctx}/images/web/311.gif" align="absmiddle" />关键字子信息</h2>
   </div>
   <form id="mainForm" action="short-key-info!tiplist.action" method="post">
    <input type="hidden" name="tipPage.pageNo" id="pageNo" value="${tipPage.pageNo}"/>
    <input type="hidden" name="tipPage.orderBy" id="orderBy" value="${tipPage.orderBy}"/>
    <input type="hidden" name="tipPage.order" id="order" value="${tipPage.order}"/>
    <input type="hidden" name="authId" id="authId" value="${authId}"/>
    <input type="hidden" name="pid" id="pid" value="${pid}"/>
    <input type="hidden" name="oldcustomid" id="oldcustomid" value="${oldcustomid}"/>
    <input type="hidden" name="cflag" id="cflag" value="${cflag}"/>
    <input type="hidden" name="tipPage.pageSize" id="pageSize" value="${tipPage.pageSize}" />
    <input type="hidden" name="tipPage.excelExp" id="excelExp" value="${tipPage.excelExp}" />
   <!-- 查询条件 -->
   <div id="filter" class="filter">
    <div id="item" class="item">
    <div class="where">
        <div class="and">
        	<span>过滤关键字:</span><input type="text" style="background-color:#EEEEEE;" readonly="readonly" value="${entity.keytent}"/>
        </div>
        <div class="and">
        	<span>广告值:</span><input type="text" style="background-color:#EEEEEE;" readonly="readonly" value="${entity.advtent}"/>
        </div>
        <div class="and">
        	<span>内容:</span><input type="text" name="filter_LIKES_cons" value="${param.filter_LIKES_cons}"/>
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
    <div><img src="${ctx}/images/b_add.gif" alt="增加" onclick="keyInfoTip('${entity.id}', true);"/></div>
    <div><img src="${ctx}/images/b_edit.gif" alt="修改" onclick="keyInfoTip('${entity.id}', false);" /></div>
    <!--  <div><img src="${ctx}/images/b_del.gif" alt="删除" onclick="opr_delete('short-key-info!tipdel.action','mainForm');" /></div>-->
    <div><img src="${ctx}/images/button_back.gif" alt="返回" onclick="back('short-key-info.action');" /></div>
  </div>
    <!-- start--------------------------------------- -->
      <table class="mtab" cellpadding="2" cellspacing="1" border="0">
			<tr align="center">
				<th class="first"><input type="checkbox" id="box" name="box" onclick="checkedAll('ids')"/></th>
				<th>开始</th>
				<th>结束</th>
				<th>是否删除</th>
				<th>是否回复</th>
				<th>号码</th>
				<th>内容</th>
			</tr>
			<c:forEach items="${tipPage.result}" var="a" varStatus="c">
				<tr align="center">
					<td><input type="checkbox" value="${a.id}" id="id_${a.id}" name="ids"/></td>
					<td>${a.kbegin}</td>
					<td>${a.keyend}</td>
					<td>
						<c:if test="${a.del == 0}">否</c:if>
						<c:if test="${a.del == 1}">是</c:if>
					</td>
					<td>
						<c:if test="${a.ret == 0}">否</c:if>
						<c:if test="${a.ret == 1}">是</c:if>
					</td>
					<td>${a.smes}</td>
					<td>${a.cons}</td>
				</tr>
			</c:forEach>
		</table>
		</form>
	  <!-- 分页区域-->
	  <div id="page" class="page">
	  <wlps:page page="${tipPage}" showPageSize="true" excelExp="false" /></div>
    <!-- end--------------------------------------- -->
</div>

</body>
</html>
