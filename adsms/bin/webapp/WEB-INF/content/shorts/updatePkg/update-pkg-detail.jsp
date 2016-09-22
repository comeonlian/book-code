<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${id==null?'查看apk':'查看apk' }</title>
	<%@ include file="/common/head.jsp" %>
	<link rel='STYLESHEET' type='text/css' href='${tree}/common/style.css' />
	<link rel="STYLESHEET" type="text/css" href="${tree}/dhtmlxtree.css" />
	<script type="text/javascript" src="${tree}/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${tree}/dhtmlxtree.js"></script>
	
    <script src="${ctx}/js/jQuery/validate/jquery.validate.js" type="text/javascript"></script>
	<link href="${ctx}/js/jQuery/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${ctx}/js/jQuery/ui/jquery.ui.datepicker-zh-CN.js"></script>
	<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgdialog.js?t=self&s=zxyg"></script>
	
	
</head>
<body>
<div class="currloca">
  <p>${auth.fullMenu}${id==null?'&nbsp;»&nbsp;<span>查看apk</span>':'&nbsp;»&nbsp;<span>查看apk</span>' }</p>
  <div class="sitemap">
  	<span id="add2custom"></span>
    <span id="showMap"><img onclick="showMap();return false;" id="sMap" class="pointer" width="64" height="18" title="后台导航" src="../images/map.gif"/></span>
  </div>
</div>

<div class="container">
  <!-- 内容区域 -->
  <div class="itemtitle"><h2>${id==null?'查看apk':'查看apk' }</h2></div>
    
      <!-- 附加信息-->
  <div id="message" class="message">
    <span style="font-weight: bold;font-size: 14px;"><!-- 提示：资源列表... --></span>
  </div>
  
  <form id="inputForm" action="update-pkg!save.action" method="post" enctype="multipart/form-data">
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
						<td class="right"><span class="red">*</span>应用名:</td>
						<td>${entity.apkname}</td>
						<td class="right"><span class="red">*</span>版本号:</td>
						<td>${entity.apkVersion}</td>
					</tr>
				    <tr>
						<td class="right"><span class="red">*</span>版本名:</td>
						<td>${entity.packagename}</td>
						<td class="right"><span class="red">*</span>主类名:</td>
						<td>${entity.mainClassName}</td>
					</tr>
				    <tr>
						<td class="right"><span class="red">*</span>文件大小:</td>
						<td>${entity.apksize}</td>
						<td class="right"><span class="red">*</span>状态:</td>
						<td>
							<c:if test="${entity.status == 1}">开放</c:if>
							<c:if test="${entity.status == 0}">关闭</c:if>
						</td>
					</tr>
					<tr>
						<td class="right"><span class="red">*</span>APK访问路径:</td>
						<td colspan="3">
							${entity.downurl}
						</td>
					</tr>
					<tr>
						<td class="right"><span class="red">*</span>APK存储路径:</td>
						<td colspan="3">
							${entity.filepath}
						</td>
					</tr>
					<tr>
						<td  class="right">MD5:</td>
                        <td colspan="3">${entity.md5}</td>
					</tr>
					<tr>
						<td  class="right">客户ID:</td>
                        <td colspan="3">
                        	<textarea id="customers" name="customers" rows="8" cols="100" readonly="readonly">${customers}</textarea>
                        </td>
					</tr>
					<tr>
						<th class="first" width="130"></th>
						<th class="last" colspan="3">
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
