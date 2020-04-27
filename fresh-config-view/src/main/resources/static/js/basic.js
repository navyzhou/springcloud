function menberLogin() {
	localStorage.setItem("targetUrl", location.href);
	location.href="login.html";
} 

var cart_infos = undefined;
function getCartInfo() {
	// 发送请求获取购物车商品的信息
	$.get("cart/getInfo", null, function(data) {
		cart_infos = data;
		$("#show_count").text(data.length);
	}, "json");
}

$(function(){
	 $.post("member/check", null, function(data) {
		 var str = "";
		if(data.mno) {
			str += '<div class="login_btn fl"><a href="#">欢迎您 &nbsp;['+data.nickName+']</a><span> | </span>';
			str += '<a href="javascript:menberLogin()">注销</a><span> | </span><a href="register.html">注册</a></div>';
			
			getCartInfo();
		} else {
			str += '<div class="login_btn fl">';
			str += '<a href="../user/login.html" target="_blank">登录</a><span> | </span><a href="../user/register.html"  target="_blank">注册</a></div>';
		}
		str += '<div class="user_link fl"><span> | </span><a href="#">用户中心</a><span> | </span>';
		str += '<a href="front/cart.html" target="_blank">我的购物车</a><span> | </span><a href="#">我的订单</a></div> ';
		$("#head_info").append($(str));
	 }, "json");
 })
