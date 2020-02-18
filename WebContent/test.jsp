
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
    <head>
		<meta charset="utf-8">
		<title>test</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
 		<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all" >
	  	<link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome-4.7.0/css/font-awesome.css" />
	  	<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/login.css" media="all" />
	</head>
	
	<body class="beg-login-bg">
	<div class="beg-login-box">
		
		
		<div class="beg-login-main">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>图形验证码</legend>
		</fieldset>
		
		<form class="layui-form" action="">
		
		
		
			<div class="layui-form-item">
			        <input type="text" name="imgVercode" id="imgVercode" lay-verify="imgVercode" autocomplete="off" placeholder="请输入验证码" style="width:155px;float: left;" class="layui-input">
			    	<div id="get-vercode" class="layui-inline" style="float: right;height: 38px;"></div>
		    </div>
		    <div class="layui-form-item">
			    <div class="layui-input-block">
			      <a class="layui-btn Link_btn" id="sub-img-code">立即提交</a>
			    </div>
			</div>
		</form>
		
		</div>
		</div>
	</body>
	
		
		<script type="text/javascript" src="layui_menu/layui.js"></script>
		<script type="text/javascript" src="js/imgcodeVerify.js"></script>
		<script>
		var verifyCode = new GVerify("get-vercode");
		
		layui.use(['layer','form','jquery'], function() {
			var layer = layui.layer, $ = layui.jquery, form = layui.form();

			$(".Link_btn").click(function(){
		        addlink();
		    });
			
			function addlink(){
				var res = verifyCode.validate(document.getElementById("imgVercode").value);
				if(res){
					layer.alert('Hello');
				 }else{
					layer.alert('NO');
				 }
			}
			
			});  	
		</script>
</html>




























