<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<link rel="stylesheet" type="text/css" href="statics/css/calendar.css">
<link rel="stylesheet" type="text/css" href="statics/css/style.css">
<!-- <link rel="stylesheet/less" type="text/css" href="css/style.less" /> -->
<script type="text/javascript" src="statics/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="statics/js/all.js"></script>
<script type="text/javascript" src="statics/js/calendar.js"></script>
<script type="text/javascript">
$(function(){
	var money = $("#money");
	var car = $("#car");
	car.blur(function(){
		var bankno =car.val();
		var lastNum = bankno.substr(bankno.length - 1, 1);//取出最后一位（与luhm进行比较）
			var first15Num = bankno.substr(0, bankno.length - 1);//前15或18位
			var newArr = new Array();
			for ( var i = first15Num.length - 1; i > -1; i--) { //前15或18位倒序存进数组
				newArr.push(first15Num.substr(i, 1));
			}
			var arrJiShu = new Array(); //奇数位*2的积 <9
			var arrJiShu2 = new Array(); //奇数位*2的积 >9
			var arrOuShu = new Array(); //偶数位数组
			for ( var j = 0; j < newArr.length; j++) {
				if ((j + 1) % 2 == 1) {//奇数位
					if (parseInt(newArr[j]) * 2 < 9)
						arrJiShu.push(parseInt(newArr[j]) * 2);
					else
						arrJiShu2.push(parseInt(newArr[j]) * 2);
				} else
					//偶数位
					arrOuShu.push(newArr[j]);
			}
			var jishu_child1 = new Array();//奇数位*2 >9 的分割之后的数组个位数
			var jishu_child2 = new Array();//奇数位*2 >9 的分割之后的数组十位数
			for ( var h = 0; h < arrJiShu2.length; h++) {
				jishu_child1.push(parseInt(arrJiShu2[h]) % 10);
				jishu_child2.push(parseInt(arrJiShu2[h]) / 10);
			}
			var sumJiShu = 0; //奇数位*2 < 9 的数组之和
    	var sumOuShu=0; //偶数位数组之和
    	var sumJiShuChild1=0; //奇数位*2 >9 的分割之后的数组个位数之和
			var sumJiShuChild2 = 0; //奇数位*2 >9 的分割之后的数组十位数之和
			var sumTotal = 0;
			for ( var m = 0; m < arrJiShu.length; m++) {
				sumJiShu = sumJiShu + parseInt(arrJiShu[m]);
			}
			for ( var n = 0; n < arrOuShu.length; n++) {
				sumOuShu = sumOuShu + parseInt(arrOuShu[n]);
			}

			for ( var p = 0; p < jishu_child1.length; p++) {
				sumJiShuChild1 = sumJiShuChild1 + parseInt(jishu_child1[p]);
				sumJiShuChild2 = sumJiShuChild2 + parseInt(jishu_child2[p]);
			}
			//计算总和
			sumTotal = parseInt(sumJiShu) + parseInt(sumOuShu)
					+ parseInt(sumJiShuChild1) + parseInt(sumJiShuChild2);

			//计算Luhm值
			var k = parseInt(sumTotal) % 10 == 0 ? 10 : parseInt(sumTotal) % 10;
			var luhm = 10 - k;
			if (lastNum == luhm && bankno != '') {
				document.getElementById("ok").innerHTML = "ok";
				return true;
			} else {
				document.getElementById("ok").innerHTML = "请输入正确的卡号";
				return false;
			}
	});
	
	money.blur(function(){
		var twomoney = money.val();
		var guding = $("#xinyong").html();
		if(twomoney==null || twomoney ==''){
			document.getElementById("meiguanx").innerHTML = "不能为空！";
			}
			else if(twomoney>guding){
			document.getElementById("meiguanx").innerHTML = "不能大于信用额度！";
			}else{
			document.getElementById("meiguanx").innerHTML = "ok";
		}
	});
	function validate(){
	alert(1);
		money.blur();
		car.blur();
		rnewPassword.blur();
		if(money.attr("status") == "true" && car.attr("status") == "true"){
			if(confirm("确定要修改密码吗？")){
				$("#tijiao").submit();
			}
		}
	};
});
</script>
</head>

