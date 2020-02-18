package com.hello.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hello.dao.DictDao;
import com.hello.pojo.Dict;
import com.hello.service.DictService;

@Transactional
public class DictServiceImpl implements DictService {

	private DictDao dictDao;

	public void setDictDao(DictDao dictDao) {
		this.dictDao = dictDao;
	}

	@Override
	public List<Dict> findByCode(String dict_type_code) {

		return dictDao.findByCode(dict_type_code);
	}
	
	
}
