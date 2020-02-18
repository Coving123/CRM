package com.hello.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hello.dao.CustomerDao;
import com.hello.pojo.Customer;
import com.hello.utils.PageBean;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	@Override
	public void add(Customer customer) {
		
		this.getHibernateTemplate().save(customer);
		
	}

	@Override
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria dc) {
		
		PageBean<Customer> pb = new PageBean<Customer>();
		
		pb.setPageCode(pageCode);
		pb.setPageSize(pageSize);
		
		// 如果我们需要查询总记录数的话，select count(*) from customer
		dc.setProjection(Projections.rowCount());
		
		// 查询的是总条数
		List<Number> numList = (List<Number>) this.getHibernateTemplate().findByCriteria(dc);
		
		// 获取条数
		if (numList != null && numList.size() > 0) {
			// 因为 numList 中的值是 Number 类型，所以要转成 int
			int totalCount = numList.get(0).intValue();
			
			// 把获取到的总条数设置回去
			pb.setTotalCount(totalCount);
		}
		
		// 当我们使用完 Projection 的时候，需要清空
		dc.setProjection(null);
		
		// 进行分页查询
		// 分页公式
		/*
			limit start pageSize;
			start：就是当前页的起始索引。
			pageSize：就是每页的条数。
			pageCode：就是当前页
			公式：start = (pageCode-1) * pageSize 
		 */
		List<Customer> customerList = (List<Customer>) this.getHibernateTemplate().findByCriteria(dc, (pageCode-1) * pageSize, pageSize);
		
		// 设置回给 pageBean
		pb.setBeanList(customerList);
		
		return pb;
	}

	@Override
	public Customer findById(Integer id) {
		
		return this.getHibernateTemplate().get(Customer.class, id);
	}

	@Override
	public void delete(Customer customer) {
		
		this.getHibernateTemplate().delete(customer);
		
	}

}
