<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'borrow.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="statics/css/style.css">
	<!-- <link rel="stylesheet/less" type="text/css" href="css/style.less" /> -->
	<script type="text/javascript" src="statics/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="statics/js/all.js"></script>
	<script type="text/javascript">
		var workStatus = false;
		var assetsStatus = false;
		var userdetiaStatus = false;
		$(document).ready(function(){
			$("#save_btn1").click(function(){
				if(confirm("确定保存所填的信息吗?")){
				//验证填写信息的合法性
				
				
				workStatus = true;
				}
			});
			$("#save_btn2").click(function(){
				if(confirm("确定保存所填的信息吗?")){
				//验证填写信息的合法性
				
				
				assetsStatus = true;
				}
			});
			$("#save_btn3").click(function(){
				if(confirm("确定保存所填的信息吗?")){
				//验证填写信息的合法性
				
				
				userdetiaStatus = true;
				}
			});
		});
		
		function doIsSubmit(){
			if(confirm("确定提交吗?")){
				//检验三张表信息是否都填写完毕
				if(workStatus && assetsStatus && userdetiaStatus){
					//修改状态为审核中
					$("#status").text("审核中……");
					
					//让提交按钮消失
					var currentBtn = document.getElementById("isSubmit");
					currentBtn.style.display="none";
					
					/* window.location.href="borrow/doBorrow"; */
				}
				
			}
		};
		
		
	</script>
  </head>
  
  <body>
<div class="zxcf_top_wper">
	<div class="zxcf_top px1000 clearfix">
		 <div class="zxcf_top_l fl">
		    <img src="statics/images/zxcf_phone.png" alt="">
		    400-027-0101(工作时间9:00-17:30)
		    <a href=""><img src="statics/statics/images/zxcf_weixin.png" alt=""></a>
		    <a href=""><img src="statics/images/zxcf_sina.png" alt=""></a>
		    <a href=""><img src="statics/images/zxcf_qq.png" alt=""></a>
		 </div>
		 <div class="zxcf_top_r fr">
		 	<a href="" class="curspan">立即登录</a>
		 	<span>|</span>
		 	<a href="">免费注册</a>
		 	<span>|</span>
		 	<a href="">常见问题</a>
		 </div>
	</div>
</div>
<!-- end top -->
<div class="zxcf_nav_wper">
	<div class="zxcf_nav clearfix px1000">
		 <div class="zxcf_nav_l fl"><img src="statics/images/zxcf_logo.png" alt=""></div>
		 <div style="margin-top: 20px"  class="fr">
		 		<img src="statics/images/zxcf_perinfo.png" alt="">
		 	<span>
		    <a style="color: green;" href="user/toMyUser">我的账户</a></span>
		 </div>
	</div>
</div>
<div class="zxcf_menu_wper">
	<div class="zxcf_menu px1000">
		 <a href="" class="zm_cura">首页</a>
		  <a href="invest/toInvest">我要投资</a>
		  <a href="borrow/toBorrow">我要借款</a>
		  <a href="check/toCheck">个人资料审查</a>
		  <a href="record/toRecord">交易记录</a>
	</div>
</div>
<!-- end  -->
<div class="bor_banner01">
	 
