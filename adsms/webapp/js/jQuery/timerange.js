/**
 * 获取时间如：12:12
 * 
 */
$(function(){
	//
	$('.timeRange').click(function(){
		$('#timeRange_div').remove();
		
		var hourOpts = '<option>00</option><option>01</option><option>02</option><option>03</option><option>04</option><option>05</option><option>06</option><option>07</option><option>08</option><option>09</option>';
		for (i=10;i<24;i++) hourOpts += '<option>'+i+'</option>';
		var minuteOpts = '<option>00</option><option>15</option><option>30</option><option>45</option><option>59</option>';
		var secondOpts = '<option>00</option><option>15</option><option>30</option><option>45</option><option>59</option>';
		
		var html = $('<div id="timeRange_div"><select id="timeRange_a">'+hourOpts+
			'</select>:<select id="timeRange_b">'+minuteOpts+
			'</select>:<select id="timeRange_c">'+secondOpts+
			'<input type="button" value="确定" id="timeRange_btn" /></div>')
			.css({
				"position": "absolute",
				"z-index": "999",
				"padding": "5px",
				"border": "1px solid #AAA",
				"background-color": "#FFF",
				"box-shadow": "1px 1px 3px rgba(0,0,0,.4)"
			})
			.click(function(){return false});
		// 如果文本框有值
		var v = $(this).val();
		if (v) {
			v = v.split(/:|-/);
			html.find('#timeRange_a').val(v[0]);
			html.find('#timeRange_b').val(v[1]);
			html.find('#timeRange_c').val(v[2]);
		}
		// 点击确定的时候
		var pObj = $(this);
		html.find('#timeRange_btn').click(function(){
			var str = html.find('#timeRange_a').val()+':'
				+html.find('#timeRange_b').val()+':'+html.find('#timeRange_c').val();
			pObj.val(str);
			$('#timeRange_div').remove();
		});
		
		$(this).after(html);
		return false;
	});
	//
	$(document).click(function(){
		$('#timeRange_div').remove();
	});
	//
});