package com.leo;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.leo.entity.User;
import com.leo.service.DemoServer;

public class Consumer {

	public static void main(String[] args) throws Exception{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.start();
		//获取接口实现类
		DemoServer demoService = (DemoServer) context.getBean("demoServer");
		//调用接口的sayHello方法
		String hello = demoService.sayHello("Tom");
		System.out.println(hello);
		//调用接口的getUser方法
		List<User> list = demoService.getUser();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getName());
			}
		}
		System.in.read();
	}

}