<body>
	<!-- header start -->
	<div class="zxcf_top_wper">
		<div class="zxcf_top px1000 clearfix">
			<div class="zxcf_top_l fl">
				<img src="statics/images/zxcf_phone.png" alt="">
				400-027-0101(工作时间9:00-17:30) <a href=""><img
					src="statics/statics/images/zxcf_weixin.png" alt=""> </a> <a
					href=""><img src="statics/images/zxcf_sina.png" alt=""> </a>
				<a href=""><img src="statics/images/zxcf_qq.png" alt=""> </a>
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
			<div class="zxcf_nav_l fl" >
				<img src="statics/images/zxcf_logo.png" alt="">
			</div>
			<div style="margin-top: 20px" class="fr" >
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
	<div class="bor_banner01"></div>
	<!-- end banner -->
	<div class="bor_con_wper">
		<div class="bor_con px1000">
			<div class="bor_detail">
				<h2 class="bor_detail_tit">
					<span class="bor_decurspan" style="margin-left: 220px">信用贷款</span>
				</h2>
				<div class="bor_detail_box">
					<div class="bor_det_one clearfix pt30 pb30">
						<div class="bor_det_onel fl">
							<p class="bor_p1">中兴财富平台的借款功能旨在帮助借款用户以 低成本获得借款。用户在需要资金时，可以将
								自有房产和车产作为抵押物，小油菜线下审核 通过后，根据抵押物的价值融资。</p>
							<p class="bor_p2">中兴财富平台的借款功能旨在帮助借款用户以 低成本获得借款。用户在需要资金时，可以将
								自有房产和车产作为抵押物，小油菜线下审核 通过后，根据抵押物的价值融资。</p>
							<h3 class="bor_onel_tit">
								<span>申请条件</span>
							</h3>
							<ul class="bor_onel_ul">
								<li><img src="statics/images/bor_pic01.png" alt="">年满18周岁以上的公民
								</li>
								<li><img src="statics/images/bor_pic02.png" alt="">需要北京房产或车产抵押
								</li>
								<li><img src="statics/images/bor_pic03.png" alt="">个人或企业银行征信记录良好
								</li>
								<li><img src="statics/images/bor_pic04.png" alt="">
									无现有诉讼记录</li>

							</ul>
							<h3 class="bor_onel_tit">
								<span>提交资料</span>
							</h3>
							<ul class="bor_onel_ul">
								<li>&nbsp;<img src="statics/images/bor_pic05.png" alt="">省份证
								</li>
								<li><img src="statics/images/bor_pic06.png" alt="">申请资料
								</li>
								<li><img src="statics/images/bor_pic07.png" alt="">其他
								</li>


							</ul>
						</div>
						<!-- end l -->
						<div class="bor_det_oner fl">
							<form id="tijiao" action="borrow/doBorrow">
								<fieldset>
									<div>
										<label>申请人</label> <strong><input name="userId" type="hidden" value="${user.id}"/>${user.idCardName}</strong><br>
									</div>
									<div class="mt15 guarmethod clearfix">
										<label class="guarmethod_l fl">信用额度</label>
										<div class="fl">
											<span id="xinyong"><strong><input name="userId" readonly="readonly" value="${user.creditsMoney}"/></strong></span><br>
										</div>
									</div>
									<div class="mt15">
										<label>手机号码</label> <strong>${user.phone}</strong>
									</div>
									<div class="mt15">
										<label>*标题</label> <input type="text" name="title" id="money" class="bor_inputbg01">
										<span id="meiguanx"></span>
									</div>
									<div class="mt15">
										<label>*借款金额</label> <input type="text" name="borrowMoney" id="money" class="bor_inputbg01">
										<span id="meiguanx"></span>
									</div>
									<div class="mt15">
										<label>*发布日期</label> <input name="createDate" value="" type="text" size="10" onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='00000000')this.value=''"/>
									</div>
									<div class="mt15">
										<label>*还款方式</label>
										<select name="reimbursement"> 
											<option value="1">按月</option>
											<option value="2">到期</option>
										</select>
										<span id="meiguanx"></span>
									</div>
									<div class="mt15">
										<label>*转入卡号</label> <input type="text" name="bankCardId" id="car" 
											class="bor_inputbg02"> <span id="ok"></span>
									</div>
									<div class="mt30">
										<label></label> <a href="javascript:validate();"
											class="bor_btn">提交材料</a>
									</div>
								</fieldset>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
