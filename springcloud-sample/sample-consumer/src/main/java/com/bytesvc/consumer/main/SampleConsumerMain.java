package com.bytesvc.consumer.main;

import org.bytesoft.bytetcc.supports.springcloud.config.SpringCloudConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@Import(SpringCloudConfiguration.class)
@MapperScan("com.bytesvc.consumer.dao")
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients("com.bytesvc.feign")
@SpringBootApplication(scanBasePackages = "com.bytesvc.consumer")
@EnableCircuitBreaker
@EnableHystrix
@EnableAutoConfiguration
public class SampleConsumerMain implements BeanFactoryAware {
	private static BeanFactory beanFactory;

	public static void main(String[] args) {
		new SpringApplicationBuilder(SampleConsumerMain.class).bannerMode(Banner.Mode.OFF).run(args);
		System.out.println("springcloud-sample-consumer started!");
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory factory) throws BeansException {
		beanFactory = factory;
	}

}
