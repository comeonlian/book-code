package com.leo.springmvc.handler;

import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.leo.springmvc.entity.User;

//@SessionAttributes(value={"user"})
@Controller
@RequestMapping("/springmvc")
public class SpringMVCTest {
	private static final String SUCCESS = "success";
	
	
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping(){
		System.out.println("testRequestMapping");
		return SUCCESS;
	}
	
	
	@RequestMapping(value="/testMethod", method=RequestMethod.POST)
	public String testMethod(){
		System.out.println("Test method");
		return SUCCESS;
	}
	
	//Accept-Language=zh-CN,zh;q=0.8
	@RequestMapping(value="/testParamsAndHeaders",
			params={"username", "age!=10"}, headers={"Accept-Language=zh-CN,zh;q=0.8"})
	public String testParamsAndHeaders(){
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}
	
	
	@RequestMapping("/testAntUrl/*/suffix")
	public String testAntUrl(){
		System.out.println("testAntUrl");
		return SUCCESS;
	}
	
	
	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id")Integer id){
		System.out.println(id);
		return SUCCESS;
	}
	/* *******************  Rest *******************/
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.GET)
	public String testRestGet(@PathVariable("id")Integer id){
		System.out.println("TestRest  GET:"+id);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest",method=RequestMethod.POST)
	public String testRestPost(){
		System.out.println("TestRest  POST:");
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.PUT)
	public String testRestPut(@PathVariable("id")Integer id){
		System.out.println("TestRest  Put:"+id);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.DELETE)
	public String testRestDelete(@PathVariable("id")Integer id){
		System.out.println("TestRest  Delete:"+id);
		return SUCCESS;
	}
	
	/* *******************  Test RequestParam *******************/
	@RequestMapping(value="/testRequestParam")
	public String testRequestParam(@RequestParam(value="username")String un,
					@RequestParam(value="age",required=false, defaultValue="0")int age){
		System.out.println("Test RequestParam:"+un+",age:"+age);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value="Accept-Language")String lang){
		System.out.println("Test RequestHeader:"+lang);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testCookieValue")
	public String testCookieValue(@CookieValue(value="JSESSIONID")String cookie){
		System.out.println("Test CookieValue:"+cookie);
		return SUCCESS;
	}
	
	/* *******************  Test Pojo *******************/
	@RequestMapping(value="/testPojo")
	public String testPojo(User user){
		System.out.println("Test Pojo:"+user);
		return SUCCESS;
	}
	
	/* *******************  Test Servlet  API *******************/
	@RequestMapping(value="/testServletApi")
	public void testServletApi(HttpServletRequest request,HttpServletResponse response,Writer out) throws Exception{
		out.write("Hello SpringMVC");
		//System.out.println("Test ServletApi:"+user);
		//return SUCCESS;
	}
	
	/* *******************  Test ModelAndView *******************/
	@RequestMapping(value="/testModelAndView")
	public ModelAndView testModelAndView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("success");
		mav.addObject("time", new Date());
		return mav;
	}
	
	/* *******************  Test Map *******************/
	@RequestMapping(value="/testMap")
	public String  testMap(Map<String,Object> map) {
		System.out.println("Test Map ...");
		map.put("array", Arrays.asList(new String[]{"Tom", "Jack"}));
		return SUCCESS;
	}
	
	/* *******************  Test SessionAttributes *******************/
	@RequestMapping(value="/testSessionAttributes")
	public String  testSessionAttributes(Map<String,Object> map) {
		System.out.println("Test SessionAttributes ...");
		User user = new User("LeoLian","12345");
		map.put("user", user);
		return SUCCESS;
	}
	
	/* *******************  Test ModelAttribute *******************/
	/*@ModelAttribute
	public void testModelAttribute(@RequestParam(value="id",required=false)Integer id,
			Map<String,Object> map){
		if(null!=id){
			//从数据库中取数据
			User user = new User("kkkkk", "12345");
			map.put("user", user);
		}
	}*/
	
	// User [username=xkxkx, password=12345, address=null]
	@RequestMapping(value="/testModelAttribute")
	public String  testModelAttribute(@ModelAttribute("user")User user) {
		System.out.println(user);
		return SUCCESS;
	}
	
	/* *******************  Test ViewAndViewResovler *******************/
	@RequestMapping(value="/testViewAndViewResovler")
	public String  testViewAndViewResovler() {
		System.out.println("Test ViewAndViewResovler ...");
		return SUCCESS;
	}
	
	/* *******************  Test HelloView *******************/
	@RequestMapping(value="/testHelloView")
	public String  testHelloView() {
		System.out.println("Test HelloView ...");
		return "helloView";
	}
	
	/* *******************  Test Redirect *******************/
	@RequestMapping(value="/testRedirect")
	public String  testRedirect() {
		System.out.println("Test Redirect ...");
		return "redirect:/index.jsp";
	}
	
	
}
