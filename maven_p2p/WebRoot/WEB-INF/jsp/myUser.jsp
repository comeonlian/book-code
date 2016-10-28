<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'welcome.jsp' starting page</title>
    
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
  </head>
  
 <body>
<!-- header start -->
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
		 <!-- <div class="zxcf_nav_r fr"> -->
		 	<!-- <span><a href="">我的账户</a> -->
		 	<!-- <img src="statics/images/zxcf_icon01.png" alt=""></span>
		 	<ul class="zxcf_perinfo" style="display:none;">
		 		<li><a href="check/toCheck">个人资料审查</a></li>
		 		<li><a href="record/toRecord">交易记录</a></li>
		 	</ul> -->
		 	 <div style="margin-top: 20px"  class="fr">
		 		<img src="statics/images/zxcf_perinfo.png" alt="">
		 	<span>
		    <a style="color: green;" href="user/index">我的账户</a></span>
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
<div class="invest_con_wper">
	  <div class="invest_con px1000">
	  	     <div class="product_choose">
	  	     	    <h2 class="product_tit clearfix">
	  	     	       <em class="proall fl">个人信息</em>
	  	     	       <div class="clearfix fl">
	  	     	       <span class="product_curspan"><img src="statics/images/invest_pic01.png"> 账户详情</span>
	  	     	       <span><img src="statics/images/invest_pic02.png"> 资产收益明细</span>
	  	     	       <span><img src="statics/images/invest_pic02.png"> 借款记录</span>
	  	     	       <span><img src="statics/images/invest_pic03.png"> 交易记录</span>
	  	     	       </div>
	  	     	      
	  	     	    </h2>
	  	     	    <div class="product_box">
	  	     	    	  <div class="product_list">
	  	     	    	  	     <div class="invest_prochoose">
					  	       	      <!-- <p><span>用户名：45918187</span><a>上次登录时间：</a><a class="inpro_cura">小于1个月</a></p>
					  	       	      <p><span  style="margin-left: 255px; margin-right: 17px">总：</span><a class="inpro_cura">不限</a></p>
					  	       	      <p><span style="margin-left: 240px;margin-right: 17px">昨日收益：</span><a class="inpro_cura">不限</a></p>
					  	       	      <p><span style="margin-left: 240px;margin-right: 17px">累计收益：</span><a class="inpro_cura">不限</a></p>
					  	       	      <p><span>审核详情：</span></p>
					  	       	      <p><span style="margin-left: 240px;margin-right: 17px">资产认证：</span><a class="inpro_cura">审核通过</a></p>
					  	       	      <p><span style="margin-left: 240px;margin-right: 17px">工作认证：</span><a class="inpro_cura">审核通过</a></p>
					  	       	      <p><span style="margin-left: 240px;margin-right: 17px">收入认证：</span><a class="inpro_cura">审核通过</a></p>
					  	       	      <p><span style="margin-left: 240px;margin-right: 17px">信用报告：</span><a class="inpro_cura">审核通过</a></p> -->
	  	                    		<table style="font-size: 14px; border-collapse:separate;border-spacing:10px;width: 100%">
	  	                    			<tr style="margin-left: 100px">
	  	                    				<td colspan="4" align="center" style="font-size: 16px;background-color: gray; height: 35px">个人基本信息</td>
	  	                    			</tr>
	  	                    			<tr>
	  	                    				<td style="width: 70px"></td>
	  	                    				<td style="width: 200px"></td>
	  	                    				<td>用户名：</td>
	  	                    				<td><a class="inpro_cura">459181870@qq.com</a></td>
	  	                    			</tr>
	  	                    			<tr>
	  	                    				<td></td>
	  	                    				<td></td>
	  	                    				<td>上次登录时间：</td>
	  	                    				<td><a class="inpro_cura">小于1个月</a></td>
	  	                    			</tr>
	  	                    			<tr>
	  	                    				<td></td>
	  	                    				<td></td>
	  	                    				<td>姓名：</td>
	  	                    				<td><a class="inpro_cura">梁文颉</a></td>
	  	                    			</tr>
	  	                    			<tr>
	  	                    				<td></td>
	  	                    				<td></td>
	  	                    				<td>手机：</td>
	  	                    				<td><a class="inpro_cura">15818608798</a></td>
	  	                    			</tr>
	  	                    			<tr>
	  	                    				<td></td>
	  	                    				<td></td>
	  	                    				<td>邮箱：</td>
	  	                    				<td><a class="inpro_cura">459181870@qq.com</a></td>
	  	                    			</tr>
	  	                    			<tr>
	  	                    				<td></td>
	  	                    				<td></td>
	  	                    				<td>信用度：</td>
	  	                    				<td><a class="inpro_cura">100</a></td>
	  	                    			</tr>
	  	                    			<tr>
	  	                    				<td></td>
	  	                    				<td></td>
	  	                    				<td>账户金额：</td>
	  	                    				<td><a class="inpro_cura">1000000000</a></td>
	  	                    			</tr>
	  	                    			<tr>
	  	                    				<td></td>
	  	                    				<td></td>
	  	                    				<td>信用额度：</td>
	  	                    				<td><a class="inpro_cura">1000000000</a></td>
	  	                    			</tr>
	  	                    			<tr>
	  	                    				<td></td>
	  	                    				<td></td>
	  	                    				<td>信用额度：</td>
	  	                    				<td><a class="inpro_cura">1000000000</a></td>
	  	                    			</tr>
	  	                    			<tr style="margin-left: 100px">
	  	                    				<td colspan="4" align="center" style="font-size: 16px;background-color: gray; height: 35px">审核进度</td>
	  	                    			</tr>
	  	                    			<tr>
	  	                    				<td></td>
	  	                    				<td></td>
	  	                    				<td>资产认证：</td>
	  	                    				<td><a class="inpro_cura">未审核</a></td>
	  	                    			</tr>
	  	                    			<tr>
	  	                    				<td></td>
	  	                    				<td></td>
	  	                    				<td>身份认证：</td>
	  	                    				<td><a class="inpro_cura">未审核</a></td>
	  	                    			</tr>
	  	                    			<tr>
	  	                    				<td></td>
	  	                    				<td></td>
	  	                    				<td>收入认证：</td>
	  	                    				<td><a class="inpro_cura">未审核</a></td>
	  	                    			</tr>
	  	                    			<tr>
	  	                    				<td></td>
	  	                    				<td></td>
	  	                    				<td>工作认证：</td>
	  	                    				<td><a class="inpro_cura">未审核</a></td>
	  	                    			</tr>
	  	                    		</table>
	  	                    	</div>
	  	     	    	  </div>
	  	     	    	  <!--  -->
	  	     	    	  <div class="product_list" style="display:none;">
	  	     	    	  	  <div class="invest_prochoose">
					  	       	      <p><span>用户：459181870@qq.com</span><a>上次登录时间：</a><a class="inpro_cura">小于1个月</a></p>
					  	       	      <p><span>项目状态：</span><a href="#" class="inpro_cura">不限</a><a href="#">所有借款</a><a href="#">正在招标的借款</a><a href="#">已成功借款</a><a href="#">已完成借款</a></p>
					  	       	      <p><span>项目收益：</span><a href="#" class="inpro_cura">不限</a><a href="#">10%以下</a><a href="#">10%-15%</a><a href="#">15%-20%</a><a href="#">20%-30%</a></p>
					  	       	      <p><span>项目类型：</span><a href="#" class="inpro_cura">不限</a><a href="#">信用标</a><a href="#">抵押标</a></p>
	  	                    	</div>
	  	     	    	  </div>
	  	     	    	  <!--  -->
	  	     	    	  <div class="product_list" style="display:none;">
	  	     	    	  	  <div class="invest_prochoose">
					  	       	      <p><span>用户：459181870@qq.com</span><a>上次登录时间：</a><a class="inpro_cura">小于1个月</a></p>
					  	       	      <p><span>总资产：</span><a href="#" class="inpro_cura">不限</a></p>
					  	       	      <p><span>昨日收益：</span><a href="#" class="inpro_cura">不限</a></p>
					  	       	      <p><span>累计收益：</span><a href="#" class="inpro_cura">不限</a></p>
	  	                    	</div>
	  	     	    	  </div>
	  	     	    	  <!--  -->
	  	     	    	  <!--  -->
	  	     	    	  <div class="product_list" style="display:none;">
	  	     	    	  	  <div class="invest_prochoose">
					  	       	      <p><span>用户：459181870@qq.com</span><a>上次登录时间：</a><a class="inpro_cura">小于1个月</a></p>
					  	       	      <p><span>总资产：</span><a href="#" class="inpro_cura">不限</a></p>
					  	       	      <p><span>昨日收益：</span><a href="#" class="inpro_cura">不限</a></p>
					  	       	      <p><span>累计收益：</span><a href="#" class="inpro_cura">不限</a></p>
	  	                    	</div>
	  	     	    	  </div>
	  	     	    	  <!--  -->

	  	     	    </div>
	  	     </div>
	  	     <!-- end block -->
			</div>
	  </div>
