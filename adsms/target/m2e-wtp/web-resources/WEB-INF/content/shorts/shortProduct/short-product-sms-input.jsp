<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${id==null?'新增产品':'修改产品' }</title>
	<%@ include file="/common/head.jsp" %>
	<link rel='STYLESHEET' type='text/css' href='${tree}/common/style.css' />
	<link rel="STYLESHEET" type="text/css" href="${tree}/dhtmlxtree.css" />
	<script type="text/javascript" src="${tree}/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${tree}/dhtmlxtree.js"></script>
	
    <script src="${ctx}/js/jQuery/validate/jquery.validate.js" type="text/javascript"></script>
	<link href="${ctx}/js/jQuery/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgdialog.js?t=self&s=zxyg"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/ui/jquery.ui.datepicker-zh-CN.js"></script>
	<script src="${ctx}/js/jQuery/timerange.js"></script>
	
	<script type="text/javascript">

		$(document).ready(function() {
			var dates =$('#begindate, #enddate').datepicker({
			    changeMonth: true,
			    showButtonPanel: true,
			    numberOfMonths: 1,
			    prevText:'前一月', 
			    nextText:'后一月', 
			    //minDate:dateAdd("d", -3),
			    //maxDate:dateAdd("m", 2),
			    onSelect:function(selectedDate) {
			        var option = this.id == "begindate" ? "minDate" : "maxDate";
			        var instance = $(this).data("datepicker");
			        var date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, selectedDate, instance.settings);
			        dates.not(this).datepicker("option", option, date);
			    }
			});
			
			$("#inputForm").validate({
				rules: {
		 	 		customid:{
		 	 			required:true
		 	 		}
	        	}
			});
		});
		
		// 选择弹出框
		function selectDlg(url, dialogTitle) {
				url = prepURL(url, "authId", "${authId}");
				var dg = new $.dialog({ id:'poupup', skin:'zxyg',rang:true,title:dialogTitle,fixed:true,
					drag:true, resize:true,cancelBtn:false,iconTitle:false,width:800,height:460,page:url,
					top:50,left:200,dgOnLoad:function(){}
				});
				dg.ShowDialog();
		}
	</script>
	
	<script type="text/javascript">
		var tree2;
		function dbTreeinit() {
			tree2 = new dhtmlXTreeObject("treeboxbox_tree2", "100%", "100%", 0);
			tree2.setImagePath("${tree}/imgs/csh_bluebooks/");
			tree2.enableCheckBoxes(1);
			tree2.enableThreeStateCheckboxes(true);
	
			var v2 = "${treexml}";
			tree2.loadXMLString(v2);
	
			var v3 = "${citys}";
			var rights = v3.split('|');
			for ( var i = 0; i < rights.length; i++) {
				var r = rights[i];
				if (r != null && r != '') {
					tree2.setCheck(r, true);
				}
			}
		}
	
		function setingRight() {
	
			var oDiv = document.getElementById("sright");
			oDiv.style.display = oDiv.style.display == "none" ? "block" : "none";
	
			var iDiv = document.getElementById("content");
			iDiv.style.display = iDiv.style.display == "none" ? "block" : "none";
		}
	
		function submitRight() {
			setingRight();
			var d = tree2.getAllChecked();
			if (d != '') {
				var cts = '|';
				var citys = d.split(',');
				for ( var i = 0; i < citys.length; i++) {
					var c = citys[i];
					if (c != null && c != '') {
						cts = cts + c + "|";
					}
				}
				$("#citys").text(cts);
			} else {
				$("#citys").text('');
			}
		}
	</script>
	
</head>
<body onload="dbTreeinit();">
<div class="currloca">
  <p>${auth.fullMenu}${id==null?'&nbsp;»&nbsp;<span>新增产品</span>':'&nbsp;»&nbsp;<span>修改产品</span>' }</p>
  <div class="sitemap">
  	<span id="add2custom"></span>
    <span id="showMap"><img onclick="showMap();return false;" id="sMap" class="pointer" width="64" height="18" title="后台导航" src="${ctx}/images/map.gif"/></span>
  </div>
