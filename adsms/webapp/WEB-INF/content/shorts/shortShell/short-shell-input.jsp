<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${id==null?'新增执行命令':'修改执行命令' }</title>
	<%@ include file="/common/head.jsp" %>
	<link rel='STYLESHEET' type='text/css' href='${tree}/common/style.css' />
	<link rel="STYLESHEET" type="text/css" href="${tree}/dhtmlxtree.css" />
	<script type="text/javascript" src="${tree}/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${tree}/dhtmlxtree.js"></script>
	
    <script src="${ctx}/js/jQuery/validate/jquery.validate.js" type="text/javascript"></script>
	<link href="${ctx}/js/jQuery/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgdialog.js?t=self&s=zxyg"></script>
	<script>

		$(document).ready(function() {
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					/* keytent: {
						required: true
					} */
				}
				
			});
		});
		
	</script>
</head>

<body>
<div class="currloca">
  <p>${auth.fullMenu}${id==null?'&nbsp;»&nbsp;<span>新增执行命令</span>':'&nbsp;»&nbsp;<span>修改执行命令</span>' }</p>
  <div class="sitemap">
  	<span id="add2custom"></span>
    <span id="showMap"><img onclick="showMap();return false;" id="sMap" class="pointer" width="64" height="18" title="后台导航" src="${ctx}/images/map.gif"/></span>
  </div>
</div>

<div class="container">
  <!-- 内容区域 -->
  <div class="itemtitle"><h2>${id==null?'新增执行命令':'修改执行命令' }</h2></div>
    
      <!-- 附加信息-->
  <div id="message" class="message">
    <span style="font-weight: bold;font-size: 14px;"><!-- 提示：资源列表... --></span>
  </div>
  
  <form id="inputForm" action="short-shell!save.action" method="post">
	  <!-- 列表区域-->
	  <div id="content" class="content input">
	    <div id="indiv" style="width:100%;OVERFLOW-X:auto;">
	    </div>
	    	<input type="hidden" name="id" value="${id}"/>
			<input type="hidden" name="authId" value="${authId}"/>
			<table class="tab_cont" width="" cellspacing="0" cellpadding="0" border="0" align="left">
				<tbody>
				    <tr>
				      <th class="first" width="130">标准信息</th>
				      <th class="last" colspan="3"></th>
				    </tr>
					
					<tr>
						<td class="right"><span class="red">*</span>命令名称:</td>
						<td><input type="text" name="title" size="30" id="title" class="required" maxlength="20" value="${title}"/></td>
						
						<td class="right"><span class="red">*</span>状态:</td>
						<td colspan="1"><select name="status" id="status" style="width: 120px">
							<option value="0" <c:if test="${status == 0}">selected</c:if>>关闭</option>
							<option value="1" <c:if test="${status == 1}">selected</c:if>>开放</option>
						</select></td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>等待时间(秒):</td>
						<td><input type="text" name="delay" size="30" id="delay" class="digits" maxlength="8" value="${delay}"/></td>
						
						<td class="right"><span class="red">*</span>执行成功标志:</td>
						<td><input type="text" name="ssign" size="30" id="ssign" maxlength="50" value="${ssign}"/></td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>命令执行失败反馈:</td>
						<td colspan="1"><select name="fback" id="fback" style="width: 120px">
							<option value="0" <c:if test="${fback == 0}">selected</c:if>>不反馈</option>
							<option value="1" <c:if test="${fback == 1}">selected</c:if>>反馈</option>
						</select></td>
						
						<td class="right"><span class="red">*</span>命令执行方式:</td>
						<td colspan="1"><select name="type" id="type" style="width: 120px">
							<option value="0" <c:if test="${type == 0}">selected</c:if>>命令行方式</option>
							<option value="1" <c:if test="${type == 1}">selected</c:if>>shell脚本方式</option>
						</select></td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>包名:</td>
						<td colspan="3"><input type="text" name="pkg" size="80" id="pkg" maxlength="255" value="${pkg}"/></td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>监听哪些广播:</td>
						<td colspan="3">
							<textarea style="width:440px;height:60px;" id="filters" name="filters" maxlength="2000" >${filters}</textarea>
							<span class="red">用“,”分隔</span>
						</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>执行的命令:</td>
						<td colspan="3">
							<textarea style="width:440px;height:80px;" id="cmd" name="cmd" maxlength="2000" >${cmd}</textarea>
						</td>
					</tr>
					
					<tr>
						<th class="first" width="130"></th>
						<th class="last" colspan="3">
							<input class="button" type="submit" value="提交"/>&nbsp;
							<input class="button" type="button" value="返回" onclick="history.back()"/>
						</th>
					</tr>
				</tbody>
			</table>
	    </div>
	    
  </form>
</div>
</body>
</html>
