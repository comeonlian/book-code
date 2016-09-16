package com.leo.crud.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.leo.crud.dao.DepartmentDao;
import com.leo.crud.dao.EmployeeDao;
import com.leo.crud.entity.Employee;

@Controller
public class RestfulCurd {
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private DepartmentDao departmentDao;
	
	/* ********************		select 		******************** */
	
	@RequestMapping(value="/emps",method=RequestMethod.GET)
	public String list(Map<String, Object> map){
		map.put("employees", employeeDao.getAll());
		return "list";
	}
	
	/* ********************		add 		******************** */
	
	@RequestMapping(value="/emp",method=RequestMethod.GET)
	public String input(Map<String, Object> map){
		Map<String,String> genders = new HashMap<String,String>();
		genders.put("1", "Male");
		genders.put("0", "Female");
		map.put("departments", departmentDao.getDepartments());
		map.put("genders", genders);
		map.put("employee", new Employee());
		return "input";
	}
	
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public String save(Employee employee){
		System.out.println(employee);
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	/* ********************		delete 		******************** */
	@RequestMapping(value="/emp/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id")Integer id){
		employeeDao.delete(id);
		return "redirect:/emps";
	}
	
	/* ********************		update 		******************** */
	@RequestMapping(value="emp/{id}",method=RequestMethod.GET)
	public String input(@PathVariable("id")Integer id,Map<String,Object> map){
		Map<String,String> genders = new HashMap<String,String>();
		genders.put("1", "Male");
		genders.put("0", "Female");
		map.put("employee", employeeDao.get(id));
		map.put("departments", departmentDao.getDepartments());
		map.put("genders", genders);
		return "input";
	}
	
	@ModelAttribute
	public void getEmployee(@RequestParam(value="id",required=false)Integer id,
						Map<String,Object> map){
		if(null!=id){
			map.put("emp", employeeDao.get(id));
		}
	}
	
	@RequestMapping(value="/emp",method=RequestMethod.PUT)
	public String update(@ModelAttribute(value="emp")Employee employee){
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
}
