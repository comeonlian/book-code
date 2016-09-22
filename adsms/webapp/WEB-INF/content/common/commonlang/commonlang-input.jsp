<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${id==null?'新增语言':'修改语言' }</title>
	<%@ include file="/common/head.jsp" %>
	<link rel='STYLESHEET' type='text/css' href='${tree}/common/style.css' />
	<link rel="STYLESHEET" type="text/css" href="${tree}/dhtmlxtree.css" />
	<script type="text/javascript" src="${tree}/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${tree}/dhtmlxtree.js"></script>
    <script src="${ctx}/js/jQuery/validate/jquery.validate.js" type="text/javascript"></script>
	<link href="${ctx}/js/jQuery/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${ctx}/js/jQuery/ui/jquery.ui.datepicker-zh-CN.js"></script>
	<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgdialog.js?t=self&s=zxyg"></script>
	<script>

		$(document).ready(function() {
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					name: {
						required: true
					}
					,file:{
						required: true				
  					},
  					file :{
  						digits:true
  					}
				},
				 messages: {				
				} 
				
			});
		});		
		
		function checkcustomid(obj){
			if('${id}' != ''){
				return;
			}
			var customid = $(obj).val();
			$.ajax({
				url : 'custom!checkcustomid.action',
				type : 'post',
				data : {customid : customid},
				dataType : 'text',
				success : function(data){
					if(data == 'NO'){
						$(obj).val("");
						$("#mess").html("<div style='color:red;font-size:13px;font-weight:bold;'>该客户编号已经存在！</div>");
					}else{
						$("#mess").html("");
					}
				}
			})
		}
	</script>
</head>
<body>
<div class="currloca">
  <p>${auth.fullMenu}${id==null?'&nbsp;»&nbsp;<span>新增客户</span>':'&nbsp;»&nbsp;<span>修改客户</span>' }</p>
  <div class="sitemap">
  	<span style="display:block;float:left"><s:actionmessage theme="custom"/></span>
    <span id="showMap"><img onclick="showMap();return false;" id="sMap" class="pointer" width="64" height="18" title="后台导航" src="${ctx}/images/map.gif"/></span>
  </div>
</div>
<div class="container">
  <!-- 内容区域 -->
  <div class="itemtitle"><h2>${id==null?'新增客户':'修改客户' }</h2></div>
    
      <!-- 附加信息-->
  <div id="message" class="message">
    <span style="font-weight: bold;font-size: 14px;"><!-- 提示：资源列表... --></span>
  </div>
  <form id="inputForm" action="commonlang!save.action" method="post">
	  <!-- 列表区域-->
	  <div id="content" class="content input" > 
	    <div id="indiv" style="width:100%;OVERFLOW-X:auto;">
	    </div>
	    	<input type="hidden" name="id" value="${id}"/>
			<input type="hidden" name="authId" value="${authId}"/>
			<table class="tab_cont"  cellspacing="0" cellpadding="0" border="0" align="left" width="">
				<tbody>
				    <tr>
				      <th class="first" width="130">标准信息</th>
				      <th class="last" id="mess"></th>
				    </tr>
					<tr>
						<td class="right"><span class="red">*</span>名称:</td>
						<td><input type="text" name="lang" size="40" id="lang" maxlength="255" value="${lang}"/>&nbsp;&nbsp;<span style="color:red;">如:中国</span></td>
					</tr>
					<tr>
						<td class="right"><span class="red">*</span>简写:</td>
						<td><input type="text" name="simplified" size="40" id="simplified" maxlength="255" value="${simplified}" />&nbsp;&nbsp;<span style="color:red;">如:cn</span></td>
					</tr>
					 <tr >
					    <td class="right"><span class="red">*</span>描叙:</td>
					    <td>
					    	<textarea rows="3" cols="35" name="langdesc">${langdesc }</textarea>
					    </td>
					</tr> 
					<tr>
						<th class="first" width="130"></th>
						<th class="last">
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
