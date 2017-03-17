package com.leolian.operation;

import org.springframework.stereotype.Controller;

import com.leolian.http.entity.Person;
import com.leolian.media.Remote;
import com.leolian.proto.ExecuteResultProtobuf.ExecuteResult;
import com.leolian.proto.ResponseProtobuf.Response;
import com.leolian.proto.UserProtobuf.User;
import com.leolian.thrift.demo.Content;

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
	
	
	@Remote("getPersonMail")
	public Object getPersonMail(Person person){
		System.out.println("Execute getPersonMail ,Person id:"+person.getId()+" name:"+person.getName());
		person.setName("Response --- "+person.getName());
		return person;
	}
	
	
	@Remote("thriftGetEmail")
	public Object getEmail(Content content){
		System.out.println("From client:"+content.getId()+" - "+content.getPhone());
		content.setId(2);
		content.setPhone("18080808080");
		content.setPhoneIsSet(true);
		return content;
	}
	
}
