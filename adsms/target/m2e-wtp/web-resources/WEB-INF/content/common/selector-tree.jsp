<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<security:authorize ifAnyGranted="ROLE_ANONYMOUS"><c:redirect url="/common/403.jsp"/></security:authorize>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>油品选择</title>
<%@ include file="/common/head.jsp"%>
    <!-- jsTree  -->
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/lib/jquery.cookie.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/lib/jquery.hotkeys.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/lib/jquery.metadata.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/lib/sarissa.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/jquery.tree.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/plugins/_jquery.tree.rtl.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/plugins/jquery.tree.checkbox.report.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/plugins/jquery.tree.contextmenu.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/plugins/jquery.tree.cookie.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/plugins/jquery.tree.hotkeys.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/plugins/jquery.tree.metadata.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/plugins/jquery.tree.themeroller.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/plugins/jquery.tree.xml_flat.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery/jsTree/plugins/jquery.tree.xml_nested.js"></script>
    <link rel="stylesheet" href="${ctx}/js/jQuery/jsTree/themes/checkbox/style.css" type="text/css"></link>

    <script type="text/javascript">

		//权限树 
		$(function (){
			parent.$("#treeload").hide();
			var seln = parent.$("#${name}").val().split(",");
			var selArray = [];
			if(seln!=""){seln = $(seln).each(function(i,item){selArray[i]=item.replace(/^\'/,'').replace(/\'$/,'');});}
				$("#oiltree").tree({
		   			selected:selArray,
		    		data : { type : "json", async : false }, ui : { theme_name : "checkbox" },
			 		types:{ "default" : { draggable : false} }, plugins : { checkbox : {three_state : false } },  // false can not cascading
			 		callback : { 
						onload : function (t) { 
							t.settings.data.opts.static = true; 
							t.settings.plugins.checkbox.three_state = true;
					    },
						beforedata : function (node, tree) { if(node == false) {tree.settings.data.opts.static = ${jtree}; } },//初始显示 
						onchange : function() { 
							var idArray = [];  //保存选中的id
							var nameArray = [];
							//if (jQuery.tree.plugins.checkbox.get_checked().length==0){return;}
							jQuery.tree.plugins.checkbox.get_checked().each( function (i,node){
																			idArray.push("'"+node.id+"'");
																			var nodename = $("#"+node.id+" a").first().text();
																			nameArray.push(nodename);
																		 });
							//把所有的被选中的id 赋值给隐藏域
							parent.$("#${name}").val(idArray.toString());
							parent.$("#selnm").val(nameArray.toString());
						}
					}
				});
		});

    </script>
    <style type="text/css">
		.pageJump{
			padding: 0px;
		}
		div.pagination{
			padding: 0px;
			margin: 0px;
		}

	.ui-autocomplete {
		max-height: 200px;
		overflow-y: auto;
	}
	</style>
</head>
<body>
<div id="main">
<div id="oiltree" class="treeDiv"></div>
</div>
</body>
</html>