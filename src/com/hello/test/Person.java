package com.hello.test;

import com.alibaba.fastjson.annotation.JSONField;

public class Person {
	
	private String pname;
	

	private Car car;

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "Person [pname=" + pname + ", car=" + car + "]";
	}
	
	

}
