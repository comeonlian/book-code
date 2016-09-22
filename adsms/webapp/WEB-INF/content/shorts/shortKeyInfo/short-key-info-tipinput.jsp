<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>关键字子信息</title>
<%@ include file="/common/head.jsp" %>
  <script type="text/javascript">
  	$(function(){
  		// 弹出层用lhgdialog
	    var DG = frameElement.lhgDG;
	    DG.addBtn( 'cancel', '返回', cancel );
	    DG.addBtn( 'ok', '确定', ok );
	    function ok(){
	        // 这里写你要操作的代码，最后写刷新代码
				$.post("short-key-info!tipsave.action", {
							pid : "${pid}",
							authId : "${authId}",
							tipId : "${tipId}",
							kbegin : $("#kbegin").val(),
							keyend : $("#keyend").val(),
							del : $("#del").val(),
							ret : $("#ret").val(),
							smes : $("#smes").val(),
							cons : $("#cons").val()
						}, function cancel() {
							$("#mainForm",window.parent.document).submit(); // 实现界面刷新
						});
	    }
	    function cancel() {
			DG.cancel();
		}
	});

	function save() {
			$("#inputForm").submit();
	}
	
  	/* function validateForm() {
		// 广告开始
		if ($("#kbegin").val() == '') {
			jAlert("广告开始不能为空!", '自动化办公系统');
			return false;
		}
		// 广告结束
		if ($("#keyend").val() == '') {
			jAlert("广告结束不能为空!", '自动化办公系统');
			return false;
		}
		return true;
  	} */
</script>
</head>

<body>
<form id="inputForm" action="short-key-info!tipsave.action" method="post">
<input type="hidden" name="authId" id="authId" value="${authId}"/>
<input type="hidden" name="pid" id="pid" value="${pid}"/>
	<!-- <div class="container">  -->
	  <!-- 列表区域-->
	  <div id="content" class="content input">
	  	<div id="indiv">
	  		<table class="tab_cont" cellspacing="0" cellpadding="0" border="0" align="left" width="620px">
	  			<tbody>
	  				<tr>
				      <th class="first last" colspan="2">关键字子信息：</th>
				    </tr>
				    <tr>
				    	<td class="right fist"><span>广告开始:</span></td>
				    	<td class="last">
				    		<textarea rows="2" cols="60" name="kbegin" id="kbegin">${keyTip.kbegin}</textarea>
						</td>
				    </tr>
				    <tr>
						<td class="right">广告结束:</td>
						<td>
							<textarea rows="2" cols="60" name="keyend" id="keyend">${keyTip.keyend}</textarea>
						</td>
					</tr>
				    <tr>
						<td class="right">是否回删:</td>
						<td>
							<select name="del" id="del" style="width: 120px">
								<option value="1" <c:if test="${keyTip.del == 1}">selected</c:if>>是</option>
								<option value="0" <c:if test="${keyTip.del == 0}">selected</c:if>>否</option>
							</select>
						</td>
					</tr>
				    <tr>
						<td class="right">是否回传:</td>
						<td>
							<select name="ret" id="ret" style="width: 120px">
								<option value="0" <c:if test="${keyTip.ret == 0}">selected</c:if>>否</option>
								<option value="1" <c:if test="${keyTip.ret == 1}">selected</c:if>>是</option>
							</select>
						</td>
					</tr>
				    <tr>
						<td class="right">号码:</td>
						<td>
							<input type="text" id="smes" name="smes" size="60" maxlength="200" value="${keyTip.smes}"/>
						</td>
					</tr>
				    <tr>
						<td class="right">内容:</td>
						<td>
							<textarea rows="10" cols="60" name="cons" id="cons">${keyTip.cons}</textarea>
							
							<!--  <input type="text" id="cons" name="cons" size="60" maxlength="200" value="${keyTip.cons}"/>
						      -->
						</td>
					</tr>
				    
	  			</tbody>
	  		</table>
	  	</div>
	  </div>	  
	<!-- </div>  -->
</form>
</body>
</html>