package com.meteor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

@SpringBootApplication

public class SpringStudy1905Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringStudy1905Application.class, args);
	}
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		
		String messageS[] = {"message/viewText", "message/coffee/coffee"};
		messageSource.setBasenames(messageS);
		return messageSource;
	}

	 @Bean(name = AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
		public ApplicationEventMulticaster createSimpleApplicationEventMulticaster(ThreadPoolTaskExecutor taskExecutor ) {
			SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
			multicaster.setTaskExecutor(taskExecutor);
			return multicaster;
		}
		
		@Bean(name="eventExecutor")
		public ThreadPoolTaskExecutor createTaskExecutor() {
			ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
			executor.setCorePoolSize(1);
			executor.setMaxPoolSize(1);
			return executor;
		} 
		@Bean
		public ViewResolver beanNameViewResolver() {
			BeanNameViewResolver resolver = new BeanNameViewResolver();
			return resolver;
		}
		
//		
//		@Bean
//		public ResourceBundleViewResolver viewResolver() {
//			ResourceBundleViewResolver viewResolver = new ResourceBundleViewResolver();
//			viewResolver.setOrder(0);
//			viewResolver.setBasename("view");
//			return viewResolver;
//		}
//		
}
