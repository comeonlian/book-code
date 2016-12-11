package com.leolian.protobuf;

import org.junit.Test;

import com.google.protobuf.ByteString;
import com.leolian.operation.UserService;
import com.leolian.proto.ExecuteResultProtobuf.ExecuteResult;
import com.leolian.proto.ResponseProtobuf.Response;
import com.leolian.proto.client.ProtobufClient;


public class TestProtobuf {
	
	@Test
	public void testProtobuf() throws Exception{
		ProtobufClient client = new ProtobufClient("localhost", 8999);
		UserService service = new UserService();
		service.client = client;
		Response response = service.save();
		ByteString responseConent = response.getResponseContent();
		ExecuteResult executeResult = ExecuteResult.parseFrom(responseConent);
		System.out.println("cmd: "+executeResult.getCmd()+" ,result: "+executeResult.getResult());
	}
	
}
