package com.hello.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hello.dao.DictDao;
import com.hello.pojo.Dict;

public class DictDaoImpl extends HibernateDaoSupport  implements DictDao {

	@Override
	public List<Dict> findByCode(String dict_type_code) {
		
		return (List<Dict>) this.getHibernateTemplate().find("from Dict where dict_type_code = ?", dict_type_code);
	}

}
