package com.leolian.thrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;
import org.junit.Test;

import com.leolian.thrift.client.ThriftClient;
import com.leolian.thrift.demo.Content;
import com.leolian.thrift.demo.ThriftRequest;

public class ThriftTest {
	
	@Test
	public void testThrift() throws Exception{
		ThriftClient client = new ThriftClient("localhost", 8999);
		
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
		
		Content result = (Content) client.run(request, Content.class); //远程调用
		System.out.println(result.getPhone());
	}
	
}