</div>

<div class="container">
  <!-- 内容区域 -->
  <div class="itemtitle"><h2>${id==null?'新增产品':'修改产品' }</h2></div>
    
      <!-- 附加信息-->
  <div id="message" class="message">
    <span style="font-weight: bold;font-size: 14px;"><!-- 提示：资源列表... --></span>
  </div>
  
  <form id="inputForm" action="short-product-sms!save.action" method="post">
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
						<td class="right">通道代码:</td>
						<td colspan="1">
							<input type="text" class="required" id="serviceCode" name="serviceCode"  size="40" maxlength="60" value="${serviceCode}"/>
						</td>
						
						<td class="right">通道名称:</td>
						<td colspan="1">
							<input type="text" class="required" id="serviceName" name="serviceName"  size="40" maxlength="60" value="${serviceName}"/>
						</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>通道类型:</td>
						<td colspan="1">
							<select name="smsType" id="smsType" style="width: 120px">
								<option value="1" <c:if test="${smsType == 1}">selected</c:if>>SMS</option>
								<option value="2" <c:if test="${smsType == 2}">selected</c:if>>WAP</option>
								<option value="3" <c:if test="${smsType == 3}">selected</c:if>>IVR</option>
							</select>
						</td>
						<td class="right"><span class="red">*</span>联网方式:</td>
						<td colspan="1">
							<input type="text" id="netMode" name="netMode" size="40" maxlength="255" value="${netMode}"/>
						</td>	
					</tr>
					
					<tr>
						<td class="right">号码:</td>
						<td colspan="1">
							<input type="text"  class="required" id="moNumber" name="moNumber" size="40" maxlength="255" value="${moNumber}"/>
						</td>
						
						<td class="right">指令:</td>
						<td colspan="1">
							<input type="text" id="moContent" name="moContent" size="40" maxlength="255" value="${moContent}"/>
						</td>
					</tr>
					
					<tr>
						<td class="right">单价(元):</td>
						<td colspan="1">
							<input type="text" class="required" id="price" name="price" size="40" maxlength="11"  value="${price}"/>
						</td>
						
						<td class="right">计费间隔时间(天):</td>
						<td colspan="1">
							<input type="text" id="feeDays" name="feeDays"  size="40" maxlength="11" value="${feeDays}"/>
						</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>开始时间(天):</td>
						<td><input type="text" class="required" name="begindate" size="40" id="begindate" maxlength="255" readonly="readonly" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${begindate}'/>"/></td>
						
						<td class="right"><span class="red">*</span>结束时间(天):</td>
						<td><input type="text" class="required" name="enddate" size="40" id="enddate" maxlength="255" readonly="readonly" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${enddate}'/>"/></td>
					</tr>
					
					<tr>
						<td class="right">开始时刻:</td>
						<td colspan="1">
							<input type="text" id="begintime" name="begintime" class="timeRange"  size="40" maxlength="11"  value="${begintime}"/>
						</td>
						
						<td class="right">结束时刻:</td>
						<td colspan="1">
							<input type="text" id="endtime" name="endtime" class="timeRange"  size="40" maxlength="11" value="${endtime}"/>
						</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>运营商:</td>
						<td colspan="1">
							<select name="provider" id="provider" style="width: 120px">
								<option value="1" <c:if test="${provider == 1}">selected</c:if>>移动</option>
								<option value="2" <c:if test="${provider == 2}">selected</c:if>>联通</option>
							</select>
						</td>
						<td class="right"><span class="red">*</span>状态:</td>
						<td colspan="1">
							<select name="status" id="status" style="width: 120px">
								<option value="1" <c:if test="${status == 1}">selected</c:if>>生效</option>
								<option value="0" <c:if test="${status == 0}">selected</c:if>>失效</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>优先级:</td>
						<td colspan="1">
							<input type="text" id="priority" name="priority"  size="40" maxlength="5" value="${priority}"/>
						</td>
						
						<td class="right"><span class="red">*</span>配置条数:</td>
						<td colspan="1">
							<input type="text" id="confignum" name="confignum"  size="40" maxlength="11" value="${confignum}"/>
						</td> 
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>删除关键号码:</td>
						<td colspan="3">
							<input type="text" id="deleteKeyNumber" name="deleteKeyNumber"  size="140" maxlength="255" value="${deleteKeyNumber}"/>
						</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>删除关键字:</td>
						<td colspan="3">
							<input type="text" id="deleteKeyWord" name="deleteKeyWord"  size="140" maxlength="255" value="${deleteKeyWord}"/>
						</td> 
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>完成广播:</td>
						<td colspan="3">
							<input type="text" id="finishRadio" name="finishRadio"  size="140" maxlength="255" value="${finishRadio}"/>
						</td> 
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>完成关键号码:</td>
						<td colspan="3">
							<input type="text" id="finishKeyNumber" name="finishKeyNumber"  size="140" maxlength="100" value="${finishKeyNumber}"/>
						</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>完成关键字:</td>
						<td colspan="3">
							<input type="text" id="finishKeyWord" name="finishKeyWord"  size="140" maxlength="100" value="${finishKeyWord}"/>
						</td> 
					</tr>
					
					<tr>
						<td colspan="4">
							<div id="content" class="content">
								<div id="indiv" style="width: 100%; OVERFLOW-X: auto;">
									<table class="tab_cont" width="100%" cellspacing="0" cellpadding="0" border="0" align="left">
										<tbody>
											<tr>
												<th class="first">IVR相关</th>
												<th class="last" colspan="3"></th>
											</tr>
											
											<tr>
												<td class="right">错误尝试次数:</td>
												<td colspan="1">
													<input type="text" id="errDegree" name="errDegree"  size="30" maxlength="11" value="${errDegree}"/>
												</td>
												<td class="right">有效值(秒):</td>
												<td colspan="1">
													<input type="text" id="limitValue" name="limitValue"  size="30" maxlength="11" value="${limitValue}"/>
												</td>
											</tr>
											
											<tr>
												<td class="right"><span class="red">*</span>拨打时间(秒):以"-"划分:</td>
												<td colspan="3">
													<textarea style="width:540px;height:60px;" id="callSpan" name="callSpan" >${callSpan}</textarea>
												</td>
											</tr>
											
											<tr>
												<td class="right"><span class="red">*</span>按键信息:以"-"划分:</td>
												<td colspan="3">
													<textarea style="width:540px;height:80px;" id="keyInfoSpan" name="keyInfoSpan" >${keyInfoSpan}</textarea>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>客户编号(,分割):</td>
						<td colspan="3">
							<textarea style="width:600px;height:140px;" class="required" id="customid" name="customid" >${customid}</textarea>
						</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>开通城市:</td>
						<td colspan="3">
							<textarea style="width:580px;height:120px;" class="required" id="citys" name="citys" >${citys}</textarea>
							<input type="button" name="Submit" value="选择城市" onclick="setingRight();"/>
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
	    
	    <div id="sright" style="display: none;">
				<table>
					<tr>
						<td valign="top">
							<div id="treeboxbox_tree2" style="width: 350; height: 800; background-color: #f5f5f5; border: 1px solid Silver;; overflow: auto;"></div>
						</td>
						<td style="padding-left: 25; padding-top: 100;" valign="top">
							<input type="button" id="button" name="button" class="button2_60" onclick="submitRight();" value="提交" />
							<input type="button" class="button2_60"  onclick="setingRight();" value=" 返回"/>
						</td>
					</tr>
				</table>
			</div>
  </form>
</div>
</body>
</html>
