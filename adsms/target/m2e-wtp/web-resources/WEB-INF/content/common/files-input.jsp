<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${id==null?'新增文件':'修改文件' }</title>
    <%@ include file="/common/head.jsp" %>
    <script src="${ctx}/js/jQuery/validate/jquery.validate.js" type="text/javascript"></script>
    <link href="${ctx}/js/jQuery/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
    <script type="text/JavaScript">
	$(function(){
		$("#type").change(function(){
			if($(this).val()=='3'){
			    $("#dep").hide();
			}else{
				$("#dep").show();
			}
		});
		$("#type").change();
		$("#department").val("${domain}");
		$("#delFiles").click(function(){
			var path ="${path}";
		    jConfirm("确定删除文件吗？", "办公室自动化系统", function(result) {
		        if (result) {
		            var url= "${ctx}/common/files!deleteFile.action?path="+path;
		            $.get(url,function(item){
		                if(item=='ok'){
		                    jAlert("删除文件成功.","办公室自动化系统");
			             }
			        });
		        }
		    });
		});

		//为inputForm注册validate函数
        $("#inputForm").validate({
            rules: {
            code: {
                    required: true,
                    remote: "files!checkFilesCode.action?oldId=" + '${id}'
                }
            },
            messages: {
                code: {
                    remote: "文件编码已存在"
                }
            }
        });
	});
    </script>
</head>

<body>

<div id="fixtitle">
<!-- 当前位置 -->
<div class="currloca">
  <p>${auth.fullMenu}</p>
  <div class="sitemap">
    <span style="display:block;float:left"><s:actionmessage theme="custom"/></span>
    <span id="add2custom"><img class="pointer" onclick="add2custom('${authId}');return false;" id="aCustom" width="24" align="absmiddle" height="24" title="添加到常用操作" src="../images/favorite.png"/></span>
  </div>
</div>

<div class="itemtitle"><h2>${id==null?'新增文件':'修改文件' }</h2></div>
</div>

<div class="container" >
  <!-- 内容区域 -->
  
  <!-- 附加信息-->
  <div id="message" class="message">
    <span style="font-weight: bold;font-size: 14px;"><!-- 提示：资源列表... --></span>
  </div>
  
  <form action="files!save.action" method="post" id="inputForm" enctype="multipart/form-data" >
    <!-- 列表区域-->
      <div id="content" class="content">
        <input type="hidden" name="id" value="${id}"/>
        <input type="hidden" name="authId" value="${authId}"/>
        <table class="tab_cont" cellspacing="0" cellpadding="0" border="0" align="left">
            <tbody>
              <tr>
                  <th class="first" width="130">文件信息</th>
                  <th class="last"></th>
              </tr>
              <tr>
                  <td class="right">编号:<img class="tip" src="../images/s_info.gif" alt="" align="absmiddle"/></td>
                  <td><input id="code" type="text" tip="编号具有唯一性，例：QC-XXX-XX" value="${code}" size="40" name="code" /></td>
               </tr>
               <tr>
                <td class="right">文件名称:</td>
                <td><input class="button" type="text" name="title" value="${title}" /></td>
              </tr>
              <tr>
                <td class="right">选择文件:</td>
                <td><input class="button" type="file" name="upload" value="${path}" /><c:if test="${!empty path}">上传文件：${path}</c:if></td>
              </tr>
              <tr>
                  <td class="right">归属类型:</td>
                  <td>
                  <wlps:dicSelect className="medium" dcode="oa_files_type" name="type" id="type" icode="${type}"></wlps:dicSelect>
                  </td>
              </tr>
                <tr id="dep" style="display: none">
                  <td class="right">部门:</td>
                  <td>
                  <select name="domain" class="medium" id="department"><c:forEach var="dl" items="${depList}"><option value="${dl.code}">${dl.label}</option></c:forEach></select>
                  </td>
              </tr>
              <tr>
                <td class="right">描述:</td>
                <td><textarea id="description" cols="35" rows="2" name="description">${description}</textarea></td>
              </tr>
              <tr>
                <th class="first" width="130"></th>
                <th class="last">
                    <input class="button" type="submit" value="确定"/>&nbsp;
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
