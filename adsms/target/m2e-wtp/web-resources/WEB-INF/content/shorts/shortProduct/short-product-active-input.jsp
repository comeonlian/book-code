<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${id==null?'新增激活信息':'修改激活信息' }</title>
	<%@ include file="/common/head.jsp" %>
	<link rel='STYLESHEET' type='text/css' href='${tree}/common/style.css' />
	<link rel="STYLESHEET" type="text/css" href="${tree}/dhtmlxtree.css" />
	<script type="text/javascript" src="${tree}/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${tree}/dhtmlxtree.js"></script>
	
	<script src="${ctx}/js/jQuery/validate/jquery.validate.js" type="text/javascript"></script>
	<link href="${ctx}/js/jQuery/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jQuery/timerange.js"></script>
	<script>

		$(document).ready(function() {
			// 模拟划屏
			var tr_count_drag = $('#active_drag').children().length - 4;
			//dynamic Table
			$('#table_tr_add_drag').click(function(){
				//1:创建一个tr
				var tag = tr_count_drag++;
                // 动态tr
                var tr = '<tr name="delTr">'+
                '<td><select name="activityWap['+tag+'].type" id="activityWap['+tag+'].type" style="width: 110px"><option value="1">直接参数</option><option value="2">方法</option><option value="3">截取</option></select></td>'+
                '<td><input type="text" id="activityWap['+tag+'].keyw" class="small" name="activityWap['+tag+'].keyw" maxlength="100" /></td>'+
                '<td><input type="text" id="activityWap['+tag+'].valuew" name="activityWap['+tag+'].valuew" class="small" maxlength="100"/></td>'+
                '<td><input type="text" id="activityWap['+tag+'].beginw" name="activityWap['+tag+'].beginw" class="small" maxlength="100"/></td>'+
                '<td><input type="text" id="activityWap['+tag+'].endw" name="activityWap['+tag+'].endw" class="small" maxlength="100"/></td>'+
                '<td><input type="text" id="activityWap['+tag+'].number" name="activityWap['+tag+'].number" class="small" maxlength="100"/></td>'+
                '<td><input type="text" id="activityWap['+tag+'].time" name="activityWap['+tag+'].time" class="small" maxlength="100"/></td>'+
                '<td><input type="button" value="删除" name="table_delete_drag"/></td>'+
                '</tr>';
				var newTr = $(tr);
				//2:把tr添加到table里面
				$('#active_drag').append(newTr);
				
				$('*[name="table_delete_drag"]').click(function(event){
					$.each($('*[name="table_delete_drag"]'),function(index,v){
						if(v==event.target){
							var delTr = $('*[name="delTr"]:eq('+index+')');
							delTr.remove();
						}
					});
				});
			});
			
			
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
				}
				
			});
		});
		
		function deleteItem(obj, tid, itemtype) {
			var delTr = $(obj).parent().parent();
			$.ajax({
			   type: "POST",
			   url: "short-product-active!deleteItem.action?tid="+tid+"&itemType="+itemtype,
			   cache: false,
			   success: function(msg){
			   }
			});
			delTr.remove();
		}
		
	</script>
	

</head>
<body>
<div class="currloca">
  <p>${auth.fullMenu}${id==null?'&nbsp;»&nbsp;<span>新增激活信息</span>':'&nbsp;»&nbsp;<span>修改激活信息</span>' }</p>
  <div class="sitemap">
  	<span id="add2custom"></span>
    <span id="showMap"><img onclick="showMap();return false;" id="sMap" class="pointer" width="64" height="18" title="后台导航" src="${ctx}/images/map.gif"/></span>
  </div>
</div>

