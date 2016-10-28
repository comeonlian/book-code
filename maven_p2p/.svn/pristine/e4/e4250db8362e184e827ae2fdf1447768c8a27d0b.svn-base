<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'detail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="statics/detail/css/style.css">
	<!-- <link rel="stylesheet/less" type="text/css" href="detail/css/style.less" /> -->
	<script type="text/javascript" src="statics/detail/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="statics/detail/js/all.js"></script>
	<script type="text/javascript">
	function validate(){
		var shuru = $("#shuru").val();
		var yue = $("#yue").html();
		alert(yue+"yue");
		alert(shuru+"shuru");
		if(shuru > yue && shuru!=''){
			$("#tijiao").submit();
		}else{
			document.getElementById("mage").innerHTML = "不能大于余额";
		}
	}
	
	</script>
  </head>
  
  <body>
<!-- header start -->
<div class="zxcf_top_wper">
	<div class="zxcf_top px1000 clearfix">
		 <div class="zxcf_top_l fl">
		    <img src="statics/images/zxcf_phone.png" alt="">
		    400-027-0101(工作时间9:00-17:30)
		    <a href=""><img src="statics/images/zxcf_weixin.png" alt=""></a>
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
<div class="zxcf_detail_wper">
	  <div class="zxcf_detail px1000">
	  	     <div class="detail_intro clearfix">
	  	     	    <div class="detail_intro_l fl">
	  	     	    	  <div class="detail_introl_tit clearfix">
	  	     	    	  	  <div class="det_introl_tit_l fl">
	  	     	    	  	  	   <span>信用</span>${user.borrow.title}
	  	     	    	  	  </div>
	  	     	    	  	  
	  	     	    	  </div>
	  	     	    	  <!-- end tit -->
	  	     	    	  <div class="det_intro_text mr20">
	  	     	    	  	    <ul class="det_intro_tul clearfix">
	  	     	    	  	    	<li>
	  	     	    	  	    		 年华收益<br>
	  	     	    	  	    		 <span style="color:#ff7112;">
	  	     	    	  	    		 	 <strong>${user.borrow.interestRate*100}</strong>% 年华
	  	     	    	  	    		 </span>
	  	     	    	  	    	</li>
	  	     	    	  	    	<li>
	  	     	    	  	    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目目标<br>
	  	     	    	  	    		
	  	     	    	  	    		 <span >
	  	     	    	  	    		 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>${user.borrow.tagetMoney/10000}</strong>万
	  	     	    	  	    		 </span>
	  	     	    	  	    		 </li>
	  	     	    	  	    </ul>
	  	     	    	  	    <!-- end  -->
	  	     	    	  	    <ul class="det_intro_tul2 clearfix">
	  	     	    	  	    	<li>
	  	     	    	  	    		发布日期：<fmt:formatDate value="${user.borrow.createDate}" pattern="yyyy-MM-dd"/>
	  	     	    	  	    	</li>
	  	     	    	  	    	<li>
	  	     	    	  	    	<c:if test="${user.borrow.reimbursement==1}">
                           	   	   还款方式：按月付息，到期还本金
                           	   	   </c:if> <c:if test="${user.borrow.reimbursement==2}">
                           	   	   还款方式：到期付清
                           	   	   </c:if>
	  	     	    	  	    	</li>
	  	     	    	  	    	<li>
	  	     	    	  	    		 还款日期：<fmt:formatDate value="${user.borrow.finishDate}" pattern="yyyy-MM-dd"/>
	  	     	    	  	    	</li>
	  	     	    	  	    </ul>
	  	     	    	  </div>
	  	     	    	  <!-- end text -->
	  	     	    </div>
	  	     	    <div class="detail_intro_r fr">
	  	     	    	  <h2 class="det_intro_h2 clearfix"><span class="fl">投资进度:</span><em class="fr"><fmt:formatNumber type="number" value="${(user.borrow.borrowMoney/user.borrow.tagetMoney*100)}" maxFractionDigits="0"/>%</em></h2>
	  	     	    	  <div class="jklb_press_wper">
                                                	 	  <div class="jklb_press">
                                                	 	  	  
                                                	 	  </div>
                          </div>
                          <!--  -->
                         
                          <p class="det_rzye clearfix"><span class="fl">融资余额</span><em class="fr"><strong id="yue">${user.borrow.tagetMoney-user.borrow.borrowMoney}</strong>元</em></p>
                          <form id="tijiao" action="invest/toInvestment">
                          <p class="det_input">
                           		<input type="hidden" name="id" value="${borrow.id}"/>
                          	  <input type="text" name="money" id="shuru" placeholder="10000" value="">
                            </p>
                         	</form>
                       
                           <p id="mage"></p> 
                          <p class="det_shouyi">每投资1万元收益<strong>${user.borrow.interestRate*10000}元</strong></p>
                          <p><a href="javascript:validate();" class=" pro_btn det_btn">立即投资</a></p>
	  	     	    </div>
	  	     </div>
	  	     <!-- end block1 -->
	  	     <div class="detail_con mt30">
	  	     	   <h2 class="detail_con_tit clearfix">
	  	     	   	   <span class="det_tit_curspan">项目描述</span>
	  	     	   	   <span>信用信息</span>
	  	     	   	   <span>投标记录</span>
	  	     	   	  
	  	     	   </h2>
	  	     	   <div class="det_con_box">
	  	     	   	      <div class="det_con_proone clearfix">
	  	     	   	           <div class="det_l_box fl">
	  	     	   	            <div class="det_proone_l">
	  	     	   	            	   <h2 class="det_proone_tit">借贷人信息</h2>
	  	     	   	            	   <div class="det_clsj ">
	  	     	   	            	   	   <p>姓名：<strong>${user.idCardName}</strong></p>
	  	     	   	            	   <p><span style="margin-right:150px;">身份证：<strong>${user.idCardNum}</strong ></span>信用度：<strong>${user.credits}</strong>
	  	     	   	            	   </p>
	  	     	   	            	   </div>
	  	     	   	            	   <h2 class="det_proone_tit mt30">工作信息</h2>
	  	     	   	            	    <div class="det_clsj ">
	  	     	   	            	         <p>公司名称：<strong>${user.work.company}</strong></p>
	  	     	   	            	         <p><span style="margin-right:150px;">公司地址：<strong>${user.work.city}</strong >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p>
	  	     	   	            	   <p><span style="margin-right:150px;">公司规模：<strong>${user.work.companySize}</strong ></span>公司岗位：<strong>${user.work.companyPost}</strong>
	  	     	   	            	   </p>
	  	     	   	            	    </div>
	  	     	   	            	     <h2 class="det_proone_tit mt30">资产信息</h2>
	  	     	   	            	     <div class="det_clsj">
	  	     	   	            	         <p><span style="margin-right:150px;">房产：<strong>
	  	     	   	            	         <c:if test="${user.assets.houseProperty==0}">
	  	     	   	            	                            无房
	  	     	   	            	         </c:if>
	  	     	   	            	         <c:if test="${user.assets.houseProperty==1}">
	  	     	   	            	                            有房
	  	     	   	            	         </c:if>
	  	     	   	            	         </strong ></span></p>
	  	     	   	            	   <p><span style="margin-right:150px;">车：
	  	     	   	            	   <strong>
	  	     	   	            	  	 <c:if test="${user.assets.car==0}">
	  	     	   	            	                            无车
	  	     	   	            	         </c:if>
	  	     	   	            	         <c:if test="${user.assets.car==1}">
	  	     	   	            	                            有车
	  	     	   	            	         </c:if>
	  	     	   	            	   
	  	     	   	            	   </strong >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>房贷：
	  	     	   	            	   <strong>
	  	     	   	            	   <c:if test="${user.assets.mortgage==0}">
	  	     	   	            	                            无房贷
	  	     	   	            	         </c:if>
	  	     	   	            	         <c:if test="${user.assets.mortgage==1}">
	  	     	   	            	                            有房贷
	  	     	   	            	         </c:if>
	  	     	   	            	   
	  	     	   	            	   </strong>
	  	     	   	            	   </p>
	  	     	   	            	     </div>
	  	     	   	            </div>
	  	     	   	            <!-- end 项目描述 -->
	  	     	   	            <div class="det_proone_l" style="display:none;">
	  	     	   	            	 <h2 class="det_proone_tit">项目评估</h2>
	  	     	   	            	 <!--  -->
	  	     	   	            	 <div class="company-msg-option ppd-company-option2 show">
	  	     	   	            	 <div class="project-assess clearfix">
                                <ul class="project-assess-pt1">
                                    <li>
                                        <div class="small-tit">信用度</div>
                                        <div class="progress-bar">
                                            <div class="progress-bar-in" style="width:${user.usercredit.creditLine}%"></div>
                                        </div>
                                        <div class="progress-bar-txt">${user.usercredit.creditLine}</div>
                                    </li>
                                    <li>
                                        <div class="small-tit">借款次数</div>
                                        <div class="progress-bar">
                                            <div class="progress-bar-in" style="width:100%"></div>
                                        </div>
                                        <div class="progress-bar-txt">${user.usercredit.borrowTime}</div>
                                    </li>
                                    <li>
                                        <div class="small-tit">成功借款</div>
                                        <div class="progress-bar">
                                            <div class="progress-bar-in" style="width:${user.usercredit.borrowSuccessTime}%"></div>
                                        </div>
                                        <div class="progress-bar-txt">${user.usercredit.borrowSuccessTime}</div>
                                    </li>
                                    <li>
                                        <div class="small-tit">成功还款</div>
                                        <div class="progress-bar">
                                            <div class="progress-bar-in" style="width:100%"></div>
                                        </div>
                                        <div class="progress-bar-txt">${user.usercredit.borrowFinishTime}</div>
                                    </li>
                                    <li>
                                        <div class="small-tit">已借款</div>
                                        <div class="progress-bar">
                                            <div class="progress-bar-in" style="width:100%"></div>
                                        </div>
                                        <div class="progress-bar-txt">${user.usercredit.useCreditMoney}</div>
                                    </li>
                                    <li>
                                        <div class="small-tit">待还款</div>
                                        <div class="progress-bar">
                                            <div class="progress-bar-in" style="width:100%"></div>
                                        </div>
                                        <div class="progress-bar-txt">${user.usercredit.payBack}</div>
                                    </li>
                                    <li>
                                        <div class="small-tit">逾期次数</div>
                                        <div class="progress-bar">
                                            <div class="progress-bar-in" style="width:0%"></div>
                                        </div>
                                        <div class="progress-bar-txt">${user.usercredit.overTime}</div>
                                    </li>
                                </ul>
                            </div>
                            </div>
                            <!--  -->
                                   <h2 class="det_proone_tit">项目保障</h2>
                                   <!--  -->

                                    <div class="prject-guarantee">
                                <dl class="dl1 clearfix">
                                    <dt>担保方</dt>
                                    <dd><em class="company-logo-sprite" style="background:url(https://dn-licaifan-upload.qbox.me/uploads/admin/e9fb6a4feac008a08ab67dc3dc2244a2.png)"></em><span><a href="/vouch/detail/8" target="_blank">金达融资担保有限责任公司</a></span></dd>
                                </dl>
                                <dl class="dl2 clearfix">
                                    <dt style="height:200px;line-height:20px;">保障措施</dt>
                                    <dd>
                                        <p>1、担保机构提供不可撤销的连带责任担保，对本息100%保障；</p><p>2、担保机构按规定计提30%的担保费作为风险保障金，由理财范与担保公司共同监管；</p><p>3、借款企业提供足值抵押物；</p><p>4、借款企业实际控制人向债权人提供连带保证；</p><p>5、北京***企业提供连带保证。</p><p><br></p>                                    </dd>
                                </dl>
                            </div>
                                   <!--  -->
                                     <h2 class="det_proone_tit">抵押物信息</h2>
                                     <div class="det_dywxx_ul">借款企业提供一处面积为6308.97平方米、评估价值2086.38万元的房产作抵押。</div>
                                     <!-- end 抵押物信息 -->
                                     <h2 class="det_proone_tit">风险控制措施</h2>
                                     <ul class="det_dywxx_ul">
                                     	 <li>1、借款企业提供足值的抵押物；</li>
											<li>2、借款企业法人提供个人连带保证；</li>
											<li>3、北京***企业提供连带保证；</li>
											<li>4、担保公司及理财范平台将执行严格的贷后管理制度。</li>
                                     </ul>
                                     <!-- end 风险控制 -->
                                      <h2 class="det_proone_tit">担保机构意见</h2>
                                     <div class="det_dywxx_ul">借款企业提供一处面积为6308.97平方米、评估价值2086.38万元的房产作抵押。</div>
                                     <!-- end 抵押物信息 -->
	  	     	   	            </div>
	  	     	   	           
	  	     	   	            <div class="det_proone_l" style="display:none;">
	  	     	   	            	  <P class="det_tbjl_tit">
	  	     	   	            	     <span class="mr30"><img src="statics/images/det_tbjl_pic01.png" alt=""> 
	  	     	   	            	     目标总金额：￥${user.borrow.tagetMoney}</span>
	  	     	   	            	     <span><img src="statics/images/det_tbjl_pic02.png" alt=""> 
	  	     	   	            	     剩余金额：￥${user.borrow.tagetMoney-user.borrow.borrowMoney}</span>
	  	     	   	            	  </P>
	  	     	   	            	  <table class="det_table">
	  	     	   	            	  	  <tr class="det_table_head" style="height:40px;">
	  	     	   	            	  	  	  <td>投资用户</td>
	  	     	   	            	  	  	  <td>投资金额</td>
	  	     	   	            	  	  	  <td class="det_w1160">投资时间</td>
	  	     	   	            	  	  </tr>
	  	     	   	            	  	  <c:forEach items="${investreport}" var="investreport">
	  	     	   	            	  	  <tr>
	  	     	   	            	  	  	  <td>${investreport.userId}</td>
	  	     	   	            	  	  	  <td>${investreport.money}</td>
	  	     	   	            	  	  	  <td><fmt:formatDate value="${investreport.createTime}" pattern="yyyy-MM-dd"/></td>
	  	     	   	            	  	  </tr>
	  	     	   	            	  	  </c:forEach>
	  	     	   	            	  </table>
	  	     	   	            </div>
	  	     	   	            <!-- end tbjl -->
	  	     	   	           </div> 
	  	     	   	            <!-- end l -->

	  	     	   	      	    <div class="det_proone_r fl">
	  	     	   	      	    	   <h3>项目资质审核</h3>
	  	     	   	      	    	   <ul class="det_proone_rul">
	  	     	   	      	    	   	   <li class="clearfix">
	  	     	   	      	    	   	   	  <span class="fl mr15">
	  	     	   	      	    	   	   	  	  <img src="statics/images/det_dbh01.png" alt=""><br><br>
	  	     	   	      	    	   	   	  	  担保函
	  	     	   	      	    	   	   	  	  
	  	     	   	      	    	   	   	  </span>
	  	     	   	      	    	   	   	   <span class="fl">
	  	     	   	      	    	   	   	  	  <img src="statics/images/det_dbh02.png" alt=""><br><br>
	  	     	   	      	    	   	   	  	  身份证
	  	     	   	      	    	   	   	  	 
	  	     	   	      	    	   	   	  </span>
	  	     	   	      	    	   	   </li>
	  	     	   	      	    	   	   <li class="clearfix">
	  	     	   	      	    	   	   	  <span class="fl mr15">
	  	     	   	      	    	   	   	  	  <img src="statics/images/det_dbh01.png" alt=""><br><br>
	  	     	   	      	    	   	   	  	  担保函
	  	     	   	      	    	   	   	  	  
	  	     	   	      	    	   	   	  </span>
	  	     	   	      	    	   	   	   <span class="fl">
	  	     	   	      	    	   	   	  	  <img src="statics/images/det_dbh02.png" alt=""><br><br>
	  	     	   	      	    	   	   	  	  身份证
	  	     	   	      	    	   	   	  	 
	  	     	   	      	    	   	   	  </span>
	  	     	   	      	    	   	   </li>
	  	     	   	      	    	   </ul>
	  	     	   	      	    </div>
	  	     	   	      	    <!-- end r -->
	  	     	   	      </div>
	  	     	   	      <!-- end 项目描述 -->
	  	     	   	      
	  	     	   </div>
	  	     </div>
	  	     <!-- end block2 -->
	  </div>
</div>
<!-- end con -->
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
