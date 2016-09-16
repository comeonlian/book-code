package com.leo.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.leo.crud.dao.EmployeeDao;
import com.leo.crud.entity.Employee;

@Controller
public class SpringmvcTest {
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	/* **********************		Test ConverterService		****************** */
	@RequestMapping(value="testConverterService",method=RequestMethod.POST)
	public String testConverterService(@RequestParam("employee")Employee employee){
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	/* **********************		Test Json		****************** */
	@ResponseBody
	@RequestMapping(value="testJson",method=RequestMethod.POST)
	public Collection<Employee> testJson(){
		System.out.println("Request  from  ajax ....");
		return employeeDao.getAll();
	}
	
	/* **********************		Test HttpMessageConverter		****************** */
	@ResponseBody
	@RequestMapping(value="testHttpMessageConverter")
	public String testHttpMessageConverter(@RequestBody String body){
		System.out.println(body);
		return "hello world "+new Date();
	}
	
	/* **********************		Test ResponseEntity		****************** */
	@ResponseBody
	@RequestMapping(value="testResponseEntity")
	public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException{
		// 读文件
		InputStream in = session.getServletContext().getResourceAsStream("/files/abc.txt");
		byte[] body = new byte[in.available()];
		in.read(body);
		// 构造Headers
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Disposition", "attachment;filename=xyz.txt");
		// StatusCode
		HttpStatus statusCode = HttpStatus.OK;
		
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
		return response;
	}
	
	/* **********************		Test i18n		****************** */
	@RequestMapping(value="i18n")
	public String testI18n(Locale locale){
		String val = messageSource.getMessage("i18n.username", null, locale);
		System.out.println(val);
		return "i18n";
	}
	
	/* **********************		Test FileUpLoad		****************** */
	@RequestMapping(value="testFileUpLoad")
	public String testFileUpLoad(@RequestParam("desc")String desc,
					@RequestParam("file")MultipartFile file){
		System.out.println("desc: "+desc);
		System.out.println("OriginalFilename: "+file.getOriginalFilename());
		System.out.println("Size: "+file.getSize());
		return "success";
	}
	
	/* **********************		Test testExceptionHandlerExceptionResolver		****************** */
	@RequestMapping(value="testExceptionHandlerExceptionResolver")
	public String testExceptionHandlerExceptionResolver(@RequestParam("i")Integer i){
		Integer val = 10 / i;
		System.out.println("val: "+val);
		return "success";
	}
	
	/*@ExceptionHandler({ArithmeticException.class})
	public ModelAndView exceptionHandler(Exception ex){
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("exception", ex);
		System.out.println("出异常了: "+ex);
		return mv;
	}*/
	
	/*@ExceptionHandler({RuntimeException.class})
	public ModelAndView exceptionHandler2(Exception ex){
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("exception", ex);
		System.out.println("出异常了-2: "+ex);
		return mv;
	}*/
	
	/* **********************		Test ResponseStatusExceptionResolver		****************** */
	@RequestMapping(value="testResponseStatusExceptionResolver")
	public String testResponseStatusExceptionResolver(@RequestParam("i")Integer i){
		if(i==15)
			throw new UserPasswordException();
		System.out.println("Test ResponseStatusExceptionResolver...");
		return "success";
	}
	
	/* **********************		Test DefaultHandlerExceptionResolver		****************** */
	@RequestMapping(value="testDefaultHandlerExceptionResolver",method=RequestMethod.POST)
	public String testDefaultHandlerExceptionResolver(){
		System.out.println("Test DefaultHandlerExceptionResolver...");
		return "success";
	}
	
	/* **********************		Test SimpleMappingExceptionResolver		****************** */
	@RequestMapping(value="testSimpleMappingExceptionResolver")
	public String testSimpleMappingExceptionResolver(@RequestParam("i")Integer i){
		String[] arr = new String[10];
		System.out.println(arr[i]);
		return "success";
	}
	
	
}
