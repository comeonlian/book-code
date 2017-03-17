package com.leolian.rpc;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.leolian.thrift.demo.HelloWorldService;

public class RpcClient {
	
	public static void start(int port) throws Exception{
		TTransport transport = new TSocket("localhost", port);
		TProtocol prot = new TBinaryProtocol(transport);
		HelloWorldService.Client client = new HelloWorldService.Client(prot);
		transport.open();
		String result = client.sayHello("Zhangsan");
		System.out.println(result);
		
	}
	
	public static void main(String[] args) throws Exception {
		start(9090);
	}
	
}
