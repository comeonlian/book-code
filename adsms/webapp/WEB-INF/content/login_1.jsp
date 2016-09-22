<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String type = request.getParameter("typelogin");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>云健康管理系统</title>
<link href="${ctx}/css/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script src="${ctx}/js/formValidator_min.js" type="text/javascript"></script>
<script src="${ctx}/js/formValidatorRegex.js" type="text/javascript"></script>
<script type="text/javascript">
	
	$(function(){
		//parent.document.getElementById("headerpage").contentWindow.location.reload(); 
		
		$("#username").focus(function(){
			if($(this).val() == '手机号/邮箱'){
				$("#username").val("");
			}
		});
		
		$("#username").blur(function(){
			if($.trim($(this).val()) == ''){
				$("#username").val("手机号/邮箱");
			}
		});
	});
	
	function userlogin() {
		var username = $("#username").val();
		var password = $("#password").val();
	
		if (username == "" || username=='手机号/邮箱') {
			alert('请输入登录帐号');
			$("#username").val("");
			$("#username").focus();
			return;
		}
		if (password == "") {
			alert('登录密码不能为空');
			$("#password").focus();
			return;
		}
		
		$("#tispspan").attr("style", "");
		$("#form2").submit();
	}
	
	/*$(function(){
		$.formValidator.initConfig({formid:"form2",submitonce:true,
			onerror:function(msg,obj,errorlist){
				
			}
		});

		
  		$("#username").formValidator({onshow:"请选择您的家庭成员",onfocus:"选择您的添加对象",oncorrect:"通过"}).inputValidator({min:1,empty:{leftempty:false,rightempty:false,emptyerror:"必须选择一个对象"},onerror:"必须选择一个对象"});
		$("#password").formValidator({onshow:"用于分析健康指数",onfocus:"用于分析健康指数",oncorrect:"成功输入 步数"}).inputValidator({min:1,onerror:"请输入正确的步数"}).regexValidator({regexp:"^([0-9]{1,})$",onerror:"格式不正确"});
	});*/
	
</script>
<style type="text/css">
input{font-size: 14px;color:#999999;}
</style>
</head>
<body>
	<div class="logo">
		<h1 style="height: 20px;width: 100%;">
		</h1>
		<h2>
			<img src="${ctx}/images/login/her_05.jpg" width="373" height="64">
		</h2>
	</div>

	<div class="huiyuan">
		<div class="huisr" style="height:255px;">
			<div class="huiseleft">
				<h1
					style="font-family:&#39;微软雅黑&#39;; font-size:18px; margin-bottom:50px;">立即登录云健康管理系统</h1>
				<form action="<%=path %>/loginweb.action" id="form2" method="post">
					<input type="hidden" value="<%=type%>" name="logintype"/>
					<ul>
						<li>
							<label>登录账户：</label>
							<input name="entity.username" type="text" id="username" value="手机号/邮箱">
						</li>
						<li>
							<label> 登录密码：</label>
							<input name="entity.password" type="password" id="password">
						</li>
					</ul>
					<span id="tispspan" style="display:none"><img src="<%=path %>/images/login/loading.gif">提交中……</span>
					<div class="f_login_but">
						<input name="tijiaoyuding" id="tijiaoyuding" type="button" value="" class="l_button" onclick="userlogin();">
					</div>
				</form>
			</div>
			<h2></h2>
			<div class="huiseright">
				<h3 style="text-align:left;">如果您还不是会员，请注册</h3>
				<h4 style="float:left; width:100%;">
					<a href="<%=path %>/registerpage.action" class="r_button"></a>
				</h4>
				<h5 style="width:100%; text-align:left;">
					<a href="http://www.flyever.com.cn/dhs/findPsd_step1.jsp">忘记密码?请点这里找回!</a>
				</h5>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<div class="footer">
		<div style="height: 70px;"></div>
		<iframe width="905px" height="100%" frameborder="0" scrolling="no" 
			src="<%=path %>/bottom.html" allowTransparency="true" style="margin-left: 5px;">
		</iframe>
	</div>
</body>
</html>