<div class="container">
  <!-- 内容区域 -->
  <div class="itemtitle"><h2>${id==null?'新增激活信息':'修改激活信息' }</h2></div>
    
      <!-- 附加信息-->
  <div id="message" class="message">
    <span style="font-weight: bold;font-size: 14px;"><!-- 提示：资源列表... --></span>
  </div>
  
  <form id="inputForm" action="short-product-active!save.action" method="post">
	  <!-- 列表区域-->
	  <div id="content" class="content input">
	    <div id="indiv" style="width:100%;OVERFLOW-X:auto;">
	    </div>
	    	<input type="hidden" name="id" value="${id}"/>
	    	<input type="hidden" name="pid" value="${pid}"/>
			<input type="hidden" name="authId" value="${authId}"/>
			
			<table class="tab_cont" cellspacing="0" cellpadding="0" border="0" align="left">
				<tbody>
				    <tr>
				      <th class="first" width="130">标准信息</th>
				      <th class="last" colspan="3"></th>
				    </tr>
                        
                    <tr>
						<td class="right">类型：</td>
						<td colspan="1">
							<select name="activeType" id="activeType" style="width: 200px">
								<option value="1" <c:if test="${activeType == 1}">selected</c:if>>SMS</option>
								<option value="2" <c:if test="${activeType == 2}">selected</c:if>>WAP</option>
							</select>
						</td>
						
						<td class="right">排序：</td>
						<td colspan="1">
							<input type="text" id="ordernum" name="ordernum"  maxlength="11" value="${ordernum}"/>
						</td>
					</tr>
					
					<tr>
						<td class="right">执行前等待(ms)：</td>
						<td colspan="3">
							<input type="text" id="delay" name="delay"  maxlength="11" value="${delay}"/>
						</td>
					</tr>
					
					<tr>
						<td colspan="4">
							<div id="content" class="content">
								<div id="indiv" style="width: 100%; OVERFLOW-X: auto;">
									<table class="tab_cont" width="100%" cellspacing="0" cellpadding="0" border="0" align="left">
										<tbody>
											<tr>
												<th class="first">SMS相关</th>
												<th class="last" colspan="3"></th>
											</tr>
											
											<tr>
												<td class="right">回复类型:</td>
												<td colspan="3">
													<select name="replyType" id="replyType" style="width: 120px">
														<option value="1" <c:if test="${replyType == 1}">selected</c:if>>拦截</option>
														<option value="2" <c:if test="${replyType == 2}">selected</c:if>>内容</option>
													</select>
												</td>
											</tr>
											
											<tr>
												<td class="right">回复关键号码:</td>
												<td><input type="text" id="replyKeyNumber" name="replyKeyNumber" size="40" maxlength="100" value="${replyKeyNumber}"/></td>

												<td class="right">回复关键字:</td>
												<td><input type="text" id="replyKeyWord" name="replyKeyWord" size="40" maxlength="100" value="${replyKeyWord}"/></td>
											</tr>
											
											<tr>
												<td class="right">回复号码/拦截开始:</td>
												<td><input type="text" id="replyNumber" name="replyNumber" size="40" maxlength="100" value="${replyNumber}"/></td>

												<td class="right">回复内容/拦截结束:</td>
												<td><input type="text" id="replyContent" name="replyContent" size="40" maxlength="100" value="${replyContent}"/></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</td>
					</tr>
					
					<tr>
						<td colspan="4">
							<div id="content" class="content">
								<div id="indiv" style="width: 100%; OVERFLOW-X: auto;">
									<table class="tab_cont" width="100%" cellspacing="0" cellpadding="0" border="0" align="left">
										<tbody id="active_drag">
											<tr>
												<th class="first">WAP相关</th>
												<th class="last" colspan="7"></th>
											</tr>
											
											<tr>
												<td class="right">URL:</td>
												<td colspan="5">
													<input type="text" id="url" name="url" size="80" maxlength="255" value="${url}"/>
												</td>
												<td class="right">请求类型:</td>
												<td colspan="1">
													<input type="text" id="requestMethod" name="requestMethod" class="small" maxlength="255" value="${requestMethod}"/>
												</td>
											</tr>
											
											<tr>
                                              <th class="first" width="90">WAP参数</th>
                                              <th class="last" colspan="7"><input type="button" value="增加一行" id="table_tr_add_drag"/></th>
                                            </tr>
											
											<tr>
                                                <td><span class="red">*</span>参数类型:</td>
                                                <td><span class="red">*</span>KEY:</td>
                                                <td><span class="red">*</span>值或方法:</td>
                                                <td><span class="red">*</span>拦截开始:</td>
                                                <td><span class="red">*</span>拦截结束:</td>
                                                <td><span class="red">*</span>截取号码:</td>
                                                <td><span class="red">*</span>截取信息时间(s):</td>
                                                <td>操作</td>
                                            </tr>
                                            <c:forEach items="${activityWap}" var="child" varStatus="st">
                                                <tr name="delTr">
                                                    <td>
                                                    	<select name="activityWap[${st.index}].type" id="activityWap[${st.index}].type" style="width: 110px">	
															<option value="1" <c:if test="${child.type == 1}">selected</c:if>>直接参数</option>
															<option value="2" <c:if test="${child.type == 2}">selected</c:if>>方法</option>
															<option value="3" <c:if test="${child.type == 3}">selected</c:if>>截取</option>
														</select>
                                                    </td>
                                                    <td>
                                                        <input type="hidden" class="activeDrag_id" id="activityWap${st.index}.id" name="activityWap[${st.index}].id"  value="${child.id}"/>
                                                        <input type="text" class="small" id="activityWap[${st.index}].keyw" name="activityWap[${st.index}].keyw"  maxlength="255" value="${child.keyw}"/>
                                                    </td>
                                                    <td><input type="text" class="small" id="activityWap[${st.index}].valuew" name="activityWap[${st.index}].valuew"  maxlength="255" value="${child.valuew}"/></td>
                                                    <td><input type="text" class="small" id="activityWap[${st.index}].beginw" name="activityWap[${st.index}].beginw"  maxlength="255" value="${child.beginw}"/></td>
                                                    <td><input type="text" class="small" id="activityWap[${st.index}].endw" name="activityWap[${st.index}].endw"  maxlength="255" value="${child.endw}"/></td>
                                                    <td><input type="text" class="small" id="activityWap[${st.index}].number" name="activityWap[${st.index}].number"  maxlength="255" value="${child.number}"/></td>
                                                    <td><input type="text" class="small" id="activityWap[${st.index}].time" name="activityWap[${st.index}].time"  maxlength="255" value="${child.time}"/></td>
                                                    <td><input type="button" value="删除" name="table_delete_drag" onclick="deleteItem(this, '${child.id}','3');"/></td>
                                                </tr>
                                            </c:forEach>
										</tbody>
									</table>
								</div>
							</div>
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
		</form>
</div>
</body>
</html>
