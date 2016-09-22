<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${id==null?'修改设备':'修改设备' }</title>
	<%@ include file="/common/head.jsp" %>
	
    <script src="${ctx}/js/jQuery/validate/jquery.validate.js" type="text/javascript"></script>
	<link href="${ctx}/js/jQuery/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${ctx}/js/jQuery/ui/jquery.ui.datepicker-zh-CN.js"></script>
	<script>

		$(document).ready(function() {
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					apkname: {
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
  <p>${auth.fullMenu}${id==null?'&nbsp;»&nbsp;<span>修改设备</span>':'&nbsp;»&nbsp;<span>修改设备</span>' }</p>
  <div class="sitemap">
  	<span style="display:block;float:left"><s:actionmessage theme="custom"/></span>
    <span id="showMap"><img onclick="showMap();return false;" id="sMap" class="pointer" width="64" height="18" title="后台导航" src="${ctx}/images/map.gif"/></span>
  </div>
</div>

<div class="container">
  <!-- 内容区域 -->
  <div class="itemtitle"><h2>${id==null?'修改设备':'修改设备' }</h2></div>
    
      <!-- 附加信息-->
  <div id="message" class="message">
    <span style="font-weight: bold;font-size: 14px;"><!-- 提示：资源列表... --></span>
  </div>
  
  <form id="inputForm" action="short-device!save.action" method="post" enctype="multipart/form-data">
	  <!-- 列表区域-->
	  <div id="content" class="content input">
	    <div id="indiv" style="width:100%;OVERFLOW-X:auto;">
	    </div>
	    	<input type="hidden" name="id" value="${id}"/>
			<input type="hidden" name="authId" value="${authId}"/>
			<input type="hidden" name="selectImei" value="${selectImei}"/>
			<input type="hidden" name="selectMac" value="${selectMac}"/>
			<input type="hidden" name="selectAndroidid" value="${selectAndroidid}"/>
			<table class="tab_cont" width="" cellspacing="0" cellpadding="0" border="0" align="left">
				<tbody>
				    <tr>
				      <th class="first" width="130">标准信息</th>
				      <th class="last" colspan="3"></th>
				    </tr>
				    <tr>
						<td class="right"><span class="red">*</span>设备ID:</td>
						<td>${deviceid}</td>
						
						<td class="right"><span class="red">*</span>imsi:</td>
						<td>${imsi}</td>
					</tr>
				    <tr>
						<td class="right"><span class="red">*</span>mac:</td>
						<td>${mac}</td>
						
						<td class="right"><span class="red">*</span>imei:</td>
						<td>${imei}</td>
					</tr>
				    <tr>
						<td class="right"><span class="red">*</span>客户ID:</td>
						<td>${customerid}</td>
						
						<td class="right"><span class="red">*</span>创建时间:</td>
						<td><fmt:formatDate value="${accesstime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>黑名单:</td>
						<td><select name="black" id="black" style="width: 120px">
							<option value="1" <c:if test="${black == 1}">selected</c:if>>否</option>
							<option value="0" <c:if test="${black == 0}">selected</c:if>>黑名单</option>
						</select></td>
						
						<td class="right"><span class="red">*</span>设备状态:</td>
						<td><select name="status" id="status" style="width: 120px">
							<option value="1" <c:if test="${status == 1}">selected</c:if>>开放</option>
							<option value="0" <c:if test="${status == 0}">selected</c:if>>关闭</option>
						</select></td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>测试模式:</td>
						<td colspan="3"><select name="test" id="test" style="width: 120px">
							<option value="0" <c:if test="${test == 0}">selected</c:if>>否</option>
							<option value="1" <c:if test="${test == 1}">selected</c:if>>是</option>
						</select></td>
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
