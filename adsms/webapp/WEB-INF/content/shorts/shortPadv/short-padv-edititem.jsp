<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>内部办公自动化系统</title>
<%@ include file="/common/head.jsp" %>
<link rel="stylesheet" href="${ctx}/css/content.css" type="text/css" />
<script src="${ctx}/js/jQuery/validate/jquery.validate.js" type="text/javascript"></script>
<link href="${ctx}/js/jQuery/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>

<script type="text/javascript">
	$(function() {
	/* 	$("#status").val("${param.filter_EQI_status}");
		
		//输入验证
		$(".rnum").each(function(i,item){
				$(item).rules("add", {number:true});
			}); */
	});
	
	// 返回
	function skip(url) {
		location.href = url;
	}
	// 返回
	function save2() {
		$("#mainForm").submit();
	}
	
</script>

<style type="text/css">
	.operate .btn{padding: 0;}
	.btn a{background:url(../../images/b_btn.gif) left 0;  color:#000; text-decoration:none; height:22px; float:left; cursor:hand; margin:0 8px 0 0;}
	.btn a:hover{background:url(../../images/b_btn.gif) left -22px;height:22px;}
	.btn a span{background:url(../../images/b_btn.gif) right 0;  padding:4px 8px 0px 0; margin:0 0 0 8px; float:left; height:18px;}
	.btn a:hover span{background:url(../../images/b_btn.gif) right -22px; color:#000;  padding:4px 8px 0px 0; margin:0 0 0 8px; height:18px; }
	.btn .disabled{color: #8B8B8B;cursor: default;}
	.btn .disabled:hover {background:url(../../images/b_btn.gif) left 0;cursor: default;}
	.btn .disabled:hover span{background:url(../../images/b_btn.gif) right 0;color: #8B8B8B }
</style>
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
   <form id="mainForm" action="short-padv!saveShortPadvItem.action" method="post">
    <input type="hidden" name="parentId" id="parentId" value="${entity.id}"/>
    <input type="hidden" name="authId" id="authId" value="${authId}"/>
   <!-- 查询条件 -->
   <%-- <div id="filter" class="filter">
    <div id="item" class="item">
    <div class="where">
    	<div class="and">
           <span>客户ID:</span><input type="text" class="small" name="filter_LIKES_customid" value="${param['filter_LIKES_customid']}" />
        </div>
        <div class="and">
           <span>状态:</span><select name="filter_EQI_status" class="small" id="status">
           		<option value="">全部</option>
           		<option value="1">生效</option>
           		<option value="0">失效</option>
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
  </div> --%>
  
  
   <div class="mainC">
   <!-- 增删改查...操作菜单-->
  <div id="operate" class="operate">
    <div class="btn">
      <a href="#" title="保存" onclick="save2();"><span>保存</span></a>
      <a href="#" title="返回" onclick="skip('short-padv!list.action?authId=${authId}');" ><span>返回</span></a>
  	</div>
  </div>
    <!-- start--------------------------------------- -->
      <table class="mtab" cellpadding="2" cellspacing="1" border="0">
			<tr align="center">
				<th>省份编码</th>
				<th>省份名称</th>			
				<th>总次数</th>
				<th>当前次数</th>
			</tr>
			<c:forEach items="${shortPadvItems}" var="a" varStatus="vs">
				<tr align="center">
					<td>
						${a.provincecode}
						<input type="hidden" class="small" id="shortPadvItems[${vs.index}].id" name="shortPadvItems[${vs.index}].id"  value="${a.id}"/>
						<input type="hidden" class="small" id="shortPadvItems[${vs.index}].pid" name="shortPadvItems[${vs.index}].pid"  value="${a.pid}"/>
						<input type="hidden" class="small" id="shortPadvItems[${vs.index}].provincecode" name="shortPadvItems[${vs.index}].provincecode"  value="${a.provincecode}"/>
						<input type="hidden" class="small" id="shortPadvItems[${vs.index}].provincename" name="shortPadvItems[${vs.index}].provincename"  value="${a.provincename}"/>
					</td>
					<td>${a.provincename}</td>		
					<td>
						<input type="text" style="color:red;" class="small rnum" id="shortPadvItems[${vs.index}].pushtimes" name="shortPadvItems[${vs.index}].pushtimes"  value="${a.pushtimes}"/>
					</td>
					<td>
						<input type="text" class="small" id="shortPadvItems[${vs.index}].pushcounts" name="shortPadvItems[${vs.index}].pushcounts"  value="${a.pushcounts}"/>
					</td>
				</tr>
			</c:forEach>
		</table>
		</form>
	  <!-- 分页区域-->
    <!-- end--------------------------------------- -->
</div>

</body>
</html>
