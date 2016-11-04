package com.leolian.platform.gb;

import com.leolian.optimize.RabbitmqConstant;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * 生产者
 * 测试应答机制
 * @author lianliang
 */
public class GbProducer {
	
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
		//channel.basicQos(RabbitmqConstant.PREFETCH_COUNT);
		//声明转发器
		//且把转发器声明为可持久化
		channel.exchangeDeclare(RabbitmqConstant.EXCHANGE_NAME, RabbitmqConstant.EXCHANGE_TYPE, RabbitmqConstant.DURATION);
		
		String msg = "", msgKey = "";
		//发送消息
		for(int i=0; i<500000; i++){
			msg = "This is " + i + " message ....";
			//消息路由键会根据消息类型的不同改变
			msgKey = RabbitmqConstant.GB_KEY; 
			//发布消息到通道
			//并且设置消息的持久化
			channel.basicPublish(RabbitmqConstant.EXCHANGE_NAME, msgKey, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
			System.out.println("--- Sending key : " + msgKey + ", msg : "+msg);
			
			//Thread.sleep(10);
		}
		//关闭连接
		channel.close();
		connection.close();
	}

}