</div>
<!-- end banner -->
<div class="bor_con_wper">
	  <div class="bor_con px1000">
	  	    <div align="center" class="bor_detail">
	  	    	   <h2 style="margin-left: -200px" class="bor_detail_tit">
	  	    	   	  <span class="bor_decurspan">工作审核</span>
	  	    	   	  <span class="bor_decurspan">资产审核</span>
	  	    	   	  <span class="bor_decurspan">实名认证</span>
	  	    	   	  <!--<span>用户与审核关系</span> -->
	  	    	   </h2>
	  	    	   <div align="center" class="bor_detail_box">
	  	    	   	    <div class="bor_det_one clearfix pt30 pb30">  
	  	    	   	    	<!-- end l -->
                            <div style="margin-left: 180px" align="center" class="bor_det_oner fl">
                                 <form>
                                      <fieldset>
                                           <div>
                                               <label>编号</label>
                                               <input type="" >
                                           </div>
                                           <div class="mt15">
                                               <label>*用户ID</label>
                                               <input type="">
                                           </div>
                                           <div class="mt15">
                                               <label>*公司名</label>
                                               <input type="">
                                           </div>
                                           <div class="mt15">
                                               <label>*公司规模</label>
                                               <input type="" >
                                           </div>
                                           <div class="mt15">
                                               <label>*公司岗位</label>
                                               <input type="" >
                                           </div> 
                                           <div class="mt15">
                                               <label>*公司地址</label>
                                               <input type="" >
                                           </div> 
                                           <div class="mt15">
                                               <label>*公司经历</label>
                                               <input type="" >
                                           </div>                                    
                                            <div class="mt15">
                                               <label>*公司收入证明</label>
                                               <input type="file" value="浏览" >
                                           </div>  	    	   	    	  	     	  	  
                                           <div class="mt30">
                                               <label></label>
                                               <span id="save_btn1" class="bor_btn">保存信息</span>
                                           </div>
                                      </fieldset>
                                 </form>
	  	    	   	    	  </div>  	    	   	    	
	  	    	   	    </div>
	  	    	   	    <!-- end 房产抵押 -->
	  	    	   	    <div style="margin-left: 180px" class="bor_det_one clearfix pt30 pb30" style="display:none;">
	  	    	   	    	  <div class="bor_det_oner fl">
                                 <form>
                                      <fieldset>
                                           <div>
                                               <label>编号</label>
                                               <input type="" >
                                           </div>
                                           <div class="mt15">
                                               <label>*用户ID</label>
                                               <input type="">
                                           </div>
                                           <div class="mt15">
                                               <label>*房产</label>
                                               <input type="">
                                           </div>
                                           <div class="mt15">
                                               <label>*房产证图片</label>
                                               <input type="file" value="浏览" >
                                           </div>
                                           <div class="mt15">
                                               <label>*车</label>
                                               <input type="">
                                           </div>
                                           <div class="mt15">
                                               <label>*车辆行驶证照片</label>
                                               <input type="file" value="浏览" >
                                           </div>   	    	  	     	  	  
                                           <div class="mt30">
                                               <label></label>
                                               <span id="save_btn2" class="bor_btn">保存信息</span>
                                           </div>
                                      </fieldset>
                                 </form>
	  	    	   	    	  </div> 
	  	    	   	    </div>
	  	    	   	    <!-- end  -->
	  	    	   	    <div style="margin-left: 180px" class="bor_det_one clearfix pt30 pb30" style="display:none;">
	  	    	   	    	 <div class="bor_det_oner fl">
                                 <form>
                                      <fieldset>
                                           <div>
                                               <label>编号</label>
                                               <input type="" >
                                           </div>
                                           <div class="mt15">
                                               <label>*用户ID</label>
                                               <input type="">
                                           </div>
                                           <div class="mt15">
                                               <label>*真实姓名</label>
                                               <input type="">
                                           </div>
                                           <div class="mt15">
                                               <label>*身份证号</label>
                                               <input type="" >
                                           </div>
                                            <div class="mt15">
                                               <label>*身份证图片</label>
                                               <input type="file" value="浏览" >
                                           </div>  	    	   	    	  	     	  	  
                                           <div class="mt30">
                                               <label></label>
                                               <span id="save_btn3" class="bor_btn" >保存信息</span>
                                           </div>
                                      </fieldset>
                                 </form>
	  	    	   	    	  </div>
	  	    	   	    </div>
	  	    	   	    <!-- end  -->  	    	   	    
	  	    	   </div>
	  	    	   <div align="center">
	  	    	   		<span id="status" class="status_info"></span><br/>
	  	    	   		<button id="isSubmit" style="display:block;" class="shenhe_btn" onclick="doIsSubmit()">提交资料</button>
	  	    	   </div>
	  	    </div>
	  </div>
</div>
</body>
</html>
