<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
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
 <script type="text/javascript" src="statics/js/jquery-1.7.2.min.js" ></script>
 <script type="text/javascript">
		$(function(){     
		var pageNo$ = parseInt($("#pageNo").val());
				$("#firstPage").click(
					function(){
						$("#pageNo").val(1);
						$("#searchForm").submit();
					}
				);
				
				$("#previousPage").click(
					function(){
						$("#pageNo").val(pageNo$ - 1);
						$("#searchForm").submit();
					}
				);
				
				$("#nextPage").click(
					function(){
						$("#pageNo").val(pageNo$ + 1);
						$("#searchForm").submit();
					}
				);
				
				$("#lastPage").click(
					function(){
						var totalPage$ = parseInt($("#totalPage").val());
						$("#pageNo").val(totalPage$);
						$("#searchForm").submit();
					}
				);
				
				$("#querybtn").click(
					function(){
						$("#pageNo").val(1);
						$("#searchForm").submit();
					});
				});
			function numPage(num){
				$("#pageNo").val(num);
				$("#searchForm").submit();
			}	
	
			 function download1(){
        		//var url="http://localhost:8080/maven_p2p/record/doExport";
        		var url="http://localhost:8080/maven_p2p/record/excel";
        			window.open(url);
   			 }
   			 function download2(){
        		//var url="http://localhost:8080/maven_p2p/record/doExport";
        		var url="http://localhost:8080/maven_p2p/invest/excel";
        			window.open(url);
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
<form action="record/toRecord" id="searchForm"> <input type="hidden" id="pageNo" value="${page.pageNo}" name="pageNo">
    		<input type="hidden" id="totalPage" value="${page.totalPage}"></form>	 
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
	  	     	    
	  	
	  <form action="record/doExport">
	  	<input type="button" value="导出交易详情数据" onclick="download1()"/>
	  
	  	
	  	<input type="button" value="导出投资信息数据" onclick="download2()"/>
	  </form>
	  	     	    
	  	     	    
	  <!--<form id="searchForm1" action="record/doExport">-->
	  <!-- 	<input type="hidden" id="totalPage" name="businessQuery.pageNo" value="${page.totalPage}">-->
	  <!-- 	<input name="sbt" type="button" onclick="javascript:doExport()" class="scbtn" value="导出Excel"/>-->
	 	<!-- <a href="record/doExport">导出 -->
	 <!-- </form>  -->	  
	 <form action="">  
	  <table class="tablelist">
    	<thead>
	    <!-- 	<s:form id="searchForm" namespace="/business" method="post" theme="simple">
	    	<ul class="seachform">  
	    	<li>
	    	<tr>-->
		     <!--   <th><a href="record/doExport/${business.userId}">导出Excel</th>-->
		        <!-- <th><input name="sbt" type="button" onclick="javascript:doExport()" class="scbtn" value="导出Excel"/></th> -->
	      <!--    </tr>
	        </li>
	        </ul>
	        
    		</s:form> -->
	    	<tr>
		        <th><input name="" type="checkbox" value="" checked="checked"/></th>
		        <th>编号<i class="sort"></i></th>
		        <th>类型</th>
		        <th>交易时间</th>
		        <th>交易金额</th>
		        <th>用户编号</th>
		        <th>操作</th>
	        </tr>
	        <c:forEach var="business" items="${page.list }" varStatus="type">
	        
	        <tr>
		        <th>
		        <input type="hidden" value="business.userId" name="userId">
		        <input name="" type="checkbox" value="" checked="checked"/></th>
		        <th>${business.id}<i class="sort"></i></th>		      		        		        
		        <c:if test="${business.type == 1 }">
					<th>充值</th>
				</c:if>
				<c:if test="${business.type == 2 }">
					<th>提现</th>
				</c:if>
				<c:if test="${business.type == 3 }">
					<th>投资</th>
				</c:if>
				<c:if test="${business.type == 4 }">
					<th>还款</th>
				</c:if>
		        <th><span>
					<fmt:formatDate value="${business.createTime}" pattern="yyyy-MM-dd"/>
					</span></th>
		        <th>${business.money}</th>
		        <th><a href="${business.userId}">${business.userId}</th>
		        <th><a href="record/delete/${business.userId}">删除
		      <!--  <a href="record/doExport/${business.userId}">导出Excel   --> 
		        </th>
	        </tr>
	        
	        </c:forEach>
	         <tr class="tr_pagenumber">
                <td colspan="100">当前第${page.pageNo }页/共${page.totalPage }页&nbsp;&nbsp;共${page.totalCount }条记录&nbsp;&nbsp;<a id="firstPage" class="badge badge-inverse">首页</a>&nbsp;<a id="previousPage" class="badge badge-inverse">上一页</a>&nbsp;
                	<c:forEach var="num" items="${arr }">
                		<c:choose>
                			<c:when test="${page.pageNo==num }">
                				<a class="badge badge-warning" onclick="numPage('${num}')">${num}</a>&nbsp;
                			</c:when>
                			<c:otherwise>
                				<a class="badge badge-inverse" onclick="numPage('${num}')">${num}</a>&nbsp;
                			</c:otherwise>
                		</c:choose>
                	</c:forEach>
                   &nbsp;<a id="nextPage" class="badge badge-inverse">下一页</a>&nbsp;<a id="lastPage" class="badge badge-inverse">尾页</a></td>
            </tr>
        </thead>
    </table>
    </form>
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
