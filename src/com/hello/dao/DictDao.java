package com.hello.dao;

import java.util.List;

import com.hello.pojo.Dict;

public interface DictDao {

	List<Dict> findByCode(String dict_type_code);

}
