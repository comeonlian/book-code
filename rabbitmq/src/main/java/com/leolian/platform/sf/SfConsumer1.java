package com.leolian.platform.sf;

import com.leolian.optimize.RabbitmqConstant;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 消费者
 * 测试应答机制
 * @author Administrator
 *
 */
public class SfConsumer1 {
	
	public static void main(String[] args) throws Exception{
		//创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(RabbitmqConstant.HOST1);
		factory.setPort(AMQP.PROTOCOL.PORT);
		factory.setUsername(RabbitmqConstant.USERNAME);
		factory.setPassword(RabbitmqConstant.PASSWORD);
		//工厂生成连接
		Connection connection = factory.newConnection();
		//创建虚拟连接 
		Channel channel = connection.createChannel();
		//设置公平分发数量为1个
//		channel.basicQos(RabbitmqConstant.PREFETCH_COUNT);
		//声明交换器，持久化转发器
		channel.exchangeDeclare(RabbitmqConstant.EXCHANGE_NAME, RabbitmqConstant.EXCHANGE_TYPE, RabbitmqConstant.DURATION);
		//声明队列，持久化队列
		String queueName = RabbitmqConstant.SF_QUEUE; //消费的队列
		channel.queueDeclare(queueName, RabbitmqConstant.DURATION, false, false, null);
		//队列绑定交换器
		channel.queueBind(queueName, RabbitmqConstant.EXCHANGE_NAME, RabbitmqConstant.SF_KEY);
		//消费消息
		System.out.println("--- SF Consumer1 Waiting for messages about klone.");
		//声明消费者，并绑定到通道
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queueName, false, consumer);
		//消费者持续接收
		String message = "";
		String routingKey = "";
		while(true){
			//从一个消费者中取出消息
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			//获取消息
			message = new String(delivery.getBody());
			routingKey = delivery.getEnvelope().getRoutingKey();
			//打印，也可以进行其他其他操作
			System.out.println("--- SF Consume1 received: key = " + routingKey + ", msg = " + message + "");
			
			//Thread.sleep(10);
			
			//手动应答
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}
	}
}
