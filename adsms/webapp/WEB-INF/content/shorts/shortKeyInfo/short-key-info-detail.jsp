<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${id==null?'新增关键字':'修改关键字' }</title>
	<%@ include file="/common/head.jsp" %>
	<link rel='STYLESHEET' type='text/css' href='${tree}/common/style.css' />
	
</head>

<body>
<div class="currloca">
  <p>${auth.fullMenu}${id==null?'&nbsp;»&nbsp;<span>查看关键字</span>':'&nbsp;»&nbsp;<span>查看关键字</span>' }</p>
  <div class="sitemap">
  	<span id="add2custom"></span>
    <span id="showMap"><img onclick="showMap();return false;" id="sMap" class="pointer" width="64" height="18" title="后台导航" src="../images/map.gif"/></span>
  </div>
</div>

<div class="container">
  <!-- 内容区域 -->
  <div class="itemtitle"><h2>${id==null?'查看关键字':'查看关键字' }</h2></div>
    
      <!-- 附加信息-->
  <div id="message" class="message">
    <span style="font-weight: bold;font-size: 14px;"><!-- 提示：资源列表... --></span>
  </div>
  
  <form id="inputForm" action="short-key-info!save.action" method="post">
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
				      <th class="last"></th>
				    </tr>
				    <tr>
						<td class="right">比对内容:</td>
						<td>
							<textarea rows="3" cols="90" name="comtent" id="comtent" readonly="readonly" maxlength="200">${entity.comtent}</textarea>
						</td>
					</tr>
				    <tr>
						<td class="right"><span class="red">*</span>关键字:</td>
						<td>
							<textarea rows="20" cols="90" name="keytent" id="keytent" readonly="readonly" maxlength="200">${entity.keytent}</textarea>
						</td>
					</tr>
				    <tr>
						<td class="right">广告键:</td>
						<td>
							<textarea rows="3" cols="90" name="advkey" id="advkey" readonly="readonly" maxlength="200">${entity.advkey}</textarea>
						</td>
					</tr>
				    <tr>
						<td class="right">广告值:</td>
						<td>
							<textarea rows="3" cols="90" name="advtent" id="advtent" readonly="readonly" maxlength="200">${entity.advtent}</textarea>
						</td>
					</tr>
				    <tr>
						<td class="right">广告开始:</td>
						<td>
							<textarea rows="3" cols="90" name="advtip" id="advtip" readonly="readonly" maxlength="200">${entity.advtip}</textarea>
						</td>
					</tr>
				    <tr>
						<td class="right">广告结束:</td>
						<td>
							<textarea rows="3" cols="90" name="advend" id="advend" readonly="readonly" maxlength="200">${entity.advend}</textarea>
						</td>
					</tr>
				    <tr>
						<td class="right">禁止删除键:</td>
						<td>
							<textarea rows="3" cols="90" name="delkey" id="delkey" readonly="readonly" maxlength="200">${entity.delkey}</textarea>
						</td>
					</tr>
					
					<tr>
						<th class="first" width="130"></th>
						<th class="last">
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
