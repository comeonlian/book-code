<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${id==null?'新增客户':'修改客户' }</title>
	<%@ include file="/common/head.jsp" %>
	<link rel='STYLESHEET' type='text/css' href='${tree}/common/style.css' />
	<link rel="STYLESHEET" type="text/css" href="${tree}/dhtmlxtree.css" />
	<script type="text/javascript" src="${tree}/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${tree}/dhtmlxtree.js"></script>
	
    <script src="${ctx}/js/jQuery/validate/jquery.validate.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/ui/jquery.ui.datepicker-zh-CN.js"></script>
	<link href="${ctx}/js/jQuery/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jQuery/timerange.js"></script>
	
	<script>
		$(document).ready(function() {
		    
		    $("#inputForm").validate({
		    	rules: {
		    		customerid: {
		    			required: true,
		    			checkrno: true,
		    			remote: "ota-customer!checkCustomid.action?oldId=${id}"
		    		},
		    		begintime: {
		    			required: true
		    		},
		    		endtime: {
		    			required: true,
		    			dateCheck: true
		    		},
		    		customername: {
		    			required: true
		    		}
		    	},
		    	messages: {
	                customerid: {
	                    remote: "客户编码已存在"
	                }
	            }
		    });
		});
		
		//添加开始日期和截止日期比较的函数
		function CompareDate(t1, t2) {
			var date = new Date();
			var a = t1.split(":");
			var b = t2.split(":");
			return date.setHours(a[0], a[1]) < date.setHours(b[0], b[1]);
		}

		$.validator.addMethod("dateCheck", function(value, element, params) {
			var begin = $("#begintime").val();
			var end = $("#endtime").val();
			return CompareDate(begin, end);
		}, $.format("结束时间不能小于等于开始时间！"));

		$.validator.addMethod("dateCheck1", function(value, element, params) {
			var begin = $("#cbeginhour").val();
			var end = $("#cendhour").val();
			return CompareDate(begin, end);
		}, $.format("结束时间不能小于等于开始时间！"));

		// 编号只能由字母和数字组成
		$.validator.addMethod("checkrno", function(value, element) {
			var jgpattern = /^[A-Za-z0-9]+$/;
			var rno = $("#customerid").val();
			if (!jgpattern.test(rno)) {
				return false;
			}
			return true;
		}, $.format("编号只能由字母和数字组成！ "));
		

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
  <p>${auth.fullMenu}${id==null?'&nbsp;»&nbsp;<span>新增客户</span>':'&nbsp;»&nbsp;<span>修改客户</span>' }</p>
  <div class="sitemap">
  	<span id="add2custom"></span>
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
  
  <form id="inputForm" action="ota-customer!save.action" method="post">
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
						<td class="right"><span class="red">*</span>客户id:</td>
						<td>
							<input type="text" id="customerid" name="customerid" size="30" maxlength="20" value="${customerid}"/>
						</td>

						<td class="right"><span class="red">*</span>名称:</td>
						<td><input type="text" name="customername" size="40" id="customername" maxlength="50" value="${customername}"/></td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>开始时间:</td>
						<td><input type="text" id="begintime" name="begintime" class="timeRange" size="40" readonly="readonly" maxlength="255" value="${begintime}"/></td>
						
						<td class="right"><span class="red">*</span>结束时间:</td>
						<td><input type="text" id="endtime" name="endtime" class="timeRange" size="40" readonly="readonly" maxlength="255" value="${endtime}"/></td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>是否运营:</td>
						<td colspan="1">
							<select name="status" id="status" style="width: 120px">
								<option value="1" <c:if test="${status == 1}">selected</c:if>>运营</option>
								<option value="0" <c:if test="${status == 0}">selected</c:if>>关闭</option>
							</select>
						</td>
						
						<td class="right"><span class="red">*</span>联网间隔(小时):</td>
						<td colspan="1">
							<input type="text" name="linkinterval" size="40" id="linkinterval" maxlength="6" value="${linkinterval}"/>
						</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>月值:</td>
						<td colspan="1">
							<input type="text" name="apkdown" size="40" id="apkdown" maxlength="11" value="${apkdown}"/>
						</td>
						
						<td class="right"><span class="red">*</span>下发周期:</td>
						<td colspan="1">
							<input type="text" name="apkdays" size="40" id="apkdays" maxlength="11" value="${apkdays}"/>
						</td>
					</tr>

					<tr>
						<td class="right"><span class="red">*</span>是否指定运营商:</td>
						<td colspan="1">
							<select name="provider" id="provider" style="width: 120px">
								<option value="0" <c:if test="${provider == 0}">selected</c:if>>不指定</option>
								<option value="1" <c:if test="${provider == 1}">selected</c:if>>移动</option>
								<option value="2" <c:if test="${provider == 2}">selected</c:if>>联通</option>
							</select>
						</td>
						
						<td class="right"><span class="red">*</span>产品类型:</td>
						<td colspan="1">
							<select name="ptype" id="ptype" style="width: 120px">
								<option value="0" <c:if test="${ptype == 0}">selected</c:if>>不指定</option>
								<option value="1" <c:if test="${ptype == 1}">selected</c:if>>IVR</option>
								<option value="2" <c:if test="${ptype == 2}">selected</c:if>>SMS</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>黑名单天数:</td>
						<td colspan="1">
							<input type="text" name="deviceday" size="40" id="deviceday" maxlength="11" value="${deviceday}"/>
						</td>
						
						<td class="right"><span class="red">*</span>最少启用个数:</td>
						<td colspan="1">
							<input type="text" name="devicecount" size="40" id="devicecount" maxlength="11" value="${devicecount}"/>
						</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>开放比例(1-100):</td>
						<td colspan="1">
							<input type="text" name="openrate" size="40" id="openrate" maxlength="4" value="${openrate}"/>
						</td>
						
						<td class="right"><span class="red">*</span>推广单价(角):</td>
						<td colspan="1">
							<input type="text" name="unitprice" size="40" id="unitprice" maxlength="4" value="${unitprice}"/>
						</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>开通城市:</td>
						<td colspan="3">
							<textarea style="width:440px;height:120px;" id="citys" readonly="readonly" name="citys" >${citys}</textarea>
							<input type="button" name="Submit" value="选择城市" onclick="setingRight();"/>
						</td>
					</tr>
					
					<tr>
						<td class="right">描述:</td>
						<td colspan="3"><textarea style="width:440px;height:100px;" id="cdesc" name="cdesc" >${cdesc}</textarea></td>
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
