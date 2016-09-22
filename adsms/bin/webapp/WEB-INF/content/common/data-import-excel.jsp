<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${auth.label}</title>
	<%@ include file="/common/head.jsp" %>
    <script src="${ctx}/js/jQuery/validate/jquery.validate.js" type="text/javascript"></script>
    <link href="${ctx}/js/jQuery/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<style type="text/css">
	#importexcel{
		margin:10px;
	}
	#importexcel td{
		padding:3px;
	}
	li{
		list-style: none;
	}
	</style>
	<script type="text/javascript">
	$(function(){
		var errormsg = $(".actionMessage").html();
		if(errormsg!=""&&errormsg!=null){
			jConfirm(" "+errormsg+" ","办公自动化系统",function(result){
			});
		}
		
		if(errormsg!="" && errormsg!=null){
			if (!$("div").is("#dialog_alert")) {
				$("<div id='dialog_alert' title='数据导入结果'>" + errormsg + "</div>").hide().appendTo(document.body);
			}
			$("#dialog_alert").dialog( {
				draggable : true,
				autoOpen : true,
				resizable : true,
				width : 600,
				minHeight: 200,
				height:300,
				modal : true,
				open : function(event, ui) {
					$(".ui-widget-overlay").css("width","100%");
					$("button").each(function(i) {
						if ($(this).html() == "确定") this.focus();
					});
				},
				close : function(event, ui) {
					$("#dialog_alert").dialog( "destroy" ).remove();
				},
				buttons : {
					确定 : function() {
						$(this).dialog('close').remove();
					}
				}
			});
		}
	});
	function loadBack(url,authId){
		var url = ctx+url+"?authId="+authId;
		location.href=url;
	}

	function excutf(){
		location.href="data-import-excel.action?authId="+'${authId}'+"&type="+'${type}'+"&className="+document.getElementById('className').value;
	}

	function downLoad(date, url){
		gotoPost({contentPath : date},'${ctx}/static-content?download=true');
	}
	
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#className").focus();
		//为inputForm注册validate函数
		$("#exportForm").validate({
			rules: {
				"file": {
					required: true
				}
			},
			messages: {
				"file": {
					required: "请选择导入excel文件"
				}
			},
			submitHandler: function(form) {
				   importExcel('${nameSpace}${enityName}!data-import-excel!impleExcel.action','${authId}');
				   }
							
		});
	});
	</script>
</head>
<body>
<div class="currloca">
  <p>数据导入&nbsp;»&nbsp;<span>${type=="business"?"业务数据导入":"基础数据导入"}</span></p>
  <div class="sitemap">
    <span style="display:block;float:left"></span>
    <span id="add2custom"><img class="pointer" onclick="add2custom('${authId}');return false;" id="aCustom" width="19" height="18" title="添加到常用操作" src="../images/add_custom.gif" /></span>
    <span id="showMap"><img onclick="showMap();return false;" id="sMap" class="pointer" width="64" height="18" title="后台导航" src="../images/map.gif"/></span>
  </div>
  <div style="display: none"> <s:actionmessage theme="simple" escape="false"/></div>
</div>
<div class="container">
  <!-- 内容区域 -->
  <div class="itemtitle"><h2>${type=="business"?"业务数据导入":"基础数据导入"}</h2></div>
  <form id="exportForm" action="data-import-excel!impleExcel.action" method="post"  enctype="multipart/form-data">
  <input  type="hidden"  name="type" value="${type}"/>
  
  <div style="overflow-x:auto;width:100%;" id="indiv">
  <table id="importexcel" style="margin:10px;" class="tab_cont"  cellpadding="0" cellspacing="0">
	  <tr>
		  <th class="first"><h3>支持Excel电子表格的导入：</h3></th>
		  <th class="last" style="color: #0099CC">类型描述：S(字符), I(整数), L(长整数), N(浮点数), D(日期), B(布尔)</th>
	  </tr>

	  <tr>
		  <td class="right">选择导入对象：</td>
		  <td><s:select list="%{objectList}" theme="simple" name="className" value="%{className}" id="className"  onchange="excutf()"
						              listKey="code" listValue="name" headerKey="" headerValue="---请选择---"></s:select>
		  </td>
	  </tr>
	  <tr>
		  <td class="right">选择文件：</td>
		  <td><input class="button" type="file" id="file" name="file"/></td>
	  </tr>
	  
	  <tr>
		  <td></td>
		  <td class="nol">
		  <input class="button" id="exportBtn" type="submit" value="导入"/>
		  <input class="button" type="button" value="返回" onclick="history.back();" />
		  </td>
	  </tr>
 </table>

<c:if test="${!empty coList}">

			  <table cellspacing="0" cellpadding="0" border="0" class="tab_cont content" style="margin:0 10px;padding:10px;">
			   <tr align="center">
			    <td align="center" width="60" style="background: #E3F3FF">名称</td><c:forEach items="${coList}" var="s" ><td align="center">${s.name}</td></c:forEach>
			   </tr>
			   <tr align="center">
			   <td align="center" style="background: #E3F3FF">类型</td><c:forEach items="${coList}" var="s" ><td align="center">${s.val1}</td></c:forEach>
			   </tr>
			   <tr align="center">
			  <td align="center" style="background: #E3F3FF">字段</td> <c:forEach items="${coList}" var="s" ><td align="center">${s.code}</td></c:forEach>
			   </tr>
			   <tr align="center">
			  <td align="center" style="background: #E3F3FF">组合唯一键</td> <c:forEach items="${coList}" var="s" ><td align="center">${s.val2!=null ? s.val2 : 'no'}</td></c:forEach>
			   </tr>
			   <tr align="center">
			  <td align="center" style="color: red;background: #E3F3FF">注意事项</td>
			  <td colspan="100" style="color: red;">
			  1、导入支持excel2000~2010格式文档<br/>
			  2、在excel数据整理过程需要注意格式匹配（特别：日期、数值）<br/>
			  3、布尔型数据只支持（是/否）<br/>
			  <a  href="javascript: downLoad('/resources/exceFile/${downLoadName}.xls','${ctx}/static-content?download=true')">模板文件下载</a>
			  </td>
			   </tr>
			  </table>
  </c:if>	  

  </div>
  </form>
  </div>
</body>
</html>
