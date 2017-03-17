package com.leolian.rpc;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.leolian.thrift.demo.FrameworkService;

@Component
public class NonBlockRpcServer implements ApplicationListener<ContextRefreshedEvent>,Ordered{
	
	public static void start(int port) throws Exception{
		TProcessor processor = new FrameworkService.Processor<FrameworkService.Iface>(new FrameworkServiceImpl());
		TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(port);
		TNonblockingServer.Args args = new TNonblockingServer.Args(serverSocket);
		args.processor(processor);
		args.transportFactory(new TFramedTransport.Factory());
		args.protocolFactory(new TBinaryProtocol.Factory());
		TServer server = new TNonblockingServer(args);
		server.serve();
	}
	
	public static void main(String[] args) throws Exception {
		start(9090);
	}
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		try {
			start(9090);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}
}
