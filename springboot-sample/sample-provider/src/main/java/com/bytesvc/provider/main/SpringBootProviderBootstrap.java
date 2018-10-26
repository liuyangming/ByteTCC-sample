package com.bytesvc.provider.main;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.bytesvc.provider" })
public class SpringBootProviderBootstrap {
	static Logger logger = LoggerFactory.getLogger(SpringBootProviderBootstrap.class);

	public static void main(String[] args) throws IOException {
		SpringApplication application = new SpringApplication(SpringBootProviderBootstrap.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
		logger.info("springboot provider start success");
	}

}
