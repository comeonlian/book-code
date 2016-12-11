package com.leolian.operation;

import com.leolian.proto.RequestParamProtobuf.RequestParam;
import com.leolian.proto.ResponseProtobuf.Response;
import com.leolian.proto.UserProtobuf.User;
import com.leolian.proto.client.ProtobufClient;

/**
 * service层
 * @author Lian
 */
public class UserService {
	public ProtobufClient client;
	
	public Response save() throws Exception{
		User.Builder builder = User.newBuilder();
		builder.setId(1001);
		builder.setUserName("Leo");
		builder.setPhone("13391919685");
		RequestParam.Builder requestParam = RequestParam.newBuilder();
		requestParam.setCmd("saveUser");
		requestParam.setRequestContent(builder.build().toByteString());
		return client.run(requestParam);
	}
	
}
