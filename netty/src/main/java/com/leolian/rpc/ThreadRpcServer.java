package com.leolian.rpc;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;

import com.leolian.thrift.demo.HelloWorldService;

public class ThreadRpcServer {
	
	public static void start(int port) throws Exception{
		TProcessor processor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());
		TServerSocket serverSocket = new TServerSocket(port);
		TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverSocket);
		args.processor(processor);
		args.protocolFactory(new TBinaryProtocol.Factory());
		TServer server = new TThreadPoolServer(args);
		server.serve();
	}
	
	public static void main(String[] args) throws Exception {
		start(9090);
	}

}
