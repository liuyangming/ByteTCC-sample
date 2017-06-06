package com.bytesvc.consumer.main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;

/**
 * 注意：<br />
 * 支持springcloud的0.4.0版本目前仍在开发之中, 与springcloud相关的功能可能会存在很多问题, 请谨慎使用.
 */
@ImportResource({ "classpath:bytetcc-supports-springcloud.xml", "classpath:spring-mybatis.xml" })
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients("com.bytesvc.feign")
@SpringBootApplication(scanBasePackages = "com.bytesvc.consumer")
public class SampleConsumerMain {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SampleConsumerMain.class).web(true).run(args);
	}

}
