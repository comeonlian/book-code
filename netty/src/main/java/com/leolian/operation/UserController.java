package com.leolian.operation;

import org.springframework.stereotype.Controller;

import com.leolian.media.Remote;
import com.leolian.proto.ExecuteResultProtobuf.ExecuteResult;
import com.leolian.proto.ResponseProtobuf.Response;
import com.leolian.proto.UserProtobuf.User;

@Controller
public class UserController {
	
	@Remote("saveUser")
	public Object save(User user){
		Response.Builder response = Response.newBuilder();
		ExecuteResult.Builder executeResult = ExecuteResult.newBuilder();
		executeResult.setCmd("saveUser");
		try{
			System.out.println("Controller execute save user, username: "+user.getUserName());
			
			executeResult.setResult("execute success");
		}catch(Exception e){
			e.printStackTrace();
			executeResult.setResult("execute failed");
		}
		return response.setId(1).setResponseContent(executeResult.build().toByteString());
	}
	
}
