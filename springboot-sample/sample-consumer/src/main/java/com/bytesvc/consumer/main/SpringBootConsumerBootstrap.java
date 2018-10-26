package com.bytesvc.consumer.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.bytesvc.consumer" })
public class SpringBootConsumerBootstrap {
	static Logger logger = LoggerFactory.getLogger(SpringBootConsumerBootstrap.class);

	public static void main(String[] args) throws Throwable {
		SpringApplication application = new SpringApplication(SpringBootConsumerBootstrap.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
		logger.info("springboot consumer start success");
	}

}
