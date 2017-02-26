package com.leolian.rpc;

import org.apache.thrift.TException;

import com.leolian.thrift.demo.HelloWorldService;

public class HelloWorldServiceImpl implements HelloWorldService.Iface {

	@Override
	public String sayHello(String username) throws TException {
		System.out.println("Hello "+username+" ...!");
		return "OK";
	}
	
}
