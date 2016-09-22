<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<style>
#funBar span{cursor: pointer;}
</style>
<script >
    $(document).ready(function(){
        if(report1_getTotalPage() == 1){$("#pageBar").hide();}
        $('#c_page_span').html(report1_getTotalPage());
        $('#t_page_span').html(report1_getCurrPage());
    }); 
</script>
<div id="funBar" style="float:right;padding-right: 60px;padding-top: 0px;">
	<span onclick="report1_print();return false;"><img src='${ctx}/images/runqian/print.png' border="no" title="打印" style="cursor:hand;"/></span>
	<span onclick="report1_saveAsExcel();return false;"><img src='${ctx}/images/runqian/excel.png' border="no" title="导出为Excel"/></span>
	<span onclick="report1_saveAsPdf();return false;"><img src='${ctx}/images/runqian/pdf.png' border="no" title="导出为pdf"/></span>
	<span onclick="report1_saveAsWord();return false;"><img src='${ctx}/images/runqian/word.png' border="no" title="导出为word"/></span>
	<span id="pageBar">        
		<span onclick="try{report1_toPage( 1 );}catch(e){}return false;"><img src='${ctx}/images/runqian/page_first.png' border="no"/></span>
		<span onclick="try{report1_toPage(report1_getCurrPage()-1);}catch(e){}return false;"><img src='${ctx}/images/runqian/page_prev.png' border="no" /></span>
		<span onclick="try{report1_toPage(report1_getCurrPage()+1);}catch(e){}return false;"><img src='${ctx}/images/runqian/page_next.png' border="no" /></span>
		<span onclick="try{report1_toPage(report1_getTotalPage());}catch(e){}return false;"><img src='${ctx}/images/runqian/page_last.png' border="no" /></span>
		<span>&nbsp;&nbsp;第<span id="t_page_span"></span>页/共<span id="c_page_span"></span>页&nbsp;</span>
	</span>
</div>
<div style="padding: 0px;clear: both;"><!-- 报表参数：${params} --></div>
<!-- 报表输出 -->     
<div id="indiv"><center>

<report:html name="report1" reportFileName="${param.reportFileName}"
   funcBarLocation="bottom"
   needPageMark="${param.needPageMark==null?'no' : param.needPageMark}"
   generateParamForm="no"
   params="${params}"
   width="-1"
   exceptionPage="reportError.jsp"
   saveAsName="${param.saveAsName==null?param.reportFileName : param.saveAsName}"
/></center>
</div>
