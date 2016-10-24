package com.leolian.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {

	private final static String QUEUE_NAME = "first";

	public static void main(String[] args) throws Exception{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.1.33");
		factory.setPort(5672);
		factory.setUsername("admin");
		factory.setPassword("admin");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "Hehe ...!";
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		System.out.println("------ Sent '" + message + "'");

		channel.close();
		connection.close();
	}

}
