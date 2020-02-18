package com.hello.service.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.hello.dao.CustomerDao;
import com.hello.pojo.Customer;
import com.hello.service.CustomerService;
import com.hello.utils.PageBean;

@Transactional
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;
	
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	/* 
	 * 添加客户
	 */
	@Override
	public void add(Customer customer) {
		
		customerDao.add(customer);
	}

	@Override
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria dc) {
		
		return customerDao.findByPage(pageCode, pageSize, dc);
	}

	@Override
	public Customer findById(Integer id) {
		
		return customerDao.findById(id);
	}

	@Override
	public void delete(Customer customer) {
		
		customerDao.delete(customer);
	}

}
