package com.leolian.topic;

import java.util.UUID;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

//发送消息端  
public class SendLogTopic {
	private static final String EXCHANGE_NAME = "topic_logs";

	public static void main(String[] args) throws Exception {
		// 创建连接和频道
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.1.33");
		factory.setPort(AMQP.PROTOCOL.PORT);
		factory.setUsername("admin");
		factory.setPassword("admin");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		// 声明转发器
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		// 定义绑定键
		String[] routing_keys = new String[] { "kernel.info", "cron.warning","auth.info", "kernel.critical" };
		
		for (String routing_key : routing_keys) {
			// 发送4条不同绑定键的消息
			String msg = UUID.randomUUID().toString();
			channel.basicPublish(EXCHANGE_NAME, routing_key, null,msg.getBytes());
			
			System.out.println("--- Sent routingKey = " + routing_key + " ,msg = " + msg + ".");
			Thread.sleep(6000);
		}

		channel.close();
		connection.close();
	}

}