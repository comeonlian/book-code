package com.t35.util;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mail {
		
		public static void sendTxtMail(String text,String to)  
	    {  
	      /* Properties props = new Properties();  
	       props.put("mail.smtp.host", "smtp.qq.com"); //smtp服务器地址  
	  
	       props.put("mail.smtp.auth", true);  //是否需要认证  
	         
	       //props.put("mail.smtp.port", 46);  //端口
*/	         
			Properties props = new Properties();
			props.put("mail.host", "smtp.qq.com");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.port", 465);
			props.put("mail.smtp.socketFactory.port", 465);
	       /**实例化一个验证里，继承abstract Authenticator 
	        * 实现     
	        *   protected PasswordAuthentication getPasswordAuthentication(){ 
	        *       return new PasswordAuthentication(userName,password); 
	        *   } 
	        */   
	       
	       MyAuthenticator myauth = new MyAuthenticator("liangwen_459181870@qq.com","qpnkhxstvdpwbjdc");  
	       //获得一个带有authenticator的session实例  
	       Session session = Session.getInstance(props,myauth);  
	       session.setDebug(true);//打开debug模式，会打印发送细节到console  
	       Message message = new MimeMessage(session); //实例化一个MimeMessage集成自abstract Message 。参数为session  
	       try  
	       {  
	       message.setFrom(new InternetAddress("liangwen_459181870@qq.com")); //设置发出方,使用setXXX设置单用户，使用addXXX添加InternetAddress[]  
	  
	       message.setText(text); //设置文本内容 单一文本使用setText,Multipart复杂对象使用setContent  
	  
	       message.setSubject("验证码"); //设置标题   
	  
	       message.setRecipient(Message.RecipientType.TO, new InternetAddress(to)); //设置接收方  
	       
	       /*Transport transport = session.getTransport();
	       transport.connect("smtp.qq.com", 465, "javamail_test100", "123456");  */
	       
	      Transport.send(message); //使用Transport静态方法发送邮件  
	    
	       }catch(AddressException e)  
	       {  
	           //此处处理AddressException异常  [The exception thrown when a wrongly formatted address is encountered.]   
	  
	       }catch(MessagingException e)  
	       {  
	           //此处处理MessagingException异常 [The base class for all exceptions thrown by the Messaging classes ]  
	       }  
	         
	    }  

}
