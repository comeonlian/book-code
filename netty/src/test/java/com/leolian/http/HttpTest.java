package com.leolian.http;

import org.junit.Test;

import com.leolian.http.client.HttpClient;
import com.leolian.http.entity.Person;
import com.leolian.http.entity.RequestParameter;
import com.leolian.utils.JsonUtils;

public class HttpTest {
	
	@Test
	public void testHttp() throws Exception {
		HttpClient client = new HttpClient("127.0.0.1", 8999);
		RequestParameter parameter = new RequestParameter();
		Person person = new Person();
		person.setId(121);
		person.setName("KKKKK");
		parameter.setCommand("getPersonMail");
		parameter.setParameter(person);
		
		Object result = client.run(parameter);
		Person resultPerson = (Person) result;
		System.out.println("Client Get :"+resultPerson.getName());
	}
	
}
