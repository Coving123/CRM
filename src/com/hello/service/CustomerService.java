package com.hello.service;

import org.hibernate.criterion.DetachedCriteria;

import com.hello.pojo.Customer;
import com.hello.utils.PageBean;

public interface CustomerService {

	public void add(Customer customer);

	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria dc);

	public Customer findById(Integer id);

	public void delete(Customer customer);

}
