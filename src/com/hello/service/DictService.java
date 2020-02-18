package com.hello.service;

import java.util.List;

import com.hello.pojo.Dict;

public interface DictService {

	List<Dict> findByCode(String dict_type_code);

}
