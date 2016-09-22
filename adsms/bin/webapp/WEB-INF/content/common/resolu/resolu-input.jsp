<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${id==null?'新增分辨率':'修改分辨率' }</title>
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
						// , remote: "user!checkLoginName.action?oldId=" + '${id}'
					}
				}
				
			});
		});
		// 选择弹出框
		/* function selectDlg(url, dialogTitle) {
			  	// url = "push-apk!selectapk.action";
				url = prepURL(url, "authId", "${authId}");
				var dg = new $.dialog({ id:'poupup', skin:'zxyg',rang:true,title:dialogTitle,fixed:true,
					drag:true, resize:true,cancelBtn:false,iconTitle:false,width:800,height:460,page:url,
					top:50,left:200,dgOnLoad:function(){}
				});
				dg.ShowDialog();
		} */
	</script>
</head>
<body>
<div class="currloca">
  <p>${auth.fullMenu}${id==null?'&nbsp;»&nbsp;<span>新增分辨率</span>':'&nbsp;»&nbsp;<span>修改分辨率</span>' }</p>
  <div class="sitemap">
  	<span style="display:block;float:left"><s:actionmessage theme="custom"/></span>
    <span id="showMap"><img onclick="showMap();return false;" id="sMap" class="pointer" width="64" height="18" title="后台导航" src="${ctx}/images/map.gif"/></span>
  </div>
</div>
<div class="container">
  <!-- 内容区域 -->
  <div class="itemtitle"><h2>${id==null?'新增分辨率':'修改分辨率' }</h2></div>
    
      <!-- 附加信息-->
  <div id="message" class="message">
    <span style="font-weight: bold;font-size: 14px;"><!-- 提示：资源列表... --></span>
  </div>
  <form id="inputForm" action="resolu!save.action" method="post" enctype="multipart/form-data">
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
						<td class="right"><span class="red">*</span>分辨率:</td>
						<td><input type="text" name="resolution" size="40" id="name" maxlength="255" value="${resolution}"/></td>
					</tr>
					<tr>
						<td class="right"><span class="red">*</span>状态:</td>
							<td><select name="isshow" id="isshow"
								style="width: 120px">
									<option value="1" <c:if test="${isshow == 1}">selected</c:if>>开放</option>
									<option value="0" <c:if test="${isshow == 0}">selected</c:if>>关闭</option>
							</select></td>
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
