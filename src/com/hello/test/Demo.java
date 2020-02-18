package com.hello.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hello.pojo.Customer;

public class Demo {
	
	@Test
	public void fun4(){
	
		Person p = new Person();
		p.setPname("xiaosan");
		
		Car c = new Car();
		c.setCname("BMW");
		
		p.setCar(c);
		c.setPerson(p);
		
		// 在使用的时候，如果我们以 A 作为主要操作对象
		// 则需要在 B 中把 A 进行设置
		String jsonStr = JSON.toJSONString(p, SerializerFeature.DisableCircularReferenceDetect);
		System.out.println(jsonStr);
	}
	
	@Test
	public void fun3(){
		
		Customer c = new Customer();
		c.setCust_id(10);
		c.setCust_name("周星驰");
		c.setCust_phone("13800138000");
		
		List<Customer> cList = new ArrayList<>();
		cList.add(c);
		cList.add(c);
		
		// 转换
		// 注意：json 数据的 key 都是 String 类型
		// 如果是按照以前的写法，会出现 $ref 这样的 key 值，前台页面不能直接获取并解析
		// String jsonStr = JSON.toJSONString(cList);
		String jsonStr = JSON.toJSONString(cList, SerializerFeature.DisableCircularReferenceDetect);
		System.out.println(jsonStr);
	}
	
	@Test
	public void fun2(){
		
		Customer c = new Customer();
		c.setCust_id(10);
		c.setCust_name("周星驰");
		c.setCust_phone("13800138000");
		
		Customer c2 = new Customer();
		c2.setCust_id(11);
		c2.setCust_name("吴孟达");
		c2.setCust_phone("13800138001");
		
		List<Customer> cList = new ArrayList<>();
		cList.add(c);
		cList.add(c2);
		
		// 转换
		// 注意：json 数据的 key 都是 String 类型
		String jsonStr = JSON.toJSONString(cList);
		System.out.println(jsonStr);
	}

	
	@Test
	public void fun(){
		
		Customer c = new Customer();
		c.setCust_id(10);
		c.setCust_name("周星驰");
		c.setCust_phone("13800138000");
		
		// 转换
		// 注意：json 数据的 key 都是 String 类型
		String jsonStr = JSON.toJSONString(c);
		System.out.println(jsonStr);
	}

}
