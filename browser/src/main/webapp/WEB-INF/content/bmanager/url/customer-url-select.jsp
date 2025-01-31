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
	
	// 选中跳转
	$(".tab_sel tr:gt(0)").bind("mouseover", function() {
		$(this).addClass("rowchange");
	}).bind("mouseout", function() {
		$(this).removeClass("rowchange");
	}).bind("click", function() {
		$('input[type=radio]').removeAttr("checked");
		$(this).children().first().children().attr("checked", true);
		ok();
	});

	// 弹出层用lhgdialog
	var DG = frameElement.lhgDG;
	DG.addBtn('ok', '确定', ok);

	function ok() {
		// 这里写你要操作的代码，最后写刷新代码
		var $radio = $("input[name=ids][checked]");
		if ($radio.length != 0) {
			parent.$('input[id="customid"]').val($radio.val());
		}

		// 确定按钮回调函数
		if ('${param.type}'.indexOf("-") == -1) {
			if (typeof (eval('parent.callback_${param.type}')) == "function") {
				eval('parent.callback_${param.type}($radio.val(),function(){DG.cancel();});');
			} else if (typeof (eval('parent.callback_${param.type}_name')) == "function") {
				eval('parent.callback_${param.type}_name($radio.val(),function(){DG.cancel();},"${name}");');
			} else if (!window.frameElement.lhgDG.parent) {
				DG.cancel();
			}
		}
	}

});

function checkid(cid) {
	window.returnValue = cid;
	this.close();
}
</script>
</head>

<body>
   <div class="titt">
     <h2><img alt="" src="${ctx}/images/web/311.gif" align="absmiddle" >选择客户</h2>
   </div>
   <form id="mainForm" action="customer-url!selectresource.action" method="post">
    <input type="hidden" name="pageResource.pageNo" id="pageNo" value="${pageResource.pageNo}"/>
    <input type="hidden" name="pageResource.orderBy" id="orderBy" value="${pageResource.orderBy}"/>
    <input type="hidden" name="pageResource.order" id="order" value="${pageResource.order}"/>
    <input type="hidden" name="authId" id="authId" value="${authId}"/>
    <input type="hidden" name="pageResource.pageSize" id="pageSize" value="${pageResource.pageSize}" />
    <input type="hidden" name="pageResource.excelExp" id="excelExp" value="${pageResource.excelExp}" />
   <!-- 查询条件 -->
   <div id="filter" class="filter">
    <div id="item" class="item">
    <div class="where">
    	<div class="and">
           <span>客户ID:</span><input type="text" class="small" name="customid" value="${customid}" />
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
      <table class="mtab tab_sel" cellpadding="2" cellspacing="1" border="0">
			<tr align="center">
				<th>选择</th>
				<th>客户ID</th>
				<th>客户名称</th>
			</tr> 
			<c:forEach items="${pageResource.result}" var="a" varStatus="c">
				<tr align="center">
					<td><input type="radio"  value="${a.customerid}" id="id_${a.customerid}" name="ids"/></td>
					<td>${a.customerid}</td>
					<td>${a.customername}</td>
				</tr>
			</c:forEach>
		</table>
		</form>
	  <!-- 分页区域-->
	  <div id="page" class="page">
	  <wlps:page page="${pageResource}" showPageSize="true" excelExp="false" /></div>
    <!-- end--------------------------------------- -->
</div>

</body>
</html>
