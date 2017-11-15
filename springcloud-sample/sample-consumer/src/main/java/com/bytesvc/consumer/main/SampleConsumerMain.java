package com.bytesvc.consumer.main;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ImportResource;

@ImportResource({ "classpath:bytetcc-supports-springcloud.xml", "classpath:spring-mybatis.xml" })
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients("com.bytesvc.feign")
@SpringBootApplication(scanBasePackages = "com.bytesvc.consumer")
@EnableCircuitBreaker
@EnableHystrix
@EnableHystrixDashboard
public class SampleConsumerMain {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SampleConsumerMain.class).bannerMode(Banner.Mode.OFF).web(true).run(args);
		System.out.println("springcloud-sample-consumer started!");
	}

}
