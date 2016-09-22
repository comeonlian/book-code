<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>访问日志详情</title>
	<%@ include file="/common/head.jsp" %>
	<link rel='STYLESHEET' type='text/css' href='${tree}/common/style.css' />
	<link rel="STYLESHEET" type="text/css" href="${tree}/dhtmlxtree.css" />
	<script type="text/javascript" src="${tree}/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${tree}/dhtmlxtree.js"></script>
	

	
	<script type="text/javascript">
	</script>
	
</head>
<body onload="IntiDiv();">
<div class="currloca">
  <p>${auth.fullMenu}${id==null?'&nbsp;»&nbsp;<span>查看日志</span>':'&nbsp;»&nbsp;<span>查看日志</span>' }</p>
  <div class="sitemap">
  	<span id="add2custom"></span>
    <span id="showMap"><img onclick="showMap();return false;" id="sMap" class="pointer" width="64" height="18" title="后台导航" src="../images/map.gif"/></span>
  </div>
</div>

<div class="container">
  <!-- 内容区域 -->
  <div class="itemtitle"><h2>${id==null?'查看日志':'查看日志' }</h2></div>
    
      <!-- 附加信息-->
  <div id="message" class="message">
    <span style="font-weight: bold;font-size: 14px;"><!-- 提示：资源列表... --></span>
  </div>
  
  <form id="inputForm" action="push-adver!save.action" method="post" enctype="multipart/form-data">

			<!-- 列表区域-->
	  <div id="content" class="content input">
	    <div id="indiv" style="width:100%;OVERFLOW-X:auto;">
	    </div>
			<table class="tab_cont" width="" cellspacing="0" cellpadding="0" border="0" align="left">
				<tbody>
				    <tr>
				      <th class="first" width="130">标准信息</th>
				      <th class="last" colspan="3"></th>
				    </tr>
				    <tr>
						<td class="right"><span class="red">*</span>访问时间:</td>
						<td><fmt:formatDate value="${entity.accesstime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						
						<td class="right"><span class="red">*</span>客户id:</td>
						<td>
							${entity.customerid}
						</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>mac:</td>
						<td>${entity.mac}</td>
						
						<td class="right"><span class="red">*</span>androidid:</td>
						<td>${entity.androidid}</td>
					</tr>

					<tr>
						<td class="right"><span class="red">*</span>cpu:</td>
						<td>${entity.cpu}</td>
						
						<td class="right"><span class="red">*</span>机型:</td>
						<td>${entity.modle}</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>品牌:</td>
						<td>${entity.brand}</td>
						
						<td class="right"><span class="red">*</span>是否wifi:</td>
						<td>
							<c:if test="${entity.wifi == 1}">是</c:if>
							<c:if test="${entity.wifi == 0}">否</c:if>
						</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>imsi:</td>
						<td>${entity.imsi}</td>
						
						<td class="right"><span class="red">*</span>imei:</td>
						<td>${entity.imei}</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>app版本号:</td>
						<td>${entity.appVersionCode}</td>
						
						<td class="right"><span class="red">*</span>启动应用包名:</td>
						<td>${entity.initPackageName}</td>
					</tr>
					

					<tr>
						<td class="right">是否平台内置:</td>
						<td colspan="1">
							<c:if test="${entity.inline == 1}">是</c:if>
							<c:if test="${entity.inline == 0}">否</c:if>
						</td>
						
						<td class="right">ip:</td>
						<td colspan="1">${entity.src}</td>
					</tr>

					<tr>
						<td class="right">是否有系统签名:</td>
						<td colspan="1">
							<c:if test="${entity.sign == 1}">是</c:if>
							<c:if test="${entity.sign == 0}">否</c:if>
						</td>
						
						<td class="right">联网方式:</td>
						<td colspan="1">${entity.netType}</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>分辨率:</td>
						<td>${entity.src}</td>
						
						<td class="right"><span class="red">*</span>语言国家编码:</td>
						<td>${entity.langCountry}</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>ROM总大小:</td>
						<td>${entity.romTotal}</td>
						
						<td class="right"><span class="red">*</span>ROM剩余空间:</td>
						<td>${entity.romLess}</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>sdcard总大小:</td>
						<td>${entity.sdcardTotal}</td>
						
						<td class="right"><span class="red">*</span>sdcar剩余空间:</td>
						<td>${entity.sdcardLess}</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>apn:</td>
						<td>${entity.apn}</td>
						
						<td class="right"><span class="red">*</span>是否测试模式:</td>
						<td>
							<c:if test="${entity.test == 1}">是</c:if>
							<c:if test="${entity.test == 0}">否</c:if>
						</td>
					</tr>
					
					<tr id="descriptText">
						<td class="right"><span class="red">*</span>sim卡信息:</td>
						<td colspan="3">
							<textarea style="width:440px;height:100px;" id="simParams" readonly="readonly" name="simParams" maxlength="4000" >${entity.simParams}</textarea>
						</td>
					</tr>
					
					<tr id="descriptText">
						<td class="right"><span class="red">*</span>响应内容:</td>
						<td colspan="3">
							<textarea style="width:440px;height:100px;" id="responseTxt" readonly="readonly" name="responseTxt" maxlength="4000" >${entity.responseTxt}</textarea>
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