<!-- footer start -->
<div class="zscf_aboutus_wper">
	  <div class="zscf_aboutus px1000 clearfix">
	  	    <div class="zscf_aboutus_l fl">
	  	    	   <ul class="zscf_aboutus_lul clearfix">
	  	    	   	  <li class="pt10"><a href=""><img src="statics/images/app.png" alt=""></a>
	  	    	   	  </li>
	  	    	   	  <li>
	  	    	   	  <p class="pb20">服务热线</p>
	  	    	   	  <strong>400-027-0101</strong>
	  	    	   	  </li>
	  	    	   	  <li>
	  	    	   	  	  <p class="pb10 linkpic">
	  	    	   	  	     <a href=""><img src="statics/images/ft_sina.png" alt=""></a>
	  	    	   	  	     <a href=""><img src="statics/images/ft_weixin.png" alt=""></a>
	  	    	   	  	     <a href=""><img src="statics/images/ft_erji.png" alt=""></a>
	  	    	   	  	  </p>
	  	    	   	  	  <p><a href="">kefu@zhongxincaifu.com</a></p>
	  	    	   	  </li>
	  	    	   </ul>
	  	    </div>
	  	    <!-- end left -->
	  	    <div class="zscf_aboutus_r fl clearfix">
	  	    	 <a href="#" class="fl ft_ewm"><img src="statics/images/ft_erweima.png" alt=""></a>
	  	    	 <ul class="fl clearfix">
	  	    	 	<li><a href="">联系我们</a></li>
	  	    	 	<li><a href="">我要融资</a></li>
	  	    	 	<li><a href="">帮助中心</a></li>
	  	    	 	<li><a href="">友情链接</a></li>
	  	    	 	<li><a href="">招贤纳士</a></li>
	  	    	 	<li><a href="">收益计算器</a></li>
	  	    	 </ul>
	  	    </div>
	  	    <!-- end right -->

	  </div>
</div>

<div class="zscf_bottom_wper">
	<div class="zscf_bottom px1000 clearfix">
		  <p class="fl">© 2014 zhongxincaifu &nbsp;  &nbsp;&nbsp;   中兴财富金融信息服务股份有限公司 &nbsp;&nbsp;&nbsp;    鄂ICP备14017254号-1</p>
		  <p class="fr">
		    <a href=""><img src="statics/images/360.png" alt=""></a>
		    <a href=""><img src="statics/images/kexin.png" alt=""></a>
		    <a href=""><img src="statics/images/norton.png" alt=""></a>
		  </p>
	</div>
</div>
<!-- footer end -->
</body>
</html>
