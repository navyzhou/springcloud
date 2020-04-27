package com.yc.fresh.zuul.filter;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Component;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * zuul还能进行请求过滤，那么我们进行一下token校验来演示一下，首先我们需要先新建一个TokenFilter类来继承ZuulFilter这个类，实现它的四个接口
 * 这里声明一下zuul过滤器执行网络请求发生的异常，过滤器里面是不能直接将try-catch捕捉的异常抛出给页面的。
 * 应用程序抛出的异常是可以返回出的需解决办法就是在catch里面用context.set()方法返回给页面。
 * 源辰信息
 * @author navy
 * @2019年8月9日
 */
@Component
public class TokenFilter extends ZuulFilter {
	//四种类型：pre,routing,error,post
	//pre：主要用在路由映射的阶段是寻找路由映射表的
	//routing：具体的路由转发过滤器是在routing路由器，具体的请求转发的时候会调用
	//error：一旦前面的过滤器出错了，会调用error过滤器。
	//post：当routing，error运行完后才会调用该过滤器，是在最后阶段的
	@Override
	public String filterType() {
		return "pre";
	}

	//自定义过滤器执行的顺序，数值越大越靠后执行，越小就越先执行
	@Override
	public int filterOrder() {
		return 0;
	}

	//控制过滤器生效不生效，可以在里面写一串逻辑来控制
	@Override
	public boolean shouldFilter() {
		return true;
	}

	//执行过滤逻辑
	@Override
	public Object run() {
		/*RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();
        String token = request.getParameter("token");
        if (token == null){
        	response.setContentType("text/html;charset=utf-8");
        	context.setRequest(request);
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            context.setResponseBody("权限不足...");

            return null;
        }*/
		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//RequestContext requestContext = RequestContext.getCurrentContext();
		//requestContext.addZuulRequestHeader("X-AUTH-ID",authentication.getPrincipal().toString());
		return null;
	}
}