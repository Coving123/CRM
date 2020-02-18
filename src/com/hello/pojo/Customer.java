package com.hello.pojo;

import java.io.Serializable;

public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer cust_id; // 客户编号(主键)
	private String cust_name; // 客户名称(公司名称)
	private Integer cust_user_id; // 负责人id
	private Integer cust_create_id; // 创建人id

	private String cust_linkman; // 联系人
	private String cust_phone; // 固定电话
	private String cust_mobile; // 移动电话

	// 关联字典
	private Dict source;
	private Dict industry;
	private Dict level;
	
	// 保存上传的路径
	private String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Dict getSource() {
		return source;
	}

	public void setSource(Dict source) {
		this.source = source;
	}

	public Dict getIndustry() {
		return industry;
	}

	public void setIndustry(Dict industry) {
		this.industry = industry;
	}

	public Dict getLevel() {
		return level;
	}

	public void setLevel(Dict level) {
		this.level = level;
	}

	public Integer getCust_id() {
		return cust_id;
	}

	public void setCust_id(Integer cust_id) {
		this.cust_id = cust_id;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public Integer getCust_user_id() {
		return cust_user_id;
	}

	public void setCust_user_id(Integer cust_user_id) {
		this.cust_user_id = cust_user_id;
	}

	public Integer getCust_create_id() {
		return cust_create_id;
	}

	public void setCust_create_id(Integer cust_create_id) {
		this.cust_create_id = cust_create_id;
	}

	public String getCust_linkman() {
		return cust_linkman;
	}

	public void setCust_linkman(String cust_linkman) {
		this.cust_linkman = cust_linkman;
	}

	public String getCust_phone() {
		return cust_phone;
	}

	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}

	public String getCust_mobile() {
		return cust_mobile;
	}

	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}

	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", cust_name=" + cust_name + ", cust_user_id=" + cust_user_id
				+ ", cust_create_id=" + cust_create_id + ", cust_linkman=" + cust_linkman + ", cust_phone=" + cust_phone
				+ ", cust_mobile=" + cust_mobile + ", source=" + source + ", industry=" + industry + ", level=" + level
				+ "]";
	}
	
	

}
