<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'invest.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="styles.css">

<link rel="stylesheet" type="text/css" href="statics/css/style.css">
<link rel="stylesheet/less" type="text/css"
	href="statics/css/style.less" />
<script type="text/javascript" src="statics/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="statics/js/all.js"></script>
<script type="text/javascript">
	function frist() {
		if ($("#pageNo").val() == 1) {
			alert("已经是首页");
		} else {
			$("#pageNo").val(1);
			$("#page").submit();
		}
	};
	function last() {
		if ($("#pageNo").val() == $("#pageCount").val()) {
			alert("已经是尾页");
		} else {
			$("#pageNo").val($("#pageCount").val());
			$("#page").submit();
		}
	};
	function up() {
		if ($("#pageNo").val() == 1) {
			alert("已经是首页");
		} else {
			$("#pageNo").val(parseInt($("#pageNo").val()) - 1);
			$("#page").submit();
		}
	};
	function down() {
		if ($("#pageNo").val() == $("#pageCount").val()) {
			alert("已经是尾页");
		} else {
			$("#pageNo").val(parseInt($("#pageNo").val()) + 1);
			$("#page").submit();
		}
		;
	};
</script>
</head>

<body>
	<!-- header start -->
	<div class="zxcf_top_wper">
		<div class="zxcf_top px1000 clearfix">
			<div class="zxcf_top_l fl">
				<img src="statics/images/zxcf_phone.png" alt="">
				400-027-0101(工作时间9:00-17:30) <a href=""><img
					src="statics/images/zxcf_weixin.png" alt=""> </a> <a href=""><img
					src="statics/images/zxcf_sina.png" alt=""> </a> <a href=""><img
					src="statics/images/zxcf_qq.png" alt=""> </a>
			</div>
			<div class="zxcf_top_r fr">
				<a href="" class="curspan">立即登录</a> <span>|</span> <a href="">免费注册</a>
				<span>|</span> <a href="">常见问题</a>
			</div>
		</div>
	</div>
	<!-- end top -->
	<div class="zxcf_nav_wper">
		<div class="zxcf_nav clearfix px1000">
			<div class="zxcf_nav_l fl">
				<img src="statics/images/zxcf_logo.png" alt="">
			</div>
			<div style="margin-top: 20px" class="fr">
				<img src="statics/images/zxcf_perinfo.png" alt=""> <span>
					<a style="color: green;" href="user/toMyUser">我的账户</a> </span>
			</div>
		</div>
	</div>
	<div class="zxcf_menu_wper">
		<div class="zxcf_menu px1000">
			<a href="" class="zm_cura">首页</a> <a href="invest/toInvest">我要投资</a>
			<a href="borrow/toBorrow">我要借款</a> <a href="check/toCheck">个人资料审查</a>
			<a href="record/toRecord">交易记录</a>
		</div>
	</div>
	<!-- end  -->
	<div class="invest_con_wper">
		<div class="invest_con px1000">
			<div class="product_choose">
				<h2 class="product_tit clearfix">
					<em class="proall fl">全部理财产品</em>
					<div class="clearfix fl">
						<span class="product_curspan"><img
							src="statics/images/invest_pic01.png"> 新手体验标</span> <span><img
							src="statics/images/invest_pic02.png"> 项目列表</span>
					</div>
				</h2>
				<div class="product_box">
					<div class="product_list">
						<div class="invest_prochoose">
							<p>
								<span>项目期限：</span><a class="inpro_cura">不限</a><a
									href="invest/30/time">小于1个月</a><a href="invest/90/time">1-3个月</a><a
									href="invest/150/time">4-6个月</a><a href="invest/220/time">7-12个月</a>
							</p>
							<p>
								<span>项目状态：</span><a class="inpro_cura">不限</a><a
									href="invest/1/borrowType">所有借款</a><a
									href="invest/2/borrowType">正在招标的借款</a><a
									href="invest/3/borrowType">已成功借款</a><a
									href="invest/4/borrowType">已完成借款</a>
							</p>
							<p>
								<span>项目收益：</span><a class="inpro_cura">不限</a><a
									href="invest/1/interestRate">10%以下</a><a
									href="invest/2/interestRate">10%-15%</a><a
									href="invest/3/interestRate">15%-20%</a><a
									href="invest/4/interestRate">20%-30%</a>
							</p>
						</div>
					</div>
					<!--  -->
					<div class="product_list" style="display:none;"></div>
					<!--  -->
					<div class="product_list" style="display:none;"></div>
					<!--  -->

				</div>
			</div>
			
			<c:forEach items="${borrow}" var="borrow">
				<div class="product_list mt20">
					<div class="prolist_one prolist_one_bl01 mt20">
						<h2 class="prolist_one_tit">
							<span>信用</span>${borrow.title}
						</h2>
						<ul class="prolist_one_ul clearfix">
							<li>年华收益：<strong>${borrow.interestRate*100}%</strong><br> <c:if
									test="${borrow.borrowType==1}">
                           	   	   还款方式：按月付息，到期还本金
                           	   	   </c:if> <c:if test="${borrow.borrowType==2}">
                           	   	   还款方式：到期付清
                           	   	   </c:if></li>
							<li>募集金额：<i>${borrow.borrowMoney}</i><br></li>
							<li class="prolist_press">目标金额 ：<strong>${borrow.tagetMoney}</strong>
								元 <br> 融资进度：<span
								class="ui-progressbar-mid ui-progressbar-mid-<fmt:formatNumber type="number" value="${(borrow.borrowMoney/borrow.tagetMoney*100)}" maxFractionDigits="0"/>">${borrow.borrowMoney/borrow.tagetMoney*100}%</span>
							</li>
							<li class="prolist_btn"><a
								href="invest/${borrow.id}/toDetail" class="pro_btn">立即投资</a>
							</li>
						</ul>
					</div>
				</div>
				</c:forEach>
			<div align="center">
				<form id="page" action="invest/toInvest" method="post">
					<input type="hidden" id="pageNo" name="pageNo"
						value="${basePage.pageNo}" /> <input type="hidden" id="pageCount"
						name="totalPage" value="${basePage.totalPage}" /> <input
						type="hidden" name="totalCount" value="${basePage.totalCount}" />
					<a href="javascript:frist();">首页</a> <a href="javascript:up();">上一页</a>
					<a href="javascript:down();">下一页</a> <a href="javascript:last();">尾页</a>
					&nbsp;&nbsp; &nbsp;&nbsp;第${basePage.pageNo} 页/共
					${basePage.totalPage} 页&nbsp;&nbsp;共${basePage.totalCount}条记录
				</form>
			</div>
			<!-- pagelink end -->
		</div>
	</div>
	<!-- footer start -->
	<div class="zscf_aboutus_wper">
		<div class="zscf_aboutus px1000 clearfix">
			<div class="zscf_aboutus_l fl">
				<ul class="zscf_aboutus_lul clearfix">
					<li class="pt10"><a href=""><img
							src="statics/images/app.png" alt=""> </a></li>
					<li>
						<p class="pb20">服务热线</p> <strong>400-027-0101</strong></li>
					<li>
						<p class="pb10 linkpic">
							<a href=""><img src="statics/images/ft_sina.png" alt="">
							</a> <a href=""><img src="statics/images/ft_weixin.png" alt="">
							</a> <a href=""><img src="statics/images/ft_erji.png" alt="">
							</a>
						</p>
						<p>
							<a href="">kefu@zhongxincaifu.com</a>
						</p></li>
				</ul>
			</div>
			<!-- end left -->
			<div class="zscf_aboutus_r fl clearfix">
				<a href="#" class="fl ft_ewm"><img
					src="statics/images/ft_erweima.png" alt=""> </a>
				<ul class="fl clearfix">
					<li><a href="">联系我们</a>
					</li>
					<li><a href="">我要融资</a>
					</li>
					<li><a href="">帮助中心</a>
					</li>
					<li><a href="">友情链接</a>
					</li>
					<li><a href="">招贤纳士</a>
					</li>
					<li><a href="">收益计算器</a>
					</li>
				</ul>
			</div>
			<!-- end right -->

		</div>
	</div>

	<div class="zscf_bottom_wper">
		<div class="zscf_bottom px1000 clearfix">
			<p class="fl">© 2014 zhongxincaifu &nbsp; &nbsp;&nbsp;
				中兴财富金融信息服务股份有限公司 &nbsp;&nbsp;&nbsp; 鄂ICP备14017254号-1</p>
			<p class="fr">
				<a href=""><img src="statics/images/360.png" alt=""> </a> <a
					href=""><img src="statics/images/kexin.png" alt=""> </a> <a
					href=""><img src="statics/images/norton.png" alt=""> </a>
			</p>
		</div>
	</div>
	<!-- footer end -->
</body>
</html>
