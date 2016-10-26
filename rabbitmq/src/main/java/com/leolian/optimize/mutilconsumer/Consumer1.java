package com.leolian.optimize.mutilconsumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 消费者
 * 测试多消费者
 * @author Administrator
 *
 */
public class Consumer1 {
	//IP
	private final static String HOST = "192.168.1.33";
	//用户名密码
	private final static String USERNAME = "admin";
	private final static String PASSWORD = "admin";
	//指定队列名称
	private final static String QUEUE_NAME = "first";
	//声明交换器的名称
	private final static String EXCHANGE_NAME = "topic_logs";
	//交换器类型
	private final static String EXCHANGE_TYPE = "topic";
	//交换器topic匹配规则
	private final static String KEY = "klone.*";
	
	
	
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
		//声明队列
		channel.queueDeclare(QUEUE_NAME,false, false, false, null);
		//队列绑定交换器
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, KEY);
		//消费消息
		System.out.println("--- Consumer1 Waiting for messages about klone.");
		//声明消费者，并绑定到通道
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, true, consumer);
		//消费者持续接收
		String message = "";
		String routingKey = "";
		while(true){
			//从一个消费者中取出消息
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			//获取消息
			message = new String(delivery.getBody());
			//打印，也可以进行其他其他操作
			routingKey = delivery.getEnvelope().getRoutingKey();
			System.out.println("--- Consume1 received: key = " + routingKey + ", msg = " + message + "");
		}
	}
}
