package com.hello.test;

import com.alibaba.fastjson.annotation.JSONField;

public class Car {
	
	private String cname;
	
	// 我们在某一方，禁止对方再次去调用当前角色
	// 只需要当前角色去调用一次对方即可
	@JSONField(serialize=false)
	private Person person;

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Car [cname=" + cname + ", person=" + person + "]";
	}
	
	

}
