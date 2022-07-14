package cn.qb.store.configurer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.qb.store.interceptor.LoginInterceptor;

@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//黑名单
		List<String> black = new ArrayList<String>();
		black.add("/user/**");
		black.add("/address/**");
		black.add("/web/**");
		//白名单
		List<String> white = new ArrayList<String>();
		white.add("/user/reg.do");
		white.add("/user/login.do");
		white.add("/web/login.html");
		white.add("/web/register.html");
		//注册
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns(black)
		.excludePathPatterns(white);
	}

}
