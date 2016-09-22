<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${id==null?'新增apk':'修改apk' }</title>
	<%@ include file="/common/head.jsp" %>
	
    <script src="${ctx}/js/jQuery/validate/jquery.validate.js" type="text/javascript"></script>
	<link href="${ctx}/js/jQuery/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${ctx}/js/jQuery/ui/jquery.ui.datepicker-zh-CN.js"></script>
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
	
	<script>
		$(document).ready(function() {
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					apkName: {
						required: true
						// , remote: "user!checkLoginName.action?oldId=" + '${id}'
					},
					beginDate: {
						required: true
					},
					endDate: {
						required: true
					},
					reso: {
						checkDesImg: '${id}',
						accept: "apk|APK"
					},
					iconfile: {
						checkDesImg: '${id}',
						accept: "jpg|bmp|gif|png|JPG|BMP|PNG|GIF"
					}
				}
				
			});
			
			// 校验描述图片：destype为2时不能为空
			jQuery.validator.addMethod("checkDesImg", function(value, element, param) {
				if (param == '') {
					if (value == '') {
						return false;
					}
				}
				return true;
			}, $.format("资源不能为空！"));

			var dates =$('#beginDate, #endDate').datepicker({
			    changeMonth: true,
			    showButtonPanel: true,
			    numberOfMonths: 1,
			    prevText:'前一月', 
			    nextText:'后一月', 
			    //minDate:dateAdd("d", -3),
			    //maxDate:dateAdd("m", 2),
			    onSelect:function(selectedDate) {
			        var option = this.id == "beginDate" ? "minDate" : "maxDate";
			        var instance = $(this).data("datepicker");
			        var date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, selectedDate, instance.settings);
			        dates.not(this).datepicker("option", option, date);
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
	</script>
	
	
</head>
<body>
<div class="currloca">
  <p>${auth.fullMenu}${id==null?'&nbsp;»&nbsp;<span>新增apk</span>':'&nbsp;»&nbsp;<span>修改apk</span>' }</p>
  <div class="sitemap">
  	<span style="display:block;float:left"><s:actionmessage theme="custom"/></span>
    <span id="showMap"><img onclick="showMap();return false;" id="sMap" class="pointer" width="64" height="18" title="后台导航" src="${ctx}/images/map.gif"/></span>
  </div>
</div>

<div class="container">
  <!-- 内容区域 -->
  <div class="itemtitle"><h2>${id==null?'新增apk':'修改apk' }</h2></div>
    
      <!-- 附加信息-->
  <div id="message" class="message">
    <span style="font-weight: bold;font-size: 14px;"><!-- 提示：资源列表... --></span>
  </div>
  
  <form id="inputForm" action="tbl-apk!save.action" method="post" enctype="multipart/form-data">
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
						<td class="right"><span class="red">*</span>应用名:</td>
						<td colspan="3"><input type="text" name="apkName" size="40" id="apkName" maxlength="255" value="${apkName}"/></td>
					</tr>
					
				    <tr>
						<td class="right"><span class="red">*</span>备注:</td>
						<td colspan="3"><input type="text" name="keyword" size="40" id="keyword" maxlength="255" value="${keyword}"/></td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>开始时间:</td>
						<td colspan="3"><input type="text" name="beginDate" size="40" id="beginDate" maxlength="255" readonly="readonly" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${beginDate}'/>"/></td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>结束时间:</td>
						<td colspan="3"><input type="text" name="endDate" size="40" id="endDate" maxlength="255" readonly="readonly" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${endDate}'/>"/></td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>APK文件:</td>
						<td colspan="3">
							<input class="button" type="file" id="reso" name="reso" value="${apkUrl}" /><c:if test="${!empty apkUrl}">上传文件：${apkUrl}</c:if>
						</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>简图:</td>
						<td colspan="3">
							<input type="file" id="iconfile" name="iconfile" value="${apkIcon}" style="width: 320px" />
							<input type="hidden" id="apkIcon" name="apkIcon" value="${apkIcon}" />
							<c:if test="${apkIcon != null && apkIcon != ''}">
								&nbsp;&nbsp;<a href="#" id="icondelete" onclick="clsGG('${apkIcon}');">查看原图</a>
							</c:if>
						</td>
					</tr>
					
					
					<tr>
						<td class="right"><span class="red">*</span>状态:</td>
						<td colspan="3">
							<select name="state" id="state" style="width: 120px">
								<option value="1" <c:if test="${state == 1}">selected</c:if>>开放</option>
								<option value="0" <c:if test="${state == 0}">selected</c:if>>关闭</option>
							</select>
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
