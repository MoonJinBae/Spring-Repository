package com.myweb.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

// @EnableWebMvc : Controller 어노테이션이 셋팅되어 있는 클래스를 Controller로 등록한다. // <annotation-driven/>
@EnableWebMvc

// 스캔할 패키지를 지정한다.  // <context:component-scan base-package="com.my.spring.controller"/>
@ComponentScan(basePackages = {"com.myweb.www.controller", "com.myweb.www.handler"})
public class ServletConfiguration implements WebMvcConfigurer{

	// 정적 파일의 경로를 매핑한다.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		
		// 파일 업로드 경로 추가 (경로에 file:/// 붙여줌)
		registry.addResourceHandler("/upload/**").addResourceLocations("file:///C:\\Users\\UserK\\_myweb\\_java\\fileupload\\"); 
	}

	// Controller의 메서드가 반환하는 jsp의 이름 앞뒤에 경로와 확장자를 붙혀주도록 설정한다.
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
		
	}
	
	// 파일 업로드시 bean으로 multipartResolver 설정 
	@Bean(name = "multipartResolver")
	public MultipartResolver getMultipartResolver() {
		StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
		return multipartResolver;
	}
}
