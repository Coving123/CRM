package com.hello.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.hello.pojo.Dict;
import com.hello.service.DictService;
import com.hello.utils.FastJsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DictAction extends ActionSupport implements ModelDriven<Dict> {

	private Dict dict = new Dict();
	
	@Override
	public Dict getModel() {
		return dict;
	}
	
	private DictService dictService;

	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}
	
	// select * form base_dict where dict_type_code = '004'
	
	public String findByCode(){
		
		// 1. 调用 service，查询并获取数据
		List<Dict> dictList = dictService.findByCode(dict.getDict_type_code());

		// 2. 用 fastJson 转换数据
		String jsonStr = FastJsonUtil.toJSONString(dictList);
		
		// 3. 获取 response 对象
		HttpServletResponse response = ServletActionContext.getResponse();
		
		// 4. 直接响应数据到浏览器
		FastJsonUtil.write_json(response, jsonStr);
	
		return NONE; 
	}
	

}
