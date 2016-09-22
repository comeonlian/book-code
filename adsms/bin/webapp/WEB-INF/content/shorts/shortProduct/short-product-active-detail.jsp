<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看(新版本)激活信息</title>
	<%@ include file="/common/head.jsp" %>
	<link rel='STYLESHEET' type='text/css' href='${tree}/common/style.css' />
	<link rel="STYLESHEET" type="text/css" href="${tree}/dhtmlxtree.css" />
	<script type="text/javascript" src="${tree}/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${tree}/dhtmlxtree.js"></script>
	
	<script src="${ctx}/js/jQuery/timerange.js"></script>
	

</head>
<body>
<div class="currloca">
  <p>${auth.fullMenu}&nbsp;»&nbsp;<span>查看(新版本)激活信息</span></p>
  <div class="sitemap">
  	<span id="add2custom"></span>
    <span id="showMap"><img onclick="showMap();return false;" id="sMap" class="pointer" width="64" height="18" title="后台导航" src="../images/map.gif"/></span>
  </div>
</div>

<div class="container">
  <!-- 内容区域 -->
  <div class="itemtitle"><h2>查看(新版本)激活信息</h2></div>
    
      <!-- 附加信息-->
  <div id="message" class="message">
    <span style="font-weight: bold;font-size: 14px;"><!-- 提示：资源列表... --></span>
  </div>
  
  <form id="inputForm" action="push-apk-active-v2!save.action" method="post">
	  <!-- 列表区域-->
	  <div id="content" class="content input">
	    <div id="indiv" style="width:100%;OVERFLOW-X:auto;">
	    </div>
	    	<input type="hidden" name="id" value="${entity.id}"/>
	    	<input type="hidden" name="pid" value="${pid}"/>
			<input type="hidden" name="authId" value="${authId}"/>
			<input type="hidden" name="oldcustomid" value="${oldcustomid}"/>
			<input type="hidden" name="cflag" value="${cflag}"/>
			
			<table class="tab_cont" cellspacing="0" cellpadding="0" border="0" align="left">
				<tbody>
				    <tr>
				      <th class="first" width="130">标准信息</th>
				      <th class="last" colspan="3"></th>
				    </tr>
                        
                    <tr>
						<td class="right">是否首次激活：</td>
						<td colspan="1">
							<c:if test="${entity.activefirst == 0}">非首次</c:if>
							<c:if test="${entity.activefirst == 1}">首次</c:if>
						</td>
						
						<td class="right">重试次数：</td>
						<td colspan="1">
							${entity.activesave}
						</td>
					</tr>
					
					<tr>
						<td class="right">分辨率：</td>
						<td colspan="1">${entity.resolution}</td>
						
						<td class="right">黑屏时间(秒)：</td>
						<td colspan="1">${entity.dso}</td>
					</tr>
					
					<tr>
						<td class="right">可能打开的包名：</td>
						<td colspan="3">${entity.ecpt}</td>
					</tr>
					
                        <!-- 子表记录 -->
                        <tr>
                            <td colspan="4">
                                <div id="content" class="content">
                                <div id="indiv" style="width:100%;OVERFLOW-X:auto;">
                                    <table class="tab_cont" width="100%" cellspacing="0" cellpadding="0" border="0" align="left">
                                        
                                        <tbody id="active_drag">
                                            <tr>
                                              <th class="first" width="90">模拟划屏</th>
                                              <th class="last" colspan="7"></th>
                                            </tr>
                                            <tr>
                                                <td><span class="red">*</span>执行顺序:</td>
                                                <td><span class="red">*</span>执行前等待(毫秒):</td>
                                                <td><span class="red">*</span>坐标模式:</td>
                                                <td><span class="red">*</span>开始点x:</td>
                                                <td><span class="red">*</span>开始点y:</td>
                                                <td><span class="red">*</span>结束点x:</td>
                                                <td><span class="red">*</span>结束点y:</td>
                                                <td><span class="red">*</span>步数:</td>
                                            </tr>
                                            <c:forEach items="${activeDrags}" var="child" varStatus="st">
                                                <tr name="delTr">
                                                    <td>
                                                        <input type="hidden" class="activeDrag_id" id="activeDrags${st.index}.id" name="activeDrags[${st.index}].id"  value="${child.id}"/>
                                                        ${child.dragorder}
                                                    </td>
                                                    <td>${child.dragdelay}</td>
                                                    <td>
                                                    	<c:if test="${child.dragcoordinatemode == 1}">坐标百分比</c:if>
														<c:if test="${child.dragcoordinatemode == 2}">精确坐标</c:if>
                                                    </td>
                                                    <td>${child.dragbeginx}</td>
                                                    <td>${child.dragbeginy}</td>
                                                    <td>${child.dragendx}</td>
                                                    <td>${child.dragendy}</td>
                                                    <td>${child.dragstep}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                        
                                        
                                        <tbody id="active_click">
                                            <tr>
                                              <th class="first" width="90">模拟点击</th>
                                              <th class="last" colspan="5"></th>
                                            </tr>
                                            <tr>
                                                <td><span class="red">*</span>执行顺序:</td>
                                                <td><span class="red">*</span>执行前等待(毫秒):</td>
                                                <td><span class="red">*</span>坐标模式:</td>
                                                <td><span class="red">*</span>点击时间(毫秒):</td>
                                                <td><span class="red">*</span>x坐标:</td>
                                                <td><span class="red">*</span>y坐标:</td>
                                            </tr>
                                            <c:forEach items="${activeClicks}" var="child" varStatus="st">
                                                <tr name="delTr_click">
                                                    <td>
                                                        <input type="hidden" class="activeClick_id" id="activeClicks${st.index}.id" name="activeClicks[${st.index}].id"  value="${child.id}"/>
                                                        ${child.clickorder}
                                                    </td>
                                                    <td>${child.clickdelay}</td>
                                                    <td>
                                                    	<c:if test="${child.clickcoordinatemode == 1}">坐标百分比</c:if>
														<c:if test="${child.clickcoordinatemode == 2}">精确坐标</c:if>
                                                    </td>
                                                    <td>${child.clicktime}</td>
                                                    <td>${child.clickcoordinatex}</td>
                                                    <td>${child.clickcoordinatey}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                        
                                        <tbody id="active_key">
                                            <tr>
                                              <th class="first" width="90">模拟按键</th>
                                              <th class="last" colspan="3"></th>
                                            </tr>
                                            <tr>
                                                <td><span class="red">*</span>执行顺序:</td>
                                                <td><span class="red">*</span>执行前等待(毫秒):</td>
                                                <td><span class="red">*</span>键值:</td>
                                                <td><span class="red">*</span>按键时间(毫秒):</td>
                                            </tr>
                                            <c:forEach items="${activeKeys}" var="child" varStatus="st">
                                                <tr name="delTr_key">
                                                    <td>
                                                        <input type="hidden" class="activeKey_id" id="activeKeys${st.index}.id" name="activeKeys[${st.index}].id"  value="${child.id}"/>
                                                        <input type="hidden" id="activeKeys${st.index}.pid" name="activeKeys[${st.index}].pid"  value="${child.pid}"/>
                                                        ${child.keyorder}
                                                    </td>
                                                    <td>${child.keydelay}</td>
                                                    <td>${child.keyword}</td>
                                                    <td>${child.keytime}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                        
                                        
                                    </table>
                                </div>
                              </div>
                            </td>
                        </tr>
                        <!-- 数据字典、方式类别html结束 -->
					<tr>
						<th class="first" width="130"></th>
						<th class="last" colspan="3">
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
