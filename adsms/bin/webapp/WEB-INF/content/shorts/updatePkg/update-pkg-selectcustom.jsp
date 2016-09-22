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
		/* $(".tab_sel tr:gt(0)").bind("mouseover", function() {
			$(this).addClass("rowchange");
		}).bind("mouseout", function() {
			$(this).removeClass("rowchange");
		}).bind("click", function() {
			$('input[type=radio]').removeAttr("checked");
			$(this).children().first().children().attr("checked", true);
			ok();
		}); */

		// 弹出层用lhgdialog
		var DG = frameElement.lhgDG;
		DG.addBtn('ok', '确定', ok);

		function ok() {
			// 这里写你要操作的代码，最后写刷新代码
			var checked = $("#sel_results :checkbox:checked").not($('#box'));
			var results = "";
			checked.each(function(i, item) {
				results = results + $(this).val() + ",";
			});
			parent.$('textarea[id="customers"]').val(results.substring(0, results.lastIndexOf(',')));

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

	function dbinit(){
		var v3="${customids}";
		var rights =v3.split(',');
		var checked = $("#sel_results :checkbox").not($('#box'));
		checked.each(function(i, item) {
			for(var i=0;i<rights.length;i++)
		    {
		        var r= rights[i];
	         	if(r == $(this).val())
	         	{
	         		$(this).attr('checked', 'checked');
	         	}
		    }
		});
	}
</script>
</head>

<body onload="dbinit();">
   <div class="titt">
     <h2><img alt="" src="${ctx}/images/web/311.gif" align="absmiddle" />选择客户</h2>
   </div>
   <form id="mainForm" action="update-pkg!selectcustom.action" method="post">
    <input type="hidden" name="pageCustomer.pageNo" id="pageNo" value="${pageCustomer.pageNo}"/>
    <input type="hidden" name="pageCustomer.orderBy" id="orderBy" value="${pageCustomer.orderBy}"/>
    <input type="hidden" name="pageCustomer.order" id="order" value="${pageCustomer.order}"/>
    <input type="hidden" name="authId" id="authId" value="${authId}"/>
    <input type="hidden" name="pageCustomer.pageSize" id="pageSize" value="${pageCustomer.pageSize}" />
    <input type="hidden" name="pageCustomer.excelExp" id="excelExp" value="${pageCustomer.excelExp}" />
   <!-- 查询条件 -->
   <div id="filter" class="filter">
    <div id="item" class="item">
    <div class="where">
    	<div class="and">
           <span>客户ID:</span><input type="text" class="small" name="filter_LIKES_customerid" value="${param['filter_LIKES_customerid']}" />
        </div>
    	<div class="and">
           <span>客户名称:</span><input type="text" class="small" name="filter_LIKES_customername" value="${param['filter_LIKES_customername']}" />
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
      <table class="mtab tab_sel" id="sel_results" cellpadding="2" cellspacing="1" border="0">
			<tr align="center">
				<th class="first"><input type="checkbox" id="box" name="box" onclick="checkedAll('ids')"/></th>
				<th>客户ID</th>
				<th>客户名称</th>
				<th>开始时间</th>
				<th>结束时间</th>
			</tr>
			<c:forEach items="${pageCustomer.result}" var="a" varStatus="c">
				<tr align="center">
					<td><input type="checkbox"  value="${a.customerid}" id="id_${a.customerid}" name="ids"/></td>
					<td><a href="#" onclick="checkid('${a.customerid}');">${a.customerid}</a></td>
					<td>${a.customername}</td>
					<td>${a.begintime}</td>
					<td>${a.endtime}</td>

				</tr>
			</c:forEach>
		</table>
		</form>
	  <!-- 分页区域-->
	  <div id="page" class="page">
	  <wlps:page page="${pageCustomer}" simpleTheme="true"/></div>
    <!-- end--------------------------------------- -->
</div>

</body>
</html>
