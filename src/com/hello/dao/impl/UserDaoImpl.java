package com.hello.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hello.dao.UserDao;
import com.hello.pojo.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	// HibernateTemplate 与 JdbcTemplate 原理是一样的
	
	@Override
	public User checkCode(String user_name) {
		
		// 注意，我们在 Hibernate 中不写纯的 SQL 语句，如果要用语句来查则使用 HQL
		// HQL 是面向对象的操作方式：from User
		
		List<User> userList = (List<User>) this.getHibernateTemplate().find("from User where user_name = ?", user_name);
		
		// 正常情况下，一个系统中，不可能有两个用户的名字和密码一模一样
		// 所以，上面获取的 userList 集合，我们最多取一个值
		if(userList != null && userList.size() > 0){
			// 如果有值，则返回第一个下标对应的值即可，就是已存在的用户
			return userList.get(0);
		}
		
		return null;
	}

	@Override
	public void save(User user) {
		
		this.getHibernateTemplate().save(user);
		
	}

	@Override
	public User login(User user) {
		
		// 如果要验证某个数据，其实就是查询
		
		// 1. 设置查询条件，离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		
		// 2. 设置查询细节
		// Restrictions 主要是用来设置查询条件中的某个值的
		// eq() 等于方法
		dc.add(Restrictions.eq("user_name", user.getUser_name()));
		dc.add(Restrictions.eq("user_password", user.getUser_password()));
		dc.add(Restrictions.eq("user_state", "1"));
		
		// 3. 执行查询
		List<User> userList = (List<User>) this.getHibernateTemplate().findByCriteria(dc);
		
		// 4. 判断
		if(userList != null && userList.size() > 0){
			return userList.get(0);
		}
		
		return null;
	}

}
