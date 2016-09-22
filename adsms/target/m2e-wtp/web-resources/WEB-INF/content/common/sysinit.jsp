<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
    request.setAttribute("now", new java.util.Date());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>welcome</title>
<%@ include file="/common/head.jsp"%>
<script>
	
</script>
</head>

<body>
<div class="currloca">
<p>${auth.fullMenu}</p>
<div class="sitemap"><span style="display: block; float: left"><s:actionmessage theme="custom" /></span> <span
    id="add2custom"><img class="pointer" onclick="add2custom('${authId}');return false;" id="aCustom" width="19"
    height="18" title="添加到常用操作" src="../images/add_custom.gif" /></span> <span id="showMap"><img
    onclick="showMap();return false;" id="sMap" class="pointer" width="64" height="18" title="后台导航"
    src="../images/map.gif" /></span></div>
</div>

<div class="container"><!-- 内容区域 -->
<div class="itemtitle">
<h2>${auth.label}</h2>
</div>


<!-- 附加信息-->
<div id="message" class="message">
当前时间：<fmt:formatDate value="${now}" pattern="yy/MM/dd kk:mm:ss EEEE" /></div>

<!-- 增删改查...操作菜单-->
<div id="operate" class="operate">
<div></div>

</div>

<!-- 列表区域-->
<div id="content" class="content">
<div id="indiv" style="width: 100%; OVERFLOW-X: auto;">


<form action="sysinit!${type}Export.action" method="post">
<table class="tab_cont" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
    <tbody>
        <tr>
            <th class="first"><b>备份数据为XML格式</b></th>
            <th class="last"><b></b></th>
        </tr>
    </tbody>
    <tr class="mouseout" onmouseout="this.className='mouseout'" onmousemove="this.className='mouseover'">
        <td colspan="2" height="40"><input type="submit" value="导出下载文件" /></td>
    </tr>
</table>
</form>

<br />
<br />

<form action="sysinit!${type}Restore.action" method="post" enctype="multipart/form-data">
<input type="hidden" name="type" value="${type}" />
<input type="hidden" name="authId" value="${authId}" />
<table class="tab_cont" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
    <tbody>
        <tr>
            <th class="first"><b>从XML文件恢复数据</b></th>
            <th class="last"><b></b></th>
        </tr>
    </tbody>
    <tbody>
        <tr class="mouseout" onmouseout="this.className='mouseout'" onmousemove="this.className='mouseover'">
            <td colspan="2"><input type="file" name="file" />  <input type="submit" value="导入恢复"/> 操作前请先备份，操作必须十分谨慎，避免数据重覆盖、丢失等问题.</td>
        </tr>
    </tbody>
</table>
</form>
</div>


</div>
</div>

</body>
</html>