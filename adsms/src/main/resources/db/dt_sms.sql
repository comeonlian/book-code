DROP TABLE IF EXISTS `short_log_sms_dtmo`;
CREATE TABLE short_log_sms_dtmo(
	id INT UNSIGNED auto_increment PRIMARY KEY, 
	accesstime  datetime COMMENT'访问时间',
	ip  VARCHAR(16) COMMENT'访问IP',
	user_agent  VARCHAR(255),
	mobile  VARCHAR(11)  COMMENT'用户电话号码:15081255953',
	spnum  VARCHAR(20) COMMENT'SP接入号码:1066101101',
	linkid VARCHAR(30) COMMENT'10370403618315949668',
	statement VARCHAR(10) COMMENT'指令代码:1',
	flag  VARCHAR(10)  COMMENT'类别:0'
)ENGINE MyISAM CHARSET utf8;

DROP TABLE IF EXISTS `short_log_sms_dtmr`;
CREATE TABLE short_log_sms_dtmr(
	id INT UNSIGNED auto_increment PRIMARY KEY, 
	accesstime datetime COMMENT'访问时间',
	ip VARCHAR(16) COMMENT'访问IP',
	user_agent VARCHAR(255),  
	mobile VARCHAR(11) COMMENT'用户电话号码:15081255953',
	linkid VARCHAR(30) COMMENT'linkid:10370403618315949668',
	status VARCHAR(15) COMMENT'返回状态:R',    
	spnum  VARCHAR(20)  COMMENT'sp接入号码:1066101101',
	statement VARCHAR(10)   COMMENT'指令代码'
)ENGINE MyISAM  CHARSET utf8;