package com.hello.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hello.pojo.User;
import com.hello.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

// XxxAction 类就相当于是前端控制器的 Controller 类
// 继承 ActionSupport 其实是为了更方便去操作当前这个 action 类，不用手动重写 execute() 方法
// 实现  ModelDriven<> 接口，就是为了“自动”封装数据
public class UserAction extends ActionSupport implements ModelDriven<User> {

	private User user = new User();
	
	@Override
	public User getModel() {
		return user;
	}
	
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 用户退出
	 * @return
	 */
	public String exit(){
		
		// 从 session 中，移除掉已存在的用户即可
		ServletActionContext.getRequest().getSession().removeAttribute("exsitUser");
		
		// 如果用户退出成功，那么应该跳转登录页面
		return LOGIN;
	}
	
	/**
	 * 用户登录
	 * @return
	 */
	public String login(){
		
		// 1. 调用 service
		User exsitUser = userService.login(user);
		
		// 2. 判断 exsitUser 是否为空
		if(exsitUser == null){
			System.out.println("用户名或密码不正确，检查后再登录...");
			return LOGIN;
		} else {
			// 如果登录成功，则需要把用户的实例放在 session 域中
			// 其他页面也能共享当前用户数据
			ServletActionContext.getRequest().getSession().setAttribute("exsitUser", exsitUser);
			
			// 如果登录成功了之后，我们应该是去首页
			return "loginSuccess";
		}
	}


	/**
	 * 验证用户名
	 * @return
	 */
	public String checkCode(){
		
		// 1. 调用 UserService 中的方法，操作验证
		User u = userService.checkCode(user.getUser_name());
		
		// 2. 如果服务端需要返回数据到页面，则可以使用 response
		// 思路：servletActionContext --> response --> getWrite --> print
		// 将上面获取的 user 进行判断，如果不为 null 则返回 no，否则返回 yes
		HttpServletResponse response = ServletActionContext.getResponse();
		// 如果要以响应的方式响应页面，还需要设置字符编码
		response.setContentType("text/html; charset=utf-8");
		
		try {
			// 获取一个输出流
			PrintWriter out = response.getWriter();
			
			if(u != null){
				out.print("no");
			} else {
				out.print("yes");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return NONE;
	}
	
	
	/**
	 * 注册用户
	 * @return
	 */
	public String register(){
		
		// System.out.println("准备注册用户");
		// 注册的目的：为数据表添加一个新的用户
		
		// 调用 service 来实现注册操作
		userService.save(user);
		
		// 注册成功之后，则直接去登录
		return LOGIN;
		
	}
	



}
