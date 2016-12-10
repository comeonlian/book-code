package com.leolian.protobuf;

import org.junit.Test;

import com.leolian.operation.UserService;
import com.leolian.proto.UserProtobuf.User;
import com.leolian.proto.client.ProtobufClient;


public class TestProtobuf {
	
	@Test
	public void testProtobuf() throws Exception{
		ProtobufClient client = new ProtobufClient("localhost", 8999);
		UserService service = new UserService();
		service.client = client;
		User user = service.save();
		System.out.println(user.getUserName());
	}
	
}
