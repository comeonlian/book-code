<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${auth.label}</title>
	<%@ include file="/common/head.jsp" %>
</head>

<body>
<!-- 当前位置 -->
<div class="currloca">
  <p>${auth.fullMenu}</p>
  <div class="sitemap">
    <span style="display:block;float:left"><s:actionmessage theme="custom"/></span>
    <span id="add2custom"><img class="pointer" onclick="add2custom('${authId}');return false;" id="aCustom" width="24" align="absmiddle" height="24" title="添加到常用操作" src="../images/favorite.png"/></span>
  </div>
</div>

<div class="container">
  <!-- 内容区域 -->
  <div class="itemtitle"><h2>${auth.label}</h2></div>
  
  <form id="mainForm" action="files.action" method="post">
  	<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
	<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
	<input type="hidden" name="page.order" id="order" value="${page.order}"/>
	<input type="hidden" name="domainId" id="domainId" value="${domainId}"/>
	<input type="hidden" name="authId" id="authId" value="${authId}"/>
	<input type="hidden" name="page.pageSize" id="pageSize" value="${page.pageSize}" />
	<input type="hidden" name="page.excelExp" id="excelExp" value="${page.excelExp}" />
	
   <!-- 查询条件 -->
   <div id="filter" class="filter">
    <div id="item" class="item">
    <div class="where">
        <div class="and">
          <span>文件名称:</span><input type="text" class="small" name="filter_LIKES_title" id="title" value="${param.filter_LIKES_title}" />
        </div>
        <div class="and">
          <span>文件编码:</span><input type="text" class="small" name="filter_LIKES_code" id="code" value="${param.filter_LIKES_code}" />
        </div>
        <div class="and">
          <span>归属类型:</span><wlps:dicSelect className="small" firstNull="true" dcode="oa_files_type" name="filter_LIKES_type" id="type" icode="${param.filter_LIKES_type}"></wlps:dicSelect>
        </div>
    </div>
      <div class="space"></div>
      <div style="text-align: center;">
      <img src="../images/b_select.gif" alt="" onclick="search();" class="pointer" align="middle"/>
      <img src="../images/b_reset.gif" alt="" onclick="resetb();" class="pointer"align="middle"/>
      </div>
    </div>
    <div id="contral" class="contral pointer" onclick="contral(this);"><img src="../images/f_close.gif" title="收起查询面板"/></div>
  </div>
  
  <!-- 附加信息-->
  <div id="message" class="message">
    <span style="font-weight: bold;font-size: 14px;"><!-- 提示：资源列表... --></span>
  </div>
  
  <!-- 增删改查...操作菜单-->
  <div id="operate" class="operate">
    <div><security:authorize ifAnyGranted="A_sys_role_add"><img src="../images/b_add.gif" alt="增加" onclick="opr_input('files!input.action',false,'${authId}');"/></security:authorize></div>
    <div><security:authorize ifAnyGranted="A_sys_role_add"><img src="../images/b_edit.gif" alt="修改" onclick="opr_update('files!input.action','mainForm',false,'${authId}');" /></security:authorize></div>
    <div><security:authorize ifAnyGranted="A_sys_role_delete"><img src="../images/b_del.gif" alt="删除" onclick="opr_delete('files!delete.action','mainForm');" /></security:authorize></div>
   <!-- <div><img src="../images/import.gif" alt="导入" onclick="importExcelInit('${nameSpace}${enityName}!load.action','${authId}');" /></div> -->
  </div>
  <!-- 列表区域-->
  <div id="content" class="content">
    <table class="tab_cont" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
    <tbody>
		  <tr>
		      <th width="3%" class="first"><input type="checkbox" id="box" name="box" onclick="checkedAll('ids')"/></th>
              <th width="10%"><a href="javascript:sort('code','asc')">文件编码</a></th>
              <th width="10%"><a href="javascript:sort('title','asc')">文件名称</a></th>
              <th width="10%"><a href="javascript:sort('type','asc')">归属类型</a></th>
              <th class="last"><a href="javascript:sort('description','asc')">描述</a></th>
            </tr>

			<s:iterator value="page.result">
				<tr>
					<td><input type="checkbox" value="${id}" id="id_${id}" name="ids"/></td>
                    <td>${code}</td>
                    <td>${title}</td>
                    <td><wlps:dic dcode="oa_files_type" icode="${type}"></wlps:dic></td>
                    <td>${description}</td>
                </tr>
			</s:iterator>
    </tbody>
    </table>
    </div>
  </form>
  
  <!-- 分页区域-->
  <div id="page" class="page">
  	<wlps:page page="${page}" showPageSize="true" excelExp="false" />
  </div>  
  
</div>
</body>
</html>
