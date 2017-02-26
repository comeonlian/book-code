package com.leolian.rpc;

import java.lang.reflect.Method;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TMemoryBuffer;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.leolian.thrift.demo.Content;
import com.leolian.thrift.demo.FrameworkService;
import com.leolian.thrift.demo.HelloWorldService;
import com.leolian.thrift.demo.ThriftRequest;
import com.leolian.thrift.demo.ThriftResponse;

public class NonBlockRpcClient {
	
	public static Object start(int port, ThriftRequest request, Class clazz) throws Exception{
		TTransport transport = new TFramedTransport(new TSocket("localhost", port));
		TProtocol prot = new TBinaryProtocol(transport);
		FrameworkService.Client client = new FrameworkService.Client(prot);
		transport.open();
		ThriftResponse thriftResponse = client.execute(request);
		Object content = buildResponse(thriftResponse, clazz);
		return content;
	}
	
	private static Object buildResponse(ThriftResponse thriftResponse, Class clazz) {
		Object obj = null;
		try {
			obj = clazz.newInstance();
			Method read = clazz.getMethod("read", TProtocol.class);
			byte[] bytes = thriftResponse.getBody();
			TMemoryBuffer buffer = new TMemoryBuffer(1024);
			buffer.write(bytes);
			TProtocol proto = new TBinaryProtocol(buffer);
			read.invoke(obj, proto);
			buffer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static void main(String[] args) throws Exception {
		TMemoryBuffer buffer1 = new TMemoryBuffer(1024);
		TProtocol prot1 = new TBinaryProtocol(buffer1);
		Content content = new Content();
		content.setId(1);
		content.setPhone("13391919685");
		content.setPrice(12.1);
		content.write(prot1);
		
		ThriftRequest request = new ThriftRequest();
		request.setCommond("thriftGetEmail");
		request.setRequestParam(buffer1.getArray());
		buffer1.close();
		
		Content result = (Content) start(9090, request, Content.class); //远程调用
		System.out.println(result.getPhone());
	}
	
}
