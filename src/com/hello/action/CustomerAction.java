package com.hello.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.hello.pojo.Customer;
import com.hello.pojo.Dict;
import com.hello.pojo.User;
import com.hello.service.CustomerService;
import com.hello.utils.PageBean;
import com.hello.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private Customer customer = new Customer();

	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public Customer getModel() {
		// 返回自动封装的数据
		return customer;
	}
	
	// 删除客户
	public String delete(){
		
		// 思路：先找到，再干掉
		
		// 获取 customer 的 id 值
		Integer id = customer.getCust_id();
		
		// 调用 service 查找对应的客户
		customer = customerService.findById(id);
		
		// 调用 service 删除对应的客户
		customerService.delete(customer);
		
		// 操作完之后，跳回列表页面，展示剩余客户
		return "delete";
	}
	
	// -------------- 文件上传 -----------
	
	// 保存上传文件
	private File upload;
	
	// 上传文件的名字
	private String uploadFileName;
	
	// 设置上传文件的 MIME 类型
	private String uploadContentType;
	
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	//---------------------------------

	// 跳转添加客户页面
	public String addUI() {

		return "addUI";
	}

	// 添加客户
	public String save() throws IOException {
		
		if(uploadFileName != null){

			// 处理文件的名字
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			System.out.println("文件名：" + uuidName);
			
			// 指定上传的位置
			String path = "D:/crm_file/";
			
			// 创建 file 对象
			File file = new File(path + uuidName);
			System.out.println("file--->" + file);
			
			// 写出去，对拷流
			FileUtils.copyFile(upload, file);
			//FileUtils.copyFileToDirectory(upload, file);
			
			// 设置路径
			customer.setFilePath(path + uuidName);
		
		}

		// 1. 调用 service 进行添加操作
		// 单独把你填写好的数据，存储到数据库中
		customerService.add(customer);

		// 2. 当你保存好客户的数据之后，最好是跳转到“客户列表”中把所有的数据都展示出来
		return "save";
	}

	// 编辑客户

	// 1）设置当前页，也就是从哪一页开始显示
	private Integer pageCode = 1;

	public void setPageCode(Integer pageCode) {

		if (pageCode == null) {
			pageCode = 1;
		}

		this.pageCode = pageCode;
	}

	// 2）设置当前页显示的条数
	private Integer pageSize = 2;

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	// 展示所有客户
	public String findByPage() {

		// 1. 调用 service 查询所有的数据
		// 需要传递三个参数：从哪一页开始，显示多少条，查询的条件是什么
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);

		// 拼接查询的条件
		// 客户名称
		String cust_name = customer.getCust_name();

		if (cust_name != null && !cust_name.trim().isEmpty()) {

			// 如果输入的客户名不为空则进行拼接
			dc.add(Restrictions.like("cust_name", "%" + cust_name + "%"));
		}

		// 客户级别
		Dict level = customer.getLevel();

		if (level != null && !level.getDict_id().trim().isEmpty()) {

			// 如果输入的客户级别不为空则进行拼接
			dc.add(Restrictions.eq("level.dict_id", level.getDict_id()));
		}

		// 客户来源
		Dict source = customer.getIndustry();

		if (source != null && !source.getDict_id().trim().isEmpty()) {

			dc.add(Restrictions.eq("source.dict_id", source.getDict_id()));

		}

		// ---------------- 分页 --------------------------

		// 2. 获取对应的分页对象
		PageBean<Customer> pb = customerService.findByPage(pageCode, pageSize, dc);

		// 3. 设置到 struts2 的值栈中
		ValueStack vs = ActionContext.getContext().getValueStack();

		// 4. 设置返回页面的数据
		vs.set("pageBean", pb);

		return "page";
	}

}
