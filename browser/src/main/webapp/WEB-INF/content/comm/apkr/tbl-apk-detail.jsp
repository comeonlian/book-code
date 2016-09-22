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
	<style>
			.gonggao{
			    position:absolute;
			    top:80px;
			    left:200px;
			    width:330px;
			    height:500px;
			    border:1px solid #993300;
			    background-color:#ffffff;
			}
			.gonggao h1{
			    line-height:50px;
			    font-size:24px;
			    font-family:"黑体";
			    font-weight:bold;
			    text-align:center;
			}
			.gonggao div{
			    line-height:30px;
			    font-size:18px;
			    font-family:"黑体";
			    text-align:center;
			    color:#993300;
			}
			.gonggao div a,.gonggao div a:visited{
			    color:#993300;
			}
	  </style>
	  
	<script type="text/javascript">
		function clsGG(srcpath) {
			//window.document.getElementById("viewimg").src= '${ctx}/showImage?imgfile='+srcpath;
			window.document.getElementById("viewimg").src = srcpath;
			document.all("gonggao").style.display = "block";
			// document.all("gonggao").style.display=(document.all("gonggao").style.display=="none")?"block":"none";
			//jQuery("#gonggao").toggle();        
		}
		function closediv() {
			document.all("gonggao").style.display = "none";
		}
		
		function backlist(url) {
			location.href = url;
		}
	</script>
	
</head>
<body>
<div class="currloca">
  <p>${auth.fullMenu}${id==null?'&nbsp;»&nbsp;<span>查看apk</span>':'&nbsp;»&nbsp;<span>查看apk</span>' }</p>
  <div class="sitemap">
  	<span id="add2custom"></span>
    <span id="showMap"><img onclick="showMap();return false;" id="sMap" class="pointer" width="64" height="18" title="后台导航" src="${ctx}/images/map.gif"/></span>
  </div>
</div>

<div class="container">
  <!-- 内容区域 -->
  <div class="itemtitle"><h2>${id==null?'查看apk':'查看apk' }</h2></div>
    
      <!-- 附加信息-->
  <div id="message" class="message">
    <span style="font-weight: bold;font-size: 14px;"><!-- 提示：资源列表... --></span>
  </div>
  
  <form id="inputForm" action="tb-apk!save.action" method="post" enctype="multipart/form-data">
  	<div id="gonggao" class="gonggao" style="display: none">
				<img name="viewimg" id="viewimg" style="width: 300; height: 500" />
				<a href="javascript:closediv();">关闭</a>
			</div>
  
	  <!-- 列表区域-->
	  <div id="content" class="content input">
	    <div id="indiv" style="width:100%;OVERFLOW-X:auto;">
	    </div>
	    	<input type="hidden" name="id" value="${id}"/>
			<input type="hidden" name="authId" value="${authId}"/>
			<input type="hidden" name="type" value="${type}"/>
			<table class="tab_cont" width="" cellspacing="0" cellpadding="0" border="0" align="left">
				<tbody>
				    <tr>
				      <th class="first" width="130">标准信息</th>
				      <th class="last" colspan="3"></th>
				    </tr>
				    <tr>
						<td class="right"><span class="red">*</span>ID:</td>
						<td>${entity.apkId}</td>
						
						<td class="right"><span class="red">*</span>应用名:</td>
						<td>${entity.apkName}</td>
					</tr>
				    <tr>
						<td class="right"><span class="red">*</span>版本号:</td>
						<td>${entity.apkVersion}</td>
						
						<td class="right"><span class="red">*</span>包名:</td>
						<td>${entity.apkPackage}</td>
					</tr>
				    <tr>
						<td class="right"><span class="red">*</span>开始时间:</td>
						<td>${entity.beginDate}</td>
						
						<td class="right"><span class="red">*</span>结束时间:</td>
						<td>${entity.endDate}</td>
					</tr>

				    <tr>
						<td class="right"><span class="red">*</span>文件大小:</td>
						<td>${entity.apkSize}</td>
						
						<td class="right">状态：</td>
						<td>
							<c:if test="${entity.state == 0}">关闭</c:if>
							<c:if test="${entity.state == 1}">开放</c:if>
						</td>
					</tr>

					
					<tr>
						<td class="right"><span class="red">*</span>APK访问路径:</td>
						<td><a href="${entity.apkUrl}">点击下载</a></td>
						
						<td class="right"><span class="red">*</span>ICON访问路径:</td>
						<td>
							<c:if test="${entity.apkIcon != null && entity.apkIcon != ''}">
								<a href="#" id="desdelete5" onclick="clsGG('${entity.apkIcon}');">查看原图</a>
							</c:if>
						</td>
					</tr>
					
					
					<tr>
						<th class="first" width="130"></th>
						<th class="last" colspan="3">
							<input class="button" type="button" value="返回" onclick="backlist('tbl-apk!list.action?authId=${authId}')"/>
						</th>
					</tr>
				</tbody>
			</table>
	    </div>
  </form>
</div>
</body>
</html>
