<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${id==null?'新增URL':'修改URL' }</title>
<%@ include file="/common/head.jsp" %>
<link rel='STYLESHEET' type='text/css' href='${tree}/common/style.css' />
<link rel="STYLESHEET" type="text/css" href="${tree}/dhtmlxtree.css" />
<script type="text/javascript" src="${tree}/dhtmlxcommon.js"></script>
<script type="text/javascript" src="${tree}/dhtmlxtree.js"></script>

<script src="${ctx}/js/jQuery/validate/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/jQuery/ui/jquery.ui.datepicker-zh-CN.js"></script>
<link href="${ctx}/js/jQuery/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgdialog.js?t=self&s=zxyg"></script>
<script src="${ctx}/js/jQuery/timerange.js"></script>
<style>
.gonggao{
    position:absolute;
    top:80px;
    left:200px;
    width:430px;
    height:300px;
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
    
    $("#inputForm").validate({
    	rules: {
    		customerid: {
    			required: true
    		},
    		urlname: {
    			required: true
    		},
    		url: {
    			required: true
    		},
    		status: {
    			required: true
    		},
    		citys: {
    			required: true
    		}
    	}
    });
});

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
//选择弹出框
function selectDlg(url, dialogTitle) {
  	// url = "push-apk!selectapk.action";
  	//alert(url);
	url = prepURL(url, "authId", "${authId}");
	var dg = new $.dialog({ id:'poupup', skin:'zxyg',rang:true,title:dialogTitle,fixed:true,
		drag:true, resize:true,cancelBtn:false,iconTitle:false,width:800,height:460,page:url,
		top:50,left:200,dgOnLoad:function(){}
	});
	dg.ShowDialog();
}
</script>

</head>
<body onload="dbTreeinit();">
<div class="currloca">
  <p>${auth.fullMenu}${id==null?'&nbsp;»&nbsp;<span>新增URL</span>':'&nbsp;»&nbsp;<span>修改URL</span>' }</p>
  <div class="sitemap">
  	<span id="add2custom"></span>
    <span id="showMap"><img onclick="showMap();return false;" id="sMap" class="pointer" width="64" height="18" title="后台导航" src="${ctx}/images/map.gif"/></span>
  </div>
</div>

<div class="container">
  <!-- 内容区域 -->
  <div class="itemtitle"><h2>${id==null?'新增URL':'修改URL' }</h2></div>
    
      <!-- 附加信息-->
  <div id="message" class="message">
    <span style="font-weight: bold;font-size: 14px;"><!-- 提示：资源列表... --></span>
  </div>
  
  <form id="inputForm" action="customer-url!save.action" method="post">
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
							<input type="text" id="customid" name="customid" size="30" maxlength="20" value="${customid}"/>
							<input type="button" onclick="selectDlg('customer-url!selectresource.action', '选择资源客户');" value="选择资源客户"/>
						</td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>URL名称:</td>
						<td><input type="text" name="urlname" size="40" id="urlname" maxlength="50" value="${urlname}"/></td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>URL:</td>
						<td><input type="text" name="url" size="40" id="url" maxlength="50" value="${url}"/></td>
					</tr>
					
					<tr>
						<td class="right"><span class="red">*</span>是否运营:</td>
						<td colspan="3">
							<select name="status" id="status" style="width: 120px">
								<option value="1" <c:if test="${status == 1}">selected</c:if>>运营</option>
								<option value="0" <c:if test="${status == 0}">selected</c:if>>关闭</option>
							</select>
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
