package com.leolian.optimize.mutilconsumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 生产者
 * 测试多消费者
 * @author lianliang
 */
public class Producer {
	//IP
	private final static String HOST = "192.168.1.33";
	//用户名密码
	private final static String USERNAME = "admin";
	private final static String PASSWORD = "admin";
	//声明交换器的名称
	private final static String EXCHANGE_NAME = "topic_logs";
	//交换器类型
	private final static String EXCHANGE_TYPE = "topic";
	//交换器topic匹配规则
	private final static String KEY = "klone.test";
	
	
	
	public static void main(String[] args) throws Exception{
		//创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		factory.setPort(AMQP.PROTOCOL.PORT);
		factory.setUsername(USERNAME);
		factory.setPassword(PASSWORD);
		//工厂生成连接
		Connection connection = factory.newConnection();
		//创建虚拟连接
		Channel channel = connection.createChannel();
		//声明交换器
		channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
		
		String msg = "";
		//发送消息
		for(int i=0; i<500000; i++){
			msg = "This is " + i + " message ....";
			//发布消息到通道
			channel.basicPublish(EXCHANGE_NAME, KEY, null, msg.getBytes());
			System.out.println("--- Sending key : " + KEY + ", msg : "+msg);
		}
		//关闭连接
		channel.close();
		connection.close();
	}

}
