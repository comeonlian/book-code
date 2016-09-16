package com.leo.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.leo.crud.entity.Department;
import com.leo.crud.entity.Employee;

@Component
public class EmployeeConverter implements Converter<String, Employee> {

	public Employee convert(String source) {
		if(null!=source){
			String[] vals = source.split("-");
			if(null!=vals&&vals.length==4){
				Department department = new Department();
				department.setId(Integer.valueOf(vals[3]));
				Employee employee = new Employee();
				employee.setLastName(vals[0]);
				employee.setEmail(vals[1]);
				employee.setGender(Integer.valueOf(vals[2]));
				employee.setDepartment(department);
				System.out.println(source+"--converter--"+employee);
				return employee;
			}
		}
		return null;
	}

}
